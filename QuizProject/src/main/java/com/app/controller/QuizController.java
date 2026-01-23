package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class QuizController {

	@RequestMapping("/main")
	public String main() {

		return "main";
	}
	
	@RequestMapping("/product")
	public String product() {

		return "product";
	}
	
	@RequestMapping("/mypage")
	public String mypage() {

		return "mypage";
	}
}