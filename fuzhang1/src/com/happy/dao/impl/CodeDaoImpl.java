package com.happy.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.happy.dao.CodeDao;
import com.happy.model.Loss;
import com.happy.model.Packe_technology;
import com.happy.model.place_technology;
import com.happy.model.process_dimensio;
import com.sun.org.apache.regexp.internal.recompile;

@Repository("codeDao")
public class CodeDaoImpl implements CodeDao {
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}

	public void setNamedParameterJdbcTemplate(
			NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	// 根据订单号查询包记录
	public List<process_dimensio> findByNumber(String planNum) {
		String sql = "select * from process_dimensio where fake=0 and p_num=:p_num order by `packe_num` asc";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("p_num", planNum);
		List<process_dimensio> list = namedParameterJdbcTemplate.query(sql,
				sps, new BeanPropertyRowMapper<process_dimensio>(
						process_dimensio.class));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	// 根据订单号 当前页和每页条数查询包记录
	public List<process_dimensio> findByNumber(String planNum, int page,
			int rows) {
		int start = (page - 1) * rows;// 每页的起始下标
		String sql = "select * from process_dimensio where fake=0 and p_num=:p_num order by `packe_num` asc limit :start,:rows";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("p_num", planNum);
		sps.addValue("start", start);
		sps.addValue("rows", rows);
		List<process_dimensio> list = namedParameterJdbcTemplate.query(sql,
				sps, new BeanPropertyRowMapper<process_dimensio>(
						process_dimensio.class));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	// 添加新的包
	public int addPack(process_dimensio process_dimensio) {
		String sql = "insert into process_dimensio (p_num,p_color,p_size,packe_num,p_number,cylinder,girard,production_number,bed,fake,completed,current,surplus) values(:p_num,:p_color,:p_size,:packe_num,:p_number,:cylinder,:girard,:production_number,:bed,:fake,:completed,:current,:surplus)";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("p_num", process_dimensio.getP_num());
		sps.addValue("p_color", process_dimensio.getP_color());
		sps.addValue("p_size", process_dimensio.getP_size());
		sps.addValue("packe_num", process_dimensio.getPacke_num());
		sps.addValue("p_number", process_dimensio.getP_number());
		sps.addValue("cylinder", process_dimensio.getCylinder());
		sps.addValue("girard", process_dimensio.getGirard());
		sps.addValue("production_number", "1");
		sps.addValue("bed", 1);
		sps.addValue("fake", 0);
		sps.addValue("completed", process_dimensio.getCompleted());
		sps.addValue("current", process_dimensio.getCurrent());
		sps.addValue("surplus", process_dimensio.getSurplus());
		int num = namedParameterJdbcTemplate.update(sql, sps);
		if (num > 0) {
			return num;
		}
		return 0;
	}

	// 根据包号查询包记录
	public process_dimensio findByPack(String packe_num) {
		String sql = "select * from process_dimensio where packe_num=:packe_num and fake=0";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("packe_num", packe_num);
		List<process_dimensio> list = namedParameterJdbcTemplate.query(sql,
				sps, new BeanPropertyRowMapper<process_dimensio>(
						process_dimensio.class));
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public int dele(int id) {
		String sql = "update process_dimensio set fake=1 where id=:id";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("id", id);
		return namedParameterJdbcTemplate.update(sql, sps);
	}

	// 改变包号表的已完成个数
	public int updatePd(String packe_num) {
		String sql = "update process_dimensio set completed=completed+1,surplus=surplus-1 where packe_num=:packe_num and fake=0";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("packe_num", packe_num);
		return namedParameterJdbcTemplate.update(sql, sps);
	}

	// 批量操作包表
	public int batchAddPack(String sqlx) {
		String sql = sqlx;
		MapSqlParameterSource sps = new MapSqlParameterSource();
		int num = namedParameterJdbcTemplate.update(sql, sps);
		if (num > 0) {
			return num;
		}
		return 0;
	}

	// 编辑包信息
	public int updatePd(process_dimensio pd) {
		String sql = "update process_dimensio set p_color=:p_color,p_size=:p_size,p_number=:p_number,cylinder=:cylinder where packe_num=:packe_num";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("p_color", pd.getP_color());
		sps.addValue("p_size", pd.getP_size());
		sps.addValue("p_number", pd.getP_number());
		sps.addValue("cylinder", pd.getCylinder());
		sps.addValue("packe_num", pd.getPacke_num());
		int num = namedParameterJdbcTemplate.update(sql, sps);
		if (num > 0) {
			return num;
		}
		return 0;
	}

	// 根据包号批量多表删除 包表(process_dimensio)和包邦定工序表(packe_technology)
	public int deletePacke_tAndP_d(String sql1) {
		String sql = "DELETE a.*,b.* FROM process_dimensio a LEFT JOIN packe_technology b ON a.packe_num=b.packe_num WHERE a.packe_num in"
				+ sql1;
		MapSqlParameterSource sps = new MapSqlParameterSource();
		int num = namedParameterJdbcTemplate.update(sql, sps);
		if (num > 0) {
			return num;
		}
		return 0;
	}

	// 根据订单号查询总包数
	public int findPackNum(String pack_num) {
		String sql = "select COUNT(DISTINCT id) from process_dimensio where fake=0 and p_num=:p_num";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("p_num", pack_num);
		int n = namedParameterJdbcTemplate.queryForInt(sql, sps);
		return n;
	}

	// 根据订单号查询总工序
	public int findGongXu(String pack_num) {
		String sql = "select sum(completed+surplus) from (select p.completed,p.surplus from process_dimensio p where fake=0 and p_num=:p_num limit 1) t";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("p_num", pack_num);
		int n = namedParameterJdbcTemplate.queryForInt(sql, sps);
		return n;
	}

	public process_dimensio findByPlace(String p_num) {
		String sql = "select packe_num from process_dimensio where fake=0 and p_num=:p_num and id=(SELECT max(id) FROM process_dimensio WHERE fake=0 and p_num=:p_num)";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("p_num", p_num);
		List<process_dimensio> list = namedParameterJdbcTemplate.query(sql,
				sps, new BeanPropertyRowMapper<process_dimensio>(
						process_dimensio.class));
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	// 分页查询总记录数
	public int findPageTotal(String place_num) {
		String sql = "select count(*) from process_dimensio where fake=0 and p_num=:p_num";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("p_num", place_num);
		int i = namedParameterJdbcTemplate.queryForInt(sql, sps);
		return i;
	}
	
	public int findNumberAByPlace(String place_num){
		String sql = "SELECT SUM(p_number) from process_dimensio where p_num =:p_num";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("p_num", place_num);
		int i = namedParameterJdbcTemplate.queryForInt(sql, sps);
		return i;
	}
	
	public List<process_dimensio> findListByPacknums(String sql){
		MapSqlParameterSource sps = new MapSqlParameterSource();
		List<process_dimensio> list = namedParameterJdbcTemplate.query(sql,
				sps, new BeanPropertyRowMapper<process_dimensio>(
						process_dimensio.class));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	
	public int addLoss(Loss loss){
		String sql = "insert into loss (place_num,color,size,loss_num,loss_type,remark,fake) values(:place_num,:color,:size,:loss_num,:loss_type,:remark,:fake)";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("place_num", loss.getPlace_num());
		sps.addValue("color", loss.getColor());
		sps.addValue("size", loss.getSize());
		sps.addValue("loss_num", loss.getLoss_num());
		sps.addValue("loss_type", loss.getLoss_type());
		sps.addValue("remark", loss.getRemark());
		sps.addValue("fake", 0);
		int num = namedParameterJdbcTemplate.update(sql, sps);
		if (num > 0) {
			return num;
		}
		return 0;
	}
	
	public List<Loss> getListByPlace(String place_num){
		String sql = "SELECT size,color,loss_type,SUM(loss_num) loss_num from loss where fake =0 and place_num = :place GROUP BY size,color";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("place", place_num);
		List<Loss> list = namedParameterJdbcTemplate.query(sql,
				sps, new BeanPropertyRowMapper<Loss>(
						Loss.class));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	
	public int deleteLossById(String ids){
		String sql = "delete from loss where id in ("+ids+")";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		int num = namedParameterJdbcTemplate.update(sql, sps);
		if(num>0){
			return num;
		}
		return 0;
	}
	public int updateLost(Loss lo){
		String sql = "update loss set color=:color,size=:size,loss_num=:loss_num,loss_type=:loss_type where id=:id";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("color", lo.getColor());
		sps.addValue("size", lo.getSize());
		sps.addValue("loss_num", lo.getLoss_num());
		sps.addValue("loss_type", lo.getLoss_type());
		sps.addValue("id", lo.getId());
		int num = namedParameterJdbcTemplate.update(sql, sps);
		if(num>0){
			return num;
		}
		return 0;
	}
	
	public Loss getLostById(Integer id){
		String sql = "select * from loss where id=:id";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("id", id);
		List<Loss> lo = namedParameterJdbcTemplate.query(sql,sps, new BeanPropertyRowMapper<Loss>(
				Loss.class));
		if(lo!=null){
			return lo.get(0);
		}
		return null;
	}
}
