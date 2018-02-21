package com.kyrrr.Model.Moves;

import com.kyrrr.Model.Effects.PoisonChance;

/**
 * Created by kyrrebugge on 20.02.2018.
 */
public class PoisonGas extends DamagingMove {
    public PoisonGas(){
        name = "Poison Gas";
        uses = 5;
        addEffects(new PoisonChance(100));
    }
}
