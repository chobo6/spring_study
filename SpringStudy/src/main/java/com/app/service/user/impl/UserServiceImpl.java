package com.app.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.common.CommonCode;
import com.app.dao.user.UserDAO;
import com.app.dto.user.User;
import com.app.service.user.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDAO userDAO;

	@Override
	public int saveUser(User user) {

		int result = userDAO.saveUser(user);

		return result;
	}

	@Override
	public int saveCustomerUser(User user) {
		// 사용자 계정 추가 시 사용하는 메소드
		// 사용자 추가 로직
//		user.setUserType("CUS");
		user.setUserType(CommonCode.USER_USERTYPE_CUSTOMER);
		
		int result = userDAO.saveUser(user);

		return result;
	}

	@Override
	public int saveAdminUser(User user) {
		// 관리자 계정 추가 시 사용하는 메소드
		// 관리자 추가 로직
//		user.setUserType("ADM");
		user.setUserType(CommonCode.USER_USERTYPE_ADMIN);
		
		int result = userDAO.saveUser(user);
		
		return result;
	}

	@Override
	public List<User> findUserList() {
		
		List<User> userList = userDAO.findUserList();
		
		return userList;
	}

	@Override
	public User findUserById(String id) {
		
		User user = userDAO.findUserById(id);
		
		return user;
	}

	@Override
	public User checkUserLogin(User user) {

		//id pw 일치하나?
		
		// 사용자가 입력한 id pw -> User user
		// 그 id에 실제 DB정보와 pw 까지 같은가? -> DB 조회 id 기준
		User loginUser = userDAO.findUserById(user.getId());
		
		//맞으면? 
		//if(		   user.getId().equals(loginUser.getId())  //X 이미 해당 ID기준으로 조회
		if( loginUser != null
				&& user.getPw().equals(loginUser.getPw())
				&& user.getUserType().equals(loginUser.getUserType())) {
			//성공 아이디 비번 모두 제대로 입력
			
			return loginUser; //정상이면 User객체 return 
		}
		
		// loginUser == null -> ID가 존재하지 않는다.
		// loginUser != null -> PW 틀리다?  -> 비밀번호 잘못입력했다
		
		//아니면?   //로그인 실패면 null return
		return null;
		
		/*
			String 의미가 담긴 코드 (SUC, FAL, LCK)
			int 1:성공 2:실패 3:휴면 4:신고 ...
		 */
	}
}
