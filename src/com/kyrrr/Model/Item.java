package com.kyrrr.Model;

import com.kyrrr.Enum.Directions;
import com.kyrrr.Interface.ItemInterface;
import com.kyrrr.Settings;
import net.slashie.libjcsi.CSIColor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Created by kyrrebugge on 05.07.2017.
 * ITEM!! YEA
 */
public class Item implements ItemInterface {

    private int xpos;
    private int ypos;
    private Coordinates coordinates = new Coordinates();
    private boolean usable = true;
    private List<Effect> effects = new ArrayList<>();

    public Item() {
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
    public void addEffects(Effect... effects) {
        Collections.addAll(this.effects, effects);
    }

    @Override
    public boolean isUsable() {
        return this.usable;
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public void setUsable(boolean usable) {
        this.usable = usable;
    }


}
