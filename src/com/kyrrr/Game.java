package com.kyrrr;

import com.kyrrr.Enum.Directions;
import com.kyrrr.Model.*;

import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.ConsoleSystemInterface;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


class Game extends Loop {

    private boolean stop = false;
    //private Player player = new Player();
    private EnemyFactory enFac = new EnemyFactory();
    private ItemFactory iFac = new ItemFactory();
    @Override
    public void loop(ConsoleSystemInterface csi) {
        //setup();
        Player player = new Player();
        List<Enemy> enemies = enFac.makeAtRand(10, Settings.screenWidth, Settings.screenHeight);
        //List<Item> items = iFac.makeAtRand(4, Settings.screenWidth, Settings.screenHeight);
        int resetCountdown = 5;
        boolean allEnemiesDefeated = false;
        while(!stop){
            csi.refresh();
            csi.cls(); //clear screen
            if(player.isAlive()) {
                csi.print(player.getXpos(), player.getYpos(), player.getModel(), player.getModelColor());
                csi.print(Settings.screenWidth - 3, Settings.screenHeight - 1, String.valueOf(player.getStatus().getHealth()), player.getModelColor());
                for (Enemy e : enemies) {
                    csi.print(e.getXpos(), e.getYpos(), e.getModel(), e.getModelColor()); // print enemies at their current position
                }
                CharKey dir = csi.inkey(); // wait for a key press.. i don't wanna wait. threading
                int dirCode = dir.code;
                if (dirCode == CharKey.Q || dirCode == CharKey.q) {
                    stop = true; // q/Q = pause
                }
                player.move(dir);
                resetCountdown--;
                for (Enemy e : enemies) {
                    //int eMoveAmount = ThreadLocalRandom.current().nextInt(1, 6); // crash o'hoy
                    if (resetCountdown < 1) {
                        int eDirection = ThreadLocalRandom.current().nextInt(Directions.values().length);
                        e.move(Directions.values()[eDirection].toString(), 1);
                    }
                    if (player.detectCollision(e)) {
                        if (e.isAlive()) {
                            Combat c = new Combat(player, e);
                            c.loop(csi);
                        }
                        if(player.isAlive()){
                            player.getStatus().setHealth(player.getStatus().getHealth() + 7);
                        }
                    }
                    if(getAliveEnemies(enemies).size() < 1){
                        allEnemiesDefeated = true;
                    }
                }
                if (resetCountdown < 1) {
                    resetCountdown = 5;
                    player.getStatus().setHealth(player.getStatus().getHealth() - 1);
                }
                while (stop) {
                    csi.cls(); //clear screen
                    if(allEnemiesDefeated){
                        System.out.println("ALL DEAD");
                    } else {
                        csi.print(5, 10, "PAUSED - Q TO CONTINUE", CSIColor.ALICE_BLUE);
                        CharKey in = csi.inkey(); // wait for a key press
                        if (in.code == CharKey.Q || in.code == CharKey.q) {
                            stop = false;
                        }
                    }
                }
                csi.refresh(); // magic
            } else {
                csi.cls();
                csi.print(Settings.screenWidth - 7, Settings.screenHeight - 1, "DEAD", player.getModelColor());
                CharKey inst = csi.inkey(); // wait for a key press.. i don't wanna wait
                int dirCode = inst.code;
                if(dirCode == CharKey.R || dirCode == CharKey.r){
                    loop(csi);
                }
            }
        } // /while don't stop
    }

    private List<Enemy> getAliveEnemies(List<Enemy> enemies){
        List<Enemy> list = new ArrayList<>();
        for (Enemy e : enemies){
            if (e.isAlive()){
                list.add(e);
            }
        }
        return list;
    }
}
