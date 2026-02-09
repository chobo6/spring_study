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
	<h1>bookList</h1>
	<c:forEach var="book" items="${bookList}">
		<p>
			<a href="/bookList/${book.id}">
				${book.id} ${book.title} ${book.author} ${book.price}
			</a>
		</p>
		<button type="button" onclick="location.href='/removeBook?bookId=${book.id}'">삭제하기</button>
		<button type="button" onclick="location.href='/modifyBook?bookId=${book.id}'">수정하기</button>
	</c:forEach>
	<br><br>
	<button type="button" onclick="location.href='/addBook'">도서 추가</button>
</body>
</html>