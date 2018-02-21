package com.kyrrr.Interface;

import com.kyrrr.Model.Actors.Actor;
import com.kyrrr.Model.Position.Coordinates;
import com.kyrrr.Model.Position.Zone;
import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.CharKey;

/**
 * Created by kyrrebugge on 10.08.2017.
 */
public interface Printable {

    void setXpos(int xpos);

    int getXpos();

    void setYpos(int ypos);

    int getYpos();

    void setPos(int x, int y);

    void setPos(Coordinates coordinates);

    String getModel();

    void setModel(String model);

    CSIColor getModelColor();

    void setModelColor(CSIColor color);

    void move(CharKey dir);

    void move(String dir, int amount);

    void move(int dir);

    Zone getZone();

    void setZone(Zone zone);

    Coordinates getNextPos(CharKey dir);

    Coordinates getNextPos(String direction);

    Coordinates getNextPos(int direction);

    CharKey getCharKeyMoveDirectionTowards(Actor actor);

    int getCharCodeMoveTowards(Actor actor);

    void setActorViewDirection(String direction);
}
