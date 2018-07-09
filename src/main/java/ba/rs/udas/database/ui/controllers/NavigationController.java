package ba.rs.udas.database.ui.controllers;

import ba.rs.udas.database.model.database.ConnectionManager;
import ba.rs.udas.database.ui.Loader;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class NavigationController implements Controller {

  @FXML
  private Button viewMembersButton;

  @FXML
  private Button artSchoolButton;

  @FXML
  private Button logoutButton;

  @Override
  public void updateStage(final Stage activeStage, final ResourceBundle resourceBundle) {
    activeStage.setTitle(resourceBundle.getString("scene_title"));
  }


  public void onViewMembersButtonClicked(final ActionEvent actionEvent) {
    Loader.getInstance(this.getClass())
          .getStage()
          .hide();

    Loader.setInstance(MemberManagementController.class, false)
          .getStage()
          .show();
  }

  public void onArtSchoolButtonClicked(final ActionEvent actionEvent) {
    showFeatureUnavailableDialog();
  }

  public void onLogoutButtonClicked(final ActionEvent actionEvent) {
    ConnectionManager.disconnect();
    Loader.setInstance(LoginController.class, true);
  }

  public void showFeatureUnavailableDialog() {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Škola crtanja");
    alert.setHeaderText("Radovi u toku");
    alert.setContentText("Baza za školu crtanja i slikanja još nije implementirana");
    alert.showAndWait();
  }
}
