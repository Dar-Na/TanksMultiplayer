package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server {
    private int port;
    public Server(int port){
        this.port = port;

    }
    public void start(){
        int clientNumber = 2;                     // number of clients should be known before gamer

        LinkedList<Socket> clients = new LinkedList<>();
        try {
            ServerSocket listner = new ServerSocket(9797);           // Start listining port 9797
            for(int i = 0;i< clientNumber; i++) {                           // getting all clients

                Socket client = listner.accept();
                clients.add(client);                                     // Adding client to list of sockets

            }

            Game game = new Game(clients);
            game.run();
        }
        catch (IOException ex){
            throw new IllegalStateException();
        }
    }
}
