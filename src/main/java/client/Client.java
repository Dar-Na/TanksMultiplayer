package client;

import messages.MessageClientServer;
import messages.MessageServerClient;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client implements Runnable{      // Communication
    List<Tank> tanks;
    String serverIp;
    Socket client;
    int port;
    MessageClientServer messageClientServer = null;
    MessageServerClient messageServerClient = null;
    public Client(String serverIp,int serverPort){
        tanks = new ArrayList<>();
        this.serverIp = serverIp;
        this.port = serverPort;
        try{
            client = new Socket(serverIp,port);
        }
        catch (IOException ex){
            System.out.println(ex);
        }

    }
    @Override
    public void run() {
        Scanner scanner  = new Scanner( System.in);
        try {
            OutputStream os = client.getOutputStream();
            InputStream is = client.getInputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            ObjectInputStream ios = new ObjectInputStream(is);

            String serverCmd = "";
            while (!serverCmd.equals("end")){
                System.out.println("Print message to a server");
                String message = scanner.nextLine();
                messageClientServer = new MessageClientServer(message);
                oos.writeObject(messageClientServer);
                MessageServerClient messageServerClient = (MessageServerClient) ios.readObject();
                System.out.println("Server reply with "+messageServerClient.getMessage());
            }
            ios.close();
            oos.close();
            is.close();
            os.close();

        }
        catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }
}
