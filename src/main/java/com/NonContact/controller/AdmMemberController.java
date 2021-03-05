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
public class AdmMemberController {
	@Autowired
	private MemberService memberService;

	@RequestMapping("/adm/member/login")
	public String login() {

		return "adm/member/login";
	}

	@RequestMapping("/adm/member/doJoin")
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

	@RequestMapping("/adm/member/doLogin")
	@ResponseBody
	public ResultData doLogin(String loginId, String loginPw, HttpSession session) {

		if (loginId == null) {
			return new ResultData("F-1", "loginId를 입력해주세요.");
		}

		Member searchForLoginId = memberService.getMemberByLoginId(loginId);

		if (searchForLoginId == null) {
			return new ResultData("F-3", "가입되지 않은 아이디입니다.","loginId",loginId);
		}

		if (loginPw == null) {
			return new ResultData("F-1", "loginPw를 입력해주세요.");
		}

		if (searchForLoginId.getLoginPw().equals(loginPw) == false) {
			return new ResultData("F-4", "잘못된 비밀번호 입니다.");
		}
		if (memberService.isAdmin(searchForLoginId) == false) {
			return new ResultData("F-4", "관리자만 이용할 수 있습니다.");
		}

		session.setAttribute("loginedMemberId", searchForLoginId.getId());

		return new ResultData("S-2", String.format("%s님 환영합니다.", searchForLoginId.getNickname()));

	}

	@RequestMapping("/adm/member/doLogout")
	@ResponseBody
	public ResultData doLogout(HttpSession session) {

		session.removeAttribute("loginedMemberId");
		return new ResultData("S-3", String.format("안녕히가세요."));
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