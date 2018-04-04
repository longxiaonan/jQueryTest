<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../dataTablesInclude/taglib.jsp"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table id="example" class="display" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th>Name</th>
                <th>Position</th>
                <th>Office</th>
                <th>Age</th>
                <th>Start date</th>
                <th>Salary</th>
            </tr>
        </thead>
        <tbody>
        </tbody>
</table>
</body>
<script type="text/javascript">
var data = [
            {
                "name":       "Tiger Nixon",
                "position":   "System Architect",
                "salary":     "$3,120",
                "start_date": "2011/04/25",
                "office":     "Edinburgh",
                "extn":       "5421"	//table中有age没该列名
            },
            {
                "name":       "Garrett Winters",
                "position":   "Director",
                "salary":     "$5,300",
                "start_date": "2011/07/25",
                "office":     "Edinburgh",
                "extn":       "8422"
            }
        ];
            //object可以如下初始化表格
        $('#example').DataTable( {
            data: data,
            //使用对象数组，一定要配置columns，告诉 DataTables 每列对应的属性
            //data 这里是固定不变的，name，position，salary，office 为你数据里对应的属性
            columns: [
                { data: 'name' },
                { data: 'position' },
                { data: 'salary' },
                { data: 'office' }
            ]
        } );
</script>
</html>