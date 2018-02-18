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
            enemy.setName("enemy"+i);
            enemy.setPos(randX, randY);
            enemy.setSpeed(9000);
            enemies.add(enemy);
        }
        return enemies;
    }
}
