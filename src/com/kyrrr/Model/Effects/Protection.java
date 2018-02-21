package com.kyrrr.Model.Effects;

import com.kyrrr.Model.Actors.Actor;

/**
 * Created by kyrrebugge on 19.02.2018.
 */
public class Protection extends Effect {
    int duration;
    public Protection(int duration){
      //  beneficialToCaster = true;
        this.duration = duration;
    }

    @Override
    public String getEffectString(Actor actor) {
        return actor.getName() + " is protected for " + duration + " rounds";
    }

    @Override
    public boolean affect(Actor actor) {
        actor.getStatus().setProtection(duration);
        return actor.getStatus().isProtected();
        //duration--;
    }
}
