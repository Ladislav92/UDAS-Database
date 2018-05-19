package com.ladislav.controllers;

import com.ladislav.model.data.MemberDAO;
import javafx.fxml.FXML;
import javafx.stage.Stage;

// TODO think of making new member dialog instead of scene
public class NewMemberController implements Controller {

  @FXML
  private
  Stage stage;
  private MemberDAO dataAdapter;

  @Override
  public void setStage(Stage stage) {
    this.stage = stage;
  }

  @Override
  public void setDao(MemberDAO dao) {
    dataAdapter = dao;
  }

}
