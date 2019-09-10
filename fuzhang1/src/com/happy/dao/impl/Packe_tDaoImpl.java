package com.happy.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.happy.dao.Packe_tDao;
import com.happy.model.Packe_technology;
import com.happy.model.PlaceSpeed;
import com.sun.org.apache.regexp.internal.recompile;

@Repository("packe_tDao")
public class Packe_tDaoImpl implements Packe_tDao {
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}

	public void setNamedParameterJdbcTemplate(
			NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	// 批量添加 包绑定工序表 的数据
	public int batchAddTechnology(String sql1) {
		String sql = sql1;
		MapSqlParameterSource sps = new MapSqlParameterSource();
		int num = namedParameterJdbcTemplate.update(sql, sps);
		if (num > 0) {
			return num;
		}
		return 0;
	}

	// 添加 包绑定工序
	public void addTechnology(Packe_technology packe_technology) {
		String sql = "insert into packe_technology (packe_num,technology_name,state) values(:packe_num,:technology_name,:state)";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("packe_num", packe_technology.getPacke_num());
		sps.addValue("technology_name", packe_technology.getTechnology_name());
		sps.addValue("state", packe_technology.getState());
		namedParameterJdbcTemplate.update(sql, sps);
	}

	// 根据包号查询这个包所绑定的所有工序
	public List<Packe_technology> findByPackT(String packe_num) {
		String sql = "select * from packe_technology where packe_num=:packe_num";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("packe_num", packe_num);
		List<Packe_technology> list = namedParameterJdbcTemplate.query(sql,
				sps, new BeanPropertyRowMapper<Packe_technology>(
						Packe_technology.class));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	// 查询已完成工序列表
	public List<Packe_technology> findByPackTcom(String packe_num) {
		String sql = "select * from packe_technology where packe_num=:packe_num and state=2";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("packe_num", packe_num);
		List<Packe_technology> list = namedParameterJdbcTemplate.query(sql,
				sps, new BeanPropertyRowMapper<Packe_technology>(
						Packe_technology.class));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	// 查询未完成工序列表
	public List<Packe_technology> findByPackTyet(String packe_num) {
		String sql = "select * from packe_technology where packe_num=:packe_num and state=1";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("packe_num", packe_num);
		List<Packe_technology> list = namedParameterJdbcTemplate.query(sql,
				sps, new BeanPropertyRowMapper<Packe_technology>(
						Packe_technology.class));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	// 改变包号绑定工序表单的工序状态为2已完成
	public int updatePt(int id, int state) {
		String sql = "update packe_technology set state=:state where id=:id";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("id", id);
		sps.addValue("state", state);
		return namedParameterJdbcTemplate.update(sql, sps);
	}
	// 改变包号绑定工序表单的工序状态为2已完成
	public int updatePts(String id, int state) {
		String sql = "update packe_technology set state=:state where id in("+id+")";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		//sps.addValue("id", id);
		sps.addValue("state", state);
		return namedParameterJdbcTemplate.update(sql, sps);
	}
	// id查询包邦定工序记录
	public List<Packe_technology> findbByPt(String ids) {
		String sql = "select * from packe_technology where id in ("+ids+")";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		//Integer[] ids = {7594,7595,7596};
		//sps.addValue("id", ids);
		List<Packe_technology> pt = namedParameterJdbcTemplate.query(sql, sps,
				new BeanPropertyRowMapper<Packe_technology>(
						Packe_technology.class));
		if (pt != null && pt.size() > 0) {
			return pt;
		}
		return null;
	}
	
	// 根据包号和工序名称修改包绑定工序表的工序状态
	public int updateTovoidOne(String packe_num, String t_name) {
		String sql = "update packe_technology set state=1 where packe_num=:packe_num and technology_name=:technology_name";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("packe_num", packe_num);
		sps.addValue("technology_name", t_name);
		int num = namedParameterJdbcTemplate.update(sql, sps);
		if (num > 0) {
			return num;
		}
		return 0;
	}

	// 根据包号更改包记录的工序完成情况
	public int updateTovoidTwo(String packe_num) {
		String sql = "update process_dimensio set completed=completed-1,surplus=surplus+1 where packe_num=:packe_num";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("packe_num", packe_num);
		return namedParameterJdbcTemplate.update(sql, sps);
	}

	public int batchupdateStart(String sql3) {
		String sql = sql3;
		MapSqlParameterSource sps = new MapSqlParameterSource();
		int num = namedParameterJdbcTemplate.update(sql, sps);
		if (num > 0) {
			return num;
		}
		return 0;
	}

	public List<Packe_technology> findByPnum(String plan_num1) {
		String sql = "select * from packe_technology where p_num=:p_num";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("p_num", plan_num1);
		List<Packe_technology> pt = namedParameterJdbcTemplate.query(sql, sps,
				new BeanPropertyRowMapper<Packe_technology>(
						Packe_technology.class));
		if (pt != null && pt.size() > 0) {
			return pt;
		}
		return null;
	}

	public int findByPnumCount(String plan_num1) {
		String sql = "select count(*) from packe_technology where p_num=:p_num";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("p_num", plan_num1);
		int pt = namedParameterJdbcTemplate.queryForInt(sql, sps);
		return pt;
	}

	// 根据订单号 和包号获取 包绑定工序表的 id
	public Packe_technology getId(String packe_num, String t_name) {
		String sql = "select * from packe_technology where packe_num=:packe_num and technology_name=:technology_name";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("packe_num", packe_num);
		sps.addValue("technology_name", t_name);
		List<Packe_technology> list = namedParameterJdbcTemplate.query(sql,
				sps, new BeanPropertyRowMapper<Packe_technology>(
						Packe_technology.class));
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	public List<Packe_technology> getIds(String packe_num, String t_name){
		String sql = "select * from packe_technology where packe_num=:packe_num and technology_name in ("+t_name+")";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("packe_num", packe_num);
		//sps.addValue("technology_name", t_name);
		List<Packe_technology> list = namedParameterJdbcTemplate.query(sql,
				sps, new BeanPropertyRowMapper<Packe_technology>(
						Packe_technology.class));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	// 获取订单进度数据报表 分页
	public List<PlaceSpeed> queryProgress(String place, int page, int rows) {
		int cur = (page - 1) * rows;
		String sql = "SELECT p_num,technology_name as t_name,SUM(if(state=2,1,0)) as completed,SUM(if(state=1,1,0)) as unfinished FROM packe_technology where p_num=:place GROUP BY technology_name limit :cur,:rows";
		//String sqlx = "";
		
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("place", place);
		sps.addValue("cur", cur);
		sps.addValue("rows", rows);
		List<PlaceSpeed> list = namedParameterJdbcTemplate.query(sql, sps,
				new BeanPropertyRowMapper<PlaceSpeed>(PlaceSpeed.class));
		if (list != null && list.size() > 0) {
			String sqls = "SELECT t_name,SUM(number)as cp_piece_num from sub_data where place_num =:place and fake =0 GROUP BY t_name";
			sps.addValue("place", place);
			List<PlaceSpeed> numlist = namedParameterJdbcTemplate.query(sqls, sps,
					new BeanPropertyRowMapper<PlaceSpeed>(PlaceSpeed.class));
			if(numlist!=null && numlist.size()>0){
				for (int i = 0; i < list.size(); i++) {
					for (int j = 0; j < numlist.size(); j++) {
						if(list.get(i).getT_name().equals(numlist.get(j).getT_name())){
							list.get(i).setCp_piece_num(numlist.get(j).getCp_piece_num());
						}
					}
				}
			}
			return list;
		}
		return null;
	}
	

	// 获取订单进度数据报表
	public List<PlaceSpeed> queryProgressAll(String place) {
		String sql = "SELECT p_num,technology_name as t_name,SUM(if(state=2,1,0)) as completed,SUM(if(state=1,1,0)) as unfinished FROM packe_technology where p_num=:place GROUP BY technology_name";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("place", place);
		List<PlaceSpeed> list = namedParameterJdbcTemplate.query(sql, sps,
				new BeanPropertyRowMapper<PlaceSpeed>(PlaceSpeed.class));
		if (list != null && list.size() > 0) {
			String sqls = "SELECT t_name,SUM(number)as cp_piece_num from sub_data where place_num =:place and fake =0 GROUP BY t_name";
			sps.addValue("place", place);
			List<PlaceSpeed> numlist = namedParameterJdbcTemplate.query(sqls, sps,
					new BeanPropertyRowMapper<PlaceSpeed>(PlaceSpeed.class));
			if(numlist!=null && numlist.size()>0){
				for (int i = 0; i < list.size(); i++) {
					for (int j = 0; j < numlist.size(); j++) {
						if(list.get(i).getT_name().equals(numlist.get(j).getT_name())){
							list.get(i).setCp_piece_num(numlist.get(j).getCp_piece_num());
						}
					}
				}
			}
			return list;
		}
		return null;
	}

	public int findPageTotal(String place) {
		String sql = "SELECT count(*) FROM (select technology_name FROM packe_technology where p_num=:place GROUP BY technology_name) as tmp";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("place", place);
		return namedParameterJdbcTemplate.queryForInt(sql, sps);
	}

	public List<Packe_technology> findListByPNumAndTechnologyName(String pNum,
			String technology_name, int state) {
		String sql = "SELECT p_color as technology_name,p_size as p_num,p_number as state,packe_num from process_dimensio where packe_num in (select packe_num from packe_technology where p_num = :pNum and technology_name =:technology_name and state = :state)";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("pNum", pNum);
		sps.addValue("technology_name", technology_name);
		sps.addValue("state", state);
		List<Packe_technology> ptList = namedParameterJdbcTemplate.query(sql,
				sps, new BeanPropertyRowMapper<Packe_technology>(
						Packe_technology.class));
		if (ptList != null && ptList.size() > 0) {
			return ptList;
		}
		return null;
	}
	
	public List<Packe_technology> findListByPnum(String plan_num1) {
		String sql = "select * from packe_technology where p_num=:p_num GROUP BY packe_num";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("p_num", plan_num1);
		List<Packe_technology> pt = namedParameterJdbcTemplate.query(sql, sps,
				new BeanPropertyRowMapper<Packe_technology>(
						Packe_technology.class));
		if (pt != null && pt.size() > 0) {
			return pt;
		}
		return null;
	}
	
	public List<Packe_technology> getPackeNums(String plan_num,String tname,int state){
		String sql = "select * from packe_technology where p_num=:p_num and state =:state and technology_name =:tname";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("p_num",plan_num);
		sps.addValue("state",state);
		sps.addValue("tname",tname);
		List<Packe_technology> pt = namedParameterJdbcTemplate.query(sql, sps,
				new BeanPropertyRowMapper<Packe_technology>(
						Packe_technology.class));
		if (pt != null && pt.size() > 0) {
			return pt;
		}
		return null;
	}
}
