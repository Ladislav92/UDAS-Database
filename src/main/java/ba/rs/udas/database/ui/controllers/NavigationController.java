package ba.rs.udas.database.ui.controllers;

import ba.rs.udas.database.SceneManager;
import ba.rs.udas.database.model.database.ConnectionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class NavigationController implements Controller {

  @FXML
  private Button viewMembersButton;

  @FXML
  private Button artSchoolButton;

  @FXML
  private Button logoutButton;

  public void onViewMembersButtonClicked(final ActionEvent actionEvent) {
    SceneManager.changeScene(MemberManagementController.class)
                .setupStage(MemberManagementController::setupStage);
  }

  public void onArtSchoolButtonClicked(final ActionEvent actionEvent) {
    showFeatureUnavailableDialog();
  }

  public void onLogoutButtonClicked(final ActionEvent actionEvent) {
    ConnectionManager.disconnect();
    SceneManager.changeScene(LoginController.class)
                .setupStage(LoginController::setupStage);
  }

  public void showFeatureUnavailableDialog() {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Škola crtanja");
    alert.setHeaderText("Radovi u toku");
    alert.setContentText("Baza za školu crtanja i slikanja još nije implementirana");
    alert.showAndWait();
  }
}
