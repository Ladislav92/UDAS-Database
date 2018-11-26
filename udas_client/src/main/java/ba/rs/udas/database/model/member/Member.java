package ba.rs.udas.database.model.member;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleStringProperty;

public class Member {

  private int memberID;

  private SimpleStringProperty name;
  private SimpleStringProperty surname;
  private SimpleStringProperty ssn;
  private SimpleStringProperty cityProvince;
  private SimpleStringProperty street;
  private SimpleStringProperty homeNumber;
  private SimpleStringProperty householdMembers;
  private SimpleStringProperty sex;
  private SimpleStringProperty note;
  private SimpleStringProperty birthDate; //TODO use date object ?!?!
  private SimpleStringProperty deathDate; //TODO use date object ?!?!
  private SimpleStringProperty city;
  private SimpleStringProperty phoneNumber;
  private SimpleStringProperty phoneNumber2;
  private SimpleStringProperty employmentStatus;
  private SimpleStringProperty educationLevel;
  private SimpleStringProperty profession;
  private SimpleStringProperty residence;
  private SimpleStringProperty maritialStatus;
  private SimpleStringProperty invalidityStatus;
  private int invalidityCategory;
  private int invalidityPercentage;
  private SimpleStringProperty injuryCause;
  private List<Injury> injuries;

  public Member(Builder builder) {
    this.memberID = builder.memberID;
    this.name = builder.name;
    this.surname = builder.surname;
    this.ssn = builder.ssn;
    this.birthDate = builder.birthDate;
    this.phoneNumber = builder.phoneNumber;
    this.phoneNumber2 = builder.phoneNumber2;
    this.city = builder.city;
    this.cityProvince = builder.cityProvince;
    this.street = builder.street;
    this.homeNumber = builder.homeNumber;
    this.householdMembers = builder.householdMembers;
    this.deathDate = builder.deathDate;
    this.educationLevel = builder.educationLevel;
    this.profession = builder.profession;
    this.employmentStatus = builder.employmentStatus;
    this.injuryCause = builder.injuryCause;
    this.invalidityStatus = builder.invalidityStatus;
    this.residence = builder.residence;
    this.sex = builder.sex;
    this.note = builder.note;
    this.injuries = builder.injuries;
    this.invalidityCategory = builder.invalidityCategory;
    this.invalidityPercentage = builder.invalidityPercentage;
  }

  public Member(int memberID, String name, String surname,
      String SSN, String dateOfBirth, String phoneNumber1, String phoneNumber2,
      String city, String cityProvince, String street, String homeNumber, String peopleInHousehold,
      String dateOfDeath, String educationLevel, String profession,
      String workStatus, String injuryCause, String invalidityStatus,
      String housingQuestion, String sex, String note, List<Injury> injuries) {

    this.memberID = memberID;
    this.name = new SimpleStringProperty(name);
    this.surname = new SimpleStringProperty(surname);
    this.ssn = new SimpleStringProperty(SSN);
    this.birthDate = new SimpleStringProperty(dateOfBirth);
    this.phoneNumber = new SimpleStringProperty(phoneNumber1);
    this.phoneNumber2 = new SimpleStringProperty(phoneNumber2);
    this.city = new SimpleStringProperty(city);
    this.cityProvince = new SimpleStringProperty(cityProvince);
    this.street = new SimpleStringProperty(street);
    this.homeNumber = new SimpleStringProperty(homeNumber);
    this.householdMembers = new SimpleStringProperty(peopleInHousehold);
    this.deathDate = new SimpleStringProperty(dateOfDeath);
    this.educationLevel = new SimpleStringProperty(educationLevel);
    this.profession = new SimpleStringProperty(profession);
    this.employmentStatus = new SimpleStringProperty(workStatus);
    this.injuryCause = new SimpleStringProperty(injuryCause);
    this.invalidityStatus = new SimpleStringProperty(invalidityStatus);
    this.residence = new SimpleStringProperty(housingQuestion);
    this.sex = new SimpleStringProperty(sex);
    this.note = new SimpleStringProperty(note);
    this.injuries = injuries;
  }

  public String getCity() {
    return city.get();
  }

  public SimpleStringProperty cityProperty() {
    return city;
  }

  public SimpleStringProperty nameProperty() {
    return name;
  }

  public SimpleStringProperty surnameProperty() {
    return surname;
  }

  public String getSsn() {
    return ssn.get();
  }

  public SimpleStringProperty ssnProperty() {
    return ssn;
  }

  public String getCityProvince() {
    return cityProvince.get();
  }

  public SimpleStringProperty cityProvinceProperty() {
    return cityProvince;
  }

  public String getStreet() {
    return street.get();
  }

  public SimpleStringProperty streetProperty() {
    return street;
  }

  public String getHomeNumber() {
    return homeNumber.get();
  }

  public SimpleStringProperty homeNumberProperty() {
    return homeNumber;
  }

  public String getHouseholdMembers() {
    return householdMembers.get();
  }

  public String getSex() {
    return sex.get();
  }

  public SimpleStringProperty sexProperty() {
    return sex;
  }

  public String getNote() {
    return note.get();
  }

  public SimpleStringProperty noteProperty() {
    return note;
  }

  public String getBirthDate() {
    return birthDate.get();
  }

  public SimpleStringProperty birthDateProperty() {
    return birthDate;
  }

  public String getDeathDate() {
    return deathDate.get();
  }

  public SimpleStringProperty deathDateProperty() {
    return deathDate;
  }

  public String getPhoneNumber() {
    return phoneNumber.get();
  }

  public SimpleStringProperty phoneNumberProperty() {
    return phoneNumber;
  }

  public String getPhoneNumber2() {
    return phoneNumber2.get();
  }

  public SimpleStringProperty phoneNumber2Property() {
    return phoneNumber2;
  }

  public String getEmploymentStatus() {
    return employmentStatus.get();
  }

  public SimpleStringProperty employmentStatusProperty() {
    return employmentStatus;
  }

  public String getEducationLevel() {
    return educationLevel.get();
  }

  public SimpleStringProperty educationLevelProperty() {
    return educationLevel;
  }

  public String getProfession() {
    return profession.get();
  }

  public SimpleStringProperty professionProperty() {
    return profession;
  }

  public String getResidence() {
    return residence.get();
  }

  public SimpleStringProperty residenceProperty() {
    return residence;
  }

  public String getMaritialStatus() {
    return maritialStatus.get();
  }

  public SimpleStringProperty maritialStatusProperty() {
    return maritialStatus;
  }

  public String getInvalidityStatus() {
    return invalidityStatus.get();
  }

  public SimpleStringProperty invalidityStatusProperty() {
    return invalidityStatus;
  }

  public int getInvalidityCategory() {
    return invalidityCategory;
  }

  public int getInvalidityPercentage() {
    return invalidityPercentage;
  }

  public String getInjuryCause() {
    return injuryCause.get();
  }

  public SimpleStringProperty injuryCauseProperty() {
    return injuryCause;
  }

  public List<Injury> getInjuries() {
    return injuries;
  }

  public int getMemberID() {
    return memberID;
  }

  @Override
  public String toString() {
    return "Member{" +
        "cityProvince=" + cityProvince +
        ", street='" + street + '\'' +
        ", homeNumber='" + homeNumber + '\'' +
        ", householdMembers=" + householdMembers +
        ", sex='" + sex + '\'' +
        ", note='" + note + '\'' +
        ", memberID=" + memberID +
        ", name='" + name + '\'' +
        ", surname='" + surname + '\'' +
        ", ssn='" + ssn + '\'' +
        ", birthDate='" + birthDate + '\'' +
        ", deathDate='" + deathDate + '\'' +
        ", city=" + city +
        ", phoneNumber='" + phoneNumber + '\'' +
        ", phoneNumber2='" + phoneNumber2 + '\'' +
        ", employmentStatus=" + employmentStatus +
        ", educationLevel=" + educationLevel +
        ", profession=" + profession +
        ", residence=" + residence +
        ", maritialStatus='" + maritialStatus + '\'' +
        ", invalidityStatus=" + invalidityStatus +
        ", invalidityCategory=" + invalidityCategory +
        ", invalidityPercentage=" + invalidityPercentage +
        ", injuryCause=" + injuryCause +
        ", injuries=" + injuries +
        '}';
  }

  public String getName() {
    return name.get();
  }

  public String getSurname() {
    return surname.get();
  }

  public boolean hasMatch(Map<String, List<String>> parameters) {

    //todo think about contains/starts with/ends with
    if (parameters.get("name") != null) {
      if (!parameters.get("name").contains(getName())) {
        return false;
      }
    }

    if (parameters.get("surname") != null) {

      if (!parameters.get("surname").contains(getSurname())) {
        return false;
      }
    }

    if (parameters.get("birth_date") != null) {
      //TODO think how to handle dates (less than, more than, equal or between)
    }

    if (parameters.get("death_date") != null) {
      //TODO check same as above
    }

    // TODO think about invalidity percentage

    if (parameters.get("phone_number") != null) {
      if (!parameters.get("phone_number").contains(getPhoneNumber())) {
        return false;
      }
    }

    if (parameters.get("phone_number_2") != null) {
      if (!parameters.get("phone_number").contains(getPhoneNumber2())) {
        return false;
      }
    }

    if (parameters.get("city") != null) {
      if (!parameters.get("city").contains(getCity())) {
        return false;
      }
    }

    if (parameters.get("city_province") != null) {
      if (!parameters.get("city_province").contains(getCityProvince())) {
        return false;
      }
    }

    if (parameters.get("street_name") != null) {
      if (!parameters.get("street_name").contains(getStreet())) {
        return false;
      }
    }

    if (parameters.get("home_number") != null) {
      if (!parameters.get("home_number").contains(getHomeNumber())) {
        return false;
      }
    }

    if (parameters.get("household_members") != null) {
      if (!parameters.get("household_members").contains(getHouseholdMembers())) {
        return false;
      }
    }

    if (parameters.get("education_level") != null) {

      if (!parameters.get("education_level").contains(getEducationLevel())) {
        return false;
      }
    }

    if (parameters.get("profession") != null) {

      if (!parameters.get("profession").contains(getProfession())) {
        return false;
      }
    }

    if (parameters.get("employment_status") != null) {
      if (!parameters.get("employment_status").contains(getEmploymentStatus())) {
        return false;
      }
    }

    if (parameters.get("injury_cause") != null) {
      if (parameters.get("injury_cause").contains(getInjuryCause())) {
        return false;
      }
    }

    if (parameters.get("sex") != null) {
      if (!parameters.get("sex").contains(getSex())) {
        return false;
      }
    }

    if (parameters.get("invalidity_status") != null) {
      if (parameters.get("invalidity_status").contains(getInvalidityStatus())) {
        return false;
      }
    }

    if (parameters.get("residence") != null) {
      if (!parameters.get("residence").contains(getResidence())) {
        return false;
      }
    }

    if (parameters.get("injury") != null) {

      List<String> paramInjuries = parameters.get("injury");
      List<String> memberInjuries = injuries.stream().map(Injury::getInjury)
          .collect(Collectors.toList());

      return !Collections.disjoint(paramInjuries, memberInjuries);
    }

    return true;
  }

  public static class Builder{
    private SimpleStringProperty name;
    private SimpleStringProperty surname;
    private SimpleStringProperty ssn;
    private SimpleStringProperty cityProvince;
    private SimpleStringProperty street;
    private SimpleStringProperty homeNumber;
    private SimpleStringProperty householdMembers;
    private SimpleStringProperty sex;
    private SimpleStringProperty note;
    private int memberID;
    private SimpleStringProperty birthDate;
    private SimpleStringProperty deathDate;
    private SimpleStringProperty city;
    private SimpleStringProperty phoneNumber;
    private SimpleStringProperty phoneNumber2;
    private SimpleStringProperty employmentStatus;
    private SimpleStringProperty educationLevel;
    private SimpleStringProperty profession;
    private SimpleStringProperty residence;
    private SimpleStringProperty maritialStatus;
    private SimpleStringProperty invalidityStatus;
    private int invalidityCategory;
    private int invalidityPercentage;
    private SimpleStringProperty injuryCause;
    private List<Injury> injuries;

    public Builder(String name, String surname){
      this.name = new SimpleStringProperty(name);
      this.surname = new SimpleStringProperty(surname);
    }

    public Builder withSsn(String ssn) {
      this.ssn = new SimpleStringProperty(ssn);
      return this;
    }

    public Builder inCityProvince(String cityProvince) {
      this.cityProvince = new SimpleStringProperty(cityProvince);
      return this;
    }

    public Builder atStreet(String street) {
      this.street = new SimpleStringProperty(street);
      return this;
    }

    public Builder withHomeNumber(String homeNumber) {
      this.homeNumber = new SimpleStringProperty(homeNumber);
      return this;
    }
    public Builder withHousholdMembers(String householdMembers) {
      this.householdMembers = new SimpleStringProperty(householdMembers);
      return this;
    }

    public Builder ofSex(String sex) {
      this.sex = new SimpleStringProperty(sex);
      return this;
    }

    public Builder withNote(String note) {
      this.note = new SimpleStringProperty(note);
      return this;
    }

    public Builder withId(int id) {
      this.memberID = id;
      return this;
    }

    public Builder withBirthDate(String birthDate) {
      this.birthDate = new SimpleStringProperty(birthDate);
      return this;
    }

    public Builder withDeathDate(String deathDate) {
      this.deathDate = new SimpleStringProperty(deathDate);
      return this;
    }

    public Builder inCity(String city) {
      this.city = new SimpleStringProperty(city);
      return this;
    }

    public Builder withPhoneNumber(String phoneNumber) {
      this.phoneNumber = new SimpleStringProperty(phoneNumber);
      return this;
    }

    public Builder withPhoneNumber2(String phoneNumber2) {
      this.phoneNumber2 = new SimpleStringProperty(phoneNumber2);
      return this;
    }

    public Builder withEmploymentStatus(String employmentStatus){
      this.employmentStatus = new SimpleStringProperty(employmentStatus);
      return this;
    }
    public Builder withEducationLevel(String educationLevel){
      this.educationLevel = new SimpleStringProperty(educationLevel);
      return this;
    }

    public Builder withProfession(String profession){
      this.profession = new SimpleStringProperty(profession);
      return this;
    }

    public Builder withResidence(String residence){
      this.residence = new SimpleStringProperty(residence);
      return this;
    }

    public Builder withMaritialStatus(String maritialStatus){
      this.maritialStatus = new SimpleStringProperty(maritialStatus);
      return this;
    }

    public Builder withInvalidityStatus(String invalidityStatus){
      this.invalidityStatus = new SimpleStringProperty(invalidityStatus);
      return this;
    }

    public Builder withInvalidityCategory(int invalidityCategory){
      this.invalidityCategory = invalidityCategory;
      return this;
    }

    public Builder withInvalidityPercentage(int invalidityPercentage){
      this.invalidityPercentage = invalidityPercentage;
      return this;
    }

    public Builder withInjuryCause(String injuryCause){
      this.injuryCause = new SimpleStringProperty(injuryCause);
      return this;
    }

    public Builder withInjuries(List<Injury> injuries){
      this.injuries = injuries;
      return this;
    }

    public Member build(){
      return new Member(this);
    }
  }
  //TODO override equals(), hashCode() and toString(), provide getters/setters when class is finished

}
