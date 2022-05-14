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
        // creating streams to exchange messages with client
        try(ObjectInputStream input = new ObjectInputStream(socket.getInputStream())){
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            String message = "";
            while (!message.equals("quit")){      // while client didn't send quit message keep exchange

                message = String.valueOf(input.readObject());
                System.out.println("Message from client:");
                System.out.println(message);
                if(message.length() < 10 && !(message.equals("quit"))){     // testing - return length if message < 10
                    output.writeObject(Integer.toString(message.length()));
                }
                else{
                    output.writeObject("end");        // sending end - sygnal to stop communication
                }

            }

            output.writeObject("end");
            output.close();

        }
        catch (IOException | ClassNotFoundException ex){
            System.out.println(ex);
        }

    }
}
