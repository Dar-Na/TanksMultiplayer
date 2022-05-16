package server;

import messages.MessageClientServer;
import messages.MessageServerClient;

import java.io.*;
import java.net.Socket;

public class SocketConnectionHandler implements Runnable {
    private final Socket socket;
    private final int unique_id;
    public SocketConnectionHandler(Socket socket, int unique_id) {
        this.socket = socket;
        this.unique_id = unique_id;
    }
    @Override
    public void run() {
        // creating streams to exchange messages with client
        try(ObjectInputStream input = new ObjectInputStream(socket.getInputStream())){
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            String message = "";
            while (!message.equals("quit")){      // while client didn't send quit message keep exchange
                MessageClientServer messageClientServer;
                messageClientServer = (MessageClientServer)input.readObject();
                System.out.println("Message from client:" +  unique_id);
                System.out.println(messageClientServer.getMessage());
                MessageServerClient messageServerClient = new MessageServerClient("Got your message - Server probably");

                output.writeObject(messageServerClient);


            }

            output.writeObject("end");
            output.close();

        }
        catch (IOException | ClassNotFoundException ex){
            System.out.println(ex);
        }

    }
}
