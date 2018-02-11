package com.kyrrr;

import com.kyrrr.Enum.Directions;
import com.kyrrr.Model.*;
import com.kyrrr.Model.Zone;
import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.ConsoleSystemInterface;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RunnablePrintTest implements Runnable {
    private ConsoleSystemInterface csi;
    private int x;
    private int y;

    RunnablePrintTest(ConsoleSystemInterface csi, int x, int y){
        this.csi = csi;
        this.x = x;
        this.y = y;
    }

    @Override
    public void run() {
        csi.print(x, y, "!");
    }
}
