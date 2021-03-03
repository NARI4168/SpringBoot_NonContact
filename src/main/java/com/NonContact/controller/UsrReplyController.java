package com.NonContact.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.NonContact.dto.Article;
import com.NonContact.dto.Reply;
import com.NonContact.dto.ResultData;
import com.NonContact.service.ArticleService;
import com.NonContact.service.ReplyService;

@Controller
public class UsrReplyController {
	@Autowired
	private ReplyService replyService;
	@Autowired
	private ArticleService articleService;

	@RequestMapping("usr/reply/list")
	@ResponseBody
	public ResultData showList(String relTypeCode, Integer relId) {
		if (relId == null) {
			return new ResultData("F-1", "id를 입력해 주세요.");
		}

		if (relTypeCode.equals("article")) {
			Article article = articleService.getArticle(relId);
			if (article == null) {
				return new ResultData("F-1", "해당 게시물이 존재하지 않습니다.");
			}
		}
		List<Reply> replies = replyService.getForPrintReplies(relTypeCode, relId);

		return new ResultData("S-1", "성공", "replies", replies);

	}
}
