package client;

import messages.MessageClientServer;
import messages.MessageServerClient;
import server.STank;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientLogic implements Runnable{      // Communication with Server




    private List<STank> serverTanks;
    private String serverIp;
    private Socket client;
    private int port;
    private MessageClientServer messageClientServer;

    public void setMessageClientServer(MessageClientServer messageClientServer) {
        this.messageClientServer = messageClientServer;
    }

    public List<STank> getServerTanks() {
        return serverTanks;
    }

    public ClientLogic(String serverIp, int serverPort){    // Creating Client socket
        this.serverTanks = new ArrayList<>();
        this.serverIp = serverIp;
        this.port = serverPort;
        try{
            client = new Socket(serverIp,port);
        }
        catch (IOException ex){
            System.out.println(ex);
        }

    }
    public void parseMessage(MessageServerClient messageServerClient){      // Parsing message from server
        String message = messageServerClient.getMessage();
        if(message.equals("REGULAR")){
            serverTanks = messageServerClient.getTanks();   // server tanks

        }
    }
    @Override
    public void run() {                                         // Communiction with server in thread
        Scanner scanner  = new Scanner( System.in);
        try {
            OutputStream os = client.getOutputStream();
            InputStream is = client.getInputStream();


            ObjectOutputStream oos = new ObjectOutputStream(os);
            ObjectInputStream ios = new ObjectInputStream(is);



            // FIRST MESSAGE
            MessageClientServer addTankMessage = new MessageClientServer("ADD_TANK");
            oos.writeObject(addTankMessage);


            String serverCmd = "";
            // MESSAGE CYCLE
            while (!serverCmd.equals("end")){


                //System.out.println("Print message to a server");
                //String message = scanner.nextLine();
                if(messageClientServer == null){
                    messageClientServer = new MessageClientServer("NULL");

                }
                else {
                    //System.out.println(messageClientServer.getMessage());
                }
                oos.writeObject(messageClientServer);
                messageClientServer = new MessageClientServer("NULL");


                // Getting state from server
                MessageServerClient messageServerClient = (MessageServerClient) ios.readObject();


                for(STank tank :  messageServerClient.getTanks()){
                    System.out.println(tank.getCoordinates().x + " " +tank.getCoordinates().y);
                }

                parseMessage(messageServerClient); // TODO

            }
            ios.close();
            oos.close();
            is.close();
            os.close();

        }
        catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }
}
