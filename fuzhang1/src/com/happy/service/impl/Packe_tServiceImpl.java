package com.happy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.happy.dao.Packe_tDao;
import com.happy.model.Packe_technology;
import com.happy.model.PlaceSpeed;
import com.happy.service.Packe_tService;

@Service("packe_tService")
public class Packe_tServiceImpl implements Packe_tService {
	@Resource
	private Packe_tDao packe_tDao;

	public Packe_tDao getPacke_tDao() {
		return packe_tDao;
	}

	public void setPacke_tDao(Packe_tDao packe_tDao) {
		this.packe_tDao = packe_tDao;
	}

	public int batchAddTechnology(String sql1) {
		return packe_tDao.batchAddTechnology(sql1);
	}

	public void addTechnology(Packe_technology packe_technology) {
		packe_tDao.addTechnology(packe_technology);
	}

	public List<Packe_technology> findByPackT(String packe_num) {
		return packe_tDao.findByPackT(packe_num);
	}

	public List<Packe_technology> findByPackTcom(String packe_num) {
		return packe_tDao.findByPackTcom(packe_num);
	}

	public List<Packe_technology> findByPackTyet(String packe_num) {
		return packe_tDao.findByPackTyet(packe_num);
	}

	public int updatePt(int id, int state) {
		return packe_tDao.updatePt(id, state);
	}
	public int updatePts(String id, int state) {
		return packe_tDao.updatePts(id, state);
	}
	public List<Packe_technology> findbByPt(String id) {
		return packe_tDao.findbByPt(id);
	}

	public int updateTovoidOne(String packe_num, String t_name) {
		return packe_tDao.updateTovoidOne(packe_num, t_name);
	}

	public int updateTovoidTwo(String packe_num) {
		return packe_tDao.updateTovoidTwo(packe_num);
	}

	public int updateStartBatch(String sql3) {
		return packe_tDao.batchupdateStart(sql3);
	}

	public List<Packe_technology> findByPnum(String plan_num1) {
		return packe_tDao.findByPnum(plan_num1);
	}

	public int findByPnumCount(String plan_num1) {
		return packe_tDao.findByPnumCount(plan_num1);
	}

	public Packe_technology getId(String packe_num, String t_name) {
		return packe_tDao.getId(packe_num, t_name);
	}
	public List<Packe_technology> getIds(String packe_num, String t_name) {
		return packe_tDao.getIds(packe_num, t_name);
	}
	
	public List<PlaceSpeed> queryProgress(String place, int page, int rows) {
		return packe_tDao.queryProgress(place, page, rows);
	}

	public List<PlaceSpeed> queryProgressAll(String place) {
		return packe_tDao.queryProgressAll(place);
	}

	public int findPageTotal(String place) {
		return packe_tDao.findPageTotal(place);
	}

	public List<Packe_technology> findListByPNumAndTechnologyName(String pNum,
			String technology_name, int state) {
		return packe_tDao.findListByPNumAndTechnologyName(pNum,
				technology_name, state);
	}
	
	public List<Packe_technology> findListByPnum(String plan_num1){
		return packe_tDao.findListByPnum(plan_num1);
	}

	public List<Packe_technology> getPackeNums(String plan_num, String tname,
			int state) {
		// TODO Auto-generated method stub
		return packe_tDao.getPackeNums(plan_num, tname, state);
	}

}
