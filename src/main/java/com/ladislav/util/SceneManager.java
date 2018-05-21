package com.ladislav.util;

import com.ladislav.controllers.Controller;
import com.ladislav.model.data.DataAdapter;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Simple util class to change between scenes from controllers. Every controller has to hold Stage
 * reference so this class could be used properly.
 */

public class SceneManager {

  public static final int DEFAULT_WIDTH = 800;
  public static final int DEFAULT_HEIGHT = 600;

  public static void changeScene(Stage stage, URL resource, Controller controller) throws IOException {
    changeScene(stage, resource, controller, DEFAULT_WIDTH, DEFAULT_HEIGHT);
  }

  public static void changeScene(Stage stage, URL resource, Controller controller, int width, int height)
      throws IOException {
    FXMLLoader loader = new FXMLLoader(resource);
    controller.setStage(stage);
    loader.setController(controller);

    Parent root = loader.load();
    stage.setScene(new Scene(root, width, height));
  }


  public static void changeScene(DataAdapter dao, Stage stage, URL resource, Controller controller)
      throws IOException {
    FXMLLoader loader = new FXMLLoader(resource);
    controller.setDao(dao);
    controller.setStage(stage);
    loader.setController(controller);

    Parent root = loader.load();
    stage.setScene(new Scene(root, DEFAULT_WIDTH, DEFAULT_HEIGHT));

  }
}

