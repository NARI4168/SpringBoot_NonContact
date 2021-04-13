package com.NonContact.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.NonContact.dto.Board;

@Mapper
public interface BoardDao {

	List<Board> getForPrintBoards(Map<String, Object> param);

	public void deleteBoard(@Param("id") String id);

	Board getForPrintBoard(@Param("id") int id);

	Board getBoard(@Param("id") int id);

	void modifyBoard(Map<String, Object> param);
}
