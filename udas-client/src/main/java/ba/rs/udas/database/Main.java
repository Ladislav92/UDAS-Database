package ba.rs.udas.database;

import ba.rs.udas.database.view.StageManager;
import ba.rs.udas.database.view.StageManager.StaticResources;
import ba.rs.udas.database.controllers.LoginController;
import javafx.application.Application;
import javafx.stage.Stage;


/**
 * Application entry point. When app is started it loads login scene which controls further flow of the
 * application.
 * <p>
 * After successful login, navigation scene is loaded. From there user can go to member management or admin
 * panel, as well as log out.
 * <p>
 * Although this class is entry point of application, LoginController does important job - initializes
 * DataAdapter object if login is successful.
 */

public class Main extends Application {

  private static StageManager mainStageManager;

  public static void main(String[] args) {
    launch(args);
  }

  public static StageManager getMainStageManager() {
    return mainStageManager;
  }

  @Override
  public void start(Stage stage) {
    stage.getIcons().add(StaticResources.APP_ICON);
    stage.setResizable(false);

    mainStageManager = new StageManager(stage).setupStage(LoginController::setupStage)
                                              .changeScene(LoginController.class)
                                              .show();
  }
}
