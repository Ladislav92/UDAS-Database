package com.ladislav;

import com.ladislav.controllers.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *  Application entry point.
 *  When app is started it loads login scene which controls further flow of the application.
 *
 *  After successful login, navigation scene is loaded. From there user can go to member management or admin panel,
 *  as well as log out.
 *
 *  Although this class is entry point of application, LoginController does important job - initializes DataAdapter object
 *  if login is successful.
 *
 */
public class Main extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {

    FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/view/login.fxml"));

    LoginController loginController = new LoginController();
    loginLoader.setController(loginController);

    Parent root = loginLoader.load();
    loginController.setStage(primaryStage);


    Scene scene = new Scene(root);
    scene.getStylesheets().add(this.getClass() .getResource("/view/style.css").toExternalForm());

    primaryStage.setTitle("Dobrodošli!");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
