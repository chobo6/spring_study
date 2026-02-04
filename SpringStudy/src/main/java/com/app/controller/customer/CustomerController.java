package com.app.controller.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.app.service.user.UserService;

@Controller
public class CustomerController {
	// 고객 사용자 관련된 서비스 (계정 관련...)
	
	@Autowired
	UserService userSerivce;
}
