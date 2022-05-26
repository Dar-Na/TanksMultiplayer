package server;

import java.io.Serializable;

public class ServerBullet implements Serializable {
    public  int x;
    public int y;
    public int height = 10;
    public int width = 10;
    public int xSpeed;
    public int ySpeed;
    public int clientId;
    public int damage;
    ServerBullet(int x, int y,int xSpeed,int ySpeed, int clientId){
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.clientId = clientId;
        this.damage = 20;
    }
}
