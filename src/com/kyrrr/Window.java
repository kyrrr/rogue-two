package com.kyrrr;

import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;

import java.util.Properties;

/**
 * Created by kyrrebugge on 17.02.2017.
 */
class Window {
    private Properties properties = new Properties();
    private ConsoleSystemInterface csi = null;
    private int width = Settings.screenWidth;
    private int height = Settings.screenHeight;

    ConsoleSystemInterface make(){
        this.properties.setProperty("font", Settings.font);
        this.properties.setProperty("fontsize", Settings.fontSize);
        try{
            String title = Settings.title;
            this.csi = new WSwingConsoleInterface(title, properties);

        }
        catch (ExceptionInInitializerError e) {
            System.out.println("*** Error: Swing Console Box cannot be initialized. Exiting...");
            e.printStackTrace();
            System.exit(-1);
        }
        return this.csi;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Properties getProperties() {
        return properties;
    }
}
