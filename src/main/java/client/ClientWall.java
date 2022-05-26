package client;

import javax.swing.*;
import java.awt.*;

public class ClientWall{
    private Point coordinates;


    private int width;
    private int height;
    private JPanel jPanel;


    public ClientWall(Point coordinates,int width, int height, JPanel jPanel){
        this.coordinates = coordinates;
        this.width = width;
        this.height = height;
        this.jPanel = jPanel;
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
        g2.setColor(Color.BLACK);
        g2.fillRect(coordinates.x, coordinates.y, width, height);

        jPanel.requestFocus(); // focus back to panel
    }
    public void update(){

    }
}
