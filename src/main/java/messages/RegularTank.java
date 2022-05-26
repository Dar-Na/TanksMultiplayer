package messages;

import java.io.Serializable;

public class RegularTank implements Serializable {
    public int x;
    public int y;
    public int health;
    public RegularTank(int x,int y,int health){
        this.x = x;
        this.y = y;
        this.health = health;
    }

}
