package com.app.controller.study.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Rest01Controller {

	
	@GetMapping("/rest/rest01")
	public String rest01() {
		
		//view 자원에 대한 경로 return
		return "rest/rest01";
	}
	
	//rest api -> text 리턴 (json 포맷)

	@ResponseBody
	@GetMapping("/rest/rest02")
	public String rest02() {
		return "rest/rest02";
		//view자원X  단순 Text return  
		//문자열 그대로 리턴
	}
	
	@ResponseBody
	@GetMapping("/rest/rest03")
	public String rest03() {
		return "this is return text";
		//view자원X  단순 Text return  
		//문자열 그대로 리턴
	}
	
}
