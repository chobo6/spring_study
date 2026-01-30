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
	public String first(Model model) {

		model.addAttribute("accessUrl", "/first");

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
}
