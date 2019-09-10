<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="../demo/jquery-labelauty.css"><!-- 漂亮多选框 -->
<link rel="stylesheet" href="../css/listnav.css"><!-- 字母排序css -->
<style>
ul { list-style-type: none;}
li { display: inline-block;}
li { margin: 10px 0;}
input.labelauty + label { font: 12px "Microsoft Yahei";}
</style>
<input id="pla" type="hidden" name="place" value="">
<!-- 查找：<input type="text" data-toggle="topjui-textbox" data-options="width:150,onChange:function(newValue,oldValue){screen(newValue);}"> -->
<!--显示字母序的层。注：此层id必需是ul的id+"-nav"-->
    <div id="dowebok-nav" style="margin-top: 20px;">
    </div>
   <!-- 兼容IE6 加clear:both;-->

<ul id="dowebok" class="dowebok">
	<!-- <li><input class="techmology_name" type="checkbox" name="techmology_name" disabled checked data-labelauty="HTML"></li>
	<li><input class="techmology_name" type="checkbox" name="techmology_name" data-labelauty="CSS"></li>
	<li><input class="techmology_name" type="checkbox" name="techmology_name" value="3" data-labelauty="JavaScript"></li>
	<li><input class="techmology_name" type="checkbox" name="techmology_name" data-labelauty="SEO"></li>
	<li><input class="techmology_name" type="checkbox" name="techmology_name" data-labelauty="PHP"></li>
	<li><input class="techmology_name" type="checkbox" name="techmology_name" data-labelauty="JAVA"></li>
	<li><input class="techmology_name" type="checkbox" name="techmology_name" data-labelauty=".NET"></li> -->
</ul>
<script src="../demo/jquery-labelauty.js"></script><!-- 漂亮多选框 -->
<script src="js/jquery.charfirst.pinyin.js"></script><!-- 字母排序-获取中文首字母 -->
<script src="js/jquery.listnav.min-2.1.js"></script><!-- 字母排序-jquery插件 -->
<script>
 //request('place');
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
        			daima+="<li style=\"margin: 10px 3px;\"><input class=\"techmology_name\" type=\"checkbox\" name=\"techmology_name\" value=\""+techmology.name+"\" data-labelauty=\""+techmology.name+"\"></li>";
        		}
        		document.getElementById("dowebok").innerHTML=daima;
        		$('.techmology_name').labelauty();
        		//首字母查询初始化
        		 $('#dowebok').listnav({
        	         includeOther: true,
        	         noMatchText: '没有内容',
        	         prefixes: ['the', 'a']
        	     });
        	}
        });
        /* $('.techmology_name').labelauty(); */
 });
 
 	/* function screen(test){
 		var newDaima="";
 		if(tList!=null){
 			var tname = tList.split(",");
 			for(var i=0;i<tname.length;i++){
 				var techmology = tname[i];
 				if(test!=""){
 					if(techmology.indexOf(test)!=-1){
 						newDaima+="<li style=\"margin: 10px 3px;\"><input class=\"techmology_name\" type=\"checkbox\" name=\"techmology_name\" value=\""+techmology+"\" data-labelauty=\""+techmology+"\"></li>";
 	 				}
 				}else{
 					newDaima+="<li style=\"margin: 10px 3px;\"><input class=\"techmology_name\" type=\"checkbox\" name=\"techmology_name\" value=\""+techmology+"\" data-labelauty=\""+techmology+"\"></li>";
 				}
 			}
 			document.getElementById("dowebok").innerHTML=newDaima;
 			$('.techmology_name').labelauty();
 		}
 		//首字母查询初始化
 		$('#dowebok').listnav({
	         includeOther: true,
	         noMatchText: '没有内容',
	         prefixes: ['the', 'a']
	     });
 	} */
</script>
