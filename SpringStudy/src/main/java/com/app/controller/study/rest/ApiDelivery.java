package com.app.controller.study.rest;

import java.util.List;

import lombok.Data;

@Data
public class ApiDelivery {
	String staffName; // 배달기사 이름
	String destination; // 도착지
	String phone; // 배달기사 연락처
	
	ApiStore apiStore; // 어디 매장
	List<ApiMenu> menuList;
}
