package server;

public class ServerMain {
  public static void main(String[] args)
  {
        Server server = new Server(9797);
        server.start();
  }
}
