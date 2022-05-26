package client;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ClientTank {
    private int width =50;
    private int height =50;
    private Point coordinates;
    private KeyHandler keyHandler;
    private JPanel jPanel;
    private int clientId;
    public BufferedImage sprite ;
    public int health;
    public int getClientId() {
        return clientId;
    }

    public ClientTank(int clientId, Point coordinates, int health,JPanel jPanel, KeyHandler keyHandler){
        this.health = health;
        this.clientId = clientId;
        this.coordinates = coordinates;
        this.keyHandler = keyHandler;
        this.jPanel = jPanel;

        try {
            sprite = ImageIO.read(new File("src/main/resources/tank.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }


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
        // health panel
        int healthWidthMax = 100;
        int healthHeight = 10;
        int offsetX = 25;
        int offsetY = 50;
        g2.setColor(Color.RED);
        g2.fillRect(coordinates.x - offsetX,coordinates.y - offsetY, healthWidthMax, healthHeight);
        g2.setColor(Color.GREEN);
        g2.fillRect(coordinates.x - offsetX,coordinates.y - offsetY, (int)(healthWidthMax * (health/100.0)), healthHeight);
        g2.drawImage(sprite, coordinates.x,coordinates.y,width,height, null);

        jPanel.requestFocus(); // focus back to panel
    }
    public void update(){

    }
}
