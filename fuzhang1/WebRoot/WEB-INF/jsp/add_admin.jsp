<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<input type="hidden" id="uuid" name="admin_id">
<table class="editTable">
    <tr>
        <td class="label">姓名</td>
        <td>
            <input type="text" name="admin_name" data-toggle="topjui-textbox" data-options="required:true">
        </td>
        <td class="label">账号</td>
        <td>
            <input type="text" name="admin_account" data-toggle="topjui-textbox" data-options="required:true">
        </td>
    </tr>
    <tr>
    	<td class="label">密码</td>
        <td>
            <input type="password" name="password" data-toggle="topjui-textbox"
                       data-options="required:true">
        </td>
        <td class="label">权限</td>
        <td>
            <!-- <input type="text" name="user_level" data-toggle="topjui-textbox" data-options="required:true"> -->
                <input type="text" name="admin_level"
                       data-toggle="topjui-combobox"
                       data-options="data:[{value:1,text:'管理员'}],required:true,panelHeight:125,">
        </td>
    </tr>
   
</table>

                      
