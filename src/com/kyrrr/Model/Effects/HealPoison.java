package com.kyrrr.Model.Effects;

import com.kyrrr.Model.Actors.Actor;
import net.slashie.libjcsi.CSIColor;

/**
 * Created by kyrrebugge on 21.07.2017.
 */
public class HealPoison extends Effect {

    public HealPoison(){
        color = CSIColor.ISLAMIC_GREEN;
    }

    @Override
    public String getEffectString(Actor actor) {
        return actor.getName() + " is healed of poison";
    }

    @Override
    public boolean affect(Actor actor) {
        actor.getStatus().setPoisoned(false);
        actor.getStatus().setPoisonStack(0);
        return !actor.getStatus().isPoisoned() && actor.getStatus().getPoisonStack() == 0;
    }

}
