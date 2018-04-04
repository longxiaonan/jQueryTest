<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>表单和表单属性选择器</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-1.11.1.js"></script>
	<script type="text/javascript">
		
		$(function(){
			//1.表单选择器
			//var elem = $(":text");
			//var elem = $(":password");
			
			//alert(elem.size());
			
			//2.表单属性选择器(按照默认值)
			//var elem = $(":radio:checked");
			
			var elem = $("select option:selected");
			
			//val(): 获取标签的值 
			//alert(elem.val());
			alert(elem.html());
		});			
		
	</script>
  </head>
  
  <body>
    
     <input type="text" name="name"/>
     <input type="text" name="nickname"/>
     <input type="text" name="email"/>
     <input type="password" name="password"/>
     <input type="radio" name="gender"  value="男"/>男
     <input type="radio" name="gender" checked="checked" value="女"/>女
     <input type="checkbox" checked="checked"/>
     <select>
     	<option selected="selected">广东</option>
     	<option>广西</option>
     </select>
     <input type="button" disabled="disabled">
  </body>
</html>