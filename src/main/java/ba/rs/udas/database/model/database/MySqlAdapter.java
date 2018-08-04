package ba.rs.udas.database.model.database;

import ba.rs.udas.database.model.member.Injury;
import ba.rs.udas.database.model.member.Member;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Frankensteins monster to access the data from DB. Probably should be extracted to server side app and act as web client.
 *
 * @author Ladislav
 */
public class MySqlAdapter implements DataAdapter {

  private Map<String, Integer> cities;
  private Map<String, Integer> cityProvinces;
  private Map<String, Integer> educationalLevels;
  private Map<String, Integer> employmentStatuses;
  private Map<String, Integer> injuryCauses;
  private Map<String, Integer> injuryLocations;
  private Map<String, Integer> invalidityStatuses;
  private Map<String, Integer> invalidityCategories;
  private Map<String, Integer> invalidityPercentages;
  private Map<String, Integer> professions;
  private Map<String, Integer> residences;

  /**
   * Inserts member received in parameter into database by executing query.
   *
   * @param member to be inserted
   * @return True or false if member exists or insertion failed.
   */
  @Override
  public boolean addMember(Member member) throws SQLException {

    String query = "INSERT INTO member (name, surname, ssn, birth_date, phone_number, phone_number_2, city_id, city_province_id,"
        + " street_name, home_number, household_members, death_date,"
        + "education_level_id, profession_id, employment_status_id, injury_cause_id,"
        + "invalidity_status_id, residence_id, sex, note)"

        + "VALUES ('" + member.getName() + "', '" + member.getSurname() + "', '" + member.getSsn() +
        "', '" + member.getBirthDate() + "', '" + member.getPhoneNumber() + "', '" + member.getPhoneNumber2() + "'"
        + ", " + cities.get(member.getCity()) + "," + cityProvinces.get(member.getCityProvince()) + ", "
        + " '" + member.getStreet() + "', '" + member.getHomeNumber() + "', '" + member.getHouseholdMembers() + "'"
        + ", '" + member.getDeathDate() + "', " + educationalLevels.get(member.getEducationLevel()) + ","
        + " " + professions.get(member.getProfession()) + ", " + employmentStatuses.get(member.getEmploymentStatus()) + ","
        + " " + injuryCauses.get(member.getInjuryCause()) + ", " + invalidityStatuses.get(member.getInvalidityStatus()) + ","
        + " " + residences.get(member.getResidence()) + ", '" + member.getSex() + "', '" + member.getNote() + "');";

    return executeUpdate(query) != -1;

  }

  /**
   * Removes member from database based on membersID.
   *
   * @param member to be removed
   * @return true or false if member does not exist or removal was unsuccessful.
   */
  @Override
  public boolean deleteMember(Member member) throws SQLException {
    ResultSet resultSet = executeQuery("DELETE FROM clan where id_clan = " + member.getMemberID());
    return resultSet.next();
  }

  /**
   * Changes certain data on existing member.
   *
   * @param id other member object carrying changes
   * @return False if member does not exist or change did not succeed.
   */
  @Override
  public boolean updateMember(int id, Member other) {
    throw new NotImplementedException();
  }

  /**
   * Returns list of member object based on query.
   * <p>
   * Search parameters should contain attribute name as key and desired value as value in Map provided.
   *
   * @param searchParameters Map
   */
  @Override
  public List<Member> getMembers(Map<String, List<String>> searchParameters) throws SQLException {

    return getMembers().stream()
        .filter(m -> m.hasMatch(searchParameters))
        .collect(Collectors.toList());
  }

  /**
   * Returns list that contains all the members that database holds. If there are no member entries in the DB, empty list is
   * returned.
   *
   * @return List of members.
   */
  public List<Member> getMembers() throws SQLException {

    String query =
        " SELECT member.id, name, surname, ssn, birth_date, phone_number, phone_number_2, city.city,\n"
            + "        city_province.city_province, street_name, home_number, household_members, death_date,\n"
            + "        education_level.education_level, profession.profession, employment_status.employment_status, injury_cause.injury_cause,\n"
            + "        invalidity_status.invalidity_status, residence.residence, sex, note\n"
            + "    FROM member\n"
            + "    LEFT JOIN city ON city.id = member.id\n"
            + "    LEFT JOIN city_province ON city_province.id = member.city_province_id\n"
            + "    LEFT JOIN education_level ON education_level.id = member.education_level_id\n"
            + "    LEFT JOIN profession ON profession.id = member.profession_id\n"
            + "    LEFT JOIN employment_status ON employment_status.id = member.employment_status_id\n"
            + "    LEFT JOIN injury_cause ON injury_cause.id = member.injury_cause_id\n"
            + "    LEFT JOIN invalidity_status ON invalidity_status.id = member.invalidity_status_id\n"
            + "    LEFT JOIN residence ON residence.id = member.residence_id;";

    ResultSet resultSet = executeQuery(query);
    List<Member> members = new ArrayList<>();
    while (resultSet.next()) {

      int clanID = resultSet.getInt("id");
      String ime = resultSet.getString("name");
      String prezime = resultSet.getString("surname");
      String JMBG = resultSet.getString("ssn");
      String datum_rodjenja = resultSet.getString("birth_date");
      String tel = resultSet.getString("phone_number");
      String tel2 = resultSet.getString("phone_number_2");
      String mjesto = resultSet.getString("city");
      String mjesna_zajednica = resultSet.getString("city_province");
      String ulica = resultSet.getString("street_name");
      String broj_stana_kuce = resultSet.getString("home_number");
      String clanoviDom = resultSet.getString("household_members");
      String datum_smrti = resultSet.getString("death_date");
      String stepen_obrazovanja = resultSet.getString("education_level");
      String zanimanje = resultSet.getString("profession");
      String radni_status = resultSet.getString("employment_status");
      String nacin_povr = resultSet.getString("injury_cause");
      String status_inv = resultSet.getString("invalidity_status");
      String stambeno_pitanje = resultSet.getString("residence");
      String pol = resultSet.getString("sex");
      String napomena = resultSet.getString("note");

      List<Injury> povrede = getInjuries(clanID);

      members.add(
          new Member(clanID, ime, prezime, JMBG, datum_rodjenja, tel, tel2, mjesto,
              mjesna_zajednica,
              ulica, broj_stana_kuce, clanoviDom, datum_smrti, stepen_obrazovanja, zanimanje,
              radni_status,
              nacin_povr,
              status_inv, stambeno_pitanje, pol, napomena, povrede));
    }

    return members;
  }

  /**
   * Helper method for getMembers. It exists to collect all the injuries member has. It's implemented separately because Injury
   * table has Many-To-Many relationship.
   *
   * @param id - members ID
   * @return List that contains Injury objects
   */
  private List<Injury> getInjuries(int id) throws SQLException {

    List<Injury> injuries = new ArrayList<>();

    ResultSet resultSet = executeQuery("\n"
        + "SELECT injury_location.injury_location, amputation\n"
        + "FROM injury\n"
        + "LEFT JOIN injury_location ON injury_location.id = injury.injury_location_id\n"
        + "WHERE member_id =" + id);

    while (resultSet.next()) {

      String povreda = resultSet.getString("mjesto_povrede");
      boolean amputacija = resultSet.getBoolean("amputacija");
      int injuryID = resultSet.getInt("id");

      injuries.add(new Injury(injuryID, povreda, amputacija));

    }

    return injuries;
  }

  private Map<String, Integer> getKeyValueData(String table) throws SQLException {
    return getKeyValueData(table, table);
  }


  /**
   * Returns all cities database holds. It's used to populate comboboxes for inserting and changing members. User is not allowed
   * to add new cities trough add/change member directly.
   *
   * @return List or empty list.
   */
  @Override
  public List<String> getCities() throws SQLException {
    if (cities == null || cities.isEmpty()) {
      cities = getKeyValueData("city");
    }

    return new ArrayList<>(cities.keySet());
  }

  /**
   * Returns all city provinces database holds. It's used to populate comboboxes for inserting and changing members. User is not
   * allowed to change city entries trough add/change member directly.
   *
   * @return List or empty list.
   */
  @Override
  public List<String> getProvinces() throws SQLException {
    if (cityProvinces == null || cityProvinces.isEmpty()) {
      cityProvinces = getKeyValueData("city_province");
    }

    return new ArrayList<>(cityProvinces.keySet());
  }

  /**
   * Returns all education levels database holds. It's used to populate comboboxes for inserting and changing members. User is not
   * allowed to make changes on this table trough add/change member directly.
   *
   * @return List or empty list.
   */
  @Override
  public List<String> getEducationLevels() throws SQLException {
    if (educationalLevels == null || educationalLevels.isEmpty()) {
      educationalLevels = getKeyValueData("education_level");
    }

    return new ArrayList<>(educationalLevels.keySet());
  }

  /**
   * Returns all professions database holds. It's used to populate comboboxes for inserting and changing members. User is not
   * allowed to make changes on this table trough add/change member directly.
   *
   * @return List or empty list.
   */
  @Override
  public List<String> getProfessions() throws SQLException {
    if (professions == null || professions.isEmpty()) {
      professions = getKeyValueData("profession");
    }

    return new ArrayList<>(professions.keySet());
  }

  /**
   * Returns all invalidity statuses database holds. It's used to populate comboboxes for inserting and changing members. User is
   * not allowed to make changes on this table trough add/change dialogs member directly.
   *
   * @return List or empty list.
   */
  @Override
  public List<String> getInvalidityStatuses() throws SQLException {
    if (invalidityStatuses == null || invalidityStatuses.isEmpty()) {
      invalidityStatuses = getKeyValueData("invalidity_status");
    }

    return new ArrayList<>(invalidityStatuses.keySet());
  }

  /**
   * Returns all invalidity categories database holds. It's used to populate comboboxes for inserting and changing members. User
   * is not allowed to make changes on this table trough add/change dialogs member directly.
   *
   * @return List or empty list.
   */
  @Override
  public List<String> getInvalidityCategories() throws SQLException {
    if (invalidityCategories == null || invalidityCategories.isEmpty()) {
      invalidityCategories = getKeyValueData("invalidity_ranking", "invalidity_category");
    }

    return new ArrayList<>(invalidityCategories.keySet());
  }

  /**
   * Returns all invalidity percentage values that database holds. It's used to populate comboboxes for inserting and changing
   * members. User is not allowed to make changes on this table trough add/change dialogs member directly.
   *
   * @return List or empty list.
   */
  @Override
  public List<String> getInvalidityPercentages() throws SQLException {
    if (invalidityCategories == null || invalidityCategories.isEmpty()) {
      invalidityPercentages = getKeyValueData("invalidity_ranking", "invalidity_percentage");
    }

    return new ArrayList<>(invalidityPercentages.keySet());
  }

  /**
   * Returns all Employment status values that database holds. It's used to populate comboboxes for inserting and changing
   * members. User is not allowed to make changes on this table trough add/change dialogs member directly.
   *
   * @return List or empty list.
   */
  @Override
  public List<String> getEmploymentStatuses() throws SQLException {
    if (employmentStatuses == null || employmentStatuses.isEmpty()) {
      employmentStatuses = getKeyValueData("employment_Status");
    }

    return new ArrayList<>(employmentStatuses.keySet());
  }

  /**
   * Returns all injury causes values that database holds. It's used to populate comboboxes for inserting and changing members.
   * User is not allowed to make changes on this table trough add/change dialogs member directly.
   *
   * @return List or empty list.
   */
  @Override
  public List<String> getInjuryCauses() throws SQLException {
    if (injuryCauses == null || injuryCauses.isEmpty()) {
      injuryCauses = getKeyValueData("injury_cause");
    }

    return new ArrayList<>(injuryCauses.keySet());
  }

  /**
   * Returns all injury type values that database holds. It's used to populate comboboxes for inserting and changing members. User
   * is not allowed to make changes on this table trough add/change dialogs member directly.
   *
   * @return List or empty list.
   */
  @Override
  public List<String> getInjuryLocations() throws SQLException {
    if (injuryLocations == null || injuryLocations.isEmpty()) {
      injuryLocations = getKeyValueData("injury_location");
    }

    return new ArrayList<>(injuryLocations.keySet());
  }

  /**
   * Returns all residence values that database holds. It's used to populate comboboxes for inserting and changing members. User
   * is not allowed to make changes on this table trough add/change member dialogs member directly.
   *
   * @return List or empty list.
   */
  @Override
  public List<String> getResidences() throws SQLException {

    if (residences == null || residences.isEmpty()) {
      residences = getKeyValueData("residence");
    }
    return new ArrayList<>(residences.keySet());
  }

  private ResultSet executeQuery(String query) throws SQLException {
    Connection connection = ConnectionManager.getConnection().orElseThrow(SQLException::new);
    Statement statement = connection.createStatement();

    return statement.executeQuery(query);
  }

  private int executeUpdate(String query) throws SQLException {
    Connection connection = ConnectionManager.getConnection().orElseThrow(SQLException::new);
    Statement statement = connection.createStatement();

    return statement.executeUpdate(query);
  }

  private Map<String, Integer> getKeyValueData(String table, String attribute) throws SQLException {
    String query = "SELECT * FROM " + table;
    ResultSet resultSet = executeQuery(query);

    Map<String, Integer> map = new HashMap<>();

    while (resultSet.next()) {
      int id = resultSet.getInt("id");
      String value = resultSet.getString(attribute);
      map.put(value, id);
    }

    return map;
  }
}

