package client;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    final int screenWidth = 500;
    final int screenHeight = 500;
    Thread gameThread;
    KeyHandler keyHandler;

    // FPS
    final  int fps = 60;
    Tank tank;
    public GamePanel(){
        System.out.println("Creating key handler");
        keyHandler = new KeyHandler();


        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // will improve game's rendering

        this.addKeyListener(keyHandler); // frame is now respond for key events
        this.setFocusable(true); // for key handling
        this.setLayout(null);

        tank = new Tank(new Point(200,200), this, keyHandler);
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
    public void update(){
        tank.update();
    }
    public void paintComponent( Graphics g){
        super.paintComponent(g);            // JPanel class is parent
        Graphics2D g2 = (Graphics2D) g;     // More functions for 2d drawings
        tank.draw(g2);
        g2.dispose();
    }
}
