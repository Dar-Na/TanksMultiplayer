package client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) {
        Scanner scanner  = new Scanner( System.in);
        System.out.println("I am a Client?");
        try(Socket client = new Socket("localhost",9797)){
            try {
                OutputStream os = client.getOutputStream();
                InputStream is = client.getInputStream();
                ObjectOutputStream oos = new ObjectOutputStream(os);
                ObjectInputStream ios = new ObjectInputStream(is);

                String server_cmd = "";
                while (!server_cmd.equals("end")){
                    System.out.println("Print essage to a server");
                    String message = scanner.nextLine();
                    oos.writeObject(message);
                    server_cmd = String.valueOf(ios.readObject());
                    System.out.println("Server reply with "+server_cmd);
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
        catch (IOException ex){
            System.out.println(ex);
        }
    }
}
