package com.happy.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.happy.dao.sub_dataDao;
import com.happy.model.Export;
import com.happy.model.Loss;
import com.happy.model.ProductionPiecework;
import com.happy.model.sub_data;
import com.happy.model.weixin.User;

@Repository("sub_dataDao")
public class sub_dataDaoImpl implements sub_dataDao {
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}

	public void setNamedParameterJdbcTemplate(
			NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	public List<sub_data> list(String job, String time) {
		String sql = "select * from sub_data where job_number=:job and fake=0 and sub_time LIKE '%"
				+ time + "%'";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("job", job);
		List<sub_data> list = namedParameterJdbcTemplate.query(sql, sps,
				new BeanPropertyRowMapper<sub_data>(sub_data.class));
		if (list != null && list.size() > 0) {
			return list;
		}

		return null;
	}
	public List<sub_data> getListByJnumAndTime(String job, String start,
			String endt) {
		String sql = "select COUNT(*)as packe_num,a.place_num,a.t_name,a.sub_time,a.state,SUM(a.number)as number,a.money,a.price,b.style as user_name from sub_data as a,place as b where a.state = 1 and a.place_num=b.plan_num and a.job_number=:job and a.fake=0 and sub_time >= :start and sub_time <= :endt GROUP BY a.place_num,a.t_name ORDER BY place_num";
		
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("job", job);
		sps.addValue("start", start);
		sps.addValue("endt", endt+"24:00:00");
		List<sub_data> list = namedParameterJdbcTemplate.query(sql, sps,
				new BeanPropertyRowMapper<sub_data>(sub_data.class));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	public List<sub_data> getListByJnumAndTimeT(String job, String start,
			String endt) {
		String sql = "select * from sub_data where state = 1 and job_number=:job and fake=0 and sub_time >=:start and sub_time <=:endt ORDER BY sub_time desc";
		
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("job", job);
		sps.addValue("start", start);
		sps.addValue("endt", endt+"24:00:00");
		List<sub_data> list = namedParameterJdbcTemplate.query(sql, sps,
				new BeanPropertyRowMapper<sub_data>(sub_data.class));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	public int sub(sub_data sd) {
		HashMap<String, Object> tableEntityMap = setHashMapByTableEntity(sd);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "insert into sub_data (place_num,packe_num,t_name,job_number,user_name,sub_time,fake,state,number,price,money) values(:place_num,:packe_num,:t_name,:job_number,:user_name,:sub_time,:fake,:state,:number,:price,:money)";
		MapSqlParameterSource sps = new MapSqlParameterSource(tableEntityMap);
		sps.addValue("place_num", sd.getPlace_num());
		sps.addValue("packe_num", sd.getPacke_num());
		sps.addValue("t_name", sd.getT_name());
		sps.addValue("job_number", sd.getJob_number());
		sps.addValue("user_name", sd.getUser_name());
		sps.addValue("sub_time", sd.getSub_time());
		sps.addValue("fake", 0);
		sps.addValue("state", 1);
		sps.addValue("number", sd.getNumber());
		sps.addValue("price", sd.getPrice());
		sps.addValue("money", sd.getMoney());
		int num = namedParameterJdbcTemplate.update(sql, sps,keyHolder,new String[] { "id" });
		if(num>0){
			num = keyHolder.getKey().intValue();
		}
		return num;
	}
	public int subD(String sql){
		MapSqlParameterSource sps = new MapSqlParameterSource();
		int num = namedParameterJdbcTemplate.update(sql, sps);
		if (num > 0) {
			return num;
		}
		return 0;
	}
	private HashMap<String, Object> setHashMapByTableEntity(sub_data sd) {
		HashMap<String, Object> tableEntityMap  = new HashMap<String, Object>();
		
	    tableEntityMap .put("place_num", sd.getPlace_num());
	    tableEntityMap .put("packe_num", sd.getPacke_num());
	    tableEntityMap .put("t_name", sd.getT_name());
	    tableEntityMap .put("job_number", sd.getJob_number());
	    tableEntityMap .put("user_name", sd.getUser_name());
	    tableEntityMap .put("sub_time", sd.getSub_time());
	    tableEntityMap .put("fake", sd.getFake());
	    tableEntityMap .put("state", sd.getState());
	    tableEntityMap .put("number", sd.getNumber());
	    tableEntityMap .put("money", sd.getMoney());
	    tableEntityMap .put("price", sd.getPrice());
	    return tableEntityMap ;
	}

	public List<sub_data> findlist(String place_num,String str) {
		String sql = "select * from sub_data where fake=0 and place_num=:place_num and packe_num=:packe_num";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("place_num", place_num);
		sps.addValue("packe_num", str);
		List<sub_data> list = namedParameterJdbcTemplate.query(sql, sps,
				new BeanPropertyRowMapper<sub_data>(sub_data.class));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * 根据订单号查询已经提交了多少工序
	 */
	public int findfinish(String place_num) {
		try {
			String sql = "select count(DISTINCT id) from sub_data where  place_num=:place_num and fake=0  ";
			MapSqlParameterSource sps = new MapSqlParameterSource();
			sps.addValue("place_num", place_num);
			int count = namedParameterJdbcTemplate.queryForObject(sql, sps,
					Integer.class);
			return count;
		} catch (DataAccessException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 根据订单号查询已经提交数据的所有人不重复
	 */
	public int findlistPeople(String place_num) {
		try {
			String sql = "select count(DISTINCT user_name) from sub_data where  place_num=:place_num and fake=0  ";
			MapSqlParameterSource sps = new MapSqlParameterSource();
			sps.addValue("place_num", place_num);
			int count = namedParameterJdbcTemplate.queryForObject(sql, sps,
					Integer.class);
			return count;
		} catch (DataAccessException e) {
			e.printStackTrace();
			return 0;

		}
	}

	/**
	 * 根据订单号和当前页和每页条数查询已经提交数据
	 */
	public List<sub_data> findlist(String place_num, int page, int rows) {
		int start = (page - 1) * rows;// 每页的起始下标
		String sql = "select * from sub_data where  place_num=:place_num and fake=0 order by packe_num asc limit :start,:rows ";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("place_num", place_num);
		sps.addValue("start", start);
		sps.addValue("rows", rows);
		List<sub_data> list = namedParameterJdbcTemplate.query(sql, sps,
				new BeanPropertyRowMapper<sub_data>(sub_data.class));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;

	}

	// 根据id查询提交记录
	public sub_data findById(int id) {
		String sql = "select * from sub_data where id=:id and fake=0";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("id", id);
		List<sub_data> list = namedParameterJdbcTemplate.query(sql, sps,
				new BeanPropertyRowMapper<sub_data>(sub_data.class));
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public int updateById(int id) {
		String sql = "update sub_data set state=2 where id=:id and fake=0";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("id", id);
		int num = namedParameterJdbcTemplate.update(sql, sps);
		if (num != 0) {
			return num;
		}
		return 0;
	}

	public int delete(int id) {
		String sql = "update sub_data set fake=1 where id=:id";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("id", id);
		return namedParameterJdbcTemplate.update(sql, sps);
	}

	// 批量删除工单
	public int batchDelete(String sqlx) {
		String sql = sqlx;
		MapSqlParameterSource sps = new MapSqlParameterSource();
		int num = namedParameterJdbcTemplate.update(sql, sps);
		if (num > 0) {
			return num;
		}
		return 0;
	}

	public List<sub_data> findByjob(String job_number) {
		String sql = "select * from sub_data where job_number=:job_number and fake=0";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("job_number", job_number);
		List<sub_data> list = namedParameterJdbcTemplate.query(sql, sps,
				new BeanPropertyRowMapper<sub_data>(sub_data.class));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * 根据员工号 当前页 每页条数查询员工提交的工单数据
	 * 
	 * @param job_number
	 * @return
	 */
	public List<sub_data> findByjob(String job_number, int page, int rows) {
		int start = (page - 1) * rows;// 每页的起始下标
		String sql = "select * from sub_data where job_number=:job_number and fake=0 order by sub_time asc limit :start,:rows ";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("job_number", job_number);
		sps.addValue("start", start);
		sps.addValue("rows", rows);
		List<sub_data> list = namedParameterJdbcTemplate.query(sql, sps,
				new BeanPropertyRowMapper<sub_data>(sub_data.class));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	// 根据员工工号组合查询
	public List<sub_data> combinatoriaQuery(String advanceFilter,
			String job_number) {
		String newsql = "";
		int o = advanceFilter.indexOf("order by");
		if (o > -1) {
			StringBuffer sb = new StringBuffer(advanceFilter);
			sb.insert(o, "and job_number='" + job_number + "' ");
			newsql = sb.toString();
		} else {
			StringBuffer sb = new StringBuffer(advanceFilter);
			sb.insert(advanceFilter.length(), " and job_number='" + job_number
					+ "' ");
			newsql = sb.toString();
		}
		String sql = "select * from sub_data " + newsql;
		MapSqlParameterSource sps = new MapSqlParameterSource();
		List<sub_data> list = namedParameterJdbcTemplate.query(sql, sps,
				new BeanPropertyRowMapper<sub_data>(sub_data.class));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	// 根据员工工号组合查询
	public List<sub_data> combinatoriaQuery(String advanceFilter,
			String job_number, int page, int rows) {
		int start = (page - 1) * rows;// 每页的起始下标
		String newsql = "";
		int o = advanceFilter.indexOf("order by");
		if (o > -1) {
			StringBuffer sb = new StringBuffer(advanceFilter);
			sb.insert(o, "and job_number='" + job_number + "' ");
			newsql = sb.toString();
		} else {
			StringBuffer sb = new StringBuffer(advanceFilter);
			sb.insert(advanceFilter.length(), " and job_number='" + job_number
					+ "' ");
			newsql = sb.toString();
		}

		String sql = "select * from sub_data " + newsql
				+ " limit :start,:rows ";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("start", start);
		sps.addValue("rows", rows);
		List<sub_data> list = namedParameterJdbcTemplate.query(sql, sps,
				new BeanPropertyRowMapper<sub_data>(sub_data.class));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	// 导出结果查询
	public List<Export> exportFind(String job_number) {
		String sql = "select place_num,packe_num,t_name,price,number,money,job_number,user_name,sub_time"
				+ " from sub_data where (state=1 or state=3) and job_number=:job_number and fake=0 order by packe_num desc";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("job_number", job_number);
		List<Export> list = namedParameterJdbcTemplate.query(sql, sps,
				new BeanPropertyRowMapper<Export>(Export.class));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	public List<sub_data> queryList(String zd, String tj, String zhi,
			String place_num, int page, int rows) {
		// 查询语句拼接
		String sql = "select * from sub_data where place_num=:place_num ";
		if (tj.equals("contains")) {// 包含
			sql += " and "
					+ zd
					+ " like '%"
					+ zhi
					+ "%' and fake=0 order by packe_num desc limit :start,:rows ";
		} else if (tj.equals("=")) {// 等于
			sql += " and "
					+ zd
					+ "='"
					+ zhi
					+ "' and fake=0 order by packe_num desc limit :start,:rows ";
		} else if (tj.equals("!=")) {// 不等于
			sql += " and "
					+ zd
					+ "<>'"
					+ zhi
					+ "' and fake=0 order by packe_num desc limit :start,:rows ";
		} else if (tj.equals(">")) {// 大于
			sql += " and "
					+ zd
					+ ">'"
					+ zhi
					+ "' and fake=0 order by packe_num desc limit :start,:rows ";
		} else if (tj.equals(">=")) {// 大于或等于
			sql += " and "
					+ zd
					+ ">='"
					+ zhi
					+ "' and fake=0 order by packe_num desc limit :start,:rows ";
		} else if (tj.equals("<")) {// 小于
			sql += " and "
					+ zd
					+ "<'"
					+ zhi
					+ "' and fake=0 order by packe_num desc limit :start,:rows ";
		} else if (tj.equals("<=")) {// 小于或等于
			sql += " and "
					+ zd
					+ "<='"
					+ zhi
					+ "' and fake=0 order by packe_num desc limit :start,:rows ";
		} else if (tj.equals("beginwith")) {// 以....开头
			sql += " and "
					+ zd
					+ " like '"
					+ zhi
					+ "%' and fake=0 order by packe_num desc limit :start,:rows ";
		} else if (tj.equals("endwith")) {// 以....结尾
			sql += " and "
					+ zd
					+ " like '%"
					+ zhi
					+ "' and fake=0 order by packe_num desc limit :start,:rows ";
		}
		int start = (page - 1) * rows;// 每页的起始下标
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("place_num", place_num);
		sps.addValue("start", start);
		sps.addValue("rows", rows);
		List<sub_data> list = namedParameterJdbcTemplate.query(sql, sps,
				new BeanPropertyRowMapper<sub_data>(sub_data.class));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	// 提交记录根据订单号组合查询
	public List<sub_data> combGetsubList(String sqlx, String place_num) {
		String newsql = "";
		int o = sqlx.indexOf("order by");
		if (o > -1) {
			StringBuffer sb = new StringBuffer(sqlx);
			sb.insert(o, "and place_num='" + place_num + "' ");
			newsql = sb.toString();
		} else {
			StringBuffer sb = new StringBuffer(sqlx);
			sb.insert(sqlx.length(), " and place_num='" + place_num + "' ");
			newsql = sb.toString();
		}
		String sql = "select * from sub_data " + newsql;
		MapSqlParameterSource sps = new MapSqlParameterSource();
		List<sub_data> list = namedParameterJdbcTemplate.query(sql, sps,
				new BeanPropertyRowMapper<sub_data>(sub_data.class));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	// 提交记录根据订单号组合查询
	public List<sub_data> combGetsubList(String sqlx, String place_num,
			int page, int rows) {
		int start = (page - 1) * rows;// 每页的起始下标
		String newsql = "";
		int o = sqlx.indexOf("order by");
		if (o > -1) {
			StringBuffer sb = new StringBuffer(sqlx);
			sb.insert(o, "and place_num='" + place_num + "' ");
			newsql = sb.toString();
		} else {
			StringBuffer sb = new StringBuffer(sqlx);
			sb.insert(sqlx.length(), " and place_num='" + place_num + "' ");
			newsql = sb.toString();
		}

		String sql = "select * from sub_data " + newsql
				+ " limit :start,:rows ";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("start", start);
		sps.addValue("rows", rows);
		List<sub_data> list = namedParameterJdbcTemplate.query(sql, sps,
				new BeanPropertyRowMapper<sub_data>(sub_data.class));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	// 查询该订单号的提交数量
	public int findlistCount(String plan_num1) {
		String sql = "select count(*) from sub_data where place_num=:place_num";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("place_num", plan_num1);
		int n = namedParameterJdbcTemplate.queryForInt(sql, sps);
		return n;
	}

	// 根据订单号和包号以及工序名称查询 提交记录
	public sub_data findByPnPkT(String place_num, String packe_num,
			String technology_name) {
		String sql = "select * from sub_data where place_num=:place_num and packe_num=:packe_num and t_name=:t_name and fake=0";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("place_num", place_num);
		sps.addValue("packe_num", packe_num);
		sps.addValue("t_name", technology_name);
		List<sub_data> list = namedParameterJdbcTemplate.query(sql, sps,
				new BeanPropertyRowMapper<sub_data>(sub_data.class));
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	public List<sub_data> findListByPnPkT(String place_num, String packe_num,
			String technology_name) {
		String sql = "select * from sub_data where place_num=:place_num and packe_num=:packe_num and t_name in("+technology_name+") and fake=0";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("place_num", place_num);
		sps.addValue("packe_num", packe_num);
		//sps.addValue("t_name", technology_name);
		List<sub_data> list = namedParameterJdbcTemplate.query(sql, sps,
				new BeanPropertyRowMapper<sub_data>(sub_data.class));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	// 根据包号查询提交记录
	public List<sub_data> findByPk(String packe_num) {
		String sql = "select * from sub_data where packe_num=:packe_num and fake=0";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("packe_num", packe_num);
		List<sub_data> list = namedParameterJdbcTemplate.query(sql, sps,
				new BeanPropertyRowMapper<sub_data>(sub_data.class));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	// 带条件查询出导出结果
	public List<Export> exportFind(String sqlx, String job_number) {
		String newsql = "";
		int o = sqlx.indexOf("order by");
		if (o > -1) {
			StringBuffer sb = new StringBuffer(sqlx);
			sb.insert(o, "and job_number='" + job_number + "' ");
			newsql = sb.toString();
		} else {
			StringBuffer sb = new StringBuffer(sqlx);
			sb.insert(sqlx.length(),
					" and (state=1 or state=3) and job_number='" + job_number
							+ "' ");
			newsql = sb.toString();
		}
		String sql = "select place_num,packe_num,t_name,price,number,money,job_number,user_name,sub_time from sub_data "
				+ newsql;
		MapSqlParameterSource sps = new MapSqlParameterSource();
		List<Export> list = namedParameterJdbcTemplate.query(sql, sps,
				new BeanPropertyRowMapper<Export>(Export.class));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	// 无条件的查询所有的提交记录统计成员工计报表
	public List<ProductionPiecework> findAll() {
		String sql = "select a.user_name,a.place_num,c.style,a.t_name,b.price,SUM(a.number)number,(b.price * SUM(a.number)) as money,a.job_number from sub_data a,place_technology b,place c WHERE a.state=1 and a.place_num=b.place_number and a.t_name=b.technology_name and a.place_num=c.plan_num GROUP BY a.user_name,a.place_num,a.t_name order by a.user_name,a.place_num,a.t_name desc";// limit
																																																																																																// 100
		MapSqlParameterSource sps = new MapSqlParameterSource();
		List<ProductionPiecework> list = namedParameterJdbcTemplate.query(sql,
				sps, new BeanPropertyRowMapper<ProductionPiecework>(
						ProductionPiecework.class));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	// 有条件的查询所有的提交记录统计成员工计报表
	public List<ProductionPiecework> findAll(String sqlx) {
		String sql = sqlx;
		MapSqlParameterSource sps = new MapSqlParameterSource();
		List<ProductionPiecework> list = namedParameterJdbcTemplate.query(sql,
				sps, new BeanPropertyRowMapper<ProductionPiecework>(
						ProductionPiecework.class));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	public int findComdPageTotal(String sqlx, String place_num) {
		String newsql = "";
		int o = sqlx.indexOf("order by");
		if (o > -1) {
			StringBuffer sb = new StringBuffer(sqlx);
			sb.insert(o, "and place_num='" + place_num + "' ");
			newsql = sb.toString();
		} else {
			StringBuffer sb = new StringBuffer(sqlx);
			sb.insert(sqlx.length(), " and place_num='" + place_num + "' ");
			newsql = sb.toString();
		}
		String sql = "select count(*) from sub_data " + newsql;
		MapSqlParameterSource sps = new MapSqlParameterSource();
		return namedParameterJdbcTemplate.queryForInt(sql, sps);
	}

	public int findPageTotal(String place_num) {
		String sql = "select count(*) from sub_data where place_num=:place_num and fake=0";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("place_num", place_num);
		return namedParameterJdbcTemplate.queryForInt(sql, sps);
	}

	public int findCombPageTotalByJob(String advanceFilter, String job_number) {
		String newsql = "";
		int o = advanceFilter.indexOf("order by");
		if (o > -1) {
			StringBuffer sb = new StringBuffer(advanceFilter);
			sb.insert(o, "and job_number='" + job_number + "' ");
			newsql = sb.toString();
		} else {
			StringBuffer sb = new StringBuffer(advanceFilter);
			sb.insert(advanceFilter.length(), " and job_number='" + job_number
					+ "' ");
			newsql = sb.toString();
		}
		String sql = "select count(*) from sub_data " + newsql;
		MapSqlParameterSource sps = new MapSqlParameterSource();
		return namedParameterJdbcTemplate.queryForInt(sql, sps);
	}

	public int findPageTotalByJob(String job_number) {
		String sql = "select count(*) from sub_data where job_number=:job_number and fake=0";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("job_number", job_number);
		return namedParameterJdbcTemplate.queryForInt(sql, sps);
	}

	public int batchWages(String sql) {
		MapSqlParameterSource sps = new MapSqlParameterSource();
		return namedParameterJdbcTemplate.update(sql, sps);
	}

	public List<User> findUsersByPlace(String place_num) {
		String sql = "SELECT user_name,job_number from sub_data where fake =0 and place_num =:place_num GROUP BY job_number";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("place_num", place_num);
		List<User> list = namedParameterJdbcTemplate.query(sql, sps,
				new BeanPropertyRowMapper<User>(User.class));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	public int addSubs(String sql){
		
		MapSqlParameterSource sps = new MapSqlParameterSource();
		int num = namedParameterJdbcTemplate.update(sql, sps);
		return num;
	}

	public int findComdLossPageTotal(String sqlx, String place_num) {
		String newsql = "";
		int o = sqlx.indexOf("order by");
		if (o > -1) {
			StringBuffer sb = new StringBuffer(sqlx);
			sb.insert(o, "and place_num='" + place_num + "' ");
			newsql = sb.toString();
		} else {
			StringBuffer sb = new StringBuffer(sqlx);
			sb.insert(sqlx.length(), " and place_num='" + place_num + "' ");
			newsql = sb.toString();
		}
		String sql = "select count(*) from loss " + newsql;
		MapSqlParameterSource sps = new MapSqlParameterSource();
		return namedParameterJdbcTemplate.queryForInt(sql, sps);
	}
	// 提交记录根据订单号组合查询
		public List<Loss> combGetLossList(String sqlx, String place_num,
				int page, int rows) {
			int start = (page - 1) * rows;// 每页的起始下标
			String newsql = "";
			int o = sqlx.indexOf("order by");
			if (o > -1) {
				StringBuffer sb = new StringBuffer(sqlx);
				sb.insert(o, "and place_num='" + place_num + "' ");
				newsql = sb.toString();
			} else {
				StringBuffer sb = new StringBuffer(sqlx);
				sb.insert(sqlx.length(), " and place_num='" + place_num + "' ");
				newsql = sb.toString();
			}

			String sql = "select * from loss " + newsql
					+ " limit :start,:rows ";
			MapSqlParameterSource sps = new MapSqlParameterSource();
			sps.addValue("start", start);
			sps.addValue("rows", rows);
			List<Loss> list = namedParameterJdbcTemplate.query(sql, sps,
					new BeanPropertyRowMapper<Loss>(Loss.class));
			if (list != null && list.size() > 0) {
				return list;
			}
			return null;
		}
		public int findLossPageTotal(String place_num) {
			String sql = "select count(id) from loss where place_num=:place_num and fake=0";
			MapSqlParameterSource sps = new MapSqlParameterSource();
			sps.addValue("place_num", place_num);
			return namedParameterJdbcTemplate.queryForInt(sql, sps);
		}
		public List<Loss> findLosslist(String place_num, int page, int rows) {
			int start = (page - 1) * rows;// 每页的起始下标
			String sql = "select * from loss where  place_num=:place_num and fake=0 order by color asc limit :start,:rows ";
			MapSqlParameterSource sps = new MapSqlParameterSource();
			sps.addValue("place_num", place_num);
			sps.addValue("start", start);
			sps.addValue("rows", rows);
			List<Loss> list = namedParameterJdbcTemplate.query(sql, sps,
					new BeanPropertyRowMapper<Loss>(Loss.class));
			if (list != null && list.size() > 0) {
				return list;
			}
			return null;

		}
}
