<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../part/head.jspf" %>	


<div class="bg-red-200 grid min-h-screen place-items-center">
  <div class="w-11/12 p-12 bg-white sm:w-8/12 md:w-1/2 lg:w-5/12">
    <h1 class="text-xl font-semibold">WELCOME GUYS, <span class="font-normal">sign in to continue</span></h1>
    <form class="mt-6">
      <label class="block text-xs font-semibold text-gray-600 uppercase">아이디</label>
      <input type="text" name="loginId" placeholder="아이디를 입력해주세요." autofocus="autofocus" class="block w-full p-3 mt-2 text-gray-700 bg-gray-200 appearance-none focus:outline-none focus:bg-gray-300 focus:shadow-inner" required />
      <label class="block mt-2 text-xs font-semibold text-gray-600 uppercase">비밀번호</label>
      <input type="password" name="loginPw" placeholder="********" autofocus="autofocus" class="block w-full p-3 mt-2 text-gray-700 bg-gray-200 appearance-none focus:outline-none focus:bg-gray-300 focus:shadow-inner" required />
      <button type="submit" class="w-full py-3 mt-6 font-medium tracking-widest text-white uppercase bg-black shadow-lg focus:outline-none hover:bg-gray-900 hover:shadow-none">
        Sign in
      </button>
      <p class="flex justify-between inline-block mt-4 text-xs text-gray-500 cursor-pointer hover:text-black">Forgot password?</p>
    </form>
  </div>
</div>


<%@ include file="../part/foot.jspf" %>	