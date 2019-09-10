package com.happy.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;

import redis.clients.jedis.Jedis;

import com.happy.lock.DistributedLock;
import com.happy.lock.Service;
import com.happy.model.Packe_technology;
import com.happy.model.place;
import com.happy.model.place_technology;
import com.happy.model.process_dimensio;
import com.happy.model.sub_data;
import com.happy.model.weixin.Signature;
import com.happy.model.weixin.User;
import com.happy.model.weixin.WechatResult;
import com.happy.service.CodeService;
import com.happy.service.Packe_tService;
import com.happy.service.PlaceService;
import com.happy.service.Place_tService;
import com.happy.service.ToKenService;
import com.happy.service.UserService;
import com.happy.service.sub_dataService;
import com.happy.util.HttpUtils;
import com.happy.util.PayWxUtil;
import com.happy.util.ResUtil;
import com.google.gson.Gson;
import com.happy.model.weixin.WeiXinUtil;
import com.opensymphony.xwork2.ActionSupport;

@Controller
public class WxPay extends ActionSupport implements ServletRequestAware {
	private HttpServletRequest request;
	@Resource
	private ToKenService toKenService;
	private String urlx; // 接收扫一扫页面ajax发送过来的url
	@Resource
	private UserService userService;
	@Resource
	private CodeService codeService;
	@Resource
	private sub_dataService sub_dataService;
	@Resource
	private PlaceService placeService;
	@Resource
	private Place_tService place_tService;
	@Resource
	private Packe_tService packe_tService;
	private static String REDIS_LOCK = "redis_lock";// key
	private static Lock lock = new ReentrantLock();
	private static Map<Integer, Lock> locks = new HashMap<Integer, Lock>();
	// 接收激活页面的数据
	private String user_account;
	private String job_number;
	// 接收扫码页面传过来的包号
	private String packe_num;
	private int id;
	private String t_name;
	private String place_num;
	private String user_name;
	private int number;
	private int biao;
	
	private String ids;
	
 	public String getT_name() {
		return t_name;
	}

	public void setT_name(String t_name) {
		this.t_name = t_name;
	}

	public int getBiao() {
		return biao;
	}

	public void setBiao(int biao) {
		this.biao = biao;
	}

	public Packe_tService getPacke_tService() {
		return packe_tService;
	}

	public void setPacke_tService(Packe_tService packe_tService) {
		this.packe_tService = packe_tService;
	}

	public Place_tService getPlace_tService() {
		return place_tService;
	}

	public void setPlace_tService(Place_tService place_tService) {
		this.place_tService = place_tService;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getPlace_num() {
		return place_num;
	}

	public void setPlace_num(String place_num) {
		this.place_num = place_num;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public sub_dataService getSub_dataService() {
		return sub_dataService;
	}

	public void setSub_dataService(sub_dataService sub_dataService) {
		this.sub_dataService = sub_dataService;
	}

	public PlaceService getPlaceService() {
		return placeService;
	}

	public void setPlaceService(PlaceService placeService) {
		this.placeService = placeService;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public CodeService getCodeService() {
		return codeService;
	}

	public void setCodeService(CodeService codeService) {
		this.codeService = codeService;
	}

	public String getPacke_num() {
		return packe_num;
	}

	public void setPacke_num(String packe_num) {
		this.packe_num = packe_num;
	}

	public String getUser_account() {
		return user_account;
	}

	public void setUser_account(String user_account) {
		this.user_account = user_account;
	}

	public String getJob_number() {
		return job_number;
	}

	public void setJob_number(String job_number) {
		this.job_number = job_number;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getUrlx() {
		return urlx;
	}

	public void setUrlx(String urlx) {
		this.urlx = urlx;
	}

	public ToKenService getToKenService() {
		return toKenService;
	}

	public void setToKenService(ToKenService toKenService) {
		this.toKenService = toKenService;
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

	// 获取code 微信支付第一步 通过code换取网页授权access_token
	public String code() throws IOException {

		request.getSession().removeAttribute("erro");

		try {
			// 回调地址
			String redirect_uri = URLEncoder.encode(WeiXinUtil.ip
					+ "WxPayopenid.action?codeID=1", "utf-8");

			/*
			 * String url =
			 * "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" +
			 * WeiXinUtil.appid + "&redirect_uri=" + redirect_uri +
			 * "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect"
			 * ;
			 */
			// scope作用域分两种 1为snsapi_base 则本步骤中获取到网页授权access_token的同时，也获取到了openid
			// 2为snsapi_userinfo
			String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
					+ WeiXinUtil.appid
					+ "&redirect_uri="
					+ redirect_uri
					+ "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
			ServletActionContext.getResponse().sendRedirect(url);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 获取openid 微信支付第二步 由第一步自动回调到这个方法，
	public String openid() throws Exception {
		HttpSession session = request.getSession();
		String OPEN_ID = "";

		String code = request.getParameter("code");

		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
				+ WeiXinUtil.appid
				+ "&secret="
				+ WeiXinUtil.secret
				+ "&code="
				+ code + "&grant_type=authorization_code";
		if (code != null) {
			String json = HttpUtils.get(requestUrl);
			WechatResult result = new Gson().fromJson(json, WechatResult.class);
			OPEN_ID = result.getOpenid();
			String access_token = result.getAccess_token();
			/*
			 * 此access_token为网页授权的access_token，和数据库存储的普通access_token不同，
			 * 数据库存储的是用来调用微信js接口用的
			 */
			// session.setAttribute("token", access_token);
			session.setAttribute("open_id", OPEN_ID);

			User user = userService.findByOpenid(OPEN_ID);
			if (user != null) {
				request.getSession().setAttribute("user", user);
				ServletActionContext.getResponse().sendRedirect(
						WeiXinUtil.ip + "jsp/weixin/sys.jsp");
				return null;
			} else {
				ServletActionContext.getResponse().sendRedirect(
						WeiXinUtil.ip + "jsp/weixin/register.jsp");
				return null;
			}
		}
		return null;

	}

	/**
	 * 调用微信后台项目的接口，获取调用微信开放js接口需要的凭证（access_token）
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getAcc() {
		request.getSession().setAttribute("urlx", urlx);// 把前端传过来的urlx地址保存在session中
		String requestUrl1 = WeiXinUtil.ip + "WxPaygetAcce.action";
		String url = WeiXinUtil.ip1 + "WxPaygetToken.action?requestUrl1="
				+ requestUrl1;
		/*
		 * String requestUrl1 = http://192.168.0.104:8080/fuzhuang1/jsp/weixin/sys.jsp
		 * "http://localhost:8080/HappysSystem/WxPaygetAcce.action"; String url
		 * =http://www.0791youxi.com/ClothingWeChat/WxPaygetToken.action?requestUrl1=http://192.168.0.104:8080/fuzhuang1/jsp/weixin/sys.jsp
		 * "http://localhost:8080/GameWebsite/WxPaygetToken.action?requestUrl1="
		 * +requestUrl1;
		 */
		try {
			ServletActionContext.getResponse().sendRedirect(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 调用第三方接口后回调的地址方法
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getAcce() throws Exception {
		String yy = (String) request.getSession().getAttribute("urlx");
		Signature s = new Signature();
		String access_token = "";
		String ticket = "";

		access_token = request.getParameter("access_token");
		ticket = request.getParameter("ticket");
		String end_time = request.getParameter("end_time");

		// 为签名实体类的属性赋值 为生成签名做准备
		s.setJsapi_ticket(ticket);
		s.setNoncestr(PayWxUtil.getNonceStr()); // 获得随机字符串
		s.setTimeStamp(new Date().getTime() / 1000 + "");
		s.setUrl(WeiXinUtil.ip + yy);
		String str = "jsapi_ticket=" + s.getJsapi_ticket() + "&noncestr="
				+ s.getNoncestr() + "&timestamp=" + s.getTimeStamp() + "&url="
				+ s.getUrl();
		s.setSignature(PayWxUtil.getSha1(str)); // 访问接口获得的签名

		JSONObject json = new JSONObject();
		json.put("s", s);
		ResUtil.write(json, ServletActionContext.getResponse());

		return null;
	}

	// 员工在激活页面的激活方法
	public String register() throws IOException {
		User user = new User();
		String uopenid =null;
		user.setJob_number(job_number);
		user.setUser_account(user_account);
		User u = userService.findByAccountAndJob(user); // 通过手机号和工号查询有无员工
		if(u!=null){
			uopenid =u.getOpen_id();
			if(uopenid==null||uopenid.equals("")){
				u.setOpen_id("0");
			}
		}
		if (u != null && (u.getOpen_id().equals("0"))) {// 激活成功
			request.getSession().removeAttribute("erro");
			String openid = (String) request.getSession().getAttribute(
					"open_id");
			u.setOpen_id(openid);
			userService.update(u);// 绑定openid
			request.getSession().setAttribute("user", u);
			ServletActionContext.getResponse().sendRedirect(
					WeiXinUtil.ip + "jsp/weixin/sys.jsp");
			return null;
		} else {// 激活失败
			request.getSession().setAttribute("erro", "激活失败，手机号或者工号错误！");
			ServletActionContext.getResponse().sendRedirect(
					WeiXinUtil.ip + "jsp/weixin/register.jsp");
			return null;
		}
	}

	// 员工扫码根据包号查询包记录
	public String findByPack() throws Exception {
		JSONObject json = new JSONObject();
		// 根据包号查询包记录
		process_dimensio pack = codeService.findByPack(packe_num);
		json.put("pack", pack);
		if (biao == 1) {
			List<sub_data> sl = sub_dataService.findByPk(packe_num);// 根据包号查询提交记录
			json.put("subList", sl);
		} else {
			if (pack != null) {
				// 已完成工序列表集合
				List<Packe_technology> comList = packe_tService
						.findByPackTcom(packe_num);
				// 未完成工序列表集合
				List<Packe_technology> yetList = packe_tService
						.findByPackTyet(packe_num);
				json.put("comList", comList);
				json.put("yetList", yetList);
			}
		}
		ResUtil.write(json, ServletActionContext.getResponse());
		return null;
	}

	public String jump() {
		try {
			ServletActionContext.getResponse().sendRedirect(
					WeiXinUtil.ip + urlx);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 提交工序完成记录
	public String sub() throws IOException {
		JSONObject jsonObject = new JSONObject();
		DistributedLock loc = new DistributedLock(Service.pool);// 初始化redis连接池
		String indentifier = "";
		String yuan = "";
		User user = (User) request.getSession().getAttribute("user");
		//User user =new User();
		try { 
			indentifier = loc.lockWithTimeout(REDIS_LOCK, 5000, 1000);// 加入分布式锁
			if (!indentifier.equals("") || indentifier != null) {
				if (user == null) {
					if (biao == 3) {
						jsonObject.put("result", "用户名或密码过期，重新登录！");
						try {
							ResUtil.write(jsonObject,
									ServletActionContext.getResponse());
						} catch (Exception e) {
							e.printStackTrace();
						}
						return null;
					}
					ServletActionContext.getResponse().sendRedirect(
							WeiXinUtil.ip + "jsp/weixin/again.jsp");
					return null;
				}
				Packe_technology pt = null;
				List<Packe_technology> ptList = null;//多选
				if (biao == 3) {//多选
					// 根据订单号 和包号获取 包绑定工序表的 id
					ptList = packe_tService.getIds(packe_num, t_name);
					if (ptList == null) {
						if (biao == 3) {
							jsonObject.put("result", packe_num + "包不存在或者已被删除！");
							try {
								ResUtil.write(jsonObject,
										ServletActionContext.getResponse());
							} catch (Exception e) {
								e.printStackTrace();
							}
							return null;
						}
						yuan = packe_num + "包不存在或者已被删除！";
						yuan = URLEncoder.encode(yuan.toString(), "UTF-8");
						ServletActionContext.getResponse().sendRedirect(
								WeiXinUtil.ip + "jsp/weixin/erro.jsp?yuan="
										+ yuan);
						return null;
					} else {
						int pstate = 1;
						ids="";
						for (int i = 0; i < ptList.size(); i++) {
							if(ptList.get(i).getState()==2){
								pstate = 2;
								t_name = ptList.get(i).getTechnology_name();
								break;
							}
							if(!(ptList.get(i).getP_num().equals(place_num))){
								pstate = 3;
								break;
							}
							if(i<ptList.size()-1){
								ids+=ptList.get(i).getId()+",";
							}else {
								ids+=ptList.get(i).getId();
							}
						}
						if (pstate ==3) {
							if (biao == 3) {
								jsonObject.put("result","生产号不匹配，请先选择正确的生产号提交！");
								try {
									ResUtil.write(jsonObject,
											ServletActionContext.getResponse());
								} catch (Exception e) {
									e.printStackTrace();
								}
								return null;
							}
							yuan = "生产号不匹配，请先选择正确的生产号提交！";
							yuan = URLEncoder.encode(yuan.toString(), "UTF-8");
							ServletActionContext.getResponse().sendRedirect(
									WeiXinUtil.ip + "jsp/weixin/erro.jsp?yuan="
											+ yuan);
							return null;
						} else if (pstate == 2) {
							if (biao == 3) {
								jsonObject.put("result",packe_num + "包的" + t_name + "工序已经完成");
								try {
									ResUtil.write(jsonObject,
											ServletActionContext.getResponse());
								} catch (Exception e) {
									e.printStackTrace();
								}
								return null;
							}
							yuan = packe_num + "包的" + t_name + "工序已经完成";
							yuan = URLEncoder.encode(yuan.toString(), "UTF-8");
							ServletActionContext.getResponse().sendRedirect(
									WeiXinUtil.ip + "jsp/weixin/erro.jsp?yuan="
											+ yuan);
							return null;
						} 
					}
				}
				if (ids!=null && packe_num != null) {
					Lock s_lock = null;
					for (int i = 0; i < 6; i++) {
						if (lock.tryLock()) {
							try {
								s_lock = locks.get(ids);
								if (s_lock == null) {
									s_lock = new ReentrantLock();
									locks.put(ids.length(), s_lock);
								}
							} finally {
								lock.unlock();
							}
							break;
						}
						if (i == 5) {
							return null;
						}
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					if (s_lock.tryLock()) {
						try {
							if (biao != 3) {
								// id查询包邦定工序记录 pt
								ptList = packe_tService.findbByPt(ids);
							} else {
								process_dimensio pd = codeService
										.findByPack(packe_num);
								number = pd.getP_number();
							}
							String t_nams ="";//成功页面提示
							String nt_names ="";
							int states = 1;
							for (int i = 0; i < ptList.size(); i++) {
								if(i<ptList.size()-1){
									t_nams+=ptList.get(i).getTechnology_name()+"";
									nt_names+="'"+ptList.get(i).getTechnology_name()+"',";
								}else {
									t_nams+=ptList.get(i).getTechnology_name();
									nt_names+="'"+ptList.get(i).getTechnology_name()+"'";
								}
								states = ptList.get(i).getState();
							}
							
							t_nams = URLEncoder.encode(t_nams, "UTF-8");//成功页面提示
							if (ptList != null && states == 1) {
								int state = 1;
								// 根据订单号和包号以及工序号查询 提交记录表 里有没有这条提交记录
								List<sub_data> s = sub_dataService.findListByPnPkT(
										place_num, packe_num,
										nt_names);
								if (s == null) {//开始插入提交数据
									List<Integer> numxs = new ArrayList<Integer>();
									for (int i = 0; i < ptList.size(); i++) {
										sub_data sd = new sub_data(place_num,packe_num,number,ptList.get(i).getTechnology_name(),job_number,user_name);					
										SimpleDateFormat df = new SimpleDateFormat(
												"yyyy-MM-dd HH:mm:ss");// 设置日期格式
										String time = df.format(new Date());
										sd.setSub_time(time);
										// 设置这条记录的金额
										// 1.通过订单号和工序名称查询订单绑定工序表的单价
										BigDecimal price = place_tService
												.findByPandT(place_num,
														ptList.get(i).getTechnology_name());
										BigDecimal shuliang = new BigDecimal(number);
										BigDecimal money = shuliang.multiply(price);// 乘
										sd.setMoney(money);
										// 设置工序单价
										sd.setPrice(price);
										// 保存提交记录
										int numx = sub_dataService.addSub(sd);// 返回一个id
										numxs.add(numx);
									}
									
									if (numxs.size() == ptList.size()) {
										// 1.改变包号绑定工序表单的工序状态为2已完成
										state = 2;
										int num = packe_tService.updatePts(ids,
												state);
										if (num > 0) {
											// 2.改变包号表的已完成个数
											// 改变记录
											int num2 = codeService
													.updatePd(packe_num);
											if (num2 > 0) {
												// 所有操作都成功 返回提交成功信息
												if (biao == 3) {
													jsonObject.put("result",
															"ok");
													try {
														ResUtil.write(
																jsonObject,
																ServletActionContext
																		.getResponse());
													} catch (Exception e) {
														e.printStackTrace();
													}
													return null;
												} else {
													String place_num = URLEncoder.encode(
															this.place_num.toString(),
															"UTF-8");
													String packe_num = URLEncoder.encode(
															this.packe_num.toString(),
															"UTF-8");
													nt_names = URLEncoder.encode(
															nt_names.toString(),
															"UTF-8");
													ServletActionContext
															.getResponse()
															.sendRedirect(
																	WeiXinUtil.ip
																			+ "jsp/weixin/success.jsp#t_name="
																			+ nt_names
																			+ ";place_num="
																			+ place_num
																			+ ";packe_num="
																			+ packe_num);
													return null;
												}
											} else {
												yuan = "修改" + packe_num
														+ "包的完成进度失败！";
												yuan = URLEncoder.encode(
														yuan.toString(),
														"UTF-8");
												state = 1;
												packe_tService.updatePts(ids,
														state);// 操作失败
												// 把数据还原
												for (int i = 0; i <numxs.size(); i++) {
													sub_dataService.delete(numxs.get(i));// 修改失败
												}
												
												// 删除提交数据
												ServletActionContext
														.getResponse()
														.sendRedirect(
																WeiXinUtil.ip
																		+ "jsp/weixin/erro.jsp?yuan="
																		+ yuan);
												return null;
											}
										} else {
											yuan = "修改" + packe_num + "包的"
													+ t_name + "工序状态失败！";
											yuan = URLEncoder.encode(
													yuan.toString(), "UTF-8");
											for (int i = 0; i <numxs.size(); i++) {
												sub_dataService.delete(numxs.get(i));// 修改失败
											}// 删除提交数据
																			
											ServletActionContext
													.getResponse()
													.sendRedirect(
															WeiXinUtil.ip
																	+ "jsp/weixin/erro.jsp?yuan="
																	+ yuan);
											return null;
										}
									} else {
										yuan = "提交记录提交失败！";
										yuan = URLEncoder.encode(
												yuan.toString(), "UTF-8");
										ServletActionContext
												.getResponse()
												.sendRedirect(
														WeiXinUtil.ip
																+ "jsp/weixin/erro.jsp?yuan="
																+ yuan);
										return null;
									}
								} else {
									yuan = "提交记录提交失败！已经有人提交" + packe_num + "包的"
											+ t_name + "工序";
									yuan = URLEncoder.encode(yuan.toString(),
											"UTF-8");
									ServletActionContext
											.getResponse()
											.sendRedirect(
													WeiXinUtil.ip
															+ "jsp/weixin/erro.jsp?yuan="
															+ yuan);

									return null;
								}
							} else {
								yuan = packe_num + "包不存在已被删除或者" + t_name
										+ "工序已完成！";
								yuan = URLEncoder.encode(yuan.toString(),
										"UTF-8");
								ServletActionContext
										.getResponse()
										.sendRedirect(
												WeiXinUtil.ip
														+ "jsp/weixin/erro.jsp?yuan="
														+ yuan);

								return null;
							}
						} finally {
							s_lock.unlock();
						}
					}
					yuan = "提交" + packe_num + "包的" + t_name
							+ "工序时发生不可预知的错误，尽快联系开发人员修正，请谅解！";
					yuan = URLEncoder.encode(yuan.toString(), "UTF-8");
					ServletActionContext.getResponse().sendRedirect(
							WeiXinUtil.ip + "jsp/weixin/erro.jsp?yuan=" + yuan);
					return null;
				} else {
					yuan = "提交" + packe_num + "包的" + t_name
							+ "工序时发生不可预知的错误，尽快联系开发人员修正，请谅解！";
					yuan = URLEncoder.encode(yuan.toString(), "UTF-8");
					ServletActionContext.getResponse().sendRedirect(
							WeiXinUtil.ip + "jsp/weixin/erro.jsp?yuan=" + yuan);
					return null;
				}
			} else {
				yuan = "获取提交锁失败！请稍后再试！";
				yuan = URLEncoder.encode(yuan.toString(), "UTF-8");
				ServletActionContext.getResponse().sendRedirect(
						WeiXinUtil.ip + "jsp/weixin/erro.jsp?yuan=" + yuan);
			}
		} catch (Exception e) {
			System.out.println(e);
			// loc.releaseLock("resource", indentifier);//解锁
		} finally {
			loc.releaseLock(REDIS_LOCK, indentifier);// 解锁
		}
		return null;
	}
	
	public String subD(){
		JSONObject jsonObject = new JSONObject();
		DistributedLock loc = new DistributedLock(Service.pool);// 初始化redis连接池
		String indentifier = "";
		String yuan = "";
		User user = (User) request.getSession().getAttribute("user");
		//User user =new User();
		try { 
			indentifier = loc.lockWithTimeout(REDIS_LOCK, 5000, 1000);// 加入分布式锁
			if (!indentifier.equals("") || indentifier != null) {
				if (user == null) {
					if (biao == 3) {
						jsonObject.put("result", "用户名或密码过期，重新登录！");
						try {
							ResUtil.write(jsonObject,
									ServletActionContext.getResponse());
						} catch (Exception e) {
							e.printStackTrace();
						}
						return null;
					}
					ServletActionContext.getResponse().sendRedirect(
							WeiXinUtil.ip + "jsp/weixin/again.jsp");
					return null;
				}
				Packe_technology pt = null;
				List<Packe_technology> ptList = null;//多选
				if (biao == 3) {//多选
					// 根据订单号 和包号获取 包绑定工序表的 id
					ptList = packe_tService.getIds(packe_num, t_name);
					if (ptList == null) {
						if (biao == 3) {
							jsonObject.put("result", packe_num + "包不存在或者已被删除！");
							try {
								ResUtil.write(jsonObject,
										ServletActionContext.getResponse());
							} catch (Exception e) {
								e.printStackTrace();
							}
							return null;
						}
						yuan = packe_num + "包不存在或者已被删除！";
						yuan = URLEncoder.encode(yuan.toString(), "UTF-8");
						ServletActionContext.getResponse().sendRedirect(
								WeiXinUtil.ip + "jsp/weixin/erro.jsp?yuan="
										+ yuan);
						return null;
					} else {
						int pstate = 1;
						ids="";
						for (int i = 0; i < ptList.size(); i++) {
							if(ptList.get(i).getState()==2){
								pstate = 2;
								t_name = ptList.get(i).getTechnology_name();
								break;
							}
							if(!(ptList.get(i).getP_num().equals(place_num))){
								pstate = 3;
								break;
							}
							if(i<ptList.size()-1){
								ids+=ptList.get(i).getId()+",";
							}else {
								ids+=ptList.get(i).getId();
							}
						}
						if (pstate ==3) {
							if (biao == 3) {
								jsonObject.put("result","生产号不匹配，请先选择正确的生产号提交！");
								try {
									ResUtil.write(jsonObject,
											ServletActionContext.getResponse());
								} catch (Exception e) {
									e.printStackTrace();
								}
								return null;
							}
							yuan = "生产号不匹配，请先选择正确的生产号提交！";
							yuan = URLEncoder.encode(yuan.toString(), "UTF-8");
							ServletActionContext.getResponse().sendRedirect(
									WeiXinUtil.ip + "jsp/weixin/erro.jsp?yuan="
											+ yuan);
							return null;
						} else if (pstate == 2) {
							if (biao == 3) {
								jsonObject.put("result",packe_num + "包的" + t_name + "工序已经完成");
								try {
									ResUtil.write(jsonObject,
											ServletActionContext.getResponse());
								} catch (Exception e) {
									e.printStackTrace();
								}
								return null;
							}
							yuan = packe_num + "包的" + t_name + "工序已经完成";
							yuan = URLEncoder.encode(yuan.toString(), "UTF-8");
							ServletActionContext.getResponse().sendRedirect(
									WeiXinUtil.ip + "jsp/weixin/erro.jsp?yuan="
											+ yuan);
							return null;
						} 
					}
				}
				if (ids!=null && packe_num != null) {
					Lock s_lock = null;
					for (int i = 0; i < 6; i++) {
						if (lock.tryLock()) {
							try {
								s_lock = locks.get(ids);
								if (s_lock == null) {
									s_lock = new ReentrantLock();
									locks.put(ids.length(), s_lock);
								}
							} finally {
								lock.unlock();
							}
							break;
						}
						if (i == 5) {
							return null;
						}
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					if (s_lock.tryLock()) {
						try {
							if (biao != 3) {
								// id查询包邦定工序记录 pt
								ptList = packe_tService.findbByPt(ids);
							} else {
								process_dimensio pd = codeService
										.findByPack(packe_num);
								number = pd.getP_number();
							}
							String t_nams ="";//成功页面提示
							String nt_names ="";
							int states = 1;
							for (int i = 0; i < ptList.size(); i++) {
								if(i<ptList.size()-1){
									t_nams+=ptList.get(i).getTechnology_name()+"";
									nt_names+="'"+ptList.get(i).getTechnology_name()+"',";
								}else {
									t_nams+=ptList.get(i).getTechnology_name();
									nt_names+="'"+ptList.get(i).getTechnology_name()+"'";
								}
								states = ptList.get(i).getState();
							}
							
							t_nams = URLEncoder.encode(t_nams, "UTF-8");//成功页面提示
							if (ptList != null && states == 1) {
								int state = 1;
								// 根据订单号和包号以及工序号查询 提交记录表 里有没有这条提交记录
								List<sub_data> s = sub_dataService.findListByPnPkT(
										place_num, packe_num,
										nt_names);
								if (s == null) {//开始插入提交数据
									List<Integer> numxs = new ArrayList<Integer>();
									SimpleDateFormat df = new SimpleDateFormat(
											"yyyy-MM-dd HH:mm:ss");// 设置日期格式
									String time = df.format(new Date());
									for (int i = 0; i < ptList.size(); i++) {
										sub_data sd = new sub_data(place_num,packe_num,number,ptList.get(i).getTechnology_name(),job_number,user_name);										
										sd.setSub_time(time);
										// 设置这条记录的金额
										// 1.通过订单号和工序名称查询订单绑定工序表的单价
										BigDecimal price = place_tService
												.findByPandT(place_num,
														ptList.get(i).getTechnology_name());
										BigDecimal shuliang = new BigDecimal(number);
										BigDecimal money = shuliang.multiply(price);// 乘
										sd.setMoney(money);
										// 设置工序单价
										sd.setPrice(price);
										// 保存提交记录
										int numx = sub_dataService.addSub(sd);// 返回一个id
										numxs.add(numx);
									}
									String sql = "insert into sub_data (place_num,packe_num,t_name,job_number,user_name,sub_time,number) values";
									for (int i = 0; i < ptList.size(); i++) {
										if(i<ptList.size()-1){
											sql+="('"+ptList.get(i).getP_num()+"','"+ptList.get(i).getPacke_num()+"','"+"','"+ptList.get(i).getTechnology_name()+"','"+job_number+"','"+user_name+"','"+time+"',"+number+"),";
										}
										sql+="('"+ptList.get(i).getP_num()+"','"+ptList.get(i).getPacke_num()+"','"+"','"+ptList.get(i).getTechnology_name()+"','"+job_number+"','"+user_name+"','"+time+"',"+number+")";
									}
									
									if (numxs.size() == ptList.size()) {
										// 1.改变包号绑定工序表单的工序状态为2已完成
										state = 2;
										int num = packe_tService.updatePts(ids,
												state);
										if (num > 0) {
											// 2.改变包号表的已完成个数
											// 改变记录
											/*int num2 = codeService
													.updatePd(packe_num);
											if (num2 > 0) {*/
												// 所有操作都成功 返回提交成功信息
												if (biao == 3) {
													jsonObject.put("result",
															"ok");
													try {
														ResUtil.write(
																jsonObject,
																ServletActionContext
																		.getResponse());
													} catch (Exception e) {
														e.printStackTrace();
													}
													return null;
												} else {
													String place_num = URLEncoder.encode(
															this.place_num.toString(),
															"UTF-8");
													String packe_num = URLEncoder.encode(
															this.packe_num.toString(),
															"UTF-8");
													nt_names = URLEncoder.encode(
															nt_names.toString(),
															"UTF-8");
													ServletActionContext
															.getResponse()
															.sendRedirect(
																	WeiXinUtil.ip
																			+ "jsp/weixin/success.jsp#t_name="
																			+ nt_names
																			+ ";place_num="
																			+ place_num
																			+ ";packe_num="
																			+ packe_num);
													return null;
												}
											/*} else {
												yuan = "修改" + packe_num
														+ "包的完成进度失败！";
												yuan = URLEncoder.encode(
														yuan.toString(),
														"UTF-8");
												state = 1;
												packe_tService.updatePts(ids,
														state);// 操作失败
												// 把数据还原
												for (int i = 0; i <numxs.size(); i++) {
													sub_dataService.delete(numxs.get(i));// 修改失败
												}
												
												// 删除提交数据
												ServletActionContext
														.getResponse()
														.sendRedirect(
																WeiXinUtil.ip
																		+ "jsp/weixin/erro.jsp?yuan="
																		+ yuan);
												return null;
											}*/
										} else {
											yuan = "修改" + packe_num + "包的"
													+ t_name + "工序状态失败！";
											yuan = URLEncoder.encode(
													yuan.toString(), "UTF-8");
											/*for (int i = 0; i <numxs.size(); i++) {
												sub_dataService.delete(numxs.get(i));// 修改失败
											}*/// 删除提交数据
																			
											ServletActionContext
													.getResponse()
													.sendRedirect(
															WeiXinUtil.ip
																	+ "jsp/weixin/erro.jsp?yuan="
																	+ yuan);
											return null;
										}
									} else {
										yuan = "提交记录提交失败！";
										yuan = URLEncoder.encode(
												yuan.toString(), "UTF-8");
										ServletActionContext
												.getResponse()
												.sendRedirect(
														WeiXinUtil.ip
																+ "jsp/weixin/erro.jsp?yuan="
																+ yuan);
										return null;
									}
								} else {
									yuan = "提交记录提交失败！已经有人提交" + packe_num + "包的"
											+ t_name + "工序";
									yuan = URLEncoder.encode(yuan.toString(),
											"UTF-8");
									ServletActionContext
											.getResponse()
											.sendRedirect(
													WeiXinUtil.ip
															+ "jsp/weixin/erro.jsp?yuan="
															+ yuan);

									return null;
								}
							} else {
								yuan = packe_num + "包不存在已被删除或者" + t_name
										+ "工序已完成！";
								yuan = URLEncoder.encode(yuan.toString(),
										"UTF-8");
								ServletActionContext
										.getResponse()
										.sendRedirect(
												WeiXinUtil.ip
														+ "jsp/weixin/erro.jsp?yuan="
														+ yuan);

								return null;
							}
						} finally {
							s_lock.unlock();
						}
					}
					yuan = "提交" + packe_num + "包的" + t_name
							+ "工序时发生不可预知的错误，尽快联系开发人员修正，请谅解！";
					yuan = URLEncoder.encode(yuan.toString(), "UTF-8");
					ServletActionContext.getResponse().sendRedirect(
							WeiXinUtil.ip + "jsp/weixin/erro.jsp?yuan=" + yuan);
					return null;
				} else {
					yuan = "提交" + packe_num + "包的" + t_name
							+ "工序时发生不可预知的错误，尽快联系开发人员修正，请谅解！";
					yuan = URLEncoder.encode(yuan.toString(), "UTF-8");
					ServletActionContext.getResponse().sendRedirect(
							WeiXinUtil.ip + "jsp/weixin/erro.jsp?yuan=" + yuan);
					return null;
				}
			} else {
				yuan = "获取提交锁失败！请稍后再试！";
				yuan = URLEncoder.encode(yuan.toString(), "UTF-8");
				ServletActionContext.getResponse().sendRedirect(
						WeiXinUtil.ip + "jsp/weixin/erro.jsp?yuan=" + yuan);
			}
		} catch (Exception e) {
			System.out.println(e);
			// loc.releaseLock("resource", indentifier);//解锁
		} finally {
			loc.releaseLock(REDIS_LOCK, indentifier);// 解锁
		}
		return null;
	}
	public String getSelect() throws Exception {
		JSONObject json = new JSONObject();
		if (biao == 3) {// 手机端订单进度查询页面的订单下拉框实现
			// 查询出所有订单的订单号
			List<place> pList = placeService.getAllpnum();
			json.put("pList", pList);
			ResUtil.write(json, ServletActionContext.getResponse());
			return null;
		}
		if (biao != 2) {
			// 查询出所有订单的订单号
			List<place> pList = placeService.getAllpnum();
			json.put("pList", pList);
		}
		// 根据订单号查询 包绑定的工序
		List<place_technology> ptList = place_tService.getAllpt(place_num);
		json.put("ptList", ptList);
		ResUtil.write(json, ServletActionContext.getResponse());
		return null;
	}

}
