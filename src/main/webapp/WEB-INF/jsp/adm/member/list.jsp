<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../part/mainLayoutHead.jspf"%>

<section class="section-1">
	<div class="bg-white shadow-md rounded container mx-auto p-8 mt-8">

		<div>
			<select class="py-2 border-gray-600 border-2 rounded select-board-id">
				<option value="">회원 선택</option>
				<option value="0">전제보기</option>
				<option value="3">일반회원</option>
				<option value="7">관리자</option>
			</select>
			<script>
				$('.section-1 .select-member-authLevel').val(param.boardId);

				$('.section-1 .select-member-authLevel').change(function() {
					location.href = '?authLevel=' + this.value;
				});
			</script>
		</div>



		<!-- component -->
		<div>
			<table class="min-w-full mt-3">
				<thead class="justify-between">
					<tr class="bg-gray-800">
						<th class="px-16 py-2"><span class="text-gray-300">No</span>
						</th>
						<th class="px-16 py-2"><span class="text-gray-300">MemberLevel</span></th>
						<th class="px-16 py-2"><span class="text-gray-300">ProfileImage</span></th>
						<th class="px-16 py-2"><span class="text-gray-300">ID</span>
						</th>
						<th class="px-16 py-2"><span class="text-gray-300">NickName</span>
						</th>
						<th class="px-16 py-2"><span class="text-gray-300">SignUpDate</span></th>
					</tr>
				</thead>

				<tbody class="justify-between bg-gray-200">
					<c:forEach items="${members}" var="member">
						<c:set var="detailUrl" value="member?id=${member.id}" />

						<tr class="bg-white border-4 border-gray-200">
						
							<td class="px-16 py-2"><a
								href="${detailUrl}"> ${member.id}</a></td>
							<td class="px-16 py-2">
							<c:choose>
							<c:when test="${member.authLevel eq '7'}" >
							<a class="px-2 py-1 bg-red-500 text-gray-100 font-bold rounded">관리자</a>
							</c:when>						
							<c:otherwise>
						<a class="px-2 py-1 bg-purple-400 text-gray-100 font-bold rounded">일반회원</a>
					</c:otherwise>										
							</c:choose>
							</td>
							
							<td class="px-16 py-2"><a href="${detailUrl}"> <img
									class="h-8 w-8 rounded-full object-cover "
									src="https://img.hankyung.com/photo/201903/AA.19067065.1.jpg" alt="" />
							</a></td>
							<td class="px-16 py-2 "><a
								href="${detailUrl}"> ${member.loginId}</a></td>
							<td class="px-16 py-2 "><a
								href="${detailUrl}"> ${member.nickname}</a></td>
							<td class="px-16 py-2 "><a
								href="${detailUrl}"> ${member.regDate}</a></td>
						</tr>
					</c:forEach>

				
				
				</tbody>
			</table>
		</div>





	</div>
</section>


<%@ include file="../part/mainLayoutFoot.jspf"%>