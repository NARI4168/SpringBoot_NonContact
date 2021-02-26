package com.NonContact.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.NonContact.dto.Article;

@Mapper
public interface ArticleDao {

	public Article getArticle(@Param(value = "id") int id);

	public List<Article> getArticles(@Param(value = "searchKeywordType") String searchKeywordType,
			@Param(value = "searchKeyword") String searchKeyword);

	// public void addArticle(@Param(value = "title") String title, @Param(value =
	// "body") String body);
	public void addArticle(Map<String, Object> param);

	public void deleteArticle(@Param(value = "id") int id);

	public void modifyArticle(@Param(value = "id") int id, @Param(value = "body") String body,
			@Param(value = "title") String title);

}
