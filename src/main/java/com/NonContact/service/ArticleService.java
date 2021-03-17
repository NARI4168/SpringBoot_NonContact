package com.NonContact.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.NonContact.dao.ArticleDao;
import com.NonContact.dto.Article;
import com.NonContact.dto.Board;
import com.NonContact.dto.ResultData;
import com.NonContact.util.Util;

@Service
public class ArticleService {
	@Autowired
	private GenFileService genFileService;
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

		String genFileIdsStr = Util.ifEmpty((String) param.get("genFileIdsStr"), null);
		
		if (genFileIdsStr != null) {
			List<Integer> genFileIds = Util.getListDividedBy(genFileIdsStr, ",");

			// 파일이 먼저 생성된 후에, 관련 데이터가 생성되는 경우에는, file의 relId가 일단 0으로 저장된다.
			// 그것을 뒤늦게라도 이렇게 고처야 한다.
			
			for (int genFileId : genFileIds) {			
				genFileService.changeRelId(genFileId, id);
			}
		}

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

	public List<Article> getForPrintArticles(int boardId, String searchKeywordType, String searchKeyword, int page,
			int itemsInAPage) {

		int limitStart = (page - 1) * itemsInAPage;
		int limitTake = itemsInAPage;

		return articleDao.getForPrintArticles(boardId, searchKeywordType, searchKeyword, limitStart, limitTake);
	}

	public Board getBoard(int boardId) {

		return articleDao.getBoard(boardId);
	}

	public ResultData addReply(Map<String, Object> param) {
		articleDao.addReply(param);

		int id = Util.getAsInt(param.get("id"), 0);

		return new ResultData("S-1", "추가되었습니다.", "id", id);

	}
}
