<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<style>
.keyword { display: block; width: 250px; margin: 10px auto; padding: 5px; border: 1px solid #ccc;}

.jq22 { width: 100%; margin: 0 auto; }
.filtr-item { display: inline-block;  padding: 10px;font-size: 17px;}
#h1{
	font-size:22px;
}
.tab{
	width:100%;
	
}
.tab td{
	width:20%;
	height:30px;
	text-align:center;
	border: 1px solid;
}
</style>
<h1 id="h1"> </h1>
<div id="jq22" class="jq22">
	<!-- <div class="filtr-item" data-category="10"><input class="techmology_name" type="checkbox" name="techmology_name" value="3" data-labelauty="JavaScript"></div>
	 -->
</div>


<script>
$(function() {
	var plan_num=window.place;
	var pla=window.str;
	//console.info(pla);
	 $(function () {
		 //var demo = makePy("平");//获取中文首字母
		//隐藏文本框录入订单号
	     $("#pla").val(pla);
	     	var daima="<table class='tab'><thead><tr><td>颜色</td> <td>尺码</td> <td>数量</td> <td>包号</td></tr></thead>";
			 /* $(':input').labelauty(); */
	        $.ajax({
	        	url:'placejinduInfo.action?plan_num='+plan_num+'&technology='+pla,
	        	dataType:'json',
	        	success:function(data){
	        		//console.info(data.ptList[1].packe_num);
	        		console.info(data);
	        		for(var i=0;i<data.ptList.length;i++){
	        			var pck = data.ptList[i].packe_num;
	        			var size = data.ptList[i].p_num;
	        			var color = data.ptList[i].technology_name;
	        			var number = data.ptList[i].state;
	        			daima +="<tbody><tr><td>"+color+"</td> <td>"+size+"</td> <td>"+number+"</td> <td>"+pck.substring(pck.length-4)+"</td></tr></tbody>";
	        			//daima+="<div class=\"filtr-item\" data-category=\"10\">"+pck.substring(pck.length-4)+"</div>";
	        			//daima+="<li style=\"margin: 10px 3px;\"><input class=\"techmology_name\" type=\"checkbox\" name=\"techmology_name\" value=\""+techmology.name+"\" data-labelauty=\""+techmology.name+"\"></li>";
	        		}
	        		daima+="</table>";
	        		document.getElementById("jq22").innerHTML=daima;
	        		document.getElementById("h1").innerHTML=pla+"未完成的包号：共有"+data.ptList.length+" 包未完成";
	        	}
	        });
	        /* $('.techmology_name').labelauty();
	        
	        for(var i=0;i<data.length;i++){
	        			var techmology = data[i];
	        			daima+="<div class=\"filtr-item\" data-category=\"10\"><input class=\"techmology_name\" type=\"checkbox\" name=\"techmology_name\" value=\""+techmology.name+"\" data-labelauty=\""+techmology.name+"\"></div>";
	        			//daima+="<li style=\"margin: 10px 3px;\"><input class=\"techmology_name\" type=\"checkbox\" name=\"techmology_name\" value=\""+techmology.name+"\" data-labelauty=\""+techmology.name+"\"></li>";
	        		}
	        		document.getElementById("jq22").innerHTML=daima;
	        		
	        		$('.techmology_name').labelauty();
	        		
	        		$('.jq22').filterizr();
	         */
	 });
	
	
	//$('.techmology_name').labelauty();


});
</script>
