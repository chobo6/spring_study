package com.app.dto.room;

import lombok.Data;

@Data
public class Room {	//Room DTO
	int roomId; // PK 기본키 식별자
	String buildingName; // 동이름 101 102 A B
	int roomNumber; // 호실번호 1101 1305
	int floor; // 층수
	int maxGuestCount; // 최대 숙박인원
	String viewType; // 뷰 구분 코드
					 // 오션뷰 시티뷰 마운틴뷰
					 // OCN	  CTY  MOT
					 // 1	  2	   3
}
