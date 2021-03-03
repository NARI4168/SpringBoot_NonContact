package com.NonContact.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.NonContact.dto.Article;
import com.NonContact.dto.Board;

@Mapper
public interface ArticleDao {

	public Article getArticle(@Param("id") int id);

	public Article getForPrintArticle(@Param("id") int id);
	
	public List<Article> getArticles(@Param(value = "searchKeywordType") String searchKeywordType,
			@Param(value = "searchKeyword") String searchKeyword);

	public List<Article> getForPrintArticles(@Param("boardId") int boardId, @Param("searchKeywordType") String searchKeywordType, @Param("searchKeyword") String searchKeyword, @Param("limitStart") int limitStart, @Param("limitTake") int limitTake);
	
	public void addArticle(Map<String, Object> param);

	public void deleteArticle(@Param("id") int id);

	public void modifyArticle(@Param("id") int id, @Param("body") String body,
			@Param("title") String title);

	public Board getBoard(int boardId);

	public void addReply(Map<String, Object> param);



}
