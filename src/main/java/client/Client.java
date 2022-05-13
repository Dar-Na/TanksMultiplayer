package client;

public class Client {
    private static int clients=0;
    private String ipClient;
    private int idClient;
    private String ipServer;
    private int port;
    public Client(String ipClient){
        this.ipClient = ipClient;
        idClient = this.clients;
        this.clients++;
    }
    public String getIpClient(){
        return this.ipClient;
    }
    public int getIdClient(){
        return this.idClient;
    }
}
