package com.kyrrr;

import com.kyrrr.Enum.Directions;
import com.kyrrr.Model.Actor;
import com.kyrrr.Model.Enemy;
import com.kyrrr.Model.Loop;
import com.kyrrr.Model.Player;

import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.ConsoleSystemInterface;

import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ResourceBundle;
import java.util.Locale;


class Game extends Loop {

    private boolean stop = false;
    //private Player player = new Player();
    private EnemyFactory enFac = new EnemyFactory();

    @Override
    public void loop(ConsoleSystemInterface csi) {
        //setup();
        Player player = new Player();
        List<Enemy> enemies = enFac.makeAtRand(42, Settings.screenWidth, Settings.screenHeight);
        int resetCountdown = 5;
        while(!stop){
            csi.refresh();
            csi.cls(); //clear screen
            if(player.isAlive()) {
                csi.print(player.getXpos(), player.getYpos(), player.getModel(), player.getModelColor());
                csi.print(Settings.screenWidth - 3, Settings.screenHeight - 1, String.valueOf(player.getStatus().getHealth()), player.getModelColor());
                for (Enemy e : enemies) {
                    csi.print(e.getXpos(), e.getYpos(), e.getModel(), e.getModelColor()); // print enemies at their current position
                }
                CharKey dir = csi.inkey(); // wait for a key press.. i don't wanna wait
                int dirCode = dir.code;
                if (dirCode == CharKey.Q || dirCode == CharKey.q) {
                    stop = true; // q/Q = pause
                }
                player.move(dir);
                //System.out.print(player.getMoves());
                resetCountdown--;
                for (Enemy e : enemies) {
                    int randomNum = ThreadLocalRandom.current().nextInt(Directions.values().length);
                    if (resetCountdown < 1) {
                        e.move(Directions.values()[randomNum].toString(), 1);
                    }
                    if (player.detectCollision(e)) {
                        if (e.isAlive()) {
                            Combat c = new Combat(player, e);
                            c.loop(csi);
                            Actor winner = c.getWinner();
                            if (winner == player) {
                                e.die();
                            } else {
                                player.die();
                            }
                        }
                    }
                }
                if (resetCountdown < 1) {
                    resetCountdown = 5;
                    player.getStatus().setHealth(player.getStatus().getHealth() + 1);
                }
                while (stop) {
                    csi.cls(); //clear screen
                    csi.print(5, 10, "PAUSED - Q TO CONTINUE", CSIColor.ALICE_BLUE);
                    CharKey in = csi.inkey(); // wait for a key press
                    if (in.code == CharKey.Q || in.code == CharKey.q) {
                        stop = false; // q/Q = exit game
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
}
