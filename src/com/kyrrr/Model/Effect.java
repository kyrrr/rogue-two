package com.kyrrr.Model;

import com.kyrrr.Interface.EffectInterface;
import net.slashie.libjcsi.CSIColor;

/**
 * Created by kyrrebugge on 20.07.2017.
 */
public class Effect implements EffectInterface{

    protected boolean good;

    protected CSIColor color;

    @Override
    public void affect(Actor actor) {
    }

    public boolean isGood() {
        return good;
    }

    public void setGood(boolean good) {
        this.good = good;
    }

    public CSIColor getColor() {
        return color;
    }

    public void setColor(CSIColor color) {
        this.color = color;
    }
}
