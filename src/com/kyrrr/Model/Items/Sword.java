package com.kyrrr.Model.Items;

import com.kyrrr.Model.Effects.DamageHealth;
import com.kyrrr.Model.Item;

/**
 * Created by kyrrebugge on 07.08.2017.
 */
public class Sword extends Weapon {

    public Sword(){
        addEffect(new DamageHealth(16));
    }

}
