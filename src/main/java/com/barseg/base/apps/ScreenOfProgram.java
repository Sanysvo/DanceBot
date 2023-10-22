package com.barseg.base.apps;

import org.sikuli.script.Screen;

/**
 * ��������� �������� ����������, ������� ��������� ���
 */
public interface ScreenOfProgram {
    int getX();
    int getY();
    int getHeight();
    int getWidth();
    Screen getScreen();
    void focus();
}
