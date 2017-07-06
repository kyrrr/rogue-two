package com.kyrrr;

import com.kyrrr.Model.Enemy;
import com.kyrrr.Model.Move;
import net.slashie.libjcsi.CSIColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by kyrrebugge on 18.02.2017.
 */
class EnemyFactory {

    List<Enemy> makeAtRand(int amount, int xbound, int ybound){
        List<Enemy> enemies = new ArrayList<>();
        for (int i = 1; i <= amount; i++){
            Enemy enemy = new Enemy();
            int randX = ThreadLocalRandom.current().nextInt(1, xbound);
            int randY = ThreadLocalRandom.current().nextInt(1, ybound);
            int randAtk = ThreadLocalRandom.current().nextInt(1, 15);
            enemy.setAlive(true);
            enemy.addMove(new Move("e-tack", randAtk));
            enemy.setSpeed(10);
            enemy.setPos(randX, randY);
            enemy.setModel("E");
            enemy.setModelColor(CSIColor.AMBER);
            enemies.add(enemy);
        }
        return enemies;
    }
}
