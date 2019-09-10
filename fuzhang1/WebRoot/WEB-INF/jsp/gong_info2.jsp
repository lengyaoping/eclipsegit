<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <!-- 避免IE使用兼容模式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="renderer" content="webkit">
    <!-- 导入公共样式 -->
    <%@ include file="ap.jsp" %>
    
    
   
</head>
<style>
   #printTable td,th{
   
       font-size:16px;
   }
</style>
<body>
<!-- layout布局 开始 -->
<div data-toggle="topjui-layout" data-options="fit:true">
    <div data-options="region:'center',iconCls:'icon-reload',title:'',split:true,border:false">
        <div data-toggle="topjui-layout" data-options="fit:true">
            <div data-options="region:'center',title:'',fit:false,split:true,border:false,bodyCls:'border_right_bottom'"
                 style="height:80%">
                <!-- datagrid表格 -->
                <table data-toggle="topjui-datagrid"
                       data-options="id:'userDg',
                      <!--  pagination:false, -->
                         singleSelect:true,
				    selectOnCheck:false,
				    checkOnSelect:false,
			           ">
                    
                </table>
            </div>
            
        </div>
    </div>
    
</div>

<div data-toggle="topjui-layout" data-options="fit:true" >
    <div data-options="region:'center',iconCls:'icon-reload',title:'',split:true,border:false">
        <div data-toggle="topjui-layout" data-options="fit:true">
            <div data-options="region:'center',title:'',fit:false,split:true,border:false,bodyCls:'border_right_bottom'"
                 style="height:80%">
                <!-- datagrid表格 -->
                <table data-toggle="topjui-datagrid"
                       data-options="id:'userDg01',
                      <!--  pagination:false, -->
                         singleSelect:true,
				    selectOnCheck:false,
				    checkOnSelect:false,
			           ">
                    
                </table>
            </div>
            
        </div>
    </div>
    
</div>
<!-- 表格工具栏开始 -->
<div id="userDg-toolbar" class="topjui-toolbar"
     data-options="grid:{
           type:'datagrid',
           id:'userDg'
       }">
       
      
</div>
    
<!-- 表格工具栏结束 -->

<script>
   var place=request('plan_num');
   $(function () {
       /*  //隐藏文本框录入订单号
        $("#pla").val(place); */
        var userDg = {
            type: 'datagrid',
            id: 'userDg'
        };
        $("#userDg").iDatagrid({
            id: 'userDg',
            url: 'placeplaceSchedule.action?place='+place,
            columns: [[
                {field: 'id', title: 'UID', checkbox: true},
                {field: 'p_num', title: '订单号', sortable: true},
                {field: 't_name', title: '工序名称', sortable: true},
                {field: 'cp_piece_num', title: '已完成件数', sortable: true},
                {field: 'performance', title: '已完成/总数量', sortable: true,formatter:performance},
                {field:'rate',title:'完成率',sortable:true,formatter:progressFormatter},
                {field:'buttom',title:'操作',sortable:true,formatter:function(value, row, index){
                	//console.info(row);
	                var plnum = row.t_name;
					var str = "<button class='layui-btn layui-btn-xs' onclick=openDiag(/"+plnum+"/)>查看</button>";
					return str;
				}}
                
            ]]
        });
       });
       function performance(value,row,index){
	       var completed=Number(row['completed']);
		   var unfinished=Number(row['unfinished']);
		   var he = Number(completed+unfinished);
		   return completed+" / "+he;
       }
   function openDiag(p){
   		str=p+"";
   		str=str.replace("/","").replace("/","");
   		//console.info(p+"--"+str);
   		$(this).iDialog('openDialog', {
                id: 'businessDialog2',
                title: str,
                iconCls: 'fa fa-binoculars',
                href: "jinduInfo1.jsp?str="+str,
                height:500,
                width:500,
          });
   }   
   function progressFormatter(value, row, index) {
	   var completed=Number(row['completed']);
	   var unfinished=Number(row['unfinished']);
	   var he = Number(completed+unfinished);
	   //console.info(he+"---"+completed);
	   value=parseInt(completed/he*100);
       var htmlstr = '<div id="p" class="topjui-progressbar progressbar" data-options="value:' + value + '" style="width: 100px; height: 26px;">';
       htmlstr += '<div class="progressbar-text" style="width: 100px; height: 26px; line-height: 26px;">' + value + '%</div>';
       htmlstr += '<div class="progressbar-value" style="width: ' + value + '%; height: 26px; line-height: 26px;">';
       htmlstr += '<div class="progressbar-text" style="width: 100px; height: 26px; line-height: 26px;">' + value + '%</div>';
       htmlstr += '</div>';
       htmlstr += '</div>';
       return htmlstr;
   }
   
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
</body>
</html>