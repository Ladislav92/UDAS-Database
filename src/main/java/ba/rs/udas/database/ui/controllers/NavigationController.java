package ba.rs.udas.database.ui.controllers;

import ba.rs.udas.database.model.database.DataAdapter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class NavigationController implements Controller {

  @FXML
  private Stage stage;

  private DataAdapter dataAdapter;


  @FXML
  public void onMembersBtnClicked(ActionEvent actionEvent) {
   /* SceneManager
        .changeScene
            (
                dataAdapter,
                stage,
                getClass().getResource("/ba/rs/udas/database/ui/controllers/member_management.fxml"),
                new MemberManagementController()
            );*/
  }

  public void onArtSchoolBtnClicked(ActionEvent actionEvent) {
    showFeatureUnavailableDialog();
  }

  public void onLogoutBtnClicked(ActionEvent actionEvent) {
   /* SceneManager
        .changeScene(stage, getClass().getResource("/ba/rs/udas/database/ui/controllers/login.fxml"), new LoginController());*/
  }

  public void showFeatureUnavailableDialog() {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Škola crtanja");
    alert.setHeaderText("Radovi u toku");
    alert.setContentText("Baza za školu crtanja i slikanja još nije implementirana");
    alert.showAndWait();
  }

  public void setStage(Stage stage) {
    this.stage = stage;
  }

  public void setDao(DataAdapter dataAdapter) {
    this.dataAdapter = dataAdapter;
  }

  public void addMemberButton(ActionEvent actionEvent) {
  }

  public void openMemberManagement(ActionEvent actionEvent) {
  }
}
