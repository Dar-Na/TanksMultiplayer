package server;

public class ServerWall{
    public int width;
    public int height;
    public  int x;
    public int y;
    public Boolean hitByBullet(ServerBullet bullet){
        boolean q1 = bullet.x > this.x && bullet.x < this.x + width;
        boolean q2 = bullet.y > this.y && bullet.y < this.y + height;
        if(q1 && q2){
            return true;
        }
        return false;
    }
    ServerWall(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
