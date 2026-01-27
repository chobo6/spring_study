package com.app.controller.study.quiz.quiz05;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/quiz05")
public class Quiz05Controller {
	
//	아래 요청 경로에 따라서 화면에 표시될 페이지와 정보를 작성하세요!
//	localhost:8080/quiz05/pathA -> 퀴즈 페이지 A 상품정보 표시
//	localhost:8080/quiz05/pathB -> 퀴즈 페이지 B 상품정보 표시
//	localhost:8080/quiz05/pathCommon/A -> 퀴즈 페이지 A 상품정보 표시
//	localhost:8080/quiz05/pathCommon/B -> 퀴즈 페이지 B 상품정보 표시

	//1) 각각 경로별 독립적인 개별 페이지 
	/*
	@GetMapping("/pathA")
	public String pathA() {
		return "quiz/quiz05/pathA";
	}
	
	@GetMapping("/pathB")
	public String pathB() {
		return "quiz/quiz05/pathB";
	}
	
	@GetMapping("/pathCommon/A")
	public String pathCommonA() {
		return "quiz/quiz05/pathCommon/A";
	}
	
	@GetMapping("/pathCommon/B")
	public String pathCommonB() {
		return "quiz/quiz05/pathCommon/B";
	}
	*/
	
	//2) 상품정보기준 A정보, B정보 2개 페이지로 사용
	/*

	@GetMapping("/pathA")
	public String pathA() {
		return "quiz/quiz05/pathA";
	}
	
	@GetMapping("/pathB")
	public String pathB() {
		return "quiz/quiz05/pathB";
	}
	
	@GetMapping("/pathCommon/A")
	public String pathCommonA() {
		return "quiz/quiz05/pathA";
	}
	
	@GetMapping("/pathCommon/B")
	public String pathCommonB() {
		return "quiz/quiz05/pathB";
	}
	
	*/
	
	//2) 경로 모아서 표시
//	@GetMapping(value = {"/pathA", "/pathCommon/A"})
//	public String pathA() {
//		return "quiz/quiz05/pathA";
//	}
//	
//	@GetMapping(value = {"/pathB", "/pathCommon/B"})
//	public String pathB() {
//		return "quiz/quiz05/pathB";
//	}
	
	// 3) pathA, pathB 는 개별 페이지, pathCommon 공통 페이지
	/*
	@GetMapping("/pathA")
	public String pathA() {
		return "quiz/quiz05/pathA";
	}
	
	@GetMapping("/pathB")
	public String pathB() {
		return "quiz/quiz05/pathB";
	}
	
	@GetMapping("/pathCommon/A")
	public String pathCommonA(Model model) {
		model.addAttribute("productName", "A");
		return "quiz/quiz05/pathCommon/common";
	}
	
	@GetMapping("/pathCommon/B")
	public String pathCommonB(Model model) {
		model.addAttribute("productName", "B");
		return "quiz/quiz05/pathCommon/common";
	}
	*/
	
	// 3) pathCommon/{pathVariable} 방식으로 사용
	@GetMapping("/pathA")
	public String pathA() {
		return "quiz/quiz05/pathA";
	}
	
	@GetMapping("/pathB")
	public String pathB() {
		return "quiz/quiz05/pathB";
	}
	
	@GetMapping("/pathCommon/{pk}")
	public String pathCommonA(@PathVariable String pk, Model model) {
		
		//pk 값 확인 -> DB 조회
		// DB조회한 데이터 -> view 데이터 전달
		// view 화면에서 양식에 맞게 표현
		
		model.addAttribute("productName", pk);
		
		return "quiz/quiz05/pathCommon/common";
	}
	
	//4) 모든 요청 경로가 하나의 공통페이지 사용
	/*
	@GetMapping("/pathA")
	public String pathA(Model model) {
		model.addAttribute("productName", "A");
		return "quiz/quiz05/pathCommon/common";
	}
	
	@GetMapping("/pathB")
	public String pathB(Model model) {
		model.addAttribute("productName", "B");
		return "quiz/quiz05/pathCommon/common";
	}
	
	@GetMapping("/pathCommon/A")
	public String pathCommonA(Model model) {
		model.addAttribute("productName", "A");
		return "quiz/quiz05/pathCommon/common";
	}
	
	@GetMapping("/pathCommon/B")
	public String pathCommonB(Model model) {
		model.addAttribute("productName", "B");
		return "quiz/quiz05/pathCommon/common";
	}
	*/
}








