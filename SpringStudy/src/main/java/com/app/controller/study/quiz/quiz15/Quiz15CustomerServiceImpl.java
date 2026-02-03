package com.app.controller.study.quiz.quiz15;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Quiz15CustomerServiceImpl implements Quiz15CustomerService {

	@Autowired
	Quiz15CustomerDAO quiz15CustomerDAO; 
	
	@Override
	public int saveCustomer(Quiz15Customer quiz15Customer) {
		//주요 서비스 메인 로직
		
		// Service -> DAO 저장해라~
		
		System.out.println("[Service] CustomerService");
		int result = quiz15CustomerDAO.saveCustomer(quiz15Customer);
		
		return result;
	}

}
