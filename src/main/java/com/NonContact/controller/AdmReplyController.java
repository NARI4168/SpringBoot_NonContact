package com.NonContact.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.NonContact.dto.Article;
import com.NonContact.dto.Member;
import com.NonContact.dto.Reply;
import com.NonContact.dto.ResultData;
import com.NonContact.service.ArticleService;
import com.NonContact.service.ReplyService;

@Controller
public class AdmReplyController {
	@Autowired
	private ReplyService replyService;
	@Autowired
	private ArticleService articleService;

	@RequestMapping("/adm/reply/list")
	@ResponseBody
	public ResultData showList(String relTypeCode, Integer relId) {
		if (relId == null) {
			return new ResultData("F-1", "id를 입력해 주세요.");
		}
		if (relTypeCode == null) {
			return new ResultData("F-1", "relTypeCode를 입력해 주세요.");
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

	// 댓글 삭제
	@RequestMapping("/adm/reply/doDelete")
	@ResponseBody
	public ResultData doDelete(Integer id, HttpServletRequest req) {

		Member loginedMember = (Member) req.getAttribute("loginedMember");

		if (id == null) {
			return new ResultData("F-2", "id를 입력해주세요.");
		}
		Reply reply = replyService.getReply(id);

		if (reply == null) {
			return new ResultData("F-1", "해당 댓글이 존재하지 않습니다.");
		}

		ResultData AuthChkRd = replyService.getAuthChkRd(reply, loginedMember);

		if (AuthChkRd.isFail()) {
			return AuthChkRd;
		}

		return replyService.deleteReply(id);
	}

	// 댓글 수정
	@RequestMapping("/adm/reply/doModify")
	@ResponseBody
	public ResultData doModify(Integer id, String body, HttpServletRequest req) {

		Member loginedMember = (Member)req.getAttribute("loginedMember");

		if (id == null) {
			return new ResultData("F-2", "id를 입력해주세요.");
		}
		if (body == null) {
			return new ResultData("F-2", "body가 입력되지 않았습니다.");
		}
		Reply reply = replyService.getReply(id);

		if (reply == null) {
			return new ResultData("F-1", "해당 댓글이 존재하지 않습니다.");
		}

		ResultData AuthChkRd = replyService.getAuthChkRd(reply, loginedMember);

		if (AuthChkRd.isFail()) {
			return AuthChkRd;
		}

		return replyService.modifyReply(id, body);
	}
}
