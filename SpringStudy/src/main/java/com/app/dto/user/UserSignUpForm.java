package com.app.dto.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserSignUpForm {
	// 필수입력 빈칸 X
	@NotBlank(message = "아이디 필수임")
	String id;

	// 비밀번호 길이제한
	@Size(min = 8, max = 12, message = "비밀번호 길이 확인")
	String pw;
	String name;
	String userType;
	// 사용자 계정 구분
	// Customer Admin
	// CUS ADM
}
