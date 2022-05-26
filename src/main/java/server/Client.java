package server;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket socket;
    public int id;
    public ObjectOutputStream out;
    public ObjectInputStream in;
    public Client(Socket socket,int id){
        this.socket = socket;
        this.id = id;
        try {
            this.out = new ObjectOutputStream(socket.getOutputStream());

            this.in = new ObjectInputStream(socket.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
