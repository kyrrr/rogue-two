package com.kyrrr.Model.Position;

import com.kyrrr.Model.Actors.Actor;
import com.kyrrr.Model.Shapes.Shape;
import com.kyrrr.Settings;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by kyrrebugge on 23.07.2017.
 */
public class Zone {

    private Coordinates origin;
    private int size; // square and within bounds //TODO get rid of
    private int width;
    private int height;
    private List<Coordinates> area = new ArrayList<>();
    private Shape shape;

    public Zone() {
    }

    public Zone(Coordinates origin, int width, int height) {
        this.origin = origin;
        this.width = width;
        this.height = height;
    }


    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void calcRect(Zone exclude){
        area = new ArrayList<>();
        for (int i = origin.getX() - width;i<=origin.getX() + width;i++){
            for(int j = origin.getY() - height;j<=origin.getY() + height;j++){
                if(i > -1 && i < Settings.screenWidth && j > -1 && j < Settings.screenHeight - 1 && !exclude.contains(i,j)){
                    area.add(new Coordinates(i,j));
                }
            }
        }
    }

    public void calcRect(){
        area = new ArrayList<>();
        for (int i = origin.getX() - width;i<=origin.getX() + width;i++){
            for(int j = origin.getY() - height;j<=origin.getY() + height;j++){
                if(i > -1 && i < Settings.screenWidth && j > -1 && j < Settings.screenHeight - 1){
                    area.add(new Coordinates(i,j));
                }
            }
        }
    }

    public void calcVision(List<Coordinates> exclude){
        getArea().stream().filter(exclude::contains).forEach(c -> c.setTraversable(false));
    }

    public void calcCircIsh(){
        area = new ArrayList<>();
    }

    public void calcRectZoneWithObstacles(int oneIn, Zone exclude){
        for (int i = 0;i<getWidth();i++){
            for(int j = 0;j<getHeight();j++){
                    int randy = ThreadLocalRandom.current().nextInt(0, oneIn);
                    int underRandy = ThreadLocalRandom.current().nextInt(0, oneIn - 1);
                    if(randy == underRandy && !exclude.contains(i,j)){
                        Coordinates nc = new Coordinates(i, j);
                        area.add(nc);
                    }
                }
            //}
        }
        //System.out.println("hello");
    }

    public void setOrigin(Coordinates origin) {
        this.origin = origin;
    }

    public void setOrigin(int x, int y){
        Coordinates c = new Coordinates();
        c.setCoordinates(x,y);
        setOrigin(c);
    }

    public Coordinates getOrigin() {
        return origin;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<Coordinates> getArea() {
        return area;
    }

    public boolean contains(Actor actor){
        Coordinates ac = actor.getCoordinates();
        for (Coordinates c : getArea()) {
            if(c.getX() == ac.getX() && c.getY() == ac.getY()){
                return true;
            }
        }
        return false;
        // TODO: refactor
    }

    public boolean contains(Coordinates coordinates){
        for (Coordinates c: getArea()) {
            if(c.getX() == coordinates.getX() && c.getY() == coordinates.getY()){
                return true;
            }
        }
        return false;
    }

    public boolean contains(int x, int y){
        for (Coordinates c: getArea()) {
            if(c.getX() == x && c.getY() == y){
                return true;
            }
        }
        return false;
    }

    public void setArea(List<Coordinates> area) {
        this.area = area;
    }

    public void add(Coordinates coordinates){
        this.area.add(coordinates);
    }

    public boolean intersects(Zone zone){
        for (Coordinates mc : area){
            for (Coordinates oc : zone.getArea()){
                if(oc.overlaps(mc)){
                    return true;
                }
            }
        }
        return false;
    }
}
