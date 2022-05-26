package server;

import java.io.Serializable;

public class ServerBullet implements Serializable {
    public double x;
    public double y;
    public int height = 10;
    public int width = 10;
    public double xSpeed;
    public double ySpeed;
    public int clientId;
    public int damage;
    ServerBullet(double x, double y,double xSpeed,double ySpeed, int clientId){
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.clientId = clientId;
        this.damage = 20;
    }
}
