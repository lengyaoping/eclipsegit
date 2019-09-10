<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>返工</title>
      <meta name="viewport" content="width=device-width,minimum-scale=1,maximum-scale=1,initial-scale=1,user-scalable=no" />
	<meta http-equiv="Access-Control-Allow-Origin" content="*">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>  <!-- 调用微信js接口需要 -->
	<!-- 导入jqueryWeui样式 -->
	<link rel="stylesheet" href="${pageContext.request.contextPath }/jsp/js/jqueryWeui/weui.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/jsp/js/jqueryWeui/jquery-weui.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/jsp/js/jqueryWeui/demos.css">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<!-- 导入jqueryWeui样式 -->
<%@include file="global.jsp" %>
  </head>
  <style>
  	body{background-color:#eee;font-size: 17px; }
	#tou{background-color:#fff; width: 100%; text-align: left; height:45px;line-height: 40px; padding-top: 6px;}
	.context{background-color: #fff; margin-top: 20px; padding: 20px 0 10px;}
	#shuju{margin: 0 auto; margin-top: 15px; width: 95%; text-align: center; font-size: 14px;}
	#shuju tr{height:35px;}
	#shuju th{background-color: #eee; font-size: 15px;}
	#biaoge{font-size: 14px;}
	#caozuo{width: 30px;}
  </style>
  <body>
  	<div class="weui-flex" id="tou" >
		<c:if test="${user.user_level == 1 }">
			<div class="weui-flex__item">&nbsp;普通用户:${user.user_name }</div>
		</c:if>
		<c:if test="${user.user_level == 2 }">
			<div class="weui-flex__item">&nbsp;管理员:${user.user_name }</div>
		</c:if>
	  <div class="weui-flex__item">&nbsp;工号:${user.job_number }</div>
	  <a href="javascript:;" id="updatePacke" class="weui-btn weui-btn_primary">扫码</a>
	  <input type="hidden" id="gonghao" value="${user.job_number }">
	</div>
	<!-- <div><font color="red">注：只能查询登录本人自己的工票</font></div> -->
	<div class="demo">
	<div class="container">
		<div class="row">
		
			<div class="weui-cell weui-cell_select weui-cell_select-after">
		        <div class="weui-cell__hd">
		          <label for="" class="weui-label">选择订单</label>
		        </div>
		        <div class="weui-cell__bd">
		          <select class="weui-select" name="place_num" id="select1" onchange="getpackList(this.value)">
		          </select>
		        </div>
	      	</div>
			<div class="weui-cell weui-cell_select weui-cell_select-after">
		        <div class="weui-cell__hd">
		          <label for="" class="weui-label">选择包号</label>
		        </div>
		        <div class="weui-cell__bd">
		          <select class="weui-select" name="place_num" id="select2" onchange="changeparent(this.value)">
		          </select>
		        </div>
	      	</div>
	      	<!--  <div class="weui-cell weui-cell_select weui-cell_select-after">
		        <div class="weui-cell__hd">
		          <label for="" class="weui-label">选择员工</label>
		        </div>
		        <div class="weui-cell__bd">
		          <select class="weui-select" name="place_num" id="select3" onchange="changeparent(this.value)">
		          <option value="1"></option></select>
		        </div>
	      	</div>-->
			<div id="demo" class="col-md-offset-3 col-md-6">
				
			</div>
		</div>
	</div>
</div>
	
	<div class="context">
		
		<div id="biaoge"></div>
	</div>
	
    
	<!-- jqueryWeui样式 -->
	<script src="https://cdn.bootcss.com/jquery/1.11.0/jquery.min.js"></script>
	<script src="https://cdn.bootcss.com/jquery-weui/1.2.0/js/jquery-weui.min.js"></script>
	<!-- jqueryWeui样式 -->
	<!-- 如果使用了某些拓展插件还需要额外的JS -->
	<script src="https://cdn.bootcss.com/jquery-weui/1.2.0/js/swiper.min.js"></script>
	<script src="https://cdn.bootcss.com/jquery-weui/1.2.0/js/city-picker.min.js"></script>
  	<script type="text/javascript">
  
  	$(function (){
		//初始化订单列表
		$.ajax({
			url:"WxPaygetSelect.action",
			type:"post",
			data:{biao:3},
			dataType:"json",
			success:function(data){
				//console.log(data);
				var pList = data.pList;
				$("#select1").html("");
				var parents = $("#select1");
				parents.append("<option value=\"1\">请选择</option>");
				for(var i=0;i<pList.length;i++){
					//alert(pList[i].plan_num);
					parents.append("<option value=\""+pList[i].plan_num+"\">"+pList[i].plan_num+"</option>");
				}
			}
		});
		
		
	});
	
	$(document).ready(function() {
		$.ajax({
			url:"WxPaygetAcc.action",
			dataType:"json",
			type:"post",
			data:{urlx:"jsp/weixin/demo.jsp"},
			async : false,//同步方式 
			success:function(data){
				//console.info(data);
				var timeStamp = data.s.timeStamp;
			    var nonceStr = data.s.noncestr;
			    var signature = data.s.signature;
				wx.config({
				    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
				    appId: appid, // 必填，公众号的唯一标识
				    timestamp:timeStamp, // 必填，生成签名的时间戳
				    nonceStr: nonceStr, // 必填，生成签名的随机串
				    signature: signature,// 必填，签名，见附录1
				    jsApiList: [ 'checkJsApi', 
			                        'scanQRCode'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
				});
				
				wx.ready(function(){
	                		wx.checkJsApi({  
			            jsApiList : ['scanQRCode'],  
			            success : function(res) {  
			            }  
			        }),
			        
			        
			        document.querySelector('#updatePacke').onclick = function() { 
			       
			            wx.scanQRCode({  
			                needResult : 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，  
			                scanType : [ "qrCode", "barCode" ], // 可以指定扫二维码还是一维码，默认二者都有  
			                success : function(res) {  
			                    var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果 
			                    
			                     window.location.href=host+"jsp/weixin/adminQery.jsp?packe_num="+result;
			                }
			                  
			            });  
			        };
			     });
			}
			
			
		});
	
	});
	//获取传入id
   function request(paras) {
            var url = location.href;
            var paraString = url.substring(url.indexOf("?") + 1, url.length).split("&");
            var paraObj = { };
            for (var i = 0; j = paraString[i]; i++) {
                paraObj[j.substring(0, j.indexOf("=")).toLowerCase()] = j.substring(j.indexOf("=") + 1, j.length);
            }
            var returnValue = paraObj[paras.toLowerCase()]; 
            if (typeof (returnValue) == "undefined") {
                return "";
            } else {
                return returnValue;
            }
    }
    
    function getpackList(p){
    	console.info(p);
    	$.ajax({
			url:"subgetPackListByPlace.action",
			type:"post",
			data:{place_num:p},
			dataType:"json",
			success:function(data){
				console.log(data);
				var pList = data.packList;
				$("#select2").html("");
				var parents = $("#select2");
				parents.append("<option value=\"1\">请选择</option>");
				for(var i=0;i<pList.length;i++){
					//alert(pList[i].plan_num);
					parents.append("<option value=\""+pList[i].packe_num+"\">"+pList[i].packe_num+"</option>");
				}
			}
		});
    }
  	function changeparent(s){
  		var placen=$("#select1 option:selected").val();
  		var usern=$("#select2 option:selected").val();
  		//console.info(usern);
  		//console.info(placen);
  		changeV(placen,usern,null);
  	}
  	function changeV(p,j,s){
	  	if(p==1){
	  		p="";
	  	}
	  	if(j==1){
	  		j="";
	  	}
	  	
	  	$.ajax({
	  			url:"subgetList.action",
	  			type:"POST",
	  			dataType:"json",
	  			data:{place_num:p,packe_num:j},
	  			success:function(data){
	  				var subList =data.slist;
	  				if(subList==undefined){
	  					subList = new Array();
	  				}
	  				console.info(data);
	  				var daima="<table id=\"shuju\"  border=\"1\" cellspacing=\"0\" bordercolor=\"#ccc\"><tr><td colspan=\"6\">该订单提交列表：<span id=\"count\"></span> 条</td></tr><tr class=\"diyi\"><th>时间</th><th>工序名称</th><th>包号</th><th>提交人</th><th>状态</th><th>操作</th></tr>";
					for(var i=0;i<subList.length;i++){
						var a = subList[i].sub_time;
						var b = subList[i].place_num;
						var c = subList[i].packe_num;
						daima += "<tr><td>"+a.substring(11)+"</td>";
						var attr = JSON.stringify(subList[i]);
						//console.info(escape(attr));
						//href=\"javascript:pinfo("+escape(attr)+")\"
						daima += "<td>"+subList[i].t_name+"</td>";
						//daima += "<td>"+subList[i].job_number+"</td>";
						daima += "<td><a href=\"javascript:pinfo("+escape(attr)+")\">"+subList[i].packe_num+"</a></td>";
						daima += "<td>"+subList[i].user_name+"</td>";
						if(subList[i].state == 1){
							daima += "<td><font>正常</font></td>";
						}else if(subList[i].state == 3){
							daima += "<td><font>已结算</font></td>";
						}else{
							daima += "<td><font color=\"red\">待批准</font></td>";
						}
						daima += "<td id=\"zuo"+subList[i].id+"\"><a href=\"javascript:fangong("+subList[i].id+")\" class=\"weui-btn weui-btn_mini weui-btn_plain-primary\">返工</a></td></tr>";
										
					}
						daima += "</table>";
						document.getElementById("biaoge").innerHTML=daima;
						document.getElementById("count").innerHTML=subList.length;
						
				}
	  		
	  		});
	  		
	  		
  	}
  	
  	function pinfo(data){
  		//console.info(data);
  		var packnum = data.packe_num.substr(data.packe_num.length-4);
  		var info = "订单号："+data.place_num+" 包号："+packnum+"<br>提交人:"+unescape(data.user_name)+ "<br>工号:"+unescape(data.job_number);
  		info+="<br>工序："+unescape(data.t_name) +" 件数："+data.number;
  		$.alert(info,"提交详情");
  		//var attr = JSON.parse(data); 
  		//console.info(attr);   // 将获取的数据重新转换成json数据
        //var params = unescape(data);    // 进行解码
        //var attr = JSON.parse(params); 
        //console.log(params);
  	}
	function fangong(id){
		$.confirm("您确认返工吗，确定了就不可更改了?", "确认返工?", function() {
			$.ajax({
				url:"subrecover.action?id="+id+"&biao=1",
				type:"post",
				dataType:"json",
				success:function(data){
					if(data.fangong == undefined){
						$.toast('返工失败！', 'cancel');
					}else{
						$.toast('返工成功!');
						var oo="zuo"+id;
						document.getElementById(oo).parentNode.remove();
						var count=document.getElementById("count").innerHTML;
						document.getElementById("count").innerHTML=count-1;
					}
				}
			});
        }, function() {
          	$.toast('你取消了返工！', 'cancel');
        });
		
	}
	</script>
  </body>
  
</html>
