<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>도서 정보 수정</h1>
	<form action="" method="post">
		<input type="hidden" name="id" value="${book.id}">
		
		<label>제목<input type="text" name="title" value="${book.title}"></label><br>
		<label>글쓴이<input type="text" name="author" value="${book.author}"></label><br>
		<label>가격<input type="text" name="price" value="${book.price}"></label><br>
		<br>
		<button type="submit">수정하기</button>
	</form>
</body>
</html>