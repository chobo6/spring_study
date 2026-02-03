package com.app.controller.study.quiz.quiz14;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Quiz14Controller {

	//의존성 주입
	@Autowired
	CupBean cupBean;
	
	@Autowired
	PlateBean plateBean;
	
	@GetMapping("/quiz14/fullOrder")
	public String fullOrder() {
		System.out.println("컵에 담긴 커피음료 이름 : "  + cupBean.getCoffeeBean().getName());
		System.out.println("접시에 담긴 디저트 이름 : "  + plateBean.getDessertBean().getName());
		return "quiz/quiz14/quiz14";
	}
	
	@GetMapping("/quiz14/orderCoffee")
	public String orderCoffee () {
		System.out.println("컵에 담긴 커피음료 이름 : "  + cupBean.getCoffeeBean().getName());
		
		return "quiz/quiz14/quiz14";
	}
	
	@GetMapping("/quiz14/orderDessert")
	public String orderDessert () {
		System.out.println("접시에 담긴 디저트 이름 : "  + plateBean.getDessertBean().getName());
		
		return "quiz/quiz14/quiz14";
	}
}
