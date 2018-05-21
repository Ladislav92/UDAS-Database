package com.ladislav.controllers;

import com.ladislav.model.data.DataAdapter;
import com.ladislav.util.SceneManager;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class NavigationController implements Controller {

  @FXML
  private
  Stage stage;

  private DataAdapter dataAdapter;

  @FXML
  public void onMembersBtnClicked(ActionEvent actionEvent) throws IOException {
    SceneManager
        .changeScene
            (
                dataAdapter,
                stage,
                getClass().getResource("/view/member_management.fxml"),
                new MemberMgmtController()
            );
  }

  public void onArtSchoolBtnClicked(ActionEvent actionEvent) {
    showFeatureUnavailableDialog();
  }

  public void onLogoutBtnClicked(ActionEvent actionEvent) throws IOException {
    SceneManager.changeScene(stage, getClass().getResource("/view/login.fxml"), new LoginController());
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

  @Override
  public void setDao(DataAdapter dataAdapter) {
    this.dataAdapter =  dataAdapter;
  }

}
