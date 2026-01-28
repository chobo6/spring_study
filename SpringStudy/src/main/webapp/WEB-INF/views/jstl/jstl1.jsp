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
	<h1>jstl1</h1>
	
	<p>${msg}<p>
	
	<p><c:out value="${msg}"/><p>
	
	<p>${product.id}<p>
	<p>${product.name}<p>
	
	<p>${productList[0].id} ${productList[0].name} ${productList[0].price}<p>
	<p>${productList[1].id} ${productList[1].name} ${productList[1].price}<p>
	<p>${productList[2].id} ${productList[2].name} ${productList[2].price}<p>
	
	<hr>
	<hr>
	
	<c:forEach var="i" begin="1" end="5" step="1">
		<div class="box">
			<p>forEach 반복문 내부영역 ${i}</p>
			<p>${productList[i-1].id}</p>
		</div>
	</c:forEach>
	
	<hr>
	
<!-- 	for(Product p : productList) -->
	<c:forEach var="p" items="${productList}">
		<p>${p.id} ${p.name} ${p.price}</p> 
	</c:forEach>
	
	<c:choose>
		<c:when test="${drinkType == 'coffee'}">
			<p>커피 선택 확인~</p>
		</c:when>
		<c:when test="${drinkType == 'juice'}">
			<p>주스 선택 확인~</p>
		</c:when>
		<c:otherwise>
			<p>그외에는.. 다른거 드시던가</p>
		</c:otherwise>
	</c:choose>
	
	<c:choose>
		<c:when test="${score >= 300 && score <= 500}">
			<p>점수가 꽤 높네</p>
		</c:when>
		<c:when test="${score >= 501 and score <= 1000}">
			<p>점수가 많이 높네</p>
		</c:when>
		<c:otherwise>
			<p>분발하도록</p>
		</c:otherwise>
	</c:choose>
	
	<c:if test="${userType == 'admin' }">
		<p>관리자 계정만 볼 수 있는 설정</p>
	</c:if>
	
	<c:if test="${isLogin == true }">
		<button>로그아웃 버튼 표시</button>
	</c:if>
	<c:if test="${isLogin == false }">
		<button>로그인 버튼 표시</button>
	</c:if>
</body>
</html>