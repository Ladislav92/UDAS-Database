package com.ladislav.controllers;

import com.ladislav.model.data.MySqlAdapter;
import javafx.fxml.FXML;
import javafx.stage.Stage;

// TODO think of making new member dialog instead of scene
public class NewMemberController implements Controller {

  @FXML
  Stage stage;

  MySqlAdapter dataAccess;

  @Override
  public void setStage(Stage stage) {
    this.stage = stage;
  }

  @Override
  public void setDao(MySqlAdapter dao) {
    dataAccess = dao;
  }
}
