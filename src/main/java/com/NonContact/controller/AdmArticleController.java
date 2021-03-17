package com.NonContact.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.NonContact.dto.Article;
import com.NonContact.dto.Board;
import com.NonContact.dto.GenFile;
import com.NonContact.dto.ResultData;
import com.NonContact.service.ArticleService;
import com.NonContact.service.GenFileService;

@Controller
public class AdmArticleController extends BaseController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private GenFileService genFileService;

	@RequestMapping("/adm/article/detail")
	public String showDetail(HttpServletRequest req, int id) {

		Article article = articleService.getForPrintArticle(id);

		if (article == null) {
			return msgAndBack(req, "해당 게시물이 존재하지 않습니다.");
		}

		GenFile genFile = genFileService.getGenFile("article", article.getId(), "common", "attachment", 1);

		if (genFile != null) {
			article.setExtra__thumbImg(genFile.getForPrintUrl());
		}

		req.setAttribute("article", article);

		return ("adm/article/detail");
	}

	@RequestMapping("/adm/article/list")
	public String showList(HttpServletRequest req, @RequestParam(defaultValue = "0") int boardId,
			String searchKeywordType, String searchKeyword, @RequestParam(defaultValue = "1") int page) {

		if (boardId != 0) {
			Board board = articleService.getBoard(boardId);

			if (board == null) {
				return msgAndBack(req, "해당 게시판은 존재하지 않습니다.");
			}
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

		List<Article> articles = articleService.getForPrintArticles(boardId, searchKeywordType, searchKeyword, page,
				itemsInAPage);

		for (Article article : articles) {
			GenFile genFile = genFileService.getGenFile("article", article.getId(), "common", "attachment", 1);

			if (genFile != null) {
				article.setExtra__thumbImg(genFile.getForPrintUrl());
			}
		}
		req.setAttribute("articles", articles);
		return ("adm/article/list");
	}

	// 게시물 추가
	@RequestMapping("/adm/article/add")
	public String showAdd(@RequestParam Map<String, Object> param, HttpServletRequest req) {
		return "adm/article/add";
	}

	@RequestMapping("/adm/article/doAdd")
	public String doAdd(@RequestParam Map<String, Object> param, HttpServletRequest req,
			MultipartRequest mltipartRequest) {

		int loginedMemberId = (int) req.getAttribute("loginedMemberId");

		if (param.get("title") == null && param.get("body") == null) {
			return msgAndBack(req, "title 또는 body가 입력되지 않았습니다.");
		}

		param.put("memberId", loginedMemberId);

		ResultData addArticleRd = articleService.addArticle(param);

		int newArticleId = (int) addArticleRd.getBody().get("id");
System.out.println("1111111111111111111111111111111111111111111111111111");
		System.out.println(newArticleId);
		Map<String, MultipartFile> fileMap = mltipartRequest.getFileMap();
		
		for (String fileInputName : fileMap.keySet()) {
			MultipartFile multipartFile = fileMap.get(fileInputName);
			if (multipartFile.isEmpty() == false) {
				genFileService.save(multipartFile, newArticleId);
			}
		}

		return msgAndReplace(req, String.format("%d번 게시물이 작성되었습니다.", newArticleId),
				"../article/detail?id=" + newArticleId);
	}

	// 게시물 삭제
	@RequestMapping("/adm/article/doDelete")
	@ResponseBody
	public ResultData doDelete(Integer id, HttpServletRequest req) {

		int loginedMemberId = (int) req.getAttribute("loginedMemberId");

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
	@RequestMapping("/adm/article/doModify")
	@ResponseBody
	public ResultData doModify(Integer id, String title, String body, HttpServletRequest req) {

		int loginedMemberId = (int) req.getAttribute("loginedMemberId");

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

	// 댓글 추가
	@RequestMapping("/adm/article/doAddReply")
	@ResponseBody
	public ResultData doAddReply(@RequestParam Map<String, Object> param, HttpServletRequest req) {
		int loginedMemberId = (int) req.getAttribute("loginedMemberId");

		if (param.get("articleId") == null && param.get("body") == null) {
			return new ResultData("F-2", "articleId 또는 body이 입력되지 않았습니다.");
		}

		param.put("memberId", loginedMemberId);

		return articleService.addReply(param);
	}

}
