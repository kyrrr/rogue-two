package com.kyrrr.Model;

import com.kyrrr.Interface.ItemInterface;
import net.slashie.libjcsi.CSIColor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by kyrrebugge on 05.07.2017.
 * ITEM!! YEA
 */
public class Item implements ItemInterface {

    private int xpos;
    private int ypos;
    private boolean usable;
    private String model;
    private CSIColor modelColor;
    private List<Effect> effects = new ArrayList<>();
    private String uniqueID = UUID.randomUUID().toString();

    public Item(){
        usable = true;
    }

    @Override
    public List<Effect> getEffects() {
        return effects;
    }

    @Override
    public void addEffect(Effect effect) {
        effects.add(effect);
    }

    @Override
    public void setXpos(int xpos) {
        this.xpos = xpos;
    }

    @Override
    public int getXpos() {
        return this.xpos;
    }

    @Override
    public void setYpos(int ypos) {
        this.ypos = ypos;
    }

    @Override
    public int getYpos() {
        return this.ypos;
    }

    @Override
    public void setPos(int x, int y) {
        this.xpos = x;
        this.ypos = y;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public CSIColor getModelColor() {
        return this.modelColor;
    }

    @Override
    public void setModelColor(CSIColor color) {
        this.modelColor = color;
    }

    @Override
    public void move(String dir, int amount) {
        // items can move around
    }

    @Override
    public boolean detectCollision(Actor actor) {
        return false;
    }

    @Override
    public boolean isUsable() {
        return this.usable;
    }

    @Override
    public void setUsable(boolean usable) {
        this.usable = usable;
    }

    @Override
    public void handleItem(Item item) {
        // item + item = item
    }

    @Override
    public String getId() {
        return this.uniqueID;
    }
}
