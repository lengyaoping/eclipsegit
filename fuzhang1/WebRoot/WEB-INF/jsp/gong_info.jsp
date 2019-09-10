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
      <form id="queryForm">
       <input type="text" name="place_time" data-toggle="topjui-datebox" id="start"
               data-options="id:'name',prompt:'时间',width:150" value="javascript:Initialization()">
        <!-- <input type="text" name="endTime" data-toggle="topjui-datebox" id="end"
               data-options="id:'code',prompt:'结束时间',width:150"> -->
        <a href="javascript:void(0)"
           data-toggle="topjui-menubutton"
           data-options="method:'query',
           iconCls:'fa fa-search',
           
           btnCls:'topjui-btn-blue',
           
           form:{id:'queryForm'},
           grid:{type:'datagrid','id':'userDg'}">查询</a>
           </form>
    </div>
    
<!-- 表格工具栏结束 -->

<script>
   var place=request('plan_num');
   //var completed=request('completed');//已完成工序
   //var surplus=request('surplus');//剩余工序
   $(function () {
       /*  //隐藏文本框录入订单号
        $("#pla").val(place); */
        var userDg = {
            type: 'datagrid',
            id: 'userDg'
        };
        $("#userDg").iDatagrid({
            id: 'userDg',
            url: 'placefindListBytime.action?plan_num='+place,
            columns: [[
                {field: 'id', title: 'UID', checkbox: true},
                {field: 'cylinder', title: '工序名称', sortable: true},{field: 'p_number', title: '件数', sortable: true}
                /* {field: 'current', title: '当前工序', sortable: true}, */
                
            ]]
        });
        
        });
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