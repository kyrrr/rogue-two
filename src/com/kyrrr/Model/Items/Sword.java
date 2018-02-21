package com.kyrrr.Model.Items;

import com.kyrrr.Model.Effects.DamageHealth;

/**
 * Created by kyrrebugge on 07.08.2017.
 */
public class Sword extends Weapon {

    public Sword(){
        setName("Sword");
        addEffect(new DamageHealth(16));
        setHitsBeforeDegrade(10);
    }
}
