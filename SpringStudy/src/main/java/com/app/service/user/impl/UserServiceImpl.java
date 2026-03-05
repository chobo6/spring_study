package com.app.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.common.CommonCode;
import com.app.dao.user.UserDAO;
import com.app.dto.user.User;
import com.app.dto.user.UserProfileImage;
import com.app.dto.user.UserSearchCondition;
import com.app.service.user.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
		// 사용자 계정 추가시 사용하는 메소드
		// 사용자 추가 로직
		//user.setUserType("CUS");
		user.setUserType( CommonCode.USER_USERTYPE_CUSTOMER );
		
		int result = userDAO.saveUser(user);

		return result;
	}

	@Override
	public int saveAdminUser(User user) {
		// 관리자 계정 추가시 사용하는 메소드
		// 관리자 추가 로직
		//user.setUserType("ADM");
		user.setUserType( CommonCode.USER_USERTYPE_ADMIN );
		
		int result = userDAO.saveUser(user);
		
		log.info("관리자 계정 추가 됨 {}", user);

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
	public User checkUserLogin(User user) {  // user.setUserType("CUS")  "ADM"

		/*
		//케이스 1)  DB에서 user 정보 조회 -> 서비스 계층에서 상태를 비교하는 로직 수행
		
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
		*/
		/*
			String 의미가 담긴 코드 (SUC, FAL, LCK)
			int 1:성공 2:실패 3:휴면 4:신고 ...
		 */
		
		
		// 케이스 2) DB 쿼리상에서, 정보 일치여부 판단 수행
		User loginUser = userDAO.checkUserLogin(user);
		
		return loginUser;  //조회O -> user객체  조회X -> null
	}

	@Override
	public int modifyUserPw(User user) {

		int result = userDAO.modifyUserPw(user);
		
		return result;
	}

	@Override
	public int modifyUser(User user) {
		
		int result = userDAO.modifyUser(user);
		
		return result;
	}

	@Override
	public List<User> findUserListBySearchCondition(UserSearchCondition userSearchCondition) {

		List<User> userList = userDAO.findUserListBySearchCondition(userSearchCondition);
		
		return userList;
	}

	@Override
	public boolean isDuplicatedId(String id) {
		//중복여부 체크 return
		
		//중복? true 
		//중복아니면? false
		
		//해당 ID가  DB에 이미 있나?
		User user = userDAO.findUserById(id);
		
		if(user == null) { //아이디 못찾음 return null -> 없음 -> 중복XXX 
			return false;
		} else { //아이디 있다 -> PK id가 이미 있다 -> 중복 OOO 
			return true;
		}
		
	}

	@Override
	public int saveUserProfileImage(UserProfileImage userProfileImage) {
		
		int result = userDAO.saveUserProfileImage(userProfileImage);
		
		return result;
	}

	@Override
	public UserProfileImage findUserProfileImageById(String id) {
		
		UserProfileImage userProfileImage = userDAO.findUserProfileImageById(id);
		
		return userProfileImage;
	}
}








