package com.kyrrr.Interface;

import com.kyrrr.Model.Actors.Actor;
import com.kyrrr.Model.Effects.Effect;
import com.kyrrr.Model.Items.Item;
import com.kyrrr.Model.Moves.Move;
import com.kyrrr.Model.Position.Coordinates;
import com.kyrrr.Model.Status.Inventory;
import com.kyrrr.Model.Status.Status;

import java.util.List;

/**
 * Created by kyrrebugge on 16.02.2017.
 */
public interface ActorInterface {

    /*int maxMoves = 0;
    String name = null;
    boolean alive = true;
    Coordinates coordinates = new Coordinates();
    Status status = new Status();
    List<Move> moves = new ArrayList<>();
    String model = null;
    CSIColor modelColor = null;
    String uniqueID = UUID.randomUUID().toString();
    Zone zone = new Zone();
    List<Item> inventory = new ArrayList<>();
    String deathModel = null;*/

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

    Inventory getInventory();

    Coordinates getCoordinates();

    void setName(String name);

    String getName();

    void setDeathModel(String deathModel);

    String getDeathModel();

    void setMaxMoves(int maxMoves);

    int getMaxMoves();

    void addItem(Item it);
}
