package com.ladislav.controllers;

import com.ladislav.model.data.MemberDAO;
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
 *  If the login to server is successful - scene will be switched to navigation and MemberDAO will be instantiated.
 *  If not dialog/message will pop up.
 */
public class LoginController implements Controller {

  private MemberDAO dataAdapter;

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

  public void setStage(Stage previousStage) {
    this.stage = previousStage;
  }

  @Override
  public void setDao(MemberDAO dao) {
    dataAdapter = dao;
  }

  /**
   * Place where MemberDAO is created if login was successful.
   *
   * NOTE: MySqlAdapter can be switched here to any other implementation of MemberDAO without any impact
   * on the application if MemberDAO is implemented correctly. https://imgflip.com/i/2al8ve
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
