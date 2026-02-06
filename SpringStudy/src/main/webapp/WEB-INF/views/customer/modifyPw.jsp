<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>사용자 비밀번호 변경</h1>
	<form action="" method="POST">
		<input type="hidden" name="id" value="${user.id}">
<%-- 		<input type="text" name="name" value="${user.name}" disabled> --%>
		<input type="text" name="name" value="${user.name}" readonly>
		<input type="hidden" name="userType" value="${user.userType}">
		<br>
		${user.name} 님 바꿀 비밀번호를 입력하세요.
		<br>
		<input type="password" name="pw">
		<br>
		<button type="submit">변경하기</button>
	</form>
	<button type="button" onclick="location.href='/customer/mypage'">마이페이지로 돌아가기</button>
</body>
</html>