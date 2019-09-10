package com.happy.model;

/**
 * 
 * @author QT-001 款式
 */
public class style {
	private int id;
	private String style_name; // 款式名字
	private String style_t_id; //款式绑定的工序id
	private int fake;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStyle_name() {
		return style_name;
	}

	public void setStyle_name(String style_name) {
		this.style_name = style_name;
	}

	public int getFake() {
		return fake;
	}

	public void setFake(int fake) {
		this.fake = fake;
	}

	public String getStyle_t_id() {
		return style_t_id;
	}

	public void setStyle_t_id(String style_t_id) {
		this.style_t_id = style_t_id;
	}

}
