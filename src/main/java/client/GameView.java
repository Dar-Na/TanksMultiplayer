package client;

import javax.swing.*;

// Drawing world
public class GameView extends JFrame {

    ClientLogic clientLogic;
    GameView(ClientLogic clientLogic){
        this.clientLogic = clientLogic;
        this.setSize(clientLogic.screenWidth,clientLogic.screenHeight);       // game size window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Tanks");
        this.setLocationRelativeTo(null); // putting frame in the center
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // FULL SCREEN
        this.setVisible(true); // frame is visible
        GamePanel gamePanel = new GamePanel(clientLogic); // creating a panel
        gamePanel.startGameThread();
        this.add(gamePanel);
        this.pack();
    }


}
