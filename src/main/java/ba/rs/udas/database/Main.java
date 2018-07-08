package ba.rs.udas.database;

import ba.rs.udas.database.ui.Loader;
import ba.rs.udas.database.ui.controllers.LoginController;
import javafx.application.Application;
import javafx.scene.image.Image;
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

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) {
    //Locale.setDefault(new Locale("sr", "BA"));
    Loader loader = Loader
        .setInstance(stage, LoginController.class, Loader.getResourceBundle(LoginController.class));
    stage.setScene(loader.getScene());
    stage.getIcons()
         .add(new Image(Main.class.getResourceAsStream("/assets/img/appicon_256px.png")));
    stage.show();
  }
}
