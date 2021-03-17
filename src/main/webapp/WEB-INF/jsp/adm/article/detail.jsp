<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../part/mainLayoutHead.jspf"%>

<section class="section-1">
	<div class="bg-white shadow-md rounded container mx-auto p-8 mt-8">

		<div class="inline-flex items-center mt-10 px-2 space-x-4 > *">

			<c:choose>
				<c:when test="${article.boardId == 1}">
					<div
						class="px-2 py-1 bg-red-500 text-gray-100 font-bold rounded hover:bg-gray-400">공지</div>
				</c:when>
				<c:otherwise>
					<div
						class="px-2 py-1 bg-purple-400 text-gray-100 font-bold rounded hover:bg-gray-400">자유</div>
				</c:otherwise>
			</c:choose>

			<a class="justify-center text-3xl text-gray-900 font-bold">${article.title}</a>
		</div>

		<div class="flex justify-between items-center mt-5">
			<span class="font-light text-black">${article.regDate}</span>

			<div>
				<a href="#" class="flex items-center"> <img
					src="https://mblogthumb-phinf.pstatic.net/MjAxODAxMTlfMjkg/MDAxNTE2MzQ5ODAyNzY5.JnY2p-t9mGEly1Y6F1Fvvm2udGo5aP_9fHRjDv_v5ikg.o1B5rIWM4SwMD0Oa2BuChU7Cl704DxIBdlLYfAEF4Hwg.JPEG.92_song/%25EC%2599%25B8%25EA%25B5%25AD%25EC%259D%25B8%25EB%25AC%25BC%25EA%25B0%2590%25EC%2584%25B1%25EC%2582%25AC%25EC%25A7%2584_(12).jpg?type=w800"
					alt="avatar" class="mx-4 w-10 h-10 object-cover rounded-full">
					<h1 class="text-gray-700 font-bold hover:underline">${article.writer}</h1>
				</a>
			</div>
		</div>

		<div class="mt-10 px-4">
			<p class="my-5 text-xl text-gray-800">${article.body}</p>

			<div class="mt-10">
				<c:if test="${article.extra__thumbImg != null}">
					<img src="${article.extra__thumbImg}" width="200" height="200"
						alt="" />
				</c:if>
			</div>
		</div>

	</div>
</section>


<%@ include file="../part/mainLayoutFoot.jspf"%>
