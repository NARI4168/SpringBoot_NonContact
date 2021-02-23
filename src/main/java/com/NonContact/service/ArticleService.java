package com.NonContact.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.NonContact.dto.Article;
import com.NonContact.dto.ResultData;
import com.NonContact.util.Util;

@Service
public class ArticleService {
	private int articlesLastId;
	private List<Article> articles;
	
	public ArticleService() {
		
		articlesLastId = 0; // article변수 초기화
		articles = new ArrayList<>();

		// test데이터 임의추가
		articles.add(new Article(++articlesLastId, "2020-12-19 13:52:11", "2020-12-19 13:52:11", "제목1", "내용1"));
		articles.add(new Article(++articlesLastId, "2020-12-19 13:52:11", "2020-12-19 13:52:11", "제목2", "내용2"));
	
	}

	public Article getArticle(int id) {
		for(Article article : articles) {
			if(article.getId()==id) {
				return article;
			}
		}
		return null;
	}

	public List<Article> getArticles() {
		
		return articles;
	}

	public ResultData add(String title, String body) {
		int id = ++articlesLastId;
		String regDate = Util.getNowDate();
		String updateDate = regDate;

		articles.add(new Article(id, regDate, updateDate, title, body));

		return new ResultData("S-1", "성공하였습니다.", "id", id);
	
	}

	public ResultData deleteArticle(int id) {		
		for(Article article : articles) {
			if(article.getId()==id) {
				articles.remove(article);
				return new ResultData("S-1", "성공하였습니다.", "id", id);
			}
		}
		return new ResultData("F-1","해당 게시물이 존재하지 않습니다.");
		
	}

	public ResultData modifyArticle(int id, String body, String title) {
		Article article = getArticle(id);
		
		article.setTitle(title);
		article.setBody(body);
		article.setUpdateDate(Util.getNowDate());
		
		return new ResultData("S-2",String.format("%d번 게시물이 수정되었습니다.", id), "id", id);
			
	}
}
