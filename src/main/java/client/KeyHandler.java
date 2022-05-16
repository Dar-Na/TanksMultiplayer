package client;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean leftPressed,rightPressed,upPressed,downPressed = false;
    @Override
    public void keyTyped(KeyEvent e) {
        // key type is invoked when key is typed Uses KeyChar, char output

        switch (e.getKeyChar()){
            case 'a':
                leftPressed = true;
                break;
            case 'w':
                upPressed = true;
                break;
            case 's':
                downPressed = true;
                break;
            case 'd':
                rightPressed = true;
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //Key pressed = Invoke when a physical key is pressed down .Uses KeyCode , int output

    }

    @Override
    public void keyReleased(KeyEvent e) {
        //Whenever a button is released
//        System.out.println("You released key char " + e.getKeyChar());
//        System.out.println("You released key code " + e.getKeyCode());
        switch (e.getKeyChar()){
            case 'a':
                leftPressed = false;

                break;
            case 'w':
                upPressed = false;
                break;
            case 's':
                downPressed = false;
                break;
            case 'd':
                rightPressed = false;
                break;
        }

    }
}
