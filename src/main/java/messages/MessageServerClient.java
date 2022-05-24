package messages;

import server.STank;

import java.io.Serializable;
import java.util.List;

public class MessageServerClient implements Serializable {
    String message;
    private List<STank> tanks;
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public void setTanks(List<STank> tanks) {
        this.tanks = tanks;
    }

    public List<STank> getTanks() {
        return tanks;
    }

    public MessageServerClient(String message){
        this.message = message;
    }
}
