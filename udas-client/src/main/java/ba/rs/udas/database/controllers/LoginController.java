package ba.rs.udas.database.controllers;

import ba.rs.udas.database.Main;
import ba.rs.udas.database.model.http.DataRequest;
import ba.rs.udas.database.view.LanguageManager;
import ba.rs.udas.database.view.LanguageManager.Language;
import java.util.concurrent.ForkJoinPool;

import com.google.gson.Gson;
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

  @FXML
  public void onLoginButtonClicked() {

    String username = loginField.getText().trim();
    String password = passwordField.getText().trim();

    String responseBody = DataRequest.doPost(
            "ba.rs.udas.database.model.endpoint.Login",
            "login",
            "{\"username\":\"" + username + "\",\"password\" : \"" + password + "\"}");

    LoginResponse login = new Gson().fromJson(responseBody, LoginResponse.class);

    if (login.success) {
      DataRequest.setToken(login.token);
      Main.getMainStageManager().changeScene(NavigationController.class);
    } else {
      showLoginErrorDialog("Invalid username or password");
    }
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

  private class LoginResponse {
    String token;
    boolean success;
  }

  @FXML
  private void onPopupButtonClicked(ActionEvent actionEvent) {

    Popup popup = new Popup();
  }
}
