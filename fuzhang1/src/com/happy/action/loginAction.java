package com.happy.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.happy.model.Admin;
import com.happy.service.AdminService;
import com.happy.util.ResUtil;

/**
 * 登录action
 * 
 * @author lzp
 * 
 */
@Controller
public class loginAction extends ActionSupport implements ServletRequestAware {

	// private final Gson gson = new
	// GsonBuilder().setDateFormat("yyyyMMddHHmmss").create();

	private HttpServletRequest request;
	private HttpSession session;
	private String account;
	private String password;
	private Admin admin;
	private String id;
	private String admin_name;
	@Resource
	private AdminService adminService;

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public AdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAdmin_name() {
		return admin_name;
	}

	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}

	// 登录的方法
	public String login() {
		// 管理员登录
		Admin admin = adminService.getAdmin(account, password);
		if (admin != null) {
			request.getSession().setAttribute("admin", admin);
			request.getSession().setAttribute("fruit", 2);
			request.getSession().removeAttribute("erro");
			request.getSession().setAttribute("isLogin", "123");
			return "index";
		}
		ServletActionContext.getRequest().getSession().setAttribute("erro", 1);
		return "login";
	}

	// 得到用户名密码`
	public String getUser() throws Exception {
		Admin user = (Admin) ServletActionContext.getRequest().getSession()
				.getAttribute("admin");
		JSONObject resultJson = new JSONObject();
		resultJson.put("name", user.getAdmin_name());
		resultJson.put("pass", user.getAdmin_password());
		resultJson.put("id", user.getAdmin_id());
		resultJson.put("account", user.getAdmin_account());
		ResUtil.write(resultJson, ServletActionContext.getResponse());
		return null;
	}

	// 修改密码
	public String updatePass() throws Exception {
		JSONObject resultJson = new JSONObject();
		int num = adminService.updateAdmin(id, password);
		if (num > 0) {
			resultJson.put("success", "修改成功");
		}
		ResUtil.write(resultJson, ServletActionContext.getResponse());
		return null;
	}

	// 修改密码
	public String updateUser() throws Exception {
		JSONObject resultJson = new JSONObject();
		int num = adminService.updateAdmin(id, password);
		if (num > 0) {
			resultJson.put("success", "修改成功");
		}
		ResUtil.write(resultJson, ServletActionContext.getResponse());
		return null;
	}

	// 退出登录
	public String loginout() {
		ServletActionContext.getRequest().getSession().invalidate();
		return "login";
	}

	public String updateAdminInfo() throws Exception {
		JSONObject resultJson = new JSONObject();
		Admin admin = new Admin();
		admin.setAdmin_account(account);
		admin.setAdmin_id(Integer.parseInt(id));
		admin.setAdmin_name(admin_name);
		admin.setAdmin_password(password);
		int num = adminService.updateAdminInfo(admin);
		if (num > 0) {
			resultJson.put("success", "修改成功,需要重新登录！");
		}
		ResUtil.write(resultJson, ServletActionContext.getResponse());
		return null;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

}
