<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<input id="pnum" name="packe_num" type="hidden" value=""/>
<input id="num" name="p_num" type="hidden" value=""/>

<input id="girard" name="girard" type="hidden" value=""/>
<input id="cylinder" name="cylinder" type="hidden" value=""/>
<table class="editTable">
    <tr>
        <td class="label">颜色</td>
        <td>
            <input type="text" name="p_color" data-toggle="topjui-textbox" data-options="required:true,validType:['length[0,10]']">
        </td>
        <td class="label">尺码</td>
        <td>
            <input type="text" name="p_size" data-toggle="topjui-textbox" data-options="required:true,validType:['length[0,10]']">
        </td>
    </tr>
    <tr>
    	
        <td class="label">件数</td>
        <td><input type="hidden" name="id">
            <input type="text" name="p_number" data-toggle="topjui-numberbox" data-options="required:true,groupSeparator:'',validType:['length[0,10]'],min:1">
        </td>
        
    </tr>
    <tr><td class="label" id = "dius">损耗类型</td>
        <td>
            <input type="text" name="loss_type" data-toggle="topjui-combobox"
            data-options="data:[
            			{value:'0',text:'不选择',selected:true},
                       {value:'1',text:'工艺'},
                       {value:'2',text:'布料'},
                       {value:'3',text:'其他'}
                       ],required:true,panelHeight:155,">
        </td>
    </tr>
    
</table>
<script>
 //request('place');
        var pla=window.place; 
      /* var linkDataProperty = $("#plan_num").val(); */
        if(pla != null && pla != ""){
       		$("#num").val(pla);
       		}else{
       			alert("请先选中订单！");
       		}
       
</script>
                      
