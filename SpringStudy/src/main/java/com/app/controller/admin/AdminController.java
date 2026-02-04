package com.app.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.dto.room.Room;
import com.app.service.room.RoomService;

@Controller
public class AdminController {
	// 관리자 접근 페이지 (판매측 사용자) or (전체 관리자)
	
	@Autowired
	RoomService roomService;
	
	@GetMapping("/admin/registerRoom")
	public String registerRoom() {
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
		
		return "admin/registerRoom";
	}
}
