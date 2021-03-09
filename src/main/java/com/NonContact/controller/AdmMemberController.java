package com.NonContact.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.NonContact.dto.Member;
import com.NonContact.dto.ResultData;
import com.NonContact.service.MemberService;
import com.NonContact.util.Util;

@Controller
public class AdmMemberController {
	@Autowired
	private MemberService memberService;

	@RequestMapping("/adm/member/login")
	public String login() {

		return "adm/member/login";
	}

	@RequestMapping("/adm/member/doJoin")
	@ResponseBody
	public String doJoin(@RequestParam Map<String, Object> param) {
		if (param.get("loginId") == null) {
			return Util.msgAndBack("loginId를 입력해주세요.");
		}

		Member validLoginId = memberService.getMemberByLoginId((String) param.get("loginId"));

		if (validLoginId != null) {
			return Util.msgAndBack(param.get("loginId") + "(은)는 이미 사용중인 로그인아이디 입니다.");
		}

		if (param.get("loginPw") == null) {
			return Util.msgAndBack("loginPw를 입력해주세요.");
		}

		if (param.get("name") == null) {
			return Util.msgAndBack("name을 입력해주세요.");
		}

		if (param.get("nickname") == null) {
			return Util.msgAndBack("nickname을 입력해주세요.");
		}

		if (param.get("cellphoneNum") == null) {
			return Util.msgAndBack("cellphoneNum를 입력해주세요.");
		}

		if (param.get("email") == null) {
			return Util.msgAndBack("email을 입력해주세요.");
		}

		memberService.join(param);

		String msg = String.format("%s님 환영합니다.", param.get("nickname"));

		return Util.msgAndReplace(msg, "adm/member/doLogin");
	}

	@RequestMapping("/adm/member/doLogin")
	@ResponseBody
	public String doLogin(String loginId, String loginPw, HttpSession session) {

		if (loginId == null) {
			return Util.msgAndBack("loginId를 입력해주세요.");
		}

		Member searchForLoginId = memberService.getMemberByLoginId(loginId);

		if (searchForLoginId == null) {
			return Util.msgAndBack(loginId + "은 가입되지 않은 아이디입니다.");
		}

		if (loginPw == null) {
			return Util.msgAndBack("loginPw를 입력해주세요.");
		}

		if (searchForLoginId.getLoginPw().equals(loginPw) == false) {
			return Util.msgAndBack("잘못된 비밀번호 입니다.");
		}
		if (memberService.isAdmin(searchForLoginId) == false) {
			return Util.msgAndBack("관리자만 이용할 수 있습니다.");
		}

		session.setAttribute("loginedMemberId", searchForLoginId.getId());
		session.setAttribute("loginedMemberNickname", searchForLoginId.getNickname());

		String msg = String.format("%s님 환영합니다.", searchForLoginId.getNickname());

		return Util.msgAndReplace(msg, "../home/main"); 

	}

	@RequestMapping("/adm/member/doLogout")
	@ResponseBody
	public String doLogout(HttpSession session) {
		
		String Nickname = (String)session.getAttribute("loginedMemberNickname");
	
		String msg = String.format(Nickname + "님 안녕히가세요.");
		session.removeAttribute("loginedMemberId");

		return Util.msgAndReplace(msg, "/adm/member/login"); //로그인 화면으로 이동
	}

	@RequestMapping("/adm/member/doModify")
	@ResponseBody
	public ResultData doModify(@RequestParam Map<String, Object> param, HttpServletRequest req) {

		if (param.isEmpty()) {
			return new ResultData("F-1", "수정할 내용을 입력해주세요.");
		}

		int loginedMemberId = (int) req.getAttribute("loginedMemberId");
		param.put("id", loginedMemberId);

		return memberService.modifyMember(param);
	}

}