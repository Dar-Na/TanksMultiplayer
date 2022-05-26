package messages;

import java.io.Serializable;

public class MessageToServer implements Serializable {
    private String message;
    private int mouseX;
    private int mouseY;

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public MessageToServer(String message, int mouseX, int mouseY){
        this.message = message;
        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }
    public String getMessage(){
        return message;
    }

}
