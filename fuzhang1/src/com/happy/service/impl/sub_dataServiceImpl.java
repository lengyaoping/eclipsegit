package com.happy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.happy.dao.sub_dataDao;
import com.happy.model.Export;
import com.happy.model.Loss;
import com.happy.model.ProductionPiecework;
import com.happy.model.sub_data;
import com.happy.model.weixin.User;
import com.happy.service.sub_dataService;

@Service("sub_dataService")
public class sub_dataServiceImpl implements sub_dataService {
	@Resource
	private sub_dataDao subDao;

	public sub_dataDao getSubDao() {
		return subDao;
	}

	public void setSubDao(sub_dataDao subDao) {
		this.subDao = subDao;
	}

	public List<sub_data> list(String job, String time) {

		return subDao.list(job, time);
	}

	public int addSub(sub_data sd) {
		return subDao.sub(sd);
	}

	public List<sub_data> findlist(String place_num,String str) {

		return subDao.findlist(place_num,str);
	}

	/**
	 * 根据订单号查询已经提交数据的所有人
	 * 
	 * @return
	 * @return
	 */
	public int findlistPeople(String place_num) {
		return subDao.findlistPeople(place_num);
	}

	/**
	 * 根据订单号和当前页和每页条数查询已经提交数据
	 */
	public List<sub_data> findlist(String place_num, int page, int rows) {
		return subDao.findlist(place_num, page, rows);
	}

	public sub_data findById(int id) {
		return subDao.findById(id);
	}

	public int updateById(int id) {
		return subDao.updateById(id);
	}

	public int delete(int id) {
		return subDao.delete(id);
	}

	/**
	 * 批量删除工单
	 */
	public int deleteBatch(String sqlx) {
		return subDao.batchDelete(sqlx);
	}

	public List<sub_data> findByjob(String job_number) {
		return subDao.findByjob(job_number);
	}

	/**
	 * 根据员工号 当前页 每页条数查询员工提交的工单数据
	 * 
	 * @param job_number
	 * @return
	 */
	public List<sub_data> findByjob(String job_number, int page, int rows) {
		return subDao.findByjob(job_number, page, rows);
	}

	public List<sub_data> combinatoriaQuery(String advanceFilter,
			String job_number) {
		return subDao.combinatoriaQuery(advanceFilter, job_number);
	}

	public List<sub_data> combinatoriaQuery(String advanceFilter,
			String job_number, int page, int rows) {
		return subDao.combinatoriaQuery(advanceFilter, job_number, page, rows);
	}

	public List<Export> exportFind(String job_number) {
		return subDao.exportFind(job_number);
	}

	public List<sub_data> queryList(String zd, String tj, String zhi,
			String place_num, int page, int rows) {

		return subDao.queryList(zd, tj, zhi, place_num, page, rows);
	}

	public List<sub_data> combGetsubList(String sql, String place_num) {
		return subDao.combGetsubList(sql, place_num);
	}

	public List<sub_data> combGetsubList(String sql, String place_num,
			int page, int rows) {
		return subDao.combGetsubList(sql, place_num, page, rows);
	}

	public int findlistCount(String plan_num1) {
		return subDao.findlistCount(plan_num1);
	}

	public sub_data findByPnPkT(String place_num, String packe_num,
			String technology_name) {
		return subDao.findByPnPkT(place_num, packe_num, technology_name);
	}
	public List<sub_data> findListByPnPkT(String place_num, String packe_num,
			String technology_name) {
		return subDao.findListByPnPkT(place_num, packe_num, technology_name);
	}
	public int findfinish(String place_num) {

		return subDao.findfinish(place_num);
	}

	public List<sub_data> findByPk(String packe_num) {
		return subDao.findByPk(packe_num);
	}

	public List<Export> exportFind(String sql, String job_number) {
		return subDao.exportFind(sql, job_number);
	}

	public List<ProductionPiecework> findAll() {
		return subDao.findAll();
	}

	public List<ProductionPiecework> findAll(String sql) {
		return subDao.findAll(sql);
	}
	public List<ProductionPiecework> findAllN(String s1,String s2,String s3) {
		StringBuffer sql = new StringBuffer("select d.user_name,a.place_num,a.style,a.t_name,a.price,SUM(a.number)as number,(a.price * SUM(a.number)) as money,a.job_number "+
				"from (select g.place_num,g.t_name,g.number,g.job_number,g.price,c.style from "+
				"(select e.place_num,e.t_name,e.number,e.job_number,f.price "+
				"from sub_data e, place_technology f "+
				"WHERE e.fake =0 and e.place_num=f.place_number and e.t_name=f.technology_name"+s1+") g,place c WHERE g.place_num=c.plan_num");
		if(s3!=null&&!(s3.equals(""))){
			sql.append(s3);
		}
		sql.append(") a,(select user_name,job_number from `user` WHERE fake=0");
		if(s2!=null&&!(s2.equals(""))){
			sql.append(s2);
		}
		sql.append(") d WHERE a.job_number=d.job_number GROUP BY a.job_number,d.user_name,a.place_num,a.t_name order by d.user_name,a.job_number,a.place_num,a.t_name desc");
		return subDao.findAll(sql.toString());
	}

	public int findComdPageTotal(String sql, String place_num) {
		return subDao.findComdPageTotal(sql, place_num);
	}

	public int findPageTotal(String place_num) {
		return subDao.findPageTotal(place_num);
	}

	public int findCombPageTotalByJob(String sql, String job_number) {
		return subDao.findCombPageTotalByJob(sql, job_number);
	}

	public int findPageTotalByJob(String job_number) {
		return subDao.findPageTotalByJob(job_number);
	}

	public int batchWages(String sql) {
		return subDao.batchWages(sql);
	}

	public List<sub_data> getListByJnumAndTime(String job, String start,
			String endt) {
		
		return subDao.getListByJnumAndTime(job,start,endt);
	}
	public List<sub_data> getListByJnumAndTimeT(String job,String start,String endt){
		return subDao.getListByJnumAndTimeT(job, start, endt);
	}
	public List<User> findByPlace(String place) {
		// TODO Auto-generated method stub
		return subDao.findUsersByPlace(place);
	}

	public int addSubs(String sql) {
		// TODO Auto-generated method stub
		return subDao.addSubs(sql);
	}
	
	public int findComdLossPageTotal(String sqlx, String place_num){
		return subDao.findComdLossPageTotal(sqlx, place_num);
	}
	public List<Loss> combGetLossList(String sqlx, String place_num,
				int page, int rows){
		return subDao.combGetLossList( sqlx, place_num,page, rows);
	}
	
	public int findLossPageTotal(String place_num){
		return subDao.findLossPageTotal(place_num);
	}
	public List<Loss> findLosslist(String place_num, int page, int rows){
		return subDao.findLosslist(place_num, page, rows);
	}

}
