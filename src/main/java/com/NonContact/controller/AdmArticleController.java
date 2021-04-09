package com.NonContact.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartRequest;

import com.NonContact.dto.Article;
import com.NonContact.dto.Board;
import com.NonContact.dto.GenFile;
import com.NonContact.dto.Member;
import com.NonContact.dto.ResultData;
import com.NonContact.service.ArticleService;
import com.NonContact.service.GenFileService;
import com.NonContact.util.Util;

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

		List<GenFile> files  = genFileService.getGenFiles("article", article.getId(), "common", "attachment");
		
		Map<String, GenFile> filesMap = new HashMap<>();
		
		for (GenFile file : files) {
			filesMap.put(file.getFileNo() + "", file);
		}

		article.getExtraNotNull().put("file__common__attachment", filesMap);
		req.setAttribute("article", article);
		if (article == null) {
			return msgAndBack(req, "존재하지 않는 게시물번호 입니다.");
		}
	
		/*if (genFile != null) {
			article.setExtra__thumbImg(genFile.getForPrintUrl());
		}

		req.setAttribute("article", article);*/

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

		int totalItemsCount = articleService.getArticlesTotalCount(boardId, searchKeywordType, searchKeyword);
		
		int itemsInAPage = 5;
		
		int totalPage = (int)Math.ceil(totalItemsCount / (double)itemsInAPage);

		int pageMenuArmSize = 3;
		int pageMenuStart = page - pageMenuArmSize;

		if (pageMenuStart < 1) {
			pageMenuStart = 1;
		}

		int pageMenuEnd = page + pageMenuArmSize;
		if (pageMenuEnd > totalPage) {
			pageMenuEnd = totalPage;
		}
		
		
		
		List<Article> articles = articleService.getForPrintArticles(boardId, searchKeywordType, searchKeyword, page,
				itemsInAPage);

		req.setAttribute("totalItemsCount", totalItemsCount);
		req.setAttribute("articles", articles);
		req.setAttribute("page", page);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("pageMenuArmSize", pageMenuArmSize);
		req.setAttribute("pageMenuStart", pageMenuStart);
		req.setAttribute("pageMenuEnd", pageMenuEnd);
		
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

		/*Map<String, MultipartFile> fileMap = mltipartRequest.getFileMap();

		for (String fileInputName : fileMap.keySet()) {
			MultipartFile multipartFile = fileMap.get(fileInputName);
			if (multipartFile.isEmpty() == false) {
				genFileService.save(multipartFile, newArticleId);
			}
		}*/

		return msgAndReplace(req, String.format("%d번 게시물이 작성되었습니다.", newArticleId),
				"../article/detail?id=" + newArticleId);
	}

	// 게시물 삭제
	@RequestMapping("/adm/article/doDelete")
	public String doDelete(Integer id, HttpServletRequest req) {

		Member loginedMember = (Member)req.getAttribute("loginedMember");
		
		Article article = articleService.getArticle(id);

		if (article == null) {
			return msgAndBack(req, "해당 게시물이 존재하지 않습니다.");
		}

		ResultData AuthChkRd = articleService.getAuthChkRd(article, loginedMember);

		if (AuthChkRd.isFail()) {
			return msgAndBack(req, "권한이 없습니다.");
		}

		articleService.deleteArticle(id);

		return msgAndReplace(req, String.format("%d번 게시물이 삭제되었습니다.", id), "../article/list");
	}

	// 게시물 수정
	@RequestMapping("/adm/article/modify")
	public String showModify(Integer id, HttpServletRequest req) {

		Article article = articleService.getForPrintArticle(id);

		List<GenFile> files = genFileService.getGenFiles("article", article.getId(), "common", "attachment");

		Map<String, GenFile> filesMap = new HashMap<>();

		for (GenFile file : files) {
			filesMap.put(file.getFileNo() + "", file);
		}

		article.getExtraNotNull().put("file__common__attachment", filesMap);
		req.setAttribute("article", article);
		
	/*	if (article == null) {
			return msgAndBack(req, "존재하지 않는 게시물번호 입니다.");
		}*/

		return "adm/article/modify";
	}

	@RequestMapping("/adm/article/doModify")
	public String doModify(@RequestParam Map<String, Object> param, HttpServletRequest req) {

		Member loginedMember = (Member) req.getAttribute("loginedMember");

		int id = Util.getAsInt(param.get("id"), 0);

		// if (id == null) {
		// return msgAndBack(req, "id를 입력해주세요.");
		//}
		
		if (Util.isEmpty(param.get("title")) && Util.isEmpty(param.get("body"))) {
			return msgAndBack(req, "title 또는 body가 입력되지 않았습니다.");
		}

		Article article = articleService.getArticle(id);
		
		if (article == null) {
			return msgAndBack(req, "해당 게시물이 존재하지 않습니다.");
		}

		ResultData AuthChkRd = articleService.getAuthChkRd(article, loginedMember);

		if (AuthChkRd.isFail()) {
			return msgAndBack(req, "권한이 없습니다.");
		}

		articleService.modifyArticle(param);

		return msgAndReplace(req, String.format("%d번 게시물이 수정되었습니다.", id), "../article/detail?id=" + id);

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
