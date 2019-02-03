package ba.rs.udas.database.controllers;

import ba.rs.udas.database.Main;
import ba.rs.udas.database.model.http.DataRequest;
import ba.rs.udas.database.model.member.Member;

import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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


    public static void setupStage(Stage stage) {
        stage.setResizable(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //dataAdapter = ConnectionManager.getDataAdapter(); // TODO Request


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

    private ObservableList<Member> getAllMembers() {

        String responseBody = DataRequest.doPost(
                "ba.rs.udas.database.model.dao.HackAdapter",
                "getMembers");


        System.out.println(responseBody);


        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Member>>() {}.getType();
        List<Member> members =  gson.fromJson(responseBody,listType);


        return FXCollections.observableList(members);
    }

    public void onBackBtnClicked() {

    }

    @FXML
    private void onSearchButtonClicked() {
        //TODO Display members based on query ! ! !
        //displayAllMembers();
    }

    private void displayAllMembers() {
        ObservableList<Member> memberObservableList = getAllMembers();

        //TODO Request
        //System.out.println(dataAdapter.getMembers());
        //memberObservableList = FXCollections.observableArrayList(dataAdapter.getMembers());

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
