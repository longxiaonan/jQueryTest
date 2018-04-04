
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>基本选择器</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js">
</script>
<script type="text/javascript">
	$(document).ready(function() {
		//1.基本
		//1.1 #  根据id选择标签
		//var div = $("#div2"); // 返回的jQuery对象，里面有一个DOM对象
		//1.2 div:  根据标签名选择标签
		//var div = $("div");  // 返回的jQuery对象，里面多个DOM对象
		//1.3 .  根据class选择标签
		//var div = $(".myCls");
		//1.4 *  通配所有标签
		//var div = $("*");
		//1.5 selector1,selector2....  并集选择, 只要一个满足条件就可以选中
		//var div = $("#div2,.myCls");

		//2.层级
		//2.1 祖先 后代
		//var div = $("#div4 div"); //5个元素, 
		
		//2.2 父>子
		//var div = $("#div4>div"); //3个
		//2.3前+后面第一个（兄弟）(必须只有1个)
		//var div  = $("#div2+div");
		//2.4前~后面的兄弟
		//var div  = $("#div2~div");//3个

		
		
		alert(div.html());//1个

		//遍历方式一: 传统方式
		//size(): 返回jQuery里面的DOM对象个数
		//get(): 返回jQuery里面的某个小标的DOM对象
		//for(var i =0;i<div.size();i++){

		//获取每个DOM对象
		/* var dom = div.get(i);//dom,是一个DOM对象
		alert(dom.innerHTML); */

		//获取每个DOM对象的jQuery对象
		//var jq = $(div.get(i));//获取dom对象后转换成jquery对象
		//var jq = $(div[i]);//不通过get方法, 直接通过下标获取
		//alert(jq.html());
		//}
		//遍历方式二: jquery的each(): jQuery的遍历方法
		div.each(function(i) {// i:下标
			//this: 是div里面的每一个DOM子对象, 需要转换成jquery对象后才能使用
			//alert(this.innerHTML); 
			alert($(this).html());
		});

	});
</script>


</head>

<body>
	<div>div1</div>
	<span>span</span>
	<div id="div2">div2</div>
	<div class="myCls">div3</div>
	<div id="div4">
		<div>div31</div>
		<div>div32</div>
		<div>
			<div>div321</div>
			<div class="myCls">div322</div>
		</div>
	</div>
	<div>div5</div>
</body>
</html>