<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>属性选择器</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js">
	
</script>
<script type="text/javascript">
	$(function() {
		//属性选择器
		var elem;
		//1.包含某个属性
		//elem = $("input[name]");
		//2.属性值等于
		//elem = $("input[name='name']");
		//3.属性值不等于
		//elem = $("input[name!='name']");
		//4.属性值以什么开头
		//elem = $("input[name^='n']");
		//5.属性值以什么结尾
		//elem = $("input[name$=e]");
		//6.属性值包含
		elem = $("input[type*=a]");//选择type属性中包含a的

		alert(elem.size());
		elem.each(function(i) {// i:下标
			//this: 是div里面的每一个DOM子对象, 需要转换成jquery对象后才能使用
			//alert(this.innerHTML); 
			alert($(this).val());
		});
	});
</script>
</head>

<body>
	<input type="text" name="name" value="input1" />
	<input type="text" name="nickname" value="input2" />
	<input type="text" name="email" value="input3" />
	<input type="password" name="password" value="input4" />
	<input type="radio" id="gender" value="input5" />
	<input type="text" name="name" value="input6" />
</body>
</html>