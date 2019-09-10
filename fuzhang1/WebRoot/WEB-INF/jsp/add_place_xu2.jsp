<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="../demo/jquery-labelauty.css"><!-- 漂亮多选框 -->

<style>
* { margin: 0; padding: 0;}
.keyword { display: block; width: 250px; margin: 10px auto; padding: 5px; border: 1px solid #ccc;}

.jq22 { width: 100%; margin: 0 auto; font-size: 0;}
.filtr-item { display: inline-block;  padding: 5px;}

input.labelauty + label { font: 12px "Microsoft Yahei";}
</style>
<input id="pla" type="hidden" name="place" value="">
<input class="keyword" placeholder="请输入关键字查询" data-search>
<div id="jq22" class="jq22">
	<!-- <div class="filtr-item" data-category="10"><input class="techmology_name" type="checkbox" name="techmology_name" value="3" data-labelauty="JavaScript"></div>
	<div class="filtr-item" data-category="10"><input class="techmology_name" type="checkbox" name="techmology_name" data-labelauty="搭讪"></div>
	<div class="filtr-item" data-category="10"><input class="techmology_name" type="checkbox" name="techmology_name" data-labelauty="上领"></div>
	<div class="filtr-item" data-category="10"><input class="techmology_name" type="checkbox" name="techmology_name" data-labelauty="JAVA"></div>
	<div class="filtr-item" data-category="10"><input class="techmology_name" type="checkbox" name="techmology_name" data-labelauty=".NET"></div>
	<div class="filtr-item" data-category="10"><input class="techmology_name" type="checkbox" name="techmology_name" data-labelauty=".NET"></div>
	<div class="filtr-item" data-category="10"><input class="techmology_name" type="checkbox" name="techmology_name" data-labelauty=".NET"></div>
	<div class="filtr-item" data-category="10"><input class="techmology_name" type="checkbox" name="techmology_name" data-labelauty=".NET"></div> -->
</div>

<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
<script src="js/jquery.filterizr.js"></script>
<script src="../demo/jquery-labelauty.js"></script><!-- 漂亮多选框 -->
<script>
$(function() {
	
	var pla=window.place;
	 $(function () {
		 //var demo = makePy("平");//获取中文首字母
		//隐藏文本框录入订单号
	     $("#pla").val(pla);
	     	var daima="";
			 /* $(':input').labelauty(); */
	        $.ajax({
	        	url:'teList.action?biao=1',
	        	dataType:'json',
	        	success:function(data){
	        		for(var i=0;i<data.length;i++){
	        			var techmology = data[i];
	        			daima+="<div class=\"filtr-item\" data-category=\"10\"><input class=\"techmology_name\" type=\"checkbox\" name=\"techmology_name\" value=\""+techmology.name+"\" data-labelauty=\""+techmology.name+"\"></div>";
	        			//daima+="<li style=\"margin: 10px 3px;\"><input class=\"techmology_name\" type=\"checkbox\" name=\"techmology_name\" value=\""+techmology.name+"\" data-labelauty=\""+techmology.name+"\"></li>";
	        		}
	        		document.getElementById("jq22").innerHTML=daima;
	        		
	        		$('.techmology_name').labelauty();
	        		
	        		$('.jq22').filterizr();
	        	}
	        });
	        /* $('.techmology_name').labelauty(); */
	 });
	
	
	
	//$('.techmology_name').labelauty();
	
	
	
	

});
</script>
