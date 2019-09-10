package com.happy.dao.impl;

import java.util.List;

import net.sf.json.util.NewBeanInstanceStrategy;

import org.apache.struts2.components.Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.happy.dao.AdminDao;
import com.happy.model.Admin;
import com.happy.model.weixin.User;


@Repository("AdminDao")
public class AdminDaoImpl implements AdminDao {
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}

	public void setNamedParameterJdbcTemplate(
			NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	// 根据用户名和密码查询管理员
	public Admin getAdmin(String account, String password) {
		String sql = "select admin_id,admin_name,admin_account,admin_password,level,fake from admin where admin_account=:account and admin_password=:password and fake=0";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("account", account);
		sps.addValue("password", password);
		List<Admin> list = namedParameterJdbcTemplate.query(sql, sps,
				new BeanPropertyRowMapper<Admin>(Admin.class));
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	// 根据用户名和密码修改密码
	public int updateAdmin(String admin_id, String password) {
		String sql = "update admin set admin_password=:password  where admin_id=:admin_id  and fake=0";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("admin_id", admin_id);
		sps.addValue("password", password);
		int num = namedParameterJdbcTemplate.update(sql, sps);
		if (num > 0) {
			return num;
		}
		return 0;
	}

	// 修改用户信息
	public int updateAdminInfo(Admin admin) {
		String sql = "update admin set admin_name=:name,admin_account=:account,admin_password=:password where admin_id=:admin_id and fake=0";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("name", admin.getAdmin_name());
		sps.addValue("account", admin.getAdmin_account());
		sps.addValue("admin_id", admin.getAdmin_id());
		sps.addValue("password", admin.getAdmin_password());
		int num = namedParameterJdbcTemplate.update(sql, sps);
		if (num > 0) {
			return num;
		}
		return 0;
	}

	

	public List<Admin> getlist(int page, int rows) {
		int start = (page - 1) * rows;// 每页的起始下标
		String sql = "select * from admin where fake=0 limit :start,:rows ";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("start", start);
		sps.addValue("rows", rows);
		List<Admin> list = namedParameterJdbcTemplate.query(sql, sps,
				new BeanPropertyRowMapper<Admin>(Admin.class));
		return list;
	}

	public List<Admin> combGetAdminList(String sql) {
		String sqlx = "select * from admin " + sql;
		MapSqlParameterSource sps = new MapSqlParameterSource();
		List<Admin> list = namedParameterJdbcTemplate.query(sql, sps,
				new BeanPropertyRowMapper<Admin>(Admin.class));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	public List<Admin> combGetAdminList(String sql, int page, int rows) {//
		int start = (page - 1) * rows;// 每页的起始下标
		String sqlx = "select * from admin " + sql + " limit :start,:rows";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("start", start);
		sps.addValue("rows", rows);
		List<Admin> list = namedParameterJdbcTemplate.query(sql, sps,
				new BeanPropertyRowMapper<Admin>(Admin.class));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	public int fingTotal() {
		String sql = "select count(*) from admin where fake=0";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("fake", 0);
		return namedParameterJdbcTemplate.queryForInt(sql, sps);
		
	}

	public int findCombPageTotal(String sql) {
		String sqlx = "select count(*) from admin " + sql;
		MapSqlParameterSource sps = new MapSqlParameterSource();
		return namedParameterJdbcTemplate.queryForInt(sql, sps);
	
	}
     /**
      * 增加管理员信息
      */
	public int addAdmin(Admin admin) {
		String sql = "insert into admin(admin_name,admin_password,admin_account,fake,level) value(:name,:password,:account,:fake,:level)";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("name",admin.getAdmin_name());
		sps.addValue("password", admin.getAdmin_password());
		sps.addValue("account", admin.getAdmin_account());
		sps.addValue("fake", 0);
		sps.addValue("level",admin.getLevel());
		return namedParameterJdbcTemplate.update(sql, sps);
	}

	

	

}
