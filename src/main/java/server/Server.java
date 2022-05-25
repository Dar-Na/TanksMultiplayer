package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int port;
    public Server(int port){
        this.port = port;

    }
    public void start(){
        System.out.println("I am a server");
        ServerLogic serverLogic = new ServerLogic();
        try {
            ServerSocket listner = new ServerSocket(9797);           // Start listining port 9797
            while (true) {
                System.out.println("Server waiting for connections");
                Socket client = listner.accept();
                System.out.println("Client connected");
                ClientHandler clientHandler = new ClientHandler(client,serverLogic);
                Thread clientThread = new Thread(clientHandler);
                clientThread.start();
            }
        }
        catch (IOException ex){
            throw new IllegalStateException();
        }
    }
}
