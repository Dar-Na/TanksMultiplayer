package server;

import messages.MessageToClient;
import messages.MessageToServer;
import messages.RegularBullet;
import messages.RegularTank;

import java.awt.*;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Random;

public class Game{

    private LinkedList<Client> clients;
    private LinkedList<ServerTank> serverTanks;
    private LinkedList<ServerWall> serverWalls;
    private LinkedList<ServerBullet> serverBullets;
    private int xBorder = 1280;
    private int yBorder = 720;

    public Game(LinkedList<Socket> sockets){
        LinkedList<Point> tankSpots = new LinkedList<>();
        tankSpots.add(new Point(70,70));
        tankSpots.add(new Point(1150,70));
        tankSpots.add(new Point(70,500));
        tankSpots.add(new Point(1150,500));



        int numOfClients =0;
        this.serverTanks = new LinkedList<>();
        this.serverWalls = new LinkedList<>();
        initWalls();
        this.serverBullets = new LinkedList<>();
        clients = new LinkedList<>();
        Random random = new Random();
        for(var socket : sockets){
            clients.add(new Client(socket,numOfClients));
            int x = random.nextInt(800);
            int y = random.nextInt(600);
            Point tankSpot = tankSpots.get(numOfClients);
            serverTanks.add(new ServerTank(tankSpot.x,tankSpot.y,numOfClients));
            numOfClients++;

        }

    }
    public void initWalls(){
        serverWalls.add(new ServerWall(200,250, 10,150));
        serverWalls.add(new ServerWall(1000,450, 10,150));
        serverWalls.add(new ServerWall(500,0, 10,300));
        serverWalls.add(new ServerWall(350,600, 10,120));
        serverWalls.add(new ServerWall(600,500, 200,10));
        serverWalls.add(new ServerWall(800,200, 300,10));
    }
    public ServerTank getTankById(int clientId){
        for (int i =0;i< serverTanks.size();i++){
            if(serverTanks.get(i).clientId == clientId){
                return serverTanks.get(i);
            }
        }
        return null;
    }
    public MessageToClient getMessage(){
        MessageToClient messageToClient = new MessageToClient("REGULAR");
        LinkedList<RegularTank> regularTanks = new LinkedList<>();
        LinkedList<RegularBullet> regularBullets = new LinkedList<>();
        for(int i = 0; i< serverTanks.size(); i++){
            regularTanks.add(new RegularTank(serverTanks.get(i).x ,serverTanks.get(i).y ));
        }
        for(int i = 0; i< serverBullets.size(); i++){
            regularBullets.add(new RegularBullet(serverBullets.get(i).x ,serverBullets.get(i).y ));
        }
        messageToClient.setTanks(regularTanks);
        messageToClient.setBullets(regularBullets);
        return messageToClient;
    }
    public void parseMessage(MessageToServer messageToServer, int clientId){
        int Tankspeed= 8;
        Point point = new Point(0,0);
        Boolean isMoved = false;
        if(messageToServer != null){

            if(messageToServer.getMessage().equals("RIGHT_PRESSED")){
                point.x = Tankspeed;
                isMoved = true;
            }
            if(messageToServer.getMessage().equals("LEFT_PRESSED")){
                point.x = -1*Tankspeed;
                isMoved = true;

            }
            if(messageToServer.getMessage().equals("UP_PRESSED")){
                point.y = -1*Tankspeed;
                isMoved = true;
            }
            if(messageToServer.getMessage().equals("DOWN_PRESSED")){
                point.y = Tankspeed;
                isMoved = true;
            }

            if(messageToServer.getMessage().equals("MOUSE_PRESSED")){
                ServerTank serverTank = getTankById(clientId);
                if(serverTank.checkReload()){
                    int tankCenterX = serverTank.x + serverTank.width/2;
                    int tankCenterY = serverTank.y + serverTank.height/2;
                    int mouseX = messageToServer.getMouseX();
                    int mouseY = messageToServer.getMouseY();
                    double vecLength = Math.sqrt(Math.pow(( tankCenterX - mouseX), 2) + Math.pow(( tankCenterY - mouseY),2));
                    int xVec =  (int)(((mouseX -  tankCenterX)*10)/vecLength) ; // normalizing vector
                    int yVec =  (int)(((mouseY -  tankCenterY)*10)/vecLength) ;
                    serverBullets.add(new ServerBullet( tankCenterX,tankCenterY,xVec,yVec,clientId));
                }
            }
            }
            if(isMoved){
                ServerTank serverTank = getTankById(clientId);
                serverTank.x += point.x;
                serverTank.y += point.y;
                for (int j =0 ; j < serverTanks.size();j++) {
                    ServerTank enemyTank = serverTanks.get(j);
                    if(serverTank.hitByTank(enemyTank)){
                        serverTank.x -= point.x;
                        serverTank.y -= point.y;
                        break;
                    }
                }
                for (int j =0 ; j < serverWalls.size();j++) {
                    ServerWall wall = serverWalls.get(j);
                    if(serverTank.hitByWall(wall)){
                        serverTank.x -= point.x;
                        serverTank.y -= point.y;
                        break;
                    }
                }
            }

    }
    public void run(){
        int fps = 60;
        double drawInterval = 1000000000 / fps; // in nano secomds // draw screen ever drawInterwall
        double nextDrawTime = System.nanoTime() + drawInterval;

        // MESSAGE CYCLE
        while (true){
            // communication with clients
            for (Client client : clients){

                try {
                    MessageToServer messageToServer = (MessageToServer)client.in.readObject();
                    if(messageToServer != null && messageToServer.getMessage().equals("MOUSE_PRESSED")){
                        System.out.println("Mouse PRESSED " + messageToServer.getMouseX() + " "+messageToServer.getMouseY());
                    }
                    parseMessage(messageToServer,client.id);
                    client.out.writeObject(getMessage());



                } catch (ClassNotFoundException|IOException e) {
                    e.printStackTrace();
                }

            }
            // calculating 60 fps
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/ 1000000;
                if(remainingTime < 0){
                    remainingTime = 0;
                }
                Thread.sleep((long)remainingTime );
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // calculating bullets position
            for (int i =0 ; i < serverBullets.size();i++){

                ServerBullet serverBullet = serverBullets.get(i);

                for (int j =0 ; j < serverTanks.size();j++) {

                    ServerTank tank = serverTanks.get(j);

                    if(tank.clientId != serverBullet.clientId && tank.hitByBullet(serverBullet)){
                        serverTanks.remove(tank);
                        serverBullets.remove(serverBullet);
                    }

                }
                for (int j =0 ; j < serverWalls.size();j++) {

                    ServerWall serverWall = serverWalls.get(j);

                    if(serverWall.hitByBullet(serverBullet)){

                        serverBullets.remove(serverBullet);
                    }
                }

                serverBullet.x += serverBullet.xSpeed;
                serverBullet.y += serverBullet.ySpeed;
                if(serverBullet.x > xBorder || serverBullet.x < 0){
                    serverBullets.remove(serverBullet);
                }
                else if(serverBullet.y > yBorder || serverBullet.y < 0){
                    serverBullets.remove(serverBullet);
                }



            }
        }
    }

}
