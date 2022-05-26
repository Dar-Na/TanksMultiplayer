package messages;

import java.io.Serializable;

public class RegularBullet implements Serializable {
    public int x;
    public int y;
    public RegularBullet(int x, int y){
        this.x = x;
        this.y = y;
    }

}
