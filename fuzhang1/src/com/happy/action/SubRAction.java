package com.happy.action;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.sql.ResultSet;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.aspectj.weaver.ast.Var;
import org.springframework.stereotype.Controller;

import com.alibaba.druid.sql.visitor.functions.If;
import com.happy.model.Admin;
import com.happy.model.Loss;
import com.happy.model.Packe_technology;
import com.happy.model.ProductionPiecework;
import com.happy.model.Record;
import com.happy.model.place_technology;
import com.happy.model.process_dimensio;
import com.happy.model.size_color;
import com.happy.model.sub_data;
import com.happy.model.weixin.User;
import com.happy.service.CodeService;
import com.happy.service.Packe_tService;
import com.happy.service.Place_tService;
import com.happy.service.RecordService;
import com.happy.service.sub_dataService;
import com.happy.util.CombinatorialQuery;
import com.happy.util.DBtoExcel;
import com.happy.util.ResUtil;
import com.happy.util.ResponseUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 提交记录控制层
 * 
 * @author zjs
 * 
 */
@Controller
public class SubRAction extends ActionSupport implements ServletRequestAware {
	@Resource
	private sub_dataService subService;
	@Resource
	private CodeService codeService;
	@Resource
	private Packe_tService packe_tService;
	@Resource
	private RecordService recordService;
	@Resource
	private Place_tService place_tService;
	private HttpServletRequest request;
	private String job_number;
	private String data;
	private String place_num;
	private String id;
	private String jsonData;
	private String packe_num;
	private String t_name;
	private String user_name;
	private int biao;
	private String style;
	private String time1;
	private String time2;
	private String tname;
	private int state;
	private String user_group;
	
	public String getUser_group() {
		return user_group;
	}

	public void setUser_group(String user_group) {
		this.user_group = user_group;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getTime1() {
		return time1;
	}

	public void setTime1(String time1) {
		this.time1 = time1;
	}

	public String getTime2() {
		return time2;
	}

	public void setTime2(String time2) {
		this.time2 = time2;
	}

	public Place_tService getPlace_tService() {
		return place_tService;
	}

	public void setPlace_tService(Place_tService place_tService) {
		this.place_tService = place_tService;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public int getBiao() {
		return biao;
	}

	public void setBiao(int biao) {
		this.biao = biao;
	}

	private String sort;// 排序依据字段名
	private String order;// 排序方式
	private int rows;// 每页显示的行数rows
	private int page; // 当前页
	private String advanceFilter;// 组合查询条件
	private String filterRules;// 过滤组件条件

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

	public String getPacke_num() {
		return packe_num;
	}

	public void setPacke_num(String packe_num) {
		this.packe_num = packe_num;
	}

	public String getT_name() {
		return t_name;
	}

	public void setT_name(String t_name) {
		this.t_name = t_name;
	}

	public Packe_tService getPacke_tService() {
		return packe_tService;
	}

	public void setPacke_tService(Packe_tService packe_tService) {
		this.packe_tService = packe_tService;
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

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPlace_num() {
		return place_num;
	}

	public void setPlace_num(String place_num) {
		this.place_num = place_num;
	}

	public CodeService getCodeService() {
		return codeService;
	}

	public void setCodeService(CodeService codeService) {
		this.codeService = codeService;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public sub_dataService getSubService() {
		return subService;
	}

	public void setSubService(sub_dataService subService) {
		this.subService = subService;
	}

	public String getJob_number() {
		return job_number;
	}

	public void setJob_number(String job_number) {
		this.job_number = job_number;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public RecordService getRecordService() {
		return recordService;
	}

	public void setRecordService(RecordService recordService) {
		this.recordService = recordService;
	}

	// 查询订单工票
	public String List() {
		// 返回前端数据
		JSONObject resultJson = new JSONObject();
		// List<sub_data> listAll = null;
		List<sub_data> listPage = null;
		int total = 0;
		if (sort != null || advanceFilter != null
				&& !advanceFilter.equals("[]") || filterRules != null
				&& !filterRules.equals("[]")) {
			String sql = CombinatorialQuery.queryOne(advanceFilter, sort,
					order, filterRules);
			// System.out.println(sql);
			// listAll = subService.combGetsubList(sql, place_num);
			total = subService.findComdPageTotal(sql, place_num);// 获取条件查询出的总记录数
			listPage = subService.combGetsubList(sql, place_num, page, rows);
			request.getSession().setAttribute("sqlx", sql);

		} else {
			// listAll = subService.findlist(place_num);
			total = subService.findPageTotal(place_num);// 获取条件查询出的总记录数
			listPage = subService.findlist(place_num, page, rows);
			request.getSession().removeAttribute("sqlx");
		}
		if (total == 0) {
			resultJson.put("rows", "");
			resultJson.put("total", 0);
		} else {
			resultJson.put("rows", listPage);
			// total = listAll.size();
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
		}
		ResponseUtil.writeJson(ServletActionContext.getResponse(),
				resultJson.toString());
		return null;
	}
	
	public String getList(){
		JSONObject resultJson = new JSONObject();
		List<sub_data> sdList = null;
		sdList = subService.findlist(place_num,packe_num);
		resultJson.put("slist", sdList);
		ResponseUtil.writeJson(ServletActionContext.getResponse(),
				resultJson.toString());
		return null;
	}
	
	public String getPackListByPlace(){
		JSONObject resultJson = new JSONObject();
		List<Packe_technology> packList = null;
		packList= packe_tService.findListByPnum(place_num);
		resultJson.put("packList", packList);
		ResponseUtil.writeJson(ServletActionContext.getResponse(),
				resultJson.toString());
		return null;
	}
	
	// 查询已提交人数
	public String ListPeople() throws Exception {
		// 返回前端数据
		JSONObject resultJson = new JSONObject();
		int num = subService.findlistPeople(place_num);// 得到提交不重复的人
		resultJson.put("findlistPeople", num);
		ResUtil.write(resultJson, ServletActionContext.getResponse());
		return null;
	}

	// 查询已完成的工序
	public String ListFinish() throws Exception {
		// 返回前端数据
		JSONObject resultJson = new JSONObject();
		int nu = subService.findfinish(place_num);
		resultJson.put("findlistFinish", nu);
		ResUtil.write(resultJson, ServletActionContext.getResponse());
		return null;
	}

	// 查询未完成的工序
	public String ListUnfinish() throws Exception {
		// 返回前端数据
		JSONObject resultJson = new JSONObject();
		int nn = codeService.findPackNum(place_num);// 总包数
		int uu = codeService.findGongXu(place_num);// 总工序
		int nu = subService.findfinish(place_num);// 已完成
		int sum = nn * uu - nu;
		resultJson.put("unfinsh", sum);
		ResUtil.write(resultJson, ServletActionContext.getResponse());
		return null;
	}
	
	// 查询工票接口
	public String getInfo() throws Exception {	
		// 返回前端数据
		JSONObject resultJson = new JSONObject();
		// 根据时间和工号查询当天所有的工单
		List<sub_data> list = null;
		if(biao==1){
			list = subService.getListByJnumAndTime(job_number, time1,time2);//统计
		}else {
			list = subService.getListByJnumAndTimeT(job_number, time1,time2);//所有单张
		}
		if (list != null) {
			int bao = list.get(0).getNumber();
			if(biao == 1){
				Map<String, size_color> map = new HashMap<String, size_color>();
				size_color size_color = new size_color();
				int pnumber = bao;
				int gnum = Integer.valueOf(list.get(0).getPacke_num());
				for (int i = 1; i < list.size(); i++) {
					bao += list.get(i).getNumber();
					if(list.get(i).getPlace_num().equals(list.get(i-1).getPlace_num())){//订单没变
						pnumber+=list.get(i).getNumber();
						gnum+=Integer.valueOf(list.get(i).getPacke_num());
					}else{
						size_color.setId(pnumber);//保存订单数量
						size_color.setSize(String.valueOf(gnum));//保存订单提交包数
						size_color.setColor(list.get(i-1).getUser_name());//保存款号
						map.put(list.get(i-1).getPlace_num(), size_color);
						size_color=new size_color();
						gnum=Integer.valueOf(list.get(i).getPacke_num());
						pnumber=list.get(i).getNumber();
					}
				}
				size_color.setId(pnumber);//保存订单数量
				size_color.setSize(String.valueOf(gnum));//保存订单提交包数
				size_color.setColor(list.get(list.size()-1).getUser_name());//保存款号
				map.put(list.get(list.size()-1).getPlace_num(),size_color);
				resultJson.put("map", map);
			}else{
				for (int i = 1; i < list.size(); i++) {
					bao += list.get(i).getNumber();
				}
			}
			resultJson.put("data", list);
			resultJson.put("bao", bao);
		} else {
			resultJson.put("data", null);
		}
		ResUtil.write(resultJson, ServletActionContext.getResponse());
		return null;
	}

	// 工单回收 //手机端返工
	public String recover() throws Exception {
		// 前端返回值
		JSONObject resultJson = new JSONObject();
		int num = 0;
		int a = 0;
		int b = 0;
		int m = 0;
		String sql = "delete from sub_data where id in (";// 批量删除提交记录
		String sql2 = "update process_dimensio set completed=completed-1,surplus=surplus+1 where packe_num in (";// 批量修改
																													// 包表
																													// 的工序完成情况
		String sql3 = "update packe_technology set state=1 where ";// 批量修改
																	// 包绑定工序表
																	// 的工序状态
		if (biao == 1) {
			sub_data sd = subService.findById(Integer.parseInt(id));
			if (sd != null) {
				sql += "'" + Integer.parseInt(id) + "')";
				sql2 += "'" + sd.getPacke_num() + "')";
				sql3 += "(packe_num='" + sd.getPacke_num()
						+ "' and technology_name='" + sd.getT_name() + "')";

				a = packe_tService.updateStartBatch(sql3);// 批量修改 包绑定工序表的工序状态
				b = codeService.batchAddPack(sql2);// 批量修改包表的完成工序数据
				num = subService.deleteBatch(sql);// 批量删除提交数据
				// 添加操作记录
				Record red = new Record();
				SimpleDateFormat dd = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				red.setDate(dd.format(new Date()));
				red.setOpid(sd.getPlace_num());
				red.setOperation("返工");
				red.setUfrom("手机端");
				red.setFake(0);
				User user = (User) request.getSession().getAttribute("user");
				red.setPeople(user.getUser_name());
				red.setRecord1("管理员" + user.getUser_name() + "在"
						+ dd.format(new Date()) + "手机返工中回收了订单号为"
						+ sd.getPlace_num() + "中" + sd.getUser_name()
						+ "提交的包号为" + sd.getPacke_num() + "做的工序为"
						+ sd.getT_name() + "的工单");
				m = recordService.add(red);
				if (num > 0 && a > 0 && b > 0) {// &&m>0
					resultJson.put("fangong", "success");
				}
			}
			ResUtil.write(resultJson, ServletActionContext.getResponse());
			return null;
		}
		String ii = id.replaceAll("'", "");// 去除单引号
		String pn = packe_num.replaceAll("'", "");// 去除单引号
		String tn = t_name.replaceAll("'", "");// 去除单引号
		String pln = place_num.replaceAll("'", "");// 去除单引号
		String un = user_name.replace("'", "");// 去除单引号
		if (ii.indexOf(",") != -1) {// 判断是否包含，
			String[] strs = ii.split(",");
			String[] strs2 = pn.split(",");
			String[] strs3 = tn.split(",");
			String[] strs4 = pln.split(",");
			for (int i = 0; i < strs.length; i++) {
				sql += "'" + Integer.parseInt(strs[i]) + "'";
				sql2 += "'" + strs2[i] + "'";
				sql3 += "(packe_num='" + strs2[i] + "' and technology_name='"
						+ strs3[i] + "')";
				if (i != strs.length - 1) {
					sql += ",";
					sql2 += ",";
					sql3 += " or ";
				} else {
					sql += ")";
					sql2 += ")";
				}
				// 查询该工单
				// sub_data sd= subService.findById(Integer.parseInt(strs[i]));
				// 根据包号和工序名称修改包绑定工序表的工序状态
				// a =
				// packe_tService.updateTovoidOne(sd.getPacke_num(),sd.getT_name());
				// if(a<0){
				// break;
				// }
				// 根据包号更改包记录的工序完成情况
				/* b = codeService.updateTovoidTwo(sd.getPacke_num()); */
				// b = packe_tService.updateTovoidTwo(sd.getPacke_num());
				// if(b<0){
				// break;
				// }
			}
			a = packe_tService.updateStartBatch(sql3);// 批量修改 包绑定工序表的工序状态
			b = codeService.batchAddPack(sql2);// 批量修改包表的完成工序数据
			num = subService.deleteBatch(sql);// 批量删除提交数据
			// 添加操作记录
			Record red = new Record();
			SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			red.setDate(dd.format(new Date()));
			red.setOpid(strs4[0]);
			red.setFake(0);
			Admin admin = (Admin) request.getSession().getAttribute("admin");
			red.setPeople(admin.getAdmin_name());
			if(biao==2){
				String[] unamess = un.split(",");
				red.setOperation("工单回收");
				red.setUfrom("员工列表—工单提交详情");
				red.setRecord1("管理员" + admin.getAdmin_name() + "在"
						+ dd.format(new Date()) + "员工列表—工单提交详情中回收了订单号为" + strs4[0]
						+ "中" + unamess[0] + "提交的包号为" + pn + "做的工序为" + tn + "的工单");
			}else{
				red.setOperation("工单回收");
				red.setUfrom("已提交数据—提交记录详情");
				red.setRecord1("管理员" + admin.getAdmin_name() + "在"
						+ dd.format(new Date()) + "已提交数据—提交记录详情中回收了订单号为" + strs4[0]
						+ "中" + un + "提交的包号为" + pn + "做的工序为" + tn + "的工单");
			}
			m = recordService.add(red);
		} else {
			sql += "'" + Integer.parseInt(ii) + "')";
			sql2 += "'" + pn + "')";
			sql3 += "(packe_num='" + pn + "' and technology_name='" + tn + "')";

			a = packe_tService.updateStartBatch(sql3);// 批量修改 包绑定工序表的工序状态
			b = codeService.batchAddPack(sql2);// 批量修改包表的完成工序数据
			num = subService.deleteBatch(sql);// 批量删除提交数据

			// 添加操作记录
			Record red = new Record();
			SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			red.setDate(dd.format(new Date()));
			red.setOpid(pln);
			red.setOperation("工单回收");
			red.setUfrom("已提交数据—提交记录详情");
			red.setFake(0);
			Admin admin = (Admin) request.getSession().getAttribute("admin");
			red.setPeople(admin.getAdmin_name());

			red.setRecord1("管理员" + admin.getAdmin_name() + "在"
					+ dd.format(new Date()) + "已提交数据—提交记录详情中回收了订单号为" + pln
					+ "中" + un + "提交的包号为" + pn + "做的工序为" + tn + "的工单");
			m = recordService.add(red);
		}

		if (num > 0 && a > 0 && b > 0) { // && m > 0
			resultJson.put("statusCode", "200");
			resultJson.put("title", "操作提示");
			resultJson.put("message", "操作成功！");
		} else {
			resultJson.put("statusCode", "500");
			resultJson.put("title", "操作提示");
			resultJson.put("message", "操作失败！数据异常！请检查是否误删数据！");
		}
		ResponseUtil.writeJson(ServletActionContext.getResponse(),
				resultJson.toString());
		return null;
	}

	// 员工作废
	public String tovoid() throws Exception {
		// 返回前端数据
		JSONObject resultJson = new JSONObject();
		// 根据id查询提交记录
		// sub_data sd= subService.findById(id);
		int num = subService.updateById(Integer.parseInt(id));
		if (num != 0) {
			resultJson.put("ok", "ok");
		} else {
			resultJson.put("ok", null);
		}

		ResUtil.write(resultJson, ServletActionContext.getResponse());
		return null;
	}

	// 订单提交包表打印
	public String Reporrinting() throws ClassNotFoundException, SQLException {
		// 前端返回值
		JSONObject resultJson = new JSONObject();
		// 项目路径地址
		String path = request.getSession().getServletContext()
				.getRealPath("/download/");// File.separator
		// 根据订单号 查询这个订单绑定的所有的工序
		List<place_technology> plat = place_tService.findByNum(place_num);
		if (plat != null) {
			// String sql = "SELECT user_name,";
			// Vector columnName = new Vector(); // 列名
			// columnName.add("姓名");
			// for(int i=0;i<plat.size();i++){
			// sql+=" IFNULL(sum(case t_name when '"+plat.get(i).getTechnology_name()+"' then number end),' ') as '"+plat.get(i).getTechnology_name()+"'";
			// if(!(i==plat.size()-1)){
			// sql+=",";
			// }else{
			// sql+=" ";
			// }
			// columnName.add(plat.get(i).getTechnology_name());
			// }
			// sql+=" FROM sub_data WHERE place_num='"+place_num+"' GROUP BY user_name";
			// ResultSet rs = subService.demo(sql);
			String sqlx = (String) request.getSession().getAttribute("sqlx");
			new test().Printing(plat, place_num, path, sqlx);
			// // 导出文件
			// new DBtoExcel().WriteExcel(rs, path+place_num+"Report.xls",
			// place_num+"订单提交记录报表", columnName);
			resultJson.put("downurl", place_num + "Report.xls");
		}
		ResponseUtil.writeJson(ServletActionContext.getResponse(),
				resultJson.toString());
		return null;
	}

	// 员工生产计件实现
	public String Report() {
		// 前端返回值
		JSONObject resultJson = new JSONObject();
		List<ProductionPiecework> pp = null;
		StringBuilder sb = new StringBuilder(
				"WHERE a.fake=0 and a.place_num=b.place_number and a.t_name=b.technology_name and a.place_num=c.plan_num and a.job_number=d.job_number and d.fake=0");
		if (biao == 1) {
			if (state != 2) {
				sb.append(" and a.state=" + state + "");
			} else {
				sb.append(" and (a.state=1 or a.state=3)");
			}
			if (user_name != null) {
				sb.append(" and a.user_name in ('"
						+ user_name.replaceAll(", ", "','") + "')");
			}
			if (job_number != null) {
				sb.append(" and a.job_number in ('"
						+ job_number.replaceAll(", ", "','") + "')");
			}
			if (user_group != null) {
				sb.append(" and d.password in ('"
						+ user_group.replaceAll(", ", "','") + "')");
			}
			if (place_num != null) {
				sb.append(" and a.place_num in ('"
						+ place_num.replaceAll(", ", "','") + "')");
			}
			if (style != null) {
				sb.append(" and c.style in ('" + style.replaceAll(", ", "','")
						+ "')");
			}
			if (tname != null) {
				sb.append(" and a.t_name in ('" + tname.replaceAll(", ", "','")
						+ "')");
			}
			if (time1 != null) {
				sb.append(" and a.sub_time>='" + time1 + "'");
			}
			if (time2 != null) {
				sb.append(" and a.sub_time<='" + time2 + "'");
			}
			if (data != null) {
				sb.insert(0,
						"UPDATE sub_data a,place_technology b,place c,user d SET a.state=3 ");
				int num = subService.batchWages(sb.toString());
				if (num > 0) {
					resultJson.put("result", "ok");
				}
			} else {
				sb.insert(
						0,
						"select d.user_name,a.place_num,c.style,a.t_name,b.price,SUM(a.number)number,(b.price * SUM(a.number)) as money,a.job_number from sub_data a,place_technology b,place c,user d ");
				sb.append(" GROUP BY a.job_number,d.user_name,a.place_num,a.t_name order by d.user_name,a.job_number,a.place_num,a.t_name desc");
				pp = subService.findAll(sb.toString());
			}
		} else {
			pp = subService.findAll();
		}
		if (pp != null) {
			resultJson.put("Table", pp);
		}
		ResponseUtil.writeJson(ServletActionContext.getResponse(),
				resultJson.toString());
		return null;
	}
	
	public String Report1(){
		JSONObject resultJson = new JSONObject();
		StringBuilder sb = new StringBuilder();
		if (time1 != null) {
			sb.append(" and e.sub_time>='" + time1 + "'");
		}
		if (time2 != null) {
			sb.append(" and e.sub_time<='" + time2 + "'");
		}
		if (tname != null) {
			sb.append(" and e.t_name in ('" + tname.replaceAll(", ", "','")
					+ "')");
		}
		if (user_name != null) {
			sb.append(" and e.user_name in ('"
					+ user_name.replaceAll(", ", "','") + "')");
		}
		if (job_number != null) {
			sb.append(" and e.job_number in ('"
					+ job_number.replaceAll(", ", "','") + "')");
		}
		if (place_num != null) {
			sb.append(" and e.place_num in ('"
					+ place_num.replaceAll(", ", "','") + "')");
		}
		if (state != 2) {
			sb.append(" and e.state=" + state + "");
		} else {
			sb.append(" and (e.state=1 or e.state=3)");
		}
		sb.append(" and e.fake=0");
		StringBuilder sb2 = new StringBuilder();
		if (user_group != null) {
			sb2.append(" and password in ('"
					+ user_group.replaceAll(", ", "','") + "')");
		}
		StringBuilder sb3 = new StringBuilder();
		if (style != null) {
			sb3.append(" and c.style in ('" + style.replaceAll(", ", "','")
					+ "')");
		}
		List<ProductionPiecework> pp = subService.findAllN(sb.toString(),sb2.toString(),sb3.toString());
		if (pp != null) {
			resultJson.put("Table", pp);
		}
		ResponseUtil.writeJson(ServletActionContext.getResponse(),
				resultJson.toString());
		return null;
	}
	public String batchSubmit(){
		JSONObject resultJson = new JSONObject();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
		if(time1.length()==1){
			time1= place_num+"000"+time1;
		}else if(time1.length()==2){
			time1= place_num+"00"+time1;
		}else if(time1.length()==3){
			time1= place_num+"0"+time1;
		}else{
			time1= place_num+time1;
		}
		if(time2.length()==1){
			time2= place_num+"000"+time2;
		}else if(time2.length()==2){
			time2= place_num+"00"+time2;
		}else if(time2.length()==3){
			time2= place_num+"0"+time2;
		}else{
			time2= place_num+time2;
		}
		String sql ="insert into sub_data (place_num,packe_num,t_name,job_number,user_name,sub_time,number,price,money) values";
		String sqlx = "select a.id,a.packe_num,b.p_number from packe_technology as a,process_dimensio as b where a.packe_num=b.packe_num and a.packe_num >='"+time1+"' and a.packe_num <='"+time2+"' and a.state = 1 and a.technology_name = '"+t_name+"'";
		List<process_dimensio> prolist = codeService.findListByPacknums(sqlx);
		if(prolist!=null){
			//String paks = prolist.get(0).getPacke_num();
			String ids ="'"+prolist.get(0).getId()+"'";
			sql+="('"+place_num+"','"+prolist.get(0).getPacke_num()+"','"+t_name+"','"+job_number+"','"+user_name+"','"+date+"','"+prolist.get(0).getP_number()+"','"+0+"','"+0+"')";
			for (int i = 1; i < prolist.size(); i++) {
				sql+=",('"+place_num+"','"+prolist.get(i).getPacke_num()+"','"+t_name+"','"+job_number+"','"+user_name+"','"+date+"','"+prolist.get(i).getP_number()+"','"+0+"','"+0+"')";
				ids+=",'"+prolist.get(i).getId()+"'";
				//paks+=","+prolist.get(i).getPacke_num()+"";
			}
			int num = subService.addSubs(sql);
			if(num==prolist.size()){
				int a = packe_tService.updatePts(ids,2);
				if(a==num){
					resultJson.put("statusCode", "200");
					resultJson.put("title", "操作提示");
					resultJson.put("message", "操作成功！提交了"+a+"包");
					// 添加操作记录
					Record red = new Record();
					SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					red.setDate(dd.format(new Date()));
					red.setOpid(place_num);
					red.setOperation("提交数据");
					red.setUfrom("员工列表—手动提交");
					red.setFake(0);
					Admin admin = (Admin) request.getSession().getAttribute("admin");
					red.setPeople(admin.getAdmin_name());

					red.setRecord1("管理员" + admin.getAdmin_name() + "在"
							+ dd.format(new Date()) + "员工列表—手动提交中提交了订单号为" + place_num
							+ "中" + t_name + "工序,提交的包号为" + time1 + "到" + time2 + "的包号,共提交了"+a+"包");
					int m = recordService.add(red);
				} else {
					resultJson.put("statusCode", "500");
					resultJson.put("title", "操作提示");
					resultJson.put("message", "操作失败！数据异常！请检查是否数据正常！");
				}
				ResponseUtil.writeJson(ServletActionContext.getResponse(),
						resultJson.toString());
			}
		}else {
			resultJson.put("statusCode", "500");
			resultJson.put("title", "操作提示");
			resultJson.put("message", "未找到这些包或者这些包已经被提交,提交失败！");
			ResponseUtil.writeJson(ServletActionContext.getResponse(),
					resultJson.toString());
		}
		return null;
	}

	
		public String batchSubmit1(){
			JSONObject resultJson = new JSONObject();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
			if(packe_num==null||packe_num.equals("")){
				resultJson.put("statusCode", "500");
				resultJson.put("title", "操作提示");
				resultJson.put("message", "您还没有勾选包号！");
				ResponseUtil.writeJson(ServletActionContext.getResponse(),
						resultJson.toString());
				return null;
			}
			String packes = packe_num.replace(", ", "','"); 
			String sql ="insert into sub_data (place_num,packe_num,t_name,job_number,user_name,sub_time,number,price,money) values";
			String sqlx = "select a.id,a.packe_num,b.p_number from packe_technology as a,process_dimensio as b where a.packe_num=b.packe_num and a.packe_num in ('"+packes+"') and state = 1 and a.technology_name = '"+t_name+"'";
			List<process_dimensio> prolist = codeService.findListByPacknums(sqlx);
			if(prolist!=null){
				//String paks = prolist.get(0).getPacke_num();
				String ids ="'"+prolist.get(0).getId()+"'";
				sql+="('"+place_num+"','"+prolist.get(0).getPacke_num()+"','"+t_name+"','"+job_number+"','"+user_name+"','"+date+"','"+prolist.get(0).getP_number()+"','"+0+"','"+0+"')";
				for (int i = 1; i < prolist.size(); i++) {
					sql+=",('"+place_num+"','"+prolist.get(i).getPacke_num()+"','"+t_name+"','"+job_number+"','"+user_name+"','"+date+"','"+prolist.get(i).getP_number()+"','"+0+"','"+0+"')";
					ids+=",'"+prolist.get(i).getId()+"'";
					//paks+=","+prolist.get(i).getPacke_num()+"";
				}
				int num = subService.addSubs(sql);
				if(num==prolist.size()){
					int a = packe_tService.updatePts(ids,2);
					if(a==num){
						resultJson.put("statusCode", "200");
						resultJson.put("title", "操作提示");
						resultJson.put("message", "操作成功！提交了"+a+"包");
						// 添加操作记录
						Record red = new Record();
						SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						red.setDate(dd.format(new Date()));
						red.setOpid(place_num);
						red.setOperation("提交数据");
						red.setUfrom("员工列表—手动提交");
						red.setFake(0);
						Admin admin = (Admin) request.getSession().getAttribute("admin");
						red.setPeople(admin.getAdmin_name());

						red.setRecord1("管理员" + admin.getAdmin_name() + "在"
								+ dd.format(new Date()) + "员工列表—手动提交中提交了订单号为" + place_num
								+ "中" + t_name + "工序,提交的包号为" + time1 + "到" + time2 + "的包号,共提交了"+a+"包");
						int m = recordService.add(red);
					} else {
						resultJson.put("statusCode", "500");
						resultJson.put("title", "操作提示");
						resultJson.put("message", "操作失败！数据异常！请检查是否数据正常！");
					}
					ResponseUtil.writeJson(ServletActionContext.getResponse(),
							resultJson.toString());
				}
			}else {
				resultJson.put("statusCode", "500");
				resultJson.put("title", "操作提示");
				resultJson.put("message", "未找到这些包或者这些包已经被提交,提交失败！");
				ResponseUtil.writeJson(ServletActionContext.getResponse(),
						resultJson.toString());
			}
			return null;
		}
		public String getPackeNums(){
			JSONObject resultJson = new JSONObject();
			List<Packe_technology> paceList = null;
			paceList = packe_tService.getPackeNums(place_num, t_name, 1);
			resultJson.put("pList", paceList);
			ResponseUtil.writeJson(ServletActionContext.getResponse(),
					resultJson.toString());
			return null;
		}
		// 查询订单工票
			public String getLossPage() {
				// 返回前端数据
				JSONObject resultJson = new JSONObject();
				// List<sub_data> listAll = null;
				List<Loss> listPage = null;
				int total = 0;
				if (sort != null || advanceFilter != null
						&& !advanceFilter.equals("[]") || filterRules != null
						&& !filterRules.equals("[]")) {
					String sql = CombinatorialQuery.queryOne(advanceFilter, sort,
							order, filterRules);
					// System.out.println(sql);
					// listAll = subService.combGetsubList(sql, place_num);
					total = subService.findComdPageTotal(sql, place_num);// 获取条件查询出的总记录数
					listPage = subService.combGetLossList(sql, place_num, page, rows);
					//request.getSession().setAttribute("sqlx", sql);

				} else {
					// listAll = subService.findlist(place_num);
					total = subService.findLossPageTotal(place_num);// 获取条件查询出的总记录数
					listPage = subService.findLosslist(place_num, page, rows);
					//request.getSession().removeAttribute("sqlx");
				}
				if (total == 0) {
					resultJson.put("rows", "");
					resultJson.put("total", 0);
				} else {
					resultJson.put("rows", listPage);
					// total = listAll.size();
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
				}
				ResponseUtil.writeJson(ServletActionContext.getResponse(),
						resultJson.toString());
				return null;
			}
	public static void main(String[] args) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		// 过去一月
		c.setTime(new Date());
		c.add(Calendar.MONTH, -1);
		Date m = c.getTime();
		String mon = format.format(m);
		System.out.println("过去一个月：" + mon);
	}

}
