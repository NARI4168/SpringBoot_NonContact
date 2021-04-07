package com.NonContact.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.NonContact.dao.ArticleDao;
import com.NonContact.dto.Article;
import com.NonContact.dto.Board;
import com.NonContact.dto.GenFile;
import com.NonContact.dto.Member;
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
		
		genFileService.changeInputFileRelIds(param, id);

		return new ResultData("S-1", "추가되었습니다.", "id", id);

	}

	public ResultData deleteArticle(int id) {

		articleDao.deleteArticle(id);

		List<GenFile> genFiles = genFileService.getGenFiles("article", id);

		if (genFiles.isEmpty() == false) {
			genFileService.deleteGenFile(genFiles);
		}

		return new ResultData("S-1", "삭제되었습니다.", "id", id);
	}

	public ResultData modifyArticle(Map<String, Object> param) {
		
		articleDao.modifyArticle(param);
		
		int id = Util.getAsInt(param.get("id"), 0);	
		
		//genFileService.changeInputFileRelIds(param, id);
		
		return new ResultData("S-2", String.format("%d번 게시물이 수정되었습니다.", id), "id", id);

	}
	

	public ResultData getAuthChkRd(Article article, Member loginedMember) {
		if (article.getMemberId() == loginedMember.getId()) {
			return new ResultData("S-1", "사용 권한이 확인 되었습니다.");
		}
		if (memberService.isAdmin(loginedMember)) {
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

		List<Article> articles = articleDao.getForPrintArticles(boardId, searchKeywordType, searchKeyword, limitStart, limitTake);
		List<Integer> articleIds = articles.stream().map(article -> article.getId()).collect(Collectors.toList());
		Map<Integer, Map<String, GenFile>> filesMap = genFileService.getFilesMapKeyRelIdAndFileNo("article", articleIds, "common", "attachment");

		for (Article article : articles) {
			Map<String, GenFile> mapByFileNo = filesMap.get(article.getId());

			if (mapByFileNo != null) {
				article.getExtraNotNull().put("file__common__attachment", mapByFileNo);
			}
		}

		return articles;
	}

	public Board getBoard(int boardId) {

		return articleDao.getBoard(boardId);
	}

	public ResultData addReply(Map<String, Object> param) {
		articleDao.addReply(param);

		int id = Util.getAsInt(param.get("id"), 0);

		return new ResultData("S-1", "추가되었습니다.", "id", id);

	}

	public int getArticlesTotalCount(int boardId, String searchKeywordType, String searchKeyword) {
		
		return articleDao.getArticlesTotalCount(boardId, searchKeywordType, searchKeyword);
	}
}
