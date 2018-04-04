<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>全选与全不选</title>
<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js">
	
</script>
<script type="text/javascript">
	var select = {
		selectAll:function() {
			$(".itemSelect").prop("checked",
					$("#selectAllId").prop("checked"))},
		selectAllOne:function() {
			$(".itemSelect").prop("checked",
					$("#selectAllId").prop("checked"))}
	};
	
	$(function() {
		$("#selectAllId").click(select.selectAll);
				

		//获取item所有复选框,遍历复选框的状态，只要有一个为false，flag的值变成false
		// true & true & false & true = false
		// true & true & true & true = true
		$(".itemSelect").click(function() {
			var flag = true;//必须写在这里面, 如果写在外面那么flag被修改了
			$(".itemSelect").each(function() {
				flag &= $(this).prop("checked");
			});
			$("#selectAllId").prop("checked", flag);
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
		<tr>
			<th><input type="checkbox" id="selectAllId">全选</th>
			<th>分类ID</th>
			<th>分类名称</th>
			<th>分类描述</th>
			<th>操作</th>
		</tr>
		<tr>
			<td><input type="checkbox" class="itemSelect"></td>
			<td>1</td>
			<td>手机数码</td>
			<td>手机数码类商品</td>
			<td><a href="">修改</a>|<a href="">删除</a></td>
		</tr>
		<tr>
			<td><input type="checkbox" class="itemSelect"></td>
			<td>2</td>
			<td>电脑办公</td>
			<td>电脑办公类商品</td>
			<td><a href="">修改</a>|<a href="">删除</a></td>
		</tr>
		<tr>
			<td><input type="checkbox" class="itemSelect"></td>
			<td>3</td>
			<td>鞋靴箱包</td>
			<td>鞋靴箱包类商品</td>
			<td><a href="">修改</a>|<a href="">删除</a></td>
		</tr>
		<tr>
			<td><input type="checkbox" class="itemSelect"></td>
			<td>4</td>
			<td>家居饰品</td>
			<td>家居饰品类商品</td>
			<td><a href="">修改</a>|<a href="">删除</a></td>
		</tr>
	</table>
</body>
</html>