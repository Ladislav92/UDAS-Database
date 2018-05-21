package com.ladislav.controllers;

import com.ladislav.model.data.DataAdapter;
import javafx.stage.Stage;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ArtSchoolController implements Controller {

  // TODO NOT :) Feature not discussed yet, do not implement !
  @Override
  public void setStage(Stage stage) {

  }

  @Override
  public void setDao(DataAdapter dataAdapter) {
      throw new NotImplementedException();
  }
}
