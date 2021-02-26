package com.NonContact.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.NonContact.dto.Member;

@Mapper
public interface MemberDao {
	public void join(Map<String, Object> param);
	
	public Member getMemberByLoginId(@Param("loginId") String loginId);
}
