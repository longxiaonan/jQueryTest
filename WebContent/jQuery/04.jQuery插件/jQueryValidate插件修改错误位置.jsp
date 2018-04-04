<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>菜鸟教程(runoob.com)</title>
<script
	src="http://static.runoob.com/assets/jquery-validation-1.14.0/lib/jquery.js"></script>
<script
	src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
<script
	src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
<script>
	//提交前处理
	$.validator.setDefaults({
		/* submitHandler : function(form) {//条件:1, 提交按钮必须type是submit, 否则都不进行校验和提交; 2, 如果不写form.submit, 那么form将不被提交
			alert("提交事件!");//如果提交按钮存在
			//form.submit();
			//loading('正在提交，请稍等...');
			///可以通过下面的方法进行异步校验
			//saveHandler("${ctx}/cartype/tIovCarType/saveTIovCarType",form);
			//updateHandler("${ctx}/cartype/tIovCarType/saveTIovCarType",form);
			alert("1111");
		} */
	});

	$().ready(
			function() {
				// 提交时验证表单
				var validator = $("#form1").validate(
						{//提示错误的位置
							errorPlacement : function(error, element) {
								// Append error within linked label
								$(element).closest("form").find(
										"label[for='" + element.attr("id")
												+ "']").append(error);
							},
							errorElement : "span",//提示错误的元素

							//?????
							success : function(label) {
								// set &nbsp; as text for IE
								label.html("&nbsp;").addClass("checked");
								//label.addClass("valid").text("Ok!")
							},
							rules : {
								user : {//跟form中的name属性名要一致
									required : true,
									minlength : 2
								},
								password : {//跟form中的name属性名要一致
									required : true,
									minlength : 5,
									maxlength : 12
								}
							},
							messages : {
								user : {
									required : " (必需字段)",
									minlength : " (不能少于 3 个字母)"
								},
								password : {
									required : " (必需字段)",
									minlength : " (字母不能少于 5 个且不能大于 12 个)",
									maxlength : " (字母不能少于 5 个且不能大于 12 个)"
								}
							}
						});

				$(".cancel").click(function() {
					validator.resetForm();
				});
			});
function submit(){
	alert("333333");
}
</script>
<style>
.error {
	color: red;
}
</style>
</head>
<body>
	<form method="get" class="cmxform" id="form1" action="">
		<fieldset>
			<legend>登录框</legend>
			<p>
				<label for="user">用户名</label> <input id="user" name="user" required
					minlength="3">
			</p>
			<p>
				<label for="password">密码</label> <input id="password"
					type="password" maxlength="12" name="password" required
					minlength="5">
			</p>
			<p>
				<input class="submit" type="button" value="Login" onclick="submit()">
			</p>
		</fieldset>
	</form>
</body>
</html>
