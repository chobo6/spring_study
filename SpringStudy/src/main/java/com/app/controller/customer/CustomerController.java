package com.app.controller.customer;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.common.ApiCommonCode;
import com.app.common.CommonCode;
import com.app.dto.api.ApiResponse;
import com.app.dto.api.ApiResponseHeader;
import com.app.dto.user.User;
import com.app.dto.user.UserDupCheck;
import com.app.service.user.UserService;
import com.app.util.LoginManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CustomerController {
	// 고객 사용자 관련된 서비스 (계정 관련...)

	@Autowired
	UserService userSerivce;

//	private static final Logger log = LogManager.getLogger(CustomerController.class);

	@GetMapping("/customer/signup")
	public String signup() {
		
		log.info("singup 페이지 접근함.");
		return "customer/signup";
	}

	@PostMapping("/customer/signup")
	public String signupAction(@Valid @ModelAttribute User user, BindingResult br) {
		
		// 유효성 검증
		
		// 검증 결과에 뭔가 문제가 있는가? (조건 위배)
		if(br.hasErrors()) { // true 위배된 게 있다
			List<ObjectError> errorList = br.getAllErrors();
			for(ObjectError er : errorList) {
				System.out.println(er.getObjectName());
				System.out.println(er.getDefaultMessage());
				System.out.println(er.getCode());
				System.out.println(er.getCodes()[0]);
			}
			
			return "customer/signup";	
		}
		
		//사용자 회원가입 -> db 저장
		
		int result = userSerivce.saveCustomerUser(user);
		
		if(result > 0) { //성공
			return "redirect:/main";
		} else { //실패 
			return "customer/signup";	
		}
	}
	
	@ResponseBody // 텍스트 응답
	@PostMapping("/customer/checkDupId")
	public String checkDupId(@RequestBody String data) {
							// 요청 body 데이터를 단순 텍스트로 수신
		System.out.println("/customer/checkDupId");
		System.out.println(data);
		
		// data = 중복인지 체크할 아이디
		// -> db 에서 중복된 사용자 아이디 있는지 체크
		
		boolean result = userSerivce.isDuplicatedId(data);
		System.out.println(result);
		
		if(result) { // true 중복
			return "Y"; // 1 T
		} else { // 중복 X
			return "N"; // 2 F
		}
	}
	
	@ResponseBody // json 요청 json 응답
	@PostMapping("/customer/checkDupIdJson")
	public ApiResponse<String> checkDupId(@RequestBody UserDupCheck userDupCheck) {
							// 요청 body 데이터에 json format text 가 담겨져있는 경우
							// key 값 == 필드변수	자동으로 객체로 파싱되어 데이터가 담김
		System.out.println("/customer/checkDupIdJson");
		System.out.println(userDupCheck);
		
		log.info("checkDupIdJson 아이디 중복체크 요청 값 {}", userDupCheck);
		
		// data = 중복인지 체크할 아이디
		// -> db 에서 중복된 사용자 아이디 있는지 체크
		
		boolean result = userSerivce.isDuplicatedId(userDupCheck.getId());
		System.out.println(result);
		
		// return ApiResponse<String>	body "Y" "N"
		
		ApiResponse<String> res = new ApiResponse<String>();
		
		// header
		ApiResponseHeader header = new ApiResponseHeader();
		header.setResultCode(ApiCommonCode.API_RESULT_SUCCESS);
		header.setResultMessage(ApiCommonCode.API_RESULT_SUCCESS_MSG);
		
		res.setHeader(header);
		
		// body
		
		if(result) { // true 중복
			res.setBody("Y");
		} else { // 중복 X
			res.setBody("N");
		}
		
		return res; // json 객체 변환
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
//			session.setAttribute("loginUserId", loginUser.getId());
			LoginManager.setSessionLoginUserId(session, loginUser.getId());
			
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
		
//		if(session.getAttribute("loginUserId") != null) {
		if(LoginManager.isLogin(session)) {
			
			//								session 에서 로그인 사용자 ID 확인
//			String loginUserId = session.getAttribute("loginUserId").toString();
			String loginUserId = LoginManager.getLoginUserId(session);
			
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
//		session.invalidate();
		LoginManager.logout(session);
		
		return "redirect:/main";
	}
	
	@GetMapping("/customer/modifyPw")
	public String modifyPw(HttpSession session, Model model) {
		if(LoginManager.isLogin(session)) {
			String loginUserId = LoginManager.getLoginUserId(session);
			User user = userSerivce.findUserById(loginUserId);
			model.addAttribute("user", user);
		}
		return "customer/modifyPw";
	}
	
	@PostMapping("/customer/modifyPw")
	public String modifyPwAction(User user) {
		
		System.out.println(user);
		
		// 비밀번호 변경 -> 연결 -> 서비스 -> dao
		
		// User 객체정보 전체를 넘겨서 update
		int result = userSerivce.modifyUser(user);
		
		if(result > 0) {
			return "redirect:/customer/mypage";
		} else {
			return "redirect:/customer/modifyPw";
		}
	}
	
	@GetMapping("/customer/modifyPw2")
	public String modfiyPw2() {
		return "customer/modifyPw2";
	}
	
	@PostMapping("/customer/modifyPw2")
	public String modifyPwAction2(HttpSession session, User user) {
		
		// 사용자가 입력한 바꿀 비밀번호 pw만 존재
		
		// 비밀번호를 바꾸려는 사용자 ID 세팅
		
		// mypage -> 비밀번호 변경 시도
		// --> 이미 로그인이 되어있다 -> session 영역에 로그인 사용자 아이디
		
		// set pw = ?
		// where id = ?
		
//		user.setId(session.getAttribute("loginUserId").toString());
		user.setId(LoginManager.getLoginUserId(session));
		
		System.out.println(user);
		int result = userSerivce.modifyUserPw(user);
		
		if(result > 0) {
			return "redirect:/customer/mypage";
		} else {
			return "redirect:/customer/modifyPw2";
		}
	}
}
