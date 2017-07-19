package com.kyrrr.Model;

import net.slashie.libjcsi.CSIColor;


/**
 * Created by kyrrebugge on 16.02.2017.
 */
public class Enemy extends Actor {

    /** what up */
    public Enemy(){

    }

    @Override
    public void setXpos(int xpos) {
        super.setXpos(xpos);
    }

    @Override
    public int getXpos() {
        return super.getXpos();
    }

    @Override
    public void setYpos(int ypos) {
        super.setYpos(ypos);
    }

    @Override
    public int getYpos() {
        return super.getYpos();
    }

    @Override
    public void setPos(int x, int y) {
        super.setPos(x,y);
    }

    @Override
    public String getModel() {
        return super.getModel();
    }

    @Override
    public CSIColor getModelColor() {
        return super.getModelColor();
    }

    @Override
    public void setModelColor(CSIColor color) {
        super.setModelColor(color);
    }

    @Override
    public void move(String dir, int amount) {
        super.move(dir, amount);
    }

    @Override
    public void attack(Actor actor, Move move) {
        super.attack(actor, move);
    }

    @Override
    public Status getStatus() {
        return super.getStatus();
    }

    @Override
    public void die() {
        super.die();
    }

    @Override
    public String getId() {
        return super.getId();
    }
}
