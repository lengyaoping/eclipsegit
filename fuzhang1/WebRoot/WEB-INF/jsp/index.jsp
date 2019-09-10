<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <meta name="keywords" content='easyui,jui,jquery easyui,easyui demo,easyui中文'/>
    <meta name="description" content='全球服装工序管理系统'/>
    <title>${sessionScope.admin.admin_name }中国服装工序管理系统</title>
    <!-- 浏览器标签图片 -->
    <link rel="shortcut icon" href="../topjui/images/favicon.ico"/>
    <!-- TopJUI框架样式 -->
    <link type="text/css" href="../topjui/css/topjui.core.min.css" rel="stylesheet">
    <link type="text/css" href="../topjui/themes/default/topjui.blue.css" rel="stylesheet" id="dynamicTheme"/>
    <!-- FontAwesome字体图标 -->
    <link type="text/css" href="../static/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet"/>
    <!-- jQuery相关引用 -->
    <script type="text/javascript" src="../static/plugins/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="../static/plugins/jquery/jquery.cookie.js"></script>
    <!-- TopJUI框架配置 -->
    <script type="text/javascript" src="../static/public/js/topjui.config.js"></script>
    <!-- TopJUI框架核心 -->
    <script type="text/javascript" src="../topjui/js/topjui.core.min.js"></script>
    <!-- TopJUI中文支持 -->
    <script type="text/javascript" src="../topjui/js/locale/topjui.lang.zh_CN.js"></script>
    <!-- 首页js -->
    <script type="text/javascript" src="../static/public/js/topjui.index.js" charset="utf-8"></script>
    
</head>

<body>
<script>
 function helpdocument(){
      var date=new Date();
      var url="<%=request.getContextPath()%>/helpdocument/help.doc";
      window.location.href=url;
  }
 
  function connection() {
    var opts = {
        id: 'pwdDialog',
        title: '扫一扫加微信',
        width: 450,
        height: 455,
        href: 'connection.html',
    };
    $('#' + opts.id).iDialog('openDialog', opts);
}; 
   
   function logout(){
     $.ajax({
           type:"post",
           url:"loginloginout.action",
           success:function () { 
	           window.location.href="login.jsp";
	        }, 
	       error:function () { 
	          alert("2");     
	       } 
	   });
   }
   function updatePwd(){
	   /*var opts = {
	        id: 'pDialog',
	        title: '修密码',
	        width: 450,
	        height: 455,
	        href: 'modifyPassword.html',
	    };*/
	    var opts = {
                id: 'adminInfo',
                title: '修改密码',
                iconCls: 'fa fa-binoculars',
                href: "adminInfo.html",
                height:400,
                width:500,
             
          };
	    $('#' + opts.id).iDialog('openDialog', opts);
	}
	
</script>

<style>  
        #iframerRight{  
            width: 50%;  
            height: 100%;  
             float: right;  
        }  
        #iframeLeft{  
            width: 50%;  
            height: 100%;  
            float: left;  
        }    
</style>  
<div id="loading" class="loading-wrap">
    <div class="loading-content">
        <div class="loading-round"></div>
        <div class="loading-dot"></div>
    </div>
</div>

<div id="mm" class="submenubutton" style="width: 140px;">
    <div id="mm-tabclose" name="6" iconCls="fa fa-refresh">刷新</div>
    <div class="menu-sep"></div>
    <div id="Div1" name="1" iconCls="fa fa-close">关闭</div>
    <div id="mm-tabcloseother" name="3">关闭其他</div>
    <div id="mm-tabcloseall" name="2">关闭全部</div>
    <div class="menu-sep"></div>
    <div id="mm-tabcloseright" name="4">关闭右侧标签</div>
    <div id="mm-tabcloseleft" name="5">关闭左侧标签</div>
    <div class="menu-sep"></div>
    <div id="mm-newwindow" name="7">新窗口中打开</div>
</div>
 


<div data-toggle="topjui-layout" data-options="id:'index_layout',fit:true">
    <div id="north" class="banner" data-options="region:'north',border:false,split:false"
         style="height: 50px; padding:0;margin:0; overflow: hidden;">
        <table style="float:left;border-spacing:0px;">
            <tr>
                <td class="webname">
                    <span class="fa fa-envira" style="font-size:26px; padding-right:8px;"></span>${sessionScope.admin.admin_name }南昌服装工序管理系统
                </td>
                <td class="collapseMenu" style="text-align: center;cursor: pointer;">
                    <span class="fa fa-chevron-circle-left" style="font-size: 18px;"></span>
                </td>
                <td>
                    <table id="topmenucontent" cellpadding="0" cellspacing="0">
                        <td id="1" title="生产管理" class="topmenu selected systemName">
                            <a class="l-btn-text bannerMenu" href="javascript:void(0)">
                                <p>
                                    <lable class="fa fa-gears"></lable>
                                </p>
                                <p><span style="white-space:nowrap;">生产管理</span></p>
                            </a>
                        </td>
                    </table>
                </td>
            </tr>
        </table>

        <span style="float: right; padding-right: 10px; height: 50px; line-height: 50px;">
            <!-- <a href="javascript:connection()" data-toggle="topjui-menubutton"
               data-options="iconCls:'fa fa-user',hasDownArrow:false"
               style="color:#fff;">联系我们帮助</a>| -->
              <!--   <a href="javascript:void(0)" onclick="logout()" data-toggle="topjui-menubutton"
               data-options="iconCls:'fa fa',hasDownArrow:false" style="color:#fff;">查看更新</a>-->
            <a href="javascript:void(0)" data-toggle="topjui-menubutton"
               data-options="menu:'#con',iconCls:'fa fa-user',hasDownArrow:true"
               style="color:#fff;">联系我们帮助</a>|
              <div id="con" style="width:37px;">
                <div data-options="iconCls:'fa fa-info-circle'" onclick="javascript:connection()">联系我们</div>
             
                <div data-options="iconCls:'fa fa-key'" onclick="javascript:helpdocument()">下载帮助文档</div>
              </div>
            <a href="javascript:void(0)" id="mb3" data-toggle="topjui-menubutton"
               data-options="menu:'#mm3',iconCls:'fa fa-cog',hasDownArrow:true" style="color:#fff;">设置</a>
            <div id="mm3" style="width:74px;">
        <!--         <div data-options="iconCls:'fa fa-info-circle'" onclick="javascript:modifyPwd(0)">个人信息</div>
                <div class="menu-sep"></div> -->
                <div data-options="iconCls:'fa fa-key'" onclick="javascript:updatePwd()">修改密码</div>
            </div>|
            <a href="javascript:void(0)" id="mb2" data-toggle="topjui-menubutton"
               data-options="menu:'#mm2',iconCls:'fa fa-tree',hasDownArrow:true" style="color:#fff;">主题</a>|
            <div id="mm2" style="width:180px;">
                <div data-options="iconCls:'fa fa-tree blue'" onclick="changeTheme('blue')">默认主题</div>
                <div data-options="iconCls:'fa fa-tree'" onclick="changeTheme('black')">黑色主题</div>
                <div data-options="iconCls:'fa fa-tree'" onclick="changeTheme('blacklight')">黑色主题-亮</div>
                <div data-options="iconCls:'fa fa-tree red'" onclick="changeTheme('red')">红色主题</div>
                <div data-options="iconCls:'fa fa-tree red'" onclick="changeTheme('redlight')">红色主题-亮</div>
                <div data-options="iconCls:'fa fa-tree green'" onclick="changeTheme('green')">绿色主题</div>
                <div data-options="iconCls:'fa fa-tree green'" onclick="changeTheme('greenlight')">绿色主题-亮</div>
                <div data-options="iconCls:'fa fa-tree purple'" onclick="changeTheme('purple')">紫色主题</div>
                <div data-options="iconCls:'fa fa-tree purple'" onclick="changeTheme('purplelight')">紫色主题-亮</div>
                <div data-options="iconCls:'fa fa-tree blue'" onclick="changeTheme('blue')">蓝色主题</div>
                <div data-options="iconCls:'fa fa-tree blue'" onclick="changeTheme('bluelight')">蓝色主题-亮</div>
                <div data-options="iconCls:'fa fa-tree orange'" onclick="changeTheme('yellow')">橙色主题</div>
                <div data-options="iconCls:'fa fa-tree orange'" onclick="changeTheme('yellowlight')">橙色主题-亮</div>
            </div>
            <a href="javascript:void(0)" onclick="logout()" data-toggle="topjui-menubutton"
               data-options="iconCls:'fa fa-sign-out',hasDownArrow:false" style="color:#fff;">注销</a>
        </span>
    </div>

    <div id="west"
         data-options="region:'west',split:true,width:230,border:false,headerCls:'border_right',bodyCls:'border_right'"
         title="" iconCls="fa fa-dashboard">
        <div id="RightAccordion"></div>
        <!--<div id="menuTab" class="topjui-tabs" data-options="fit:true,border:false">
            <div title="导航菜单" data-options="iconCls:'fa fa-sitemap'" style="padding:0;">
                <div id="RightAccordion" class="topjui-accordion"></div>
            </div>
            <div title="常用链接" data-options="iconCls:'fa fa-star',closable:true">
                <ul id="channgyongLink"></ul>
            </div>
        </div>-->
    </div>

    <div id="center" data-options="region:'center',border:false" style="overflow:hidden;">
        <div id="index_tabs" style="width:100%;height:100%">
	     <div title="系统首页" iconCls="fa fa-home" data-options="border:true,iframe:true">
	    	<iframe id="iframeLeft" src='home.jsp' scrolling='auto' frameborder='0' style='width:55%;height:100%;'></iframe>
	    	<iframe id="iframeRight" src='../image/liu.png' scrolling='auto' frameborder='0' style='width:45%;height:100%;'></iframe>
	     </div>  
        </div> 
  </div>
    <div data-options="region:'south',border:true"
         style="text-align:center;height:30px;line-height:30px;border-bottom:0;overflow:hidden;">
        <span style="float:left;padding-left:5px;width:30%;text-align: left;">登录用户名：${sessionScope.admin.admin_name }</span>
        <span style="padding-right:5px;width:40%">
            版权所有 © 2018-2023
            <a href="http://www.0791youxi.com" target="_blank">江西秋天传媒有限公司</a>
            <!-- <a href="http://www.miitbeian.gov.cn" target="_blank"></a> -->
        </span>
        <span style="float:right;padding-right:5px;width:30%;text-align: right;">版本：v3.3.0</span>
    </div> 
</div>
 
<!--[if lte IE 8]>
<div id="ie6-warning">
    <p>您正在使用低版本浏览器，在本页面可能会导致部分功能无法使用，建议您升级到
        <a href="http://www.microsoft.com/china/windows/internet-explorer/" target="_blank">IE9或以上版本的浏览器</a>
        或使用<a href="http://se.360.cn/" target="_blank">360安全浏览器</a>的极速模式浏览
    </p>
</div>
<![endif]-->

</body>
</html>

