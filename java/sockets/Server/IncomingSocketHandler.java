package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class IncomingSocketHandler implements Runnable {
    private final int port;
    private ServerSocket serverSocket;

    public IncomingSocketHandler(int port){
        this.port = port;
        try {
            this.serverSocket = new ServerSocket(this.port);
            this.serverSocket.setSoTimeout(2000);
        } catch (IOException except){
            except.printStackTrace();
        }
    }

    @Override
    public void run(){
        while(!Thread.interrupted()){
            try {
                Socket socket = serverSocket.accept();
                new Thread(new SocketHandler(socket)).start();
            } catch (SocketTimeoutException except){
                System.out.println("Serwer timeout");
            } catch (IOException except){
                except.printStackTrace();
            }
        }
    }
}
