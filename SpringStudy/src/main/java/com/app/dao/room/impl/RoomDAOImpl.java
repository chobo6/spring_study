package com.app.dao.room.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.dao.room.RoomDAO;
import com.app.dto.room.Room;

// 데이터소스와 연결/통신하는 역할
// DAO  Repository
// DB 연동 -> DAO
// 외부 API -> Repository
@Repository
public class RoomDAOImpl implements RoomDAO {

	// 실제 DB 연동 처리할 때 필요한 관련 객체(Bean)
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<Room> findRoomList() {

		System.out.println("[DAO] 호출 findRoomList");

		// DB 에 연동 -> room 테이블 데이터 조회 -> List
		List<Room> roomList = sqlSessionTemplate.selectList("room_mapper.findRoomList");

		return roomList;
	}

	@Override
	public int saveRoom(Room room) {

		// DB에 room 정보 저장

		// 실행할 쿼리가 위치한 식별자 , 매개변수
		// namespace.id
		int result = sqlSessionTemplate.insert("room_mapper.saveRoom", room);

		return result;
	}

	@Override
	public Room findRoomByRoomId(int roomId) {

		Room room = sqlSessionTemplate.selectOne("room_mapper.findRoomByRoomId", roomId);

		return room;
	}

	@Override
	public int removeRoom(int roomId) {
		
		int result = sqlSessionTemplate.delete("room_mapper.removeRoom", roomId);

		return result;
	}

}
