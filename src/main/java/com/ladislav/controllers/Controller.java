package com.ladislav.controllers;

import com.ladislav.model.data.MySqlAdapter;
import javafx.stage.Stage;

public interface Controller {

  void setStage(Stage stage);

  void setDao(MySqlAdapter dao);
}
