package client;

import messages.MessageClientServer;
import server.STank;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements Runnable {
    final int screenWidth = 500;
    final int screenHeight = 500;
    Thread gameThread;
    KeyHandler keyHandler;
    ClientLogic clientLogic;
    // FPS
    final  int fps = 60;
    private List<CTank> clientTanks;

    public GamePanel(ClientLogic client){
        this.clientLogic = client;
        System.out.println("Creating key handler");
        keyHandler = new KeyHandler();

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.ORANGE);
        this.setDoubleBuffered(true); // will improve game's rendering

        this.addKeyListener(keyHandler); // frame is now respond for key events
        this.setFocusable(true); // for key handling
        this.setLayout(null);
        clientTanks = new ArrayList<>();

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
        clientLogic.setMessageClientServer(new MessageClientServer(message));
    }
    public void update(){
        List<STank> sTanks = clientLogic.getServerTanks();
        clientTanks = new ArrayList<>();
        for (STank sTank : sTanks){
            int clientId = sTank.getId();
            Point coords = sTank.getCoordinates();

            CTank tank = new CTank(clientId,coords,this,keyHandler);
            clientTanks.add(tank);
            //System.out.println("Tank with id " + tank.getClientId() + " Coords " + tank.getX() + " "+ tank.getY());
        }

        for(CTank tank : clientTanks){
            tank.update();

        }
        //tank = new Tank(new Point(200,200), this, keyHandler);



    }
    public void paintComponent( Graphics g){
        super.paintComponent(g);            // JPanel class is parent
        Graphics2D g2 = (Graphics2D) g;     // More functions for 2d drawings

        for(CTank tank : clientTanks){
            tank.draw(g2);

        }
        g2.dispose();
    }

}
