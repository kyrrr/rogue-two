package com.kyrrr.Model.Moves;

import com.kyrrr.Model.Effects.DamageHealth;

/**
 * Created by kyrrebugge on 20.02.2018.
 */
public class Hadoken extends DamagingMove {
    public Hadoken(){
        name = "Hadoken";
        uses = 99;
        addEffects(new DamageHealth(9001));
    }
}
