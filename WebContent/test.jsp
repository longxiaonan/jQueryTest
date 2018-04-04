<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- jQuery -->
<script type="text/javascript" charset="utf8" src="http://code.jquery.com/jquery-1.10.2.min.js"></script>

<!-- 1.######## DataTables js -->
<title>Insert title here</title>
<script type="text/javascript">
isNotNull = function(_val){
	if (typeof(_val)!="undefined" && null != _val && _val !=""){
		return true;
	}
    return false;
}
$(function(){
	var jqTable = $("<table>",{
		'id': "tableId",
		'width': '100%',
		'class': 'stripe row-border order-column'
	});
	var myheads = $("#myDatatables").find("myheads");
	var jqThead = $("<thead/>").appendTo(jqTable);
	var jqContainer = $("#myDatatables");
	if(myheads.length > 0){
		var jqTr = $("<tr/>").appendTo(jqThead);
		$("<th/>").attr("rowspan", 2).html('<input type="checkbox" class="checkAll">').appendTo(jqTr);
		$("<th/>").attr("rowspan", 2).appendTo(jqTr);
		var myheadArr = myheads.find("myhead");
		for(var idx = 0; idx < myheadArr.length; idx++){
			var jqTh = $("<th/>");
			var myhead = $(myheadArr[idx]);
			var width = myhead.attr('width');
			if(isNotNull(width)){
				jqTh.attr("width", width);
			}
		console.log(jqTh.get(0));
		}
		console.log(jqTr);
	}
	console.log(jqTable);
})
</script>
</head>
<body>
	<div id="myDatatables" style="width: 100%;">
		<!-- myfields是自定义的标签, 用于生成表格(不用去深究), 可以通过orderBy设置加载时数据排序 -->
		<myheads>
		   <myhead text="字典名" datafield="label" width="30%" orderBy="ASC"/>
		   <myhead text="字典类型" datafield="type" />
		   <myhead text="描述" datafield="description" />
		   <myhead text="操作" datafield="TASK_ID" renderFun="editForm" />
		</myheads>
	</div>
</body>
</html>