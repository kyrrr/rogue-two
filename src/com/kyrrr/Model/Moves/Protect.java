package com.kyrrr.Model.Moves;

import com.kyrrr.Model.Effects.Protection;

/**
 * Created by kyrrebugge on 20.02.2018.
 */
public class Protect extends SelfMove {
    public Protect(){
        name = "Protect";
        uses = 10;
        effects.add(new Protection(1));
    }
}
