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
    
    <title>工票查询</title>
      <meta name="viewport" content="width=device-width,minimum-scale=1,maximum-scale=1,initial-scale=1,user-scalable=no" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script> 
	<!-- 导入jqueryWeui样式 
	<link rel="stylesheet" href="https://cdn.bootcss.com/weui/1.1.2/style/weui.min.css">
	<link rel="stylesheet" href="https://cdn.bootcss.com/jquery-weui/1.2.0/css/jquery-weui.min.css">-->
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-2.1.4.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/jsp/js/jqueryWeui/weui.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/jsp/js/jqueryWeui/jquery-weui.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/jsp/js/jqueryWeui/demos.css">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<!-- 导入jqueryWeui样式 -->
  </head>
  <style>
  	body{background-color:#eee;font-size: 17px; }
	#tou{background-color:#fff; width: 100%; text-align: left; height:45px;line-height: 40px; padding-top: 6px;}
	.context{background-color: #fff; margin-top: 20px; text-align: center; padding: 20px 0 10px;}
	.shuju{margin: 0 auto; margin-top: 15px; width: 95%; text-align: center; font-size: 14px;}
	.shuju tr{height:35px;}
	.shuju th{background-color: #eee; font-size: 16px;}
	.spt{
		padding:0px;
        line-height:1.5;	
	}
	#chaxun a{
		margin:0 20 0 10px;
	}
  </style>
  <body>
  	<div class="weui-flex" id="tou" >
	  <c:if test="${user.user_level != 2 }">
			<div class="weui-flex__item">&nbsp;普通用户:${user.user_name }</div>
		</c:if>
		<c:if test="${user.user_level == 2 }">
			<div class="weui-flex__item" id="gly">&nbsp;管理员:${user.user_name }</div>
		</c:if>
	  <div class="weui-flex__item" id="ghl">&nbsp;工号:${user.job_number }</div>
	  <input type="hidden" id="gonghao" value="${user.job_number }">
	</div>
	<!-- <div><font color="red">注：只能查询登录本人自己的工票</font></div> -->
	
	<div class="context">
		<!-- <table style="margin: 0 auto; width: 75%; height: 40px; text-align: center;" border="1" cellspacing="0" bordercolor="#ccc">
			<tr>
				<td><input type="radio" name="time" value="1" onclick="chaxun(1)">当天工票</td>
				<td><input type="radio" name="time" value="2" onclick="chaxun(2)">本周工票</td>
				<td><input type="radio" name="time" value="3" onclick="chaxun(3)">本月工票</td>
			</tr>
		</table> -->
			<div class="weui-cell" style="background-color: #eee; margin-bottom: 10px;">
			   <!-- -->
			    <div class="weui-cell__bd" >
			    <div class="weui-cell__hd"><label for="" class="weui-label">开始日期</label></div>
			      <input class="weui-input"  id="time" type="date" value="">
			    </div> 
			    <div class="weui-cell__bd">
			    <div class="weui-cell__hd"><label for="" class="weui-label">结束日期</label></div>
			      <input class="weui-input"  id="timed" type="date" value="">
			    </div>
		    </div>
		  <div id="chaxun">
		  	<a href="javascript:chaxun(2);" class="weui-btn weui-btn_mini weui-btn_primary" >普通查询</a>
		  	<a href="javascript:chaxun(1);" class="weui-btn weui-btn_mini weui-btn_primary">汇总查询</a>
		  </div>
		  
		<div style="margin-top: 10px;">工票张数：<span id="zhang">0</span>，合计包数：<span id="bao">0</span>，合计件数：<span id="jiang">0</span></div>
		<div id="biaoge"></div>
	</div>
	
    
	<!-- jqueryWeui样式 
	<script src="https://cdn.bootcss.com/jquery/1.11.0/jquery.min.js"></script>
	<script src="https://cdn.bootcss.com/jquery-weui/1.2.0/js/jquery-weui.min.js"></script>-->
	<!-- jqueryWeui样式 -->
	<!-- 如果使用了某些拓展插件还需要额外的JS
	<script src="https://cdn.bootcss.com/jquery-weui/1.2.0/js/swiper.min.js"></script>
	<script src="https://cdn.bootcss.com/jquery-weui/1.2.0/js/city-picker.min.js"></script> -->

  	<script type="text/javascript">
  	window.onload=function(){

		var username = sessionStorage.getItem("ur");
		var jobnumber = sessionStorage.getItem("gh");
		//alert(username+""+jobnumber );
		if(username!=null&&jobnumber!=null){
		document.getElementById("gly").innerHTML=" 管理员:"+username;	
		document.getElementById("ghl").innerHTML="工号:"+jobnumber;	
		}
  		var date = new Date();
        var seperator1 = "-";
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = year + seperator1 + month + seperator1 + strDate;
		$("#time").val(currentdate);
		$("#timed").val(currentdate);
  	};
    function chaxun(event){
   
    	var job_number = sessionStorage.getItem("gh");
    	
    	var data = $("#time").val();
    	var time2 = $("#timed").val();
		if(job_number==null){
			job_number= document.getElementById("gonghao").value;
		}
    	
    	if(data == undefined || data == "" ){
    		alert("请选择日期再查询！", "警告！");
    		if(time2 == undefined || data == "" ){
    			alert("请选择结束日期再查询！", "警告！");
    		}
    	}else{
    		/* job_number="9528"; */
    		document.getElementById("chaxun").innerHTML="<a href=\"javascript:;\" class=\"weui-btn weui-btn_mini weui-btn_primary weui-btn_loading\">正在加载...</a>";
			//job_number="QT19041281";
			$.ajax({
				url:"subgetInfo.action",
				type:"post",
				data:{job_number:job_number,time1:data,time2:time2,biao:event},
				dataType:"json",
				success:function(data){
					/* console.log(data.data); */
					if(data.data  == undefined){
						alert("你当日未提交工序！");
						document.getElementById("biaoge").innerHTML="";
						document.getElementById("zhang").innerHTML="0";
					    document.getElementById("bao").innerHTML="0";
					    document.getElementById("jiang").innerHTML="0";
						document.getElementById("chaxun").innerHTML="<a href=\"javascript:chaxun(2);\" class=\"weui-btn weui-btn_mini weui-btn_primary\">普通查询</a><a href=\"javascript:chaxun(1);\" class=\"weui-btn weui-btn_mini weui-btn_primary\">汇总查询</a>";
					}else{
						//console.info(data.data);//var shuju=data.data;
						var shuju = data.data;
						var daima="";
						var gnumber = 0;
					if(event==1){
						daima="<table class=\"shuju\"  border=\"1\" cellspacing=\"0\" bordercolor=\"#ccc\"><tr class=\"diyi\"><th>生产号</th><th>款号</th><th>工序名称</th><th>工票数</th><th>数量</th></tr>";
						for(var i=0;i<shuju.length;i++){
							
							daima += "<td>"+shuju[i].place_num+"</td>";
							daima += "<td>"+shuju[i].user_name+"</td>";
							gnumber+=parseInt(shuju[i].packe_num);
							//znum+=shuju[i].number;
							daima += "<td>"+shuju[i].t_name+"</td>";
							daima += "<td>"+shuju[i].packe_num+"</td>";
							daima += "<td>"+shuju[i].number+"</td></tr>";
						}
						daima += "</table><h4 style='margin-top:10px;'>汇总表</h4>";
						var map = data.map;
						daima+="<table class=\"shuju\"  border=\"1\" cellspacing=\"0\" bordercolor=\"#ccc\"><tr class=\"diyi\"><th>生产号</th><th>款号</th><th>工票数</th><th>数量</th></tr>";
						for(var key in map){
							daima += "<td>"+key+"</td>";
							daima += "<td>"+map[key].color+"</td>";
							daima += "<td>"+map[key].size+"</td>";
							daima += "<td>"+map[key].id+"</td></tr>";
						}daima += "</table>";
						}else{
							daima="<table class=\"shuju\"  border=\"1\" cellspacing=\"0\" bordercolor=\"#ccc\"><tr class=\"diyi\"><th>生产号</th><th>工序</th><th>包号</th><th>数量</th><th>时间</th></tr>";
							var listt= data.data;
							for(var i=0;i<listt.length;i++){
								daima += "<td>"+listt[i].place_num+"</td>";
								daima += "<td>"+listt[i].t_name+"</td>";
								var paknum = listt[i].packe_num.substring(listt[i].packe_num.length-4);
								daima += "<td>"+paknum+"</td>";
								daima += "<td>"+listt[i].number+"</td>";
								var sub_time = listt[i].sub_time.slice(0,-3);
								daima += "<td>"+sub_time+"</td></tr>";
							}
							gnumber = listt.length;
							daima += "</table>";
						}
						
						document.getElementById("biaoge").innerHTML=daima;
						document.getElementById("zhang").innerHTML=gnumber;
						document.getElementById("bao").innerHTML=gnumber;
						document.getElementById("jiang").innerHTML=data.bao;
					    document.getElementById("chaxun").innerHTML="<a href=\"javascript:chaxun(2);\" class=\"weui-btn weui-btn_mini weui-btn_primary\">普通查询</a><a href=\"javascript:chaxun(1);\" class=\"weui-btn weui-btn_mini weui-btn_primary\">汇总查询</a>";
					}
				}
			});
		}
	}
	function shengqing(id){
		if(confirm("您确认申请作废吗，申请了就不可更改了？")){
			$.ajax({
							url:"subtovoid.action?id="+id,
							type:"post",
							dataType:"json",
							success:function(data){
								if(data.ok == undefined){
									alert("申请提交失败！");
								}else{
									alert("申请提交成功，待管理员确认！");
									var oo="zuo"+id;
									document.getElementById(oo).innerHTML="<font color=\"red\">待批准</font>";
								}
							}
						});return;
		}
		return false;
	   
	    /* $.confirm("您确认申请作废吗，申请了就不可更改了?", "确认申请?", function() {
	     	$.ajax({
					url:"subtovoid.action?id="+id,
					type:"post",
					dataType:"json",
					success:function(data){
						if(data.ok == undefined){
							$.toast("申请提交失败！");
						}else{
							$.toast("申请提交成功，待管理员确认！");
							var oo="zuo"+id;
							document.getElementById(oo).innerHTML="<font color=\"red\">待批准</font>";
						}
					}
				});
	          
	        }, function() {
	          $.toast("已取消申请!", 'cancel');
	        });*/
		
	}
	</script>
  </body>
  
</html>
