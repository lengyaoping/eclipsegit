<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<input id="num" name="id" type="text" value=""/>
<input id="num" name="place_num" type="text" value=""/>
<table class="editTable">
    <tr>
        <td class="label">颜色</td>
        <td>
            <input type="text" name="color" data-toggle="topjui-textbox" data-options="required:true,validType:['length[0,10]']">
        </td>
        <td class="label">尺码</td>
        <td>
            <input type="text" name="size" data-toggle="topjui-textbox" data-options="required:true,validType:['length[0,10]']">
        </td>
    </tr>
    <tr>
    	
        <td class="label">件数</td>
        <td><input type="hidden" name="fake">
            <input type="text" name="loss_num" data-toggle="topjui-numberbox" data-options="required:true,groupSeparator:'',validType:['length[0,10]'],min:1">
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
 		/* var id = request('id');
        var pla=window.id; 
        console.info(id);
        console.info(pla); */
      /* var linkDataProperty = $("#plan_num").val(); */
        /*if(pla != null && pla != ""){
       		$("#num").val(pla);
       		}else{
       			alert("请先选中订单！");
       		}*/
       
</script>
                      
