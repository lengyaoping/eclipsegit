<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<style>
	
	#sstable{
		width:100%;
		border: 0px solid black;
	}
	#sstable tr{
		height:30px;
		border: 0px solid black;
	}
	#sstable tr td{
		text-align:center;
		font-size:15px;
		font-family:KaiTi;
		border: 1px solid black;
	}
	#sstable thead tr td{
		font-size: 20px;
	}
	#h1{
		width:240px;
		float: left;
		font-size: 12px;
	}
	#h2{
		width:240px;
		float: left;
		font-size: 12px;
	}
	#h1 span{
		float: left;
	}
	#h2 span{
		float: left;
	}
	.colo{
		width:75px;
		float: left;
		font-size: 12px;
		text-align: center;
	}
	.siz{
		width:55px;
		float: left;
		text-align: center;
		font-size: 12px;
	}
	
</style>

<table id="sstable">
	<thead id="shq">
		
	</thead>
	<tbody id="stq">
		
	</tbody>
</table>
<div style="margin-top:20px;" id="sw">


<div id="h1"></div>
<div id="h2"></div>
</div>
<script>
$(function() {
	
	//var pla=window.pl;
	var plan=window.str;
	//console.info(pla+"-----"+plan);
	 $(function () {
		 $.ajax({
		 	url:'placefindListByPlaceNum.action?plan_num='+plan,
		 	dataType:'json',
	        success:function(data){
		        var daima ="";
		        if(data.scList!=null){
		        	var sslist = new Array();
		        sslist.push(data.scList[0].color);
		        var alist = new Array();
		        alist = data.scList;
		        var sizelist = new Array();
	        	for(var i=0;i<data.scList.length;i++){
	        			var color = data.scList[i].color;
	        			var size = data.scList[i].size;
	        			var number = data.scList[i].fake;
	        			if(i>0 &&color!=data.scList[i-1].color){
	        	      		sslist.push(color);
	        			}
	        			sizelist.push(size);
	        			
	        			daima+="<tr class=\"filtr-item\" ><td>"+color+"</td><td>"+size+"</td><td>"+number+"</td></tr>";
	        	}
	        	//console.info(sizelist);
	        	document.getElementById("shq").innerHTML="<tr><td>颜色</td><td>尺码</td><td>分包件数</td></tr>";
	        	document.getElementById("stq").innerHTML=daima;
	        	var numList = new Array();
	        	var dm="<p style='font-size:16px;'>颜色  汇总</p>";
	        	for(var i=0;i<sslist.length;i++){
	        		var num = 0;
	        		for(var j=0;j<data.scList.length;j++){
	        			if(data.scList[j].color==sslist[i]){
	        				num+=data.scList[j].fake;
	        			}
	        		}
	        		dm += "<div class='colo'>"+sslist[i]+"</div><span>共有</span><div class='siz'> "+num+"</div>件  <br>";
	        		numList.push(num);
	        	}
	        	var nlist = new Array();
	        	for(var i=0;i<sizelist.length;i++)  {       
			          if (nlist.indexOf(sizelist[i]) == -1) {
			          nlist.push(sizelist[i]);   
			          }
			    } 
			    var dm1="<p style='font-size:16px;'>尺码  汇总</p>";
	        	for(var i=0;i<nlist.length;i++){
	        		var num = 0;
	        		for(var j=0;j<data.scList.length;j++){
	        			if(data.scList[j].size==nlist[i]){
	        				num+=data.scList[j].fake;
	        			}
	        		}
	        		dm1 += "<div class='colo'>"+nlist[i]+"</div><span>共有</span><div class='siz'> "+num+"</div>件  <br>";
	        		numList.push(num);
	        	}
	        	document.getElementById("h1").innerHTML=dm;
	        	document.getElementById("h2").innerHTML=dm1;
		        }else{
		        	daima="<span style='font-size:20px;'>该订单还没有添加任何包！请添加后再来查看</span>";
		        	document.getElementById("sw").innerHTML=daima;
		        }
		        
	        }
		 });
	 });
	
	
	//$('.techmology_name').labelauty();


});
</script>
