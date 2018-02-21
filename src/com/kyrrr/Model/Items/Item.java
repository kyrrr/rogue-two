package com.kyrrr.Model.Items;

import com.kyrrr.Interface.ItemInterface;
import com.kyrrr.Model.Effects.Effect;
import com.kyrrr.Model.Position.Coordinates;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by kyrrebugge on 05.07.2017.
 * ITEM!! YEA
 */
public class Item implements ItemInterface {

    private int xpos;
    private int ypos;
    private String name;
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

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void use() {

    }


}
