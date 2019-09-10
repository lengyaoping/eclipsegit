<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<!-- saved from url=(0058)http://www.17sucai.com/preview/11/2017-06-18/21/index.html -->
<html lang="zh">
<head>
<meta name="viewport" content="width=device-width,minimum-scale=1,maximum-scale=1,initial-scale=1,user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes">
<title>订单进度</title>
<!-- 导入jqueryWeui样式 -->
<link rel="stylesheet" href="https://cdn.bootcss.com/weui/1.1.2/style/weui.min.css">
<link rel="stylesheet" href="https://cdn.bootcss.com/jquery-weui/1.2.0/css/jquery-weui.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-2.1.4.js"></script>
<!-- 导入jqueryWeui样式 -->
<link href="../../css/h5progress/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="../../css/h5progress/demo.css">
<style type="text/css">
	.progress-title{
	    font-size: 14px;
	    font-weight: 500;
	    color: #333;
	    margin: 0 0 13px;
	}
	.progress{
	    height: 10px;
	    background: #333;
	    border-radius: 0;
	    box-shadow: none;
	    margin-bottom: 5px;
	    overflow: visible;
	}
	.progress .progress-bar{
	    position: relative;
	    -webkit-animation: animate-positive 2s;
	    animation: animate-positive 2s;
	}
	.progress .progress-bar:after{
	    content: "";
	    display: inline-block;
	    width: 9px;
	    background: #fff;
	    position: absolute;
	    top: -4px;
	    bottom: -5px;
	    right: -5px;
	    z-index: 1;
	    transform: rotate(35deg);
	}
	.progress .progress-value{
	    display: block;
	    font-size: 14px;
	    font-weight: 400;
	    color: #333;
	    position: absolute;
	    top: -18px;
	    right: -35px;
	}
	@-webkit-keyframes animate-positive{
	    0%{ width: 0; }
	}
	@keyframes animate-positive {
	    0%{ width: 0; }
	}
	.center{
		padding-left:10px;
	}
</style>
</head>
<script type="text/javascript">
	$(function(){
		//初始化订单列表
		$.ajax({
			url:"WxPaygetSelect.action",
			type:"post",
			data:{biao:3},
			dataType:"json",
			success:function(data){
				var pList = data.pList;
var htm="";
				$("#select1").html("");
				for(var i=0;i<pList.length;i++){
//console.info(pList[i].plan_num);
					htm += "<a onclick='changeparent(\""+pList[i].plan_num+"\")' href='javascript:void(0);' class='weui-media-box weui-media-box_appmsg'><div class='weui-media-box__bd'><h4 class='weui-media-box__title center'>"+pList[i].plan_num+"</h4><span class='weui-media-box__desc'>下单日期："+pList[i].place_time+" 订单数量："+pList[i].number+"</span></div></a>";
				}
				console.info(htt);
				$("#htt").html(htm);
			}
		});
		
		/* var daima="";
		daima+="<h3 class=\"progress-title\">HTML5</h3>";
		daima+="<div class=\"progress\">";
		daima+="<div class=\"progress-bar\" style=\"width:10%;\" class=\"yanse2\">";
		daima+="<div class=\"progress-value\">10%</div>";
		daima+="</div></div>";
		document.getElementById("demo").innerHTML=daima; */
	});
	
	function changeparent(v){
		window.location.href="userSwitch.jsp?place_num="+v;
	
	}
</script>

<body ontouchstart>
    <div class="page__pd">
      <!--  <div class="weui-search-bar" id="searchBar">
        <form class="weui-search-bar__form" action="#">
          <div class="weui-search-bar__box">
            <i class="weui-icon-search"></i>
            <input type="search" class="weui-search-bar__input" id="searchInput" placeholder="搜索" required="">
            <a href="javascript:" class="weui-icon-clear" id="searchClear"></a>
          </div>
          <label class="weui-search-bar__label" id="searchText" style="transform-origin: 0px 0px 0px; opacity: 1; transform: scale(1, 1);">
            <i class="weui-icon-search"></i>
            <span>搜索</span>
          </label>
        </form>
        <a href="javascript:" class="weui-search-bar__cancel-btn" id="searchCancel">取消</a>
      </div>-->
      <div class="weui-panel weui-panel_access">
        <div class="weui-panel__bd" id="htt">          
         
          
          
        </div>
      </div>
    </div>
    <style>
      .weui-panel {
        margin: 0;
      }
      .weui-media-box {
        padding: 8px 15px;
      }
      .weui-panel__bd .weui-media-box__hd {
        width: 50px;
        height: 50px;
        line-height: 50px;
        position: relative;
      }
      .weui-media-box__desc {
        -webkit-line-clamp: 1;
      }
      .weui-media-box__title {
        margin-top: -4px;
      }
    </style>
    <script src="../lib/jquery-2.1.4.js"></script>
<script src="../lib/fastclick.js"></script>
<script src="../js/jquery-weui.js"></script>

  </body>
</html>
