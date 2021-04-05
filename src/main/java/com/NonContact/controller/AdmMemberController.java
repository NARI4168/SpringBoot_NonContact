package com.NonContact.controller;

import java.util.List;
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
public class AdmMemberController extends BaseController {
	@Autowired
	private MemberService memberService;

	@RequestMapping("/adm/member/detail")
	public String showDetail(HttpServletRequest req, int id) {

		Member member = memberService.getMember(id);

		if (member == null) {
			return Util.msgAndBack("해당 회원이 존재하지 않습니다.");
		}

		req.setAttribute("member", member);

		return "adm/member/detail";
	}

	@RequestMapping("/adm/member/list")
	public String showList(HttpServletRequest req, @RequestParam(defaultValue = "0") int authLevel,
			String searchKeywordType, String searchKeyword, @RequestParam(defaultValue = "1") int page,
			@RequestParam Map<String, Object> param) {

		if (authLevel != 0) {
			List<Member> member = memberService.getMemberByAuthLevel(authLevel);
		}

		if (searchKeywordType != null) {
			searchKeywordType = searchKeywordType.trim();
		}

		if (searchKeywordType == null || searchKeywordType.length() == 0) {
			searchKeywordType = "name";
		}

		if (searchKeyword != null && searchKeyword.length() == 0) {
			searchKeyword = null;
		}

		if (searchKeyword != null) {
			searchKeyword = searchKeyword.trim();
		}

		if (searchKeyword == null) {
			searchKeywordType = null;
		}

		int itemsInAPage = 3;

		List<Member> members = memberService.getForPrintMembers(authLevel, searchKeywordType, searchKeyword, page,
				itemsInAPage, param);

		req.setAttribute("members", members);

		return "adm/member/list";
	}

	@RequestMapping("/adm/member/login")
	public String login() {
		return "adm/member/login";
	}

	@RequestMapping("/adm/member/join")
	public String Join() {
		return "adm/member/join";
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
	public String doLogin(String loginId, String loginPw, String redirectUrl, HttpSession session) {

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

		redirectUrl = Util.ifEmpty(redirectUrl, "../home/main");

		return Util.msgAndReplace(msg, redirectUrl);

	}

	@RequestMapping("/adm/member/doLogout")
	@ResponseBody
	public String doLogout(HttpSession session) {

		String Nickname = (String) session.getAttribute("loginedMemberNickname");

		String msg = String.format(Nickname + "님 안녕히가세요.");
		session.removeAttribute("loginedMemberId");

		return Util.msgAndReplace(msg, "../member/login"); // 로그인 화면으로 이동
	}

	@RequestMapping("/adm/member/modify")
	public String showModify(Integer id, HttpServletRequest req) {
		if (id == null) {
			return msgAndBack(req, "id를 입력해주세요.");
		}

		Member member = memberService.getForPrintMember(id);

		req.setAttribute("member", member);

		if (member == null) {
			return msgAndBack(req, "존재하지 않는 회원번호 입니다.");
		}

		return "adm/member/modify";
	}

	@RequestMapping("/adm/member/doModify")
	@ResponseBody
	public String doModify(@RequestParam Map<String, Object> param, HttpServletRequest req) {

		Member loginedMember = (Member) req.getAttribute("loginedMember");
		int id = Util.getAsInt(param.get("id"), 0);
		Member member = memberService.getMember(id);

		if (param.isEmpty()) {
			return msgAndBack(req, "수정할 정보를 입력해주세요.");
		}
		if (member == null) {
			return msgAndBack(req, "해당 회원이 존재하지 않습니다.");
		} else {
			id = member.getId();
		}

		ResultData AuthChkRd = memberService.getAuthChkRd(member, loginedMember);

		if (AuthChkRd.isFail()) {
			return msgAndBack(req, "권한이 없습니다.");
		}

		memberService.modifyMember(param);

		String msg = String.format("%d번 회원정보가 수정되었습니다.", id);

		return Util.msgAndReplace(msg, "../member/detail?id=" + id);

	}

	@RequestMapping("/adm/member/doDelete")
	public String doDelete(Integer id, HttpServletRequest req) {

		Member loginedMember = (Member) req.getAttribute("loginedMember");

		Member member = memberService.getMember(id);

		if (member == null) {
			return msgAndBack(req, "해당 회원이 존재하지 않습니다.");
		}

		ResultData AuthChkRd = memberService.getAuthChkRd(member, loginedMember);

		if (AuthChkRd.isFail()) {
			return msgAndBack(req, "권한이 없습니다.");
		}

		memberService.deleteMember(id);
		
		return msgAndReplace(req, String.format("%d번 게시물이 삭제되었습니다.", id), "../member/list");
	}

}