package com.kyrrr.Model.Effects;

import com.kyrrr.Model.Actor;
import com.kyrrr.Model.Effect;
import net.slashie.libjcsi.CSIColor;

/**
 * Created by kyrrebugge on 21.07.2017.
 */
public class Antidote extends Effect {

    public Antidote(){
        good = true;
        color = CSIColor.ISLAMIC_GREEN;
    }

    @Override
    public String getEffectString(Actor actor) {
        return actor.getName() + " is healed of poison";
    }

    @Override
    public void affect(Actor actor) {
        actor.getStatus().setPoisoned(false);
    }

    @Override
    public boolean isGood() {
        return super.isGood();
    }
}
