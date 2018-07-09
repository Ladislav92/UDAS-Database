package ba.rs.udas.database.ui.controllers;

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

/**
 * Here happens most of the important stuff for an end user. Members can be searched, queried
 * added/updated/deleted.
 */

public class MemberManagementController implements Controller, Initializable {

  @FXML
  private TableView<Member> membersTableView;

  @FXML
  private TableColumn<Member, String> nameCol;

  @FXML
  private TableColumn<Member, String> surnameCol;

  @FXML
  private TableColumn<Member, String> cityCol;

  @FXML
  private TableColumn<Member, String> addressCol;

  @FXML
  private TableColumn<Member, String> phoneOneCol;

  @FXML
  private TableColumn<Member, String> phoneTwoCol;


  private DataAdapter dataAdapter;


  @Override
  public void initialize(URL location, ResourceBundle resources) {
    dataAdapter = ConnectionManager.getDataAdapter();

    nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    surnameCol.setCellValueFactory(cellData -> cellData.getValue().surnameProperty());
    cityCol.setCellValueFactory(cellData -> cellData.getValue().cityProperty());
    addressCol.setCellValueFactory(cellData -> cellData.getValue().streetProperty());
    phoneOneCol.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());
    phoneTwoCol.setCellValueFactory(cellData -> cellData.getValue().phoneNumber2Property());
    membersTableView.setEditable(false);
    displayAllMembers();

    if (!membersTableView.getSelectionModel().isEmpty()) {
      membersTableView.getSelectionModel().selectFirst();
    }

  }

  public void onBackBtnClicked() {
    /*SceneManager.changeScene(stage, getClass().getResource(
        "/ba/rs/udas/database/ui/controllers/navigation.fxml"),
        new NavigationController());*/
  }

  @FXML
  public void onSearchButtonClicked() {
    //TODO Display members based on query ! ! !

    displayAllMembers();
  }

  public void displayAllMembers() {
    ObservableList<Member> memberObservableList = null;

    try {
      System.out.println(dataAdapter.getMembers());
      memberObservableList = FXCollections.observableArrayList(dataAdapter.getMembers());
    } catch (SQLException e) {
      e.printStackTrace();
    }

    membersTableView.setItems(memberObservableList);
  }

  @FXML
  public void openNewMemberDialog(ActionEvent actionEvent) {
   /* try {
      SceneManager.changeScene(
          dataAdapter,
          stage,
          getClass().getResource("/ba/rs/udas/database/ui/controllers/add_member.fxml"),
          new AddMemberController()
      );
    } catch (IOException e) {
      e.printStackTrace();
    }*/
  }
}
