<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<link rel="stylesheet" href="../demo/jquery-labelauty.css"><!-- 漂亮多选框 -->
<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
<script src="js/jquery.filterizr.js"></script>
<script src="../demo/jquery-labelauty.js"></script><!-- 漂亮多选框 -->
<style>
* { margin: 0; padding: 0;}
.keyword { display: block; width: 250px; margin: 10px auto; padding: 5px; border: 1px solid #ccc;}

.jq22 { width: 100%; margin: 0 auto; font-size: 0;}
.filtr-item { display: inline-block;  padding: 5px;}

input.labelauty + label { font: 12px "Microsoft Yahei";}

#ttt{
	display: none;
	visibility: hidden;
}
</style>
<input type="hidden" id="uuid" name="id">
<table class="editTable">
    <tr>
        <td class="label">款式名称</td>
        <td> <input type="text" name="style_name" data-toggle="topjui-textbox" data-options="validType:['checkMaterialP','length[0,20]'],required:true">
         </td>
    </tr>
    <tr>
        <td class="label"></td>
        <td>
        <div id="ttt">
   		<input type="text" id ="tid" name="style_t_id" data-toggle="topjui-textbox" data-options="onChange:function(newv,oldv){
   			//alert(oldv+'--'+newv);
   			nsss(newv);
   		}"></div>
     </td>
    </tr>
</table>

<div id="jq22" class="jq22">
	
</div>

<script>
	
	
	$(function(){
		
		$.ajax({
				url:"teList.action",
				type:"GET",
				data:{biao:1},
				success:function(data){var daima="";
					for(var i=0;i<data.length;i++){
	        			var techmology = data[i];																										
	        			daima+="<div class=\"filtr-item\" data-category=\"10\"><input class=\"techmology_name\" type=\"checkbox\" name=\"t_id\" value=\""+techmology.id+"\" data-labelauty=\""+techmology.name+"\"></div>";
	        			//daima+="<li style=\"margin: 10px 3px;\"><input class=\"techmology_name\" type=\"checkbox\" name=\"techmology_name\" value=\""+techmology.name+"\" data-labelauty=\""+techmology.name+"\"></li>";
	        			document.getElementById("jq22").innerHTML=daima;
	        		}
	        		//document.getElementById("jq22").innerHTML=daima;
	        		$('.techmology_name').labelauty();
	        		$('.jq22').filterizr();
				}
			});
			
	});
	function nsss(newv){
		str = newv.replace(/\s*/g,"");
		var b = str.split(",");
		//console.info(b);
		var i = 0;
		if(b.length>0){
			$("input[name='t_id']").each(function(index) {
				//console.info("竟来了"+i);
				if($("input[name='t_id']").get(index).value==b[i]){
					$("input[name='t_id']").get(index).checked = true;
					i++;
				}
				if(i==b.length-1){
					return;
				}
				
			});
		}
	}
	
</script>               
