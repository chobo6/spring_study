package com.app.controller.study.quiz.quiz15;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Quiz15RoomController {

	@Autowired
	Quiz15RoomService quiz15RoomService;

	@GetMapping("/quiz15/registerRoom")
	public String registerRoom() {
		// 등록하는 페이지로 연결
		return "quiz/quiz15/registerRoom";
	}

	@PostMapping("/quiz15/registerRoom")
	public String registerRoomAction(@ModelAttribute Quiz15Room quiz15Room) {
		// 등록페이지에서 입력한 정보를 바탕으로 -> 파라미터 전달
		// -> DB 저장
		// Controller -> Service -> DAO -> DB
		// DTO DTO DTO

		System.out.println("[controller] registerRoom");
		int result = quiz15RoomService.saveRoom(quiz15Room);

		if (result > 0) { // 적용된 행의 갯수 1 -> 저장 성공
			return "quiz/quiz15/registerRoom";
		} else {
			return "quiz/quiz15/registerRoom";
		}

	}
}
