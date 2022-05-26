package client;

import messages.MessageToServer;
import messages.RegularBullet;
import messages.RegularTank;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements Runnable {

    Thread gameThread;
    KeyHandler keyHandler;
    MouseHandler mouseHandler;
    ClientLogic clientLogic;
    private java.util.List<ClientTank> clientTanks;
    private java.util.List<ClientBullet> clientBullets;
    private List<ClientWall> clientWalls;
    public synchronized List<ClientTank> getClientTanks(){
        clientTanks = new ArrayList<>();
        if(clientLogic.getRegularTanks() != null){
            for(RegularTank regularTank : clientLogic.getRegularTanks()){
                clientTanks.add(new ClientTank(0, new Point(regularTank.x, regularTank.y),regularTank.health,this,keyHandler));
            }
        }

        return clientTanks;
    }
    public synchronized List<ClientBullet> getClientBullets(){
        clientBullets = new ArrayList<>();
        if(clientLogic.getRegularBullets() != null){
            for(RegularBullet regularBullet : clientLogic.getRegularBullets()){
                clientBullets.add(new ClientBullet(0, new Point(regularBullet.x, regularBullet.y),this,keyHandler));
            }
        }

        return clientBullets;
    }
    public void initWalls(){
        clientWalls.add(new ClientWall(new Point(200,250), 10,150,this));
        clientWalls.add(new ClientWall(new Point(1000,450), 10,150,this));
        clientWalls.add(new ClientWall(new Point(500,0), 10,300,this));
        clientWalls.add(new ClientWall(new Point(350,600), 10,120,this));
        clientWalls.add(new ClientWall(new Point(600,500), 200,10,this));
        clientWalls.add(new ClientWall(new Point(800,200), 300,10,this));
    }
    // FPS
    final  int fps = 60;


    public GamePanel(ClientLogic client){
        this.clientTanks = new ArrayList<>();
        this.clientBullets = new ArrayList<>();
        this.clientWalls = new ArrayList<>();
        initWalls();

        this.clientLogic = client;
        System.out.println("Creating key handler");
        keyHandler = new KeyHandler();
        mouseHandler = new MouseHandler();
        this.setPreferredSize(new Dimension(clientLogic.screenWidth, clientLogic.screenHeight));
        this.setBackground(Color.ORANGE);
        this.setDoubleBuffered(true); // will improve game's rendering

        this.addKeyListener(keyHandler); // frame is now respond for key events
        this.addMouseListener(mouseHandler);
        this.setFocusable(true); // for key handling
        this.setLayout(null);


    }
    public void startGameThread(){
        this.gameThread = new Thread(this); // starting game loop
        gameThread.start();
    }
    @Override
    public void run() {
        double drawInterval = 1000000000 / fps; // in nano secomds // draw screen ever drawInterwall
        double nextDrawTime = System.nanoTime() + drawInterval;
        while(gameThread != null){  // creating game loop
            // UPDATE INFORMATION

            // Information
            update();
            //Drawing
            repaint();
            //Sending to server
            keyUpdate();

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
        }
    }
    public void keyUpdate(){
        String message = "";
        if(keyHandler.rightPressed){
            message = "RIGHT_PRESSED";

        }
        if(keyHandler.leftPressed){
            message = "LEFT_PRESSED";
        }
        if(keyHandler.downPressed){
            message = "DOWN_PRESSED";;
        }
        if(keyHandler.upPressed){
            message = "UP_PRESSED";
        }
        if(mouseHandler.mousePressed){
            message = "MOUSE_PRESSED";

        }

        clientLogic.setMessageClientServer(new MessageToServer(message, mouseHandler.x,mouseHandler.y));


    }
    public void update(){

        getClientTanks();
        getClientBullets();
    }
    public void paintComponent( Graphics g){
        super.paintComponent(g);            // JPanel class is parent
        Graphics2D g2 = (Graphics2D) g;     // More functions for 2d drawings

        for(int i =0;i<clientTanks.size();i++){
           clientTanks.get(i).draw(g2);
        }
        for(int i =0;i<clientBullets.size();i++){
            clientBullets.get(i).draw(g2);
        }
        for(int i =0;i<clientWalls.size();i++){
            clientWalls.get(i).draw(g2);
        }

        g2.dispose();
    }

}
