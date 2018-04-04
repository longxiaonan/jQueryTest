<html>
<head>
<title>jQuery的筛选方法</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js">
	
</script>
<script type="text/javascript">
	//6.使用clostest来完成事件委托
	$(document).bind("click", function(e) {
		$(e.target).closest("li").toggleClass("hilight");
	});
	$(function() {
		var elem;
		//1.chilren() 所有子后代元素
		//var elem = $("#div1").children();

		//2.parent()  父元素, 直接父元素
		//var elem = $("div:eq(2)").parent();
		//var elem = $("#div1>div:nth-child(2)");
		//var elem = $("#div1 div:contains(div3)");

		//3. next(): 下一个兄弟
		//var elem = $("div:eq(2)").next();

		//4.prev()  上一个兄弟
		//var elem = $("div:eq(2)").prev();

		//5.siblings(): 所有兄弟, 包括前面的和后面的兄弟
		//var elem = $("div:eq(2)").siblings();

		//6.eq()方法
		//var elem = $("div:eq(2)"); //div3, 编号成0开始
		/* elem.each(function() {
			alert($(this).html());
		}); */
	});
</script>

</head>

<body>
	<div id="div1">
		<div>div2</div>
		<div>div3</div>
		<div>div31</div>
		<div>div4</div>
		<div>div5</div>
	</div>
	<ul>
		<li><b>Click me!</b></li>
		<li>You can also <b>Click me!</b></li>
	</ul>
</body>
</html>