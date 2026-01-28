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
	<h1>jstl2</h1>

	<p>${msg1 }</p>
	<p>${msg2 }</p>

	&lt; &gt;
	&nbsp;띄어쓰기&nbsp;&nbsp;&nbsp;&nbsp;띄어쓰기
	<%-- 	${msg1 } --%>
	<!-- 	msg1 -->
	
	<br>
<%-- 	${msgXml} --%>
	
	<c:out value="${msgXml }" escapeXml="true"/><br>
	
	<c:out value="${msg1 }" escapeXml="true"/><br>
	<c:out value="${msg2 }" escapeXml="true"/><br>
	
	<c:out value="${msg2 }" escapeXml="false"/><br>
	
	<hr>
	
	<a href="/jstl1">jstl1 페이지로 이동</a>
	<button id="btn">jstl1 페이지로 버튼</button>

	<c:url var="jstl1Url" value="/jstl1" />	
	<a href="${jstl1Url }">jstl1 페이지로 이동</a>
	<a href="/jstl1">jstl1 페이지로 이동</a>
	
<%-- 	<c:redirect url="https://www.naver.com/"/> --%>
	
	<script>
// 		location.href = "https://www.naver.com/";
		document.getElementById('btn').addEventListener('click', ()=>{
			location.href = '/jstl1';
		})
	</script>
</body>
</html>