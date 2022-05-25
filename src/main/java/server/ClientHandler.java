package server;

import messages.MessageToServer;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private int clientId=0;
    private Socket client;
    private ServerLogic serverLogic;
    public ClientHandler(Socket client,ServerLogic serverLogic){
        this.client = client;
        this.serverLogic = serverLogic;

    }
    @Override
    public void run() {
        try {
            this.serverLogic.incNumOfClients();
            clientId = serverLogic.getNumOfClients();    // getting id from new client
            serverLogic.addTank(clientId);         // Adding client tank to a server
            // Streams
            OutputStream os = client.getOutputStream();
            InputStream is = client.getInputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            ObjectInputStream ios = new ObjectInputStream(is);
            String clientCmd = "";
            // MESSAGE CYCLE
            while (!clientCmd.equals("end")){
                MessageToServer messageFromClient = (MessageToServer)ios.readObject();
                if(messageFromClient != null) {
                serverLogic.parseMessage(messageFromClient);
                }

                oos.writeObject(serverLogic.getMessage());


            }
            // Streams Closed
            ios.close();
            oos.close();
            is.close();
            os.close();

        }
        catch (IOException  | ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }
}
