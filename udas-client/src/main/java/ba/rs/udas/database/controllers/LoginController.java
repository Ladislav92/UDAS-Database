package ba.rs.udas.database.controllers;

import ba.rs.udas.database.Main;
import ba.rs.udas.database.model.database.ConnectionManager;
import ba.rs.udas.database.view.LanguageManager;
import ba.rs.udas.database.view.LanguageManager.Language;
import java.sql.SQLException;
import java.util.concurrent.ForkJoinPool;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import javafx.stage.Stage;
import org.controlsfx.control.NotificationPane;


/**
 * This part of app controls will user go further or not. If the login to server is successful - scene will be
 * switched to navigation and DataAdapter will be instantiated. If not dialog/message will pop up.
 */
public final class LoginController implements Controller {

  @FXML
  private NotificationPane notificationPane;

  @FXML
  private TextField loginField;

  @FXML
  private PasswordField passwordField;

  @FXML
  private Button loginButton;

  @FXML
  private ComboBox<Language> languageComboBox;

  public static void setupStage(Stage stage) {
    stage.setResizable(false);
  }

  private void setUpBindings() {
    loginButton.disableProperty().bind(notificationPane.showingProperty());
  }

  private void setUpLanguageComboBox() {
    languageComboBox.getItems().addAll(Language.values());
    languageComboBox.setConverter(new Language.LanguageToStringConverter());
    languageComboBox.getSelectionModel().select(LanguageManager.getActiveLanguage());
  }

  @FXML
  private void onLanguageComboBoxAction(ActionEvent actionEvent) {
    LanguageManager
        .changeLanguage(languageComboBox.getSelectionModel().getSelectedItem(), Main.getMainStageManager());
  }

  @FXML
  private void initialize() {
    setUpBindings();
    setUpLanguageComboBox();

    Platform.runLater(() -> loginField.getParent().requestFocus());
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

    Main.getMainStageManager().changeScene(NavigationController.class);
    try {
      ConnectionManager.connect(username, password);
    } catch (SQLException e) {
      System.out.println(e); //TODO: proper logging
      showLoginErrorDialog(e.getMessage());
    }
  }

  @FXML
  private void onPopupButtonClicked(ActionEvent actionEvent) {

    Popup popup = new Popup();
  }
}
