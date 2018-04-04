<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../dataTablesInclude/taglib.jsp"%>
<!DOCTYPE html>
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
$(function(){
	var data = [
	            [
	                "Tiger Nixon",
	                "System Architect",
	                "Edinburgh",
	                "5421",
	                "2011/04/25",
	                "$3,120"
	            ],
	            [
	                "Garrett Winters",
	                "Director",
	                "Edinburgh",
	                "8422",
	                "2011/07/25",
	                "$5,300"
	            ]
	        ];
	     
	        //然后 DataTables 这样初始化：
	        $('#example').DataTable( {
	            data: data
	        } );
})
</script>
</html>