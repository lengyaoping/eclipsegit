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
    
    <title>修改包信息</title>
    <meta name="viewport" content="width=device-width,minimum-scale=1,maximum-scale=1,initial-scale=1,user-scalable=no" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-2.1.4.js"></script>
	<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>  <!-- 调用微信js接口需要 -->
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/awardRotate.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/ui.js"></script>
	
	<link href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"><!-- 图标 -->
	
	<!-- 导入jqueryWeui样式 
	<link rel="stylesheet" href="https://cdn.bootcss.com/weui/1.1.2/style/weui.min.css">
	<link rel="stylesheet" href="https://cdn.bootcss.com/jquery-weui/1.2.0/css/jquery-weui.min.css">-->
	<!-- 导入jqueryWeui样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/jsp/js/jqueryWeui/weui.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/jsp/js/jqueryWeui/jquery-weui.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/jsp/js/jqueryWeui/demos.css">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
  </head>
  <script type="text/javascript">

	$(function (){
		//var packe_num=request('packe_num');
		var packe_num="VESTIDOG连衣裙0004"; 
		//alert(packe_num);
		$.ajax({
			url:"codefindByPack.action?packe_num="+packe_num,
			type:"post",
			dataType:"json",
			//async : false,//同步方式 
			success:function(data){
				
				//console.info(data);
				var pack = data;
				//alert(pack);
				if(pack != undefined){
					document.getElementById("cylinder").value=pack.cylinder;
					document.getElementById("oldnum").value=pack.id;
					document.getElementById("baohao1").value=pack.packe_num;
					document.getElementById("p_num").value=pack.p_num;
					document.getElementById("shuliang1").value=pack.p_number;
					document.getElementById("chima").value=pack.p_size;
					document.getElementById("yanse").value=pack.p_color;
					
				}else{
					//alert(333);
					/* console.log(pack);
					console.log(comList);
					console.log(yetList); */
					
					alert('此包已删除！请返回主页重新扫码！', 'cancel');
				}
				
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

</script>
<style>
	body{background-color:#eee;font-size: 17px; }
	#tou{background-color:#fff; width: 100%; text-align: left; height:55px;line-height: 50px; padding-top: 6px;}
	.context{background-color: #fff; margin-top: 20px;}
	.placeholder {
        margin: 5px;
        padding: 0 10px;
        background-color: #fff;
        height: 1.5em;
        line-height: 1.5em;
        text-align: left;
        color: #000;
        white-space:nowrap;overflow:hidden;text-overflow:ellipsis;
      }
      #gonghao1{white-space:nowrap;overflow:hidden;text-overflow:ellipsis;}
	.ssinp{
		width:95%;
		height: 40px;
		padding-top:15px;
		padding-left:15px;
		border: 0px solid;
	}
	.ssinp input{
		width:65%;
		height: 45px;
		border-radius:15px;
		margin-left: 16px;
		font-size:17px;
	}
	.ready{
		width:95%;
		height: 40px;
		padding-top:15px;
		padding-left:15px;
		border: 0px solid;
	}
	.ready input{
		width:65%;
		height: 45px;
		border: 1px solid;
		font-size:16px;
		margin-left: 16px;
		text-align:center;
		background-color:#eee;
		border-radius:15px;
	}
</style>
  <body>
 
  	<form action="${pageContext.request.contextPath }/codeupdate.action" name="myform" method="post" >
  		<input type="hidden" name="user_name" value="${user.user_name }"/>
			<input type="hidden" name="id" id="oldnum"/>
			<input type="hidden" name="biao" value="1"/>
  			<input type="hidden" id="cylinder" name="cylinder" value=""/>
		<div class="ready" >
  			订单号：<input type="text" id="p_num" name="p_num" readonly="true" style="margin-left: 5px;" />
  		</div>
  		<div class="ready">
  			包  号：<input type="text" id="baohao1" name="packe_num" readonly="true"/>
  		</div>
    		<div class="ready">
  			颜  色：<input type="text" id="yanse" name="p_color" readonly="true"/>
  		</div>
  		<div class="ready">
  			尺 码：<input type="text" id="chima" name="p_size" readonly="true"/>
  		</div>
  		<div class="ssinp">
  			件  数：<input type="number" id="shuliang1" name="p_number"/>
  		</div>
  		<div class="weui-cells__title">选择损耗</div>
    <div class="weui-cells">
      <!-- <div class="weui-cell weui-cell_select">
        <div class="weui-cell__bd">
          <select class="weui-select" name="select1">
            <option selected="" value="1">微信号</option>
            <option value="2">QQ号</option>
            <option value="3">Email</option>
          </select>
        </div>
      </div> -->
      <div class="weui-cell weui-cell_select weui-cell_select-after">
        <div class="weui-cell__hd">
          <label for="" class="weui-label">损耗类型</label>
        </div>
        <div class="weui-cell__bd">
          <select class="weui-select" name="loss_type">
          	<option value="1">不选择</option>
            <option value="1">工艺</option>
            <option value="2">布料</option>
            <option value="3">其他</option>
          </select>
        </div>
      </div>
    </div>
<input  style="margin: 30px 0;" type="button" name="submit1"   class="weui-btn weui-btn_primary" value="提交" onclick="check(this.form)">
  	</form>
  	
	<!-- jqueryWeui样式 
	<script src="https://cdn.bootcss.com/jquery/1.11.0/jquery.min.js"></script>
	<script src="https://cdn.bootcss.com/jquery-weui/1.2.0/js/jquery-weui.min.js"></script>-->
	<!-- jqueryWeui样式 -->
	<!-- 如果使用了某些拓展插件还需要额外的JS 
	<script src="https://cdn.bootcss.com/jquery-weui/1.2.0/js/swiper.min.js"></script>
	<script src="https://cdn.bootcss.com/jquery-weui/1.2.0/js/city-picker.min.js"></script>-->
	<script>
	function check(form){  
		var a = form.shuliang1.value;
		if(form.shuliang1.value==''){
	       alert("请输入要修改的数量");
	       return;
	    }
		if (!(/(^[1-9]\d*$)/.test(a))){
		   alert("请输入正整数");
		   return;
		}
		//console.info(form.shuliang1.value);
		if(form.shuliang1.value<=0){
			alert("不能输入小于0的数");
			return;
		}			
		if(confirm("您确认把这包的数量修改为"+form.shuliang1.value+"吗？")){
		document.myform.submit();	
		}     
	}  
	</script>
  </body>
</html>
