package com.ladislav.controllers;

import com.ladislav.model.data.MemberDAO;
import javafx.stage.Stage;

public interface Controller {

  void setStage(Stage stage);

  void setDao(MemberDAO dao);
}
