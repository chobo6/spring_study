package com.app.controller.study.cookie;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.app.util.MyCookieUtil;

@Controller
public class Cookie01Controller {

	@GetMapping("/saveCookie")
	public String saveCookie(HttpServletResponse response) {
		
		//서버에서 쿠키 생성 -> response 담아서 전달
		// 			-> 사용자(브라우저) -> 쿠키? -> 쿠키 저장
		
		Cookie ck1 = new Cookie("menu", "choco"); //name value
		ck1.setMaxAge( 60 * 60 * 12 ); //쿠키 유지하는 수명 (초단위)
		
		response.addCookie(ck1);
		
		Cookie ck2 = new Cookie("today", "Friday");
		ck2.setMaxAge(30);
		
		response.addCookie(ck2);
		
		
		//쿠키 value 에는 띄어쓰기 사용이 안됨
		// -> URLEncode
		try {
			String value = URLEncoder.encode("temp minus 9", "UTF-8");
			
			Cookie ck3 = new Cookie("temperature", value);
			ck3.setMaxAge(60*60);
			response.addCookie(ck3);
			
			
			Cookie ck4 = new Cookie("status", URLEncoder.encode("매우 배고픔", "UTF-8"));
			ck4.setMaxAge(60*60);
			response.addCookie(ck4);
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		Cookie ck10 = MyCookieUtil.createCookie("ckName1", "value1");
		response.addCookie(ck10);
		
		Cookie ck11 = MyCookieUtil.createCookie("ckName2", "value2", 3000);
		response.addCookie(ck11);
		
		return "cookie/saveCookie";
	}
	
	@GetMapping("/readCookie")
	public String readCookie(HttpServletRequest request){
		
		Cookie[] cookies = request.getCookies();
		
		String menu = null;
		for(Cookie ck : cookies) {
			System.out.println(ck.getName() + " : " +ck.getValue());
			
			if(ck.getName().equals("menu")) {
				menu = ck.getValue();
				//request.setAttribute("menu", ck.getValue());
			}
			
			
			//인코딩 된 value값 쿠키들
			if(ck.getName().equals("temperature") || ck.getName().equals("status")) {
				//decode 해서 출력
				try {
					String value = URLDecoder.decode(ck.getValue() , "UTF-8");
					System.out.println("decode : " + value);
					
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				
			}
		}
		
		//
		request.setAttribute("menu", menu);
		
		//util 사용
		//Cookie[] cookies = request.getCookies();
		String menu2 = MyCookieUtil.getCookie(cookies, "menu");
		String status = MyCookieUtil.getCookie(cookies, "status");
		String temperature = MyCookieUtil.getCookie(request, "temperature");
		
		System.out.println(menu2);
		System.out.println(status);
		System.out.println(temperature);
		
		return "cookie/readCookie";
	}
	
	
	@GetMapping("/removeCookie")
	public String removeCookie(HttpServletResponse response){
		
		//쿠키 삭제하기
		//쿠키의 수명시간 -> 0으로 세팅 -> 브라우저에게 전달
		Cookie ck1 = new Cookie("menu", "anything"); //name value
		ck1.setMaxAge( 0 ); //쿠키 유지하는 수명 (초단위)
		
		response.addCookie(ck1);
		
		Cookie ck = MyCookieUtil.createCookieForRemove("ckName2");
		response.addCookie(ck);
		
		return "cookie/saveCookie";
	}
}


















