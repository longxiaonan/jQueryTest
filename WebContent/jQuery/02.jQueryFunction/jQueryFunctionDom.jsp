<html>
  <head>
    <title>jQuery的DOM方法</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta charset="UTF-8">
	<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
	<script type="text/javascript">
		
		$(function(){
			//1.内部插入方法
			//1.1 append()： 把所选的元素里面追加内容
			$("#btn1").click(function(){
				$("div").append($("#testBtn"));
			});
			
			//1.2 appendTo()： 把所选内容追到另一个元素里面里面
			$("#btn2").click(function(){
				$("#testBtn").appendTo($("div"));
			});
			
			//1.3 prepend(): 把所选的元素里面前置内容
			$("#btn3").click(function(){
				$("div").prepend($("#testBtn"));
			});
			
			//1.4 prependTo()： 把所选内容前置另一个元素里面里面
			$("#btn4").click(function(){
				$("#testBtn").prependTo($("div"));
			});
			
			//2.外部插入
			//2.1 after()： 把所选的元素外面追加内容
			$("#btn5").click(function(){
				$("div").after($("#testBtn"));
			});
			
			//2.2 insertAfter()： 把所选内容追到另一个元素外面
			$("#btn6").click(function(){
				$("#testBtn").insertAfter($("div"));
			});
			
			//2.3 before()： 把所选的元素外面前置内容
			$("#btn7").click(function(){
				$("div").before($("#testBtn"));
			});
			
			//2.4 insertBefore()： 把所选内容前置另一个元素外面
			$("#btn8").click(function(){
				$("#testBtn").insertBefore($("div"));
			});
			
			
			$("#btn9").click(function(){
				//empty() : 删除当前标签的子标签或文本
				//$("div").empty();
				//remove() : 删除当前标签
				$("div").remove();
			});
		});
	</script>

  </head>
  
  <body>
   <div>看不见远处的青山</div>
   <hr/>
   <input id="testBtn" type="button" value="测试按钮"/>
   
   <hr/>
   
   <input id="btn1" type="button" value="append"/>
   <input id="btn2" type="button" value="appendTo"/>
   <input id="btn3" type="button" value="prepend"/>
   <input id="btn4" type="button" value="prependTo"/>
   <input id="btn5" type="button" value="after"/>
   <input id="btn6" type="button" value="insertAfter"/>
   <input id="btn7" type="button" value="before"/>
   <input id="btn8" type="button" value="insertBefore"/>
   <input id="btn9" type="button" value="删除"/>
  
  </body>
</html>