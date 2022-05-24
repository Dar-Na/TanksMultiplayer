package server;

import java.awt.*;
import java.io.Serializable;

public class STank implements Serializable {
    int id;
    private Point coordinates;

    public int getId() {
        return id;
    }


    public Point getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Point coordinates) {
        this.coordinates = coordinates;
    }

    public STank(int id, Point coordinates){
        this.id = id;
        this.coordinates = coordinates;
    }
}
