<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>全选与全不选</title>
<jsp:include page="/WEB-INF/views/include/taglib.jsp"></jsp:include>
</script>
<script type="text/javascript">
	
	//被调用者必须放在前面优先加载
	function openSIMRecharge(id,simCode) {
			var url;
			if (id!=null) {
			 	url="${ctx}/sim/SIMRecharge/addSIMRecharge?id"+id;
			}else {
				url="${ctx}/sim/SIMRecharge/addSIMRecharge?simCode"+simCode;	
			}
			//调用弹出层
			layer_show("充值记录",url,1000); 
		}
</script>
</head>
<body>
	<input type="button" onclick="openSIMRecharge(null, '1123')" 
					class="btn btn-success" value="添加充值记录" />
</body>
</html>