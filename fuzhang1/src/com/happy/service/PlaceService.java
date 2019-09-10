package com.happy.service;

import java.math.BigDecimal;
import java.util.List;

import com.happy.model.place;
import com.happy.model.place_technology;
import com.happy.model.process_dimensio;

public interface PlaceService {
	/**
	 * 根据当前页和每页显示行数实现分页查询订单
	 */
	public List<place> getPlace(int page, int rows);

	/**
	 * 查询所有订单
	 */
	public List<place> getPlace();

	/**
	 * 添加订单
	 */
	public int add(place p);

	public List<place> findByCustomer(String st);

	/**
	 * 根据订单ID查询订单
	 * 
	 */
	public List<place> findById(int id);

	/**
	 * 根据id修改订单
	 */
	public int updateInfo(place p);

	/**
	 * 根据id删除订单
	 */
	public int delete(int id);

	/**
	 * 根据订单号查询订单记录
	 */
	public place findByNumber(String planNum);

	/**
	 * 查询所有待生产订单
	 */
	public List<place> findDai();

	/**
	 * 根据当前页和每页条数查询所有待生产订单
	 */
	public List<place> findDai(int page, int rows);

	/**
	 * 查询总条数
	 */
	public int count();

	/**
	 * 根据订单号更新完成率
	 */
	public int updatePlace_Speed(String plan_num, String place_speed);

	/**
	 * 根据订单号更新已添加到包的数量
	 */
	public int updateResidue(String plan_num, int residue,int lossnum);

	/**
	 * 批量关联删除5张表
	 * 
	 * @param sql1
	 * @return
	 */
	public int deleteBatch(String sql1);

	/**
	 * 组合查询
	 * 
	 * @param sql
	 * @return
	 */
	public List<com.happy.model.place> combGetPlaceList(String sql);

	/**
	 * 组合查询
	 * 
	 * @param sql
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<com.happy.model.place> combGetPlaceList(String sql, int page,
			int rows);

	/**
	 * 查询出所有订单的订单号
	 * 
	 * @return
	 */
	public List<place> getAllpnum();

	/**
	 * 查询表中的总记录数
	 * 
	 * @return
	 */
	public int findPageTotal();

	/**
	 * 组合查询表中的总记录数
	 * 
	 * @param sql
	 * @return
	 */
	public int findComdPageTotal(String sql);
	
	public List<process_dimensio> findSubProcessD(String place,int page,int row);
	
	public List<process_dimensio> findByTime(String place,int page,int row,String time);
	/**
	 * 模糊查询
	 * @param q
	 * @return
	 */
	public List<place> findByPlaceLike(String q);
	
	public int updateState(String sql);
}
