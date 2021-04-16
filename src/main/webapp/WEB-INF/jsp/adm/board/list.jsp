<%@ page import="com.NonContact.util.Util"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../part/mainLayoutHead.jspf"%>

<script>
function deleteValue(){
	var url = "/adm/board/doDelete";
	var valueArr = new Array();
	var list = $("input[name = 'id']");
	for(var i=0; i <list.length; i++){
		if(list[i].checked){
			valueArr.push(list[i].value);
		}
	}
	if(valueArr.length == 0){
		alert("선택된 게시판이 없습니다.")
	}else{
		var chk = confirm("해당 게시판을 삭제하시겠습니까?");
		$.ajax({
			url : url,
			type : 'POST',
			traditional : true,
			data : {
				valueArr : valueArr
			},
			success : function(jdata){
				if(jdata=1){
					alert("삭제되었습니다.");
					location.replace("list")
				}else {
					alert("삭제실패");
				}
			}
		});
	}
	
}

function modifyValue(){
	var url = "/adm/board/modify";
	var valueArr = new Array();
	var list = $("input[name = 'id']");
	for(var i=0; i <list.length; i++){
		if(list[i].checked){
			valueArr.push(list[i].value);
		}
	}
	if(valueArr.length == 0){
		alert("선택된 게시판이 없습니다.")
	}else{
		var chk = confirm("해당 게시판을 수정하시겠습니까?");
		$.ajax({
			url : url,
			type : 'POST',
			traditional : true,
			data : {
				valueArr : valueArr
			},
			success : function(jdata){
				if(jdata=1){
					location.href="/adm/board/modify?id="+valueArr;
					
				}else {
					alert("삭제실패");
				}
			}
		});
	}
}


</script>


<section class="section-1">
	<div class="bg-white shadow-md rounded container mx-auto p-8 mt-8">

		<table class="min-w-full mt-3">
			<thead>
				<tr class="border-4 border-gray-800 bg-gray-800 text-gray-300">
					<th class="numeric">No</th>
					<th class="numeric">Code</th>
					<th class="numeric">Name</th>
					<th class="numeric">Number of posts</th>
					<th class="numeric">RegDate</th>
					<th class="numeric"></th>

				</tr>
			</thead>

			<tbody class="text-center">
				<c:forEach items="${boards}" var="board">
					<c:set var="detailUrl" value="../article/list?boardId=${board.id}" />

					<tr class="bg-white border-4 border-gray-200">

						<td data-title="No" class="numeric py-2"><a
							href="${detailUrl}">${board.id}</a></td>

						<td data-title="Code" class="numeric"><a href="${detailUrl}">
								${board.code}</a></td>

						<td data-title="Name" class="numeric hover:underline"><a
							href="${detailUrl}">${board.name}</a></td>

						<td data-title="Number of posts" class="numeric"><a
							href="${detailUrl}">8</a></td>

						<td data-title="RegDate" class="numeric">${board.regDate}</td>


						<td data-title=" " class="numeric"><input type="checkbox"
							name="id" value="${board.id}"></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<div class="flex text-blue-500 justify-end w-full mt-3 py-2">
			<input type="button" value="수정"
				class="bg-transparent ml-2 hover:underline cursor-pointer"
				onclick="modifyValue();"> <input type="button" value="삭제"
				class="bg-transparent ml-2 hover:underline cursor-pointer"
				onclick="deleteValue();">
		</div>

	</div>
</section>

<%@ include file="../part/mainLayoutFoot.jspf"%>
