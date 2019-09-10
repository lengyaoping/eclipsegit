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
    #daochu{ display: inline-block; height: 34px;width: 82px; line-height: 31px; border-radius:3px; background-color: #009688; color: #fff; text-align: center;}
	
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
	<a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'openDialog',
       extend: '#userDg-toolbar',
       iconCls: 'fa fa-plus',
       dialog:{
            width: 450,
           height: 450,
           id:'userAddDialog',
           href:'add_place_xu2.jsp?place',
           buttonsGroup:[
               {text:'保存',url:'placeadd_gong.action',iconCls:'fa fa-plus',handler:'ajaxForm',btnCls:'topjui-btn-brown'}
           ]
       }">添加工序</a>
       <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'openDialog',
       extend: '#userDg-toolbar',
       iconCls: 'fa fa-pencil',
       btnCls: 'topjui-btn-green',
       dialog:{
            width: 350,
           height: 450,
           id:'userAddDialog',
           href:'exit_place_xu.jsp?place',
           buttonsGroup:[
               {text:'保存',url:'placeupdatePrice.action',iconCls:'fa fa-plus',handler:'ajaxForm',btnCls:'topjui-btn-brown'}
           ]
       }">编辑单价</a> 
        <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'doAjax',
       extend: '#userDg-toolbar',
       btnCls:'topjui-btn-brown',
       iconCls:'fa fa-trash',
       url:'placedelete_gong.action',
       grid: {uncheckedMsg:'请先勾选要删除的数据',param:'id:id,place_number:place_number'}">删除工序</a>
       <a href="javascript:dayin()" id="daochu"><i class="fa fa-file-excel-o"></i>&nbsp;导出表格</a>
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
            url: 'placefindByT.action?place='+place,
            columns: [[
                {field: 'id', title: 'UID', checkbox: true},
                {field: 'place_number', title: '订单编号', sortable: true},
                {field: 'technology_name', title: '工序名称', sortable: true},
                {field: 'price', title: '工序单价', sortable: true}
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
        
         //打印
        function dayin(){
        	//测试导出exsl
              $.ajax({
	                type : "post",//提交的方法为post
	                url : 'placeTechnoecxlOut.action?place_number='+place,//对应的Action提交的路径
	                dataType: 'json',
	                success : function(data) {//当Ajax提交成功时调用的方法
	                         /* alert(data.message); */
	                          var downurl=data.downurl;
	                          var url="<%=request.getContextPath()%>/download/"+downurl;
	                       /*  alert(url);  */
	                       console.info(url);
	                          window.location.href = url;
                              //$("#download").attr("href",+url);
                              //$('#dd').dialog('open'); 
                         /*  download(downurl); */
	                    }
	                });
	                //console.log("打印");
        }
</script>
</body>
</html>