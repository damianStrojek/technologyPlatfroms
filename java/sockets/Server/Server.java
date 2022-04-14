package Server;

public class Server {
    private static int port = 1500;

    public static void main(String[] args) {
        System.out.println("Rozpoczeto serwer na porcie " + port);
        new Thread(new IncomingSocketHandler(port)).start();
    }
}
