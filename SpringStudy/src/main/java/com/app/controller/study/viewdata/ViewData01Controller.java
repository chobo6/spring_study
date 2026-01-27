package com.app.controller.study.viewdata;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.dto.study.Product;

@Controller
public class ViewData01Controller {
	
	/*
 		FE(사용자 client)	--> request(param) --> BE(Server)    <--> DB
 		(html/css/js)						  요청 파라미터 확인
 												비즈니스 로직
 				(html/css/js)	<--	(jsp) <-- return view 자원
 									 [view에 필요한 데이터]
	 */
	
	@GetMapping("/viewData1")
	public String viewData1(HttpServletRequest request) {
		
		// localhost:8080/viewData1?p1=abc
		System.out.println("/viewData1 요청");
		// 요청에 담긴 파라미터 확인
		System.out.println(request.getParameter("p1"));
		
		//view 에 데이터 전달
		//request.setAttribute(key, value)
		request.setAttribute("store", "맘스터치"); //key value
		request.setAttribute("menu", "싸이버거");
		
		
		return "viewData/viewData1";
	}
	
	@GetMapping("/viewData2")
	public String viewData2(Model model) {
		// localhost:8080/viewData2
		System.out.println("/viewData2 요청");
		model.addAttribute("store", "달식당");
		model.addAttribute("menu", "김치우동");
		
		return "viewData/viewData1";
	}
	
	@GetMapping("/viewData3")
	public ModelAndView viewData3() {
		// localhost:8080/viewData3
		System.out.println("/viewData3 요청");
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("viewData/viewData1");
		mav.addObject("store", "김밥천국");
		mav.addObject("menu", "참치김밥");
		
		return mav;
	}
	
	@GetMapping("/viewData4")
	public ModelAndView viewData4(ModelAndView mav) {
		// localhost:8080/viewData4
		System.out.println("/viewData4 요청");
		
		mav.setViewName("viewData/viewData1");
		mav.addObject("store", "전주해장");
		mav.addObject("menu", "콩나물비빔밥");
		
		return mav;
	}
	
	@GetMapping("/viewData5")
	public String viewData5(Model model) {
		System.out.println("/viewData5 요청");
		
		//객체 전달
		Product product = new Product();
		product.setId("pdid");
		product.setName("nameprd");
		product.setPrice(123000);
		
		model.addAttribute("product", product);

		model.addAttribute("store", "달식당");
		model.addAttribute("menu", "김치우동");
		
		return "viewData/viewData5";
	}

	@GetMapping("/viewData6")
	public String viewData6(HttpServletRequest request) {
		
		// 프로그램 
		// 매개변수 -> 로직 -> 리턴(반환)
		
		// localhost:8080/viewData6?search=커피
		
		//요청 들어온 파라미터
		String search = request.getParameter("search");
		
		//view Data 전달
		//request.setAttribute("", "");
		
		
		request.setAttribute("store", "오늘의 카페");
		request.setAttribute("menu", "음료다수~");
		
		if(search.equals("커피")) {
			Product p = new Product();
			p.setId("coffee");
			p.setName("아메리카노");
			p.setPrice(5000);
			
			request.setAttribute("product", p);
		} else {
			Product p = new Product();
			p.setId("tea");
			p.setName("밀크티");
			p.setPrice(6000);
			
			request.setAttribute("product", p);
		}
		
		return "viewData/viewData5";
	}
	
	
	@GetMapping("/viewData7")
	public String viewData7(@RequestParam(required = false) String search, Model model) {
		
		model.addAttribute("store", "오늘의 카페");
		model.addAttribute("menu", "음료다수~");
		
		//serach null
		if(search != null) {
			if(search.equals("커피")) {
				Product p = new Product();
				p.setId("coffee");
				p.setName("카페라떼");
				p.setPrice(5500);
				
				model.addAttribute("product", p);
			} else {
				Product p = new Product();
				p.setId("tea");
				p.setName("허브민트티");
				p.setPrice(6500);
				
				model.addAttribute("product", p);
			}
		}
		
		return "viewData/viewData5";
	}
}









