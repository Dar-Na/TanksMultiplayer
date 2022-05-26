package messages;

import java.io.Serializable;

public class RegularTank implements Serializable {
    public int x;
    public int y;
    public RegularTank(int x,int y){
        this.x = x;
        this.y = y;
    }

}
