package com.app.controller.study.quiz.quiz15;

import org.springframework.stereotype.Repository;

@Repository
public class Quiz15RoomDAOImpl implements Quiz15RoomDAO {

	@Override
	public int saveRoom(Quiz15Room quiz15Room) {
		
		//DB 연결 
		System.out.println("[DAO] quiz15Room");
		
		return 0;
	}
	
}
