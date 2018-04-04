<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>jQuery的取值方法</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		//1.val() 获取value属性值/赋值
		alert($("input[name=name]").val());

		//$("input[name=name]").val("小泽");

		//2.html(): 获取元素的html内容（包含标签子元素）
		//alert($("div").html());  //等价于innerHTML属性

		//3.text(): 获取元素的文本内容（不包含标签子元素）
		alert($("p").text());
	});
</script>

</head>

<body>

	<div id = "aa">
		<p><div>div的值</div></p>
	</div>

	<input type="text" name="name" value="小苍" />

</body>
</html>