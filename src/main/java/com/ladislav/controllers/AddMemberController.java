package com.ladislav.controllers;

import com.ladislav.model.data.MemberDAO;
import com.ladislav.model.member.Member;
import com.ladislav.util.SceneManager;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

// TODO think of making new member dialog instead of scene
public class AddMemberController implements Controller, Initializable {

  @FXML
  private
  Stage stage;
  private MemberDAO dataAdapter;

  @Override
  public void setStage(Stage stage) {
    this.stage = stage;
  }

  @Override
  public void setDao(MemberDAO dao) {
    dataAdapter = dao;
  }

  @FXML
  public void openMemberManagement() {
    try {
      SceneManager
          .changeScene(dataAdapter, stage, getClass().getResource("/view/member_management.fxml"), new MemberMgmtController());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  public void addMemberButton(ActionEvent actionEvent) {

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
