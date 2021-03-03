package com.NonContact.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.NonContact.dao.ReplyDao;
import com.NonContact.dto.Reply;

@Service
public class ReplyService {
	@Autowired
	private ReplyDao replyDao;
	
	public List<Reply> getForPrintReplies(String relTypeCode, int relId) {
		
		return replyDao.getForPrintReplies(relTypeCode, relId);
	}

}
