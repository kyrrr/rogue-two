package com.kyrrr.Model;

import com.kyrrr.Enum.Directions;
import com.kyrrr.Interface.ActorInterface;
import com.kyrrr.Interface.Printable;
import com.kyrrr.LOL;
import com.kyrrr.Settings;
import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.CharKey;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by kyrrebugge on 03.07.2017.
 */
public class Actor implements ActorInterface, Printable {

    protected String name;
    protected boolean alive;
    protected Coordinates coordinates = new Coordinates();
    protected Status status = new Status();
    protected List<Move> moves = new ArrayList<>();
    protected String model;
    protected CSIColor modelColor;
    protected String uniqueID = UUID.randomUUID().toString();
    protected Zone zone = new Zone();
    protected List<Item> inventory = new ArrayList<>();


    public Actor(){
        alive = true;
    }

    @Override
    public void setXpos(int xpos) {
        this.coordinates.setX(xpos);
    }

    @Override
    public int getXpos() {
        return coordinates.getX();
    }

    @Override
    public void setYpos(int ypos) {
        this.coordinates.setY(ypos);
    }

    @Override
    public int getYpos() {
        return coordinates.getY();
    }

    @Override
    public void setPos(int x, int y) {
        this.coordinates.setX(x);
        this.coordinates.setY(y);
    }

    @Override
    public void setPos(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public void setModel(String model) {
        this.model = model;
    }


    @Override
    public CSIColor getModelColor() {
        return modelColor;
    }

    @Override
    public void setModelColor(CSIColor color) {
        this.modelColor = color;
    }

    @Override
    public void move(CharKey dir) {
        if(alive){
            int ypos = coordinates.getY();
            int xpos = coordinates.getX();
            int moveAmount = 1;
            if(dir.isUpArrow()&& (ypos - 1 >= 0)){
                ypos -= moveAmount;
            }
            if(dir.isDownArrow() && (ypos + 1 < Settings.screenHeight)){
                ypos += moveAmount;
            }
            if(dir.isLeftArrow() && (xpos-1 >= 0)){
                xpos -= moveAmount;
            }
            if(dir.isRightArrow() && (xpos+1 < Settings.screenWidth)){
                xpos += moveAmount;
            }
            coordinates.setCoordinates(xpos, ypos);
            zone.setOrigin(coordinates);
            zone.calcRect();
            if(status.isPoisoned()){
                status.setHealth(status.getHealth() - 1);
            }
        }
    }

    @Override
    public void move(String dir, int amount) {
        if(alive) {
            int ypos = coordinates.getY();
            int xpos = coordinates.getX();
            dir = dir.toUpperCase();
            //System.out.println(this + " wants to go " + dir);
            if (dir.equals(Directions.UP.toString()) && (ypos - amount > -1)) {
                ypos -= amount;
            }
            if (dir.equals(Directions.DOWN.toString()) && (ypos + amount < Settings.screenHeight)) {
                ypos += amount;
            }
            if (dir.equals(Directions.LEFT.toString()) && (xpos - amount > -1)) {
                xpos -= amount;
            }
            if (dir.equals(Directions.RIGHT.toString()) && (xpos + amount < Settings.screenWidth)) {
                xpos += amount;
            }
            coordinates.setCoordinates(xpos, ypos);
            zone.setOrigin(coordinates);
            zone.calcRect();
            if(status.isPoisoned()){
                status.setHealth(status.getHealth() - 1);
            }
        }
    }

    @Override
    public void move(int dir) {
        /*
            up 86
            down 82
            left 64
            right 67
             */
        if(alive) {
            int ypos = coordinates.getY();
            int xpos = coordinates.getX();
            int amount = 1;
            String dirStr = "";
            //System.out.println(this + " wants to go " + dir);
            if ((dir == 0 || dir == 86) && (ypos - amount > -1)) {
                ypos -= amount;
                dirStr = "UP";
            }
            if ((dir == 1 || dir == 82) && (ypos + amount < Settings.screenHeight)) {
                ypos += amount;
                dirStr = "DOWN";
            }
            if ((dir == 2 || dir == 64) && (xpos - amount > -1)) {
                xpos -= amount;
                dirStr = "LEFT";
            }
            if ((dir == 3 || dir == 67) && (xpos + amount < Settings.screenWidth)) {
                xpos += amount;
                dirStr = "RIGHT";
            }
            setActorViewDirection(dirStr);
            coordinates.setCoordinates(xpos, ypos);
            zone.setOrigin(coordinates);
            zone.calcRect();
            if(status.isPoisoned()){
                status.setHealth(status.getHealth() - 1);
            }
        }
    }

    @Override
    @LOL
    public void attack(Actor actor, Move move) {
        if(alive){
            if(status.isPoisoned()){
                status.setHealth(status.getHealth() - 1);
            }
            if(move.getEffect().isGood()){
                this.handleMove(move);
            } else {
                actor.handleMove(move);
            }
        }
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public void die() {
        //System.out.println(this + " died");
        this.status.setHealth(0);
        this.alive = false;
        this.modelColor = CSIColor.AMARANTH;
        this.model = "†";
    }

    @Override
    public boolean detectCollision(Actor actor) {
        return coordinates.getX() == actor.getXpos() && coordinates.getY() == actor.getYpos();
    }

    @Override
    public boolean detectCollision(Coordinates coordinates) {
        return this.coordinates.getX() == coordinates.getX() && this.coordinates.getY() == coordinates.getY();
    }

    @Override
    public void handleItem(Item item) {
        if(item.isUsable()){
            //System.out.println(this + " will handle " + item);
            //System.out.println(item + " has " + item.getEffects().size() + " effects");
            item.getEffects().forEach(this::handleEffect);
            item.setUsable(false);
        }
    }

    @Override
    public void handleEffect(Effect effect) {
        //System.out.println(this + " is affected by good?(" + effect.isGood() + ") " + effect);
        effect.affect(this);
    }

    @Override
    public int getSpeed() {
        return this.status.getSpeed();
    }

    @Override
    public void addMove(Move move) {
        this.moves.add(move);
    }

    @Override
    public List<Move> getMoves() {
        return this.moves;
    }

    @Override
    public boolean isAlive() {
        return status.getHealth() > 0 && alive;
    }

    @Override
    public void setAlive(boolean alive) {
        if(!alive){ // if alive = false, set health to 0
            status.setHealth(0);
        }
        this.alive = alive;
    }

    @Override
    public void handleMove(Move move) {
        //System.out.println(this + " handling " + move.getName() + " w/ effect " + move.getEffect());
        move.getEffect().affect(this);
        //int power = move.getPower();
        //status.setHealth(health - power);
        //System.out.println(this + " has " + status.getHealth() + " HP remaining");
        if(status.getHealth() < 1){
            die();
        }
    }

    @Override
    public String getId() {
        return uniqueID;
    }

    @Override
    public Actor chooseTarget(List<Actor> actors) {
        for (Actor possibleTarget : actors){
            if(this.alive && !possibleTarget.equals(this)){
                if(possibleTarget instanceof Player){
                    return possibleTarget;
                } else if(possibleTarget instanceof Enemy) {
                    return possibleTarget;
                }
            }
        }
        return null;
    }

    @Override
    public Move chooseMove(int index) {
        if(index > 0 && index < moves.size()){
            return moves.get(index);
        }
        return null;
    }

    @Override
    public List<Item> getInventory() {
        return inventory;
    }

    @Override
    public Zone getZone() {
        return zone;
    }

    @Override
    public void setZone(Zone zone) {
        this.zone = zone;
    }

    @Override
    public Coordinates getNextPos(CharKey dir) {
        int ypos = coordinates.getY();
        int xpos = coordinates.getX();
        int moveAmount = 1;
        if(dir.isUpArrow()&& (ypos - 1 >= 0)){
            ypos -= moveAmount;
        }
        if(dir.isDownArrow() && (ypos + 1 < Settings.screenHeight)){
            ypos += moveAmount;
        }
        if(dir.isLeftArrow() && (xpos-1 >= 0)){
            xpos -= moveAmount;
        }
        if(dir.isRightArrow() && (xpos+1 < Settings.screenWidth)){
            xpos += moveAmount;
        }
        Coordinates c = new Coordinates();
        c.setCoordinates(xpos, ypos);
        return c;
    }

    @Override
    public Coordinates getNextPos(String direction) {
        int ypos = coordinates.getY();
        int xpos = coordinates.getX();
        direction = direction.toUpperCase();
        int amount = 1;
        //System.out.println(this + " wants to go " + dir);
        if (direction.equals(Directions.UP.toString()) && (ypos - amount > -1)) {
            ypos -= amount;
        }
        if (direction.equals(Directions.DOWN.toString()) && (ypos + amount < Settings.screenHeight)) {
            ypos += amount;
        }
        if (direction.equals(Directions.LEFT.toString()) && (xpos - amount > -1)) {
            xpos -= amount;
        }
        if (direction.equals(Directions.RIGHT.toString()) && (xpos + amount < Settings.screenWidth)) {
            xpos += amount;
        }
        return new Coordinates(xpos, ypos);
    }

    @Override
    public Coordinates getNextPos(int direction) {
        /*
            up 86
            down 82
            left 64
            right 67
         */
        int ypos = coordinates.getY();
        int xpos = coordinates.getX();
        int amount = 1;
        //System.out.println(this + " wants to go " + dir);
        if (direction == 0 || direction == 86 && (ypos - amount > -1)) {
            ypos -= amount;
        }
        if (direction == 1 || direction == 82 && (ypos + amount < Settings.screenHeight)) {
            ypos += amount;
        }
        if (direction == 2 || direction == 64 && (xpos - amount > -1)) {
            xpos -= amount;
        }
        if (direction == 3 || direction == 67 && (xpos + amount < Settings.screenWidth)) {
            xpos += amount;
        }
        return new Coordinates(xpos, ypos);
    }

    @Override
    public int getCharCodeMoveDirectionTowards(Actor actor) {
        Coordinates ac = actor.getCoordinates();
        if(ac.overlaps(coordinates)){
            return -1;
        }
        int distanceX = coordinates.getX() - ac.getX();
        int distanceY = coordinates.getY() - ac.getY();
        //System.out.println(actor  + " is " + distanceX + " away (+left/-right)");
        //System.out.println(actor  + " is " + distanceY + " away (+down/-up)");
        int either = ThreadLocalRandom.current().nextInt(0,2);
        if(distanceX > 0 && distanceY > 0){
            //left and up?
            //System.out.println("left and up");
            if(either == 0){
                return 0; // up
            }
            return 2; // left
        } else if(distanceX < 0 && distanceY < 0){
            //right and down?
            //System.out.println("right and down");
            if(either == 0){
                return 1; // down
            }
            return 3; // right
        } else if(distanceX > 0 && distanceY < 0){
            //System.out.println("left and down");
            if(either == 0){
                return 1; // down
            }
            return 2;
        } else if(distanceX < 0 && distanceY > 0){
            //System.out.println("right and up");
            if(either == 0){
                return 0; // up
            }
            return 3;
        } else if(distanceX == 0 && distanceY > 0){
            //System.out.println("down");
            return 0;
        } else if(distanceX == 0 && distanceY < 0){
            //System.out.println("up");
            return 1;
        } else if(distanceX > 0 && distanceY == 0){
            //System.out.println("left");
            return 2;
        } else if(distanceX < 0 && distanceY == 0){
            //System.out.println("right");
            return 3;
        }
        return -1;
    }


    @Override
    public CharKey getCharKeyMoveDirectionTowards(Actor actor) {
        return null;
    }

    @Override
    public Coordinates getCoordinates() {
        return coordinates;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }


    @Override
    public void setActorViewDirection(String direction) {
        if(alive) {
            //System.out.println(this + " wants to go " + dir);
            switch (direction){
                case "UP":
                    setModel("^");
                    break;
                case "DOWN":
                    setModel("v");
                    break;
                case "LEFT":
                    setModel("<");
                    break;
                case "RIGHT":
                    setModel(">");
                    break;
            }
        }
    }

    @Override
    public void setSpeed(int speed) {
        this.status.setSpeed(speed);
    }

}
