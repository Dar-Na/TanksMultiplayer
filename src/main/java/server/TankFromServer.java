package server;

import java.io.Serializable;

public class TankFromServer implements Serializable {
    public  int x;
    public int y;
    private int clientId;

    TankFromServer(int x, int y,int clientId){
        this.x = x;
        this.y = y;
        this.clientId = clientId;
    }
}
