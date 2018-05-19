package com.ladislav.model.data;

import com.ladislav.model.member.Injury;
import com.ladislav.model.member.Member;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Frankensteins monster to access the data from DB. Probably should be extracted to
 * server side app and act as web client.
 *
 * @author Ladislav
 */
public class MySqlAdapter implements MemberDAO {

  Connection connection;

  public MySqlAdapter(String username, String password) throws SQLException {
    connection = ConnectionFactory.getConnection();
  }

  /**
   * Inserts member received in parameter into database by executing query.
   *
   * @param member to be inserted
   * @return True or false if member exists or insertion failed.
   * @throws SQLException
   */
  @Override
  public boolean addMember(Member member) {
    throw new NotImplementedException();
  }

  /**
   *
   * Removes member from database based on membersID.
   *
   * @param member to be removed
   * @return true or false if member does not exist or removal was unsuccessful.
   * @throws SQLException
   */
  @Override
  public boolean deleteMember(Member member) throws SQLException {
    ResultSet resultSet = executeQuery("DELETE FROM clan where id_clan = " + member.getMemberID());
    return resultSet.next();
  }

  /**
   * Changes certain data on existing member.
   *
   *
   * @param member member object carrying changes
   * @return False if member does not exist or change did not succeed.
   */
  @Override
  public boolean updateMember(Member member) {
    throw new NotImplementedException();
  }


  /**
   * Returns list of member object based on query.
   *
   * Search parameters should contain attribute name as key and desired value as value in Map provided.
   *
   * @param searchParameters Map
   * @return
   */
  @Override
  public List<Member> getMembers(Map<String, String> searchParameters) {
    throw new NotImplementedException();
  }

  /**
   *  Returns list that contains all the members that database holds.
   *  If there are no member entries in the DB, empty list is returned.
   * @return List of members.
   * @throws SQLException
   */
  public List<Member> getMembers() throws SQLException {

    String query = " SELECT id_clan, ime, prezime, JMBG, datum_rodjenja, tel1, tel2, mjesto.mjesto,\n"
        + "        mjesna_zajednica.mjesna_zajednica, ulica, broj_stana_kuce, clanovi_domacinstva, datum_smrti,\n"
        + "        stepen_obrazovanja.stepen_obrazovanja, zanimanje.zanimanje, radni_status.radni_status, nacin_povrede.nacin_povrede,\n"
        + "        status_invalidnosti.status_invalidnosti, stambeno_pitanje.stambeno_pitanje, pol, napomena\n"
        + "    FROM clan\n"
        + "    LEFT JOIN mjesto ON mjesto.id_mjesto = clan.id_clan\n"
        + "    LEFT JOIN mjesna_zajednica ON mjesna_zajednica.id_mjesna_zajednica = clan.id_mjesna\n"
        + "    LEFT JOIN stepen_obrazovanja ON stepen_obrazovanja.id_stepen_obrazovanja = clan.id_stepen_obr\n"
        + "    LEFT JOIN zanimanje ON zanimanje.id_zanimanje = clan.id_zanimanje\n"
        + "    LEFT JOIN radni_status ON radni_status.id_radni_status = clan.id_radni_status\n"
        + "    LEFT JOIN nacin_povrede ON nacin_povrede.nacin_povrede = clan.id_nacin_povrd\n"
        + "    LEFT JOIN status_invalidnosti ON status_invalidnosti.id_status_invalidnosti = clan.id_status_invalidnosti\n"
        + "    LEFT JOIN stambeno_pitanje ON stambeno_pitanje.id_stambeno_pitanje = clan.id_stambeno_pitanje;";

    ResultSet resultSet = executeQuery(query);
    List<Member> members = new ArrayList<>();
    while (resultSet.next()) {

      int clanID = resultSet.getInt("id_clan");
      String ime = resultSet.getString("ime");
      String prezime = resultSet.getString("prezime");
      String JMBG = resultSet.getString("JMBG");
      String datum_rodjenja = resultSet.getString("datum_rodjenja");
      String tel = resultSet.getString("tel1");
      String tel2 = resultSet.getString("tel2");
      String mjesto = resultSet.getString("mjesto");
      String mjesna_zajednica = resultSet.getString("mjesna_zajednica");
      String ulica = resultSet.getString("ulica");
      String broj_stana_kuce = resultSet.getString("broj_stana_kuce");
      int clanoviDom = resultSet.getInt("clanovi_domacinstva");
      String datum_smrti = resultSet.getString("datum_smrti");
      String stepen_obrazovanja = resultSet.getString("stepen_obrazovanja");
      String zanimanje = resultSet.getString("zanimanje");
      String radni_status = resultSet.getString("radni_status");
      String nacin_povr = resultSet.getString("nacin_povrede");
      String status_inv = resultSet.getString("status_invalidnosti");
      String stambeno_pitanje = resultSet.getString("stambeno_pitanje");
      String pol = resultSet.getString("pol");
      String napomena = resultSet.getString("napomena");

      List<Injury> povrede = getInjuries(clanID);

          members.add(
              new Member(clanID, ime, prezime, JMBG, datum_rodjenja, tel, tel2, mjesto, mjesna_zajednica,
                  ulica, broj_stana_kuce, clanoviDom, datum_smrti, stepen_obrazovanja, zanimanje, radni_status,
                  nacin_povr,
                  status_inv, stambeno_pitanje, pol, napomena, povrede));
    }

    return members;
  }

  /**
   * Helper method for getMembers. It exists to collect all the injuries member has.
   * It's implemented separately because Injury table has Many-To-Many relationship.
   * @param id - members ID
   * @return List that contains Injury objects
   * @throws SQLException
   */
  private List<Injury> getInjuries(int id) throws SQLException {

    List<Injury> injuries = new ArrayList<>();

    ResultSet resultSet = executeQuery("\n"
        + "SELECT mjesto_povrede.mjesto_povrede, amputacija\n"
        + "FROM povreda\n"
        + "LEFT JOIN mjesto_povrede ON mjesto_povrede.id_mjesto_povrede = povreda.id_mjesto_povrede\n"
        + "WHERE id_clan =" + id);

    while (resultSet.next()) {

      String povreda = resultSet.getString("mjesto_povrede");
      boolean amputacija = resultSet.getBoolean("amputacija");

      injuries.add(new Injury(povreda, amputacija));

    }

    return injuries;
  }

  /**
   * Returns all cities database holds. It's used to populate comboboxes for
   * inserting and changing members. User is not allowed to add new cities trough add/change member
   * directly.
   *
   * @return List or empty list.
   */
  @Override
  public List<String> getCities() {
    throw new NotImplementedException();
  }

  /**
   * Returns all city provinces database holds. It's used to populate comboboxes for
   * inserting and changing members. User is not allowed to change city entries trough add/change member
   * directly.
   *
   * @return List or empty list.
   */
  @Override
  public List<String> getProvinces() {
    throw new NotImplementedException();
  }

  /**
   * Returns all education levels database holds. It's used to populate comboboxes for
   * inserting and changing members. User is not allowed to make changes on this table trough add/change member
   * directly.
   *
   * @return List or empty list.
   */
  @Override
  public List<String> getEducationLevels() {
    throw new NotImplementedException();
  }

  /**
   * Returns all professions database holds. It's used to populate comboboxes for
   * inserting and changing members. User is not allowed to make changes on this table trough add/change member
   * directly.
   *
   * @return List or empty list.
   */
  @Override
  public List<String> getProfessions() {
    throw new NotImplementedException();
  }

  /**
   * Returns all invalidity statuses database holds. It's used to populate comboboxes for
   * inserting and changing members. User is not allowed to make changes on this table trough add/change
   * dialogs member directly.
   *
   * @return List or empty list.
   */
  @Override
 public List<String> getInvalidityStatuses() {
    throw new NotImplementedException();
  }

  /**
   * Returns all invalidity categories database holds. It's used to populate comboboxes for
   * inserting and changing members. User is not allowed to make changes on this table trough add/change
   * dialogs member directly.
   *
   * @return List or empty list.
   */
  @Override
  public List<String> getInvalidityCategories() {
    throw new NotImplementedException();
  }

  /**
   * Returns all invalidity percentage values that database holds. It's used to populate comboboxes for
   * inserting and changing members. User is not allowed to make changes on this table trough add/change
   * dialogs member directly.
   *
   * @return List or empty list.
   */
  @Override
  public List<String> getInvalidityPercentage() {
    throw new NotImplementedException();
  }

  /**
   * Returns all Employment status values that database holds. It's used to populate comboboxes for
   * inserting and changing members. User is not allowed to make changes on this table trough add/change
   * dialogs member directly.
   *
   * @return List or empty list.
   */
  @Override
  public List<String> getEmploymentStatuses() {
    throw new NotImplementedException();
  }

  /**
   * Returns all injury causes values that database holds. It's used to populate comboboxes for
   * inserting and changing members. User is not allowed to make changes on this table trough add/change
   * dialogs member directly.
   *
   * @return List or empty list.
   */
  @Override
  public List<String> getInjuryCauses() {
    throw new NotImplementedException();
  }

  /**
   * Returns all injury type values that database holds. It's used to populate comboboxes for
   * inserting and changing members. User is not allowed to make changes on this table trough add/change
   * dialogs member directly.
   *
   * @return List or empty list.
   */
  @Override
  public List<String> getInjuryTypes() {
    throw new NotImplementedException();
  }

  private ResultSet executeQuery(String query) throws SQLException {
    Statement statement = connection.createStatement();
    return statement.executeQuery(query);

  }

  /**
   *
   * For testing purpose
   *
   * */
  public static void main(String[] args) throws SQLException {

    MySqlAdapter access = new MySqlAdapter("Lado", "lado");
    access.getMembers().forEach(System.out::println);

  }
}

