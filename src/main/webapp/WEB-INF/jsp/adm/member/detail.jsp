<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../part/mainLayoutHead.jspf"%>

<section class="section-1">
	<div
		class="bg-white shadow-md rounded container content-center text-center mx-auto p-8 mt-8">

		<div class="flex flex-col items-center justify-center">

			<div class="place-items-center">
			<img
				class="rounded-full object-cover"
				alt="User avatar" width="150" height="150" src="${member.memberThumbImgUrl}"/>
	
			</div>


			<table class="mt-8 text-xl">

				<tr>
					<th scope="row">회원등급</th>
					<td><c:choose>
							<c:when test="${member.authLevel == 7}">
					관리자
							</c:when>
							<c:otherwise>
					일반회원
				</c:otherwise>
						</c:choose></td>
				</tr>
				<tr>
					<th scope="row">ID</th>
					<td>${member.loginId}</td>
				</tr>
				<tr>
					<th scope="row">PassWord</th>
					<td>${member.loginPw}</td>
				</tr>
				<tr>
					<th scope="row">Name</th>
					<td>${member.name}</td>
				</tr>
				<tr>
					<th scope="row">NickName</th>
					<td>${member.nickname}</td>
				</tr>
				<tr>
					<th scope="row">CellphoneNum</th>
					<td>${member.cellphoneNum}</td>
				</tr>
				<tr>
					<th scope="row">Email</th>
					<td>${member.email}</td>
				</tr>
				<tr>
					<th scope="row">SignUp Date</th>
					<td>${member.regDate}</td>
				</tr>
				<tr>
					<th scope="row">UpdateDate</th>
					<td>${member.updateDate}</td>
				</tr>
			</table>
		</div>


		<div class="mt-8">

			<a href="modify?id=${member.id}"
				class="ml-2 text-blue-500 hover:underline">수정</a> <a
				onclick="if ( !confirm('삭제하시겠습니까?') ) return false;"
				href="doDelete?id=${member.id}"
				class="ml-2 text-blue-500 hover:underline">삭제</a> <a
				onclick="history.back();"
				class="ml-2 text-blue-500 hover:underline cursor-pointer">뒤로가기</a>

		</div>


	</div>
</section>


<%@ include file="../part/mainLayoutFoot.jspf"%>
