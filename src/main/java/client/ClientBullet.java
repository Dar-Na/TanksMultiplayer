package client;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ClientBullet {
    private Point coordinates;
    private KeyHandler keyHandler;
    private JPanel jPanel;
    private int clientId;
    public BufferedImage sprite ;

    public int getClientId() {
        return clientId;
    }

    public ClientBullet(int clientId, Point coordinates, JPanel jPanel, KeyHandler keyHandler){

        this.clientId = clientId;
        this.coordinates = coordinates;
        this.keyHandler = keyHandler;
        this.jPanel = jPanel;

        try {
            sprite = ImageIO.read(new File("src/main/resources/bullet.png"));
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
        g2.drawImage(sprite, coordinates.x,coordinates.y,10,10, null);

        jPanel.requestFocus(); // focus back to panel
    }
    public void update(){

    }
}
