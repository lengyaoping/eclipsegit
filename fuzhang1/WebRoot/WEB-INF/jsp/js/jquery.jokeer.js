function aa() {
	$("#shu").val('')
}

function my() {
	$('#stat').css('display', 'none');
	$('#my').css('output', 'none');
	$('#output').empty();
	$('#fanh').css('display', 'none');
	$('#my').css('display', 'block');
	$('#final').css('display', 'block');
	$('#full').css('background-image', 'url(static/images/black.gif)');
	setTimeout('aa()', 1000);
}

//function haha() {
//	window.location.href = "http://blog.csdn.net/yjaspire";
//	window.open("http://blog.csdn.net/yjaspire,'_blank'");
//}