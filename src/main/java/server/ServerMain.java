package server;

public class ServerMain {
  public static void main(String[] args) {
      System.out.println("I am a server");
      Thread serverThread = new Thread(new IncomingSocketConnectionHandler(9797)); // thread that handles incoming clients
      serverThread.start();
  }

}
