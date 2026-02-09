<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>sign up 페이지</h1>

	<form action="" method="post">
		사용자 아이디 : <input type="text" name="id" id="inputId">
		<button type="button" id="btn_checkDupId">중복체크</button>
		<p id="checkDupIdMsg"></p>
		
		사용자 이름 : <input type="text" name="name"><br>
		사용자 비밀번호 : <input type="password" name="pw"><br>
		
		<button type="submit">가입하기</button>
	</form>
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	
	<script>
		const btn_checkDupId = document.getElementById('btn_checkDupId');
		const p_checkDupIdMsg = document.getElementById('checkDupIdMsg');
		
		btn_checkDupId.addEventListener('click', ()=>{
			// 아이디 중복체크 버튼 누르면
			// 입력 아이디 값 확인 -> 서버로 ajax 요청 (아이디) -> (서버) 중복체크 확인
			// (클라이언트) -> 결과를 Y/N 응답 -> 화면에 표시
			let inputId = document.getElementById('inputId').value;
			console.log(inputId);
			
			// 서버에 요청 (중복체크 요청)
			
			/*
			
			// 1) 단순 텍스트 id 요청 "inputId" -> 응답 Y/N
			$.ajax({
				type: "POST",
				url: "http://localhost:8080/customer/checkDupId",
				headers: {
					"Content-type":"application/json"
				},
				data: inputId, // 서버에 보낼 데이터 값
				dataType: 'text',
				success: function(result){
					console.log('ajax success');
					console.log(result); // Y or N
					if(result == 'Y'){ // 중복 O
						p_checkDupIdMsg.textContent = '중복된 아이디입니다.';
						p_checkDupIdMsg.style.color = 'red';
					} else { // 중복 X
						p_checkDupIdMsg.textContent = '사용 가능한 아이디입니다.';
						p_checkDupIdMsg.style.color = 'green';
					}
				},
				error: function(error) {
					console.log(error);
				}
			})
			
			*/
			
			// 2) json 포맷 전송, json 포맷 서버 응답 수신
			
			// 요청할 데이터 json 포맷
			
			let obj = { // javascript 객체 타입
					"id": inputId,
					"type": "CUS"
			};
			
			// js obj -> JSON format text	JSON.stringify	
			// JSON format text -> js obj	JSON.parse
			
			let jsonText = JSON.stringify(obj); // 서버에 보낼 json text
			
			$.ajax({
				type: "POST",
				url: "http://localhost:8080/customer/checkDupIdJson",
				headers: {
					"Content-type":"application/json"
				},
				data: jsonText, // 서버에 보낼 데이터 값
				dataType: 'json', // 서버에서 응답도 json 포맷으로 보낼 예정
				success: function(result){
					console.log('ajax success');
					console.log(result); // Y or N
					
					// dataType -> text -> json format text -> JSON.parse(result) -> javascript Object
					// dataType -> json -> result (javaScript object 로 변환된 상태)
					
					// dataType -> text
					// let jsObj = JSON.parse(result);
					
					// dataType -> json
					
					let jsObj = result;
					
					console.log(jsObj.header.resultCode);
					console.log(jsObj.header.resultMessage);
					console.log(jsObj.body);
					
					if(jsObj.body == 'Y'){ // 중복 O
						p_checkDupIdMsg.textContent = '중복된 아이디입니다.';
						p_checkDupIdMsg.style.color = 'red';
					} else { // 중복 X
						p_checkDupIdMsg.textContent = '사용 가능한 아이디입니다.';
						p_checkDupIdMsg.style.color = 'green';
					}
				},
				error: function(error) {
					console.log(error);
				}
			})
		});
	</script>
</body>
</html>