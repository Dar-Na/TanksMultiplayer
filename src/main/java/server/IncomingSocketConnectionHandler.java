package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class IncomingSocketConnectionHandler implements Runnable{
    private final ServerSocket serverSocket;
    private static int numOfClients = 0;
    private ServerLogic server;
    public IncomingSocketConnectionHandler(int port, ServerLogic server){

        this.server = server;
        try{
            System.out.println("Opening server at port " + port);
            serverSocket = new ServerSocket(port); // server socket for listening clients
            serverSocket.setSoTimeout(1000);  // will block a call to accept for certain time
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

                Socket socket = serverSocket.accept(); // thread to accept client
                System.out.println("Client connected");
                int uniqueId = numOfClients;
                numOfClients++;
                new Thread(new SocketConnectionHandler(socket, uniqueId)).start(); // working with client
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
