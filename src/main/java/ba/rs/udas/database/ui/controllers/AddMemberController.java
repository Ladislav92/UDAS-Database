package ba.rs.udas.database.ui.controllers;

import ba.rs.udas.database.model.database.DataAdapter;
import ba.rs.udas.database.model.member.Member;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

// TODO think of making new member dialog instead of scene
public class AddMemberController implements Controller, Initializable {

  @FXML
  private Stage stage;

  private DataAdapter dataAdapter;


  public void setStage(Stage stage) {
    this.stage = stage;
  }

  public void setDao(DataAdapter dataAdapter) {
    this.dataAdapter = dataAdapter;
  }

  @FXML
  public void openMemberManagement() {
/*    try {
      SceneManager
          .changeScene(dataAdapter, stage,
              getClass().getResource("/ba/rs/udas/database/ui/controllers/member_management.fxml"),
              new MemberManagementController());
    } catch (IOException e) {
      e.printStackTrace();
    }*/
  }

  @FXML
  public void addMemberButton(ActionEvent actionEvent) throws SQLException {

    //TODO gett all the data from elements

    Member newMember = null;

    boolean added = dataAdapter.addMember(newMember);

    if (added) {
      openMemberManagement();
    } else {
      System.out.println("Add member failed. ");
      //TODO dialog
    }
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {

    //TODO add all data from DB to comboboxes

  }
}
