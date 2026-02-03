package com.app.controller.study.quiz.quiz14;

import lombok.Data;

@Data
public class PlateBean {
	DessertBean dessertBean;
	
	//디저트빈 주입
	//setter
	public void setDessertBean(DessertBean dessertBean) {
		this.dessertBean = dessertBean;
	}
}
