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
	#hh{
		width:100%;
		float: left;
		font-size: 12px;
	}
	#h1{
		width:50%;
		float: left;
		font-size: 12px;
	}
	#h2{
		width:50%;
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

<div id="hh"></div>
<div id="h1"></div>
<div id="h2"></div>
</div>
<script>
$(function() {
	
	//var pla=window.pl;
	var plan=window.str;
	console.info("-----"+plan);
	var type1 = 0;
	var type2 = 0;
	var type3 = 0;
	 $(function () {
		 $.ajax({
		 	url:'codegetLostListByPlace.action?place_num='+plan,
		 	dataType:'json',
	        success:function(data){
	        	console.info(data);
		        var daima ="";
		        if(data!=null){
	        	var slist = new Array();
	        	var clist = new Array();
	        	for(var i=0;i<data.losses.length;i++){
	        			var color = data.losses[i].color;
	        			var size = data.losses[i].size;
	        			var number = data.losses[i].loss_num;
	        			var type = "";
	        			if(data.losses[i].loss_type=="1"){
	        				type="工艺";
	        				type1 += number;
	        			}else if(data.losses[i].loss_type=="2"){type2 += number;
	        				type="布料";
	        			}else if(data.losses[i].loss_type=="3"){type3 += number;
	        				type="其他";
	        			}
	        			slist.push(data.losses[i].size);
	        			clist.push(data.losses[i].color);
	        			daima+="<tr class=\"filtr-item\" ><td>"+size+"</td><td>"+color+"</td><td>"+type+"</td><td>"+number+"</td></tr>";
	        	}
	        	console.info(type1+"--"+type2+"---"+type3);
	        	
	        	document.getElementById("shq").innerHTML="<tr><td>尺码</td><td>颜色</td><td>破损原因</td><td>件数</td></tr>";
	        	document.getElementById("stq").innerHTML=daima;
	        	document.getElementById("hh").innerHTML="<font size='3px' color='red'>工艺破损："+type1+" 布料破损："+type2+" 其他破损："+type3+"</font>";
	        	slist = uniq(slist);
	        	var dm1="<p style='font-size:16px;'>尺码  汇总</p>";
	        	for(var i=0;i<slist.length;i++){
	        		var num = 0; 
	        		for(var j=0;j<data.losses.length;j++){
	        			if(data.losses[j].size == slist[i]){
	        				num+=data.losses[j].loss_num;
	        			}
	        		}
	        		dm1 += "<div class='colo'>"+slist[i]+"</div><span>共有</span><div class='siz'> "+num+"</div>件  <br>";
	        	}
	        	clist = uniq(clist);
	        	var dm="<p style='font-size:16px;'>颜色  汇总</p>";
	        	for(var i=0;i<clist.length;i++){
	        		var num = 0; 
	        		for(var j=0;j<data.losses.length;j++){
	        			if(data.losses[j].color == clist[i]){
	        				num+=data.losses[j].loss_num;
	        			}
	        		}
	        		dm += "<div class='colo'>"+clist[i]+"</div><span>共有</span><div class='siz'> "+num+"</div>件  <br>";
	        	}
	        	document.getElementById("h1").innerHTML=dm;
	        	document.getElementById("h2").innerHTML=dm1;
	        	}
	        	
	        	
	        }
		 });
	 });
	
	
	//$('.techmology_name').labelauty();

	function uniq(array){
    var temp = {}, r = [], len = array.length, val, type;
    for (var i = 0; i < len; i++) {
        val = array[i];
        type = typeof val;
        if (!temp[val]) {
            temp[val] = [type];
            r.push(val);
        } else if (temp[val].indexOf(type) < 0) {
            temp[val].push(type);
            r.push(val);
        }
    }
    return r;
}
});
</script>
