package com.kyrrr.Model.Effects;

import com.kyrrr.Model.Actors.Actor;
import net.slashie.libjcsi.CSIColor;

/**
 * Created by kyrrebugge on 19.02.2018.
 */
public class ParalyzeChance extends ChanceEffect {

    public ParalyzeChance(){
        //beneficialToCaster = false;
        color = CSIColor.ISLAMIC_GREEN;
        chance = 33;
    }

    @Override
    public String getEffectString(Actor actor) {
        return actor.getName() + " is paralyzed";
    }

    @Override
    public boolean affect(Actor actor) {
        if(this.roll()){
            actor.getStatus().setPoisoned(true);
            return true;
        }
        return false;
    }

}
