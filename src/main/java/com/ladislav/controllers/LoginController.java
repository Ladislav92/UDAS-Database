package com.ladislav.controllers;

import com.ladislav.model.data.DataAdapter;
import com.ladislav.model.data.MySqlAdapter;
import com.ladislav.util.SceneManager;
import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 *  This part of app controls will user go further or not.
 *  If the login to server is successful - scene will be switched to navigation and DataAdapter will be instantiated.
 *  If not dialog/message will pop up.
 */
public class LoginController implements Controller {

  private DataAdapter dataAdapter;

  @FXML
  private
  Stage stage;
  @FXML
  TextField userNameText;
  @FXML
  PasswordField passwordField;

  public void showLoginErrorDialog(String message) {
    //TODO implement me
  }

  public void setStage(Stage stage) {
    this.stage = stage;
  }

  @Override
  public void setDao(DataAdapter dataAdapter) {
    this.dataAdapter = dataAdapter;
  }

  /**
   * Place where DataAdapter is created if login was successful.
   *
   * NOTE: MySqlAdapter can be switched here to any other implementation of DataAdapter without any impact
   * on the application if DataAdapter is implemented correctly. https://imgflip.com/i/2al8ve
   *
   */

  @FXML
  public void onLoginBtnClicked() {

    String username = userNameText.getText();
    String password = passwordField.getText();

    try {
      dataAdapter = new MySqlAdapter(username, password);
    } catch (SQLException e) {
      // TODO print error dialog
      e.printStackTrace();
    }

    try {
      SceneManager.changeScene(dataAdapter, stage, getClass().getResource("/view/navigation.fxml"), new NavigationController());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
