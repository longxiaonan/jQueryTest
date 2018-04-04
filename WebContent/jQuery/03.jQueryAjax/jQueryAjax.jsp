<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>菜鸟教程(runoob.com)</title>
<script src="https://cdn.bootcss.com/jquery/1.10.2/jquery.min.js">
</script>
<script>
$(document).ready(function(){
	var personObj={
		name:"logn",
		age:123,
		gender:"man"
	};
	//序列化
	$("#serialize").click(function(){
		//alert(personOb);	      //Object:Object
		alert($.param(personObj));//序列化后的字符串, 其实ajax提交到后台时, 会自动序列化json对象
	});
	//get方法ajax访问
	$("#getButton").click(function(){
		alert("1111");
		$.get("http://www.baidu.com/"+"4",$.param(personObj), function(data){
			alert(data);
		})
	});
	//post方法ajax访问
	$("#postButton").click(function(){
		alert("2222");
		$.post("http://www.baidu.com",$.param(personObj), function(data){
			alert(data);
		})
	});
});
</script>
</head>
<body>
	<button id="serialize">序列化对象</button>
	<div></div>
	<button id="getButton">getButton</button>
	<button id="postButton">postButton</button>
	<button id="loadButton">loadButton</button>
</body>
</html>