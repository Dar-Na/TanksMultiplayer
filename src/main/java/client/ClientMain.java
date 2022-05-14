package client;

public class ClientMain {
    public static void main(String[] args) {
        // Server Communication
        System.out.println("Creating a client.");
        Client client = new Client("127.0.0.1", 9797);
        Thread thread = new Thread(client);
        thread.start();

        // Game drawing
        GameView gameView = new GameView();

    }
}
