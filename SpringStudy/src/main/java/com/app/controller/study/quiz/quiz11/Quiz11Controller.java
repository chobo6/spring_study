package com.app.controller.study.quiz.quiz11;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Quiz11Controller {
//		s.setAttribute("accessUrl", "/" + accessUrl);
//
//		if (accessUrl.equals("firsthide2")) {
//			return "redirect:/quiz11/first";
//		}
//		return "quiz/quiz11/targetPage";

	@GetMapping("/quiz11/first")
	public String first(Model model, HttpSession session) {

		if(session.getAttribute("accessUrl") == null) {
			//session.setAttribute("accessUrl", "/firsthide3");
			model.addAttribute("accessUrl", "/first");			
		} else { //세션에 accessUrl 들어있다 -> firsthide3 들렸다가 왔다!
			
			//model.addAttribute("accessUrl", "/first");
			model.addAttribute("accessUrl", session.getAttribute("accessUrl"));
			
			//session 인식 사용 완료 -> 삭제
			session.removeAttribute("accessUrl"); //특정 키값 삭제
			//session.invalidate(); //세션 클리어 초기화
		}
		

		
		return "quiz/quiz11/targetPage";
	}

	@GetMapping("/quiz11/firsthide1")
	public String firsthide1(Model model) {

		model.addAttribute("accessUrl", "/firsthide1");

		return "quiz/quiz11/targetPage";
	}

	@GetMapping("/quiz11/firsthide2")
	public String firsthide2(Model model) {

		// firsthide2 요청 request
		// model.addAttribute("accessUrl", "/first");
		// 필요없음.

		// first 경로로 새로운 요청 request
		// return "quiz/quiz11/targetPage";
		return "redirect:/quiz11/first";
	}
	
	@GetMapping("/quiz11/firsthide3")
	public String firsthide3(Model model, HttpSession session) {
		
		//model.addAttribute("accessUrl", "/firsthide3");
		
		//firsthide3 들렸다가~ -> first 에 왔다!
		//session 영역에 저장
		session.setAttribute("accessUrl", "/firsthide3");
		
		return "redirect:/quiz11/first";
	}
}
