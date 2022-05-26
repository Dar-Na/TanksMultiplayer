package server;

import java.io.Serializable;

public class ServerBullet implements Serializable {
    public  int x;
    public int y;
    public int xSpeed;
    public int ySpeed;
    public int clientId;

    ServerBullet(int x, int y,int xSpeed,int ySpeed, int clientId){
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.clientId = clientId;
    }
}
