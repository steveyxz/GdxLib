package me.partlysunny.gdxlib;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setForegroundFPS(60);
        config.setTitle("Flappy Bird");
        //Emulate mobile aspect ratio
        config.setWindowedMode(360, 640);
        config.setResizable(false);
        new Lwjgl3Application(new MainGame(), config);
    }
}
