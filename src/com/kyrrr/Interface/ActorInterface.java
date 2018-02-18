package com.kyrrr.Interface;

import com.kyrrr.Model.*;

import java.util.List;

/**
 * Created by kyrrebugge on 16.02.2017.
 */
public interface ActorInterface {



    void attack(Actor actor, Move move);

    Status getStatus();

    void die();

    boolean detectCollision(Actor actor);

    boolean detectCollision(Coordinates coordinates);

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

    Move chooseMove(int index);

   // Inventory getInventory();

    List<Item> getInventory();

    Coordinates getCoordinates();

    void setName(String name);

    String getName();

}
