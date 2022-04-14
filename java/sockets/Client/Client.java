package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import Message.Message;

public class Client {
    private final Integer port = 1500;

    String createRequest(int n){
        try {
            InetAddress address = InetAddress.getLocalHost();
            Socket socket = new Socket(address, this.port);

            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

            String response = (String) inputStream.readObject();
            if(!response.equals("gotowy")) return "Blad";
            outputStream.writeObject(n);

            response = (String) inputStream.readObject();
            if(!response.equals("gotowy na wiadomosci")) return "Blad";

            for(int i = 0; i < n; i++) outputStream.writeObject(new Message("Wiadomosc " + (i+1)));

            response = (String) inputStream.readObject();
            if(!response.equals("skonczone")) return "Blad";
            return response;
        } catch (IOException except){
            System.out.format("Cos nie poszlo.%n* %s%nKlient odmowil polaczenia.", except.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "Blad";
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Client client = new Client();
        boolean i = true;
        while(i){
            System.out.print("Ile wiadomosci chcesz wyslac : ");
            String line = scanner.nextLine();
            if(line.equals("exit")){
                i = false;
                System.out.println("Klient zakonczyl prace");
            }
            else{
                System.out.println(client.createRequest(Integer.parseInt(line)));
            }
        }
    }


}
