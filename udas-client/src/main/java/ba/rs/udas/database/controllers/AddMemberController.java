package ba.rs.udas.database.controllers;

import ba.rs.udas.database.Main;
import ba.rs.udas.database.model.database.ConnectionManager;
import ba.rs.udas.database.model.database.DataAdapter;
import ba.rs.udas.database.model.member.Member;
import ba.rs.udas.database.model.member.Member.Builder;
import java.net.URL;
import java.sql.SQLException;
import java.util.Collection;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public final class AddMemberController implements Controller, Initializable {

  public ComboBox<String> professionComboBox;
  public ComboBox<String> employmentStatusComboBox;
  public ComboBox<String> residenceComboBox;
  public ComboBox<String> injuryCauseComboBox;
  public ComboBox<String> invalidityCategoryComboBox;
  public ComboBox<String> invalidityStatusComboBox;
  public ComboBox<String> invalidityPercentageComboBox;
  public ComboBox<String> cityComboBox;
  public ComboBox<String> educationLevelComboBox;
  public ComboBox<String> cityProvinceComboBox;

  public TextField nameTextField;
  public TextField surnameTextField;
  public TextField ssnTextField;
  public TextField firstPhoneTextField;
  public TextField secondPhoneTextField;
  public DatePicker dateOfBirthDatePicker;
  public DatePicker dateOfDeathDatePicker;
  public TextField homeNumberTextField;
  public TextField householdPeopleTextField;
  public RadioButton sexMaleRadioButton;
  public TextArea noteTextArea;

  private DataAdapter dataAdapter;

  @FXML
  public void openMemberManagement() {
    Main.getMainStageManager().changeScene(MemberManagementController.class);
  }

  @FXML
  public void addMemberButton(ActionEvent actionEvent) throws SQLException {
    //TODO gett all the data from elements

    String name = nameTextField.getText().trim();
    String surname = surnameTextField.getText().trim();
    String ssn = ssnTextField.getText().trim();
    String birthDate = dateOfBirthDatePicker.getConverter().toString().trim();
    String deathDate = dateOfDeathDatePicker.getConverter().toString().trim();
    String phoneNumber = firstPhoneTextField.getText().trim();
    String phoneNumber2 = secondPhoneTextField.getText().trim();
    String city = cityComboBox.getSelectionModel().getSelectedItem().trim();
//    String cityProvince = cityProvinceComboBox.getSelectionModel().getSelectedItem().trim();
    //String street = streetTx.getText().trim(); TODO
    String homeNumber = homeNumberTextField.getText().trim();
    String householdMembers = householdPeopleTextField.getText().trim();
    String educationLevel = educationLevelComboBox.getSelectionModel().getSelectedItem().trim();
    String profession = professionComboBox.getSelectionModel().getSelectedItem().trim();
    String employmentStatus = employmentStatusComboBox.getSelectionModel().getSelectedItem().trim();
    String injuryCause = injuryCauseComboBox.getSelectionModel().getSelectedItem().trim();
    String sex = sexMaleRadioButton.isSelected() ? "M" : "Ž";
    String note = noteTextArea.getText().trim();
    String invalidityStatus = invalidityStatusComboBox.getSelectionModel().getSelectedItem().trim();
    String residence = residenceComboBox.getSelectionModel().getSelectedItem().trim();

    validateInput(name, surname, ssn, birthDate, deathDate, phoneNumber, phoneNumber2, city, "", homeNumber
        , householdMembers, educationLevel, profession, employmentStatus, injuryCause, sex, note, invalidityStatus, residence,
        "");

    Member newMember =
        new Builder(name, surname)
            .withSsn(ssn)
            .withBirthDate("1939-02-10")
            .withDeathDate("2002-02-10")
            .withPhoneNumber(phoneNumber)
            .withPhoneNumber2(phoneNumber2)
            .inCity(city)
            .inCityProvince("Ćetava")
            .atStreet("Ulična žiškoćorka")
            .withHomeNumber(homeNumber)
            .withHousholdMembers(householdMembers)
            .withEducationLevel(educationLevel)
            .withProfession(profession)
            .withEmploymentStatus(employmentStatus)
            .withInjuryCause(injuryCause)
            .ofSex(sex)
            .withNote(note)
            .withInvalidityStatus(invalidityStatus)
            .withResidence(residence)
//        .withInvalidityCategory(Integer.parseInt(invalidityCategoryComboBox.getSelectionModel().getSelectedItem().trim()))
//        .withInvalidityPercentage(Integer.parseInt(invalidityPercentageComboBox.getSelectionModel().getSelectedItem().trim()))
            .build();

    System.out.println(ConnectionManager.getDataAdapter());
    boolean added = ConnectionManager.getDataAdapter().addMember(newMember);

    if (added) {
      openMemberManagement();
    } else {
      System.out.println("Add member failed. ");
      //TODO dialog
    }
  }

  private String validateInput(String name, String surname, String ssn, String birthDate, String deathDate, String phoneNumber,
      String phoneNumber2, String city, String cityProvince, String homeNumber, String householdMembers, String educationLevel,
      String profession, String employmentStatus, String injuryCause, String sex, String note, String invalidityStatus,
      String residence, String address) {

    // Morate da unesete ime člana!
    if (name == null || name.isEmpty()) {
      return "You have to provide name of the member!";
    }

    //Morate da unesete prezime člana!
    if (surname == null || surname.isEmpty()) {
      return "You have to provide surname of the member!";
    }

    //JMBG mora imati tačno 13 numeričkih karaktera.
    if (ssn != null && !ssn.matches("[0-9]{13}")) {
      return "SSN has to have exactly 13 numeric characters.";
    }

    //Morate dodati bar adresu ili broj telefona da biste uspiješno unijeli člana!
    if (address == null && (phoneNumber == null || phoneNumber2 == null)) {

      return "You have to specify at least address or phone number to succesfully add member!";
    }

    return "";
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {

    dataAdapter = ConnectionManager.getDataAdapter();
    try {
      fillCombobox(professionComboBox, dataAdapter.getProfessions());
      fillCombobox(employmentStatusComboBox, dataAdapter.getEmploymentStatuses());
      fillCombobox(injuryCauseComboBox, dataAdapter.getInjuryCauses());
      fillCombobox(invalidityStatusComboBox, dataAdapter.getInvalidityStatuses());
      fillCombobox(cityComboBox, dataAdapter.getCities());
      fillCombobox(educationLevelComboBox, dataAdapter.getEducationLevels());
      fillCombobox(residenceComboBox, dataAdapter.getResidences());
      fillCombobox(cityProvinceComboBox, dataAdapter.getProvinces());
      fillCombobox(invalidityCategoryComboBox, dataAdapter.getInvalidityCategories());
      fillCombobox(invalidityPercentageComboBox, dataAdapter.getInvalidityPercentages());

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private void fillCombobox(ComboBox<String> cb, Collection<String> data) {
    ObservableList<String> observableData = FXCollections.observableArrayList(data);
    cb.setItems(observableData);
    cb.getSelectionModel().selectFirst();
  }
}
