package com.kyrrr.Model.Items;

import com.kyrrr.Model.Effects.DamageHealth;
import com.kyrrr.Model.Effects.Poison;

/**
 * Created by kyrrebugge on 10.08.2017.
 */
public class RottenDagger extends Weapon {
    public RottenDagger(){
        addEffect(new DamageHealth(3));
        addEffect(new Poison());
    }
}
