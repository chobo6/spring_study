package com.app.controller.study.quiz.quiz10;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/quiz10")
public class Quiz10Controller {
	@GetMapping("/A")
	public String a(HttpSession session, Model model) {
		model.addAttribute("msg",(String)session.getAttribute("msg"));
		session.removeAttribute("msg");
		return "quiz/quiz10/a";
	}
	
	@GetMapping("/B")
	public String b(HttpSession session) {
		session.setAttribute("msg", "FromB");
		return "quiz/quiz10/b";
	}
}
