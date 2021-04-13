package com.NonContact.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.NonContact.dto.Board;
import com.NonContact.service.BoardService;
import com.NonContact.util.Util;

@Controller
public class AdmBoardController extends BaseController {
	@Autowired
	private BoardService boardService;

	@RequestMapping("/adm/board/list")
	public String showList(HttpServletRequest req, @RequestParam(defaultValue = "1") int page,
			@RequestParam Map<String, Object> param) {

		int itemsInAPage = 10;

		List<Board> boards = boardService.getForPrintBoards(page, itemsInAPage, param);

		req.setAttribute("boards", boards);

		return "adm/board/list";
	}

	@RequestMapping("/adm/board/doDelete")
	public String doDelete(HttpServletRequest req) {

		String[] ajaxMsg = req.getParameterValues("valueArr");
		int size = ajaxMsg.length;
		for (int i = 0; i < size; i++) {
			boardService.deleteBoard(ajaxMsg[i]);
		}
		return "redirect:list";
	}

	@RequestMapping("/adm/board/modify")
	public String modify(HttpServletRequest req) {

		String selectedId = req.getParameter("selectedId");
		int id = Integer.parseInt(selectedId);

		Board board = boardService.getForPrintBoard(id);

		req.setAttribute("board", board);

		return "adm/board/modify";
	}

	@RequestMapping("/adm/board/doModify")
	@ResponseBody
	public String doModify(@RequestParam Map<String, Object> param, HttpServletRequest req) {

		int id = Util.getAsInt(param.get("id"), 0);
		Board board = boardService.getBoard(id);
		boardService.modifyBoard(param);

		String msg = String.format("%d번 게시판이 수정되었습니다.", id);

		return Util.msgAndReplace(msg, "../board/list");

	}

}
