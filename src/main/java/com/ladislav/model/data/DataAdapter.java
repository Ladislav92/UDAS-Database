package com.ladislav.model.data;

import com.ladislav.model.member.Member;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface DataAdapter {

  List<Member> getMembers() throws SQLException;

  boolean addMember(Member member) throws SQLException;

  boolean deleteMember(Member member) throws SQLException;

  boolean updateMember(int id, Member other) throws SQLException;

  Map<Integer, String> getCities() throws SQLException;

  List<String> getProvinces() throws SQLException;

  List<String> getEducationLevels() throws SQLException;

  List<String> getProfessions() throws SQLException;

  List<String> getInvalidityStatuses() throws SQLException;

  List<String> getInvalidityCategories() throws SQLException;

  List<String> getInvalidityPercentage() throws SQLException;

  List<String> getEmploymentStatuses() throws SQLException;

  List<String> getInjuryCauses() throws SQLException;

  List<String> getInjuryTypes() throws SQLException;

  List<Member> getMembers(Map<String, List<String>> searchParameters) throws SQLException;


}
