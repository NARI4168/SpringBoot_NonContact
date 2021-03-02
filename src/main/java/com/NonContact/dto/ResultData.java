package com.NonContact.dto;

import java.util.Map;

import com.NonContact.util.Util;

import lombok.Data;

@Data //toString, GettersSetters

public class ResultData {
	
	private String resultCode;
	private String msg;
	private Map<String, Object> report;
	
	public ResultData(String resultCode, String msg, Object...args) {
		this.resultCode = resultCode;
		this.msg = msg;
		this.report = Util.mapOf(args);
	}
	
	public boolean isSuccess() {
		return resultCode.startsWith("S-");
	}

	public boolean isFail() {
		return isSuccess() == false;
	}

}
