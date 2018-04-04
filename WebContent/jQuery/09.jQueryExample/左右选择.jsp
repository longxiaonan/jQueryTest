<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>左右select选择</title>
		<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
	<script type="text/javascript">
		
		$(function(){
			//全部过去
			$("#left3").click(function(){
				$("#leftSelectId option").appendTo($("#rightSelectId"));
			});
			
			//选中的过去
			$("#left2").click(function(){
				$("#leftSelectId option:selected").appendTo($("#rightSelectId")).removeProp("selected");
			});
			
			//选中的一个个过去
			$("#left1").click(function(){
				$("#leftSelectId option:selected:first").appendTo($("#rightSelectId")).removeProp("selected");
			});
			
		});
	</script>
	</head>
	<body>
		<table>
			<tr>
				<td>
					<select id="leftSelectId" style="width:100px" multiple="multiple" size="6">
						<option>孤独求败</option>
						<option>孤独求败</option>
					</select>
					
				</td>
				<td>
					<input id="left1" type="button" value=">" style="width:50px" /> <br/>
					<input id="left2" type="button" value=">>" style="width:50px" /> <br/>
					<input id="left3" type="button" value=">>>" style="width:50px" /> <br/>
					
					<input type="button" value="<" style="width:50px"/> <br/>
					<input type="button" value="<<" style="width:50px"/> <br/>
					<input type="button" value="<<<" style="width:50px"/> <br/>
					
				</td>
				<td>
					<select id="rightSelectId"  style="width:100px" multiple="multiple" size="6">
						<option>东方不败</option>
						<option>东方不败</option>
					</select>
				</td>
			</tr>
		</table>
			
	</body>
</html>