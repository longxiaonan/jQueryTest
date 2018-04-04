var selectAll = function() {
	$(".itemSelect").prop("checked", $("#selectAllId").prop("checked"))
};
// 被调用者必须放在前面优先加载
var selectAllOne = function() {
	$(".itemSelect").prop("checked", $("#selectAllId").prop("checked"))
};
var select = {
	/** chax */
	selectAll : selectAll,
	selectAllOne : selectAllOne
};