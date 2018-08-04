package ba.rs.udas.database.model.database;

import ba.rs.udas.database.model.member.Member;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface DataAdapter {

  List<Member> getMembers() throws SQLException;

  boolean addMember(Member member) throws SQLException;

  boolean deleteMember(Member member) throws SQLException;

  boolean updateMember(int id, Member other) throws SQLException;

  List<String> getCities() throws SQLException;

  List<String> getProvinces() throws SQLException;

  List<String> getEducationLevels() throws SQLException;

  List<String> getProfessions() throws SQLException;

  List<String> getInvalidityStatuses() throws SQLException;

  List<String> getInvalidityCategories() throws SQLException;

  List<String> getInvalidityPercentages() throws SQLException;

  List<String> getEmploymentStatuses() throws SQLException;

  List<String> getInjuryCauses() throws SQLException;

  List<String> getInjuryLocations() throws SQLException;

  List<String> getResidences() throws SQLException;

  List<Member> getMembers(Map<String, List<String>> searchParameters) throws SQLException;

  int insertCity(String city) throws SQLException;

  int insertCityProvince(String cityProvince, String city) throws SQLException;

  int insertEducationLevel(String educationLevel) throws SQLException;

  int insertEmploymentStatus(String employmentStatus) throws SQLException;

  int insertInjuryCause(String injuryCause) throws SQLException;

  int insertInjuryLocation(String injuryLocation) throws SQLException;

  int insertInvalidityRanking(int invalidityCategory, int invalidityPercentage) throws SQLException;

  int insertInvalidityStatus(String invalidityStatus) throws SQLException;

  int insertProfession(String profession) throws SQLException;

  int insertResidence(String residence) throws SQLException;

}
