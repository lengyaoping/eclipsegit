<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <!-- TopJUI妗嗘灦閰嶇疆 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/public/js/topjui.config.js"></script>
    <style>
    	#ss h4{
    		float: left;
    	}
    	h4{
    		font-size: 20px;
    	}
    	#numA{
    		color:red;
    	}
    	
    </style>
    <div id="ss"><h4>综合单价：</h4><h4 id="numA"></h4></div>
<table class="editTable" id="editTable">
	
    <tr>
        <td class="label">
        	<input id="pla" type="hidden" name="place" value="">
        	
        </td>
        <td>
        	
        </td>
    </tr>
</table>

<script>
 //request('place');
 var pla=window.place;
  $(function () {
  		//闅愯棌鏂囨湰妗嗗綍鍏ヨ鍗曞彿
     	/* $("#pla").val(pla); */
        $.ajax({
        	url:"placefindAllByplace.action?place="+pla,
        	type:"post",
        	dataType: "json",
        	success:function(data){
        		//console.info(data);
        		document.getElementById("editTable").innerHTML="";
        		var daima="<tr><td class=\"label\"><input id=\"pla\" type=\"hidden\" name=\"place\" value=\""+pla+"\">工序名称</td><td>单价</td></tr>";
        		var list = data.list;
        		var numaA = 0;
        		for(var i=0;i<list.length;i++){
        			numaA+=list[i].price;
        			daima+="<tr><td class=\"label\"><input type=\"hidden\" name=\"id\" value=\""+list[i].id+"\">"+list[i].technology_name+"</td><td><input type=\"number\" name=\"price\" value=\""+list[i].price+"\" onchange='getnum(this)'></td></tr>";
        		}
        		document.getElementById("editTable").innerHTML=daima;
        		document.getElementById("numA").innerHTML=numaA;
        	}
        });
        
       
        });
       
        function getnum(obj){
        	
        	var num = obj.value;
        	if(num==""){
        	 obj.value = 0;
        	 };
        	 var a=0.0;
        	 //console.info(a);
        	$("input[name='price']").each(function(){
        		a+=Number(this.value);
        	});
        	document.getElementById("numA").innerHTML=a;
        }
</script>
