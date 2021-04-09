<%@ page import="com.NonContact.util.Util"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%@ include file="../part/mainLayoutHead.jspf"%>

<c:set var="fileInputMaxCount" value="10" />

<section class="section-1">
	<div class="bg-white shadow-md rounded container mx-auto p-8 mt-8">
		<div class="w-full">
			<div class="flex flex-row mt-2 py-3">
				<div class="rounded-full border-2 border-pink-500">
					<img
						class="w-12 h-12 object-cover rounded-full shadow cursor-pointer"
						alt="User avatar" src="${article.writerThumbImgUrl}">
				</div>
				<div class="flex flex-col mb-2 ml-4 mt-1">
					<div class="text-gray-600 text-sm font-semibold">${article.writer}</div>
					<div class="flex w-full mt-1">
						<a href="#"
							class="text-blue-700 font-base text-xs mr-1 cursor-pointer">
							${article.extra__boardName} </router-link>
							<div class="text-gray-400 font-thin text-xs">${article.regDate}</div>
					</div>
				</div>
			</div>
			<div class="text-gray-600 font-semibold text-lg mb-2">${article.title}</div>
			<div class="text-gray-500 font-thin text-sm mb-6">${article.body}</div>
			<div class="border-b border-gray-100"></div>
			<div class="text-gray-400 font-medium text-sm mb-7 mt-6">
				<c:forEach begin="1" end="${fileInputMaxCount}" var="inputNo">
					<c:set var="fileNo" value="${String.valueOf(inputNo)}" />
					<c:set var="file"
						value="${article.extra.file__common__attachment[fileNo]}"/>
					${file.mediaHtml}
				</c:forEach>
			</div>
			<div class="flex justify-start mb-4 border-t border-gray-100">
				<div class="flex w-full mt-1 pt-2">
					<span
						class="bg-white transition ease-out duration-300 hover:text-red-500 border w-8 h-8 px-2 pt-2 text-center rounded-full text-gray-400 cursor-pointer mr-2">
						<svg xmlns="http://www.w3.org/2000/svg" fill="none" width="14px"
							viewBox="0 0 24 24" stroke="currentColor">
                    <path stroke-linecap="round" stroke-linejoin="round"
								stroke-width="2"
								d="M5 5a2 2 0 012-2h10a2 2 0 012 2v16l-7-3.5L5 21V5z"></path>
                  </svg>
					</span> <img
						class="inline-block object-cover w-8 h-8 text-white border-2 border-white rounded-full shadow-sm cursor-pointer"
						src="https://images.unsplash.com/photo-1491528323818-fdd1faba62cc?ixlib=rb-1.2.1&amp;ixid=eyJhcHBfaWQiOjEyMDd9&amp;auto=format&amp;fit=facearea&amp;facepad=2&amp;w=256&amp;h=256&amp;q=80"
						alt=""> <img
						class="inline-block object-cover w-8 h-8 -ml-2 text-white border-2 border-white rounded-full shadow-sm cursor-pointer"
						src="https://images.unsplash.com/photo-1550525811-e5869dd03032?ixlib=rb-1.2.1&amp;auto=format&amp;fit=facearea&amp;facepad=2&amp;w=256&amp;h=256&amp;q=80"
						alt=""> <img
						class="inline-block object-cover w-8 h-8 -ml-2 text-white border-2 border-white rounded-full shadow-sm cursor-pointer"
						src="https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-1.2.1&amp;ixid=eyJhcHBfaWQiOjEyMDd9&amp;auto=format&amp;fit=crop&amp;w=634&amp;q=80"
						alt=""> <img
						class="inline-block object-cover w-8 h-8 -ml-2 text-white border-2 border-white rounded-full shadow-sm cursor-pointer"
						src="https://images.unsplash.com/photo-1500648767791-00dcc994a43e?ixlib=rb-1.2.1&amp;ixid=eyJhcHBfaWQiOjEyMDd9&amp;auto=format&amp;fit=facearea&amp;facepad=2.25&amp;w=256&amp;h=256&amp;q=80"
						alt="">
				</div>
				<div class="flex justify-end w-full mt-1 pt-2">
					<span
						class="transition ease-out duration-300 hover:bg-blue-50 bg-blue-100 h-8 px-2 py-2 text-center rounded-full text-blue-400 cursor-pointer mr-2">
						<svg xmlns="http://www.w3.org/2000/svg" fill="none" width="14px"
							viewBox="0 0 24 24" stroke="currentColor">
                    <path stroke-linecap="round" stroke-linejoin="round"
								stroke-width="2"
								d="M8.684 13.342C8.886 12.938 9 12.482 9 12c0-.482-.114-.938-.316-1.342m0 2.684a3 3 0 110-2.684m0 2.684l6.632 3.316m-6.632-6l6.632-3.316m0 0a3 3 0 105.367-2.684 3 3 0 00-5.367 2.684zm0 9.316a3 3 0 105.368 2.684 3 3 0 00-5.368-2.684z"></path>
                  </svg>
					</span> <span
						class="transition ease-out duration-300 hover:bg-blue-500 bg-blue-600 h-8 px-2 py-2 text-center rounded-full text-gray-100 cursor-pointer">
						<svg xmlns="http://www.w3.org/2000/svg" fill="none" width="14px"
							viewBox="0 0 24 24" stroke="currentColor">
                    <path stroke-linecap="round" stroke-linejoin="round"
								stroke-width="2"
								d="M4 16v1a3 3 0 003 3h10a3 3 0 003-3v-1m-4-4l-4 4m0 0l-4-4m4 4V4"></path>
                  </svg>
					</span>
				</div>
			</div>
			<div class="flex w-full border-t border-gray-100">
				<div class="mt-3 flex flex-row">
					<div
						class="flex text-gray-700 font-normal text-sm rounded-md mb-2 mr-4 items-center whitespace-nowrap">
						댓글:
						<div class="ml-1 text-gray-400 font-thin text-ms">30</div>
					</div>
					<div
						class="flex text-gray-700 font-normal text-sm rounded-md mb-2 mr-4 items-center whitespace-nowrap">
						조회:
						<div class="ml-1 text-gray-400 font-thin text-ms">60k</div>
					</div>
				</div>
				<div class="mt-3 w-full flex justify-end">
					<div
						class="flex text-gray-700 font-normal text-sm rounded-md mb-2 items-center whitespace-nowrap">
						좋아요:
						<div class="ml-1 text-gray-400 font-thin text-ms">120k</div>
					</div>
				</div>
			</div>
			<div
				class="relative flex items-center self-center w-full max-w-xl py-4 text-gray-600 focus-within:text-gray-400">
				<img
					class="w-10 h-10 object-cover rounded-full shadow mr-2 cursor-pointer"
					alt="User avatar"
					src="https://images.unsplash.com/photo-1477118476589-bff2c5c4cfbb?ixlib=rb-1.2.1&amp;ixid=eyJhcHBfaWQiOjEyMDd9&amp;auto=format&amp;fit=crop&amp;w=200&amp;q=200">
				<span class="absolute inset-y-0 right-0 flex items-center pr-6">
					<button type="submit"
						class="p-1 focus:outline-none focus:shadow-none hover:text-blue-500">
						<svg
							class="w-6 h-6 transition ease-out duration-300 hover:text-blue-500 text-gray-400"
							xmlns="http://www.w3.org/2000/svg" fill="none"
							viewBox="0 0 24 24" stroke="currentColor">
                    <path stroke-linecap="round" stroke-linejoin="round"
								stroke-width="2"
								d="M14.828 14.828a4 4 0 01-5.656 0M9 10h.01M15 10h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"></path>
                  </svg>

					</button>
				</span> <input type="search"
					class="w-full py-2 pl-4 pr-10 text-sm bg-gray-100 border border-transparent appearance-none rounded-tg placeholder-gray-400 focus:bg-white focus:outline-none focus:border-blue-500 focus:text-gray-900 focus:shadow-outline-blue"
					style="border-radius: 25px" placeholder="댓글을 입력해주세요."
					autocomplete="off">
			</div>
		</div>
	</div>

</section>





<!--
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
			<a class="text-xl text-gray-900 font-bold">NO. ${article.id}</a>

			<a class=" text-3xl text-gray-900 font-bold">${article.title}</a>
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
				<a class="inline-block" href="${article.extra__thumbImg}" target="_blank" title="자세히 보기">
					<img  class="max-w-sm" src="${article.extra__thumbImg}" width="200" height="200"
						alt="" /></a>
				</c:if>
			</div>
		</div>
			 <a
				href="modify?id=${article.id}"
				class="ml-2 text-blue-500 hover:underline">수정</a> <a
				onclick="if ( !confirm('삭제하시겠습니까?') ) return false;"
				href="doDelete?id=${article.id}"
				class="ml-2 text-blue-500 hover:underline">삭제</a>
			


	</div>
</section>
-->

<%@ include file="../part/mainLayoutFoot.jspf"%>
