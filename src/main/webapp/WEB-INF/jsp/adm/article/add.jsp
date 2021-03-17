<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../part/mainLayoutHead.jspf"%>

<c:set var="fileInputMaxCount" value="5" />
<script>
	ArticleAdd__fileInputMaxCount = parseInt("${fileInputMaxCount}");
</script>

<script>

	ArticleAdd__submited = false;
	function ArticleAdd__checkAndSubmit(form) {
		if (ArticleAdd__submited) {
			alert('처리중입니다.');
			return;
		}

		form.title.value = form.title.value.trim();
		if (form.title.value.length == 0) {
			alert('제목을 입력해주세요.');
			form.title.focus();
			return false;
		}
		form.body.value = form.body.value.trim();
		if (form.body.value.length == 0) {
			alert('내용을 입력해주세요.');
			form.body.focus();
			return false;
		}
		var maxSizeMb = 50;
		var maxSize = maxSizeMb * 1024 * 1024;

		for (let inputNo = 1; inputNo <= ArticleAdd__fileInputMaxCount; inputNo++) {
			const input = form["file__article__0__common__attachment__"
					+ inputNo];

			if (input.value) {
				if (input.files[0].size > maxSize) {
					alert(maxSizeMb + "MB 이하의 파일을 업로드 해주세요.");
					input.focus();

					return;
				}
			}
		}

		const startSubmitForm = function(data) {
		//	let genFileIdsStr = '';
              
			if (data && data.body && data.body.genFileIdsStr) {
				form.genFileIdsStr.value = data.body.genFileIdsStr;
			}

			for (let inputNo = 1; inputNo <= ArticleAdd__fileInputMaxCount; inputNo++) {
				const input = form["file__article__0__common__attachment__"
						+ inputNo];
				input.value = '';
			}

			form.submit();
		};
		
		const startUploadFiles = function(onSuccess) {

			var needToUpload = false;

			for (let inputNo = 1; inputNo <= ArticleAdd__fileInputMaxCount; inputNo++) {
				const input = form["file__article__0__common__attachment__"
						+ inputNo];
				if (input.value.length > 0) {
					needToUpload = true;
					break;
				}
			}

			if (needToUpload == false) {
				onSuccess();
				return;
			}

			var fileUploadFormData = new FormData(form);

			$.ajax({
				url : '/common/genFile/doUpload',
				data : fileUploadFormData,
				processData : false,
				contentType : false,
				dataType : "json",
				type : 'POST',
				success : onSuccess
			});
		}
		ArticleAdd__submited = true;
		startUploadFiles(startSubmitForm);
	}
</script>
<script>
	function getBoardId() { //게시판종류 번호 얻는 함수
		i = document.join.boardId.selectedIndex // 선택항목의 인덱스 번호
		var board = document.join.boardId.options[i].value // 선택항목 value
		document.join.BoardId.value = board
	}
	</script>

<section class="section-1">
	<div class="bg-white shadow-md rounded container mx-auto p-8 mt-8">

		<form action="doAdd" method="POST" enctype="multipart/form-data"
			onsubmit="ArticleAdd__checkAndSubmit(this); return false;">
			<input type="hidden" name="genFileIdsStr" value="" />

			<div class="form-row flex flex-col lg:flex-row">
				<div class="lg:flex lg:items-center lg:w-28">
					<span>게시판 선택</span>
				</div>
				<select name="boardId" onChange="getBoardId"
					class="py-2 border-gray-600 border-2 rounded" required>
					<option value="">선택</option>
					<option value="1">공지사항</option>
					<option value="2">자유게시판</option>
				</select> <input type="hidden" name="boardId" value="BoardId" />
			</div>

			<div class="form-row flex flex-col lg:flex-row">
				<div class="lg:flex lg:items-center lg:w-28">
					<span>제목</span>
				</div>
				<div class="lg:flex-grow">
					<input type="text" name="title" autofocus="autofocus"
						class="form-row-input w-full rounded-sm" placeholder="제목을 입력해주세요."
						required />
				</div>
			</div>

			<div class="form-row flex flex-col lg:flex-row">
				<div class="lg:flex lg:items-center lg:w-28">
					<span>내용</span>
				</div>
				<div class="lg:flex-grow">
					<textarea name="body" class="form-row-input w-full rounded-sm"
						placeholder="내용을 입력해주세요." required></textarea>
				</div>
			</div>

			<c:forEach begin="1" end="${fileInputMaxCount}" var="inputNo">
				<div class="form-row flex flex-col lg:flex-row">
					<div class="lg:flex lg:items-center lg:w-28">
						<span>첨부파일 ${inputNo}</span>
					</div>
					<div class="lg:flex-grow">
						<input type="file"
							name="file__article__0__common__attachment__${inputNo}"
							class="form-row-input w-full rounded-sm" />
					</div>
				</div>
			</c:forEach>

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
