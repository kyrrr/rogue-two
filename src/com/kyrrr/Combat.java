package com.kyrrr;

import com.kyrrr.Model.*;
import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.ConsoleSystemInterface;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by kyrrebugge on 03.07.2017.
 * COMBAAAT
 */
// TODO: ATTACKS/EVERYTHING
class Combat extends Loop {

    private List<Actor> actors = new ArrayList<>();
    
    Combat(Actor... actors){
        Collections.addAll(this.actors, actors);
    }

    @Override
    public void loop(ConsoleSystemInterface csi){
        boolean matchOver = false;
        while(!matchOver){
            List<Actor> order = getOrder();
            for (Actor a : order){
                csi.print(a.getXpos(), a.getYpos(), a.getModel(), a.getModelColor());
            }
            if (getOrder().size() > 1) {
                for (Actor a : order) {
                    Actor target = a.chooseTarget(this.actors);
                    System.out.println(a + " will attack: " + target);
                    if (a instanceof Player) {
                        CharKey k = csi.inkey();
                        System.out.println(a + " pressed key: " + k);
                    }
                    a.attack(target, a.getMoves().get(0));
                }
            } else {
                matchOver = true;
            }
        }
    }

    private List<Actor> getOrder(){
        List<Actor> order = Collections.synchronizedList(new ArrayList<>());
        TreeMap<Integer, Actor> aMap = new TreeMap<>(Collections.reverseOrder());
        this.actors.stream().filter(Actor::isAlive).forEach(actor -> {
            aMap.put(actor.getSpeed(), actor);
        });
        order.addAll(aMap.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList()));
        return order;
    }
}
