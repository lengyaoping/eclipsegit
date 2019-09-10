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
<!-- 导入jqueryWeui样式 -->
<link href="../../css/h5progress/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="../../css/h5progress/demo.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-2.1.4.js"></script>
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
	    width:98%;
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
	.weui-tabbar{
		position: fixed;
	}
</style>
</head>
<script>
var place_num = decodeURI(request("place_num"));
	$(function(){var url=window.location.href;
console.info(url);
		var pnum= url.substring(url.indexOf("place_num=") + 10,url.indexOf("#tab"));
console.info(pnum);
	$("#dd").html("订单号 : "+place_num);
		changeparent(place_num);
	});
	//获取传入id
   function request(paras) {
            var url = location.href;
            var paraString = url.substring(url.indexOf("?") + 1, url.length).split("&")||url.substring(url.indexOf("?") + 1, url.length).split("#");
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
</script>
<body>
<div class="demo">
	<div class="container">
		<div class="row">
		
			<div class="weui-cell weui-cell_select weui-cell_select-after">
		        <div class="weui-cell__hd" style="width:100%">
		          <label for="" id ="dd" class="weui-label" style="width:90%">订单:</label>
		        </div>
		       
	      	</div>
			
			<div id="demo" class="col-md-offset-3 col-md-6">
				
			</div>
			<div id="demo2" style="width:100%;height:61px;" class="col-md-offset-3 col-md-6">
				
			</div>
		</div>
	</div>
</div>
<div class="weui-tabbar">
        <a onclick="checkH(1)" href="#tab1" class="weui-tabbar__item weui-bar__item--on">
          <!--<span class="weui-badge" style="position: absolute;top: -.4em;right: 1em;">8</span>-->
          <div class="weui-tabbar__icon">
            <!-- <img src="./images/icon_nav_button.png" alt="">-->
          </div>
          <p class="weui-tabbar__label">工序</p>
        </a>
        <a onclick="checkH(2)" href="#tab2" class="weui-tabbar__item">
          <div class="weui-tabbar__icon">
            <!--  <img src="./images/icon_nav_msg.png" alt="">-->
          </div>
          <p class="weui-tabbar__label">颜色尺码</p>
        </a>
        
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
	.le{
		display:block;
		width:40%;
		float:left;
	}
	.ce{
		display:block;
		width:30%;
		float:left;
	}
	.right{
		display:block;
		float:right;
	}
	.sppp{
		display:block;
		background: #f2f2f2;
		width:100%;
		height: 25px;
		text-align: center;
		line-height: 25px;
	}
    </style>
<!-- jqueryWeui样式 -->
<script src="https://cdn.bootcss.com/jquery/1.11.0/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/jquery-weui/1.2.0/js/jquery-weui.min.js"></script>
<!-- jqueryWeui样式 -->
<!-- 如果使用了某些拓展插件还需要额外的JS -->
<script src="https://cdn.bootcss.com/jquery-weui/1.2.0/js/swiper.min.js"></script>
<script src="https://cdn.bootcss.com/jquery-weui/1.2.0/js/city-picker.min.js"></script>
<script type="text/javascript">
	function checkH(s){
		console.info(s);console.info(place_num);
		if(s==1){
			changeparent(place_num);
		}else{
			$.ajax({
				url:"placefindListBySizeAndColor.action?biao=2",
				type:"post",
				data:{plan_num:place_num},
				dataType:"json",
				success:function(data){
					console.log(data);
					var daima="";
					var tList = data.list;
					if(tList){
						daima +="<p class='weui-media-box__desc sppp'>"+tList[0].cylinder+"</p>";
						daima += "<a  href='javascript:void(0);' class='weui-media-box weui-media-box_appmsg'><div class='weui-media-box__bd'><h5 class='weui-media-box__title'>"
						+"<span class='le'>"+tList[0].p_color+"</span> <span class='ce'>"+tList[0].p_size+" </span><span class='ri'>"+tList[0].p_number+"</span></h5></div></a>";
					for(var i=1;i<tList.length;i++){
						var t = tList[i];
						if(t.cylinder!=tList[i-1].cylinder){
							daima +="<p class='weui-media-box__desc sppp'>"+t.cylinder+"</p>";
							daima += "<a  href='javascript:void(0);' class='weui-media-box weui-media-box_appmsg'><div class='weui-media-box__bd'><h5 class='weui-media-box__title'><span class='le'>"+t.p_color+"</span> <span class='ce'>"+t.p_size+" </span><span class='ri'>"+t.p_number+"</span></h5></div></a>";
						}else{
							daima += "<a  href='javascript:void(0);' class='weui-media-box weui-media-box_appmsg'><div class='weui-media-box__bd'><h5 class='weui-media-box__title'><span class='le'>"+t.p_color+"</span> <span class='ce'>"+t.p_size+" </span><span class='ri'>"+t.p_number+"</span></h5></div></a>";
						}
					}
					}else{
						alert("<font color='red'>此订单还没有提交数据哦！</font>");
					}
					
					document.getElementById("demo").innerHTML=daima;
				}
			});
		}
		
	}
	function changeparent(v){
	//alert(v);placefindListBySizeAndColor.action?plan_num='+place
		if(v===1){

			return;
		}
		
		$.ajax({
			url:"placeplaceSchedule.action?place="+v,
			type:"post",
			data:{biao:1},
			dataType:"json",
			success:function(data){
				console.log(data);
				var daima="";
				var tList = data.list;
				for(var i=0;i<tList.length;i++){
					var t = tList[i];
					var jindu = (Number(t.completed)/(Number(t.completed)+Number(t.unfinished))).toFixed(4)*100;
					jindu = jindu.toFixed(2);
					daima+="<h3 class=\"progress-title\">"+t.t_name+"-共完成了"+t.cp_piece_num +"件</h3>";
					daima+="<div class=\"progress\">";
					daima+="<div class=\"progress-bar\" style=\"width:"+jindu+"%;\">";
					daima+="<div class=\"progress-value\">"+jindu+"%</div>";
					daima+="</div></div>";
				}
				document.getElementById("demo").innerHTML=daima;
			}
		});
	}
</script>

</body>
</html>
