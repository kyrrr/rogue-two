package com.kyrrr.Model;

import com.kyrrr.Interface.ActorInterface;
import com.kyrrr.Interface.ItemInterface;
import net.slashie.libjcsi.CSIColor;

/**
 * Created by kyrrebugge on 05.07.2017.
 */
public class Item implements ItemInterface {

    @Override
    public void setXpos(int xpos) {

    }

    @Override
    public int getXpos() {
        return 0;
    }

    @Override
    public void setYpos(int ypos) {

    }

    @Override
    public int getYpos() {
        return 0;
    }

    @Override
    public void setPos(int x, int y) {

    }

    @Override
    public String getModel() {
        return null;
    }

    @Override
    public void setModel(String model) {

    }

    @Override
    public CSIColor getModelColor() {
        return null;
    }

    @Override
    public void setModelColor(CSIColor color) {

    }

    @Override
    public void move(String dir, int amount) {

    }

    @Override
    public boolean detectCollision(Actor actor) {
        return false;
    }

    @Override
    public boolean isUsable() {
        return false;
    }

    @Override
    public void setUsable(boolean usable) {

    }

    @Override
    public void handleItem(Item item) {

    }

    @Override
    public String getId() {
        return null;
    }
}
