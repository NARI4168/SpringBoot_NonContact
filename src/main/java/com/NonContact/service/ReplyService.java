package com.NonContact.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.NonContact.dao.ReplyDao;
import com.NonContact.dto.Reply;
import com.NonContact.dto.ResultData;

@Service
public class ReplyService {
	@Autowired
	private ReplyDao replyDao;
	@Autowired
	private MemberService memberService;
	
	public List<Reply> getForPrintReplies(String relTypeCode, int relId) {
		
		return replyDao.getForPrintReplies(relTypeCode, relId);
	}

	public Reply getReply(int id) {
		
		return replyDao.getReply(id);
	}

	public ResultData getAuthChkRd(Reply reply, int loginedMemberId) {
		if(reply.getMemberId() == loginedMemberId) {
			return new ResultData("S-1", "사용 권한이 확인 되었습니다.");
		}
		if (memberService.isAdmin(loginedMemberId)) {
			return new ResultData("S-1", "사용 권한이 확인 되었습니다.");
		}
		return new ResultData("F-6", "사용 권한이 없습니다.");
	}

	public ResultData deleteReply(int id) {
		replyDao.deleteReply(id);
		return new ResultData("S-1", "삭제되었습니다.", "id", id);
	}

	public ResultData modifyReply(int id, String body) {
		replyDao.modifyReply(id, body);
		return new ResultData("S-1", "수정되었습니다.", "id", id);
	}

}
