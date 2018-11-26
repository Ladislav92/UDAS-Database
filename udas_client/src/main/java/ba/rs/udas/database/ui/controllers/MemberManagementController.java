package ba.rs.udas.database.ui.controllers;

import ba.rs.udas.database.Main;
import ba.rs.udas.database.model.database.ConnectionManager;
import ba.rs.udas.database.model.database.DataAdapter;
import ba.rs.udas.database.model.member.Member;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * Here happens most of the important stuff for an end user. Members can be searched, queried
 * added/updated/deleted.
 */

public final class MemberManagementController implements Controller, Initializable {

  @FXML
  private TableView<Member> membersTableView;

  @FXML
  private TableColumn<Member, String> firstNameTableColumn;

  @FXML
  private TableColumn<Member, String> lastNameTableColumn;

  @FXML
  private TableColumn<Member, String> cityTableColumn;

  @FXML
  private TableColumn<Member, String> addressTableColumn;

  @FXML
  private TableColumn<Member, String> primaryPhoneTableColumn;

  @FXML
  private TableColumn<Member, String> secondaryPhoneTableColumn;

  private DataAdapter dataAdapter;

  public static void setupStage(Stage stage) {
    stage.setResizable(true);
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    dataAdapter = ConnectionManager.getDataAdapter();

    firstNameTableColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    lastNameTableColumn.setCellValueFactory(cellData -> cellData.getValue().surnameProperty());
    cityTableColumn.setCellValueFactory(cellData -> cellData.getValue().cityProperty());
    addressTableColumn.setCellValueFactory(cellData -> cellData.getValue().streetProperty());
    primaryPhoneTableColumn.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());
    secondaryPhoneTableColumn.setCellValueFactory(cellData -> cellData.getValue().phoneNumber2Property());
    membersTableView.setEditable(false);
    displayAllMembers();

    if (!membersTableView.getSelectionModel().isEmpty()) {
      membersTableView.getSelectionModel().selectFirst();
    }

  }

  public void onBackBtnClicked() {

  }

  @FXML
  private void onSearchButtonClicked() {
    //TODO Display members based on query ! ! !
    //displayAllMembers();
  }

  private void displayAllMembers() {
    ObservableList<Member> memberObservableList = null;

    try {
      System.out.println(dataAdapter.getMembers());
      memberObservableList = FXCollections.observableArrayList(dataAdapter.getMembers());
    } catch (SQLException e) {
      e.printStackTrace();
    }

    membersTableView.setItems(memberObservableList);
  }

  // Options menu
  @FXML
  private void openNewMemberDialog(ActionEvent actionEvent) {
    Main.getMainStageManager()
        .changeScene(AddMemberController.class);
  }

  @FXML
  private void onNewMemberMenuItemClicked(ActionEvent actionEvent) {
    Main.getMainStageManager()
        .changeScene(AddMemberController.class);
  }

  @FXML
  private void onSignOutMenuItemClicked(ActionEvent actionEvent) {
    ConnectionManager.disconnect();
    Main.getMainStageManager()
        .changeScene(LoginController.class)
        .setupStage(LoginController::setupStage);
  }

  // Search menu

  @FXML
  private void onExitMenuItemClicked(ActionEvent actionEvent) {
    System.out.println("onExitMenuItemClicked");
  }

  @FXML
  private void onSearchFiltersMenuItemClicked(ActionEvent actionEvent) {
    System.out.println("onSearchFiltersMenuItemClicked");
  }

  @FXML
  private void onResetFiltersMenuItemClicked(ActionEvent actionEvent) {
    System.out.println("onResetFiltersMenuItemClicked");
  }

  // Help menu

  @FXML
  private void onFindMenuItemClicked(ActionEvent actionEvent) {
    System.out.println("onFindMenuItemClicked");
  }

  @FXML
  private void onUserGuideMenuItemClicked(ActionEvent actionEvent) {
    System.out.println("onUserGuideMenuItemClicked");
  }

  @FXML
  private void onAboutMenuItemClicked(ActionEvent actionEvent) {
    System.out.println("onAboutMenuItemClicked");
  }

}
