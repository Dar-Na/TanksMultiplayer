package server;

import java.io.Serializable;

public class ServerTank implements Serializable {
    private int health = 100;
    public int width = 50;
    public int height = 50;
    public  int x;
    public int y;
    public int clientId;
    private long lastReload;
    public Boolean isDead = false;
    public int getHealth() {
        return health;
    }
    public void minusHealth(int damage){
        if(health - damage <= 0){
            isDead = true;
            health = 0;
        }
        else {
            health -= damage;
        }
    }
    public Boolean hitByBullet(ServerBullet bullet){
        if(bullet.clientId == this.clientId) return false;
        boolean q1 = this.x < bullet.x + bullet.width;
        boolean q2 =this.x +  this.width > bullet.x;
        boolean q3 = this.y < bullet.y + bullet.height;
        boolean q4 = this.height +  this.y > bullet.y;
        if(q1 && q2 && q3 && q4){
            minusHealth(bullet.damage);
            return true;
        }
        return false;
    }
    public Boolean hitByTank(ServerTank tank){
        if(tank.clientId == this.clientId) return false;
        boolean q1 = this.x < tank.x + tank.width;
        boolean q2 =this.x +  this.width > tank.x;
        boolean q3 = this.y < tank.y + tank.height;
        boolean q4 = this.height +  this.y > tank.y;
        if(q1 && q2 && q3 && q4){
            return true;
        }
        return false;
    }
    public Boolean hitByWall(ServerWall wall){
        boolean q1 = this.x < wall.x + wall.width;
        boolean q2 =this.x +  this.width > wall.x;
        boolean q3 = this.y < wall.y + wall.height;
        boolean q4 = this.height +  this.y > wall.y;
        if(q1 && q2 && q3 && q4){
            return true;
        }
        return false;
    }

    public Boolean checkReload(){

        if(System.currentTimeMillis() - lastReload > 1000){
            lastReload = System.currentTimeMillis();
            return true;
        }
        return false;
    }
    ServerTank(int x, int y, int clientId){
        this.lastReload = System.currentTimeMillis();
        this.x = x;
        this.y = y;
        this.clientId = clientId;
    }
}
