<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HOME</title>

<!-- 모바일에서 사이트가 PC에서의 픽셀크기 기준으로 작동하게 하기(반응형 하려면 필요) -->
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/tailwindcss/2.0.3/tailwind.min.css" />
<link rel="stylesheet" href="/resource/common.css" />

</head>
<body class="bg-red-200">

	<!-- admin -->
	<div
		class="max-w-screen-lg bg-red-500 shadow-2xl rounded-lg mx-auto text-center py-12 mt-20">
		<h2
			class="text-3xl leading-9 font-bold tracking-tight text-white sm:text-4xl sm:leading-10">
			Administrator Only</h2>
		<div class="mt-8 flex justify-center">
			<div class="inline-flex rounded-md bg-white shadow">
				<a href="../adm/member/login"
					class="text-gray-700 font-bold py-2 px-6"> Start </a>
			</div>
		</div>
	</div>

	<!-- member -->
	<div
		class="max-w-screen-lg bg-indigo-500 shadow-2xl rounded-lg mx-auto text-center py-12 mt-4">
		<h2
			class="text-3xl leading-9 font-bold tracking-tight text-white sm:text-4xl sm:leading-10">
			Membership</h2>
		<div class="mt-8 flex justify-center">
			<div class="inline-flex rounded-md bg-white shadow">
				<a href="../usr/member/login"
					class="text-gray-700 font-bold py-2 px-6"> Start </a>
			</div>
		</div>
	</div>

</body>
</html>