package com.NonContact.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.NonContact.dto.Article;
import com.NonContact.util.Util;

@Controller
public class UsrArticleController {
	private int articlesLastId;
	private List<Article> articles;

	public UsrArticleController() {
		articlesLastId = 0; // article변수 초기화

		articles = new ArrayList<>();
		
        //test데이터 임의추가
		articles.add(new Article(++articlesLastId, "2020-12-19 13:52:11", "2020-12-19 13:52:11", "제목1", "내용1"));
		articles.add(new Article(++articlesLastId, "2020-12-19 13:52:11", "2020-12-19 13:52:11", "제목2", "내용2"));
	}

	@RequestMapping("/usr/article/detail")
	@ResponseBody
	public Article showDetail(int id) {

		return articles.get(id - 1);
	}

	@RequestMapping("/usr/article/list")
	@ResponseBody
	public List<Article> showList() {

		return articles;
	}

	// 게시물 추가
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public Map<String, Object> doAdd(String title, String body) {
       
		String regDate = Util.getNowDate();
		String updateDate = regDate;

		articles.add(new Article(++articlesLastId, regDate, updateDate, title, body));

		Map<String, Object> rs = new HashMap<>();
		rs.put("resultCode", "S");
		rs.put("msg", "추가되었습니다.");
		rs.put("id", "artileLastId");

		return rs;

	}
	
	// 게시물 삭제
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public Map<String, Object> doDelete(int id) {
		boolean deleteArticleRs = deleteArticle(id);

		Map<String, Object> rs = new HashMap<>();

		if (deleteArticleRs) {
			rs.put("resultCode", "S");
			rs.put("msg", "삭제되었습니다.");
		} else {
			rs.put("resultCode", "F");
			rs.put("msg", "해당 게시물이 존재하지 않습니다.");
		}
		rs.put("id", id);

		return rs;

	}

	private boolean deleteArticle(int id) {
		for (Article article : articles) {
			if (article.getId() == id) {
				articles.remove(article);
				return true;
			}
		}
		return false;
	}

	// 게시물 수정
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public Map<String, Object> doModify(int id, String title, String body) {
		Article selArticle = null;

		for (Article article : articles) {
			if (article.getId() == id) {
				selArticle = article;
				break;
			}
		}

		Map<String, Object> rs = new HashMap<>();

		if (selArticle == null) {
			rs.put("resultCode", "F");
			rs.put("msg", String.format("%d번 게시물이 존재하지 않습니다", id));
			return rs;
		}
		
        selArticle.setUpdateDate(Util.getNowDate());
		selArticle.setTitle(title);
		selArticle.setBody(body);

		rs.put("resultCode", "S");
		rs.put("msg", String.format("%d번 게시물이 수정되었습니다", id));
		rs.put("id", id);

		return rs;

	}

}
