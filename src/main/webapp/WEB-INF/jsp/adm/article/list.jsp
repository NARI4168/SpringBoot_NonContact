<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../part/mainLayoutHead.jspf"%>

<section class="section-1">
	<div class="bg-white shadow-md rounded container mx-auto p-8 mt-8">


		<div>
			<select class="py-2 border-gray-600 border-2 rounded select-board-id">
				<option value="">게시물 선택</option>
				<option value="0">전제보기</option>
				<option value="1">공지사항</option>
				<option value="2">자유게시판</option>
			</select>
			<script>
				$('.section-1 .select-board-id').val(param.boardId);

				$('.section-1 .select-board-id').change(function() {
					location.href = '?boardId=' + this.value;
				});
			</script>

			<a href="add?"
				class="btn-primary bg-gray-600 rounded hover:bg-gray-400 text-white font-bold py-2.5 px-5">글쓰기</a>
		</div>



		<c:forEach items="${articles}" var="article">
			<c:set var="detailUrl" value="detail?id=${article.id}" />
			<c:set var="thumbFileNo" value="${String.valueOf(1)}" />
			<c:set var="thumbFile"
				value="${article.extra.file__common__attachment[thumbFileNo]}" />
			<c:set var="thumbUrl" value="${thumbFile.getForPrintUrl()}" />

			<div class="flex items-center mt-10 space-x-4 > *">
				<span class="font-light text-gray-600">${article.regDate}</span>

				<div class="flex-grow"></div>
				<c:choose>
					<c:when test="${article.boardId == 1}">
						<a href="list?boardId=${article.boardId}"
							class="px-2 py-1 bg-red-500 text-gray-100 font-bold rounded hover:bg-gray-400">공지</a>
					</c:when>
					<c:otherwise>
						<a href="list?boardId=${article.boardId}"
							class="px-2 py-1 bg-purple-400 text-gray-100 font-bold rounded hover:bg-gray-400">자유</a>
					</c:otherwise>
				</c:choose>
			</div>

			<div class="mt-2">
				<a href="${detailUrl}" class="text-l font-bold">NO.
					${article.id}</a> <a href="${detailUrl}"
					class="text-2xl text-gray-900 font-bold hover:underline p-3">${article.title}</a>
				<div>
					<a href="${detailUrl}" class="mt-5 text-gray-600">${article.body}</a>
					<div>
						<c:if test="${thumbUrl != null}">
							<a class="block  mt-4" href="${detailUrl}"> <img
								src="${thumbUrl}" width="50" height="50" alt="" /></a>
						</c:if>
					</div>
						

				<div class="flex items-center mt-4">
					<a href="detail?id=${article.id}"
						class="text-blue-500 hover:underline">자세히 보기</a> <a
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
				</div>
				</div>
				</div>
		</c:forEach>
	</div>
	

</section>
<!-- 
<section class="page">
	<div class="bg-white shadow-md rounded container mx-auto p-5 mt-8">


		<div class="flex justify-center items-center">

			<c:set var="pageSize" value="5" />


			<c:set var="from" value="1" />
			<c:set var="end" value="${pageSize}" />


			<a class="hover:underline" href="#"> ≪ </a> &nbsp;&nbsp;



			<c:forEach var="i" begin="${from}" end="${end}" step="1">

				<a class="hover:underline" href="list?page=${i}">${i}</a> &nbsp;&nbsp;
						
		</c:forEach>



			<a class="hover:underline" href="#">≫</a>

		</div>


	</div>
</section>-->
<%@ include file="../part/mainLayoutFoot.jspf"%>
