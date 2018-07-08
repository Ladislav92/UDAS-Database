package ba.rs.udas.database.ui.controllers;

import ba.rs.udas.database.model.database.ConnectionManager;
import ba.rs.udas.database.ui.Loader;
import java.sql.SQLException;
import java.util.concurrent.ForkJoinPool;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.NotificationPane;


/**
 * This part of app controls will user go further or not. If the login to server is successful - scene will be
 * switched to navigation and DataAdapter will be instantiated. If not dialog/message will pop up.
 */
public class LoginController implements Controller {

  @FXML
  private NotificationPane notificationPane;

  @FXML
  private TextField loginField;

  @FXML
  private PasswordField passwordField;

  @FXML
  private Button loginButton;

  @FXML
  private ComboBox languageComboBox;

  private void setUpBindings() {
    loginButton.disableProperty().bind(notificationPane.showingProperty());
  }

  @FXML
  private void initialize() {
    setUpBindings();
    Platform.runLater(() -> loginField.getParent().requestFocus());
  }

  @Override
  public void postInit(Loader loader) {
    String title = Loader.getResourceBundle(this.getClass())
                         .getString("scene_title");

    Stage stage = loader.getStage();
    stage.setTitle(title);
    stage.setResizable(false);

    languageComboBox.getItems().addAll("en", "sr", "ср");
  }

  //TODO: develop further
  public void showLoginErrorDialog(String message) {
    notificationPane.setText(message);
    notificationPane.show();
    ForkJoinPool.commonPool()
                .execute(() -> {
                  try {
                    Thread.sleep(3000);
                    Platform.runLater(notificationPane::hide);
                  } catch (InterruptedException e) {
                    //ignored
                  }
                });
  }

  /**
   * Place where DataAdapter is created if login was successful.
   * <p>
   * NOTE: MySqlAdapter can be switched here to any other implementation of DataAdapter without any impact on
   * the application if DataAdapter is implemented correctly. https://imgflip.com/i/2al8ve
   */

  @FXML
  public void onLoginButtonClicked() {
    String username = loginField.getText();
    String password = passwordField.getText();

    try {
      ConnectionManager.connect(username, password);
    } catch (SQLException e) {
      System.out.println(e); //TODO: proper logging
      showLoginErrorDialog(e.getMessage());
    }
  }
}
