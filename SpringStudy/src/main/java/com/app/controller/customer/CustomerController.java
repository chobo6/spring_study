package com.app.controller.customer;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.app.common.ApiCommonCode;
import com.app.common.CommonCode;
import com.app.controller.admin.AdminController;
import com.app.controller.study.viewdata.ViewData01Controller;
import com.app.dto.api.ApiResponse;
import com.app.dto.api.ApiResponseHeader;
import com.app.dto.file.FileInfo;
import com.app.dto.user.User;
import com.app.dto.user.UserDupCheck;
import com.app.dto.user.UserProfileImage;
import com.app.dto.user.UserProfileRequestForm;
import com.app.dto.user.UserValidError;
import com.app.service.file.FileService;
import com.app.service.user.UserService;
import com.app.util.FileManager;
import com.app.util.LoginManager;
import com.app.validator.UserCustomValidator;
import com.app.validator.UserValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CustomerController {

	//고객 사용자 관련된 서비스 (계정 관련... )
	
	@Autowired
	UserService userService;
	
	@Autowired
	FileService fileService;
	
//	private static final Logger log = LogManager.getLogger(CustomerController.class);
	
	@GetMapping("/customer/signup")
	public String signup() {
		
		log.info("signup 페이지 접근함");
		
		return "customer/signup";
	}
	
//	@InitBinder("user")
//	public void initUserBinder(WebDataBinder binder) {
//		UserValidator userValidator = new UserValidator();
//		binder.setValidator(userValidator);
//	}
	
	@PostMapping("/customer/signup")
	public String signupAction( /*@Valid*/ @ModelAttribute User user, BindingResult br, Model model) {
		
		//유효성 검증
		
		//검증 결과에 뭔가 문제가 있는가? (조건 위배)
//		if(br.hasErrors()) { //true  위배된게 있다
//			
//			List<ObjectError> errorList = br.getAllErrors();
//			for(ObjectError er : errorList) {
//				System.out.println( er.getObjectName() );
//				System.out.println( er.getDefaultMessage() );
//				System.out.println( er.getCode() );
//				System.out.println( er.getCodes()[0] );
//			}
//			
//			return "customer/signup";
//		}
		
		//CustomValidator
		UserValidError userValidError = new UserValidError();
		boolean validResult = UserCustomValidator.validate(user, userValidError);
		
		if(validResult == false) {
			model.addAttribute("userValidError", userValidError);
			return "customer/signup";
		}
		
		
		//사용자 회원가입 -> db 저장
		
		int result = userService.saveCustomerUser(user);
		
		if(result > 0) { //성공
			return "redirect:/main";
		} else { //실패 
			return "customer/signup";	
		}
	}
	
	
	@ResponseBody  //텍스트 응답
	@PostMapping("/customer/checkDupId")
	public String checkDupId(@RequestBody String data) {  
						//요청 body 데이터를 단순 텍스트로 수신
		System.out.println("/customer/checkDupId");
		System.out.println(data);
		
		// data : 중복인지 체크할 아이디
		// -> db 에서 중복된 사용자 아이디 있는지 체크
		
		boolean result = userService.isDuplicatedId(data);
		System.out.println(result);
		
		if(result) { //true 중복
			return "Y";  //1  T  
		} else {  //증복 X 
			return "N";  //2  F
		}
	}
	
	
	@ResponseBody  //json 요청 json 응답
	@PostMapping("/customer/checkDupIdJson")
	public ApiResponse<String>  checkDupId(@RequestBody UserDupCheck userDupCheck) {  
					//요청 body 데이터에 json format text 가 담겨져있는 경우
					//key값 == 필드변수  자동으로 객체로 파싱되어 데이터가 담김
		System.out.println("/customer/checkDupIdJson");
		System.out.println(userDupCheck);
		
		log.info("checkDupIdJson 아이디 중복체크 요청 값 {}", userDupCheck);
		
		// data : 중복인지 체크할 아이디
		// -> db 에서 중복된 사용자 아이디 있는지 체크
		
		boolean result = userService.isDuplicatedId( userDupCheck.getId() );
		System.out.println(result);
		
		
		//return ApiResponse<String>    body "Y" "N"
		
		ApiResponse<String> res = new ApiResponse<String>();
		
		//header
		ApiResponseHeader header = new ApiResponseHeader();
		header.setResultCode( ApiCommonCode.API_RESULT_SUCCESS );
		header.setResultMessage( ApiCommonCode.API_RESULT_SUCCESS_MSG );
		
		res.setHeader(header);
		
		//body
		
		
		if(result) { //true 중복
			res.setBody("Y");  
		} else {  //증복 X 
			res.setBody("N");
		}
		
		return res;  //json 객체 변환 
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
		User loginUser = userService.checkUserLogin(user); //서비스내부에서 userType까지 비교
		
		if(loginUser == null) {  //실패
			System.out.println("로그인 실패");
			return "customer/signin";
		} else { //성공
			System.out.println("로그인성공");
			System.out.println(loginUser);
			
			//로그인 성공 아이디 세션에 저장
			//session.setAttribute("loginUserId", loginUser.getId());
			LoginManager.setSessionLoginUserId(session, loginUser.getId());
			
			//return "redirect:/main";

			//로그인 성공후에 마이페이지로 연결
			return "redirect:/customer/mypage"; 
		}
		
		
		//로그인 성공 -> 로그인 성공한 사용자 아이디 -> session 영역 저장
		// -> 성공한 이후 페이지 연결 (메인 / 마이페이지)
		
		//로그인 실패 -> 로그인 시도 화면
	}
	
	
	@GetMapping("/customer/mypage")
	public String mypage(HttpSession session, Model model) {
		
		//로그인을 한 상태면? -> session 에 로그인 사용자 아이디가 존재
		
		//if(session.getAttribute("loginUserId") != null) {
		if( LoginManager.isLogin(session) ) {
			
			//								session 에서 로그인 사용자 ID 확인
			//String loginUserId = session.getAttribute("loginUserId").toString();
			String loginUserId = LoginManager.getLoginUserId(session);
			
			// DB에서 user 데이터 조회
			User user = userService.findUserById(loginUserId);
			
			//view 데이터 전달
			model.addAttribute("user", user);
			
			
			//현재 mypage에 접속한 사용자가 프로필이미지가 있으면  프로필 파일 정보도 같이 view 전달
			UserProfileImage userProfileImage = userService.findUserProfileImageById(loginUserId);
			if(userProfileImage != null) {  //프로필 이미지 등록된게 있다!
				
				//프로필 사진 파일 정보 -> 화면
				FileInfo fileInfo = fileService.findFileInfoByFileName(userProfileImage.getFileName());
				model.addAttribute("fileInfo", fileInfo);
			}
			
			return "customer/mypage";
		}
		
		//로그인 안되어있으면? -> 로그인 페이지로 연결
		return "redirect:/customer/signin";
	}
	
	@GetMapping("/customer/signout")
	public String signout(HttpSession session) {
		
		//세션 초기화
		//session.invalidate();
		LoginManager.logout(session);
		
		return "redirect:/main";
	}
	
	//@GetMapping("/customer/modifyPw")
	public String modifyPw(HttpSession session, Model model) {
		
		if( LoginManager.isLogin(session) ) {
			String loginUserId = LoginManager.getLoginUserId(session);
			User user = userService.findUserById(loginUserId);
			model.addAttribute("user", user);
		}
		
		return "customer/modifyPw";
	}
	
	//@PostMapping("/customer/modifyPw")
	public String modifyPwAction(User user) {
		System.out.println(user);
		
		//비밀번호 변경 -> 연결 -> 서비스 -> dao 
		
		//User 객체정보 전체를 넘겨서 update
		int result = userService.modifyUser(user);
		
		if(result > 0) {
			return "redirect:/customer/mypage";
		} else {
			return "redirect:/customer/modifyPw";
		}
		
	}
	
	@GetMapping("/customer/modifyPw")
	public String modifyPw2() {
		return "customer/modifyPw2";
	}
		
	@PostMapping("/customer/modifyPw")
	public String modifyPwAction2(User user, HttpSession session) {
		
		//사용자가 입력한 바꿀 비번 pw 만 존재
		
		//비번을 바꾸려는 사용자 ID 세팅 
		
		//mypage -> 비밀번호 변경 시도  
		//--> 이미 로그인이 되어있다는 뜻 -> session 영역에 로그인 사용자 아이디
		
		// set pw = ?
		// where id = ? 
		//user.setId(session.getAttribute("loginUserId").toString());
		user.setId(LoginManager.getLoginUserId(session));
		
		System.out.println(user);  //id 기존id  pw 바꾸려는 비번 
		int result = userService.modifyUserPw(user);
		
		if(result > 0) {
			return "redirect:/customer/mypage";
		} else {
			return "redirect:/customer/modifyPw";
		}
	}
		
	
	@PostMapping("/customer/profile")
	public String profileAction(HttpServletRequest request,
			MultipartRequest multipartRequest) {
		
		System.out.println(request.getParameter("id"));
		System.out.println(request.getParameter("name"));
		
		MultipartFile file = multipartRequest.getFile("profileImage");
		
		System.out.println( file.getName() );
		System.out.println( file.getOriginalFilename() );
		System.out.println( file.isEmpty() );
		System.out.println( file.getContentType() );
		System.out.println( file.getSize() );
		
		return "redirect:/customer/mypage";
	}
	
	@PostMapping("/customer/profiledto")
	public String profiledtoAction(UserProfileRequestForm userProfileRequestForm) {
		
		System.out.println(userProfileRequestForm.getId());
		System.out.println(userProfileRequestForm.getName());
		
		MultipartFile file = userProfileRequestForm.getProfileImage();
		//form 에서 전송한 첨부파일 수신
		
		//첨부파일 정보 확인
		System.out.println( file.getName() );
		System.out.println( file.getOriginalFilename() );
		System.out.println( file.isEmpty() );
		System.out.println( file.getContentType() );
		System.out.println( file.getSize() );
		
		
		//1. 첨부파일   별도의 저장소에 실제파일을 저장
		
		
		//컨트롤러 파일 자체 저장 
//		try {
//			file.transferTo( new File("d:/fileStorage/" + file.getOriginalFilename()) );
//			//파일명이 중복되는 경우 그냥 덮어쓰기 되는 문제
//		} catch (IllegalStateException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		//별도의 유틸성 FileManager 클래스 활용 (파일처리 로직 분리)
		
		
		//실제 폴더에 파일이 저장되고, 저장된 정보 fileInfo 로 반환
		try {
			
			FileInfo fileInfo = FileManager.storeFile(file);  //실제 저장폴더 경로에 저장
			
			System.out.println(fileInfo);
			
			//2. 저장된 프로필사진 파일에 대한 정보 + 저장해야하는 관련 정보 --> DB 테이블에 저장
			
			// 1) 파일정보 fileInfo DB저장
			int result = fileService.saveFileInfo(fileInfo);
			
			if(result > 0) {  //실제파일저장 -> DB에 파일정보 저장
				
				
				// 2) 어떤 사용자의 프로필 사진 정보 DB 저장
				
				UserProfileImage userProfileImage = new UserProfileImage();
				// 사용자id, 파일이름 fileName
				
				// userProfileRequestForm -> id 
				// 로그인 -> 마이페이지 -> 프로필사진업로드     로그인한 사용자 id 정보 -> session 저장소에서 조회가능
				userProfileImage.setId(userProfileRequestForm.getId());  //mypage hidden id
				userProfileImage.setFileName(fileInfo.getFileName());
				
				
				int result2 = userService.saveUserProfileImage(userProfileImage);
				
				if(result2 > 0) {
					// 저장성공~
				}  //저장실패 ~ 
				
			}
			
			//파일저장안됐으면? --> controller 방향 결정 로직
			
		} catch (IllegalStateException | IOException e) {
			
			//exception 걸렸다? -> 실제 파일 저장 실패
			e.printStackTrace();
		}
		
		return "redirect:/customer/mypage";
	}
}















