package com.ladislav.model.member;

import java.util.List;
import javafx.beans.property.SimpleStringProperty;

// TODO Builder pattern in member class?

public class Member {

  private SimpleStringProperty name;
  private SimpleStringProperty surname;
  private SimpleStringProperty identityNumber;

  private SimpleStringProperty cityProvince;
  private SimpleStringProperty street;
  private SimpleStringProperty homeNumber;
  private SimpleStringProperty peopleInHousehold;
  private SimpleStringProperty sex;
  private SimpleStringProperty comments;
  private int memberID;

  //TODO use date objects ?!?!
  private SimpleStringProperty birthDate;
  private SimpleStringProperty deathDate;

  public String getCity() {
    return city.get();
  }

  public SimpleStringProperty cityProperty() {
    return city;
  }

  private SimpleStringProperty city;

  private SimpleStringProperty phoneNumber;
  private SimpleStringProperty phoneNumber2;

  public SimpleStringProperty nameProperty() {
    return name;
  }

  public SimpleStringProperty surnameProperty() {
    return surname;
  }

  public String getIdentityNumber() {
    return identityNumber.get();
  }

  public SimpleStringProperty identityNumberProperty() {
    return identityNumber;
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

  public SimpleStringProperty getPeopleInHousehold() {
    return peopleInHousehold;
  }

  public String getSex() {
    return sex.get();
  }

  public SimpleStringProperty sexProperty() {
    return sex;
  }

  public String getComments() {
    return comments.get();
  }

  public SimpleStringProperty commentsProperty() {
    return comments;
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

  public String getWorkStatus() {
    return workStatus.get();
  }

  public SimpleStringProperty workStatusProperty() {
    return workStatus;
  }

  public String getEducationLevel() {
    return educationLevel.get();
  }

  public SimpleStringProperty educationLevelProperty() {
    return educationLevel;
  }

  public String getProffesion() {
    return proffesion.get();
  }

  public SimpleStringProperty proffesionProperty() {
    return proffesion;
  }

  public String getHousingQuestion() {
    return housingQuestion.get();
  }

  public SimpleStringProperty housingQuestionProperty() {
    return housingQuestion;
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

  public int getInvalidityLevel() {
    return invalidityLevel;
  }

  public int getInvalidityDegree() {
    return invalidityDegree;
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

  private SimpleStringProperty workStatus;
  private SimpleStringProperty educationLevel;
  private SimpleStringProperty proffesion;

  private SimpleStringProperty housingQuestion;

  private SimpleStringProperty maritialStatus;

  private SimpleStringProperty invalidityStatus;

  //TODO add new table to database and implement it in here and in Access object ! (Ladislav)
  private int invalidityLevel;
  private int invalidityDegree;

  private SimpleStringProperty injuryCause;
  private List<Injury> injuries;

  public Member(int memberID, String name, String surname,
      String identityNumber, String dateOfBirth, String phoneNumber1, String phoneNumber2,
      String city, String cityProvince, String street, String homeNumber, String peopleInHousehold,
      String dateOfDeath, String educationLevel, String profession,
      String workStatus, String injuryCause, String invalidityStatus,
      String housingQuestion, String sex, String comments, List<Injury> injuries) {

    this.memberID = memberID;
    this.name = new SimpleStringProperty(name);
    this.surname = new SimpleStringProperty(surname);
    this.identityNumber = new SimpleStringProperty(identityNumber);
    this.birthDate = new SimpleStringProperty(dateOfBirth);
    this.phoneNumber = new SimpleStringProperty(phoneNumber1);
    this.phoneNumber2 = new SimpleStringProperty(phoneNumber2);
    this.city = new SimpleStringProperty(city);
    this.cityProvince = new SimpleStringProperty(cityProvince);
    this.street = new SimpleStringProperty(street);
    this.homeNumber = new SimpleStringProperty(homeNumber);
    this.peopleInHousehold = new SimpleStringProperty(peopleInHousehold);
    this.deathDate = new SimpleStringProperty(dateOfDeath);
    this.educationLevel = new SimpleStringProperty(educationLevel);
    this.proffesion = new SimpleStringProperty(profession);
    this.workStatus = new SimpleStringProperty(workStatus);
    this.injuryCause = new SimpleStringProperty(injuryCause);
    this.invalidityStatus = new SimpleStringProperty(invalidityStatus);
    this.housingQuestion = new SimpleStringProperty(housingQuestion);
    this.sex = new SimpleStringProperty(sex);
    this.comments = new SimpleStringProperty(comments);
    this.injuries = injuries;
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
        ", peopleInHousehold=" + peopleInHousehold +
        ", sex='" + sex + '\'' +
        ", comments='" + comments + '\'' +
        ", memberID=" + memberID +
        ", name='" + name + '\'' +
        ", surname='" + surname + '\'' +
        ", identityNumber='" + identityNumber + '\'' +
        ", birthDate='" + birthDate + '\'' +
        ", deathDate='" + deathDate + '\'' +
        ", city=" + city +
        ", phoneNumber='" + phoneNumber + '\'' +
        ", phoneNumber2='" + phoneNumber2 + '\'' +
        ", workStatus=" + workStatus +
        ", educationLevel=" + educationLevel +
        ", proffesion=" + proffesion +
        ", housingQuestion=" + housingQuestion +
        ", maritialStatus='" + maritialStatus + '\'' +
        ", invalidityStatus=" + invalidityStatus +
        ", invalidityLevel=" + invalidityLevel +
        ", invalidityDegree=" + invalidityDegree +
        ", injuryCause=" + injuryCause +
        ", injuries=" + injuries +
        '}';
  }

  //TODO override equals(), hashCode() and toString(), provide getters/setters when class is finished

}
