package com.kyrrr.Model.Moves;

import com.kyrrr.Model.Effects.DamageHealth;
import com.kyrrr.Model.Effects.ParalyzeChance;

/**
 * Created by kyrrebugge on 20.02.2018.
 */
public class Thunderbolt extends DamagingMove {
    public Thunderbolt(){
        name = "Thunderbolt";
        uses = 20;
        effect = new DamageHealth(15);
        effects.add(new ParalyzeChance()); //todo int chance init
    }
}
