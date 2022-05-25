package messages;

import java.io.Serializable;

public class MessageToServer implements Serializable {
    private String message;
    public MessageToServer(String message){
        this.message = message;
    }
    public String getMessage(){
        return message;
    }

}
