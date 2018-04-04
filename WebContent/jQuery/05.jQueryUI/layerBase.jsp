<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>全选与全不选</title>
<jsp:include page="/WEB-INF/views/include/taglib.jsp"></jsp:include>
</script>
<script type="text/javascript">
	function button01(id, simCode) {
		layer.alert('见到你真的很高兴', {
			icon : 6
		});
	}
	function button02(id, simCode) {
		layer.alert('墨绿风格，点击确认看深蓝', {
			skin : 'layui-layer-molv' //样式类名  自定义样式
			,
			closeBtn : 1 // 是否显示关闭按钮
			,
			anim : 1 //动画类型
			,
			btn : [ '重要', '奇葩' ] //按钮
			,
			icon : 6 // icon
			,
			yes : function() {
				layer.msg('按钮1')
			},
			btn2 : function() {
				layer.msg('按钮2')
			}
		});
	}
	function button03(id, simCode) {
		layer.msg('大部分参数都是可以公用的<br>合理搭配，展示不一样的风格', {
			time : 2000, //2s后自动关闭
			btn : [ '明白了', '知道了', '哦' ]
		});
	}
	function button04(id, simCode) {
		layer.msg('也可以这样', {
			btn : [ '明白了', '知道了' ],
			yes : function(index, layero) {
				layer.msg("按钮1回调,参数是:" + index)//index是从1开始的奇数, 每点一次加一
			},
			btn2 : function(index, layero) {
				//按钮【按钮二】的回调
				layer.msg("按钮2回调,参数是:" + index)
				return false //开启该代码可禁止点击该按钮关闭
			}
		});
	}
	function button05(id, simCode) {
		layer.open({
					type : 1,
					title : "open方式弹出层", //不显示标题栏   title : false/标题
					closeBtn : true,
					area : '300px;',
					shade : 0.8,
					id : 'LAY_layuipro', //设定一个id，防止重复弹出
					resize : false,
					btn : [ '火速围观', '残忍拒绝' ],
					btnAlign : 'c',
					moveType : 1, //拖拽模式，0或者1
					content : '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">内容<br>内容</div>',
					success : function(layero) {
						var btn = layero.find('.layui-layer-btn');
						btn.find('.layui-layer-btn0').attr({	//点击一按钮在新标签跳到layui官网
							href : 'http://www.layui.com/',	//layui-layer-btn0表示第一次按钮,layui-layer-btn1表示第二个按钮
							target : '_blank'
						});
					}
				});
	}
	function button06(id, simCode) {
		layer.alert('见到你真的很高兴', {
			icon : 6
		});
	}
	function button07(id, simCode) {
		layer.alert('见到你真的很高兴', {
			icon : 6
		});
	}
	function button08(id, simCode) {
		layer.alert('见到你真的很高兴', {
			icon : 6
		});
	}
	function button09(id, simCode) {
		layer.alert('见到你真的很高兴', {
			icon : 6
		});
	}
	function button10(id, simCode) {
		layer.alert('见到你真的很高兴', {
			icon : 6
		});
	}
	function button11(id, simCode) {
		layer.alert('见到你真的很高兴', {
			icon : 6
		});
	}
</script>
</head>
<body>
	<input type="button" onclick="button01()" value="alert" />
	<input type="button" onclick="button02()" value="alertCallback" />
	<input type="button" onclick="button03()" value="msg" />
	<input type="button" onclick="button04()" value="msgCallback" />
	<input type="button" onclick="button05()" value="open" />
	<input type="button" onclick="button06()" value="alert2" />
	<input type="button" onclick="button07()" value="alert2" />
	<input type="button" onclick="button08()" value="alert2" />
	<input type="button" onclick="button09()" value="alert2" />
	<input type="button" onclick="button010()" value="alert2" />
	<input type="button" onclick="button011()" value="alert2" />

</body>
</html>