package com.app.controller.study.rest;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.common.CommonCode;
import com.app.dto.user.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//@Controller  //기본 return 이 view 자원
@RestController   //기본 return 이 text리턴     
public class Rest02Controller {
	// Controller 역할 + RESTAPI 형식 기준 통신용 컨트롤러
	//내부 메소드가 모두 restapi 형식 -> text 리턴

	@GetMapping("/rest/rest04")
	public String rest04() {
		return "rest/rest04";
	}
	
	@GetMapping("/rest/rest05")
	public String rest05() {
		return "etc text test rest01 hahaha";
	}
	
	// 단순 텍스트 -> JSON 포맷
	
	@GetMapping("/rest/rest06")
	public String rest06() {
		
		//json format 리턴
		
		//json format 으로 텍스트를 만들어서!
		
		/*
			abc,abc1,abc,abc3
			abc|abc1|abc2|abc3|ddd
			
			{
				"id":"abc",
				"pw":"abcde",
				"name":"nameabc",
				"userType":"CUS"
			}
		 */
		
		//1) 단순 텍스트로 json
	
		String result = " { "
				+ " \"id\" : \"abc\" , "
				+ " \"pw\" : \"abcd\" , "
				+ " \"name\" : \"aaaname\" , "
				+ " \"userType\" : \"CUS\"  "
				  + " } ";
		
		return result;
	}
	
	@GetMapping("/rest/rest07")
	public String rest07() {
		
		// 2) 라이브러리 활용
		// json-simple
		JSONObject obj = new JSONObject();
		obj.put("id", "abc");
		obj.put("pw", "nabcpw");
		obj.put("name", "aaaname");
		obj.put("userType", "CUS");
		
		String result = obj.toJSONString();
		return result;
	}
	
	@GetMapping("/rest/rest08")
	public String rest08() {
		// 3) 라이브러리 활용
		// jackson 
		
		User user = new User();
		user.setId("abc");
		user.setPw("owowowpw");
		user.setName("namekk");
		user.setUserType(CommonCode.USER_USERTYPE_CUSTOMER);
		
		//객체 -> JSON 포맷 텍스트
		
		ObjectMapper mapper = new ObjectMapper();
		String result = null;
		
		try {
			result = mapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		//json format string return
		return result;
	}
	
	@GetMapping("/rest/rest09")
	public User rest09() {
		User user = new User();
		user.setId("abcid");
		user.setPw("pwpwwwpw");
		user.setName("nnnnkk");
		user.setUserType(CommonCode.USER_USERTYPE_ADMIN);
		
		// @ResponseBody   or  @RestController
		// REST API 통신 text로 반환하는 상황
		// 객체를 리턴 -> 객체가 JSON 포맷으로 변환되어서 -> return
		// **주의**조건 : jackson 라이브러리가 존재 (pom.xml 의존성 추가 확인)
		
		return user;
	}
	
	@GetMapping("/rest/rest10")
	public String rest10() {
		
		return "this is RESTAPI 응답 텍스트 입니다.";		
	}
}












