package com.barseg.dance;

import com.barseg.base.BotApplication;
import com.barseg.base.apps.WindowsApp;
import com.barseg.dance.controllers.MainWindowController;
import javafx.stage.Stage;

public class DanceBotApplication extends BotApplication<MainWindowController> {
    WindowsApp danceWorldGameScreen;
    public DanceBotApplication() {
        super("main_window");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        super.start(primaryStage);
        MainWindowController controller = this.getController();
        try {
            danceWorldGameScreen = new WindowsApp("Dance World");
            controller.init(danceWorldGameScreen);
            controller.setLogs("Окно игры найдено");
        } catch (RuntimeException e) {
            controller.setLogs("Окно игры не найдено");
        }

    }
}
