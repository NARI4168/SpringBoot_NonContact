package com.NonContact.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.NonContact.dao.ArticleDao;
import com.NonContact.dto.Article;
import com.NonContact.dto.ResultData;

@Service
public class ArticleService {
	@Autowired
	private ArticleDao articleDao;

	public Article getArticle(int id) {

		return articleDao.getArticle(id);
	}

	public List<Article> getArticles(String searchKeywordType, String searchKeyword) {

		return articleDao.getArticles(searchKeywordType, searchKeyword);

	}

	public ResultData addArticle(String title, String body) {

		int id = articleDao.addArticle(title, body);
		return new ResultData("S-1", "추가되었습니다.", "id", id);

	}

	public ResultData deleteArticle(int id) {

		boolean rs = articleDao.deleteArticle(id);
		if (rs == false) {
			return new ResultData("F-1", "해당 게시물이 존재하지 않습니다.");
		}
		return new ResultData("S-1", "삭제되었습니다.", "id", id);
	}

	public ResultData modifyArticle(int id, String body, String title) {

		articleDao.modifyArticle(id, body, title);
		return new ResultData("S-2", String.format("%d번 게시물이 수정되었습니다.", id), "id", id);

	}
}
