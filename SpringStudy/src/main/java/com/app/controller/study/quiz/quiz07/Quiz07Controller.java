package com.app.controller.study.quiz.quiz07;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Quiz07Controller {

	
	//1. localhost:8080/practice07/listTest    ?type=str 경로로 접근시

	//2. localhost:8080/practice07/listTest    ?type=member 경로로 접근시
	
	@GetMapping("/quiz07/listTest")
	public String listTest(@RequestParam(required = false) String type, Model model) {
		
		//---화면처리 케이스
		
		// 1) type 으로 구분해서 별도의 페이지로 구성
		
		/*
		if(type.equals("str")) {  //str
			//str 반복 처리 
			// 1) view 안에서 고정 텍스트 (하드코딩)
			// 2) view 안에서 고정 텍스트 반복처리 (하드코딩)
			// 3) 출력할 메시지를 전달하여 그걸 반복   /  고정메시지 몇회 반복
			// 4) 리스트 형태로 전달 -> 반복
			
			model.addAttribute("msg", "!!스트링 리스트입니다!!");
			model.addAttribute("count", 8);
			
			List<String> strList = new ArrayList<String>();
			for(int i=1; i<=5; i++)
				strList.add("List 스트링 리스트입니다!!");
			
			model.addAttribute("strList", strList);
			
			
			return "quiz/quiz07/str";
			
		} else {  //member
			
			List<Member> memberList = new ArrayList<Member>();
			
			for(int i=1; i<=5; i++) {
				memberList.add(new Member("아이디"+i, "비번"+i, "이름"+i));
			}
			
//			memberList.add(new Member("아이디1", "비번1", "이름1"));
//			memberList.add(new Member("아이디2", "비번2", "이름2"));
			
			model.addAttribute("memberList", memberList);
			
			return "quiz/quiz07/member";
		}
		
		
		//if(type.equals("member")) {}
		*/
		
		// 2) 같은 동일 페이지 사용 -> type 따라서 동적으로 구분 
		System.out.println("parameter : " + type);
		model.addAttribute("type", type); //str member
		
		//각 type 파라미터 기준으로 구분해서 필요한 연산작업만 수행
		
		if(type.equals("str")) { 
			model.addAttribute("msg", "스트링 리스트입니다!!");	
		} else {
			List<Member> memberList = new ArrayList<Member>();
			
			for(int i=1; i<=5; i++) {
				memberList.add(new Member("아이디"+i, "비번"+i, "이름"+i));
			}
			model.addAttribute("memberList", memberList);
		}
		
		return "quiz/quiz07/listTest";
		
	}
	
	//quiz09 로 인한 추가사항
//	@GetMapping("/quiz07/listTest/str")
//	public String listTestStr(@RequestParam(required = false) String type, Model model) {
//	}
//	
//	@GetMapping("/quiz07/listTest/member")
//	public String listTestMember(@RequestParam(required = false) String type, Model model) {
//	}
	
	@GetMapping("/quiz07/listTest/{type}")
	public String listTestType(@PathVariable String type, Model model
				, HttpServletRequest request) {
		
		/*
		//같은 로직을 똑같이 수행
		
		model.addAttribute("type", type); //str member
		
		//각 type 파라미터 기준으로 구분해서 필요한 연산작업만 수행
		
		if(type.equals("str")) { 
			model.addAttribute("msg", "스트링 리스트입니다!!");	
		} else {
			List<Member> memberList = new ArrayList<Member>();
			
			for(int i=1; i<=5; i++) {
				memberList.add(new Member("아이디"+i, "비번"+i, "이름"+i));
			}
			model.addAttribute("memberList", memberList);
		}
		
		return "quiz/quiz07/listTest";
		*/
		
		System.out.println("@PathVariable : " + type);
		
		//기존로직 활용
		if(type.equals("str")) {
			return "forward:/quiz07/listTest?type=" + type;
		}
		else {
			System.out.println(request.getParameter("type"));
			if(request.getParameter("type") == null) {
				return "forward:/quiz07/listTest?type=" + type;	
			} else {
				return "forward:/quiz07/listTest";
			}
			
		}
		
	}
	
}














