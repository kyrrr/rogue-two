package com.kyrrr.Model.Moves;

import com.kyrrr.Model.Effects.BurnChance;
import com.kyrrr.Model.Effects.DamageHealth;

/**
 * Created by kyrrebugge on 19.02.2018.
 */
public class Fireball extends DamagingMove {
    public Fireball(){
        name = "Fireball";
        uses = 20;
        addEffects(new DamageHealth(10), new BurnChance());
    }
}
