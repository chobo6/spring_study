package com.app.controller.study.quiz.quiz13;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.util.MyCookieUtil;

@Controller
public class Quiz13Controller {

	
	@GetMapping("/quiz13/hideAd")
	public String hideAd(HttpServletRequest request) {
		
		String hideAd = MyCookieUtil.getCookie(request, "hideAd");
		if(hideAd != null) {
			request.setAttribute("hideAd", hideAd);
		}
		
		return "quiz/quiz13/hideAd";
	}
	
	@PostMapping("/quiz13/hideAd")
	public String hideAdAction(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println(request.getParameter("hideAd"));
		//24시간보지않기 체크후 요청을 했으면?
		//쿠키에 값 저장!! -> 나중에 해당 화면 접근시, 광고 표시 여부로 활용
		
		if(request.getParameter("hideAd") != null) {  //체크함 확인~
			Cookie ck = MyCookieUtil.createCookie("hideAd", "hideAd", 60 * 60 * 24);
			response.addCookie(ck);
		}
		
		
		return "redirect:/quiz13/hideAd";
	}
}


