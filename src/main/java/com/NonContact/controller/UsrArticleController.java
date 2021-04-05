package com.NonContact.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.NonContact.dto.Article;
import com.NonContact.dto.Board;
import com.NonContact.dto.Member;
import com.NonContact.dto.ResultData;
import com.NonContact.service.ArticleService;
import com.NonContact.util.Util;


@Controller
public class UsrArticleController {
	@Autowired
	private ArticleService articleService;

	@GetMapping("/usr/article/detail")
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

	@GetMapping("/usr/article/list")
	@ResponseBody
	public ResultData showList(@RequestParam(defaultValue = "1") int boardId, String searchKeywordType, String searchKeyword, @RequestParam(defaultValue = "1") int page) {
		
		Board board = articleService.getBoard(boardId);
		
		if(board == null){
			return new ResultData("F-1","해당 게시판은 존재하지 않습니다.");
		}
		
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
		
		int itemsInAPage = 3;

		List<Article> articles = articleService.getForPrintArticles(boardId,searchKeywordType, searchKeyword, page, itemsInAPage);

		return new ResultData("S-1", "성공", "articles", articles);
	}

	// 게시물 추가
	@PostMapping("/usr/article/doAdd")
	@ResponseBody
	public ResultData doAdd(@RequestParam Map<String, Object> param, HttpServletRequest req) {
		int loginedMemberId = (int)req.getAttribute("loginedMemberId");

		if (param.get("title") == null && param.get("body") == null) {
			return new ResultData("F-2", "title 또는 body가 입력되지 않았습니다.");
		}

		param.put("memberId", loginedMemberId);

		return articleService.addArticle(param);
	}

	// 게시물 삭제
	@PostMapping("/usr/article/doDelete")
	@ResponseBody
	public ResultData doDelete(Integer id, HttpServletRequest req) {

		Member loginedMember = (Member)req.getAttribute("loginedMember");

		if (id == null) {
			return new ResultData("F-2", "id를 입력해주세요.");
		}
		Article article = articleService.getArticle(id);

		if (article == null) {
			return new ResultData("F-1", "해당 게시물이 존재하지 않습니다.");
		}

		ResultData AuthChkRd = articleService.getAuthChkRd(article, loginedMember);

		if (AuthChkRd.isFail()) {
			return AuthChkRd;
		}

		return articleService.deleteArticle(id);
	}

	// 게시물 수정
	@PostMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData doModify(@RequestParam Map<String, Object> param, HttpServletRequest req) {

		Member loginedMember = (Member)req.getAttribute("loginedMember");
		int id = Util.getAsInt(param.get("id"), 0);

		if (id == 0) {
			return new ResultData("F-2", "id를 입력해주세요.");
		}
		if (Util.isEmpty(param.get("title"))&& Util.isEmpty(param.get("body")) ) {
			return new ResultData("F-2", "title 또는 body가 입력되지 않았습니다.");
		}

		Article article = articleService.getArticle(id);
		if (article == null) {
			return new ResultData("F-1", "해당 게시물이 존재하지 않습니다.");
		}

		ResultData AuthChkRd = articleService.getAuthChkRd(article, loginedMember);

		if (AuthChkRd.isFail()) {
			return AuthChkRd;
		}

		return articleService.modifyArticle(param);
	}
	
	//댓글 추가
	@PostMapping("/usr/article/doAddReply")
	@ResponseBody
	public ResultData doAddReply(@RequestParam Map<String, Object> param, HttpServletRequest req) {
		int loginedMemberId = (int)req.getAttribute("loginedMemberId");
		
		if (param.get("articleId") == null && param.get("body") == null) {
			return new ResultData("F-2", "articleId 또는 body이 입력되지 않았습니다.");
		}
		
		param.put("memberId", loginedMemberId);
		
		return articleService.addReply(param);
	}
	
	

}
