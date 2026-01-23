package com.app.dto.study;

//client 화면 form 에서 입력한 값을 담아서 서버에서 파라미터 받는 용도로 쓰는 객체
public class ProductRequestForm {

	public String id;
	public String name;
	public int price;
	
	public String type;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "ProductRequestForm [id=" + id + ", name=" + name + ", price=" + price + ", type=" + type + "]";
	}

	
	
	
}
