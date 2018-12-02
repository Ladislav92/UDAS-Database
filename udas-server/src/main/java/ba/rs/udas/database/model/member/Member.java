package ba.rs.udas.database.model.member;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Member {

  private int memberID;

  private String name;
  private String surname;
  private String ssn;
  private String cityProvince;
  private String street;
  private String homeNumber;
  private String householdMembers;
  private String sex;
  private String note;
  private String birthDate; //TODO use date object ?!?!
  private String deathDate; //TODO use date object ?!?!
  private String city;
  private String phoneNumber;
  private String phoneNumber2;
  private String employmentStatus;
  private String educationLevel;
  private String profession;
  private String residence;
  private String maritialStatus;
  private String invalidityStatus;
  private int invalidityCategory;
  private int invalidityPercentage;
  private String injuryCause;
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
    this.name = name;
    this.surname = surname;
    this.ssn = SSN;
    this.birthDate = dateOfBirth;
    this.phoneNumber = phoneNumber1;
    this.phoneNumber2 = phoneNumber2;
    this.city = city;
    this.cityProvince = cityProvince;
    this.street = street;
    this.homeNumber = homeNumber;
    this.householdMembers = peopleInHousehold;
    this.deathDate = dateOfDeath;
    this.educationLevel = educationLevel;
    this.profession = profession;
    this.employmentStatus = workStatus;
    this.injuryCause = injuryCause;
    this.invalidityStatus = invalidityStatus;
    this.residence = housingQuestion;
    this.sex = sex;
    this.note = note;
    this.injuries = injuries;
  }

  public String getCity() {
    return city;
  }

  public String getSsn() {
    return ssn;
  }

  public String getCityProvince() {
    return cityProvince;
  }

  public String getStreet() {
    return street;
  }

  public String getHomeNumber() {
    return homeNumber;
  }

  public String getHouseholdMembers() {
    return householdMembers;
  }

  public String getSex() {
    return sex;
  }

  public String getNote() {
    return note;
  }

  public String getBirthDate() {
    return birthDate;
  }

  public String getDeathDate() {
    return deathDate;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public String getPhoneNumber2() {
    return phoneNumber2;
  }

  public String getEmploymentStatus() {
    return employmentStatus;
  }

  public String getEducationLevel() {
    return educationLevel;
  }

  public String getProfession() {
    return profession;
  }

  public String getResidence() {
    return residence;
  }

  public String getMaritialStatus() {
    return maritialStatus;
  }

  public String getInvalidityStatus() {
    return invalidityStatus;
  }
  public int getInvalidityCategory() {
    return invalidityCategory;
  }

  public int getInvalidityPercentage() {
    return invalidityPercentage;
  }

  public String getInjuryCause() {
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
    return name;
  }

  public String getSurname() {
    return surname;
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
    private String name;
    private String surname;
    private String ssn;
    private String cityProvince;
    private String street;
    private String homeNumber;
    private String householdMembers;
    private String sex;
    private String note;
    private int memberID;
    private String birthDate;
    private String deathDate;
    private String city;
    private String phoneNumber;
    private String phoneNumber2;
    private String employmentStatus;
    private String educationLevel;
    private String profession;
    private String residence;
    private String maritialStatus;
    private String invalidityStatus;
    private int invalidityCategory;
    private int invalidityPercentage;
    private String injuryCause;
    private List<Injury> injuries;

    public Builder(String name, String surname){
      this.name = new String(name);
      this.surname = new String(surname);
    }

    public Builder withSsn(String ssn) {
      this.ssn = ssn;
      return this;
    }

    public Builder inCityProvince(String cityProvince) {
      this.cityProvince = cityProvince;
      return this;
    }

    public Builder atStreet(String street) {
      this.street = street;
      return this;
    }

    public Builder withHomeNumber(String homeNumber) {
      this.homeNumber = homeNumber;
      return this;
    }
    public Builder withHousholdMembers(String householdMembers) {
      this.householdMembers = householdMembers;
      return this;
    }

    public Builder ofSex(String sex) {
      this.sex = sex;
      return this;
    }

    public Builder withNote(String note) {
      this.note = note;
      return this;
    }

    public Builder withId(int id) {
      this.memberID = id;
      return this;
    }

    public Builder withBirthDate(String birthDate) {
      this.birthDate = birthDate;
      return this;
    }

    public Builder withDeathDate(String deathDate) {
      this.deathDate = deathDate;
      return this;
    }

    public Builder inCity(String city) {
      this.city = city;
      return this;
    }

    public Builder withPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
      return this;
    }

    public Builder withPhoneNumber2(String phoneNumber2) {
      this.phoneNumber2 = phoneNumber2;
      return this;
    }

    public Builder withEmploymentStatus(String employmentStatus){
      this.employmentStatus = employmentStatus;
      return this;
    }
    public Builder withEducationLevel(String educationLevel){
      this.educationLevel = educationLevel;
      return this;
    }

    public Builder withProfession(String profession){
      this.profession = profession;
      return this;
    }

    public Builder withResidence(String residence){
      this.residence = residence;
      return this;
    }

    public Builder withMaritialStatus(String maritialStatus){
      this.maritialStatus = maritialStatus;
      return this;
    }

    public Builder withInvalidityStatus(String invalidityStatus){
      this.invalidityStatus = invalidityStatus;
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
      this.injuryCause = injuryCause;
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
