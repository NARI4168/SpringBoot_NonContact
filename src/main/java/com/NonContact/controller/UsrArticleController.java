package com.NonContact.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.NonContact.dto.Article;
import com.NonContact.dto.ResultData;
import com.NonContact.service.ArticleService;
import com.NonContact.util.Util;

@Controller
public class UsrArticleController {
	@Autowired
	private ArticleService articleService;

	@RequestMapping("/usr/article/detail")
	@ResponseBody
	public ResultData showDetail(Integer id) {
		
		if(id == null) {
			return new ResultData("F-2", "id를 입력해주세요.");
		}
		Article article = articleService.getForPrintArticle(id);
		
		if(article == null) {
			return new ResultData("F-1", "해당 게시물이 존재하지 않습니다.");
		}

		return new ResultData("S-1", "성공", "article", article);
	}

	@RequestMapping("/usr/article/list")
	@ResponseBody
	public ResultData showList(String searchKeywordType, String searchKeyword) {
		if (searchKeywordType != null) {
			searchKeywordType = searchKeywordType.trim();
		}
		if (searchKeywordType == null || searchKeywordType.length() == 0) {
			searchKeywordType = "titleAndBody";
		}
		if (searchKeyword != null && searchKeyword.length() == 0) {
			searchKeyword = null;
		}
		if (searchKeyword != null) {
			searchKeyword = searchKeyword.trim();
		}
		if (searchKeyword == null) {
			searchKeywordType = null;
		}

		List<Article> articles = articleService.getForPrintArticles(searchKeywordType, searchKeyword);

		return new ResultData("S-1", "성공", "articles", articles);
	}

	// 게시물 추가
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public ResultData doAdd(@RequestParam Map<String, Object> param, HttpSession session) {
		int loginedMemberId = Util.getAsInt(session.getAttribute("loginedMemberId"), 0);

		if (param.get("title") == null && param.get("body") == null) {
			return new ResultData("F-2", "title 또는 body가 입력되지 않았습니다.");
		}

		param.put("memberId", loginedMemberId);

		return articleService.addArticle(param);
	}

	// 게시물 삭제
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public ResultData doDelete(Integer id, HttpSession session) {

		int loginedMemberId = Util.getAsInt(session.getAttribute("loginedMemberId"), 0);

		if (id == null) {
			return new ResultData("F-2", "id를 입력해주세요.");
		}
		Article article = articleService.getArticle(id);

		if (article == null) {
			return new ResultData("F-1", "해당 게시물이 존재하지 않습니다.");
		}

		ResultData AuthChkRd = articleService.getAuthChkRd(article, loginedMemberId);

		if (AuthChkRd.isFail()) {
			return AuthChkRd;
		}

		return articleService.deleteArticle(id);
	}

	// 게시물 수정
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData doModify(Integer id, String title, String body, HttpSession session) {

		int loginedMemberId = Util.getAsInt(session.getAttribute("loginedMemberId"), 0);

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

		ResultData AuthChkRd = articleService.getAuthChkRd(article, loginedMemberId);

		if (AuthChkRd.isFail()) {
			return AuthChkRd;
		}

		return articleService.modifyArticle(id, body, title);
	}

}
