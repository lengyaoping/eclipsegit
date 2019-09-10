package com.happy.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;




import com.happy.model.Admin;
import com.happy.model.weixin.User;
import com.happy.service.AdminService;
import com.happy.util.ChineseInital;
import com.happy.util.CombinatorialQuery;
import com.happy.util.ResponseUtil;
import com.opensymphony.xwork2.ActionSupport;
@Controller
public class AdminAction extends ActionSupport implements ServletRequestAware {
    private HttpServletRequest request;
    
    @Resource
    private AdminService adminService;
    
    private String sort;// 排序依据字段名
	private String order;// 排序方式
	private String advanceFilter;// 组合查询条件
	private String filterRules;// 过滤组件条件
	private int rows;// 每页显示的行数rows
	private int page; // 当前页
    
    private String admin_name;
    private String password;
    private  int admin_level;
    private String admin_account;
    private int admin_fake;
    private int id;
    
    
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getAdmin_name() {
		return admin_name;
	}



	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public int getAdmin_level() {
		return admin_level;
	}



	public void setAdmin_level(int admin_level) {
		this.admin_level = admin_level;
	}



	public String getAdmin_account() {
		return admin_account;
	}



	public void setAdmin_account(String admin_account) {
		this.admin_account = admin_account;
	}



	public int getAdmin_fake() {
		return admin_fake;
	}



	public void setAdmin_fake(int admin_fake) {
		this.admin_fake = admin_fake;
	}



	public HttpServletRequest getRequest() {
		return request;
	}



	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}



	public AdminService getAdminService() {
		return adminService;
	}



	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}



	public String getSort() {
		return sort;
	}



	public void setSort(String sort) {
		this.sort = sort;
	}



	public String getOrder() {
		return order;
	}



	public void setOrder(String order) {
		this.order = order;
	}



	public String getAdvanceFilter() {
		return advanceFilter;
	}



	public void setAdvanceFilter(String advanceFilter) {
		this.advanceFilter = advanceFilter;
	}



	public String getFilterRules() {
		return filterRules;
	}



	public void setFilterRules(String filterRules) {
		this.filterRules = filterRules;
	}



	public int getRows() {
		return rows;
	}



	public void setRows(int rows) {
		this.rows = rows;
	}



	public int getPage() {
		return page;
	}



	public void setPage(int page) {
		this.page = page;
	}



	public void setServletRequest(HttpServletRequest request) {
      this.request=request;

	}
	
	//获取管理员列表
	public String getAdmin(){
		JSONObject resultJson = new JSONObject();
		// List<User> listAll = null;
		List<Admin> listPage = null;
		int total = 0;
		if (sort != null || advanceFilter != null
				&& !advanceFilter.equals("[]") || filterRules != null
				&& !filterRules.equals("[]")) {
			String sql = CombinatorialQuery.queryOne(advanceFilter, sort,
					order, filterRules);
			// System.out.println(sql);
			// listAll = userService.combGetUserList(sql);
			total = adminService.findPage(sql);
			listPage = adminService.combGetAdminList(sql, page, rows);
		} else {
			// listAll = userService.getlist();
			total =adminService.findTotal();
			listPage = adminService.getlist(page,rows);
		}
		if (total != 0) {
			resultJson.put("rows", listPage);
			resultJson.put("total", total);// 总记录数
			int totalPage = total % rows == 0 ? (total / rows)
					: (total / rows) + 1;// 总页数
			resultJson.put("totalPage", totalPage);
			resultJson.put("currentPage", page);// 当前页
			resultJson.put("numPerPage", rows);// 每页数
			resultJson.put("nextPage", totalPage - page == 0 ? page : page + 1);// 下一页
			resultJson.put("previousPage", page - 0 == 1 ? page : page - 1);// 上一页
			resultJson.put("hasPreviousPage", true);// 有上一页
			resultJson.put("hasNextPage", true);// 有下一页
			resultJson.put("firstPage", true);// 首页
			resultJson.put("lastPage", true);// 尾页
		} else {
			resultJson.put("rows", "");
			resultJson.put("total", 0);
		}
		ResponseUtil.writeJson(ServletActionContext.getResponse(),
				resultJson.toString());
		return null;
	
		
	}
	
	public String add() {
		// 获取前端传入的json
		// 前端返回值
		JSONObject resultJson = new JSONObject();
		// 将前端传入值序列对象
		int num = 0;
		int m = 0;
		Admin a = new Admin();
		a.setAdmin_name(admin_name);
		a.setAdmin_password(password);
		a.setAdmin_account(admin_account);
		a.setLevel(admin_level);
		
		
		
			num = adminService.addAdmin(a);
		
			if (num >= 0) {// &&m>0
				resultJson.put("statusCode", "200");
				resultJson.put("title", "操作提示");
				resultJson.put("message", "操作成功！");
			} else {
				resultJson.put("statusCode", "500");
				resultJson.put("title", "操作提示");
				resultJson.put("message", "操作失败！");
			}
		
		ResponseUtil.writeJson(ServletActionContext.getResponse(),
				resultJson.toString());
		return null;
	}

	
	
	
	

}
