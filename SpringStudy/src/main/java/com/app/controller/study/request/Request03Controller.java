package com.app.controller.study.request;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.dto.study.Product;
import com.app.dto.study.ProductRequestForm;

@Controller
@RequestMapping("/request03")
public class Request03Controller {
	
	@GetMapping("/param1")
	public String param1(HttpServletRequest request) {
		// localhost:8080/request03/param1?msg=hi&page=1&level=5
		
		System.out.println("/request03/param1");
		System.out.println(request.getParameter("msg"));
		System.out.println(request.getParameter("page"));
		System.out.println(request.getParameter("level"));
		
		return "req/param/param1";
	}
	
	@PostMapping("/param2")
	public String param2(HttpServletRequest request) {
		// localhost:8080/request03/param2?name=abc&count=123
		System.out.println("/request03/param2");
		System.out.println(request.getParameter("name"));
		System.out.println(request.getParameter("count"));
		
		return "req/param/param2";
	}
	
	@GetMapping("/param3")
	public String param3(HttpServletRequest request) {
		// localhost:8080/request03/param3?p1=10&p2=20&p3=30
		
		System.out.println("/request03/param3");
		System.out.println(request.getParameter("p1"));
		System.out.println(request.getParameter("p2"));
		//System.out.println(request.getParameter("p3"));  //단일 파라미터 값


		// localhost:8080/request03/param3?p1=10&p2=20&p3=30&p3=40
		//여러개 복수개 파라미터 값
		String[] p3Values = request.getParameterValues("p3");
		for(String s : p3Values) {
			System.out.println(s);
		}
		
		return "req/param/param2";
	}
	
	@RequestMapping("/param4")
	public String param4(@RequestParam String p1, @RequestParam String p2) {
		// localhost:8080/request03/param4?p1=10&p2=20
	
		System.out.println("/request03/param4");
		System.out.println(p1);
		System.out.println(p2);
		
		return "req/param/param2";
	}
	
	@RequestMapping("/param5")
	public String param5(@RequestParam String p1, @RequestParam(required = false) String p2) {

		// localhost:8080/request03/param5?p1=10&p2=20
		// localhost:8080/request03/param5?p1=10
		
		System.out.println("/request03/param5");
		System.out.println(p1);
		System.out.println(p2);
		
		return "req/param/param2";
	}
	
	@RequestMapping("/param6")
	public String param6(@RequestParam String p1, 
						@RequestParam(required = false, defaultValue = "p2Default") String p2) {
		
		// localhost:8080/request03/param6?p1=10
		System.out.println("/request03/param6");
		System.out.println(p1);
		System.out.println(p2);
		
		return "req/param/param2";
	}
	
	@RequestMapping("/param7")
	public String param7(@RequestParam Map<String, String> paramMap) {
							//요청에 담겨온 파라미터 값들이 key:value 형태로 저장되어 전달됨

		// localhost:8080/request03/param7?p1=10&p2=50&count=99
		System.out.println("/request03/param7");
		System.out.println(paramMap.get("p1"));
		System.out.println(paramMap.get("p2"));
		System.out.println(paramMap.get("count"));
		
		return "req/param/param2";
	}
	
	@GetMapping("/param8")  //GET 조회/읽기  요청 파라미터에 따라서 화면 연결 
	public String param8() {
		
		System.out.println("/request03/param8 [GET 요청]");
		return "req/param/param8";
	}
	
	@PostMapping("/param8")  //POST 쓰기/저장 데이터 전달 -> 실제 데이터 받고 로직 처리
	//public String param8Action(Stirng s1) {
	//public String param8Process(Stirng s1) {
	//public String param8(@ModelAttribute Product product) {   //dto 객체
	public String param8(@ModelAttribute ProductRequestForm product) {   //dto 객체
		System.out.println("/request03/param8 [POST 요청]");
		
		//처리하는 로직
		System.out.println(product);
		
		//if(product.getType()) {  buy -> table
		//
		//}   refund -> table
		
		return "req/param/param8after";
	}
	
	
	// localhost:8080/request03/param9?itemId=123
	// localhost:8080/request03/param9/123
	@RequestMapping("/param9/{itemId}")
	public String param9(@PathVariable String itemId) {
		System.out.println("/request03/param9/{itemId}");
		System.out.println(itemId);
		
		return "req/param/param2";
	}

	
}















