<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<form action="/quiz06/result-bmi" method="post">
			<input type="text" name="name" placeholder="이름"> <br>
			<input type="text" name="height" placeholder="키"> <br>
			<input type="text" name="weight" placeholder="몸무게"> <br>
			<button type="submit">확인하기</button>
		</form>
	</div>
</body>
</html>