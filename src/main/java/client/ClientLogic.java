package client;

import messages.MessageToClient;
import messages.MessageToServer;
import messages.RegularBullet;
import messages.RegularTank;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Random;

public class ClientLogic implements Runnable{      // Communication with Server

    public final int screenWidth = 1280;
    public final int screenHeight = 720;
    private String serverIp;
    private Socket client;
    private int port;
    private MessageToServer messageToServer;
    private LinkedList<RegularTank> regularTanks;
    private LinkedList<RegularBullet> regularBullets;

    public LinkedList<RegularTank> getRegularTanks() {
        return regularTanks;
    }
    public LinkedList<RegularBullet> getRegularBullets() {
        return regularBullets;
    }
    public void setMessageClientServer(MessageToServer msg) {
        this.messageToServer =msg;

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
        if(messageToClient.getTanks() != null){
           this.regularTanks = messageToClient.getTanks();
        }
        if(messageToClient.getBullets() != null){
            this.regularBullets = messageToClient.getBullets();
        }
    }
    @Override
    public void run() {                                         // Communiction with server in thread

        try {
            // Streams
            ObjectOutputStream out =  new ObjectOutputStream(client.getOutputStream());

            System.out.println("out created");
            ObjectInputStream in = new ObjectInputStream(client.getInputStream());
            System.out.println("in created");
            String serverCmd = "";
            // MESSAGE CYCLE
            Random random = new Random();
            int rand = random.nextInt();
            while (!serverCmd.equals("end")){
                 if(messageToServer == null) {
                    this.messageToServer = new MessageToServer(" ",0,0);
                 }
                 out.writeObject(messageToServer);

                 messageToServer = new MessageToServer("",0,0);     // set Message to empty
                 MessageToClient messageFromServer = (MessageToClient) in.readObject();
                 parseMessage(messageFromServer);
                 var bullets = messageFromServer.getBullets();
                 for (int i =0;i<bullets.size();i++){
                     System.out.println(bullets.size());
                 }

            }
            in.close();
            out.close();

            // Streams Closed
           // TODO

        }
        catch (IOException  | ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }
}
