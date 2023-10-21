package com.barseg.base;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;

public class BotApplication extends Application {

    Stage primaryStage;
    private VBox rootLayout;
    FXMLLoader loader;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        loader = new FXMLLoader();
        loader.setLocation(getTemplateFromPath("main_window"));
        rootLayout = loader.load();
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Программа для Уличных гонок. ver: ");
        primaryStage.show();
    }

    public URL getTemplateFromPath(String fxmlName){
        return BotApplication.class.getResource("/ui/" + fxmlName + ".fxml");
    }
}
