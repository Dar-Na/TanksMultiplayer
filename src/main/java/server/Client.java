package server;

import messages.MessageToClient;
import messages.MessageToServer;

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
            this.out = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            System.out.println("out created");
            this.in = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
            System.out.println("in created");
            MessageToServer messageToServer = (MessageToServer)in.readObject();
            System.out.println(messageToServer.getMessage());
            out.writeObject(new MessageToClient("Init Client"));
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
