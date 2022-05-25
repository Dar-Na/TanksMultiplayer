package messages;

import server.TankFromServer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MessageToClient implements Serializable {
    private String message;

    public String getMessage() {
        return message;
    }

    private List<TankFromServer> tanks;

    public List<TankFromServer> getTanks() {
        return tanks;
    }

    public void setTanks(List<TankFromServer> tanks) {
        this.tanks = new ArrayList<>(tanks);
    }

    public MessageToClient(String message){
       this.message = message;
   }
}
