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

@Controller
public class UsrMemberController {
	@Autowired
	private MemberService memberService;

	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public ResultData doJoin(@RequestParam Map<String, Object> param) {
		if (param.get("loginId") == null) {
			return new ResultData("F-1", "loginId를 입력해주세요.");
		}

		Member validLoginId = memberService.getMemberByLoginId((String) param.get("loginId"));

		if (validLoginId != null) {
			return new ResultData("F-2", String.format("%s (은)는 이미 사용중인 로그인아이디 입니다.", param.get("loginId")));
		}

		if (param.get("loginPw") == null) {
			return new ResultData("F-1", "loginPw를 입력해주세요.");
		}

		if (param.get("name") == null) {
			return new ResultData("F-1", "name을 입력해주세요.");
		}

		if (param.get("nickname") == null) {
			return new ResultData("F-1", "nickname을 입력해주세요.");
		}

		if (param.get("cellphoneNum") == null) {
			return new ResultData("F-1", "cellphoneNum를 입력해주세요.");
		}

		if (param.get("email") == null) {
			return new ResultData("F-1", "email을 입력해주세요.");
		}

		return memberService.join(param);
	}

	@RequestMapping("/usr/member/memberByAuthKey")
	@ResponseBody
	public ResultData memberByAuthKey(String authKey) {
		if (authKey == null) {
			return new ResultData("F-1", "authKey를 입력해주세요.");
		}
		Member searchForLoginId = memberService.getMemberByAuthKey(authKey);
		
		return new ResultData("S-1", String.format("유효한 회원입니다."), "member", searchForLoginId);
	}

	@RequestMapping("/usr/member/authKey")
	@ResponseBody
	public ResultData showAuthKey(String loginId, String loginPw) {
		if (loginId == null) {
			return new ResultData("F-1", "loginId를 입력해주세요.");
		}

		Member searchForLoginId = memberService.getMemberByLoginId(loginId);

		if (searchForLoginId == null) {
			return new ResultData("F-3", "가입되지 않은 아이디입니다.", "loginId", loginId);
		}
		if (loginPw == null) {
			return new ResultData("F-1", "loginPw를 입력해주세요.");
		}

		if (searchForLoginId.getLoginPw().equals(loginPw) == false) {
			return new ResultData("F-4", "잘못된 비밀번호 입니다.");
		}

		return new ResultData("S-1", String.format("%s님 환영합니다.", searchForLoginId.getNickname()), "authKey",
				searchForLoginId.getAuthKey(), "id", searchForLoginId.getId(), "name", searchForLoginId.getName(),
				"nickname", searchForLoginId.getNickname());
	}

	@RequestMapping("/usr/member/login")
	public String login() {

		return "usr/member/login";
	}

	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public ResultData doLogin(String loginId, String loginPw, HttpSession session) {

		if (loginId == null) {
			return new ResultData("F-1", "loginId를 입력해주세요.");
		}

		Member searchForLoginId = memberService.getMemberByLoginId(loginId);

		if (searchForLoginId == null) {
			return new ResultData("F-3", "가입되지 않은 아이디입니다.", "loginId", loginId);
		}

		if (loginPw == null) {
			return new ResultData("F-1", "loginPw를 입력해주세요.");
		}

		if (searchForLoginId.getLoginPw().equals(loginPw) == false) {
			return new ResultData("F-4", "잘못된 비밀번호 입니다.");
		}

		session.setAttribute("loginedMemberId", searchForLoginId.getId());

		return new ResultData("S-2", String.format("%s님 환영합니다.", searchForLoginId.getNickname()));

	}

	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public ResultData doLogout(HttpSession session) {

		session.removeAttribute("loginedMemberId");
		return new ResultData("S-3", String.format("안녕히가세요."));
	}

	@RequestMapping("/usr/member/doModify")
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