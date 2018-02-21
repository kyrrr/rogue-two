package com.kyrrr.Interface;

import com.kyrrr.Model.Effects.Effect;

import java.util.List;

/**
 * Created by kyrrebugge on 05.07.2017.
 */
public interface ItemInterface {

    List<Effect> getEffects();

    void addEffect(Effect effect);

    void addEffects(Effect... effects);

    boolean isUsable();

    String getType();

    void setUsable(boolean usable);

    void setName(String name);

    String getName();

    void use();
}
