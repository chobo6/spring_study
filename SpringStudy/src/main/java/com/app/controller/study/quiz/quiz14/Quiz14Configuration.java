package com.app.controller.study.quiz.quiz14;

import java.beans.BeanProperty;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Quiz14Configuration {

	//Bean 등록 설정


	//Coffee
	//Cup
	
	//Dessert
	//Plate
	
	//  CoffeeBean coffeeBean = new CoffeeBean();
	@Bean
	public CoffeeBean coffeeBean() {
		CoffeeBean coffeeBean = new CoffeeBean();
		coffeeBean.setName("민트초코프라페");
		
		return coffeeBean;
	}
	
	@Bean
	public CupBean cupBean(CoffeeBean coffeeBean) {
		//						의존성 주입
		CupBean cupBean = new CupBean(coffeeBean);
		
		return cupBean;
	}
	
	@Bean
	public DessertBean dessertBean() {
		DessertBean dessertBean = new DessertBean();
		dessertBean.setName("당근케이크");
		return dessertBean;
	}
	
	@Bean
	public PlateBean plateBean(DessertBean dessertBean) {
		PlateBean plateBean = new PlateBean();
		plateBean.setDessertBean(dessertBean);
		
		return plateBean;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
