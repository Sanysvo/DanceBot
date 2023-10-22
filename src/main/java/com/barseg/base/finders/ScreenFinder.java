package com.barseg.base.finders;


import com.barseg.base.apps.ScreenOfProgram;

public class ScreenFinder extends Thread {
    ScreenOfProgram program;
    private boolean runs = true;
    @Override
    public void interrupt() {
        super.interrupt();
    }

    public ScreenFinder(ScreenOfProgram program) {
        this.program = program;
    }

    @Override
    public synchronized void start() {
        program.focus();
        super.start();
    }

    public void setRuns(boolean runs) {
        this.runs = runs;
    }

    @Override
    public void run() {
        super.run();
        while (runs){
            doStep();
        }
    }

    public void doStep() {

    }
}
