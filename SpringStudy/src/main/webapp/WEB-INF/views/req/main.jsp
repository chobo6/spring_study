<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>/req/main</h1>
	
	<p><a href="/url7">/url7 번 경로로 이동</a> </p>
	<p><a href="/board/faq">FAQ 자주묻는질문</a> </p>
	<p><a href="/board/notice">공지사항 페이지로 이동</a> </p>
	
	<p><button onClick='location.href="/url5"'>이동버튼</button></p>
	
	
	<form action="/url6" method="post">
		<h2>여기는 Form Post로 요청할거임</h2>
		<button type="submit">요청</button>
	</form>
	
</body>
</html>
