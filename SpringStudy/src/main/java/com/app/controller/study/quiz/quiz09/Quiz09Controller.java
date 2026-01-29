package com.app.controller.study.quiz.quiz09;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Quiz09Controller {

	//http://localhost:8080/quiz09/listTest?type=member
	@GetMapping("/quiz09/listTest")
	public String listTest(@RequestParam(required = false) String type) {
		
		//http://localhost:8080/quiz09/listTest?type=member
		// -> http://localhost:8080/quiz07/listTest/member
		// *단, url 주소는 유지 (최종 브라우저 표시 경로 : /quiz09/listTest?type=member) -> forward
		
		//http://localhost:8080/quiz09/listTest?type=str
		// -> http://localhost:8080/quiz07/listTest/str
		// *단, url 주소도 변경 (최종 브라우저 표시 경로 : /quiz07/listTest/str) -> redirect

		if(type.equals("str")) {
			return "redirect:/quiz07/listTest/str";
		} else {  //member
			return "forward:/quiz07/listTest/member";
		}

	}
}
