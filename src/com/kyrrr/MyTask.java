package com.kyrrr;

import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.ConsoleSystemInterface;

public class MyTask implements Runnable {
    ConsoleSystemInterface csi;
    MyTask(ConsoleSystemInterface consoleSystemInterface){
        csi = consoleSystemInterface;
    }

    @Override
    public void run() {
        while (true){
            System.out.println("hello");
            try {
                csi.wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("goodbye");
        }
    }

}
