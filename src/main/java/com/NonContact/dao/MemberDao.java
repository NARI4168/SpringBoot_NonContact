package com.NonContact.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.NonContact.dto.Member;

@Mapper
public interface MemberDao {
	void join(Map<String, Object> param);
	
	Member getMemberByLoginId(@Param("loginId") String loginId);

	void modifyMember(Map<String, Object> param);

	Member getMember(@Param("id") int id);

	Member getMemberByAuthKey(@Param("authKey") String authKey);

	List<Member> getForPrintMembers(Map<String, Object> param);

	Member getMemberByAuthLevel(int authLevel);
}
