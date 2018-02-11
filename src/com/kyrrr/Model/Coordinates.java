package com.kyrrr.Model;

/**
 * Created by kyrrebugge on 22.07.2017.
 */
public class Coordinates {
    private int x;
    private int y;
    private boolean traversable;

    public Coordinates(){}

    public Coordinates(int x, int y){
        setCoordinates(x, y);
    }

    public void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public boolean overlaps(Coordinates coordinates){
        return x == coordinates.getX() && y == coordinates.getY();
    }

    public void setTraversable(boolean traversable) {
        this.traversable = traversable;
    }

    public boolean isTraversable() {
        return traversable;
    }
}
