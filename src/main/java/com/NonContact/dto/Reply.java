package com.NonContact.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reply extends EntityDto{
	private int id;
	private int relId;
	private String regDate;
	private String updateDate;
	private String relTypeCode;
	private String body;
	private int memberId;
	
	private String extra__writer;
}
