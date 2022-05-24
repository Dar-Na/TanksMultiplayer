package server;

import messages.MessageServerClient;

import java.util.ArrayList;
import java.util.List;

public  class ServerLogic { // Server logic
    private static ServerLogic instance = null;
    public static ServerLogic getInstance(){
        if(instance == null)
            instance = new ServerLogic();
        return instance;
    }
    private static List<STank> tanks ;

    public synchronized List<STank> getTanks() {
        return tanks;
    }
    public synchronized STank getTankWithId(int id){
        for (var tank : tanks){
            if(tank.getId() == id){
                return tank;
            }
        }
        return null;
    }
    public synchronized void addTank(STank tank){
        tanks.add(tank);
    }

    public ServerLogic(){
        tanks = new ArrayList<>();
    }
    public synchronized MessageServerClient getMessage(){    // creating message with game state to be sent to client
        MessageServerClient messageServerClient = new MessageServerClient("REGULAR");
        messageServerClient.setTanks(tanks);

        return messageServerClient;
    }
}
