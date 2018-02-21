package com.kyrrr.Model.Effects;

import com.kyrrr.Model.Actors.Actor;
import net.slashie.libjcsi.CSIColor;

/**
 * Created by kyrrebugge on 21.07.2017.
 */
public class PoisonChance extends ChanceEffect {

    public PoisonChance(){
       // beneficialToCaster = false;
        color = CSIColor.ISLAMIC_GREEN;
        chance = 66;
    }

    public PoisonChance(int chance){
       // beneficialToCaster = false;
        color = CSIColor.ISLAMIC_GREEN;
        setChance(chance);
    }

    @Override
    public String getEffectString(Actor actor) {
        return actor.getName() + " is poisoned";
    }

    @Override
    public boolean affect(Actor actor) {
        if(roll()){
            actor.getStatus().setPoisoned(true);
            return true;
        }
        return false;
    }
}
