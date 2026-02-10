<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>관리자 페이지</h1>
	<h2>사용자(User) 추가</h2>
	
<!-- 	<form action="" method="post"> -->
<!-- 		사용자 아이디 : <input type="text" name="id"><br> -->
<!-- 		사용자 이름 : <input type="text" name="name"><br> -->
<!-- 		사용자 비밀번호 : <input type="password" name="pw"><br> -->
<!-- 		<select name="userType"> -->
<!-- 			<option value="ADM">관리자</option> -->
<!-- 			<option value="CUS">사용자</option> -->
<!-- 		</select><br> -->
<!-- 		<button type="submit">등록하기</button> -->

<!-- 관리자가 필요한 값만 사용자로 임시 등록 -->
	<form action="" method="post" id="form_add">
		사용자 아이디 : <input type="text" name="id" id="input_id"><br>
		사용자 이름 : <input type="text" name="name" required="required"><br>
		<button type="submit">등록하기</button>
	</form>
	
	<script>
		const form_add = document.getElementById('form_add');
		form_add.addEventListener('submit', (event)=>{
			
			event.preventDefault(); // submit 전송 중지
			
			// 유효성 검증
			let id = document.getElementById('input_id').value;
			id = id.trim();
			
			if(id == ''){
				console.log('id 공백임');
				return;
			}
			
			// id 길이 제한 3글자 이상 15글자 이하
			if(id.length < 3 || id.length > 15){
				console.log('id 길이 제한에 걸림');
				return;
			}
			
			// 유효성 검증이 통과하면? submit
			form_add.submit();
		})
	</script>
</body>
</html>