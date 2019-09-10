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
<title>二维码页面</title>
<!-- TopJUI框架样式 -->
     <link type="text/css" href="${pageContext.request.contextPath}/topjui/css/topjui.core.min.css" rel="stylesheet">
    <link type="text/css" href="${pageContext.request.contextPath}/topjui/themes/default/topjui.red.css" rel="stylesheet" id="dynamicTheme"/>
    <script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.jqprint-0.3.js"></script>
<script type="text/javascript" src="js/jquery.browser.js"></script>
<script type="text/javascript" src="js/jquery.qrcode.min.js"></script>
<script type="text/javascript" src="js/jquery.jokeer.js"></script>
<style type="text/css">
    p{
        margin: 0;
        padding: 0;
        -webkit-tap-highlight-color: rgba(0,0,0,0);
        font: 12px/normal "microsoft yahei","Times New Roman","宋体",Times,serif;
        letter-spacing: 1px;
    }
    .one{
        width:170px;
        height:120px;
        border:1px red solid;
        background-color: white;
        float:left;
    }
    .two{
        width:170px;
        height:150px;
        border:1px red solid;
        background-color: white;
        float:left;
    }
    .three{
        width:170px;
        height:175px;
        border:1px red solid;
        background-color: white;
        float:left;
        text-align:center;
        padding-bottom:50px;
    }
    .simg{
    	width:70px;
    	height:70px;
    }
  	
    .small-font{
        font-size: 12px;
        -webkit-transform-origin-x: 0;
        -webkit-transform: scale(0.75);
        line-height: 10px;
    }
    .smallsize-font {
        　　　　font-size:9.8px;
    }
    .bh1{
    	
    	transform:rotate(90deg);
        -ms-transform:rotate(90deg); /* Internet Explorer 9*/
        -moz-transform:rotate(90deg); /* Firefox */
        -webkit-transform:rotate(90deg); /* Safari 和 Chrome */
        -o-transform:rotate(90deg); /* Opera */
        filter: progid:DXImageTransform.Microsoft.BasicImage(rotation=1);
        margin-left:0px;
    }
    body{
        background-color: gray;
    }
    input[name="start"]{width: 80px;}
    input[name="end"]{width: 80px;}
    #bw{
        width:150px;
        height:25px;
    }
    hr{
    	height:2px;
    	border:none;
    	border-top:2px solid #185598;
    }
</style>
    
</head>

<body id="body">
<!--startprint-->
<input style="width:150px;height:35px" onclick="preview()" value="打印二维码" type="button" />
<input type="number" name="start" placeholder="开始包数">
<span style="color:#fff">~~~</span>
<input type="number" name="end" placeholder="结束包数">
<input type="submit" id="scsc" value="选择" style="width:150px;height:35px">
<select id="dyStyle" style="width:150px;height:35px">
    <option>效果1</option>
    <option>效果2</option>
    <option>效果3</option>
</select>
<input type="text" value="" id ="bw" placeholder="填写部位"><button style="width:150px;height:35px" id="writeBW" onclick="upBw()">确定填写</button>
<div class="codeBox"></div>
<!--endprint-->



</body>
<!-- <script language="javascript" src="LodopFuncs.js"></script>
<object  id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0>
       <embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed>
</object> -->
<script type="text/javascript" src="js/jquery.jokeer.js"></script>
<script type="text/javascript">

    $("#dyStyle").change(function(){//code...
        //console.info(111);
        document.getElementById("scsc").click();
        //$("#scsc").onclick();
    });
    //根据订单号查询所有的二维码信息


    function utf16to8(str) {
        var out, i, len, c;
        out = "";
        len = str.length;
        for(i = 0; i < len; i++) {
            c = str.charCodeAt(i);
            if((c >= 0x0001) && (c <= 0x007F)) {
                out += str.charAt(i);
            } else if(c > 0x07FF) {
                out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));
                out += String.fromCharCode(0x80 | ((c >> 6) & 0x3F));
                out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
            } else {
                out += String.fromCharCode(0xC0 | ((c >> 6) & 0x1F));
                out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
            }
        }
        return out;
    }

    //proDuctCode(ArrCode);

    //打印
    function preview(){
        /* bdhtml=window.document.body.innerHTML;
        sprnstr="<!--startprint-->";
        eprnstr="<!--endprint-->";
        prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)+17);
        prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));
        window.document.body.innerHTML=prnhtml;
        window.print();*/
        print();
        /* var tata=document.execCommand("print");
        if(tata){
            console.log("取消打印");
        } */
    }
    function print(){
        $(".codeBox").jqprint({
            debug:false,
            importCSS:true,
            printContainer:true,
            operaSupport:false
        });
    }
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
    $("#scsc").click(function(){
        var start = Number($("input[name='start']").val());
        var end = Number($("input[name='end']").val());
        if((!(/(^[1-9]\d*$)/.test(start)) || !(/(^[1-9]\d*$)/.test(end))) || end<start){
            alert("请准确输入！");
        }else if(end-start>399){
            alert("一次最多打印400包!");
        }else{
            start=start-1;
            var id=request('id');
            console.info(id);
            $.ajax({
                url: "codefindById.action?place_num="+id,
                type: "post",
                dataType: "json",
                async: !1,
                success: function(data) {
                    if(end>data.rows.length){
                        alert("该订单最多"+data.rows.length+"个包！");
                        return;
                    }
                    //var size=data.rows.length;
                    var size=end-start;
                    var ArrCode = [size];//需要生成二维码的数据
                    for(var j=0;j<size;j++){
                        var num = Number(j)+Number(start);
                        ArrCode[j]=data.rows[num].packe_num;
                    }
                    if(ArrCode) {
                        var _html = "";
                        var sty = $("#dyStyle").val();
                        //console.info("打印效果：---"+sty);
                        if(sty == "效果1"){
                            //console.info("打印效果：---1");
                            for(var i = 0; i < ArrCode.length; i++) {
                                var num = Number(i)+Number(start);
                                var packe = data.rows[num];
                                //console.info(packe);
                                var packnum = packe.packe_num;
                                var bao=packnum.substr(packnum.length-4);
                                _html+="<div class='one' id='code" + num + "' ><p>生产号:"+packe.p_num+"</p><p >件数:"+packe.p_number+"&nbsp;颜色:"+packe.p_color+"</p><img id='img" + num + "'/><span class='buwei'></span><p >尺码:"+packe.p_size+"&nbsp;包号:"+bao+"</p></div>";
                            }
                        }else if(sty == "效果2"){
                            //console.info("打印效果：---2");
                            for(var i = 0; i < ArrCode.length; i++) {
                                var num = Number(i)+Number(start);
                                var packe = data.rows[num];
                                var packnum = packe.packe_num;
                                var bao=packnum.substr(packnum.length-4);
                                _html+="<div class='two' id='code" + num + "' ><p style='padding-top:0px'>生产号:"+packe.p_num+"</p><p>件数:"+packe.p_number+"&nbsp;颜色:"+packe.p_color+"</p><img id='img" + num + "'/><span class='buwei'></span><p style='margin-bottom:11px;'>尺码:"+packe.p_size+"&nbsp;包号:"+bao+"</p><p style='font-size:  9.21px;-webkit-transform-origin-x: 0;width:240px;-webkit-transform: scale(0.71);line-height: 12px;'> 生产号:"+packe.p_num+"</p><p style='font-size: 9.21px;-webkit-transform-origin-x: 0;width:240px;-webkit-transform: scale(0.71);line-height: 12px;'> 颜色:"+packe.p_color+" 尺码:"+packe.p_size+"</p><p style='font-size:  9.21px;-webkit-transform-origin-x: 0;width:240px;-webkit-transform: scale(0.71);line-height:12px;padding-bottom:10px;'> 包号:"+bao+"  件数:"+packe.p_number+"</p></div>";
                                //_html+="<div class='two' id='code" + num + "' ><p style='margin-top:3px;'>生产号:"+packe.p_num+"</p><p>件数:"+packe.p_number+"&nbsp;颜色:"+packe.p_color+"</p><img id='img" + num + "'/><p style='margin-bottom:15px;'>尺码:"+packe.p_size+"&nbsp;包号:"+bao+"</p><p style='font-size:15px;'>陶：13767984080</p></div>";

                            }
                        }else if(sty == "效果3"){
                            //console.info("打印效果：---2");
                            for(var i = 0; i < ArrCode.length; i++) {
                                var num = Number(i)+Number(start);
                                var packe = data.rows[num];
                                var packnum = packe.packe_num;
                                var bao=packnum.substr(packnum.length-4);
                               	//_html+="<div class='three' id='code" + num + "' ><div class='simg'><img style='float:left;' id='img" + num + "'/><p>颜色:"+packe.p_color+"</p><p >尺码:"+packe.p_size+"</p></div><div class='bh'>编号:"+packnum+"</div></div>";
_html+="<div class='three' id='code" + num + "' ><p style='padding-top:5px'>生产号</p><p>"+packe.p_num+"</p><p>包号:"+bao+"</p><img class='simg' id='img" + num + "'/><p >款号</p><p >"+packe.girard+"</p><p >尺码</p><p style='font-size: 25.21px;'>"+packe.p_size+"</p><hr style='padding-bottom:10px'/></div>";
                            }
                        }


                        $(".codeBox").html("");
                        $(".codeBox").html(_html);
                        /*
                        for(var i = 0; i < ArrCode.length; i++) {
                            var num = Number(i)+Number(start);
                            $('#code' + num).qrcode({
                                width: 50,
                                height: 50,
                                correctLevel: 0,
                                text: utf16to8(ArrCode[i])
                            });
                            var canvas=$('#code' + num).find('canvas').get(0);
                            var data = canvas.toDataURL('image/jpg');
                            $('#img'+num).attr('src',data) ;
                            $('#code' + num).find('canvas').remove();
                        }
                        */
                        for(var i = 0; i < ArrCode.length; i++) {
                            var num = Number(i)+Number(start);
                            $('#code' + num).qrcode({
                                width: 50,
                                height: 50,
                                correctLevel: 0,
                                text: utf16to8(ArrCode[i])
                            });
                            var canvas=$('#code' + num).find('canvas').get(0);
                            var data = canvas.toDataURL('image/jpg');
                            $('#img'+num).attr('src',data) ;
                            $('#code' + num).find('canvas').remove();
                        }
                        
                    } else {
                        console.log("输入失败");
                    }
                }
            });
        }
        //alert("start="+start+",end="+end);
    });
    
    function upBw() {
        var bwText = $("#bw").val();
        console.info("--"+bwText);
        $(".buwei").text("部位:"+bwText);
        /*$(".buwei").each(function (index) {
            console.info(this);
            this.innerHTML(bwText);
        })*/
    }
</script>
</html>
