package com.kyrrr.Interface;

import com.kyrrr.Model.Actors.Actor;

/**
 * Created by kyrrebugge on 20.07.2017.
 */
public interface EffectInterface {
    boolean affect(Actor actor);
    String getEffectString(Actor actor);
}
