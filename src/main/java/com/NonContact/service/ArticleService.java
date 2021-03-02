package com.NonContact.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.NonContact.dao.ArticleDao;
import com.NonContact.dto.Article;
import com.NonContact.dto.ResultData;
import com.NonContact.util.Util;

@Service
public class ArticleService {
	@Autowired
	private ArticleDao articleDao;
	@Autowired
	private MemberService memberService;

	public Article getArticle(int id) {

		return articleDao.getArticle(id);
	}

	public List<Article> getArticles(String searchKeywordType, String searchKeyword) {

		return articleDao.getArticles(searchKeywordType, searchKeyword);

	}

	public ResultData addArticle(Map<String, Object> param) {
		articleDao.addArticle(param);

		int id = Util.getAsInt(param.get("id"), 0);

		return new ResultData("S-1", "추가되었습니다.", "id", id);

	}

	public ResultData deleteArticle(int id) {

		articleDao.deleteArticle(id);

		return new ResultData("S-1", "삭제되었습니다.", "id", id);
	}

	public ResultData modifyArticle(int id, String body, String title) {

		articleDao.modifyArticle(id, body, title);
		return new ResultData("S-2", String.format("%d번 게시물이 수정되었습니다.", id), "id", id);

	}

	public ResultData getAuthChkRd(Article article, int loginedMemberId) {
		if (article.getMemberId() == loginedMemberId) {
			return new ResultData("S-1", "사용 권한이 확인 되었습니다.");
		}
		if (memberService.isAdmin(loginedMemberId)) {
			return new ResultData("S-1", "사용 권한이 확인 되었습니다.");
		}
		return new ResultData("F-6", "사용 권한이 없습니다.");
	}

	public Article getForPrintArticle(int id) {
		
		return articleDao.getForPrintArticle(id);
	}

	public List<Article> getForPrintArticles(String searchKeywordType, String searchKeyword) {
		
		return articleDao.getForPrintArticles(searchKeywordType, searchKeyword);
	}
}
