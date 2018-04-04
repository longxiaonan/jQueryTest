<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>过滤选择器</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js">
	
</script>
<script type="text/javascript">
	//$(document).ready(function(){});

	$(function() {
		//1.基本（按照数量过滤）
		//1.1 :first  选择第一个元素 , 转换成dom元素后, 显示的是里面的dom内容
		//var elem = $("div:first");//选中div1, 转换成dom后, 里面的dom是div里面的dom树
		//1.2 :last  选择最后一个元素
		//var elem = $("div:last");//选中最后一个div
		//1.3 :eq(index):  选择第几个元素  (index从0开始)
		//var elem = $("div:eq(2)");//显示的div3
		//1.4 :gt(index): 选择索引大于xx元素
		//var elem = $("div:gt(0)");//选中的是div1后面的所有div
		//1.5 :lt(index): 选择索引小于xx元素
		//var elem = $("div:lt(4)");
		//1.6 :even  索引为偶数的元素
		//var elem = $("div:even");
		//1.7 :odd   索引为奇数的元素
		//var elem = $("div:odd");

		//2.内容（按照内容过滤）
		//2.1 :contains(text)  包含xx内容的元素（支持模糊搜索）
		//var elem = $("div:contains(divvvv2)");//2个, div1和divvvv2, divvvv2也是div1的内容
		//2.2 :empty  空标签
		//var elem = $("div:empty");//内容为空的标签
		//2.3 :has(selctor)    包含某个选择器的元素
		//var elem = $("div:has(span)");
		//2.4  :parent  选择包含子元素或文本的元素
		//var elem = $("div:parent");//跟"div:empty"相反

		//3. 可见性 （按照可见性查询）
		//3.1 :visible   看得见的元素
		//var elem = $("div:visible");//包括内容为空的元素

		//3.2 :hidden   看不见的元素
		//var elem = $("div:hidden");//选中有style="display: none"的元素

		//size(): 包含DOM对象的数量
		alert(elem.size());
		
		
		elem.each(function(i) {
			alert($(this).html());
		});
	});
</script>
</head>

<body>
	<div id="div1">
		<div>divvvv2</div>
		<div style="display: none">div3</div>
		<div>div4</div>
		<div id="div5">
			<div>div6</div>
			<div>div7</div>
		</div>
		<div></div>
		<span>span的内容</span>
	</div>
	<div style="display: none">div8</div>
	<div>div9</div>
</body>
</html>