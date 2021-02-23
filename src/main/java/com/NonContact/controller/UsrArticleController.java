package com.NonContact.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.NonContact.dto.Article;
import com.NonContact.dto.ResultData;
import com.NonContact.service.ArticleService;

@Controller
public class UsrArticleController {
	@Autowired
	private ArticleService articleService;

	@RequestMapping("/usr/article/detail")
	@ResponseBody
	public Article showDetail(int id) {
		Article article = articleService.getArticle(id);

		return article;
	}

	@RequestMapping("/usr/article/list")
	@ResponseBody
	public List<Article> showList() {

		return articleService.getArticles();
	}

	// 게시물 추가
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public ResultData doAdd(String title, String body) {
		if (title == null && body == null) {
			return new ResultData("F-2", "title 또는 body가 입력되지 않았습니다.");
		}

		// return Util.mapOf("resultCode", "S-1", "msg", "추가되었습니다.", "id",
		// articlesLastId);
		// return new ResultData("S-1", "완료되었습니다.", "id", articlesLastId);
		ResultData rsData = articleService.add(title, body);

		return rsData;
	}

	// 게시물 삭제
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public ResultData doDelete(Integer id) {
		/*
		 * boolean deleteArticleRs = deleteArticle(id); if (deleteArticleRs == false) {
		 * //return Util.mapOf("resultCode", "F", "msg", "해당 게시물이 존재하지 않습니다.", "id",
		 * id); return new ResultData("F-1","해당 게시물이 존재하지 않습니다."); } //return
		 * Util.mapOf("resultCode", "S", "msg", "삭제되었습니다.", "id", id); return new
		 * ResultData("S-1","완료되었습니다.", "id", id); }
		 * 
		 * private boolean deleteArticle(int id) { for (Article article : articles) { if
		 * (article.getId() == id) { articles.remove(article); return true; } } return
		 * false;
		 */
		if (id == null) {
			return new ResultData("F-2", "id를 입력해주세요.");
		}
		Article article = articleService.getArticle(id);

		if (article == null) {
			return new ResultData("F-1", "해당 게시물이 존재하지 않습니다.");
		}
		return articleService.deleteArticle(id);
	}

	// 게시물 수정
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData doModify(Integer id, String title, String body) {
		/*
		 * Article selArticle = null;
		 * 
		 * for (Article article : articles) { if (article.getId() == id) { selArticle =
		 * article; break; } }
		 * 
		 * if (selArticle == null) { //return Util.mapOf("resultCode", "F", "msg",
		 * String.format("%d번 게시물이 존재하지 않습니다", id)); return new ResultData("F-1", "msg",
		 * String.format("%d번 게시물이 존재하지 않습니다", id)); }
		 * 
		 * selArticle.setUpdateDate(Util.getNowDate()); selArticle.setTitle(title);
		 * selArticle.setBody(body);
		 * 
		 * //return Util.mapOf("resultCode", "S", "msg",
		 * String.format("%d번 게시물이 수정되었습니다", id)); return new ResultData("S-2", "msg",
		 * String.format("%d번 게시물이 수정되었습니다.", id));
		 */
		
		if (id == null) {
			return new ResultData("F-2", "id를 입력해주세요.");
		}
		if (title == null && body == null) {
			return new ResultData("F-2", "title 또는 body가 입력되지 않았습니다.");
		}
		
		Article article = articleService.getArticle(id);
		if (article == null) {
			return new ResultData("F-1", "해당 게시물이 존재하지 않습니다.");
		}
		return articleService.modifyArticle(id, body, title);
	}

}
