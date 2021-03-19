package com.NonContact.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.NonContact.dto.Article;
import com.NonContact.dto.Board;

@Mapper
public interface ArticleDao {

	Article getArticle(@Param("id") int id);

	Article getForPrintArticle(@Param("id") int id);
	
	List<Article> getArticles(@Param(value = "searchKeywordType") String searchKeywordType,
			@Param(value = "searchKeyword") String searchKeyword);

	List<Article> getForPrintArticles(@Param("boardId") int boardId, @Param("searchKeywordType") String searchKeywordType, @Param("searchKeyword") String searchKeyword, @Param("limitStart") int limitStart, @Param("limitTake") int limitTake);
	
	void addArticle(Map<String, Object> param);

	void deleteArticle(@Param("id") int id);

	void modifyArticle(Map<String, Object> param);

	Board getBoard(int boardId);

	void addReply(Map<String, Object> param);



}
