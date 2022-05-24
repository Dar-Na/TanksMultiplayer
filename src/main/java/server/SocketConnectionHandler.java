package server;

import messages.MessageClientServer;
import messages.MessageServerClient;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

public class SocketConnectionHandler implements Runnable {
    static int test =0;
    private final Socket socket;
    private final int uniqueId;

    public SocketConnectionHandler(Socket socket, int unique_id) {
        this.socket = socket;
        this.uniqueId = unique_id;

    }
    @Override
    public void run() {
        // creating streams to exchange messages with client
        try{
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());



            String message = "";
            while (!message.equals("quit")){      // while client didn't send quit message keep exchange
                MessageClientServer messageClientServer;
                messageClientServer = (MessageClientServer)input.readObject();    // recieve message for client

                parseMessaage( messageClientServer);          // translate message


                MessageServerClient messageServerClient = ServerLogic.getInstance().getMessage();
                for(var tank : messageServerClient.getTanks()){
                    System.out.println(tank.getCoordinates().toString());
                }
                output.writeObject(messageServerClient);

            }

            output.writeObject("end");
            output.close();
            input.close();

        }
        catch (IOException | ClassNotFoundException ex){
            System.out.println(ex);
        }

    }
    public void parseMessaage(MessageClientServer messageClientServer){
        String command = messageClientServer.getMessage();
        if(command.equals("ADD_TANK")){             // Client ask for the tank
            Random rand = new Random();
            int min = 50;
            int max = 450;
            STank tank = new STank(uniqueId, new Point(rand.nextInt((max - min) + 1) + min,rand.nextInt((max - min) + 1) + min));
            ServerLogic.getInstance().addTank(tank);
            System.out.println("Created tank for client " + uniqueId);
        }
        if(command.equals("RIGHT_PRESSED")){
            STank tank =  ServerLogic.getInstance().getTankWithId(uniqueId);

            Point coords = tank.getCoordinates();
            Point newCoords = new Point(coords.x+10, coords.y);
            tank.setCoordinates(newCoords);

        }
        if(command.equals("LEFT_PRESSED")){

            STank tank =  ServerLogic.getInstance().getTankWithId(uniqueId);
            Point coords = tank.getCoordinates();
            Point newCoords = new Point(coords.x-10, coords.y);
            tank.setCoordinates(newCoords);
        }
        if(command.equals("DOWN_PRESSED")){
            STank tank =  ServerLogic.getInstance().getTankWithId(uniqueId);
            Point coords = tank.getCoordinates();
            Point newCoords = new Point(coords.x, coords.y+10);
            tank.setCoordinates(newCoords);
        }
        if(command.equals("UP_PRESSED")){
            STank tank =  ServerLogic.getInstance().getTankWithId(uniqueId);
            Point coords = tank.getCoordinates();
            Point newCoords = new Point(coords.x, coords.y-10);
            tank.setCoordinates(newCoords);
        }
    }
}
