package messages;

import java.io.Serializable;

public class MessageToServer implements Serializable {

    private int mouseX;
    private int mouseY;
    public boolean pressed;
    public boolean up;
    public boolean down;
    public boolean right;
    public boolean left;
    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }
    public MessageToServer(){
        this.pressed = false;
        this.down = false;
        this.up = false;
        this.left = false;
        this.right = false;
        this.mouseX = 0;
        this.mouseY = 0;
    }
    public MessageToServer(boolean pressed,boolean up,boolean down, boolean right,boolean left, int mouseX, int mouseY){
        this.pressed = pressed;
        this.down = down;
        this.up = up;
        this.left = left;
        this.right = right;
        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }


}
