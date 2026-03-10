package com.app.controller.study.rs;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.util.LoginManager;

@RestController
public class ReactSpringAPIController {
	
	//API 통신용 컨트롤러
	
	@GetMapping("/api/getMsg")
	public String getMsg() {
		System.out.println("/api/getMsg");
		return "spring rest api getMsg";
	}
	
	@GetMapping("/api/getDrinks")
	public List<DrinkItem> getDrinks(){
		
		System.out.println("/api/getDrinks");
		
		List<DrinkItem> drinkList = new ArrayList<DrinkItem>();
		drinkList.add(new DrinkItem("아메리카노","커피"));
		drinkList.add(new DrinkItem("카페라떼","커피"));
		drinkList.add(new DrinkItem("생강차","차"));
		
		//api 통신용 임의로 생성 return 
		
		// Service -> DAO -> DB 
		
		return drinkList;
	}
	
	@GetMapping("/api/getDrinksDiv")
	//public List<DrinkItem> getDrinksDiv(HttpServletRequest request){
	public List<DrinkItem> getDrinksDiv(@RequestParam String type){
		
		System.out.println("/api/getDrinksDiv " + type );
		List<DrinkItem> drinkList = new ArrayList<DrinkItem>();
		
		if(type.equals("커피")) {
			drinkList.add(new DrinkItem("아메리카노","커피"));
			drinkList.add(new DrinkItem("카페라떼","커피"));
			drinkList.add(new DrinkItem("카푸치노","커피"));
		}
		
		if(type.equals("차")) {
			drinkList.add(new DrinkItem("페퍼민트","차"));
			drinkList.add(new DrinkItem("쌍화차","차"));
			drinkList.add(new DrinkItem("생강차","차"));
		}
		
		return drinkList;
	}
	
	@PostMapping("/api/getDrinksNum")
	public List<DrinkItem> getDrinksNum(@RequestBody String num){
		//					jsonformat text -> DTO객체 
		// 	DTO객체 -> 변수 값 -> 로직 -> DB -> return 
		
		System.out.println("/api/getDrinksNum");
		System.out.println(num);
		
		//name{"num":0,"type":"test"}
		//type{"num":0,"type":"test"}
		
		List<DrinkItem> drinkList = new ArrayList<DrinkItem>();
		drinkList.add(new DrinkItem("name"+num, "type"+num));
		
		return drinkList;
	}
	
	
//  사용자입력한 id, pw          
	// json   {id : 'abc', pw:'1234qwer'}
	@PostMapping("/api/login")  
	public String login(@RequestBody Member member, HttpServletRequest request) {
		
		System.out.println(member.getId());
		System.out.println(member.getPw());
		
		// id,pw 확인 -> Service,DAO -> DB T_User 정보

		// id, pw 맞는지 확인
		
		// 로그인 성공 return "loginOk";
		// 로그인 실패 return "fail";  "no";
		// 단순 텍스트 리턴
		
		
		// return apiResponse  (header, body)
		// resultCode, resultMsg
		
		// 로그인 성공했다고 치고 
		
		// 로그인 유지
		// Session 에 로그인 여부 처리 -> 로그인 사용자 id 저장
		LoginManager.setSessionLoginUserId(request, member.getId());
			
		
		return "loginOk";
	}
	
	@PostMapping("/api/loginCheck")
	public String loginCheck(HttpServletRequest request) {
		
		if(LoginManager.isLogin(request)) { //로그인 되어있나 체크
			String loginId = LoginManager.getLoginUserId(request);
			System.out.println(loginId);
			
			return "login user : " + loginId;
		} else {
			return "is not login";
		}
	}
}

















