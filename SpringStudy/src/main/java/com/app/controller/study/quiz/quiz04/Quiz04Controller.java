package com.app.controller.study.quiz.quiz04;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/quiz04")
public class Quiz04Controller {

//	1. /quiz04/request1-x?category=100&product=4000 요청 시, 값들 출력 케이스
//	  Console창에 println 으로 출력 (사용자 요청을 서버가 읽어서 확인 가능한지)

	//    1) request 활용 (/request1-1)
	@GetMapping("/request1-1")
	public String request1_1(HttpServletRequest request) {
		
		System.out.println("/request1-1");
		System.out.println(request.getParameter("category"));
		System.out.println(request.getParameter("product"));
		
		return "quiz/quiz04/req";
	}
	
//    2) RequestParam 활용 (/request1-2)
	@GetMapping("/request1-2")
	public String request1_2(@RequestParam String category,
							 @RequestParam String product) {
		
		System.out.println("/request1-2");
		System.out.println(category);
		System.out.println(product);
		
		return "quiz/quiz04/req";
	}
	
	//   /quiz04/request1-x?category=100&product=4000
//    3) Dto 객체 활용 (/request1-3)
	@GetMapping("/request1-3")
	public String request1_3(@ModelAttribute Product product) {
		
		System.out.println("/request1-3");
		System.out.println(product);
		System.out.println(product.getCategory());
		System.out.println(product.getProduct());
		
		return "quiz/quiz04/req";
	}
	
//	4) Map 활용 (/request1-4)
	@GetMapping("/request1-4")
	public String request1_4(@RequestParam Map<String, String> paramMap) {
		
		System.out.println("/request1-4");
		System.out.println(paramMap.get("category"));
		System.out.println(paramMap.get("product"));
		
		return "quiz/quiz04/req";
	}
//	
//	2. /quiz04/viewData1-x요청시 화면(view .jsp)에 값 출력
//	* 화면 전달된 값은 아래와 같이 작성시 출력되어야 함
//	*전달되는 값은 임의로 작성 "넘어간 값"
//	
//	1) request 활용  ( /viewData1-1)
	@GetMapping("/viewData1-1")
	public String viewData1_1(HttpServletRequest request) {
		System.out.println("/quiz04/viewData1-1");
		request.setAttribute("response001", "싸이버거");
		request.setAttribute("response099", "햄버거");
		
		return "quiz/quiz04/viewData";
	}
	
//	2) Model 활용    /viewData1-2
	@GetMapping("/viewData1-2")
	public String viewData1_2(Model model) {
		System.out.println("/quiz04/viewData1-2");
		model.addAttribute("response001", "순대국밥");
		model.addAttribute("response099", "한식");
		
		return "quiz/quiz04/viewData";
	}
	
//	3) ModelAndView 활용   /viewData1-3
	@GetMapping("/viewData1-3")
	public ModelAndView viewData1_3() {
		ModelAndView mav = new ModelAndView();
		
		System.out.println("/quiz03/viewData1-4");
		mav.setViewName("quiz/quiz04/viewData");
		mav.addObject("response001", "양념치킨");
		mav.addObject("response099", "치킨");
		
		return mav;
	}
	
	
//	4) ModelAttribute 객체 활용    /viewData1-4
	
	@GetMapping("/viewData1-4")
	public String viewData1_4(Model model) {
		System.out.println("/quiz04/viewData1-4");
		
		
		model.addAttribute("response001", "콩나물국밥");
		model.addAttribute("response099", "한식");
		
		//객체 전달
		TransferItem t = new TransferItem();
		//			transferItem
		
		t.setValue001("아침이다");
		t.setValue099("졸리다");
		
		model.addAttribute("transferItem", t);
		
		return "quiz/quiz04/viewData";
	}
}

