package com.app.controller.study.quiz.quiz06;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/quiz06")
public class Quiz06Controller {
	
	@GetMapping("/ask-bmi")
	public String ask() {
		return "quiz/quiz06/ask";
	}
	
	@PostMapping("/result-bmi")
	public String result(BmiDTO dto) {

		double h = dto.getHeight() / 100.0;
		double bmi = dto.getWeight() / (h * h);
		dto.setBmi(bmi);

//		model.addAttribute("dto", dto);

		return "quiz/quiz06/result";
	}
}
