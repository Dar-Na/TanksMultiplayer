package server;

import java.io.*;
import java.net.Socket;

public class SocketConnectionHandler implements Runnable {
    private final Socket socket;
    public SocketConnectionHandler(Socket socket) {
        this.socket = socket;

    }
    @Override
    public void run() {
        try(ObjectInputStream input = new ObjectInputStream(socket.getInputStream())){
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            while (true){

                String message = String.valueOf(input.readObject());
                System.out.println("Message from client:");
                System.out.println(message);
                if(message.length() < 10){
                    output.writeObject(Integer.toString(message.length()));
                }
                else{
                    output.writeObject("end");
                }
            }

        }
        catch (IOException | ClassNotFoundException ex){
            System.out.println(ex);
        }

    }
}
