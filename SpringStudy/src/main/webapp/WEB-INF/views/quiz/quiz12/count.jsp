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
<%-- 	<p>${id}</p> --%>
<%-- 	<p>${pw}</p> --%>
<%-- 	<c:choose> --%>
<%--         <c:when test="${id == null}"> --%>
<!--             <p>0</p> -->
<%--         </c:when> --%>
<%--         <c:otherwise> --%>
<%--             <p>${count}</p> --%>
<%--         </c:otherwise> --%>
<%--     </c:choose> --%>
		<h1>quiz12 count 페이지</h1>
	
<!-- 	누가 로그인했는가 아이디 -->
<!-- 	몇번 로그인했는가 횟수 -->
	
	<c:choose>
<%-- 		<c:when test="${loginId == '' || loginId == null}"> --%>
<%--		null, "", length0  --%>
		<c:when test="${ empty loginId }">
			<p>익명의 사용자 접속중</p>
			<p>로그인 해주세요</p>
		</c:when>
		<c:otherwise>
			<p>로그인한 아이디 ${loginId} 님 환영합니다.</p>
		</c:otherwise>
	</c:choose>
	
	
	<p>접속횟수 : ${count} </p>
	
	
	<br><br><br>
	<a href="/quiz12/logout">로그아웃</a>
</body>
</html>