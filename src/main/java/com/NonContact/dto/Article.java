package com.NonContact.dto;

public class Article {
	private int id;
	private String regDate;
	private String title;
	private String body;
	
	public Article(int id, String regDate, String title, String body) {
		this.id = id;
		this.regDate= regDate;
		this.title = title;
		this.body = body;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", regDate=" + regDate + ", title=" + title + ", body=" + body + "]";
	}

}
