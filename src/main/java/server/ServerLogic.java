package server;

import messages.MessageToClient;
import messages.MessageToServer;

import java.util.*;

public class ServerLogic {
    private int numOfClients = 0;
    private List<TankFromServer> tanks;


    public List<TankFromServer> getTanks() {
        return tanks;
    }

    public int getNumOfClients() {
        return numOfClients;
    }

    public void incNumOfClients() {

        this.numOfClients++;
    }

    public ServerLogic(){

        tanks =new ArrayList<>();

    }

    public void addTank(int clientId){
        Random random= new Random();
        int x= random.nextInt(500);
        int y= random.nextInt(500);
        tanks.add(new TankFromServer(x,y,clientId));
    }
    public   MessageToClient getMessage(){
        MessageToClient messageToClient = new MessageToClient("REGULAR");
        messageToClient.setTanks(new ArrayList<>(tanks));
        for (var tank : messageToClient.getTanks()){
            System.out.println(tank.x + " " + tank.y);
        }
        return messageToClient;
    }

    public void parseMessage(MessageToServer messageFromClient) {
        String message = messageFromClient.getMessage();

        if(message.equals("RIGHT_PRESSED")){
           System.out.println("PRESSED RIGHT");
           tanks.get(0).x+=2;
        }

    }
}
