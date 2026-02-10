package com.app.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.dto.room.Room;
import com.app.dto.room.RoomSearchCondition;
import com.app.dto.user.User;
import com.app.dto.user.UserSearchCondition;
import com.app.service.room.RoomService;
import com.app.service.user.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AdminController {
	// 관리자 접근 페이지 (판매측 사용자) or (전체 관리자)
	
	@Autowired
	RoomService roomService;
	
	@Autowired
	UserService userService;
	
//	private static final Logger log = LogManager.getLogger(AdminController.class);
	@GetMapping("/admin/registerRoom")
	public String registerRoom() {
		
		System.out.println("이것은 println");
		log.info("log level info 기록");
		log.error("심각한 에러가 발생해서 이건 보완해야함.");
		log.debug("debug level 문을 열고 들어왔다.");
		
		log.trace("trace level 문을 열었다.");
		log.trace("trace level 오른손으로 위에서 아래로 손잡이를 눌렀다.");
		log.trace("trace level 오른발 먼저 해서 들어왔다.");
		
		return "admin/registerRoom";
	}
	
	@PostMapping("/admin/registerRoom")
	public String registerRoomAction(@ModelAttribute Room room) {
		
		// 화면에서 입력한 값이 제대로 전달 됐는지
		System.out.println(room);
		
		int result = roomService.saveRoom(room);
		// result 값
		// 성공? 실패? -> 진행 방향
		System.out.println("insert 처리 결과 적용 행의 수 : " + result);
		
		// 저장 성공 or 실패
		if(result > 0) { // 저장 성공
			return "redirect:/admin/rooms";
		} else { // 저장 실패
			return "admin/registerRoom";
		}
	}
	
	
	// 관리자가 객실 관리	전체 객실 목록 조회
	@GetMapping("admin/rooms")
	public String rooms(Model model, RoomSearchCondition roomSearchCondition) {
		
		// rooms 페이지 정보
		// T_ROOM 객실 데이터	DB조회
		
		//검색 조건에 따라서 수행
		List<Room> roomList = roomService.findRoomListBySearchCondition(roomSearchCondition);
		
		//전체 조회
		//List<Room> roomList = roomService.findRoomList();
		
		model.addAttribute("roomList", roomList);
		model.addAttribute("roomSearchCondition", roomSearchCondition);
		
		return "admin/rooms";
	}
	
	// 객실 개별 상세 페이지 조회
//	@GetMapping("/admin/room?roomId=1")
	@GetMapping("/admin/room/{roomId}")
	public String room(@PathVariable String roomId, Model model) {
		
		Room room = roomService.findRoomByRoomId(Integer.parseInt(roomId));
		model.addAttribute("room", room);
		
		if(room == null) { // roomId에 해당하는 정보가 없다
			// 조회할 정보가 없을 때 보여줄 페이지
		}
		
		return "admin/room";
		
	}
	
	// 객실 정보 삭제
	// localhost:8080/admin/removeRoom?roomId=4
	@GetMapping("/admin/removeRoom")
	public String removeRoom(HttpServletRequest request) {
		String roomId = request.getParameter("roomId");
		
		if(roomId == null) {
			return "redirect:/admin/rooms";
		}

		int result = roomService.removeRoom(Integer.parseInt(roomId));
		
		// if(result > 0)
		
		return "redirect:/admin/rooms";
	}
	
	@GetMapping("/admin/modifyRoom")
	public String modifyRoom(HttpServletRequest request) {
		String roomId = request.getParameter("roomId");
		
		if(roomId == null) {
			return "redirect:/admin/rooms";
		}
		
		
		// PK roomId -> 해당하는 객실 정보 조회
		Room room = roomService.findRoomByRoomId(Integer.parseInt(roomId));
		System.out.println("기존에 가지고 있던 정보");
		System.out.println(room);
		
		// view -> 기본 데이터로 세팅
		request.setAttribute("room", room);
		
		// 수정
		
		return "admin/modifyRoom";
	}
	
	@PostMapping("/admin/modifyRoom")
	public String modifyRoomAction(@ModelAttribute Room room) {
		
		System.out.println("수정하려는 객실 정보");
		System.out.println(room);
		
		int result = roomService.modifyRoom(room);
		
		if(result > 0) { // 성공
			return "redirect:/admin/room/" + room.getRoomId();
		} else { // 실패
			// return "admin/modifyRoom"; // 바로 화면 연결
			
			// 수정 페이지로 다시 진입
			return "redirect:/admin/modifyRoom?roomId=" + room.getRoomId();
		}
	}
	
	// 관리자가 사용자 계정 관리 -> 사용자 계정 추가
	@GetMapping("admin/users/add")
	public String addUser() {
		return "admin/addUser";
	}
	
	@PostMapping("admin/users/add")
	public String addUserAction(User user) {
		
		// user 정보를 DB에 저장
		System.out.println(user);
		
		// 관리자가 사용자 계정을 추가
		// 사용자 계정 -> userType 값은 "CUS" 코드로 저장되어야 한다.
		
		/*
		1) 컨트롤러에서 바로 처리
		
		user.setUserType("CUS");
		
		userService.saverUser(user);
		
		*/
		
		/*
		2) 서비스 계층/레이어/레벨에서 사용자를 저장하는 메소드를 활용
		
		userService.saveCustomerUser(user);
		
		 */
		
		int result = userService.saveCustomerUser(user);
		
		if(result > 0) {
			return "redirect:/admin/users";
		} else {
			return "admin/addUser";
		}
		
	}
	
	@GetMapping("/admin/users")
	public String users(Model model, UserSearchCondition userSearchCondition) {
		
		System.out.println(userSearchCondition);
		
		// 검색 조건에 따라서
		// 검색 조건이 있으면 -> 조건 검색
		// 검색 조건이 없으면 -> 전체 조회
		
//		List<User> userList = userService.findUserList();
		List<User> userList = userService.findUserListBySearchCondition(userSearchCondition);
		
		model.addAttribute("userList", userList);
		
		return "admin/users";
	}
	
	// 관리자페이지
	
	// 사용자 상세 정보
	@GetMapping("/admin/user/{id}")
	public String user(@PathVariable String id, Model model) {
		
		User user = userService.findUserById(id);
		model.addAttribute("user", user);
		
		return "admin/user";
	}
	
	// 사용자 정보 변경(수정)
	@GetMapping("/admin/modifyUser/{id}")
	public String modifyUser(@PathVariable String id, Model model) {
		
		// 수정페이지
		// 기존에 있는 값 -> view 세팅
		User user = userService.findUserById(id);
		model.addAttribute("user", user);
		
		return "admin/modifyUser";
	}
	
	@PostMapping("/admin/modifyUser")
	public String modifyUserAction(User user) {
		
		System.out.println("modifyUser 넘어온 값");
		System.out.println(user);
		
		// DB 연동 수정
		int result = userService.modifyUser(user);
		
		if(result > 0) {
			return "redirect:/admin/user/" + user.getId();
		} else {
			return "redirect:/admin/modifyUser/" + user.getId();
		}
	}
}
