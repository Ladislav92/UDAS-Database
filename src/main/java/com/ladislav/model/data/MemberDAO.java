package com.ladislav.model.data;

import com.ladislav.model.member.Member;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface MemberDAO {

  List<Member> getMembers() throws SQLException;

  boolean addMember(Member member);

  boolean deleteMember(Member member) throws SQLException;

  boolean updateMember(Member member);

  List<String> getCities();

  List<String> getProvinces();

  List<String> getEducationLevels();

  List<String> getProfessions();

  List<String> getInvalidityStatuses();

  List<String> getInvalidityCategories();

  List<String> getInvalidityPercentage();

  List<String> getEmploymentStatuses();

  List<String> getInjuryCauses();

  List<String> getInjuryTypes();

  List<Member> getMembers(Map<String, String> searchParameters);



}
