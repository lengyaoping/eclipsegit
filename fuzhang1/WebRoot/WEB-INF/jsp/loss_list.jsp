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
<style type="text/css">
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
       data-options="method:'search',
       extend: '#userDg-toolbar',
       btnCls:'topjui-btn-blue'">查询</a>
       <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method: 'openDialog',
            extend: '#userDg-toolbar',
            iconCls: 'fa fa-pencil',
            btnCls: 'topjui-btn-green',
            grid: {
                type: 'datagrid',
                id: 'userDg'
            },
            dialog: {
                width: 590,
                height: 250,
                href: 'loss_info.jsp?',
                url: 'codefindLossByid.action?id={id}',
                buttonsGroup: [
                    {
                        text: '更新',
                        url: 'codeupdateLossByid.action',
                        iconCls: 'fa fa-save',
                        handler: 'ajaxForm',
                        btnCls: 'topjui-btn-green'
                    }
                ]
            }">修改信息</a> 
       <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'doAjax',
       extend: '#userDg-toolbar',
       btnCls:'topjui-btn-brown',
       iconCls:'fa fa-trash',
       url:_ctx + 'codedelLossByid.action?place_num='+plan_num,
       grid: {uncheckedMsg:'请先勾选要删除的数据',param:'id:id,p_number:loss_num'}">删除</a>
      <!-- <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'filter',
       extend: '#userDg-toolbar',
       btnCls:'topjui-btn-black'">过滤</a>
       <a href="javascript:dayin()" id="daochu"><i class="fa fa-file-excel-o"></i>&nbsp;导出表格</a> 
      <a href="javascript:void(0)">参与人数  </a> <span class="sum" style="color:red;"></span> 
      <a href="javascript:void(0)">已完成工序 : </a> <span class="gsum" style="color:red;"></span>
      <a href="javascript:void(0)">未完成工序 : </a> <span class="ugsum" style="color:red;"></span>-->
    </div>
<!-- 表格工具栏结束 -->

<script>
    var plan_num=request('plan_num');
   <%--  //打印
    function dayin(){
    	//测试导出exsl
          $.ajax({
             type : "post",//提交的方法为post
             url : 'subReporrinting.action?place_num='+plan_num,//对应的Action提交的路径
             dataType: 'json',
             success : function(data) {//当Ajax提交成功时调用的方法
                       //alert(data.message); 
                       console.info(data);
                       var downurl=data.downurl;
                       var url="<%=request.getContextPath()%>/download/"+downurl;
                    /*  alert(url);  */
                       window.location.href = url;
                          //$("#download").attr("href",+url);
                          //$('#dd').dialog('open'); 
                     /*  download(downurl); */
                 }
             });
             //console.log("打印");
    }
    
    function printReceipt(){ //打印
        bdhtml=window.document.body.innerHTML;    
        sprnstr="<!--startprint-->";    
        eprnstr="<!--endprint-->";    
        prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)+17);    
        prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));    
        window.document.body.innerHTML=prnhtml;    
        window.print();
    }--%>
    $(function () {
      
        var userDg = {
            type: 'datagrid',
            id: 'userDg'
        };
        $("#userDg").iDatagrid({
            id: 'userDg',
            url: 'subgetLossPage.action?place_num='+plan_num,
            columns: [[
                {field: 'id', title: 'UID', checkbox: true},
                //{field: 'place_num', title: '订单号', sortable: true},
                {field: 'color', title: '颜色', sortable: true},
                {field: 'size', title: '大小', sortable: true},
                {field: 'loss_num', title: '数量', sortable: true},
                /* {field: 'price', title: '单价', sortable: true},
                {field: 'money', title: '工钱', sortable: true}, */
                {field: 'loss_type',title:'损耗类型',sortable:true,
                formatter:function(value,row,index){
                        if (value == '1') {
                            return '<font color=\"green\">工艺</font>';
                        } else if (value == '2') {
                            return '<font color=\"orange\">布料</font>';
		                }else if(value == '3'){
		                	return '<font color=\"red\">其他</font>';
		                }
		                }
		                },
                {field: 'remark', title: '录入人', sortable: true}
            ]]
            
        });
        $('#queryBtn').iMenubutton({
            event: 'query',
            iconCls: 'fa fa-search',
            btnCls: 'topjui-btn-danger',
            form: {id: 'queryForm'},
            grid: {type: 'datagrid', 'id': 'userDg'}
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
    // 自定义方法
    /* function myQuery() {
        // 提示信息
        $.iMessager.alert('自定义方法', '自定义方法被执行了！', 'messager-info');
        // 提交参数查询表格数据
        $('#productDg').iDatagrid('reload', {
            name: $('#name').iTextbox('getValue'),
            code: $('#code').iTextbox('getValue')
        });
    } */
</script>
</body>
</html>