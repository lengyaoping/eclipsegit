<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>员工生产计件报表 </title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta http-equiv="keywords" content="报表">
	<meta http-equiv="description" content="员工生产报表">
	<link rel="stylesheet" type="text/css" href="../css/fSelect.css" ><!-- 下拉多选框 -->
	<link rel="stylesheet" type="text/css" href="../css/demo.css"><!-- 下拉多选框 -->
    <script src="./js/grhtml5-6.5-min.js" type="text/javascript"></script>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script src="http://code.jquery.com/jquery-1.11.3.min.js"></script><!-- 下拉多选框 -->
	<script src="../js/fSelect.js"></script><!-- 下拉多选框 -->
    <script language="javascript" type="text/javascript">
    	var placeList;
    	var placeListA;
    	$(function(){ 
    		 $(":radio").click(function(){
    		 	//console.info(placeList);
    		 	//console.info(placeListA);
			   //alert("您是..." + $(this).val());
			   var place_nums = "<select class=\"place_num\" multiple=\"multiple\">",
        			styles = "<select class=\"style\" multiple=\"multiple\">";
			   if($(this).val()==1){
			   		for(var i=0;i<placeList.length;i++){
        				var place = placeList[i];
        				//console.info(place.plan_num);
        				place_nums+="<option value=\""+place.plan_num+"\">"+place.plan_num+"</option>";
        				styles+="<option value=\""+place.style+"\">"+place.style+"</option>";
        				document.getElementById("place_nums").innerHTML=place_nums+"</select>";
        				document.getElementById("styles").innerHTML=styles+"</select>";
        			}
        			
			   }else{
			   		for(var i=0;i<placeListA.length;i++){
        				var place = placeListA[i];
        				//console.info(place.plan_num);
        				place_nums+="<option value=\""+place.plan_num+"\">"+place.plan_num+"</option>";
        				styles+="<option value=\""+place.style+"\">"+place.style+"</option>";
        				document.getElementById("place_nums").innerHTML=place_nums+"</select>";
        				document.getElementById("styles").innerHTML=styles+"</select>";
        			}
			   }
			   $('.place_num,.style').fSelect();0
			  });
    		//姓名员工号初始化
        	$.ajax({
        		url:'usergetAllUserList.action',
        		dataType:'json',
        		success:function(data){
        			var list = data.list;
        			var usergroup = data.fenzuList;
        			placeList = data.listPlace;
        			placeListA = data.listA;
        			var tlist = data.tlist,
        			users="<select class=\"demo\" name=\"suss\" id=\"susss\" multiple=\"multiple\">",
        			job_numbers = "<select class=\"job_number\" multiple=\"multiple\">",
        			group = "<select class=\"group\" multiple=\"multiple\">",
        			place_nums = "<select class=\"place_num\" multiple=\"multiple\">",
        			styles = "<select class=\"style\" multiple=\"multiple\">",
        			tnames = "<select class=\"tname\" multiple=\"multiple\">";
        			for(var i=0;i<list.length;i++){
        				var usre=list[i];
        				users+="<option value=\""+usre.job_number+"\">"+usre.user_name+"</option>";
        				job_numbers+="<option value=\""+usre.job_number+"\">"+usre.job_number+"</option>";
        			}
        			for(var i=0;i<placeList.length;i++){
        				var place = placeList[i];
        				place_nums+="<option value=\""+place.plan_num+"\">"+place.plan_num+"</option>";
        				styles+="<option value=\""+place.style+"\">"+place.style+"</option>";
        			}
        			for(var i=0;i<usergroup.length;i++){
        				//console.log(usergroup[i]);
        				group+="<option value=\""+usergroup[i].password+"\">"+usergroup[i].password+"</option>";
        			}
        			for(var i=0;i<tlist.length;i++){
        				var t_name = tlist[i];
        				tnames+="<option value=\""+t_name.name+"\">"+t_name.name+"</option>";
        			}
        			//users+="</select>";
        			document.getElementById("users").innerHTML=users+"</select>";
        			document.getElementById("job_numbers").innerHTML=job_numbers+"</select>";
        			document.getElementById("group").innerHTML=group+"</select>";
        			document.getElementById("place_nums").innerHTML=place_nums+"</select>";
        			document.getElementById("styles").innerHTML=styles+"</select>";
        			document.getElementById("tnames").innerHTML=tnames+"</select>";
        			$('.demo,.job_number,.group,.place_num,.style,.tname').fSelect();//下拉多选框初始化,这里新增搜索框的话要要改fSelect()方法
        			//$('.job_number').fSelect();//下拉多选框初始化
        		}
        	});
    		
        	$("#time1").val(nowDate());
        	//$("#time2").val(nowDate());
        	var nextDate = new Date(new Date().getTime() + 24*60*60*1000); //后一天
        	var date = nextDate.getFullYear()+"-"+Number(Number(nextDate.getMonth())+Number(1))+"-"+nextDate.getDate();
        	$("#time2").val(date);
    	});
    	/*报表初始化方法 API 实现*/
        /* function window_onload() {
            var reportURL = "./grf/demo.grf",
                dataURL = "subReport.action",
                options = getQueryString("options"),
                reportViewer = window.rubylong.grhtml5.insertReportViewer("report_holder", reportURL, dataURL);


            if (options) {
                options = options.split(";");
                options.forEach(function (item) {
                    reportViewer.options[item] = true;
                });
            }
            reportViewer.start();
        } */
        /*报表初始化方法 API 实现*/
        
    	/*筛选按钮方法实现*/
        function screen(){
        	 var options = $("#susss option:selected");
        	//console.info(options.length);
        	var usre_name1=$("#user_name").text();
        	 if(options.length>0){//防止名字重复或被修改  改成工号查询
        	 	usre_name1="";
	        	 for (var i=0;i<options.length;i++){
	                  usre_name1 += options[i].value;
	                 //console.info(options[i].value);
	                  usre_name1 += ", ";
	             }
        	 }
        	 //console.info(usre_name1);
        	
         	var input = /^[\s]*$/;
         	var job_number=$("#job_number").text();
        	if(usre_name1!="空" && job_number!="空"){
        		job_number=usre_name1+job_number;
        		usre_name1="空";
        	}else if(usre_name1!="空" && job_number=="空"){
        		//console.info(usre_name1);
        		job_number = usre_name1.substring(0,usre_name1.length-2);
        		//console.info(job_number);
        		usre_name1="空";
        	}
        	console.info(job_number);
        	//return;
        	//console.info(job_number);
        	//console.info(usre_name1);
        	//return;
        	var user_group=$("#user_group").text();
        	var place_num=$("#place_num").text();
        	var style=$("#style").text();
        	var tname=$("#tneme").text();
        	var time1=$("#time1").val();
        	var time2=$("#time2").val();
        	var state = $("input:checked").val();
        	var htt = "&state="+state;
        	if(usre_name1 != "空"){
        		htt+="&user_name="+usre_name1;
        	}
        	if(job_number != "空"){
        		htt+="&job_number="+job_number;
        	}
        	if(user_group != "空"){
        		htt+="&user_group="+user_group;
        	}
        	if(place_num != "空"){
        		htt+="&place_num="+place_num;
        	}
        	if(style != "空"){
        		htt+="&style="+style;
        	}
        	if(tname != "空"){
        		htt+="&tname="+tname;
        	}
        	if(!(input.test(time1))){
        		htt+="&time1="+time1;
        	}
        	if(!(input.test(time2))){
        		htt+="&time2="+time2;
        	}
        	if(input.test(htt)){//如果条件htt为空或者空格，则不添加条件加载出报表
        		var reportURL = "./grf/demo.grf",
	                dataURL = "subReport1.action",
	                options = getQueryString("options"),
	                reportViewer = window.rubylong.grhtml5.insertReportViewer("report_holder", reportURL, dataURL);
	
	            /* document.title += getQueryString("title"); */ //设置当前网页标题
	
	            if (options) {
	                options = options.split(";");
	                options.forEach(function (item) {
	                    reportViewer.options[item] = true;
	                });
	            }
	            reportViewer.start();
	            scrollTo(0,0);
	            
	          	//删除
    			sessionStorage.removeItem("time1");
    			sessionStorage.removeItem("time2");
    			sessionStorage.removeItem("htt");
    			sessionStorage.removeItem("state");
	            
        	}else{//如果条件htt不 为空或者空格，则要添加条件加载出报表
        		if(!(input.test(time1)) && !(input.test(time2))){
        			// 存储
        			sessionStorage.setItem("time1", time1);
        			sessionStorage.setItem("time2", time2);
        			sessionStorage.setItem("htt", htt);
        			sessionStorage.setItem("state", state);
        		}else{
        			//删除
        			sessionStorage.removeItem("time1");
        			sessionStorage.removeItem("time2");
        			sessionStorage.removeItem("htt");
        			sessionStorage.removeItem("state");
        		}
        		
        		var reportURL = "./grf/demo.grf",
	                dataURL = "subReport1.action?biao=1"+htt,
	                options = getQueryString("options"),
	                reportViewer = window.rubylong.grhtml5.insertReportViewer("report_holder", reportURL, dataURL);
	
	            /* document.title += getQueryString("title"); */ //设置当前网页标题
	
	            if (options) {
	                options = options.split(";");
	                options.forEach(function (item) {
	                    reportViewer.options[item] = true;
	                });
	            }
	            reportViewer.start();
	            scrollTo(0,0);
        	}
        }
        /*筛选按钮方法实现*/
        
        function jiesuan(){
        	var input = /^[\s]*$/;
        	var time1 = sessionStorage.getItem("time1");
        	var time2 = sessionStorage.getItem("time2");
        	var htt = sessionStorage.getItem("htt");
        	var state = sessionStorage.getItem("state");
        	if(htt!=null && time1!=null && time2!=null && !(input.test(time1)) && !(input.test(time2)) && state==1){
        		var startD = new Date(Date.parse(time1.replace(/-/g,"/")));
   				var endD   = new Date(Date.parse(time2.replace(/-/g,"/")));
   				var days = parseInt((endD.getTime()-startD.getTime()) / (1000 * 60 * 60 * 24));
   				if(days >= 1){//时间合理
   					$.ajax({
   						url:"subReport.action?biao=1&data=1"+htt,
   						type:"post",
   						dataType:"json",
   						success:function(data){
   							if(data.result=="ok"){
   								alert("结算成功!");
   								location.reload();
   							}else{
   								alert("没数据!");
   							}
 							//删除
		        			sessionStorage.removeItem("time1");
		        			sessionStorage.removeItem("time2");
		        			sessionStorage.removeItem("htt");
		        			sessionStorage.removeItem("state");
   						}
   					});
   					return;
   				}
        	}
        	alert("请筛选出最少一天的未结的数据！");
    		return;
		}
        
		
        /*获取地址栏参数的方法实现*/
        function getQueryString(name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) {
                return decodeURI(r[2]); //return unescape(r[2]);
            }
            return null;
        }
        /*获取地址栏参数的方法实现*/
        
        /*打印按钮方法实现*/
		function preview(){    
	        bdhtml=window.document.body.innerHTML;    
	        sprnstr="<!--startprint-->";    
	        eprnstr="<!--endprint-->";  
	        
	        //保存打印时不需要的数据
		    var beforePrnHtml = bdhtml.substr(0,bdhtml.indexOf(sprnstr)+17);
		    var afterPrnHtml = bdhtml.substr(bdhtml.indexOf(eprnstr));
		    
	        //提取欲打印的数据
	        prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)+17);    
	        prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));    
	        window.document.body.innerHTML=prnhtml;
	        window.print();   //打印 
	        
	        //恢复页面，可再次打印
    		window.document.body.innerHTML=beforePrnHtml + prnhtml + afterPrnHtml;
		}
		/*打印按钮方法实现*/
		
        /*导出Excel表按钮方法实现*/
        function demo(){
        	document.getElementsByTagName("table")[2].border="1";
        	// 使用outerHTML属性获取整个table元素的HTML代码（包括<table>标签），然后包装成一个完整的HTML文档，设置charset为urf-8以防止中文乱码
            var html = "<html><head><meta charset='utf-8' /></head><body>" + document.getElementsByTagName("table")[2].outerHTML + "</body></html>";
            // 实例化一个Blob对象，其构造函数的第一个参数是包含文件内容的数组，第二个参数是包含文件类型属性的对象
            var blob = new Blob([html], { type: "application/vnd.ms-excel" });
            // 利用URL.createObjectURL()方法为a元素生成blob URL
            /* a.href = URL.createObjectURL(blob); */
            $("#demo").attr('href',URL.createObjectURL(blob));
            // 设置文件名，目前只有Chrome和FireFox支持此属性
            /* a.download = "学生成绩表.xls"; */
            $("#demo").attr('download',"员工生产计件报表.xls");
        	
        }
        /*导出Excel表按钮方法实现*/
        
        // 获取当前时间
		function nowDate(){
		    now = new Date();     // 当前日期
		    if((now.getMonth() + 1) < 10){
		        if(now.getDate()<10){
		            // 当前日期
		            today = now.getFullYear() + '-' +'0' + (now.getMonth() + 1) + '-' +'0' + now.getDate();             
		        }else{
		            // 当前日期
		            today = now.getFullYear() + '-' +'0' + (now.getMonth() + 1) + '-' + now.getDate();
              }
		    }else{
		        if(now.getDate()<10){
		            // 当前日期
		            today = now.getFullYear() + '-' + (now.getMonth() + 1) + '-' +'0' + now.getDate();
		        }else{
		            // 当前日期
		            today = now.getFullYear() + '-' + (now.getMonth() + 1) + '-' + now.getDate();
		        }
		    }
		    //console.log("today is day--->" + today);
		    return today;
		}
    </script>
    <style>
    	*{margin: 0;padding: 0}
    	
    	/*左侧div*/
    	.div_left{border: 1px solid #fff; float: left; width: 20%;}
    	.div_left .div_screen{position: fixed;left: 5px;top: 10px;width: 250px;text-align:center;}
    	
    	/*左侧筛选表格table*/
    	.div_left .div_screen .screen_table{width: 100%; height: 400px;font-size: 14px;}
    	.div_left .div_screen .screen_table input[type!='radio']{border-radius:3px; border: 1px solid #ccc; height: 20px; outline:none;
    	width: 180px;}
    	.div_left .div_screen .screen_table input[type='date']{border-radius:3px; border: 1px solid #ccc; height: 20px; outline:none;
    	width: 180px;}
    	/* .div_left .div_screen .screen_table input[type='radio']{width: 60px;} */
    	
    	/*左侧筛选按钮*/
    	.div_left .div_screen .screen_btn{background: gold;border: none;border-radius: 40px 10px; width: 80px;height: 
    	40px;color: #428bca; font-weight: bold; font-size: 18px;outline:none;cursor:pointer;margin-bottom: 15px;}
    	
    	/* 左侧打印按钮 */
    	.div_left .div_screen .printing{width:90px;height:35px;cursor:pointer;background: #09F;border: 0;color: #fff;
    	outline:none;}
    
    	/* 打印后的页面样式设置 */
	   @media print{}
	   
	   /*左侧导出Excel按钮*/
	  .div_left .div_screen a{display: inline-block;height: 35px;width: 90px; background: red;line-height: 35px;
	  background: #6C3;font-size: 14px;color: #fff;cursor:pointer;text-decoration:none;}
	   
	  /*右侧div*/
	  .div_right{float: left; width: 79%;padding-bottom: 10px;}
	</style>
</head>
<body ><!-- onload="window_onload()" -->
	<div class="div_left">
		<div class="div_screen">
			<table class="screen_table">
				<tr>
					<td style="width: 30%;" align="right">状态：</td>
					<td><input type="radio" name="state" value="1" checked="checked">未结</td>
					<td><input type="radio" name="state" value="3">已结</td>
					<td><input type="radio" name="state" value="2">全部</td>
				</tr>
				<tr>
					<td style="width: 30%;" align="right">姓名：</td>
					<td id="users" colspan="3">
						<!-- <input type="text" id="user_name"> -->
						<!-- <select class="demo" multiple="multiple">
						    <optgroup label="Languages">
						        <option value="cp">C++</option>
						        <option value="cs">C#</option>
						        <option value="oc">Object C</option>
						        <option value="c">C</option>
						    </optgroup>
						    <optgroup label="Scripts">
						        <option value="js">JavaScript</option>
						        <option value="php">PHP</option>
						        <option value="asp">ASP</option>
						        <option value="jsp">JSP</option>
						    </optgroup>
						</select> -->
					</td>
				</tr>
				<tr>
					<td align="right">员工号：</td>
					<td id="job_numbers" colspan="3">
						<!-- <input type="text" id="job_number" autocomplete="off"> -->
					</td>
				</tr>
				<tr>
					<td align="right">分组：</td>
					<td id="group" colspan="3">
						<!-- <input type="text" id="job_number" autocomplete="off"> -->
					</td>
				</tr>
				<tr>
					<td align="right">生产号：</td>
					<td id="place_nums" colspan="3">
						<!-- <input type="text" id="place_num" autocomplete="off"> -->
						
					</td>
				</tr>
				<tr>
					<td align="right">款式：</td>
					<td id="styles" colspan="3">
						<!-- <input type="text" id="style" autocomplete="off"> -->
					</td>
				</tr>
				<tr>
					<td align="right">工序：</td>
					<td id="tnames" colspan="3">
						<!-- <input type="text" id="style" autocomplete="off"> -->
					</td>
				</tr>
				<tr>
					<td align="right">开始于：</td>
					<td colspan="3"><input type="date" id="time1"></td>
					
				</tr>
				<tr>
					<td align="right">结束于：</td>
					<td colspan="3"><input type="date" id="time2"></td>
				</tr>
				<tr>
					<td colspan="4" style="color: red;">注：如需结算9月1号（包含1号当天）到30号（包含30号当天）的工资，选择开始于9月1号，结束于9月31号（这里推后一天）</td>
				</tr>
			</table>
			<input class="screen_btn" type="submit" onclick="screen()" value="筛选">
			<div>
				<button class="printing" onclick="preview()">打印</button>
				<a id="demo" onclick="demo()">导出Excel</a>
			</div>
			<div style="margin-top:15px;">
				<button class="printing" onclick="javascript:if(confirm('确认结算工资吗？')){jiesuan()}">工资结算</button>
			</div>
		</div>
	</div>
	<div class="div_right">
			<!--startprint-->
			<center>
	    		<div id="report_holder">
	    		</div>
    		</center>
			<!--endprint-->
	</div>
</body>
</html>



