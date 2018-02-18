package com.kyrrr.Interface;

import com.kyrrr.Model.Actor;

/**
 * Created by kyrrebugge on 20.07.2017.
 */
public interface EffectInterface {
    void affect(Actor actor);
    String getEffectString(Actor actor);
}
