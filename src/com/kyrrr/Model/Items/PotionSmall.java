package com.kyrrr.Model.Items;

import com.kyrrr.Model.Effects.BoostHealth;

/**
 * Created by kyrrebugge on 20.02.2018.
 */
public class PotionSmall extends Consumable {
    public PotionSmall(){
        setName("Small Potion");
        addEffects(new BoostHealth(50));
        setUses(1);
    }
}
