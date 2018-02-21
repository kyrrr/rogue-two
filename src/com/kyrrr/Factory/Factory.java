package com.kyrrr.Factory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kyrrebugge on 20.02.2018.
 */
public class Factory {
    
    protected List choices = new ArrayList();
    protected List result = new ArrayList();


    public List makeAtRandom(int amount, int xBound, int yBound) {
        return null;
    }

    public List getResult(){
        return result;
    }
    
    public Object getPreset(String preset) {
        return null;
    }

    
    public Object getPreset(int preset) {
        return null;
    }
}
