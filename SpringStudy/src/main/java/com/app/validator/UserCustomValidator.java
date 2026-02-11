package com.app.validator;

import com.app.dto.user.User;
import com.app.dto.user.UserValidError;

public class UserCustomValidator {

	public static boolean validate(User user, UserValidError userValidError) {
		
		boolean result = true;
		
		//유효성 검증 비교 
		if(user.getId() == null || user.getId().trim().equals("")) {
			//입력 제대로 안함. 유효성 검증 필터
			userValidError.setId("아이디 필수임 입력 안했는데요?");
			result = false;
		}
		
		if(user.getId().length() < 4 || user.getId().length() > 15) {
			userValidError.setId("아이디 길이 기각 4~15 ");
			result = false;
		}
		
		if( (user.getPw().length() >= 8 && user.getPw().length() <= 12) == false ) {
			userValidError.setPw("비번은 8~12 입니다");
			result = false;
		}
		
		if( user.getName() == null || user.getName().trim().equals("")) {
			userValidError.setName("이름도 필수니까 적어주세요");
			result = false;
		}
		
		return result;
	}
}
