package client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable{      // Communication
    String serverIp;
    int port;
    Socket client;

    public Client(String serverIp,int serverPort){
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
                oos.writeObject(message);
                serverCmd = String.valueOf(ios.readObject());
                System.out.println("Server reply with "+serverCmd);
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
