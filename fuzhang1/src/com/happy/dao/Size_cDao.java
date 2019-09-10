package com.happy.dao;

import java.util.List;

import com.happy.model.size_color;

public interface Size_cDao {
	/**
	 * 根据款式id查询款式的尺寸颜色
	 * 
	 * @param id
	 * @return
	 */
	List<size_color> getStyleC(int id);

	/**
	 * 根据款式id当前页和每页条数查询款式的尺寸颜色
	 * 
	 * @param id
	 * @return
	 */
	List<size_color> getStyleC(int id, int page, int rows);

	/**
	 * 添加款式详情
	 * 
	 * @param i
	 * @param string2
	 * @param string3
	 * @return
	 */
	int addStyleC(int i, String string2, String string3);

	/**
	 * 根据款式详情id查询款式详情记录
	 * 
	 * @param id
	 * @return
	 */
	size_color findByCid(int id);

	/**
	 * 编辑款式详情
	 * 
	 * @param sc
	 * @return
	 */
	int updateStyleC(size_color sc);

	/**
	 * 删除款式详情
	 * 
	 * @param parseInt
	 * @return
	 */
	int deleteStyleC(int parseInt);

	/**
	 * 批量删除款式详情
	 */
	int batchdeleteStyleC(String sqlx);

	/**
	 * 根据订单号查找共有分包颜色和尺寸分类状况
	 * 
	 * @param place_num
	 * @return
	 */
	List<size_color> findListByPlaceNum(String place_num);
}
