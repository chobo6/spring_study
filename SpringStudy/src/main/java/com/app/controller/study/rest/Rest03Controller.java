package com.app.controller.study.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.common.ApiCommonCode;
import com.app.common.CommonCode;
import com.app.dto.api.ApiResponse;
import com.app.dto.api.ApiResponseHeader;
import com.app.dto.room.Room;
import com.app.dto.user.User;

@RestController
public class Rest03Controller {

	// API 요청에 대한 -> 응답 (JSON)

	// json -> header, body 영역으로 나눠서 응답

	@GetMapping("/rest/object1")
	public ApiMenu object1() {

		ApiMenu apiMenu = new ApiMenu("아메리카노", 1700);

		return apiMenu;
	}

	@GetMapping("/rest/object2")
	public List<ApiMenu> object2() {

		List<ApiMenu> menuList = new ArrayList<ApiMenu>();

		menuList.add(new ApiMenu("아메리카노", 1700));
		menuList.add(new ApiMenu("아이스티", 2000));
		menuList.add(new ApiMenu("카페라떼", 2700));
		menuList.add(new ApiMenu("감자빵", 3700));

		return menuList;
	}

	@GetMapping("/rest/object3")
	public ApiDelivery object3() {

		ApiDelivery apiDelivery = new ApiDelivery();

		apiDelivery.setStaffName("이병헌");
		apiDelivery.setDestination("한강공원 분수대 앞");
		apiDelivery.setPhone("010-1234-1223");

		ApiStore apiStore = new ApiStore();
		apiStore.setName("맥도날드");
		apiStore.setAddress("한강동 123번지 1,2층");
		apiStore.setTel("02-123-1212");

		apiDelivery.setApiStore(apiStore);

		List<ApiMenu> menuList = new ArrayList<ApiMenu>();

		menuList.add(new ApiMenu("아이스드립커피", 1700));
		menuList.add(new ApiMenu("스낵랩", 2500));
		menuList.add(new ApiMenu("토네이도", 3700));
		menuList.add(new ApiMenu("감자튀김", 2100));

		apiDelivery.setMenuList(menuList);

		return apiDelivery;
	}

	@GetMapping("/rest/response1")
	public int response1() {
		// 10 정상
		// 20 내부오류
		// 30 인증오류
		return 30;
	}

	@GetMapping("/rest/response2")
	public ApiResponseHeader response2() {
		ApiResponseHeader apiResponseHeader = new ApiResponseHeader();
		apiResponseHeader.setResultCode(ApiCommonCode.API_RESULT_SUCCESS);
		apiResponseHeader.setResultMessage(ApiCommonCode.API_RESULT_SUCCESS_MSG);

		return apiResponseHeader;
	}

	@GetMapping("/rest/response3")
	public ApiResponseDelivery response3() {

		// header
		ApiResponseHeader apiResponseHeader = new ApiResponseHeader();
		apiResponseHeader.setResultCode(ApiCommonCode.API_RESULT_SUCCESS);
		apiResponseHeader.setResultMessage(ApiCommonCode.API_RESULT_SUCCESS_MSG);

		// body
		ApiDelivery apiDelivery = new ApiDelivery();

		apiDelivery.setStaffName("이병헌");
		apiDelivery.setDestination("한강공원 분수대 앞");
		apiDelivery.setPhone("010-1234-1223");

		ApiStore apiStore = new ApiStore();
		apiStore.setName("맥도날드");
		apiStore.setAddress("한강동 123번지 1,2층");
		apiStore.setTel("02-123-1212");

		apiDelivery.setApiStore(apiStore);

		List<ApiMenu> menuList = new ArrayList<ApiMenu>();

		menuList.add(new ApiMenu("아이스드립커피", 1700));
		menuList.add(new ApiMenu("스낵랩", 2500));
		menuList.add(new ApiMenu("토네이도", 3700));
		menuList.add(new ApiMenu("감자튀김", 2100));

		apiDelivery.setMenuList(menuList);

		// 응답 객체
		ApiResponseDelivery apiResponseDelivery = new ApiResponseDelivery();
		apiResponseDelivery.setHeader(apiResponseHeader);
		apiResponseDelivery.setBody(apiDelivery);

		return apiResponseDelivery;
	}

	@GetMapping("/rest/response4")
	public ApiResponse<ApiDelivery> response4() {
		List<String> list1;
		List<Integer> list2;
		List<Room> list3;
		List<User> list4;

		ApiResponse<String> ar;
		ApiResponse<Integer> ar2;
		ApiResponse<ApiDelivery> ar3;
		ApiResponse<User> ar4;
		ApiResponse<List<User>> ar5; // body 객체 -> 내부 List<User> 필드로

		// -------------------------

		// header
		ApiResponseHeader apiResponseHeader = new ApiResponseHeader();
		apiResponseHeader.setResultCode(ApiCommonCode.API_RESULT_SUCCESS);
		apiResponseHeader.setResultMessage(ApiCommonCode.API_RESULT_SUCCESS_MSG);

		// body
		ApiDelivery apiDelivery = new ApiDelivery();

		apiDelivery.setStaffName("이병헌");
		apiDelivery.setDestination("한강공원 분수대 앞");
		apiDelivery.setPhone("010-1234-1223");

		ApiStore apiStore = new ApiStore();
		apiStore.setName("맥도날드");
		apiStore.setAddress("한강동 123번지 1,2층");
		apiStore.setTel("02-123-1212");

		apiDelivery.setApiStore(apiStore);

		List<ApiMenu> menuList = new ArrayList<ApiMenu>();

		menuList.add(new ApiMenu("아이스드립커피", 1700));
		menuList.add(new ApiMenu("스낵랩", 2500));
		menuList.add(new ApiMenu("토네이도", 3700));
		menuList.add(new ApiMenu("감자튀김", 2100));

		apiDelivery.setMenuList(menuList);

		// 리턴 객체
		ApiResponse<ApiDelivery> res = new ApiResponse<ApiDelivery>();
		res.setHeader(apiResponseHeader);
		res.setBody(apiDelivery);

		return res;
	}

	@GetMapping("/rest/response5")
	public ApiResponse<User> response5() {

		// header
		ApiResponseHeader apiResponseHeader = new ApiResponseHeader();
		apiResponseHeader.setResultCode(ApiCommonCode.API_RESULT_EMPTY_DATA);
		apiResponseHeader.setResultMessage(ApiCommonCode.API_RESULT_EMPTY_DATA_MSG);
		
		// body
		User user = new User();
		user.setId("apiid");
		user.setPw("apipw");
		user.setName("apiname");
		user.setUserType(CommonCode.USER_USERTYPE_ADMIN);
		
		// response 응답객체
		
		ApiResponse<User> res = new ApiResponse<User>();
		res.setHeader(apiResponseHeader);
		res.setBody(user);
		
		return res;
	}
}
