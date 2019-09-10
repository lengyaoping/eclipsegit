<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<input type="hidden" id="uuid" name="id">
<table class="editTable">
    <tr>
        <td class="label">员工名字</td>
        <td>
            <input type="text" name="user_name" data-toggle="topjui-textbox" data-options="required:true">
        </td>
        <td class="label">联系方式</td>
        <td>
            <input type="text" name="user_account" data-toggle="topjui-textbox" data-options="required:true,validType:['checkPhone','length[0,11]']">
        </td>
    </tr>
    <tr>
    	<td class="label">分组</td>
        <td>
            <input type="text" name="password" data-toggle="topjui-combobox"
                       data-options="data:[
                       {value:'A',text:'A组',selected:true},
                       {value:'B',text:'B组'},
                       {value:'C',text:'C组'},
                       {value:'D',text:'D组'},{value:'E',text:'E组'},
                       ],required:true,panelHeight:155,">
        </td>
        <td class="label">权限</td>
        <td>
            <!-- <input type="text" name="user_level" data-toggle="topjui-textbox" data-options="required:true"> -->
                <input type="text" name="user_level"
                       data-toggle="topjui-combobox"
                       data-options="data:[{value:1,text:'普通员工',selected:true},{value:2,text:'管理员'},{value:3,text:'三工段'},{value:4,text:'多选'}],required:true,panelHeight:125,">
        </td>
    </tr>
    <tr >
        <td class="label" >入职日期 </td>
        <td>
           <input type="text" name="user_time" data-toggle="topjui-datebox" data-options="required:true">
           <!-- <input name="user_time"
                   data-toggle="topjui-datetimebox"
                   data-options="required:true,
                   width:155,
                   showSeconds:false"> 
        </td>-->
    </tr>
    <tr>
        <td class="label" colspan="1">详细</td>
        <td colspan="2">
           <input type="text" name="remark" data-toggle="topjui-textbox" data-options="">
           <!-- <input name="user_time"
                   data-toggle="topjui-datetimebox"
                   data-options="required:true,
                   width:155,
                   showSeconds:false"> -->
        </td>
    </tr>
</table>

                      
