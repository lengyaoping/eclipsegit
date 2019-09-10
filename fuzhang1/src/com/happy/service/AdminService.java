package com.happy.service;

import java.util.List;

import com.happy.model.Admin;

public interface AdminService {
	/**
	 * 根据用户名和密码查询管理员
	 * 
	 * @param username
	 * @param password
	 */
	public Admin getAdmin(String account, String password);

	/**
	 * 根据用户名和密码更改密码
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	int updateAdmin(String account, String password);

	/**
	 * 修改用户
	 * 
	 * @param admin
	 * @return
	 */
	int updateAdminInfo(Admin admin);
	
	
	
	 
	int findTotal();
	
	List<Admin> getlist(int page, int  rows);//通过当前页和行数查询所有数据
	
	List<Admin> combGetAdminList(String sql,int page, int rows);//组合查询

	 int findPage(String sql);

	 /**
		 * 增加管理员
		 * @return
		 */
		
	 int addAdmin(Admin admin) ;
	
}

