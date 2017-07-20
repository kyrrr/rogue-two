package com.kyrrr.Interface;

import com.kyrrr.Model.*;
import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.CharKey;

import java.util.List;

/**
 * Created by kyrrebugge on 16.02.2017.
 */
public interface ActorInterface {

    void setXpos(int xpos);

    int getXpos();

    void setYpos(int ypos);

    int getYpos();

    void setPos(int x, int y);

    String getModel();

    void setModel(String model);

    CSIColor getModelColor();

    void setModelColor(CSIColor color);

    void move(CharKey dir);

    void move(String dir, int amount);

    void attack(Actor actor, Move move);

    Status getStatus();

    void die();

    boolean detectCollision(Actor actor);

    boolean detectCollision(Item item);

    void handleItem(Item item);

    void handleEffect(Effect effect);

    void setSpeed(int speed);

    int getSpeed();

    void addMove(Move move);

    List<Move> getMoves();

    boolean isAlive();

    void setAlive(boolean alive);

    void handleMove(Move move);

    String getId();

    Actor chooseTarget(List<Actor> actors);
}
