package com.kyrrr.Model.Effects;

import com.kyrrr.Model.Actor;
import com.kyrrr.Model.Effect;
import com.kyrrr.Model.Status;
import net.slashie.libjcsi.CSIColor;

/**
 * Created by kyrrebugge on 20.07.2017.
 */
public class BoostHealth extends Effect {

    private int healthBoost;

    public BoostHealth(int healthBoost){
        this.good = true;
        this.healthBoost = healthBoost;
        this.color = CSIColor.GREEN;
    }

    @Override
    public void affect(Actor actor) {
        Status status = actor.getStatus();
        status.setHealth(status.getHealth() + healthBoost);
    }

    public int getHealthBoost() {
        return healthBoost;
    }

    public void setHealthBoost(int healthBoost) {
        this.healthBoost = healthBoost;
    }
}
