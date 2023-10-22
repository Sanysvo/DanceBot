package com.barseg.base;

import com.barseg.base.controllers.FXMLController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;

public abstract class BotApplication<T extends FXMLController> extends Application {

    Stage primaryStage;
    private VBox rootLayout;
    FXMLLoader loader;

    public BotApplication(String mainScreenName) {
        loader = new FXMLLoader();
        loader.setLocation(getTemplateFromPath(mainScreenName));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        rootLayout = loader.load();
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Программа для Уличных гонок. ver: ");
        primaryStage.show();
    }

    public T getController() {
        return loader.getController();
    }

    public URL getTemplateFromPath(String fxmlName){
        return BotApplication.class.getResource("/ui/" + fxmlName + ".fxml");
    }
}
