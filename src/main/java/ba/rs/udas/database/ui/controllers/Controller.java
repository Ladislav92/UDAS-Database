package ba.rs.udas.database.ui.controllers;

import ba.rs.udas.database.ui.Loader;
import java.util.ResourceBundle;
import javafx.stage.Stage;

public interface Controller {

  default Stage prepareStage(final ResourceBundle resourceBundle) {
    Stage stage = new Stage();
    stage.getIcons().add(Loader.StaticResources.APP_ICON);
    return stage;
  }

  default void updateStage(final Stage activeStage, final ResourceBundle resourceBundle) {
  }
}
