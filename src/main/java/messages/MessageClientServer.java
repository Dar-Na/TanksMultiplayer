package messages;

import java.io.Serializable;

public class MessageClientServer implements Serializable {
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageClientServer(String message){
        this.message = message;
    }
}
