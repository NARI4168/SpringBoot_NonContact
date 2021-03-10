<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../part/head.jspf"%>


<script>
	const LoginForm__checkAndSubmitDone = false;
	function LoginForm__checkAndSubmit(form) {
		if (LoginForm__checkAndSubmitDone) {
			return;
		}

		form.loginId.value = form.loginId.value.trim();

		if (form.loginId.value.length == 0) {
			alert('아이디를 입력해주세요.');
			form.loginId.focus();

			return;
		}

		if (form.loginPw.value.length == 0) {
			alert('비밀번호를 입력해주세요.');
			form.loginPw.focus();

			return;
		}

		form.submit();
		LoginForm__checkAndSubmitDone = true;
	}
</script>

<section class="section-login">

	<div class="bg-red-200 grid min-h-screen place-items-center">
		<div class="w-11/12 p-12 bg-white sm:w-8/12 md:w-1/2 lg:w-5/12">
			<h1 class="text-xl font-semibold">
				This is the administrator's page. <br>
				<span class="font-normal">sign in to continue</span>
			</h1>
			<form class="mt-6" action="doLogin" method="POST"
				onsubmit="LoginForm__checkAndSubmit(this); return false;">
				<input type="hidden" name="redirectUrl" value="${param.redirectUrl}" />
				<label class="block text-xs font-semibold text-gray-600 uppercase">아이디</label>
				<input type="text" name="loginId" placeholder="아이디를 입력해주세요."
					autofocus="autofocus"
					class="block w-full p-3 mt-2 text-gray-700 bg-gray-200 appearance-none focus:outline-none focus:bg-gray-300 focus:shadow-inner"
					required /> <label
					class="block mt-2 text-xs font-semibold text-gray-600 uppercase">비밀번호</label>
				<input type="password" name="loginPw" placeholder="********"
					autofocus="autofocus"
					class="block w-full p-3 mt-2 text-gray-700 bg-gray-200 appearance-none focus:outline-none focus:bg-gray-300 focus:shadow-inner"
					required />
				<button type="submit"
					class="w-full py-3 mt-6 font-medium tracking-widest text-white uppercase bg-black shadow-lg focus:outline-none hover:bg-gray-900 hover:shadow-none">
					Sign in</button>
				<p
					class="flex justify-between inline-block mt-4 text-xs text-gray-500 cursor-pointer hover:text-black">Forgot
					password?</p>
			</form>
		</div>
	</div>

</section>

<%@ include file="../part/foot.jspf"%>
