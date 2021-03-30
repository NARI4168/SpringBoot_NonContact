<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../part/mainLayoutHead.jspf"%>

<section class="section-1">
	<div
		class="bg-white shadow-md rounded container text-center mx-auto p-8 mt-8">

		<div class="items-center">


			<div>
				<a href="#"><img class="h-8 w-8 rounded-full object-cover"
					src="https://img.hankyung.com/photo/201903/AA.19067065.1.jpg"
					alt="" /> </a>
			</div>


			<table>

				<tr>
					<th scope="row">Profile</th>
					<td>이미지</td>
				</tr>

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
		<!-- 	<div class="flex justify-between items-center mt-5">

			<span class="font-light text-black">${article.regDate}</span>
			<a
				href="modify?id=${article.id}"
				class="ml-2 text-blue-500 hover:underline">수정</a> <a
				onclick="if ( !confirm('삭제하시겠습니까?') ) return false;"
				href="doDelete?id=${article.id}"
				class="ml-2 text-blue-500 hover:underline">삭제</a>
			<div class="flex-grow"></div>
			<div>
				<a href="#" class="flex items-center"> <img
					src="https://mblogthumb-phinf.pstatic.net/MjAxODAxMTlfMjkg/MDAxNTE2MzQ5ODAyNzY5.JnY2p-t9mGEly1Y6F1Fvvm2udGo5aP_9fHRjDv_v5ikg.o1B5rIWM4SwMD0Oa2BuChU7Cl704DxIBdlLYfAEF4Hwg.JPEG.92_song/%25EC%2599%25B8%25EA%25B5%25AD%25EC%259D%25B8%25EB%25AC%25BC%25EA%25B0%2590%25EC%2584%25B1%25EC%2582%25AC%25EC%25A7%2584_(12).jpg?type=w800"
					alt="avatar" class="mx-4 w-10 h-10 object-cover rounded-full">
					<h1 class="text-gray-700 font-bold hover:underline">${article.writer}</h1>
				</a>
			</div>
		</div>-->
		<!-- 
		<div class="mt-10 px-4">
			<p class="my-5 text-xl text-gray-800">${article.body}</p>

			<div class="mt-10">
				<c:if test="${article.extra__thumbImg != null}">
				<a class="inline-block" href="${article.extra__thumbImg}" target="_blank" title="자세히 보기">
					<img  class="max-w-sm" src="${article.extra__thumbImg}" width="200" height="200"
						alt="" /></a>
				</c:if>
			</div>
		</div>-->

	</div>
</section>


<%@ include file="../part/mainLayoutFoot.jspf"%>
