package com.kyrrr.Model.Effects;

import com.kyrrr.Model.Actors.Actor;
import com.kyrrr.Model.Status.Status;
import net.slashie.libjcsi.CSIColor;

/**
 * Created by kyrrebugge on 20.07.2017.
 */
public class BoostHealth extends Effect {

    private int healthBoost;

    public BoostHealth(int healthBoost){
       // this.beneficialToCaster = true;
        this.healthBoost = healthBoost;
        this.color = CSIColor.GREEN;
    }

    @Override
    public String getEffectString(Actor actor) {
        return actor.getName() + " gains " + healthBoost + " health";
    }

    @Override
    public boolean affect(Actor actor) {
        Status status = actor.getStatus();
        int beforeHeal = status.getHealth();
        status.setHealth(status.getHealth() + healthBoost);
        return status.getHealth() > beforeHeal;
    }

    public int getHealthBoost() {
        return healthBoost;
    }

    public void setHealthBoost(int healthBoost) {
        this.healthBoost = healthBoost;
    }
}
