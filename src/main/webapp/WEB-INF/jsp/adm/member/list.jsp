<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../part/mainLayoutHead.jspf"%>

<section class="section-1">
	<div class="bg-white shadow-md rounded container mx-auto p-8 mt-8">

		<div>
			<select class="py-2 border-gray-600 border-2 rounded select-member-authLevel">
				<option value="">회원 선택</option>
				<option value="0">전제보기</option>
				<option value="3">일반회원</option>
				<option value="7">관리자</option>
			</select>
			<script>
				$('.section-1 .select-member-authLevel').val(param.authLevel);

				$('.section-1 .select-member-authLevel').change(function() {
					location.href = '?authLevel=' + this.value;
				});
			</script>
		</div>

		<!-- component-->

		<table class="min-w-full mt-3">
			<thead>
				<tr class="border-4 border-gray-800 bg-gray-800 text-gray-300">
					<th class="numeric">No</th>
					<th class="numeric">Member Level</th>
					<th class="numeric">Profile Image</th>
					<th class="numeric">ID</th>
					<th class="numeric">NickName</th>
					<th class="numeric">SignUp Date</th>
				</tr>
			</thead>

			<tbody class="text-center">
				<c:forEach items="${members}" var="member">
					<c:set var="detailUrl" value="detail?id=${member.id}" />

					<tr class="bg-white border-4 border-gray-200">

						<td data-title="No" class="numeric py-2"><a href="${detailUrl}">${member.id}</a></td>
						<td data-title="Member Level" class="numeric"><c:choose>
								<c:when test="${member.authLevel eq '7'}">
									<a class="px-2 py-1 bg-red-500 text-gray-100 font-bold rounded">관리자</a>
								</c:when>
								<c:otherwise>
									<a
										class="px-2 py-1 bg-purple-400 text-gray-100 font-bold rounded">일반회원</a>
								</c:otherwise>
							</c:choose></td>

						<td data-title="Profile Image" class="numeric"><a
							href="${detailUrl}"><img
								class="h-8 w-8 rounded-full object-cover"
								src="https://img.hankyung.com/photo/201903/AA.19067065.1.jpg"
								alt="" />
						</a></td>
						<td data-title="ID" class="numeric hover:underline"><a
							href="${detailUrl}"> ${member.loginId}</a></td>
						<td data-title="NickName" class="numeric hover:underline"><a
							href="${detailUrl}">${member.nickname}</a></td>
						<td data-title="SignUp Date" class="numeric">${member.regDate}</td>
					</tr>
				</c:forEach>



			</tbody>
		</table>






	</div>
</section>


<%@ include file="../part/mainLayoutFoot.jspf"%>