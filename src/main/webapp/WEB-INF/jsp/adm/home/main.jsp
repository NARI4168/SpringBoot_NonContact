<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../part/head.jspf"%>



<div class="flex flex-col items-center justify-center h-screen text-xl">


	<a href="#1"
		class="text-gray-700 font-bold py-3 px-6 hover:text-red-600"><i class="fas fa-users"> 회원 관리</i>
		 </a> 
	<a href="#2"
		class="text-gray-700 font-bold py-3 px-6 hover:text-red-600"><i class="fas fa-clipboard-list"> 게시판
		관리 </i></a> 
	<a href="#3"
		class="text-gray-700 font-bold py-3 px-6 hover:text-red-600"><i class="fas fa-pen-nib"> 게시글
		관리 </i></a> 
	<a href="../member/doLogout"
		class="text-gray-700 font-bold py-3 px-6 hover:text-red-600"><i class="fas fa-sign-out-alt"> 로그아웃
	</i></a>


</div>


<%@ include file="../part/foot.jspf"%>