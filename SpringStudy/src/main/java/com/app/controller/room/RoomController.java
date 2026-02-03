package com.app.controller.room;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.app.dto.room.Room;
import com.app.service.room.RoomService;

@Controller
public class RoomController {

	@Autowired	//의존성 주입방법
	RoomService roomService;
	//스프링이 객체 관리 -> RoomService 객체를 주입 받아서 사용
    
	//모든 보유한 호실정보를 보여주는 요청
	@GetMapping("/rooms")
	public String rooms(Model model) {
		
		System.out.println("[Controller] 호출 /rooms ");
		
		//호실정보
		//Controller -> Service -> DAO <-> DB
		List<Room> roomList = roomService.findRoomList();
		
		//화면에 보여줄 데이터 -> view 전달
		model.addAttribute("roomList", roomList);
		
		return "room/rooms";
	}
}
