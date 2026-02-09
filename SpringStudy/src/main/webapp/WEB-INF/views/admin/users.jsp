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
	<h1>관리자 페이지</h1>
	<h2>사용자 목록 조회</h2>
	
	<div>
		<form action="" method="get">
			<p>검색기준</p>
			<label>아이디 <input type="checkbox" name="id" value="searchId"   ></label>
			<label>이름 <input type="checkbox" name="name" ></label>
			
			<br>
			<label><input type="radio" name="userType" value="ADM"  >관리자</label>
			<label><input type="radio" name="userType" value="CUS" >고객(사용자)</label>
			<br>
			
			<label>검색어 : <input type="text" name="searchKeyword" ></label>
			<button type="submit">검색</button>	
		</form>
		
	</div>
	
	<hr>

	<c:forEach var="user" items="${userList}">
		<p>
			<a href="/admin/user/${user.id}">
				${user.id} ${user.pw} ${user.name}
				<c:choose>
					<c:when test="${user.userType eq 'ADM'}">관리자</c:when>
					<c:when test="${user.userType eq 'CUS'}">사용자</c:when>
				</c:choose>
			</a>
		</p>
	</c:forEach>
	
	<button type="button" onclick="location.href='/main'">메인으로</button>
</body>
</html>