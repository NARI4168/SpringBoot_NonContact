<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../part/mainLayoutHead.jspf"%>


<script>
	function getBoardId() { //게시판종류 번호 얻는 함수
		i = document.join.boardId.selectedIndex // 선택항목의 인덱스 번호
		var board = document.join.boardId.options[i].value // 선택항목 value
		document.join.BoardId.value = board
	}
	
</script>

<section class="section-1">
	<div class="bg-white shadow-md rounded container mx-auto p-8 mt-8">

		<form action="doAdd" method="POST">


			<div class="form-row flex flex-col lg:flex-row">
				<div class="lg:flex lg:items-center lg:w-28">
					<span>게시판 선택</span>
				</div>
				<select name="boardId" onChange="getBoardId"
					class="py-2 border-gray-600 border-2 rounded" required>
					<option value="">선택</option>
					<option value="1">공지사항</option>
					<option value="2">자유게시판</option>
				</select>			
						<input type="hidden" name="boardId" value="BoardId" />				
			</div>


			<div class="form-row flex flex-col lg:flex-row">
				<div class="lg:flex lg:items-center lg:w-28">
					<span>제목</span>
				</div>
				<div class="lg:flex-grow">
					<input type="text" name="title" autofocus="autofocus"
						class="form-row-input w-full rounded-sm" placeholder="제목을 입력해주세요." required />
				</div>
			</div>

			<div class="form-row flex flex-col lg:flex-row">
				<div class="lg:flex lg:items-center lg:w-28">
					<span>내용</span>
				</div>
				<div class="lg:flex-grow">
					<textarea name="body" class="form-row-input w-full rounded-sm"
						placeholder="내용을 입력해주세요." required ></textarea>
				</div>
			</div>

			<div class="form-row flex flex-col lg:flex-row">
				<div class="lg:flex lg:items-center lg:w-28">
					<span>작성</span>
				</div>
				<div class="lg:flex-grow">
					<div class="btns">
						<input type="submit"
							class="btn-primary bg-blue-500 hover:bg-blue-400 text-white font-bold py-2 px-4 rounded"
							value="작성"> <input onclick="history.back();"
							type="button"
							class="btn-info bg-red-500 hover:bg-red-400 text-white font-bold py-2 px-4 rounded"
							value="취소">
					</div>
				</div>
			</div>
		</form>
	</div>
</section>

<%@ include file="../part/mainLayoutFoot.jspf"%>
