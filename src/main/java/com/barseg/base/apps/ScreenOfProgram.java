package com.barseg.base.apps;

import org.sikuli.script.Screen;

/**
 * Интерфейс оконного приложения, которое исследует бот
 */
public interface ScreenOfProgram {
    int getX();
    int getY();
    int getHeight();
    int getWidth();
    Screen getScreen();
    void focus();
}
