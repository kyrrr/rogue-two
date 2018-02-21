package com.kyrrr.Model.Effects;

import com.kyrrr.Model.Actors.Actor;

/**
 * Created by kyrrebugge on 19.02.2018.
 */
public class BurnChance extends ChanceEffect {

    public BurnChance(){
    //    beneficialToCaster = false;
        chance = 33;
    }

    @Override
    public String getEffectString(Actor actor) {
        return actor.getName() + " is burnt! Ouch!";
    }


    @Override
    public boolean affect(Actor actor) {
        if(roll()){
            actor.getStatus().setBurned(true);
            return true;
        }
        return false;
    }
}
