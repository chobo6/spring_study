package com.app.controller.study.quiz.quiz14;

import lombok.Data;

@Data
public class CupBean {
	CoffeeBean coffeeBean;
	
	//커피빈 주입
	//생성자
	public CupBean(CoffeeBean coffeeBean) {
		this.coffeeBean = coffeeBean;
	}
}
