package com.NonContact.dto;

import com.NonContact.service.MemberService;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member extends EntityDto{
	private int id;
	private String regDate;
	private String updateDate;
	private String loginId;
	@JsonIgnore
	private String loginPw;
	@JsonIgnore
	private int authLevel;
	@JsonIgnore
	private String authKey;
	private String name;
	private String nickname;
	private String cellphoneNum;
	private String email;
	private String extra__thumbImg;
	
	
	public String getAuthLevelName() {
		return MemberService.getAuthLevelName(this);
	}
	
	public String getAuthLevelNameColor() {
		return MemberService.getAuthLevelNameColor(this);
	}
	
	public String getMemberThumbImgUrl() {
		return "/common/genFile/file/member/" + id + "/common/attachment/1";
	}
}