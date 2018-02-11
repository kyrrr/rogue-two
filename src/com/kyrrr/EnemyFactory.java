package com.kyrrr;

import com.kyrrr.Model.Effects.BoostHealth;
import com.kyrrr.Model.Effects.DamageHealth;
import com.kyrrr.Model.Effects.Poison;
import com.kyrrr.Model.Enemy;
import com.kyrrr.Model.Items.Sword;
import com.kyrrr.Model.Move;
import net.slashie.libjcsi.CSIColor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by kyrrebugge on 18.02.2017.
 */
class EnemyFactory {

    List<Enemy> makeAtRand(int amount, int xbound, int ybound){
        List<Enemy> enemies = new ArrayList<>();
        //Zone copy = exclude.getCopy();
        for (int i = 1; i <= amount; i++){
            Enemy enemy = new Enemy();
            int randX = ThreadLocalRandom.current().nextInt(12, xbound - 1);
            int randY = ThreadLocalRandom.current().nextInt(1, ybound - 1);
            int randAtk = ThreadLocalRandom.current().nextInt(1, 15);
            enemy.setAlive(true);
            enemy.addMove(new Move("Scratch", 1000, new DamageHealth(randAtk)));
            enemy.addMove(new Move("Spit", 1000, new Poison()));
            enemy.addMove(new Move("Heal", 1000, new BoostHealth(25)));
            enemy.setSpeed(10);
            enemy.setPos(randX, randY);
            enemy.getZone().setOrigin(enemy.getCoordinates());
            enemy.getZone().setHeight(4);
            enemy.getZone().setWidth(10);
            enemy.getZone().calcRect();
            enemy.setModel("E");
            enemy.setModelColor(CSIColor.AMBER);
            enemy.getInventory().add(new Sword());
            enemies.add(enemy);
        }
        return enemies;
    }
}
