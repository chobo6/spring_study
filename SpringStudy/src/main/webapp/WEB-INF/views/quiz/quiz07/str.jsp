<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>str</h1>
	
	<!-- 	고정된 값 10개 하드코딩 -->
	<p>스트링 리스트입니다.</p>
	<p>스트링 리스트입니다.</p>
	<p>스트링 리스트입니다.</p>
	<p>스트링 리스트입니다.</p>
	<p>스트링 리스트입니다.</p>
	<p>스트링 리스트입니다.</p>
	<p>스트링 리스트입니다.</p>
	<p>스트링 리스트입니다.</p>
	<p>스트링 리스트입니다.</p>
	<p>스트링 리스트입니다.</p>
	<br>
	
<!-- 	view 안에서 고정 텍스트 반복처리 -->
	<c:forEach var="i" begin="1" end="10" step="1">
		<p>스트링 리스트입니다.</p>
	</c:forEach>
	<br>
	
<!-- 	출력할 메시지를 전달하여 그걸 반복  -->
	<c:forEach var="i" begin="1" end="${count}" step="1">
		<p>${msg}</p>
	</c:forEach>
	<br>
	
<!-- 	리스트 형태로 전달 -> 반복 -->
	<c:forEach var="s" items="${strList}">
		<p>${s}</p>
	</c:forEach>
	
</body>
</html>





















