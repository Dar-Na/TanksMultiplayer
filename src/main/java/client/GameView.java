package client;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameView extends JFrame implements KeyListener {
    JLabel label;
    ImageIcon icon;
    GameView(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closing when x is pressed
        this.setSize(500,500);
        this.setLayout(null);
        this.addKeyListener(this); // frame is now respond for key events

        icon = new ImageIcon("src/main/resources/tankImage.png");
        label = new JLabel();
        label.setBounds(0,0,100,100);
        //label.setBackground(Color.RED);
        //label.setOpaque(true);// display background color
        label.setIcon(icon);
        this.add(label);
        this.setVisible(true); // frame is visible
    }
    @Override
    public void keyTyped(KeyEvent e) {
        // key type is invoked when key is typed Uses KeyChar, char output
        switch (e.getKeyChar()){
            case 'a':
                label.setLocation(label.getX()-10, label.getY());
                break;
            case 'w':
                label.setLocation(label.getX(), label.getY()-10);
                break;
            case 's':
                label.setLocation(label.getX(), label.getY()+10);
                break;
            case 'd':
                label.setLocation(label.getX()+10, label.getY());
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //Key pressed = Invoke when a physical key is pressed down .Uses KeyCode , int output
        switch (e.getKeyCode()){
            case 37:
                label.setLocation(label.getX()-10, label.getY());
                break;
            case 38:
                label.setLocation(label.getX(), label.getY()-10);
                break;
            case 39:
                label.setLocation(label.getX()+10, label.getY());
                break;
            case 40:
                label.setLocation(label.getX(), label.getY()+10);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //Whenever a button is released
//        System.out.println("You released key char " + e.getKeyChar());
//        System.out.println("You released key code " + e.getKeyCode());

    }
}
