package client;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tank {
    private Point coordinates;
    private KeyHandler keyHandler;
    private JPanel jPanel;
    public BufferedImage sprite ;
    public Tank(Point coordinates,JPanel jPanel, KeyHandler keyHandler){

        this.coordinates = coordinates;
        this.keyHandler = keyHandler;
        this.jPanel = jPanel;
        System.out.println("Reading the image...");
        try {
            sprite = ImageIO.read(new File("src/main/resources/tank.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("End reading...");
    }
    public Point getCoordinates(){
        return this.coordinates;
    }
    public void setCoordinates(Point coordinates){
        this.coordinates = coordinates;

    }
    public int getX(){
        return coordinates.x;
    }
    public int getY(){
        return coordinates.y;
    }
    public void draw(Graphics2D g2){

//        g2.setColor(Color.WHITE);
//        g2.fillRect(coordinates.x,coordinates.y,50,50);
      g2.drawImage(sprite, coordinates.x,coordinates.y,50,50, null);
        jPanel.requestFocus(); // focus back to panel
    }
    public void update(){
        if(keyHandler.rightPressed){
            coordinates.x += 10;

        }
        if(keyHandler.leftPressed){
            coordinates.x -= 10;
        }
        if(keyHandler.downPressed){
            coordinates.y += 10;
        }
        if(keyHandler.upPressed){
            coordinates.y -= 10;
        }
    }
}
