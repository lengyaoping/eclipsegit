package com.happy.model;

/**
 * 订单进度实体类
 * 
 * @author zjs
 * 
 */
public class PlaceSpeed {
	private String p_num;// 订单号
	private String t_name;// 工序名称
	private int completed;// 已完成数量
	private int unfinished;// 未完成数量
	private int piece_num;//总件数
	private int cp_piece_num;//已完成件数
	public String getP_num() {
		return p_num;
	}

	public void setP_num(String p_num) {
		this.p_num = p_num;
	}

	public String getT_name() {
		return t_name;
	}

	public void setT_name(String t_name) {
		this.t_name = t_name;
	}

	public int getCompleted() {
		return completed;
	}

	public void setCompleted(int completed) {
		this.completed = completed;
	}

	public int getUnfinished() {
		return unfinished;
	}

	public void setUnfinished(int unfinished) {
		this.unfinished = unfinished;
	}

	public int getPiece_num() {
		return piece_num;
	}

	public void setPiece_num(int piece_num) {
		this.piece_num = piece_num;
	}

	public int getCp_piece_num() {
		return cp_piece_num;
	}

	public void setCp_piece_num(int cp_piece_num) {
		this.cp_piece_num = cp_piece_num;
	}
	
	
}
