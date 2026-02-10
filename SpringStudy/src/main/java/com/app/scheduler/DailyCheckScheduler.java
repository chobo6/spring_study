package com.app.scheduler;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.app.dao.room.RoomDAO;
import com.app.service.room.RoomService;
import com.app.service.user.UserService;

public class DailyCheckScheduler {
	
	@Autowired
	UserService userService;
	
	@Autowired
	RoomService roomService;
	
	@Autowired
	RoomDAO roomDAO;
	
	// https://docs.spring.io/spring-framework/reference/integration/scheduling.html
	// Cron Expressions
	
//	@Scheduled(cron = "0/5 * * * * *")
	public void test1() {
		System.out.println("DailyCheckScheduler test1 " + LocalDateTime.now());
	}
	
//	@Scheduled(cron = "0 30 2-4 * * * ")
	@Scheduled(cron = "0 0 2 * * * ")
	public void dailySales() {
		userService.findUserById(null);
		roomService.findRoomList();
		
		// 일일 정산
		
		// 매출 통계
	}
}
