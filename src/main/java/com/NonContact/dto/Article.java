package com.NonContact.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //toString, GettersSetters
@NoArgsConstructor //인자없는 생성자
@AllArgsConstructor //생성자
public class Article {
	private int id;
	private String regDate;
	private String updateDate;
	private String title;
	private String body;

}
