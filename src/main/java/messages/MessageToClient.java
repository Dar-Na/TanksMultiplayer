package messages;

import java.io.Serializable;
import java.util.LinkedList;

public class MessageToClient implements Serializable {
    private String message;

    public String getMessage() {
        return message;
    }

    private LinkedList<RegularTank> tanks;
    private LinkedList<RegularBullet> bullets;

    public LinkedList<RegularTank> getTanks() {
        return tanks;
    }

    public void setTanks(LinkedList<RegularTank> tanks) {
        this.tanks =tanks;
    }

    public LinkedList<RegularBullet> getBullets() {
        return bullets;
    }

    public void setBullets(LinkedList<RegularBullet> bullets) {
        this.bullets = bullets;
    }

    public MessageToClient(String message){
       this.message = message;
   }
}
