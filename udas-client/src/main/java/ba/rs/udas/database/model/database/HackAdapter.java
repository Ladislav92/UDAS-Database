package ba.rs.udas.database.model.database;

import ba.rs.udas.database.model.member.Injury;
import ba.rs.udas.database.model.member.Member;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class HackAdapter implements DataAdapter {

  static List<Member> members = new ArrayList<>();

  static {
    members.add(
        new Member(
            1,
            "Rade",
            "Kornjaca",
            "1234567890098",
            "10-10-1956",
            "051226883",
            null,
            "Prijedor",
            "Urije",
            "Branka Popovića",
            "23A",
            "3",
            null,
            "KV",
            "Stolar",
            "Zaposlen",
            "NUS",
            "RVI",
            "Riješeno",
            "M",
            "Od ranije poznat policiji.",
            new ArrayList<>(Collections.singletonList(new Injury(1, "Potkoljenica", true)))));

    members.add(
        new Member(
            2,
            "Petar",
            "Petrović",
            "2233111234567",
            "01-02-1954",
            "051888999",
            null,
            "Banja Luka",
            "Centar 1",
            "Gundulićeva",
            "17",
            "5",
            null,
            "KV",
            "Stolar",
            "Nezaposlen",
            "Mina",
            "RVI",
            "Sopstveni objekat",
            "M",
            "Od oca Mitra.",
            new ArrayList<>(Collections.singletonList(new Injury(2, "Nadlaktica", true)))
        ));


    members.add(new Member.Builder("Miljenko", "Popović").build());
  }

  @Override
  public List<Member> getMembers() {
    return members;
  }

  @Override
  public boolean addMember(Member member) {

    if (members.contains(member)) {
      return false;
    }

    members.add(member);
    return true;
  }

  @Override
  public boolean deleteMember(Member member) {
    if (!members.contains(member)) {
      return false;
    }

    members.remove(member);
    return true;
  }

  @Override
  public boolean updateMember(int id, Member other) throws SQLException {

    Optional<Member> member = getMembers().stream().filter(m -> m.getMemberID() == id).findAny();

    if (!member.isPresent()) {
      return false;
    }

    members.remove(member.get());
    members.add(other);
    return true;
  }

  @Override
  public List<String> getCities() {
    throw null;
  }

  @Override
  public List<String> getProvinces() {
    return Arrays.asList("Urije", "Centar 1");
  }

  @Override
  public List<String> getEducationLevels() {
    return Arrays.asList("NK", "VK", "KV", "VKV", "SSS");
  }

  @Override
  public List<String> getProfessions() {
    return Arrays.asList("Profesor", "Stolar", "Automehaničar", "Ljekar", "Nastavnik");
  }

  @Override
  public List<String> getInvalidityStatuses() {
    return Arrays.asList("RVI", "CŽR", "Nepoznato");
  }

  @Override
  public List<String> getResidences() {
    return Arrays.asList("Riješeno", "Podstanar", "Nepoznato");
  }

  @Override
  public List<String> getInvalidityCategories() {
    return Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
  }

  @Override
  public List<String> getInvalidityPercentages() {
    return Arrays.asList("10", "20", "30", "40", "50", "60", "70", "80", "90", "100");
  }

  @Override
  public List<String> getEmploymentStatuses() {
    return Arrays.asList("Zaposlen", "Nezaposlen", "Školuje se", "Penzioner");
  }

  @Override
  public List<String> getInjuryCauses() {
    return Arrays.asList("Mina", "NUS", "Granata", "Metak", "Nepoznato");
  }

  @Override
  public List<String> getInjuryLocations() {
    return Arrays
        .asList("Natkoljenica", "Potkoljenica", "Oko", "Uho", "Nadlaktica", "Podlaktica", "Šaka",
            "Prsti", "Stopalo");
  }

  @Override
  public List<Member> getMembers(final Map<String, List<String>> searchParameters)
      throws SQLException {

    return getMembers().stream()
                       .filter(m -> m.hasMatch(searchParameters))
                       .collect(Collectors.toList());

  }

  @Override
  public int addCity(String city) throws SQLException {
    return 0;
  }

  @Override
  public int addCityProvince(String cityProvince, String city) throws SQLException {
    return 0;
  }

  @Override
  public int addEducationLevel(String educationLevel) throws SQLException {
    return 0;
  }

  @Override
  public int addEmploymentStatus(String employmentStatus) throws SQLException {
    return 0;
  }

  @Override
  public int addInjuryCause(String injuryCause) throws SQLException {
    return 0;
  }

  @Override
  public int addInjuryLocation(String injuryLocation) throws SQLException {
    return 0;
  }

  @Override
  public int addInvalidityRanking(int invalidityCategory, int invalidityPercentage) throws SQLException {
    return 0;
  }

  @Override
  public int addInvalidityStatus(String invalidityStatus) throws SQLException {
    return 0;
  }

  @Override
  public int addProfession(String profession) throws SQLException {
    return 0;
  }

  @Override
  public int addResidence(String residence) throws SQLException {
    return 0;
  }

  public static void main(String[] args) {
    List<Member> members = new ArrayList<>();

    for(int i = 0; i < 10000; i++) {
      members.add(
          new Member(
              1000 - i,
              1000 - i + "Petar",
              "Petrović",
              "2233111234567",
              "01-02-1954",
              "051888999",
              null,
              "Banja Luka",
              "Centar 1",
              "Gundulićeva",
              "17",
              "5",
              null,
              "KV",
              "Stolar",
              "Nezaposlen",
              "Mina",
              "RVI",
              "Sopstveni objekat",
              "M",
              "Od oca Mitra.",
              new ArrayList<>(Collections.singletonList(new Injury(2, "Nadlaktica", true)))
          ));
    }

    ObservableList<Member> observableList = FXCollections.observableArrayList(members);

    long timeToSort = System.currentTimeMillis();
    observableList.sort(Comparator.comparing(Member::getName));
    long ended = System.currentTimeMillis();

    System.out.println("Time spent sorting 10k members by name:" + (ended - timeToSort));
  }
}