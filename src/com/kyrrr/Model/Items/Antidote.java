package com.kyrrr.Model.Items;

import com.kyrrr.Model.Effects.BoostHealth;
import com.kyrrr.Model.Effects.HealPoison;

/**
 * Created by kyrrebugge on 20.02.2018.
 */
public class Antidote extends Consumable {
    public Antidote(){
        setName("Antidote");
        addEffects(new HealPoison(), new BoostHealth(5));
        setUses(2);
    }
}
