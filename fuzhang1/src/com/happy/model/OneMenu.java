package com.happy.model;

/**
 * 一级菜单实体类
 * 
 * @author zjs 2017/12/19
 */
public class OneMenu {
	private Integer id;
	private Integer pid; // 父级菜单
	private String text; // 菜单名字
	private String state; // 菜单默认状态 打开/关闭
	private String iconCls; // 菜单图标名称
	private String url; // 菜单映射地址
	private String level; // 菜单显示权限等级

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

}
