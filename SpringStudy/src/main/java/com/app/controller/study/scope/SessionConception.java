package com.app.controller.study.scope;

import java.util.HashMap;
import java.util.Map;

public class SessionConception {

	public static void main(String[] args) {
		
		/*		서버 <--> 사용자
		 
		  사용자(브라우저)	--(요청)-> 서버
		  쿠키	    	<-(응답)-- 서버
		  				   
		  				   
			쿠키값(JSESSIONID)
			
			사용자 --(요청:쿠키값 포함)--> 서버 
									쿠키값(JSESSIONID) 확인 (key)
									key별(각세션별) 저장공간영역 -> session.setAttribute	
									
			
			Map
			Key:JSESSIONID Value:저장공간(SessionStorage)
	
		 */
		
		
		Map<String, SessionStorage> sessionMap = new HashMap<String, SessionStorage>();
		
		//새로운 사용자 접근 -> A (key)
		
		sessionMap.put("A", new SessionStorage());
		
		//사용자 누구? A
		SessionStorage session = sessionMap.get("A");
		session.setAttribute("fromB", "B Msg");
		session.setAttribute("hello", "good morning");
		
		// 사용자 B 가 접근
		sessionMap.put("B", new SessionStorage());
		
		SessionStorage session2 = sessionMap.get("B");
		session2.setAttribute("key1", "value1");
		
		// A가 다시 접근
		session = sessionMap.get("A");
		System.out.println(session.getAttribute("hello"));
		//System.out.println(session.getAttribute("key1"));   이건 B의 저장공간
	}
}

class SessionStorage {
	
	Map<String, Object> storage;
	
	public SessionStorage() {
		storage = new HashMap<String, Object>();
	}
	
	public Object getAttribute(String key) {
		return storage.get(key);
	}
	
	public void setAttribute(String key, Object value) {
		storage.put(key, value);  //storage Map에 값 저장 추가
	}
	
	public void removeAttribute(String key) {
		storage.remove(key); //특정 키값으로 삭제
	}
	
	public void invalidate() {
		storage.clear(); //전부삭제 초기화
	}
	
}