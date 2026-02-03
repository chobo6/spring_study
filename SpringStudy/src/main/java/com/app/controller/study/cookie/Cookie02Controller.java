package com.app.controller.study.cookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.util.MyCookieUtil;

@Controller
public class Cookie02Controller {

	
	
	//**쿠키 활용 -> 아이디 기억
	
	@GetMapping("/idCookie")
	public String idCookie(HttpServletRequest request) {
		
		//쿠키에서 remember 값 -> 존재 체크
		// 있으면? 아이디 기억 -> 화면에 표시
		// 없으면? 그냥 패스 (공백으로)
		
		String remember = MyCookieUtil.getCookie(request, "remember");
		if(remember != null) {
			request.setAttribute("remember", remember);
		}
		
		
		return "cookie/idCookie";
	}
	
	@PostMapping("/idCookie")
	public String idCookieAction(HttpServletRequest request, HttpServletResponse response) {
		
		//로직 처리...
		// 입력 유효성 검증
		// id pw <->DB 데이터
		// 맞냐  -> 로그인처리 -> 성공 페이지로 이동
		// 틀리냐 -> 실패 -> 다시 로그인화면
		
		System.out.println(request.getParameter("id"));
		System.out.println(request.getParameter("pw"));
		System.out.println(request.getParameter("remember"));
		//체크박스 값 : 체크 on   체크안하면 null
		
		
		//로그인 성공 실패...
		// 체크박스 : 아이디저장 확인 -> 체크O -> 쿠키에 아이디를 저장해두자
		String id = request.getParameter("id");
		String remember = request.getParameter("remember");
		
		if( remember == null) { //기억 체크 X
			
			//기억하지 않겠다 -> 쿠키 값 삭제
			Cookie ck = MyCookieUtil.createCookieForRemove("remember");
			response.addCookie(ck);
			
		} else { // 기억 체크 O
			
			//remember true
			//if(remember.equals("check")
			boolean isRemember = Boolean.parseBoolean(remember);
			
			//id 값
			Cookie ck = MyCookieUtil.createCookie("remember", id, 60 * 60);
			response.addCookie(ck);
		}
		
		
		return "redirect:/readCookie";
	}
}



















