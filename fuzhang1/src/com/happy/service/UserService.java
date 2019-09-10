package com.happy.service;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.happy.model.weixin.User;

public interface UserService {
	// 通过openid查询员工
	User findByOpenid(String oPEN_ID);

	// 通过手机号和工号查询员工
	User findByAccountAndJob(User user);

	// 更新数据
	void update(User u);

	// 查询所有员工
	public List<User> getlist();

	// 根据当前页和每页条数查询所有员工
	public List<User> getlist(int page, int rows);

	// 添加员工
	public int add_user(User u);

	// 编辑员工信息
	public int updateuser(User u);

	// 根据id查询员工信息
	public List<User> findById(int id);

	// 根据id删除员工信息
	public int delete(int id);

	// 批量删除
	public int batchDelete(String sqlx);

	// 组合条件查询
	List<User> combGetUserList(String sql);

	// 组合条件分页查询
	List<User> combGetUserList(String sql, int page, int rows);

	int findCombPageTotal(String sql);

	int findPageTotal();
	
	//模糊查询
	List<User> selectq(String q);
}
