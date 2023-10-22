package com.barseg.dance.screens;

import com.barseg.base.apps.ScreenOfProgram;
import com.barseg.base.controllers.FXMLController;
import com.barseg.dance.Arrow;
import com.barseg.dance.ArrowMatch;
import com.barseg.dance.PatternLoader;
import com.sun.javafx.geom.Vec2d;
import javafx.application.Platform;
import org.sikuli.script.*;

import java.util.*;

public class DanceScreen extends Thread {
    FXMLController fxmlController;
    int i = 0;
    ScreenOfProgram program;
    boolean isActive = true;
    List<Arrow> arrows = new ArrayList<>();

    Stack<Arrow> combinationLeft = new Stack<>();
    LinkedList<Arrow> combinationRight = new LinkedList<>();

    Screen spaceScreen;

    boolean readyToPressSpace = false;

    boolean isDebug = true;
    public DanceScreen(ScreenOfProgram program, FXMLController fxmlController) {
        this.program = program;
        this.fxmlController = fxmlController;
        arrows.add(new Arrow(PatternLoader.ARROW_DOWN, Key.DOWN));
        arrows.add(new Arrow(PatternLoader.ARROW_UP, Key.UP));
        arrows.add(new Arrow(PatternLoader.ARROW_LEFT, Key.LEFT));
        arrows.add(new Arrow(PatternLoader.ARROW_RIGHT, Key.RIGHT));
        spaceScreen = new Screen();
        spaceScreen.setRect(program.getX() + 800, program.getY() + 607, 217, 40);
    }

    @Override
    public void run() {
        super.run();
        program.focus();
        while (isActive) {
            try {
                doStep();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                fxmlController.setLogs("Бот остановлен");
            }
        });
    }

    public void disable() {
        this.isActive = false;
    }

    private void doStep() throws InterruptedException {

        if(readyToPressSpace) {
            Match ball = findArrow(PatternLoader.BALL, spaceScreen);
            if(ball != null) {
                if(ball.x > program.getX() + 940) {
                    System.out.println("Space click " + ball.x);
                    program.getScreen().keyDown(Key.SPACE);
                    program.getScreen().keyUp(Key.SPACE);
                    readyToPressSpace = false;
                    return;
                }
            }
        }

        Match match = null;

        for(Arrow arrow : arrows) {
            match = findArrow(arrow.getImage().similar(0.9));
            if(match != null) {
                readyToPressSpace = false;
                System.out.println(arrow.getImage().getFilename() + " ; " + match.w + " : " + match.h + " ; " + match.x + " : " + match.y);
                //Запоминаем найденную стрелку
                ArrowMatch findedArrow = new ArrowMatch(match, arrow);
                //Получаем стрелку слева от найденной
                ArrowMatch leftArrow = getLeftArrow(findedArrow.getMatch());
                //Добавим текущую стрелку в стэк левых
                combinationLeft.push(findedArrow.getArrow());
                //Если слева есть стрелка запускаем цикл по перебору
                while (leftArrow != null) {
                    //Запоминаем предыдущую стрелку
                    combinationLeft.push(leftArrow.getArrow());
                    //Получаем новую стрелку слева
                    leftArrow = getLeftArrow(leftArrow.getMatch());
                }
                //Найдем стрелку справа
                ArrowMatch rightArrow = getRightArrow(findedArrow.getMatch());
                while (rightArrow != null) {
                    //Запоминаем предыдущую стрелку
                    combinationRight.addFirst(rightArrow.getArrow());
                    //Получаем новую стрелку справа
                    rightArrow = getRightArrow(rightArrow.getMatch());
                }
                break;
            }
        }

        if(match == null) {
            if(program.getScreen().has(PatternLoader.ARROW_PRESSED)) {
                readyToPressSpace = true;
                return;
            }
        }


        while (!combinationLeft.isEmpty()) {
            pressButton(combinationLeft.pop(), program.getScreen());
            Thread.sleep(50);
        }

        while (!combinationRight.isEmpty()) {
            pressButton(combinationRight.pollLast(), program.getScreen());
            Thread.sleep(50);
        }
    }

    private Match findArrow(Pattern pattern) {
        try {
            return program.getScreen().find(pattern);
        } catch (FindFailed e) {
            return null;
        }
    }

    private Match findArrow(Pattern pattern, Screen screen) {
        try {
            return screen.find(pattern);
        } catch (FindFailed e) {
            return null;
        }
    }

    /**
     * Функция возвращает стрелку слева от переданной
     * @return - Совпадение или null
     */
    private ArrowMatch getLeftArrow(Match arrowMatch) {
        Screen screen = new Screen();
        screen.setRect(arrowMatch.x - 50, arrowMatch.y - 10, arrowMatch.w + 10, arrowMatch.h + 15);
        for (Arrow arrow : arrows) {
            Match match = findArrow(arrow.getImage().similar(0.9), screen);
            if(match != null) return new ArrowMatch(match, arrow);
        }
        return null;
    }

    /**
     * Функция возвращает стрелку слева от переданной
     * @return - Совпадение или null
     */
    private ArrowMatch getRightArrow(Match arrowMatch) {
        Screen screen = new Screen();
        screen.setRect(arrowMatch.x + 40, arrowMatch.y - 10, arrowMatch.w + 10, arrowMatch.h + 10);
        for (Arrow arrow : arrows) {
            Match match = findArrow(arrow.getImage().similar(0.9), screen);
            if(match != null) return new ArrowMatch(match, arrow);
        }
        return null;
    }

    private void pressButton(Arrow arrow, Screen screen) throws InterruptedException {
        screen.keyDown(arrow.getKey());
        screen.keyUp(arrow.getKey());
    }
}
