package com.NonContact.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.NonContact.dao.BoardDao;
import com.NonContact.dto.Board;

@Service
public class BoardService {
	@Autowired
	private BoardDao boardDao;

	public List<Board> getForPrintBoards(int page, int itemsInAPage, Map<String, Object> param) {
		
		int limitStart = (page - 1) * itemsInAPage;
		int limitTake = itemsInAPage;
		
		param.put("limitStart", limitStart);
		param.put("limitTake", limitTake);

		
		return boardDao.getForPrintBoards(param);
	}

	public void deleteBoard(String id) {	       
		boardDao.deleteBoard(id);
	}

	public Board getForPrintBoard(int id) {
		return boardDao.getForPrintBoard(id);
	}

	public Board getBoard(int id) {
		return boardDao.getBoard(id);
	}

	public void modifyBoard(Map<String, Object> param) {
		boardDao.modifyBoard(param);
		
	}


	
}
