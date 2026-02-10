package com.app.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.app.util.LoginManager;

public class LoginInterceptor implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
			
//		System.out.println("인터셉터 preHandle");
		
		// 로그인 여부 판단하는 인터셉터
		// 로그인 안했으면? -> 진행 X -> 로그인 페이지로 보내기
		if(LoginManager.isLogin(request) == false) { // 로그인 안했으면
			response.sendRedirect("/customer/signin"); // 로그인 화면으로 리다이렉트
			return false; // 이 다음으로 진행 X
		}
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

//		System.out.println("인터셉터 postHandle");
		
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

//		System.out.println("인터셉터 afterCompletion");
		
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}
