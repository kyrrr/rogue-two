package com.kyrrr.Model;

import com.kyrrr.Enum.Directions;
import com.kyrrr.Interface.ActorInterface;
import com.kyrrr.Settings;
import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.CharKey;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by kyrrebugge on 03.07.2017.
 */
public class Actor implements ActorInterface {

    int xpos;
    int ypos;
    int speed;
    boolean alive;
    Status status = new Status();
    List<Move> moves = new ArrayList<>();
    String model;
    CSIColor modelColor;
    String uniqueID = UUID.randomUUID().toString();

    public Actor(){
    }

    @Override
    public void setXpos(int xpos) {
        this.xpos = xpos;
    }

    @Override
    public int getXpos() {
        return xpos;
    }

    @Override
    public void setYpos(int ypos) {
        this.ypos = ypos;
    }

    @Override
    public int getYpos() {
        return ypos;
    }

    @Override
    public void setPos(int x, int y) {
        this.xpos = x;
        this.ypos = y;
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
        }
    }

    @Override
    public void move(String dir, int amount) {
        if(alive) {
            dir = dir.toUpperCase();
            if (dir.equals(Directions.UP.toString()) && (ypos - 1 >= 0)) {
                ypos -= amount;
            }
            if (dir.equals(Directions.DOWN.toString()) && (ypos + 1 < 25)) {
                ypos += amount;
            }
            if (dir.equals(Directions.LEFT.toString()) && (xpos - 1 >= 0)) {
                xpos -= amount;
            }
            if (dir.equals(Directions.RIGHT.toString()) && (xpos + 1 < 80)) {
                xpos += amount;
            }
        }
    }

    @Override
    public void attack(Actor actor, Move move) {
        if(this.alive){
            actor.handleMove(move);
        }
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public void die() {
        System.out.println(this + " died");
        this.status.setHealth(0);
        this.alive = false;
        this.modelColor = CSIColor.AMARANTH;
        this.model = "†";
    }

    @Override
    public boolean detectCollision(Actor actor) {
        return this.xpos == actor.getXpos() && this.ypos == actor.getYpos();
    }

    @Override
    public boolean detectCollision(Item item) {
        return this.xpos == item.getXpos() && this.ypos == item.getYpos();
    }

    @Override
    public void handleItem(Item item) {
        if(item.isUsable()){
            System.out.println(this + " will handle " + item);
            System.out.println(item + " has " + item.getEffects().size() + " effects");
            item.getEffects().forEach(this::handleEffect);
            item.setUsable(false);
        }
    }

    @Override
    public void handleEffect(Effect effect) {
        System.out.println(this + " is affected by " + effect);
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
        System.out.println(this + " attacked by " + move.getName() + " for " + move.getPower() + " points");
        int health = status.getHealth();
        int power = move.getPower();
        status.setHealth(health - power);
        System.out.println(this + " has " + status.getHealth() + " HP remaining");
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
        for (Actor a : actors){
            if(!a.equals(this)){
                if(a instanceof Player){
                    return a;
                } else if(a instanceof Enemy) {
                    return a;
                }
            }
        }
        return null;
    }

    @Override
    public void setSpeed(int speed) {
        this.status.setSpeed(speed);
    }
}
