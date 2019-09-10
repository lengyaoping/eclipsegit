<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <!-- 避免IE使用兼容模式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="renderer" content="webkit">
     <%@ include file="ap.jsp" %>
</head>

<body>
<div data-toggle="topjui-layout" data-options="fit:true">
    <div data-options="region:'center',iconCls:'icon-reload',title:'',split:true,border:false,bodyCls:'border_left_right'">
        <!-- datagrid表格 -->
        <table data-toggle="topjui-datagrid"
               data-options="id:'userDg',
               singleSelect:true,
				selectOnCheck:false,
				checkOnSelect:false,
                    url:'admingetAdmin.action',
                    childTab: [{id:'eastTabs'}],
                    filter: [{
                        field: 'adminName',
                        type: 'textbox',
                        op: ['contains', 'equal', 'notequal', 'less', 'greater']
                    },{
                        field: 'sex',
                        type: 'combobox',
                        options: {
                            valueField: 'value',
                            textField: 'label',
                            data: [{
                                label: '男',
                                value: '1'
                            }, {
                                label: '女',
                                value: '2'
                            }]
                        },
                        op: ['contains', 'equal', 'notequal', 'less', 'greater']
                    },{
                        field: 'post',
                        type: 'combobox',
                        options: {
                            valueField: 'value',
                            textField: 'label',
                            multiple: true,
                            data: [{
                                label: 'CEO',
                                value: 'CEO'
                            }, {
                                label: 'COO',
                                value: 'COO'
                            }, {
                                label: 'CTO',
                                value: 'CTO'
                            }]
                        },
                        op: ['contains', 'equal', 'notequal', 'less', 'greater']
                    }]">
            <thead>
            <tr>
                <th data-options="field:'id',title:'UUID',checkbox:true"></th>
               
                <th data-options="field:'admin_name',title:'姓名',sortable:true"></th>
                <th data-options="field:'admin_account',title:'账号',sortable:true"></th>
               
               
                <th data-options="field:'admin_level',title:'权限',sortable:true,
                    formatter:function(value,row,index){
                       
                            return '<font color=\'red\'>管理</font>';
                        
                    }"></th>
                 
            </tr>
            </thead>
        </table>
    </div>
    <div data-options="region:'east',iconCls:'icon-chart_pie',title:'',split:true,border:false,width:'50%'">
        <div data-toggle="topjui-tabs"
             data-options="id:'eastTabs',
             fit:true,
             border:true,
             bodyCls:'border_right_none',
             parentGrid:{
                 type:'datagrid',
                 id:'adminDg',
                 param:'puuid:uuid'
             }">

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
           width: 650,
           height: 380,
           id:'adminAddDialog',
           href:'add_admin.jsp',
           buttonsGroup:[
               {text:'保存',url:'adminadd.action',iconCls:'fa fa-plus',handler:'ajaxForm',btnCls:'topjui-btn-brown'}
           ]
       }">新增员工</a>
    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method: 'openDialog',
            extend: '#userDg-toolbar',
            iconCls: 'fa fa-pencil',
            btnCls: 'topjui-btn-green',
            grid: {
                type: 'datagrid',
                id: 'adminDg'
            },
            dialog: {
                width: 650,
                height: 380,
                href: 'add_admin.jsp?id={admin_id}',
                url:'adminfindById.action?id={admin_id}',
                buttonsGroup: [
                    {
                        text: '更新',
                        url:'adminupdate.action',
                        iconCls: 'fa fa-save',
                        handler: 'ajaxForm',
                        btnCls: 'topjui-btn-green'
                    }
                ]
            }">编辑员工信息</a>
             <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method: 'openDialog',
            extend: '#userDg-toolbar',
            iconCls: 'fa fa-plus',
            btnCls: 'topjui-btn-orange',
            grid: {
                type: 'datagrid',
                id: 'adminDg'
            },
            dialog: {
                width: 780,
                height: 450,title:'手动添加提交信息',
                href: 'subInfo.jsp',
                buttonsGroup: [
                    {
                        text: '提交',
                        url:'subbatchSubmit1.action',
                        iconCls: 'fa fa-save',
                        handler: 'ajaxForm',
                        btnCls: 'topjui-btn-green'
                    }
                ]
            }">手动提交</a>
    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'doAjax',
       extend: '#userDg-toolbar',
       btnCls:'topjui-btn-brown',
       iconCls:'fa fa-trash',
       url:'userdelete.action',
       grid: {uncheckedMsg:'请先勾选要删除的数据',param:'ids:id,job_number:job_number,user_name:user_name'}">删除员工</a>
    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'filter',
       extend: '#userDg-toolbar',
       btnCls:'topjui-btn-black'">过滤</a>
   <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'search',
       extend: '#userDg-toolbar',
       btnCls:'topjui-btn-blue'">查询</a>
    </div>
<!-- 表格工具栏结束 -->
</body>
</html>