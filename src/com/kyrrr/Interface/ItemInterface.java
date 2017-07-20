package com.kyrrr.Interface;

import com.kyrrr.Model.*;
import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.CharKey;

import java.util.List;

/**
 * Created by kyrrebugge on 05.07.2017.
 */
public interface ItemInterface {

    List<Effect> getEffects();

    void addEffect(Effect effect);

    void setXpos(int xpos);

    int getXpos();

    void setYpos(int ypos);

    int getYpos();

    void setPos(int x, int y);

    String getModel();

    void setModel(String model);

    CSIColor getModelColor();

    void setModelColor(CSIColor color);

    void move(String dir, int amount);

    boolean detectCollision(Actor actor);

    boolean isUsable();

    void setUsable(boolean usable);

    void handleItem(Item item);

    String getId();
}
