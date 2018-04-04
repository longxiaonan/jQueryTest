<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>子元素选择器</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
	<script type="text/javascript">
	$(function(){
		//子元素选择器
		var elem;
		//1.first-child
		//elem = $("#div1:first-child");			//本身的第一个儿子和最后一个儿子都是自己..
		//elem = $("#div1 div:first-child");	//第一个儿子和第一个孙子, 如果第一个儿子或第一个孙子不是
											    //div, 那么没选中
		//elem = $("#div1>div:first-child");		//第一个儿子, 如果第一个儿子不是div, 就没选择到
		//2.first-of-type
		//elem = $("#div1>div:first-of-type");	//不管第一个是不是div, 选择找到的第一个div
		//3.last-child
		//elem = $("#div1>div:last-child");
		//4.last-of-type
		//elem = $("#div1>div:last-of-type");
		//5.nth-child
		//elem=$("#div1>div:nth-child(3)");		//div3因为是从1开始计算, 第3个孩子
		elem.each(function(i){
			alert($(this).html());
		});
	});			
	</script>
  </head>
  
  <body>
    <div id="div1">
    	<span>span1</span>
    	<div>div2</div>
    	<div style="display: none">div3</div>
    	<div id="div4">div4</div>
    	<div>
    		<div id="div5">div5</div>
    		<div>div6</div>
    		<div>div7</div>
    	</div>
    	<div>div8</div>
    </div>
  </body>
</html>