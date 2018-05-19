package com.ladislav.controllers;

import com.ladislav.model.data.MySqlAdapter;
import com.ladislav.util.SceneManager;
import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class LoginController implements Controller {

  MySqlAdapter mySqlAdapter;

  @FXML
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
  public void setDao(MySqlAdapter dao) {
    mySqlAdapter = dao;
  }

  @FXML
  public void onLoginBtnClicked() {

    String username = userNameText.getText();
    String password = passwordField.getText();

    try {
      mySqlAdapter = new MySqlAdapter(username, password);
    } catch (SQLException e) {
      // TODO print error dialog
      e.printStackTrace();
    }

    try {
      SceneManager.changeScene(mySqlAdapter, stage, getClass().getResource("/view/navigation.fxml"), 300, 275);
    } catch (IOException e) {
      e.printStackTrace();

    }
  }
}
