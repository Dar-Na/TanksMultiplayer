package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class IncomingSocketConnectionHandler implements Runnable{
    private final ServerSocket server;

    public IncomingSocketConnectionHandler(int port){
        try{
            System.out.println("Opening server at port " + port);
            server = new ServerSocket(port); // server socket for listening clients
            server.setSoTimeout(1000);  // will block a call to accept for sertain time
        }
        catch (IOException ex){
            throw new IllegalStateException();
        }
    }

    @Override
    public void run() {
        System.out.println("Started Server...");
        while(!Thread.interrupted()){
            try{

                Socket socket = server.accept(); // thread to accept client
                System.out.println("Client connected");
                new Thread(new SocketConnectionHandler(socket)).start(); // working with client
            }
            catch (SocketTimeoutException ex){ // information about timeout
                //System.out.println(ex);
            }
            catch (IOException ex){
                System.out.println(ex);
            }
        }
    }
}
