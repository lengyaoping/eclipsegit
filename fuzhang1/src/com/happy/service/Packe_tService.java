package com.happy.service;

import java.util.List;

import com.happy.model.Packe_technology;
import com.happy.model.PlaceSpeed;

/**
 * 包邦定工序表 业务层
 * 
 * @author zjs
 * 
 */
public interface Packe_tService {
	/**
	 * 批量添加 包绑定工序表 的数据
	 * 
	 * @param sql1
	 * @return
	 */
	int batchAddTechnology(String sql1);

	/**
	 * 添加 包绑定工序
	 * 
	 * @param packe_technology
	 */
	void addTechnology(Packe_technology packe_technology);

	/**
	 * 根据包号查询这个包所绑定的所有工序
	 * 
	 * @param packe_num
	 * @return
	 */
	List<Packe_technology> findByPackT(String packe_num);

	/**
	 * 已完成工序列表集合
	 * 
	 * @param packe_num
	 * @return
	 */
	List<Packe_technology> findByPackTcom(String packe_num);

	/**
	 * 未完成工序列表集合
	 * 
	 * @param packe_num
	 * @return
	 */
	List<Packe_technology> findByPackTyet(String packe_num);

	/**
	 * 改变包绑定工序表的 工序状态为2已完成
	 * 
	 * @param id
	 * @param state
	 * @return
	 */
	int updatePt(int id, int state);
	int updatePts(String id, int state);
	/**
	 * id查询包邦定工序记录
	 * 
	 * @param id
	 * @return
	 */
	List<Packe_technology> findbByPt(String id);

	/**
	 * 根据包号和工序名称修改 包绑定工序表 的工序状态
	 * 
	 * @param packe_num
	 * @param t_name
	 * @return
	 */
	int updateTovoidOne(String packe_num, String t_name);

	/**
	 * 根据包号更改包记录的工序完成情况
	 * 
	 * @param packe_num
	 * @return
	 */
	int updateTovoidTwo(String packe_num);

	/**
	 * 批量修改 包绑定工序表的工序状态
	 * 
	 * @param sql3
	 * @return
	 */
	int updateStartBatch(String sql3);

	/**
	 * 根据订单号 查询 包邦定工序表 的数据
	 * 
	 * @param plan_num1
	 * @return
	 */
	List<Packe_technology> findByPnum(String plan_num1);

	/**
	 * 查询这个订单号的所有包绑定工序 数据量
	 * 
	 * @param plan_num1
	 * @return
	 */
	int findByPnumCount(String plan_num1);

	/**
	 * 根据订单号 和包号获取 包绑定工序表的 id
	 * 
	 * @param packe_num
	 * @param t_name
	 * @return
	 */
	Packe_technology getId(String packe_num, String t_name);
	List<Packe_technology> getIds(String packe_num, String t_name);
	/**
	 * 获取订单进度数据报表 分页
	 * 
	 * @param place
	 * @param page
	 * @param rows
	 * @return
	 */
	List<PlaceSpeed> queryProgress(String place, int page, int rows);

	/**
	 * 获取订单进度数据报表
	 * 
	 * @param place
	 * @return
	 */
	List<PlaceSpeed> queryProgressAll(String place);

	int findPageTotal(String place);

	/**
	 * 查询指定生产号指定工序有多少包完成或未完成
	 * 
	 * @param pNum
	 * @param technology_name
	 * @param state
	 * @return
	 */
	List<Packe_technology> findListByPNumAndTechnologyName(String pNum,
			String technology_name, int state);
	/**
	 * 查询该订单的所有包
	 * @param plan_num1
	 * @return
	 */
	List<Packe_technology> findListByPnum(String plan_num1);
	public List<Packe_technology> getPackeNums(String plan_num,String tname,int state);
}
