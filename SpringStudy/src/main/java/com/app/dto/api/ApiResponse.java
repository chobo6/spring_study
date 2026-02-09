package com.app.dto.api;

import lombok.Data;

@Data
public class ApiResponse<T> {
	// Generic 제네릭 타입을 고정하지않고 일반화
	
	ApiResponseHeader header; // 응답 결과 코드
	T body;
	
//	List<String>
//	List<Integer>
//	List<ApiMenu>
}
