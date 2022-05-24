package client;

public class ClientMain {
    public static void main(String[] args) {
        // Server Communication
        System.out.println("Creating a client.");
        ClientLogic clientLogic = new ClientLogic("127.0.0.1", 9797);
        Thread thread = new Thread(clientLogic);
        thread.start(); // thread for communication

        // Game drawing

        GameView gameView = new GameView(clientLogic);


    }
}
