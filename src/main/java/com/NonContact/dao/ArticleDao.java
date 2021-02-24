package com.NonContact.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.NonContact.dto.Article;
import com.NonContact.util.Util;

@Component
public class ArticleDao {
	private int articlesLastId;
	private List<Article> articles;

	public ArticleDao() {

		articlesLastId = 0; // article변수 초기화
		articles = new ArrayList<>();

		// test데이터 임의추가
		articles.add(new Article(++articlesLastId, "2020-12-19 13:52:11", "2020-12-19 13:52:11", "제목1 입니다", "내용1 입니다"));
		articles.add(new Article(++articlesLastId, "2020-12-19 13:52:11", "2020-12-19 13:52:11", "제목2 입니다", "내용2 입니다"));

	}

	public Article getArticle(int id) {
		for (Article article : articles) {
			if (article.getId() == id) {
				return article;
			}
		}
		return null;

	}

	public List<Article> getArticles(String searchKeywordType, String searchKeyword) {
		if (searchKeyword == null) {
			return articles;
		}
		List<Article> filtered = new ArrayList<>();

		for (Article article : articles) {
			boolean contains = false;

			if (searchKeywordType.equals("title")) {
				contains = article.getTitle().contains(searchKeyword);
			} else if (searchKeywordType.equals("body")) {
				contains = article.getBody().contains(searchKeyword);
			} else {
				contains = article.getTitle().contains(searchKeyword);
				if (contains == false) {
					contains = article.getBody().contains(searchKeyword);
				}
			}

			if (contains) {
				filtered.add(article);
			}
		}
		return filtered;

	}

	public int addArticle(String title, String body) {

		int id = ++articlesLastId;
		String regDate = Util.getNowDate();
		String updateDate = regDate;

		articles.add(new Article(id, regDate, updateDate, title, body));

		return id;
	}

	public boolean deleteArticle(int id) {
		for (Article article : articles) {
			if (article.getId() == id) {
				articles.remove(article);
				return true;
			}
		}
		return false;
	}

	public void modifyArticle(int id, String body, String title) {
		Article article = getArticle(id);

		article.setTitle(title);
		article.setBody(body);
		article.setUpdateDate(Util.getNowDate());

	}

}
