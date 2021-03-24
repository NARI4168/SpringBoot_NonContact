<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../part/head.jspf"%>


<script>
	const JoinForm__checkAndSubmitDone = false;
	function JoinForm__checkAndSubmit(form) {
		if (JoinForm__checkAndSubmitDone) {
			return;
		}

		form.loginId.value = form.loginId.value.trim();

		if (form.loginId.value.length == 0) {
			alert('아이디를 입력해주세요.');
			form.loginId.focus();

			return;
		}

		form.loginPw.value = form.loginPw.value.trim();

		if (form.loginPw.value.length == 0) {
			alert('비밀번호를 입력해주세요.');
			form.loginPw.focus();

			return;
		}
		
		if (form.loginPwConfirm.value.length == 0) {
			alert('로그인비번 확인을 입력해주세요.');
			form.loginPwConfirm.focus();
			return;
		}
		
		if (form.loginPw.value != form.loginPwConfirm.value) {
			alert('로그인비번이 일치하지 않습니다.');
			form.loginPwConfirm.focus();
			return;
		}
		
		form.name.value = form.name.value.trim();
		if (form.name.value.length == 0) {
			alert('이름을 입력해주세요.');
			form.name.focus();
			return;
		}
		
		form.nickname.value = form.nickname.value.trim();
		if (form.nickname.value.length == 0) {
			alert('별명을 입력해주세요.');
			form.nickname.focus();
			return;
		}
		
		form.email.value = form.email.value.trim();
		if (form.email.value.length == 0) {
			alert('이메일을 입력해주세요.');
			form.email.focus();
			return;
		}
		
		form.cellphoneNum.value = form.cellphoneNum.value.trim();
		if (form.cellphoneNum.value.length == 0) {
			alert('휴대전화번호를 입력해주세요.');
			form.cellphoneNum.focus();
			return;
		}

		form.submit();
		JoinForm__checkAndSubmitDone = true;
	}
</script>

<section class="section-join">
	<div class="bg-red-200 grid min-h-screen place-items-center">
		<div class="w-11/12 p-12 bg-white sm:w-8/12 md:w-1/2 lg:w-5/12">
			<h1 class="text-2xl flex justify-center font-semibold">ADMIN</h1>
			<form class="mt-6" action="doJoin" method="POST"
				onsubmit="JoinForm__checkAndSubmit(this); return false;">
				<input type="hidden" name="redirectUrl" value="${param.redirectUrl}" />

				<div class="flex flex-col mb-4 md:flex-row">
					<div class="p-1 md:w-36 md:flex md:items-center">
						<span>아이디</span>
					</div>
					<div class="p-1 md:flex-grow">
						<input
							class="shadow appearance-none border rounded w-full py-2 px-3 text-grey-darker"
							autofocus="autofocus" type="text" placeholder="아이디를 입력해주세요."
							name="loginId" maxlength="20" required />
					</div>
				</div>

				<div class="flex flex-col mb-4 md:flex-row">
					<div class="p-1 md:w-36 md:flex md:items-center">
						<span>비밀번호</span>
					</div>
					<div class="p-1 md:flex-grow">
						<input type="password" name="loginPw" placeholder="********"
							autofocus="autofocus"
							class="block w-full p-3 mt-2 text-gray-700 bg-gray-200 appearance-none focus:outline-none focus:bg-gray-300 focus:shadow-inner"
							required />
					</div>
				</div>
				<div class="flex flex-col mb-4 md:flex-row">
					<div class="p-1 md:w-36 md:flex md:items-center">
						<span>비밀번호 확인</span>
					</div>
					<div class="p-1 md:flex-grow">
						<input type="password" name="loginPwConfirm" placeholder="********"
							autofocus="autofocus"
							class="block w-full p-3 mt-2 text-gray-700 bg-gray-200 appearance-none focus:outline-none focus:bg-gray-300 focus:shadow-inner"
							required />
					</div>
				</div>
				
				<div class="flex flex-col mb-4 md:flex-row">
					<div class="p-1 md:w-36 md:flex md:items-center">
						<span>이름</span>
					</div>
					<div class="p-1 md:flex-grow">
						<input type="text" name="name" placeholder="이름을 입력해주세요."
							autofocus="autofocus"
							class="block w-full p-3 mt-2 text-gray-700 bg-gray-200 appearance-none focus:outline-none focus:bg-gray-300 focus:shadow-inner"
							required />
					</div>
				</div>
				
				<div class="flex flex-col mb-4 md:flex-row">
					<div class="p-1 md:w-36 md:flex md:items-center">
						<span>닉네임</span>
					</div>
					<div class="p-1 md:flex-grow">
						<input type="text" name="nickname" placeholder="닉네임을 입력해주세요."
							autofocus="autofocus"
							class="block w-full p-3 mt-2 text-gray-700 bg-gray-200 appearance-none focus:outline-none focus:bg-gray-300 focus:shadow-inner"
							required />
					</div>
				</div>
				
				<div class="flex flex-col mb-4 md:flex-row">
					<div class="p-1 md:w-36 md:flex md:items-center">
						<span>이메일</span>
					</div>
					<div class="p-1 md:flex-grow">
						<input type="email" name="email" placeholder="이메일을 입력해주세요."
							autofocus="autofocus"
							class="block w-full p-3 mt-2 text-gray-700 bg-gray-200 appearance-none focus:outline-none focus:bg-gray-300 focus:shadow-inner"
							required />
					</div>
				</div>
				
				<div class="flex flex-col mb-4 md:flex-row">
					<div class="p-1 md:w-36 md:flex md:items-center">
						<span>휴대전화번호</span>
					</div>
					<div class="p-1 md:flex-grow">
						<input type="tel" name="cellphoneNum" placeholder="휴대전화번호를 입력해주세요.(- 없이 입력해주세요.)"
							autofocus="autofocus"
							class="block w-full p-3 mt-2 text-gray-700 bg-gray-200 appearance-none focus:outline-none focus:bg-gray-300 focus:shadow-inner"
							required />
					</div>
				</div>
				
				<button type="submit"
					class="w-full py-3 mt-6 font-medium tracking-widest text-white uppercase bg-black shadow-lg focus:outline-none hover:bg-gray-900 hover:shadow-none">
					Sign up</button>
					
					<p onclick="history.back();"
					class="flex justify-between inline-block mt-4 text-xs text-gray-500 cursor-pointer hover:text-black">돌아가기</p>




			</form>
		</div>
	</div>

</section>


<%@ include file="../part/foot.jspf"%>
