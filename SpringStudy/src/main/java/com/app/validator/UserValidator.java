package com.app.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.app.dto.user.User;

public class UserValidator implements Validator {

	// User 객체에 대한 검증

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub

		User user = (User)target;
		// 유효성 검증 코드
		// 서버 측에서 Insert 처리 전에 유효성 검증
		if (user.getId() == null || user.getId().trim().equals("")) {
			// 입력 제대로 안함 -> 유효성 검증 필터에 걸림
			errors.rejectValue("id", "EmptyUserId", "아이디 입력 안했는데요?");
		}
		
		if(user.getId().length() < 4 || user.getId().length() > 15) {
			errors.rejectValue("id", "LengthUserId", "아이디 길이 기각");
		}
		
		if((user.getPw().length() >= 8 && user.getPw().length() <= 12) == false) {
			errors.rejectValue("pw", "LengthUserPw", "비밀번호는 8~12 입니다.");
		}
	}

}
