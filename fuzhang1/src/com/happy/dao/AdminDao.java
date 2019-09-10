package com.happy.dao;

import java.util.List;

import com.happy.model.Admin;
import com.happy.model.weixin.User;

public interface AdminDao {
	/**
	 * 根据用户名和密码查询管理员
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	Admin getAdmin(String account, String password);

	/**
	 * 根据用户名和密码更改密码
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	int updateAdmin(String account, String password);

	  
	
	   // 根据当前页和每页条数查询
		public List<Admin> getlist(int page, int rows);
		// 组合条件查询
		List<Admin> combGetAdminList(String sql);
		
		// 组合条件分页查询
		List<Admin> combGetAdminList(String sql, int page, int rows);
		//
		int findCombPageTotal(String sql);
		//查询总数据数
		int fingTotal();
		// 编辑 管理员信息
		int updateAdminInfo(Admin admin);
		//增加管理员信息
		int addAdmin(Admin admin);
}
