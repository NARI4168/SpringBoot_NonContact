package com.NonContact.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	// 현재 날짜 가져오는 함수
	public static String getNowDate() {
		SimpleDateFormat format1 = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
		Date time = new Date();
		return format1.format(time);
	}
}
