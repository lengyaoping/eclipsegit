<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="../demo/jquery-labelauty.css"><!-- 漂亮多选框 -->
<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
<script src="js/jquery.filterizr.js"></script>
<script src="../demo/jquery-labelauty.js"></script><!-- 漂亮多选框 -->
<style>
* { margin: 0; padding: 0;}
.keyword { display: block; width: 250px; margin: 10px auto; padding: 5px; border: 1px solid #ccc;}

.jq22 { }
.jq22 *{float:left;}
.filtr-item { display: inline-block;  padding: 5px;}

input.labelauty + label { font: 12px "Microsoft Yahei";}

#ttt{
	display: none;
	visibility: hidden;
}
</style>
<table class="editTable" style="margin-left:50px">
      <tr><td>选择订单号</td>
		<td><input type="text" name="place_num" value=""
                   data-toggle="topjui-combogrid"
                   data-options="id:'plan_num',
                   width:250,
                   idField: 'plan_num',
                   textField: 'plan_num',
                   url:'placefindByPlace.action',
                   columns:[[
                       {field: 'plan_num', title: '订单号',width:'120px'},{field: 'style', title: '款号'}
                   ]],onSelect: function (index,row){
                  	sss(row['plan_num']);
                   },prompt:'必填',required:true"></tr>
                   <tr>
                   <td>选择工序</td>
                   <td><input id="tname" type="text" name="t_name" value=""
                           data-toggle="topjui-combogrid"
                           data-options="id:'tname',width:250,
           					prompt:'必填',required:true,onSelect: function (index,row){
           					console.info(row);
                  	chageT(row['technology_name'],row['place_number']);
                   }"></td>
                       </tr>
                   <script>
                   	function sss(s){
                   		//console.info(s);
                   		$('#tname').iCombogrid({
                                type:'get',
                                idField: 'technology_name',
                   				textField: 'technology_name',
                                url:'placefindByT.action?place='+s+'&page=1&rows=100',
                                columns:[[
                       					{field: 'technology_name', title: '工序',width:'190px'}
                  			 	]]
                         });
                   	}
                   </script>
                    <tr><td>选择员工</td>
                    <td><input type="text" name="user_name" value=""
	                   data-toggle="topjui-combogrid"
	                   data-options="
	                   width:250,
	                   idField: 'user_name',
	                   textField: 'user_name',
	                   url:'userseleceQ.action',
	                   columns:[[
	                       {field: 'user_name', title: '姓名',width:'100px'},{field: 'job_number', title: '员工号'}
	                   ]],onSelect: function (index,row){
	                   	
	                   $('#job_number').val(row['job_number']);
	                   },prompt:'必填',required:true">
	                   <input id="job_number" type="hidden" name="job_number" value="">
                   </td></tr>
                    <!--<tr><td>输入包号</td>
                       <td id="fs"><input id="t1" name="time1" data-toggle="topjui-numberbox"
                                  data-options="width:100,min:1,prompt:'输入包号的',required:true">~~
                           <input id="t2" name="time2" data-toggle="topjui-numberbox"
                                  data-options="width:100,min:1,max:9999,prompt:'后几位数字',required:true">
                           </td> 
                    </tr>  -->
</table>
<div id="jq22" class="jq22" >
	
</div>
        <script>
        	function chageT(t,p){
        		console.info(t,p);
        		$.ajax({
				url:"subgetPackeNums.action",
				type:"GET",
				data:{place_num:p,t_name:t},
				success:function(data){
					console.info(data);
					var plist = data.pList;
					var daima="";
					for(var i=0;i<plist.length;i++){
	        			var packt = plist[i];	
	        			//var bao = packt.packe_num.substr(packt.packe_num.length-4);																									
	        			daima+="<div class=\"filtr-item\" data-category=\"10\"><input class=\"packe_num\" type=\"checkbox\" name=\"packe_num\" value=\""+packt.packe_num+"\" data-labelauty=\""+packt.packe_num.substr(packt.packe_num.length-4)+"\"></div>";
	        			//daima+="<li style=\"margin: 10px 3px;\"><input class=\"techmology_name\" type=\"checkbox\" name=\"techmology_name\" value=\""+techmology.name+"\" data-labelauty=\""+techmology.name+"\"></li>";
	        			document.getElementById("jq22").innerHTML=daima;
	        		}
	        		//document.getElementById("jq22").innerHTML=daima;
	        		$('.packe_num').labelauty();
	        		$('.jq22').filterizr();
				}
			});
        	}
        </script>              
