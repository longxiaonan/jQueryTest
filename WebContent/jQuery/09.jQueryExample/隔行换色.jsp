<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>隔行换色</title>
<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<style type="text/css">
.myCls {
	background-color: orange;
}
</style>
<script type="text/javascript">
	$(function() {
		//普通换色
		//$("tr:gt(1):odd").addClass("myCls");

		//鼠标滑过每行换色
		$("tr:gt(1)").mouseover(function() {
			$(this).addClass("myCls");
		}).mouseout(function() {
			$(this).removeClass("myCls");
		});
	});
</script>
</head>
<body>
	<table id="tab1" border="1" width="800" align="center">
		<tr>
			<td colspan="5"><input type="button" value="添加" /> <input
				type="button" value="删除"></td>
		</tr>
		<tr style="background-color: #999;">
			<th><input type="checkbox"></th>
			<th>分类ID</th>
			<th>分类名称</th>
			<th>分类描述</th>
			<th>操作</th>
		</tr>
		<tr>
			<td><input type="checkbox"></td>
			<td>1</td>
			<td>手机数码</td>
			<td>手机数码类商品</td>
			<td><a href="">修改</a>|<a href="">删除</a></td>
		</tr>
		<tr>
			<td><input type="checkbox"></td>
			<td>2</td>
			<td>电脑办公</td>
			<td>电脑办公类商品</td>
			<td><a href="">修改</a>|<a href="">删除</a></td>
		</tr>
		<tr>
			<td><input type="checkbox"></td>
			<td>3</td>
			<td>鞋靴箱包</td>
			<td>鞋靴箱包类商品</td>
			<td><a href="">修改</a>|<a href="">删除</a></td>
		</tr>
		<tr>
			<td><input type="checkbox"></td>
			<td>4</td>
			<td>家居饰品</td>
			<td>家居饰品类商品</td>
			<td><a href="">修改</a>|<a href="">删除</a></td>
		</tr>
	</table>
</body>
</html>