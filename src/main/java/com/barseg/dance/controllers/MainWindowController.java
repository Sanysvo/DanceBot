package com.barseg.dance.controllers;

import com.barseg.base.apps.ScreenOfProgram;
import com.barseg.base.controllers.FXMLController;
import com.barseg.dance.screens.DanceScreen;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainWindowController implements FXMLController {
    @FXML
    Label logs;

    ScreenOfProgram program;

    DanceScreen danceScreen;

    public void setLogs(String message) {
        logs.setText(message);
    }

    public void init(ScreenOfProgram program) {
        this.program = program;
    }

    public void startBot() {
        danceScreen = new DanceScreen(program, this);
        danceScreen.start();
    }

    public void stopBot() {
        danceScreen.disable();
    }
}
