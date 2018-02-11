package com.kyrrr.Model.Effects;

import com.kyrrr.Model.Actor;
import com.kyrrr.Model.Effect;
import com.kyrrr.Model.Status;
import net.slashie.libjcsi.CSIColor;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by kyrrebugge on 21.07.2017.
 */
public class Poison extends Effect {

    public Poison(){
        good = false;
        color = CSIColor.ISLAMIC_GREEN;
    }

    @Override
    public void affect(Actor actor) {
        actor.getStatus().setPoisoned(true);
    }

    public void rollForAffect(Actor actor){
        int r = ThreadLocalRandom.current().nextInt(1,101);
        if(r < 61){
            affect(actor);
        }
    }

    @Override
    public boolean isGood() {
        return super.isGood();
    }

}
