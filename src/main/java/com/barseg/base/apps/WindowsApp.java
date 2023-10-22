package com.barseg.base.apps;

import org.sikuli.script.App;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

public class WindowsApp implements ScreenOfProgram {
    public App app;
    private Region region;
    private Screen screen = new Screen();

    private String name;

    public WindowsApp(String name){
        this.name = name;
        app = new App(name);
        if(!app.isRunning()){
            throw new RuntimeException("Необходимое приложение не запущено");
        }
        region = app.window(0);
    }

    public int getX(){
        return region.x;
    }

    public int getY(){
        return region.y;
    }

    public int getHeight(){
        return region.h;
    }

    public int getWidth(){
        return region.w;
    }



    public Screen getScreen(){
        screen.setRect(region);

        return screen;
    }

    @Override
    public void focus() {
        this.app.focus();
    }
}
