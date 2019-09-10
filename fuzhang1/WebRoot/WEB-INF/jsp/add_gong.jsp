<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<input type="hidden" id="uuid" name="id">
<table class="editTable">
    <tr>
        <!-- <td class="label">工序编号</td>
        <td>
            <input type="text" name="number" data-toggle="topjui-textbox" data-options="required:true">
        </td> -->
        <td class="label">工序名称</td>
        <td>
            <input type="text" name="name" data-toggle="topjui-textbox" data-options="required:true,validType:['checkMaterialP','length[0,20]']">
        </td>
    </tr>
    <tr>
        <!-- <td class="label">工序编号</td>
        <td>
            <input type="text" name="number" data-toggle="topjui-textbox" data-options="required:true">
        </td> -->
        <td class="label">工序单价</td>
        <td>
            <input type="text" name="number" data-toggle="topjui-numberbox" data-options="prompt:'',required:true,min:0,groupSeparator:'',validType:['length[0,7]'],precision:3">
        </td>
    </tr>
</table>


                      
