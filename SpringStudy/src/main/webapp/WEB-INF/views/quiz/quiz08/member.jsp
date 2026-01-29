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
	<h1>quiz08</h1>
	<c:forEach var="member" items="${memberList}">
		<c:if test="${auth == 'admin' || member.type == auth}">
			<p>${member.id} ${member.pw} ${member.name}</p>
		</c:if>
	</c:forEach>
</body>
</html>