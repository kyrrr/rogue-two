package com.kyrrr.Model;

import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.CharKey;
import java.util.List;

public class Player extends Actor {

    public Player(){
        alive = true;
        xpos = 0;
        ypos = 0;
        model = "@";
        modelColor = CSIColor.PAPAYA_WHIP;
        status.setSpeed(20);
        moves.add(new Move("p-tack", 25));
    }

    @Override
    public void setXpos(int xpos) {
        this.xpos = xpos;
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
    public int getXpos() {
        return xpos;
    }

    @Override
    public boolean detectCollision(Actor actor) {
        return super.detectCollision(actor);
    }

    @Override
    public void setPos(int x, int y) {
        super.setPos(x, y);
    }

    @Override
    public String getModel() {
        return super.getModel();
    }

    @Override
    public void setModel(String model) {
        super.setModel(model);
    }

    @Override
    public CSIColor getModelColor() {
        return modelColor;
    }

    @Override
    public void move(CharKey dir) {
        super.move(dir);
    }

    @Override
    public void attack(Actor actor, Move move) {
        super.attack(actor, move);
    }

    @Override
    public void die() {
        super.die();
    }

    @Override
    public void handleMove(Move move) {
        super.handleMove(move);
    }

    @Override
    public boolean isAlive() {
        return super.isAlive();
    }

    @Override
    public List<Move> getMoves() {
        return super.getMoves();
    }

    @Override
    public String getId() {
        return super.getId();
    }
}
