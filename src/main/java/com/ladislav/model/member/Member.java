package com.ladislav.model.member;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleStringProperty;

// TODO Builder pattern in member class?

public class Member {

  private SimpleStringProperty name;
  private SimpleStringProperty surname;
  private SimpleStringProperty ssn;

  private SimpleStringProperty cityProvince;
  private SimpleStringProperty street;
  private SimpleStringProperty homeNumber;
  private SimpleStringProperty householdMembers;
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

  public String getProffesion() {
    return proffesion.get();
  }

  public SimpleStringProperty proffesionProperty() {
    return proffesion;
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

  private SimpleStringProperty employmentStatus;
  private SimpleStringProperty educationLevel;
  private SimpleStringProperty proffesion;

  private SimpleStringProperty residence;

  private SimpleStringProperty maritialStatus;

  private SimpleStringProperty invalidityStatus;

  //TODO add new table to database and implement it in here and in Access object ! (Ladislav)
  private int invalidityLevel;
  private int invalidityDegree;

  private SimpleStringProperty injuryCause;
  private List<Injury> injuries;

  public Member(int memberID, String name, String surname,
      String SSN, String dateOfBirth, String phoneNumber1, String phoneNumber2,
      String city, String cityProvince, String street, String homeNumber, String peopleInHousehold,
      String dateOfDeath, String educationLevel, String profession,
      String workStatus, String injuryCause, String invalidityStatus,
      String housingQuestion, String sex, String comments, List<Injury> injuries) {

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
    this.proffesion = new SimpleStringProperty(profession);
    this.employmentStatus = new SimpleStringProperty(workStatus);
    this.injuryCause = new SimpleStringProperty(injuryCause);
    this.invalidityStatus = new SimpleStringProperty(invalidityStatus);
    this.residence = new SimpleStringProperty(housingQuestion);
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
        ", householdMembers=" + householdMembers +
        ", sex='" + sex + '\'' +
        ", comments='" + comments + '\'' +
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
        ", proffesion=" + proffesion +
        ", residence=" + residence +
        ", maritialStatus='" + maritialStatus + '\'' +
        ", invalidityStatus=" + invalidityStatus +
        ", invalidityLevel=" + invalidityLevel +
        ", invalidityDegree=" + invalidityDegree +
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

      if (!parameters.get("profession").contains(getProffesion())) {
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

      List<String> paramInjuries  = parameters.get("injury");
      List<String> memberInjuries = injuries.stream().map(Injury::getInjury).collect(Collectors.toList());

      if (Collections.disjoint(paramInjuries, memberInjuries)) {
        return false;
      }
    }

    return true;
}


  //TODO override equals(), hashCode() and toString(), provide getters/setters when class is finished

}
