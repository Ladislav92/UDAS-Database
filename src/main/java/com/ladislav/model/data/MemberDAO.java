package com.ladislav.model.data;

import com.ladislav.model.member.Member;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface MemberDAO {

  List<Member> getMembers() throws SQLException;

  void createMember(Member member);

  boolean deleteMember(Member member) throws SQLException;

  boolean updateMember(Member member);

  Member getMember(Member member);

  List<String> getCities();

  List<String> getProvinces();

  List<String> getEducationLevels();

  List<String> getProffesions();

  List<String> getInvalidityStatuses();

  List<String> getInvalidityKategories();

  List<String> getInvlaidityPercentage();

  List<String> getWorkStatuses();

  List<String> getInjuryCauses();

  List<Member> getMembers(HashMap<String, String> searchParameters);


}
