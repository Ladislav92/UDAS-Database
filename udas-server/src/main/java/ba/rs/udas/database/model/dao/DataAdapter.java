package ba.rs.udas.database.model.dao;

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

  int addCity(String city) throws SQLException;

  int addCityProvince(String cityProvince, String city) throws SQLException;

  int addEducationLevel(String educationLevel) throws SQLException;

  int addEmploymentStatus(String employmentStatus) throws SQLException;

  int addInjuryCause(String injuryCause) throws SQLException;

  int addInjuryLocation(String injuryLocation) throws SQLException;

  int addInvalidityRanking(int invalidityCategory, int invalidityPercentage) throws SQLException;

  int addInvalidityStatus(String invalidityStatus) throws SQLException;

  int addProfession(String profession) throws SQLException;

  int addResidence(String residence) throws SQLException;

}
