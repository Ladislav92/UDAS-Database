package com.ladislav.controllers;

import com.ladislav.model.data.MemberDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class AddMemberController implements Controller, Initializable {

  private Stage stage;
  private MemberDAO dataAdapter;

 @Override
  public void setStage(Stage stage) {
    this.stage = stage;
  }

  @Override
  public void setDao(MemberDAO dao) {
    dataAdapter = dao;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }
}
