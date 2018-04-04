$(function(){
		var table = $("<table>",{
			'id': tableId,
			'width': '100%',
			'class': 'stripe row-border order-column'
		});
		var myheads = jqContainer.find("myheads");
		var jqThead = $("<thead/>").appendTo(jqTable);
		var jqContainer = $("#myDatatables");
		jqContainer.find("myfields");
		if(myheads.length > 0){
			var jqTr = $("<tr/>").appendTo(jqThead);
			if(settings['showCheckBox']){
				$("<th/>").attr("rowspan", 2)
					.html('<input type="checkbox" class="checkAll">').appendTo(jqTr);
				columns.push({className: 'check-box'});
			}else if(settings['rowIndex']){//如果要显示列表索引就自动增加一个空的列头
				$("<th/>").attr("rowspan", 2).appendTo(jqTr);
				columns.push({className: 'table-index'});
			}
			var myheadArr = myheads.find("myhead");
		}
	})