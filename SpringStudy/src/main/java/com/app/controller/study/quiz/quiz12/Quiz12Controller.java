package com.app.controller.study.quiz.quiz12;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/quiz12")
public class Quiz12Controller {
//	@GetMapping("/login")
//	public String getLogin(HttpSession s) {
//		if(s.getAttribute("id") != null)
//			return "redirect:/quiz12/count";
//		return "quiz/quiz12/login";
//	}
//	@PostMapping("/login")
//	public String postLogin(@RequestParam String id, String pw, HttpSession s) {
//		s.setAttribute("id", id);
//		s.setAttribute("pw", pw);
//		return "redirect:/quiz12/count";
//	}
//	
//	@GetMapping("count")
//	public String count(HttpSession s) {
//	    Integer count = (Integer) s.getAttribute("count");
//	    if(count == null) {
//	        count = 0;
//	    }
//	    if(s.getAttribute("id") != null)
//	    	count++;
//	    s.setAttribute("count", count);
//		return "quiz/quiz12/count";
//	}
//	
//	@GetMapping("logout")
//	public String logout(HttpSession s) {
//		s.invalidate();;
//		return "redirect:/quiz12/count";
//	}
	@GetMapping("/quiz12/login")
	public String login(HttpSession session) {
		if(session.getAttribute("loginId") != null)
			return "redirect:/quiz12/count";
		return "quiz/quiz12/login";
	}
	
	@PostMapping("/quiz12/login")
	public String loginAction(HttpServletRequest request) {
		
		System.out.println(request.getParameter("id"));
		System.out.println(request.getParameter("pw"));
		
		//id pw 맞나? 과정
		// 성공으로 간주함.
		// 세션에 해당 ID를 로그인한 아이디로 저장하고
		// /quiz12/count 페이지로 이동시킨다.
		
		HttpSession session = request.getSession();
		session.setAttribute("loginId", request.getParameter("id"));
		session.setAttribute("count", 0); //현재 세션에 대한 count 값 초기화 
		
		//return "quiz/quiz12/count"; //view 자원
		return "redirect:/quiz12/count"; 
	}
	
	@GetMapping("/quiz12/count")
	public String count(Model model, HttpSession session) {
		
		//loginId
		//int count = 0;
		//model.addAttribute("count", count++);
		
		
		//session.getAttribute("loginId")
		//session.getAttribute("count") == null -> 1
		//else +1
		
		//로그인한 사용자가있다 -> count값 초기화도 되어있다
		//count 값이 있어야 수행
		//loginId 가 존재해야 수행
		
		//if(session.getAttribute("count") != null) {
		if(session.getAttribute("loginId") != null) {
			// count = count+1;
			session.setAttribute("count", (Integer)(session.getAttribute("count")) + 1 );
		} else { //로그인한 사용자가 없다
			model.addAttribute("count", 0);
		}
		
		
		return "quiz/quiz12/count";
	}
	
	@GetMapping("/quiz12/logout")
	public String logout(HttpSession session) {
		
		// 세션에 저장되어있는 기록을 삭제하고 로그인한 이력이 없는것
		// /quiz12/count 페이지로 이동 시킨다
		
		//session.removeAttribute("loginId");
		//session.removeAttribute("count");
		session.invalidate(); //다 삭제 초기화
		
		//return "quiz/quiz12/count";
		return "redirect:/quiz12/count";
	}
	
}
