package com.happy.model;


public class Loss {
	
	private int id;
	private String place_num;
	private String color;
	private String size;
	private int loss_num;
	private String loss_type;
	private String remark;
	private int fake;
	
	public Loss() {
		
	}
	public Loss(String place_num,String color,String size) {
		this.place_num = place_num;
		this.color = color;
		this.size = size;
		this.fake = 0;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPlace_num(){
		return place_num;
	}
	public void setPlace_num(String place_num) {
		this.place_num = place_num;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public int getLoss_num() {
		return loss_num;
	}
	public void setLoss_num(int loss_num) {
		this.loss_num = loss_num;
	}
	public String getLoss_type() {
		return loss_type;
	}
	public void setLoss_type(String loss_type) {
		this.loss_type = loss_type;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getFake() {
		return fake;
	}
	public void setFake(int fake) {
		this.fake = fake;
	}
	
	
}
