<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.NonContact.util.Util"%>

<%@ include file="../part/mainLayoutHead.jspf"%>
<
<script>
	BoardModify__submited = false;
	function BoardModify__checkAndSubmit(form) {
		if (BoardModify__submited) {
			alert('처리중입니다.');
			return;
		}
		
		form.code.value = form.code.value.trim();
		
		if (form.code.value.length == 0) {
			alert('코드를 입력해주세요.');
			form.code.focus();
			return false;
		}
		
		form.name.value = form.name.value.trim();
		
		if (form.name.value.length == 0) {
			alert('내용을 입력해주세요.');
			form.name.focus();
			return false;
		}

		BoardModify__submited = true;
		form.submit();
	
	}
</script>

<section class="section-1">
	<div class="bg-white shadow-md rounded container mx-auto p-8 mt-8">

		<form action="doModify" method="POST"
			onsubmit="BoardModify__checkAndSubmit(this); return false;">
		<input	type="hidden" name="id" value="${board.id}" /> 
				
			<div class="form-row flex flex-col lg:flex-row">
				<div class="lg:flex lg:items-center lg:w-28">
					<span>게시판 코드</span>
				</div>
				<div class="lg:flex-grow boder-3">
					<input value="${board.code}" type="text" name="code"
						autofocus="autofocus" class="form-row-input w-full rounded-sm border-2" />
				</div>
			</div>
			

			<div class="form-row flex flex-col lg:flex-row">
				<div class="lg:flex lg:items-center lg:w-28">
					<span>게시판 이름</span>
				</div>
				<div class="lg:flex-grow">
					<textarea name="name" class="form-row-input w-full rounded-sm border-2"
						>${board.name}</textarea>
				</div>
			</div>
		

			<div class="form-row flex flex-col lg:flex-row">
				<div class="lg:flex lg:items-center lg:w-28">
					<span>수정</span>
				</div>
				<div class="lg:flex-grow">
					<div class="btns">
						<input type="submit"
							class="btn-primary bg-blue-500 hover:bg-blue-dark text-white font-bold py-2 px-4 rounded"
							value="수정"> <input onclick="history.back();"
							type="button"
							class="btn-info bg-red-500 hover:bg-red-dark text-white font-bold py-2 px-4 rounded"
							value="취소">
					</div>
				</div>
			</div>
		</form>
	</div>
</section>

<%@ include file="../part/mainLayoutFoot.jspf"%>