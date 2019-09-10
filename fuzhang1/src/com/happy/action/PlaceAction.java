package com.happy.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;

import testExport.ExportExcel;

import com.google.gson.Gson;
import com.happy.model.Admin;
import com.happy.model.Export;
import com.happy.model.Packe_technology;
import com.happy.model.PlaceSpeed;
import com.happy.model.Record;
import com.happy.model.technology;
import com.happy.model.place;
import com.happy.model.place_technology;
import com.happy.model.process_dimensio;
import com.happy.model.size_color;
import com.happy.service.CodeService;
import com.happy.service.Packe_tService;
import com.happy.service.PlaceService;
import com.happy.service.Place_tService;
import com.happy.service.RecordService;
import com.happy.service.Size_cService;
import com.happy.service.sub_dataService;
import com.happy.service.technologyService;
import com.happy.util.CombinatorialQuery;
import com.happy.util.ResUtil;
import com.happy.util.ResponseUtil;
import com.opensymphony.xwork2.ActionSupport;

@Controller
public class PlaceAction extends ActionSupport implements ServletRequestAware {

	private HttpServletRequest request;
	@Resource
	private PlaceService placeService;
	@Resource
	private Place_tService place_tService;
	@Resource
	private CodeService codeService;
	@Resource
	private Packe_tService packe_tService;
	@Resource
	private sub_dataService sub_dataService;
	@Resource
	private RecordService recordService;
	@Resource
	private Size_cService sCService;
	@Resource
	private technologyService technologyService;
	private String sort;// 排序依据字段名
	private String order;// 排序方式
	private int rows;// 每页显示的行数rows
	private int page; // 当前页
	private String advanceFilter;// 组合查询条件
	private String filterRules;// 过滤组件条件
	
	private String q;
	
	private String st;
	private String id;
	private String place_number;// 订单号
	private String place;
	private String techmology_name;
	private place pl;
	private String jsonData;
	private String plan_num;// 订单号
	private String style;// 风格
	private String customer;// 顾客名称
	private String number;// 数量
	private String material;// 里料
	private String cloth;// 主布
	private String distribution;// 配布
	private String fabric;// 面料说明
	private String technology;// 工艺说明
	private String delivery_time;// 交货日期
	private String place_time;// 下单日期
	private String place_speed;// 订单进度
	private String input_name;// 录入人
	private String price;
	private String t_id;
	private int biao;

	public int getBiao() {
		return biao;
	}

	public void setBiao(int biao) {
		this.biao = biao;
	}

	public RecordService getRecordService() {
		return recordService;
	}

	public void setRecordService(RecordService recordService) {
		this.recordService = recordService;
	}

	public Packe_tService getPacke_tService() {
		return packe_tService;
	}

	public void setPacke_tService(Packe_tService packe_tService) {
		this.packe_tService = packe_tService;
	}

	public sub_dataService getSub_dataService() {
		return sub_dataService;
	}

	public void setSub_dataService(sub_dataService sub_dataService) {
		this.sub_dataService = sub_dataService;
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPlan_num() {
		return plan_num;
	}

	public void setPlan_num(String plan_num) {
		this.plan_num = plan_num;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getCloth() {
		return cloth;
	}

	public void setCloth(String cloth) {
		this.cloth = cloth;
	}

	public String getDistribution() {
		return distribution;
	}

	public void setDistribution(String distribution) {
		this.distribution = distribution;
	}

	public String getFabric() {
		return fabric;
	}

	public void setFabric(String fabric) {
		this.fabric = fabric;
	}

	public String getTechnology() {
		return technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
	}

	public String getDelivery_time() {
		return delivery_time;
	}

	public void setDelivery_time(String delivery_time) {
		this.delivery_time = delivery_time;
	}

	public String getPlace_time() {
		return place_time;
	}

	public void setPlace_time(String place_time) {
		this.place_time = place_time;
	}

	public String getPlace_speed() {
		return place_speed;
	}

	public void setPlace_speed(String place_speed) {
		this.place_speed = place_speed;
	}

	public String getInput_name() {
		return input_name;
	}

	public void setInput_name(String input_name) {
		this.input_name = input_name;
	}

	public Place_tService getPlace_tService() {
		return place_tService;
	}

	public void setPlace_tService(Place_tService place_tService) {
		this.place_tService = place_tService;
	}

	public String getPlace_number() {
		return place_number;
	}

	public void setPlace_number(String place_number) {
		this.place_number = place_number;
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

	public CodeService getCodeService() {
		return codeService;
	}

	public void setCodeService(CodeService codeService) {
		this.codeService = codeService;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getTechmology_name() {
		return techmology_name;
	}

	public void setTechmology_name(String techmology_name) {
		this.techmology_name = techmology_name;
	}

	public String getSt() {
		return st;
	}

	public void setSt(String st) {
		this.st = st;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public place getPl() {
		return pl;
	}

	public void setPl(place pl) {
		this.pl = pl;
	}
	
	public String getT_id() {
		return t_id;
	}

	public void setT_id(String t_id) {
		this.t_id = t_id;
	}

	public PlaceService getPlaceService() {
		return placeService;
	}

	public void setPlaceService(PlaceService placeService) {
		this.placeService = placeService;
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

	// 查询所有订单
	public String list() {
		JSONObject resultJson = new JSONObject();
		if(biao==1){
			if(filterRules!=null){
				StringBuilder sb = new StringBuilder(filterRules);//构造一个StringBuilder对象
		        sb.insert(1, "{'field':'state','op':'contains','value':'1'},");//在指定的位置1，插入指定的字符串
		        filterRules = sb.toString();
			}else {
				filterRules ="[{'field':'state','op':'contains','value':'1'}]";
			}
	        //System.out.println(filterRules);
		}else{
			
		}
		List<place> listAll = null;
		List<place> listPage = null;
		int total = 0;
		if (sort != null || advanceFilter != null
				&& !advanceFilter.equals("[]") || filterRules != null
				&& !filterRules.equals("[]")) {
			String sql = CombinatorialQuery.queryOne(advanceFilter, sort,
					order, filterRules);
			// System.out.println(sql);
			// listAll = placeService.combGetPlaceList(sql);
			total = placeService.findComdPageTotal(sql);// 组合查询表中的总记录数
			listPage = placeService.combGetPlaceList(sql, page, rows);
		} else {
			// listAll = placeService.getPlace();// 查询所有记录
			total = placeService.findPageTotal();// 查询表中的总记录数
			listPage = placeService.getPlace(page, rows);// 查询分页
		}
		if (listPage == null) {
			resultJson.put("rows", "");
			resultJson.put("total", 0);
		} else {
			resultJson.put("rows", listPage);
			// int total = listAll.size();
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

	public String add() {
		// 前端返回值
		JSONObject resultJson = new JSONObject();
		// 判断是否有相同的订单
		place place = placeService.findByNumber(plan_num);
		if (place != null) {
			resultJson.put("statusCode", "500");
			resultJson.put("title", "操作提示");
			resultJson.put("message", "订单号已经存在！");
			ResponseUtil.writeJson(ServletActionContext.getResponse(),
					resultJson.toString());
			return null;
		}
		// 将前端传入值序列对象
		place pls = new place();
		pls.setCustomer(customer);
		pls.setDelivery_time(delivery_time);
		pls.setDistribution(distribution);
		pls.setFabric(fabric);
		pls.setCloth(cloth);
		pls.setMaterial(material);
		pls.setPlace_speed("0");
		pls.setPlace_time(place_time);
		pls.setPlan_num(plan_num);
		pls.setState(1);
		pls.setStyle(style);
		pls.setStyle_num(000);
		pls.setTechnology(technology);
		pls.setInput_name(input_name);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		pls.setInput_time(df.format(new Date()));

		// 验证数量是否是数字
		String regex = "[0-9]*";
		boolean flag = number.matches(regex);
		if (flag == true) {
			pls.setNumber(Integer.parseInt(number));
			// 英文或数字中文或英文数字中文组合的正则表达式
			/*String reg = "^[a-z0-9A-Z\u4e00-\u9fa5]+$";
			boolean flag2 = plan_num.matches(reg);
			if (flag2 == true) {*/
				int num = placeService.add(pls);
				// 添加操作记录
				// Record re=new Record();
				// re.setDate(df.format(new Date()));
				// re.setOpid(plan_num);
				// re.setOperation("新增订单");
				// re.setUfrom("订单列表");
				// re.setFake(0);
				// Admin admin=(Admin)
				// request.getSession().getAttribute("admin");
				// re.setPeople(admin.getAdmin_name());
				// re.setRecord1("管理员"+admin.getAdmin_name()+"在"+df.format(new
				// Date())+"订单列表中新增了一条"+"订单号"+plan_num+"，顾客名"+customer+"，数量"+number+"的订单");
				// int m=recordService.add(re);
				if (num > 0) {// &&m>0
					
					if(t_id.length()>0){
						StringBuilder sql = new StringBuilder("select * from technology where fake=0 ");
						sql.append(" and id in ("
								+ t_id.replaceAll(", ", "','") + ")");
						List<technology> teList = technologyService.findListByIds(sql.toString());
						if(teList!=null &&teList.size()>0 ){
							String sqlx = "INSERT INTO `place_technology` (`place_number`, `technology_name`, `price`, `fake`) VALUES ";
							for (int i = 0; i < teList.size(); i++) {
								sqlx+= "('" + plan_num + "','" + teList.get(i).getName() + "','" + 0
										+ "','" + 0 + "')";
								if (i != teList.size() - 1) {
									sqlx += ",";
								}
							}
							int a = place_tService.batchdelete_gong(sqlx);
						}
					}
					resultJson.put("statusCode", "200");
					resultJson.put("title", "操作提示");
					resultJson.put("message", "操作成功！");
				} else {
					resultJson.put("statusCode", "300");
					resultJson.put("title", "操作提示");
					resultJson.put("message", "操作失败！");
				}
				ResponseUtil.writeJson(ServletActionContext.getResponse(),
						resultJson.toString());
				return null;
			/*} else {
				resultJson.put("statusCode", "300");
				resultJson.put("title", "操作提示");
				resultJson.put("message", "订单号只能有英文或数字组成");
				ResponseUtil.writeJson(ServletActionContext.getResponse(),
						resultJson.toString());
				return null;
			}*/

		} else {
			resultJson.put("statusCode", "300");
			resultJson.put("title", "操作提示");
			resultJson.put("message", "订单数量不正确");
			ResponseUtil.writeJson(ServletActionContext.getResponse(),
					resultJson.toString());
			return null;
		}
	}
	
	// 根据订单客户customer 模糊查询订单
	/*public String findByCustomer() {
		List<place> placeList = placeService.findByCustomer(st);
		if (placeList != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("placeList", placeList);
			Gson gson = new Gson();
			String json = gson.toJson(map);
			ServletActionContext.getResponse().setContentType(
					"text/html;charset=utf-8");
			try {
				ServletActionContext.getResponse().getWriter().print(json);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		return null;
	}*/
	// 根据订单客户Place 模糊查询订单
	public String findByPlace() {
		if(q==null){
			q="";
		}
		JSONObject resultJson = new JSONObject();
		List<place> placeList = placeService.findByPlaceLike(q);
		if (placeList != null) {
			resultJson.put("rows", placeList);
			int total = placeList.size();
			resultJson.put("total", total);// 总记录数
			int totalPage = total % 1 == 0 ? (total / 1)
					: (total / 1) + 1;// 总页数
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
	// 订单绑定工序
	public String add_gong() throws UnsupportedEncodingException {
		// 前端返回值
		JSONObject resultJson = new JSONObject();
		if (techmology_name == null) {
			resultJson.put("statusCode", "300");
			resultJson.put("title", "操作提示");
			resultJson.put("message", "请选择工序！");
			ResponseUtil.writeJson(ServletActionContext.getResponse(),
					resultJson.toString());
			return null;
		}
		String place1 = URLDecoder.decode(place,"UTF-8");
		List<place_technology> list = place_tService.findByNum(place1);
		boolean flag = false;
		// 该订单否生成了包，如果生成了则不可以添加工序
		
		String[] strs = techmology_name.split(",");
		String sql = "INSERT INTO `place_technology` (`place_number`, `technology_name`, `price`, `fake`) VALUES ";
		if (list == null) {
			//if (listPack == null) {
				for (int i = 0; i < strs.length; i++) {
					sql += "('" + place1 + "','" + strs[i].trim() + "','" + 0
							+ "','" + 0 + "')";
					if (i != strs.length - 1) {
						sql += ",";
					}
				}
				int num = place_tService.batchdelete_gong(sql);

				//
				//  place_technology pt=new place_technology();
				//  pt.setPlace_number(place);
				//  pt.setTechnology_name(techmology_name);
				//  
				//  BigDecimal a = new BigDecimal(price); pt.setPrice(a); int
				//  num=place_tService.add_gong(pt);
				// 
				if (num > 0) {
					resultJson.put("statusCode", "200");
					resultJson.put("title", "操作提示");
					resultJson.put("message", "操作成功！");
				} else {
					resultJson.put("statusCode", "300");
					resultJson.put("title", "操作提示");
					resultJson.put("message", "操作失败！");
				}
			//} else {
			//	resultJson.put("statusCode", "500");
			//	resultJson.put("title", "操作提示");
			//	resultJson.put("message", "操作失败！该订单已经生成包，不可添加工序");
			//}
		} else {
			for (int i = 0; i < list.size(); i++) {
				for (int j = 0; j < strs.length; j++) {
					if (list.get(i).getTechnology_name().equals(strs[j].trim())) {
						flag = true;
						resultJson.put("message", "该订单已经绑定" + strs[j].trim()
								+ "工序！");
						break;
					}
				}

			}
			if (flag) {
				resultJson.put("statusCode", "500");
				resultJson.put("title", "操作提示");
			} else {
				//if (listPack == null) {
					for (int i = 0; i < strs.length; i++) {
						sql += "('" + place1 + "','" + strs[i].trim() + "','"
								+ 0 + "','" + 0 + "')";
						if (i != strs.length - 1) {
							sql += ",";
						}
					}
					int num = place_tService.batchdelete_gong(sql);
					//
					//  place_technology pt=new place_technology();
					//  pt.setPlace_number(place);
					//  pt.setTechnology_name(techmology_name); BigDecimal a =
					//  new BigDecimal(price); pt.setPrice(a); 
					//  num=place_tService.add_gong(pt);
					// 
					if (num > 0) {
						List<process_dimensio> listPack = codeService.findByNumber(place1);
						if(listPack!=null){
							String sql1 = "insert into packe_technology (packe_num,technology_name,state,p_num) values";
							String pnum = listPack.get(0).getP_num();
							for (int i = 0; i < listPack.size(); i++) {
								for (int j = 0; j < strs.length; j++) {
									sql1 += "('" + listPack.get(i).getPacke_num() + "','"
											+ strs[j].trim() + "','"
											+ 1 + "','"
											+ pnum + "'),";
								}
							}
							sql1 = sql1.substring(0, sql1.length()-1);
							int num1 = packe_tService.batchAddTechnology(sql1);// 批量给新的包添加绑定工序
							if(num1<1){
								resultJson.put("statusCode", "400");
								resultJson.put("title", "操作提示");
								resultJson.put("message", "给包添加工序时出现未知错误！");
								ResponseUtil.writeJson(ServletActionContext.getResponse(),
										resultJson.toString());
								return null;
							}
						}
						
						resultJson.put("statusCode", "200");
						resultJson.put("title", "操作提示");
						resultJson.put("message", "操作成功！");
					} else {
						resultJson.put("statusCode", "300");
						resultJson.put("title", "操作提示");
						resultJson.put("message", "操作失败！");
					}
				//} else {
				//	resultJson.put("statusCode", "300");
				//	resultJson.put("title", "操作提示");
				//	resultJson.put("message", "操作失败！该订单已经生成包，不可添加工序。");
				//}

			}
		}
		ResponseUtil.writeJson(ServletActionContext.getResponse(),
				resultJson.toString());
		return null;
	}
	// 订单绑定工序
	public String add_gong1() throws UnsupportedEncodingException {
		// 前端返回值
		JSONObject resultJson = new JSONObject();
		if (techmology_name == null) {
			resultJson.put("statusCode", "300");
			resultJson.put("title", "操作提示");
			resultJson.put("message", "请选择工序！");
			ResponseUtil.writeJson(ServletActionContext.getResponse(),
					resultJson.toString());
			return null;
		}
		String place1 = URLDecoder.decode(place,"UTF-8");
		List<place_technology> list = place_tService.findByNum(place1);
		boolean flag = false;
		// 该订单否生成了包，如果生成了则不可以添加工序
		List<process_dimensio> listPack = codeService.findByNumber(place1);
		String[] strs = techmology_name.split(",");
		String sql = "INSERT INTO `place_technology` (`place_number`, `technology_name`, `price`, `fake`) VALUES ";
		if (list == null) {
			if (listPack == null) {
				for (int i = 0; i < strs.length; i++) {
					sql += "('" + place1 + "','" + strs[i].trim() + "','" + 0
							+ "','" + 0 + "')";
					if (i != strs.length - 1) {
						sql += ",";
					}
				}
				int num = place_tService.batchdelete_gong(sql);

					/*
					 * place_technology pt=new place_technology();
					 * pt.setPlace_number(place);
					 * pt.setTechnology_name(techmology_name);
					 * 
					 * BigDecimal a = new BigDecimal(price); pt.setPrice(a); int
					 * num=place_tService.add_gong(pt);
					 */
				if (num > 0) {
					resultJson.put("statusCode", "200");
					resultJson.put("title", "操作提示");
					resultJson.put("message", "操作成功！");
				} else {
					resultJson.put("statusCode", "300");
					resultJson.put("title", "操作提示");
					resultJson.put("message", "操作失败！");
				}
			} else {
				resultJson.put("statusCode", "500");
				resultJson.put("title", "操作提示");
				resultJson.put("message", "操作失败！该订单已经生成包，不可添加工序");
			}

		} else {
			for (int i = 0; i < list.size(); i++) {
				for (int j = 0; j < strs.length; j++) {
					if (list.get(i).getTechnology_name().equals(strs[j].trim())) {
						flag = true;
						resultJson.put("message", "该订单已经绑定" + strs[j].trim()
								+ "工序！");
					}
				}
			}
			if (flag) {
				resultJson.put("statusCode", "500");
				resultJson.put("title", "操作提示");
			} else {
				if (listPack == null) {
					for (int i = 0; i < strs.length; i++) {
						sql += "('" + place1 + "','" + strs[i].trim() + "','"
								+ 0 + "','" + 0 + "')";
						if (i != strs.length - 1) {
							sql += ",";
						}
					}
					int num = place_tService.batchdelete_gong(sql);
					/*
					 * place_technology pt=new place_technology();
					 * pt.setPlace_number(place);
					 * pt.setTechnology_name(techmology_name); BigDecimal a =
					 * new BigDecimal(price); pt.setPrice(a); int
					 * num=place_tService.add_gong(pt);
					 */
					if (num > 0) {
						resultJson.put("statusCode", "200");
						resultJson.put("title", "操作提示");
						resultJson.put("message", "操作成功！");
					} else {
						resultJson.put("statusCode", "300");
						resultJson.put("title", "操作提示");
						resultJson.put("message", "操作失败！");
					}
				} else {
					resultJson.put("statusCode", "300");
					resultJson.put("title", "操作提示");
					resultJson.put("message", "操作失败！该订单已经生成包，不可添加工序。");
				}

			}
		}
		ResponseUtil.writeJson(ServletActionContext.getResponse(),
				resultJson.toString());
		return null;
	}
	// 根据订单id删除工序
	public String delete_gong() {
		int num = 0;
		String sql = "DELETE FROM place_technology where id in";
		// 获取前端传入的json
		JSONObject jsonobject = JSONObject.fromObject(jsonData);
		// 前端返回值
		JSONObject resultJson = new JSONObject();
		String id1 = id.replaceAll("'", "");// 去除单引号
		String place_number1 = place_number.replaceAll("'", "");// 去除单引号
		if (id1.indexOf(",") == -1 && place_number1.indexOf(",") == -1) {
			// 工序是否生成了包，如果生成了则不可以删除
			List<process_dimensio> list = codeService
					.findByNumber(place_number1);
			if (list != null) {
				resultJson.put("statusCode", "300");
				resultJson.put("title", "操作提示");
				resultJson.put("message", "删除失败该工序已绑定了包");
			} else {
				num = place_tService.delete_gong(Integer.parseInt(id1));// 根据id删除订单绑定的工序
				if (num > 0) {
					resultJson.put("statusCode", "200");
					resultJson.put("title", "操作提示");
					resultJson.put("message", "操作成功！");
				} else {
					resultJson.put("statusCode", "300");
					resultJson.put("title", "操作提示");
					resultJson.put("message", "操作失败！");
				}
			}
		} else {
			String[] strs1 = place_number1.split(",");
			List<process_dimensio> list = codeService.findByNumber(strs1[0]);
			if (list != null) {
				resultJson.put("statusCode", "300");
				resultJson.put("title", "操作提示");
				resultJson.put("message", "删除失败该工序已绑定了包");
			} else {
				sql += "(" + id + ")";
				num = place_tService.batchdelete_gong(sql);// 根据id批量删除订单绑定的工序

				if (num > 0) {
					resultJson.put("statusCode", "200");
					resultJson.put("title", "操作提示");
					resultJson.put("message", "操作成功！");
				} else {
					resultJson.put("statusCode", "300");
					resultJson.put("title", "操作提示");
					resultJson.put("message", "操作失败！");
				}
			}

		}
		ResponseUtil.writeJson(ServletActionContext.getResponse(),
				resultJson.toString());
		return null;
	}

	// 根据订单号查询所有包的完成情况
	public String find() {
		// List<place_technology> list=placeService.findByNum(place);
		List<process_dimensio> packList = codeService.findByNumber(place);
		List<process_dimensio> listPage = codeService.findByNumber(place, page,
				rows);
		JSONObject resultJson = new JSONObject();
		if (packList == null) {
			resultJson.put("rows", "");
			resultJson.put("total", 0);
		} else {
			resultJson.put("rows", listPage);
			int total = packList.size();
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

	// 获取订单进度数据报表
	public String placeSchedule() throws Exception {
		JSONObject resultJson = new JSONObject();
		if (biao == 1) {
			List<PlaceSpeed> packList = packe_tService.queryProgressAll(place);
			resultJson.put("list", packList);
			ResUtil.write(resultJson, ServletActionContext.getResponse());
			return null;
		}
		List<PlaceSpeed> listPage = packe_tService.queryProgress(place, page,
				rows);
		// List<PlaceSpeed> packList = packe_tService.queryProgressAll(place);
		int total = packe_tService.findPageTotal(place);// 查询总记录数
		
		if (total == 0) {
			resultJson.put("rows", "");
			resultJson.put("total", 0);
		} else {
			resultJson.put("rows", listPage);
			// int total = packList.size();
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

	// 根据订单id查询订单详情
	public String findInfo() {
		List<place> list = placeService.findById(Integer.parseInt(id));
		JSONObject info = JSONObject.fromObject(list.get(0));
		ResponseUtil.writeJson(ServletActionContext.getResponse(),
				info.toString());
		return null;
	}
	public String findInfo1() {
		List<place> list = placeService.findById(Integer.parseInt(id));
		
		JSONObject resultJson = new JSONObject();
		//resultJson
		ResponseUtil.writeJson(ServletActionContext.getResponse(),
				resultJson.toString());
		return null;
	}
	// 更新订单信息
	public String update() {
		// 前端返回值
		JSONObject resultJson = new JSONObject();
		/*List<place_technology> listAll = place_tService.findByNum(plan_num);

		if (listAll != null) {
			resultJson.put("statusCode", "300");
			resultJson.put("title", "操作提示");
			resultJson.put("message", "修改失败该订单已绑定工序");
		} else {*/
			int num1 = codeService.findNumberAByPlace(plan_num);
			if(num1>Integer.valueOf(number)){
				resultJson.put("statusCode", "500");
				resultJson.put("title", "操作提示");
				resultJson.put("message", "操作失败,订单数量不能小于已分包的数量！");
				ResponseUtil.writeJson(ServletActionContext.getResponse(),
						resultJson.toString());
				return null;
			}
		
			// 将前端传入值序列对象
			place pls = new place();
			pls.setId(Integer.parseInt(id));
			pls.setCustomer(customer);
			pls.setDelivery_time(delivery_time);
			pls.setDistribution(distribution);
			pls.setFabric(fabric);
			pls.setCloth(cloth);
			pls.setMaterial(material);
			pls.setNumber(Integer.parseInt(number));
			pls.setPlace_speed("0");
			pls.setPlace_time(place_time);
			pls.setPlan_num(plan_num);
			pls.setState(1);
			pls.setStyle(style);
			pls.setStyle_num(000);
			pls.setTechnology(technology);
			pls.setInput_name(input_name);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			pls.setInput_time(df.format(new Date()));//
			int num = placeService.updateInfo(pls);
			// 添加操作记录
			Record re=new Record();
			re.setDate(df.format(new Date()));
			re.setOpid(plan_num);
			re.setOperation("修改订单");
			re.setUfrom("订单列表");
			re.setFake(0);
			Admin admin=(Admin) request.getSession().getAttribute("admin");
			re.setPeople(admin.getAdmin_name());
			re.setRecord1("管理员"+admin.getAdmin_name()+"在"+df.format(new
			Date())+"订单列表中把"+plan_num+"订单的数量修改为"+number);
			int m=recordService.add(re);
			if (num > 0) {
				resultJson.put("statusCode", "200");
				resultJson.put("title", "操作提示");
				resultJson.put("message", "操作成功！");
			} else {
				resultJson.put("statusCode", "300");
				resultJson.put("title", "操作提示");
				resultJson.put("message", "操作失败！");
			}
		//}
		ResponseUtil.writeJson(ServletActionContext.getResponse(),
				resultJson.toString());
		return null;

	}

	// 删除订单
	public String delete() {
		int num = 0;
		// 前端返回值
		JSONObject resultJson = new JSONObject();
		String sql1 = "delete a.*,b.*,c.* from place a left join place_technology b on a.plan_num=b.place_number left join process_dimensio c on a.plan_num=c.p_num "
				+ "where a.plan_num in (";// 订单表
		String sql2 = "";// 订单绑定工序表
		String sql3 = "";// 包表
		String sql4 = "delete from packe_technology where p_num in (";// 包邦定工序表
		String sql5 = "delete from sub_data where place_num in(";// 提交记录表
		String sql = "";
		String id1 = id.replaceAll("'", "");// 去除单引号0
		String plan_num1 = plan_num.replaceAll("'", "");// 去除单引号0
		if (id1.indexOf(",") != -1) {// 判断是否包含，
			resultJson.put("statusCode", "300");
			resultJson.put("title", "操作提示");
			resultJson.put("message", "只能勾选单个订单删除！");
			ResponseUtil.writeJson(ServletActionContext.getResponse(),
					resultJson.toString());
			return null;
		} else {
			sql += "'" + plan_num1 + "')";
			int ptCount = packe_tService.findByPnumCount(plan_num1);
			if (ptCount > 0) {
				sql4 += sql + " order by packe_num desc limit 1000 ";
				for (int i = 0; i < ptCount; i += 1000) {
					int n = packe_tService.updateStartBatch(sql4);
					if (n < 1)
						break;
				}
			}
			int sdCount = sub_dataService.findlistCount(plan_num1);
			if (sdCount > 0) {
				sql5 += sql + " order by packe_num desc limit 1000 ";
				for (int i = 0; i < sdCount; i += 1000) {
					int n = sub_dataService.deleteBatch(sql5);
					if (n < 1)
						break;
				}
			}
			num = placeService.deleteBatch(sql1 + sql);
			// 添加操作记录
			Record re = new Record();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			re.setDate(df.format(new Date()));
			re.setOpid(plan_num1);
			re.setOperation("删除订单");
			re.setUfrom("订单列表");
			re.setFake(0);
			Admin admin = (Admin) request.getSession().getAttribute("admin");
			re.setPeople(admin.getAdmin_name());
			re.setRecord1("管理员" + admin.getAdmin_name() + "在"
					+ df.format(new Date()) + "订单列表中删除了" + "订单号为" + plan_num
					+ "的订单");
			int m = recordService.add(re);
		}
		if (num > 0) {
			resultJson.put("statusCode", "200");
			resultJson.put("title", "操作提示");
			resultJson.put("message", "操作成功！");
		} else {
			resultJson.put("statusCode", "300");
			resultJson.put("title", "操作提示");
			resultJson.put("message", "操作失败！");
		}
		ResponseUtil.writeJson(ServletActionContext.getResponse(),
				resultJson.toString());
		return null;
	}

	// 根据订单号查询所有工序
	public String findByT() {
		// List<place_technology> listAll = place_tService.findByNum(place);//
		// 根据订单号查询该订单所有的绑定工序
		int total = place_tService.findPageTotal(place);// 查询满足条件的总记录数
		List<place_technology> listPage = place_tService.findByNum(place, page,
				rows);// 根据订单号分页查询该订单绑定的工序
		JSONObject resultJson = new JSONObject();
		if (total == 0) {
			resultJson.put("rows", "");
			resultJson.put("total", 0);
		} else {
			resultJson.put("rows", listPage);
			// int total = listAll.size();
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

	// 查询待生产订单
	public String findDsc() {
		List<place> listAll = placeService.findDai();
		List<place> listPage = placeService.findDai(page, rows);
		JSONObject resultJson = new JSONObject();
		if (listAll == null) {
			resultJson.put("rows", "");
			resultJson.put("total", 0);
		} else {
			resultJson.put("rows", listPage);
			int total = listAll.size();
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

	// 编辑单价查询订单绑定工序
	public String findAllByplace() {
		JSONObject resultJson = new JSONObject();
		List<place_technology> listAll = place_tService.findByNum(place);// 根据订单号查询该订单所有的绑定工序
		resultJson.put("list", listAll);
		ResponseUtil.writeJson(ServletActionContext.getResponse(),
				resultJson.toString());
		return null;
	}

	// 编辑单价
	public String updatePrice() {
		JSONObject resultJson = new JSONObject();
		String[] sid = id.split(",");
		String[] sprice = price.split(",");
		String sql1 = "update place_technology set price = case id";
		String sql2 = " end where id in (";
		for (int i = 0; i < sid.length; i++) {
			String newPrice;
			if (sprice[i].equals("")) {
				newPrice = "0";
			} else {
				newPrice = sprice[i].trim();
			}
			sql1 += " when " + sid[i].trim() + " then " + newPrice;
			sql2 += sid[i].trim();
			if (i != sid.length - 1) {
				sql2 += ",";
			} else {
				sql2 += ")";
			}
		}
		String sql = sql1 + sql2;
		int num = place_tService.batchdelete_gong(sql);
		if (num > 0) {
			resultJson.put("statusCode", "200");
			resultJson.put("title", "操作提示");
			resultJson.put("message", "操作成功！");
		} else {
			resultJson.put("statusCode", "300");
			resultJson.put("title", "操作提示");
			resultJson.put("message", "操作失败！");
		}
		ResponseUtil.writeJson(ServletActionContext.getResponse(),
				resultJson.toString());
		return null;
	}

	/**
	 * 查询指定工序和订单号的未完成包号
	 * 
	 * @return
	 */
	public List<Packe_technology> jinduInfo() {
		JSONObject resultJson = new JSONObject();
		List<Packe_technology> ptList = packe_tService
				.findListByPNumAndTechnologyName(plan_num, technology, 1);
		resultJson.put("ptList", ptList);
		ResponseUtil.writeJson(ServletActionContext.getResponse(),
				resultJson.toString());
		return null;
	}
	/**
	 * 根据颜色尺码分类查询
	 * @return
	 */
	public String findListBySizeAndColor(){
		List<process_dimensio> listAll = placeService.findSubProcessD(plan_num, -1, -1);//查询所有
		List<process_dimensio> listPage = placeService.findSubProcessD(plan_num,page, rows);
		JSONObject resultJson = new JSONObject();
		if (listAll == null) {
			resultJson.put("rows", "");
			resultJson.put("total", 0);
		} else {
			if(biao==2){
				resultJson.put("list", listAll);
				ResponseUtil.writeJson(ServletActionContext.getResponse(),
						resultJson.toString());
				return null;
			}
			resultJson.put("rows", listPage);
			int total = listAll.size();
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
	/**
	 * 根据时间查询
	 * @return
	 */
	public String findListBytime(){
		if(place_time==null){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
			System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
			place_time =df.format(new Date());
		}
		
		List<process_dimensio> listAll = placeService.findByTime(plan_num, -1, -1,place_time);//查询所有
		List<process_dimensio> listPage = placeService.findByTime(plan_num,0, rows,place_time);
		JSONObject resultJson = new JSONObject();
		if (listAll == null) {
			resultJson.put("rows", "");
			resultJson.put("total", 0);
		} else {
			if(biao==2){
				resultJson.put("list", listAll);
				ResponseUtil.writeJson(ServletActionContext.getResponse(),
						resultJson.toString());
				return null;
			}
			resultJson.put("rows", listPage);
			int total = listAll.size();
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
	/**
	 * 修改订单状态
	 * @return
	 */
	public String updateState(){
		JSONObject resultJson = new JSONObject();
		String sql = "UPDATE place SET state = (CASE state WHEN 1 THEN 3 WHEN 3 THEN 1 END) where id in (";
		String ids = id.replace("'", "");
		sql = sql+ids+")";
		int num = placeService.updateState(sql);
		if (num > 0) {
			resultJson.put("statusCode", "200");
			resultJson.put("title", "操作提示");
			resultJson.put("message", "操作成功！");
		} else {
			resultJson.put("statusCode", "300");
			resultJson.put("title", "操作提示");
			resultJson.put("message", "操作失败！");
		}
		ResponseUtil.writeJson(ServletActionContext.getResponse(),
				resultJson.toString());
		return null;
	}
	/**
	 * 根据订单号查询订单颜色
	 * @return
	 */
	public List<size_color> findListByPlaceNum() {
		JSONObject resultJson = new JSONObject();
		List<size_color> scList = sCService.findListByPlaceNum(plan_num);
		resultJson.put("scList", scList);
		ResponseUtil.writeJson(ServletActionContext.getResponse(),
				resultJson.toString());
		return null;
	}
	public String TechnoecxlOut(){
		// fieldName
				// excelTitle:文章管理_导出数据_20180323104111
				// colName:标题,创建时间
				// 跟据员工id查询员工所提交的数据
				// 前端返回值
				JSONObject resultJson = new JSONObject();
				ExportExcel<place_technology> ex = new ExportExcel<place_technology>();
				String[] headers = { "序号","订单号",  "工序名称", "单价" };
				String sql = (String) request.getSession().getAttribute("sql");
				List<place_technology> listAll = null;
				//if (sql == null) {
					// 导出结果查询
					listAll = place_tService.getAllpt(place_number);
					if(listAll!=null&&listAll.size()>0){
						BigDecimal num = new BigDecimal(Double.toString(0.0)); ;
						for (int i = 0; i < listAll.size(); i++) {
							listAll.get(i).setId(i+1);
							BigDecimal num1 = listAll.get(i).getPrice();
							num = num.add(num1);
						}
						place_technology place_technology = new place_technology();
						place_technology.setId(listAll.size()+1);
						place_technology.setPlace_number(listAll.get(0).getPlace_number());
						place_technology.setPrice(num);
						place_technology.setTechnology_name("综合单价");
						listAll.add(place_technology);
					}
					
				//} else {
				//	listAll = sub_dataService.exportFind(sql, job_number);
				//}

				//String user_name = listAll.get(0).getUser_name();
				SimpleDateFormat df = new SimpleDateFormat("MM-dd-HH-mm-ss");// 设置日期格式
				String time = df.format(new Date());
				String path = request.getSession().getServletContext()
						.getRealPath("/download")
						+ File.separator;
				File file = new File(path);
				if (!file.exists()) {
					file.mkdirs();
				}
				try {
					OutputStream out = new FileOutputStream(path + "daochu" + time
							+ ".xls");
					ex.exportExcel(headers, listAll, out);
					out.close();
					// JOptionPane.showMessageDialog(null, "导出成功!");
					// System.out.println("excel导出成功！");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				/* resultJson.put("message", "导出表格成功"); */
				resultJson.put("downurl", "daochu" + time + ".xls");
				ResponseUtil.writeJson(ServletActionContext.getResponse(),
						resultJson.toString());
				return null;
	}
}
