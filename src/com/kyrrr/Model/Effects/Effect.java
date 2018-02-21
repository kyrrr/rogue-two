package com.kyrrr.Model.Effects;

import com.kyrrr.Interface.EffectInterface;
import com.kyrrr.Model.Actors.Actor;
import net.slashie.libjcsi.CSIColor;

/**
 * Created by kyrrebugge on 20.07.2017.
 */
public class Effect implements EffectInterface{

   // protected boolean beneficialToCaster;

    protected CSIColor color;

    @Override
    public boolean affect(Actor actor) {
        return false;
    }

    @Override
    public String getEffectString(Actor actor) {
        return null;
    }
/*
    public boolean isBeneficialToCaster() {
        return beneficialToCaster;
    }

   /* public void setBeneficialToCaster(boolean beneficialToCaster) {
        this.beneficialToCaster = beneficialToCaster;
    }*/

    public CSIColor getColor() {
        return color;
    }

    public void setColor(CSIColor color) {
        this.color = color;
    }
}
