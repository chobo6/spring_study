<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>도서 정보</h2>
	
	<p>
		${book.id} ${book.title} ${book.author} ${book.price}
	</p>
	
	<div>
		<button type="button" onClick="location.href='/bookList'">도서 목록</button>
	</div>
</body>
</html>