package ba.rs.udas.database;

import ba.rs.udas.database.ui.LanguageManager;
import ba.rs.udas.database.ui.LanguageManager.Language;
import ba.rs.udas.database.ui.StageManager;
import ba.rs.udas.database.ui.StageManager.StaticResources;
import ba.rs.udas.database.ui.controllers.dialogs.ValuesEditorController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Test extends Application {

  private static StageManager mainStageManager;

  public static void main(String[] args) {
    launch(args);
  }

  public static StageManager getMainStageManager() {
    return mainStageManager;
  }

  @Override
  public void start(Stage stage) {
    LanguageManager.changeLanguage(Language.ENGLISH);
    stage.getIcons().add(StaticResources.APP_ICON);

    mainStageManager = new StageManager(stage).setupStage(ValuesEditorController::setupStage)
                                              .changeScene(ValuesEditorController.class)
                                              .show();
  }
}
