package messages;

import java.io.Serializable;

public class MessageServerClient implements Serializable {
    String message;
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public MessageServerClient(String message){
        this.message = message;
    }
}
