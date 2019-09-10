<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<table class="editTable">
    
     <tr>
        <td class="label">用户名</td>
        <td>
                 <input type="text" name="admin_name" data-toggle="topjui-textbox"
                        data-options="required:true">    
        </td>
    </tr>
    <tr>
        <td class="label">账号</td>
        <td>
             <input id="pla" type="hidden" name="adminAccount" value="" data-toggle="topjui-textbox"
                        data-options="required:true">  
        </td>
    </tr>
    <tr>
        <td class="label">旧密码</td>
        <td>
            <input type="text" name="adminPassword"  data-toggle="topjui-textbox"
                   data-options="required:true">    
        </td>
    </tr>
    <tr>
        <td class="label">新密码</td>
        <td>
            <input type="text" name="password" data-toggle="topjui-textbox"
                   data-options="required:true">    
        </td>
    </tr>
    <tr>
        <td class="label">确认密码</td>
        <td>
            <input type="text" name="password" data-toggle="topjui-textbox"
                   data-options="required:true">    
        </td>
    </tr>
     
</table>

<script>
 //request('place');
 var pla=window.place;
  $(function () {
       //隐藏文本框录入订单号
       $("#pla").val(pla);
  });
</script>
