package com.ladislav.controllers;

import com.ladislav.model.data.DataAdapter;
import javafx.stage.Stage;

public interface Controller {

  void setStage(Stage stage);

  void setDao(DataAdapter dataAdapter);
}
