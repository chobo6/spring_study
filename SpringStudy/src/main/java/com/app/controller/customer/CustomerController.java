package com.app.controller.customer;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.common.CommonCode;
import com.app.dto.user.User;
import com.app.service.user.UserService;

@Controller
public class CustomerController {
	// 고객 사용자 관련된 서비스 (계정 관련...)

	@Autowired
	UserService userSerivce;

	@GetMapping("/customer/signup")
	public String signup() {
		return "customer/signup";
	}

	@PostMapping("/customer/signup")
	public String signupAction(User user) {
		
		//사용자 회원가입 -> db 저장
		
		int result = userSerivce.saveCustomerUser(user);
		
		if(result > 0) { //성공
			return "redirect:/main";
		} else { //실패 
			return "customer/signup";	
		}
	}
	
	@GetMapping("/customer/signin")
	public String signin() {
		return "customer/signin";
	}
	
	@PostMapping("/customer/signin")
	public String signinAction(User user, HttpSession session) {
		
		//로그인 처리 로직
		System.out.println("사용자 입력한 값");
		System.out.println(user);
		
		// 사용자 입력한 id pw  비교
		// 		-> DB 조회

		// userType -> 사용자가 접속하는 페이지
		// admin 관리자가 접속하는 페이지
		
		user.setUserType( CommonCode.USER_USERTYPE_CUSTOMER );  
		User loginUser = userSerivce.checkUserLogin(user); //서비스내부에서 userType까지 비교
		
		if(loginUser == null) {  //실패
			System.out.println("로그인 실패");
			return "customer/signin";
		} else { //성공
			System.out.println("로그인 성공");
			System.out.println(loginUser);
			
			//로그인 성공 아이디 세션에 저장
			session.setAttribute("loginUserId", loginUser.getId());
			
//			return "redirect:/main";
			return "redirect:/customer/mypage";
		}
		
		
		//로그인 성공 -> 로그인 성공한 사용자 아이디 -> session 영역 저장
		// -> 성공한 이후 페이지 연결 (메인 / 마이페이지)
		
		//로그인 실패 -> 로그인 시도 화면
	}
	
	@GetMapping("/customer/mypage")
	public String mypage(HttpSession session, Model model) {
		
		// 로그인을 한 상태면? -> session 에 로그인 사용자 아이디가 존재
		
		if(session.getAttribute("loginUserId") != null) {
			
			//								session 에서 로그인 사용자 ID 확인
			String loginUserId = session.getAttribute("loginUserId").toString();
			
			// DB에서 user 데이터 조회
			User user = userSerivce.findUserById(loginUserId);
			
			// view 데이터 전달
			model.addAttribute("user", user);
			
			return "customer/mypage";
		}
		
		// 로그인 안되어있으면? -> 로그인 페이지로 연결
		return "redirect:/customer/signin";
	}
	
	@GetMapping("/customer/signout")
	public String signout(HttpSession session) {
		
		// 세션 초기화
		session.invalidate();
		
		return "redirect:/main";
	}
}
