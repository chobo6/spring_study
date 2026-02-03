package com.app.controller.study.quiz.quiz15;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Quiz15CustomerController {

	@Autowired
	Quiz15CustomerService quiz15CustomerService;
	
	//고객 신규 등록 페이지
	@GetMapping("/quiz15/registerCustomer")
	public String registerCustomer() {
		//등록하는 페이지로 연결
		return "quiz/quiz15/registerCustomer";
	}
	
	//고객 신규 등록 처리
	@PostMapping("/quiz15/registerCustomer")
	public String registerCustomerAction(@ModelAttribute Quiz15Customer quiz15Customer) {
		//등록페이지에서 입력한 정보를 바탕으로 -> 파라미터 전달
		// -> DB 저장
		// Controller -> Service -> DAO -> DB
		//           DTO        DTO    DTO
		
		System.out.println("[controller] registerCustomer");
		int result = quiz15CustomerService.saveCustomer(quiz15Customer);
		
		if(result > 0 ) {  //적용된 행의 갯수  1  -> 저장 성공
			return "quiz/quiz15/registerCustomer";
		} else {
			return "quiz/quiz15/registerCustomer";
		}
		
	}
	
	
}
