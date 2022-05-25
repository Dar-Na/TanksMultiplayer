package client;

import messages.MessageToClient;
import messages.MessageToServer;
import server.TankFromServer;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClientLogic implements Runnable{      // Communication with Server
    
    private String serverIp;
    private Socket client;
    private int port;
    private MessageToServer messageToServer;
    private List<TankFromServer> tanksFromServer;

    public List<TankFromServer> getTanksFromServer() {
        return tanksFromServer;
    }

    public void setMessageClientServer(MessageToServer messageClientServer) {
        this.messageToServer = messageClientServer;
    }



    public ClientLogic(String serverIp, int serverPort){    // Creating Client socket

        this.serverIp = serverIp;
        this.port = serverPort;
        try{
            client = new Socket(serverIp,port);
        }
        catch (IOException ex){
            System.out.println(ex);
        }

    }
    public void parseMessage(MessageToClient messageToClient){      // Parsing message from server
        if(messageToClient.getMessage() != null){
           this.tanksFromServer = new ArrayList<>(messageToClient.getTanks());

        }
    }
    @Override
    public void run() {                                         // Communiction with server in thread

        try {
            // Streams
            OutputStream os = client.getOutputStream();
            InputStream is = client.getInputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            ObjectInputStream ios = new ObjectInputStream(is);



            String serverCmd = "";
            // MESSAGE CYCLE
            Random random = new Random();
            int rand = random.nextInt();
            while (!serverCmd.equals("end")){

                oos.writeObject(messageToServer);
                MessageToClient messageFromServer = (MessageToClient) ios.readObject();

                parseMessage(messageFromServer);


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
