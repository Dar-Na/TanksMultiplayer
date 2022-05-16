package client;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// Drawing world
public class GameView extends JFrame implements MouseListener {

    Thread gameThread;

    GameView(){



        this.setSize(500,500);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Tanks");
        this.setLocationRelativeTo(null); // putting frame in the center
        this.setVisible(true); // frame is visible
        GamePanel gamePanel = new GamePanel(); // creating a panel
        gamePanel.startGameThread();
        this.add(gamePanel);
        this.pack();

        this.addMouseListener(this); // frame is now respond for mouse events


    }




    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse clicked.");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("You pressed the mouse.");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("You released the mouse.");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("You entered the component.");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("You exited the component.");
    }
}
