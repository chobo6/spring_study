<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>여기는 main 페이지입니다.</h1>

	<div>
		<c:if test="${empty loginUserId}">
			<button type="button" onClick="location.href='/customer/signup'">회원가입하기</button>
			<button type="button" onClick="location.href='/customer/signin'">로그인</button>
		</c:if>
		
		<c:if test="${!empty loginUserId}">
			<p>${loginUserId} 님 환영합니다!</p>
			<button type="button" onClick="location.href='/customer/signout'">로그아웃</button>
			<button type="button" onClick="location.href='/customer/mypage'">마이페이지</button>
		</c:if>
	</div>
</body>
</html>