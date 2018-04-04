<!DOCTYPE html>
<%@ include file="../dataTablesInclude/taglib.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<script src="dataTablesInclude/defaultSet.js" type="text/javascript"></script>
<title>Insert title here</title>
<script type="text/javascript">
<!--第三步：初始化Datatables-->
$(document).ready( function () {
    $('#example').DataTable({
    	language:{//>>>国际化
    	   url: "dataTablesInclude/localisationChinese.json"
    	},
        scrollCollapse: true,	//是否显示滚动条
        scrollY: 200,			//垂直滚动条高度
        scrollY: 200,			//水平滚动条高度
        jQueryUI: true,	//jqueryUI显示风格, 暗黑酷炫, 需要配合对应的css来显示
    	//dom: '<"top"i>rt<"bottom"flp><"clear">',//通过dom控制表格信息, 搜索框, 每页显示数, 分页按钮的位置
    	paging: true,	//默认true, false为在一页中显示所有内容
    	scroll: 400,	//滚动条测试没生效
    	searching: true,	//默认true, 此次在default文件中设置成了false, 在每个具体的表中再进行设置
    	//numbers - 只有只有数字按钮
		//simple - 只有上一页、下一页两个按钮
		//simple_numbers - 除了上一页、下一页两个按钮还有页数按钮，Datatables默认是这个
		//full - 有四个按钮首页、上一页、下一页、末页
		//full_numbers - 除首页、上一页、下一页、末页四个按钮还有页数按钮
		//first_last_numbers - 除首页、末页两个按钮还有页数按钮
    	pagingType: "first_last_numbers", 	//分页类型
    });
    
    //换页的时候会重draw表格, 会触发该事件
    $('#example').on( 'draw.dt', function () {
        //alert( 'Table redraw' );
    } );
} );
</script>
</head>
<body>
<!-- 第二部: 设置表格 -->
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
        <tfoot>
            <tr>
                <th>Name</th>
                <th>Position</th>
                <th>Office</th>
                <th>Age</th>
                <th>Start date</th>
                <th>Salary</th>
            </tr>
        </tfoot>
        <tbody>
            <tr>
                <td>Tiger Nixon</td>
                <td>System Architect</td>
                <td>Edinburgh</td>
                <td>61</td>
                <td>2011/04/25</td>
                <td>$320,800</td>
            </tr>
            <tr>
                <td>Garrett Winters</td>
                <td>Accountant</td>
                <td>Tokyo</td>
                <td>63</td>
                <td>2011/07/25</td>
                <td>$170,750</td>
            </tr>
            <tr>
                <td>Ashton Cox</td>
                <td>Junior Technical Author</td>
                <td>San Francisco</td>
                <td>66</td>
                <td>2009/01/12</td>
                <td>$86,000</td>
            </tr>
            <tr>
                <td>Cedric Kelly</td>
                <td>Senior Javascript Developer</td>
                <td>Edinburgh</td>
                <td>22</td>
                <td>2012/03/29</td>
                <td>$433,060</td>
            </tr>
            <tr>
                <td>Airi Satou</td>
                <td>Accountant</td>
                <td>Tokyo</td>
                <td>33</td>
                <td>2008/11/28</td>
                <td>$162,700</td>
            </tr>
            <tr>
                <td>Brielle Williamson</td>
                <td>Integration Specialist</td>
                <td>New York</td>
                <td>61</td>
                <td>2012/12/02</td>
                <td>$372,000</td>
            </tr>
            <tr>
                <td>Herrod Chandler</td>
                <td>Sales Assistant</td>
                <td>San Francisco</td>
                <td>59</td>
                <td>2012/08/06</td>
                <td>$137,500</td>
            </tr>
            <tr>
                <td>Rhona Davidson</td>
                <td>Integration Specialist</td>
                <td>Tokyo</td>
                <td>55</td>
                <td>2010/10/14</td>
                <td>$327,900</td>
            </tr>
            <tr>
                <td>Colleen Hurst</td>
                <td>Javascript Developer</td>
                <td>San Francisco</td>
                <td>39</td>
                <td>2009/09/15</td>
                <td>$205,500</td>
            </tr>
            <tr>
                <td>Sonya Frost</td>
                <td>Software Engineer</td>
                <td>Edinburgh</td>
                <td>23</td>
                <td>2008/12/13</td>
                <td>$103,600</td>
            </tr>
            <tr>
                <td>Jena Gaines</td>
                <td>Office Manager</td>
                <td>London</td>
                <td>30</td>
                <td>2008/12/19</td>
                <td>$90,560</td>
            </tr>
            <tr>
                <td>Quinn Flynn</td>
                <td>Support Lead</td>
                <td>Edinburgh</td>
                <td>22</td>
                <td>2013/03/03</td>
                <td>$342,000</td>
            </tr>
            <tr>
                <td>Charde Marshall</td>
                <td>Regional Director</td>
                <td>San Francisco</td>
                <td>36</td>
                <td>2008/10/16</td>
                <td>$470,600</td>
            </tr>
            <tr>
                <td>Haley Kennedy</td>
                <td>Senior Marketing Designer</td>
                <td>London</td>
                <td>43</td>
                <td>2012/12/18</td>
                <td>$313,500</td>
            </tr>
            <tr>
                <td>Tatyana Fitzpatrick</td>
                <td>Regional Director</td>
                <td>London</td>
                <td>19</td>
                <td>2010/03/17</td>
                <td>$385,750</td>
            </tr>
            <tr>
                <td>Michael Silva</td>
                <td>Marketing Designer</td>
                <td>London</td>
                <td>66</td>
                <td>2012/11/27</td>
                <td>$198,500</td>
            </tr>
            <tr>
                <td>Donna Snider</td>
                <td>Customer Support</td>
                <td>New York</td>
                <td>27</td>
                <td>2011/01/25</td>
                <td>$112,000</td>
            </tr>
        </tbody>
    </table>
</body>
</html>