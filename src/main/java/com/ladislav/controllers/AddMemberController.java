package com.ladislav.controllers;

import com.ladislav.model.data.MemberDAO;
import com.ladislav.model.data.MySqlAdapter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class AddMemberController implements Controller, Initializable {

  Stage stage;
  MemberDAO dataAdapter;

 @Override
  public void setStage(Stage stage) {
    this.stage = stage;
  }

  @Override
  public void setDao(MySqlAdapter dao) {

  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }
}
