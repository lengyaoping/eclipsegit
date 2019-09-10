package com.happy.service;

import java.util.List;

import com.happy.model.Export;
import com.happy.model.Loss;
import com.happy.model.ProductionPiecework;
import com.happy.model.sub_data;
import com.happy.model.weixin.User;

public interface sub_dataService {
	/**
	 * 根据工号，时间查询工单
	 */
	public List<sub_data> list(String job, String time);
	
	/**
	 * 查询一段时间内一个员工提交的数据
	 * @param job
	 * @param start
	 * @param endt
	 * @return
	 */
	public List<sub_data> getListByJnumAndTime(String job,String start,String endt); 
	public List<sub_data> getListByJnumAndTimeT(String job,String start,String endt); 
	/**
	 * 提交工序
	 */
	public int addSub(sub_data sd);
	/**
	 * 提交工序
	 */
	public int addSubs(String sql);
	/**
	 * 根据订单号查询已经提交数据
	 */
	public List<sub_data> findlist(String place_num,String str);

	/**
	 * 根据订单号查询已经提交数据的所有人
	 */
	public int findlistPeople(String place_num);

	/**
	 * 根据订单号查询已经提交了多少工序
	 * 
	 * @param place_num
	 * @return
	 */
	public int findfinish(String place_num);

	/**
	 * 根据订单号和当前页和每页条数查询已经提交数据
	 */
	public List<sub_data> findlist(String place_num, int page, int rows);

	/**
	 * 根据id查询提交记录
	 * 
	 * @param id
	 * @return
	 */
	public sub_data findById(int id);

	/**
	 * 根据id编辑状态
	 * 
	 * @param id
	 * @return
	 */
	public int updateById(int id);

	/**
	 * 删除工单
	 */
	public int delete(int id);

	/**
	 * 批量删除工单
	 */
	public int deleteBatch(String sqlx);

	/**
	 * 根据员工号查询员工提交的工单数据
	 * 
	 * @param job_number
	 * @return
	 */
	public List<sub_data> findByjob(String job_number);

	/**
	 * 根据员工号 当前页 每页条数查询员工提交的工单数据
	 * 
	 * @param job_number
	 * @return
	 */
	public List<sub_data> findByjob(String job_number, int page, int rows);

	/**
	 * 组合查询
	 * 
	 * @param advanceFilter
	 * @param job_number
	 * @return
	 */
	public List<sub_data> combinatoriaQuery(String advanceFilter,
			String job_number);

	/**
	 * 组合查询
	 * 
	 * @param advanceFilter
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<sub_data> combinatoriaQuery(String advanceFilter,
			String job_number, int page, int rows);

	/**
	 * 导出结果查询
	 * 
	 * @param job_number
	 * @return
	 */
	public List<Export> exportFind(String job_number);

	/**
	 * 自定义组件模糊查询，测试
	 */
	public List<sub_data> queryList(String zd, String tj, String zhi,
			String place_num, int page, int rows);

	/**
	 * 提交记录组合查询
	 * 
	 * @param sql
	 * @param place_num
	 * @return
	 */
	public java.util.List<sub_data> combGetsubList(String sql, String place_num);

	/**
	 * 提交记录组合查询
	 * 
	 * @param sql
	 * @param place_num
	 * @param page
	 * @param rows
	 * @return
	 */
	public java.util.List<sub_data> combGetsubList(String sql,
			String place_num, int page, int rows);

	/**
	 * 根据订单号 查询所有的提交数
	 * 
	 * @param plan_num1
	 * @return
	 */
	public int findlistCount(String plan_num1);

	/**
	 * 根据订单号和包号以及工序名称查询 提交记录
	 * 
	 * @param place_num
	 * @param packe_num
	 * @param technology_name
	 * @return
	 */
	public sub_data findByPnPkT(String place_num, String packe_num,
			String technology_name);
	public List<sub_data> findListByPnPkT(String place_num, String packe_num,
			String technology_name);
	/**
	 * 根据包号查询提交记录
	 * 
	 * @param packe_num
	 * @return
	 */
	public List<sub_data> findByPk(String packe_num);

	/**
	 * 带条件查询出导出结果
	 * 
	 * @param sql
	 * @param job_number
	 * @return
	 */
	public List<Export> exportFind(String sql, String job_number);

	/**
	 * 无条件的查询所有的提交记录统计成员工计件报表
	 * 
	 * @return
	 */
	public java.util.List<ProductionPiecework> findAll();

	/**
	 * 有条件的查询所有的提交记录统计成员工计件报表
	 * 
	 * @param sql
	 * @return
	 */
	public java.util.List<ProductionPiecework> findAll(String sql);
	/**
	 * 有条件的查询所有的提交记录统计成员工计件报表 优化
	 * 
	 * @param sql
	 * @return
	 */
	public List<ProductionPiecework> findAllN(String s1,String s2,String s3);

	public int findComdPageTotal(String sql, String place_num);

	public int findPageTotal(String place_num);

	public int findCombPageTotalByJob(String sql, String job_number);

	public int findPageTotalByJob(String job_number);

	/**
	 * 结算工资修改提交记录状态
	 * 
	 * @param sql
	 * @return
	 */
	public int batchWages(String sql);
	
	public List<User> findByPlace(String place);
	
	public int findComdLossPageTotal(String sqlx, String place_num);
	public List<Loss> combGetLossList(String sqlx, String place_num,
				int page, int rows);
	
	public int findLossPageTotal(String place_num);
	public List<Loss> findLosslist(String place_num, int page, int rows);
}
