<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>jQuery的动画方法</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>	
<script type="text/javascript">
	$(function() {
		$("#btn").click(function() {

			var val = $("#btn").val();
			if (val == "显示") {
				//显示
				//$("img").show(3000);
				//滑下
				//$("img").slideDown(3000);
				//淡入
				//$("img").fadeIn(3000);
				$("#btn").val("隐藏");
			} else {
				//隐藏
				//$("img").hide(3000);
				//滑上
				//$("img").slideUp(3000);
				//淡出
				//$("img").fadeOut(3000);
				$("#btn").val("显示");
			}

			//$("img").slideToggle(3000);

		});
	});
</script>

</head>

<body>
	<img src="1.jpg" style="display: none" width="200px" height="200px" />
	<hr />
	<input id="btn" type="button" value="显示" />

</body>
</html>