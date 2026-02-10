package com.app.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.app.common.CommonCode;
import com.app.dto.user.User;
import com.app.service.user.UserService;
import com.app.util.LoginManager;

public class AdminInterceptor implements HandlerInterceptor{
	
	@Autowired
	UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// 로그인 여부 + 관리자 계정여부 (userType ADM)
		
		// /admin 으로 시작하는 경로에 작동
		
		if(LoginManager.isLogin(request)) { // 로그인 O
			// 관리자 맞나?
			
			// 현재 로그인한 계정이 관리자인가?
			String userId = LoginManager.getLoginUserId(request);
			User user = userService.findUserById(userId);
			
			// 관리자 판단
			if(user.getUserType().equals(CommonCode.USER_USERTYPE_ADMIN)) {
				// 관리자 맞음
				// 다음 과정 순차적으로 진행
				return HandlerInterceptor.super.preHandle(request, response, handler);
			} else {
				// 관리자 아님
				response.sendRedirect("/error/badAccess");
				// response.sendRedirect("/main");
				return false;
			}
			
		} else { // 로그인 X
			// 관리자 페이지 접속
			// -> 로그인 페이지
			// -> 잘못된 접근입니다
			response.sendRedirect("/error/badAccess");
			return false;
		}
		
	}
}
