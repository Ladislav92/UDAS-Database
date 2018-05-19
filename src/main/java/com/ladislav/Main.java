package com.ladislav;

import com.ladislav.controllers.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {

    FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
    Parent root = loginLoader.load();

    LoginController loginController = loginLoader.getController();
    loginController.setStage(primaryStage);
    primaryStage.setTitle("Dobrodošli!");
    primaryStage.setScene(new Scene(root, 300, 275));
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
