<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="../dataTablesInclude/taglib.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<script src="../dataTablesInclude/defaultSet.js" type="text/javascript"></script>
<title>Insert title here</title>
<script type="text/javascript">
<!--第三步：初始化Datatables-->
	$(document).ready(function() {
		$('#example').DataTable({
			language : {//>>>国际化
				url : "../dataTablesInclude/localisationChinese.json"
			},
			iDisplayLength:100,
			
			//"scrollCollapse": true, //默认true
			//"scrollY" : 250, //默认无限高, 最好设置, 当一页显示不完的时候出现垂直滚动条, 显示不全的时候出现空白
			//"scrollX": true,        //默认true, 水平滚动条, 动态高度
			"jQueryUI" : true,
			//dom: '<"top"i>rt<"bottom"flp><"clear">',//通过dom控制表格信息, 搜索框, 每页显示数, 分页按钮的位置
			paging : true, //默认true, false为在一页中显示所有内容
			scroll : 400, //滚动条测试没生效
			searching : true, //默认true, 显示搜索框,此次在default文件中设置成了false, 在每个具体的表中再进行设置
			//设置默认排序的列, 跟数组下标一样，第一列从0开始，这里表格初始化时，第四列默认降序
			"order" : [ [ 3, "desc" ] ],
			//"lengthMenu": [[2, 4, 6, -1], [2, 4, 6, "All"]], //定义每列显示宽度, 感觉在td里面定义更加直观些
			"columnDefs" : [ //自定义方式来控制列
			{
				"targets" : [ 2 ],
				"visible" : true, //隐藏表格中的第三列, 使之不显示在列表中
				"searchable" : false//不对第三列进行搜索
			}, {
				"targets" : [ 3 ],
				"visible" : true,//隐藏表格中的第四列, 使之不显示在列表中
				"bSortable": false,//不对第四列进行列排序,列上排序图标消失
				"data": "aaa",
		        "render": function ( data, type, full ) { //根据年龄为条件, 判断后显示为老年, 青年等.
		             return ''+seAge(data)+'';
		           }
			}],
			// 列筛选功能, 每个列上出现下拉列表, 可以选择进行相应的筛选, 查数据库时没实现
			/* initComplete: function (data) {
                       var api = this.api();
                       api.columns().indexes().flatten().each(function (i) {
                           var column = api.column(i);
                           var $span = $('<span class="addselect" width=40%">▾</span>').appendTo($(column.header()));
                           var select = $('<select><option value="">All</option></select>')
                                   .appendTo($(column.header()))
                                   .on('click', function (evt) {
                                        evt.stopPropagation();
                                        var val = $.fn.dataTable.util.escapeRegex(
                                              $(this).val());
                                              column.search(val ? '^' + val + '$' : '', true, false)
                                                    .draw();
                                              });
                           column.data().unique().sort().each(function (d, j) {
                               function delHtmlTag(str) {
                                   return str.replace(/<[^>]+>/g, "");// 去掉html标签
                                   };
                                   d = delHtmlTag(d);
                                   select.append('<option value="' + d + '">' + d + '</option>');
                                   $span.append(select)
                                   });
                           });
                       } */
            });

		//换页的时候会重draw表格, 会触发该事件
		$('#example').on('draw.dt', function() {
			//alert( 'Table redraw' );
		});
		
		//实现列宽手动调整, 需要colResizable插件(在jsp中有引入)支持.
		//$("#example").colResizable();
	});
	
	//定义控件
	function seAge(a){
		if(a<20){return "少年";}
		if(a>=20 && a<45){return "<span class='text-navy'><i class='fa fa-level-up'></i> 青年</span>";}
		if(a>=45){return "<span class='text-warning'><i class='fa fa-level-down'></i> 老年</span>";}
	}
	
	//监听内置事件
	/* $(document).ready(function() {
	    var eventFired = function(type) {
	        var n = $('#demo_info')[0];
	        n.innerHTML += '<div>' + type + ' 事件- ' + new Date().getTime() + '</div>';
	        n.scrollTop = n.scrollHeight;
	    }
	    $('#example').on('order.dt',
	    function() {
	        eventFired('排序');
	    }).on('search.dt',
	    function() {
	        eventFired('搜索');
	    }).on('page.dt',
	    function() {
	        eventFired('翻页');
	    }).dataTable();
	}); */

</script>
</head>
<body>
	<!-- 第二部: 设置表格 -->
	<table id="example" class="" cellspacing="0" width="100%">
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
			<tr>
				<td></td>
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
				<td>Paul Byrd</td>
				<td>Chief Financial Officer (CFO)</td>
				<td>New York</td>
				<td>64</td>
				<td>2010/06/09</td>
				<td>$725,000</td>
			</tr>
			<tr>
				<td>Gloria Little</td>
				<td>Systems Administrator</td>
				<td>New York</td>
				<td>59</td>
				<td>2009/04/10</td>
				<td>$237,500</td>
			</tr>
			<tr>
				<td>Bradley Greer</td>
				<td>Software Engineer</td>
				<td>London</td>
				<td>41</td>
				<td>2012/10/13</td>
				<td>$132,000</td>
			</tr>
			<tr>
				<td>Dai Rios</td>
				<td>Personnel Lead</td>
				<td>Edinburgh</td>
				<td>35</td>
				<td>2012/09/26</td>
				<td>$217,500</td>
			</tr>
			<tr>
				<td>Jenette Caldwell</td>
				<td>Development Lead</td>
				<td>New York</td>
				<td>30</td>
				<td>2011/09/03</td>
				<td>$345,000</td>
			</tr>
			<tr>
				<td>Yuri Berry</td>
				<td>Chief Marketing Officer (CMO)</td>
				<td>New York</td>
				<td>40</td>
				<td>2009/06/25</td>
				<td>$675,000</td>
			</tr>
			<tr>
				<td>Caesar Vance</td>
				<td>Pre-Sales Support</td>
				<td>New York</td>
				<td>21</td>
				<td>2011/12/12</td>
				<td>$106,450</td>
			</tr>
			<tr>
				<td>Doris Wilder</td>
				<td>Sales Assistant</td>
				<td>Sidney</td>
				<td>23</td>
				<td>2010/09/20</td>
				<td>$85,600</td>
			</tr>
			<tr>
				<td>Angelica Ramos</td>
				<td>Chief Executive Officer (CEO)</td>
				<td>London</td>
				<td>47</td>
				<td>2009/10/09</td>
				<td>$1,200,000</td>
			</tr>
			<tr>
				<td>Gavin Joyce</td>
				<td>Developer</td>
				<td>Edinburgh</td>
				<td>42</td>
				<td>2010/12/22</td>
				<td>$92,575</td>
			</tr>
			<tr>
				<td>Jennifer Chang</td>
				<td>Regional Director</td>
				<td>Singapore</td>
				<td>28</td>
				<td>2010/11/14</td>
				<td>$357,650</td>
			</tr>
			<tr>
				<td>Brenden Wagner</td>
				<td>Software Engineer</td>
				<td>San Francisco</td>
				<td>28</td>
				<td>2011/06/07</td>
				<td>$206,850</td>
			</tr>
			<tr>
				<td>Fiona Green</td>
				<td>Chief Operating Officer (COO)</td>
				<td>San Francisco</td>
				<td>48</td>
				<td>2010/03/11</td>
				<td>$850,000</td>
			</tr>
			<tr>
				<td>Shou Itou</td>
				<td>Regional Marketing</td>
				<td>Tokyo</td>
				<td>20</td>
				<td>2011/08/14</td>
				<td>$163,000</td>
			</tr>
			<tr>
				<td>Michelle House</td>
				<td>Integration Specialist</td>
				<td>Sidney</td>
				<td>37</td>
				<td>2011/06/02</td>
				<td>$95,400</td>
			</tr>
			<tr>
				<td>Suki Burks</td>
				<td>Developer</td>
				<td>London</td>
				<td>53</td>
				<td>2009/10/22</td>
				<td>$114,500</td>
			</tr>
			<tr>
				<td>Prescott Bartlett</td>
				<td>Technical Author</td>
				<td>London</td>
				<td>27</td>
				<td>2011/05/07</td>
				<td>$145,000</td>
			</tr>
			<tr>
				<td>Gavin Cortez</td>
				<td>Team Leader</td>
				<td>San Francisco</td>
				<td>22</td>
				<td>2008/10/26</td>
				<td>$235,500</td>
			</tr>
			<tr>
				<td>Martena Mccray</td>
				<td>Post-Sales support</td>
				<td>Edinburgh</td>
				<td>46</td>
				<td>2011/03/09</td>
				<td>$324,050</td>
			</tr>
			<tr>
				<td>Unity Butler</td>
				<td>Marketing Designer</td>
				<td>San Francisco</td>
				<td>47</td>
				<td>2009/12/09</td>
				<td>$85,675</td>
			</tr>
			<tr>
				<td>Howard Hatfield</td>
				<td>Office Manager</td>
				<td>San Francisco</td>
				<td>51</td>
				<td>2008/12/16</td>
				<td>$164,500</td>
			</tr>
			<tr>
				<td>Hope Fuentes</td>
				<td>Secretary</td>
				<td>San Francisco</td>
				<td>41</td>
				<td>2010/02/12</td>
				<td>$109,850</td>
			</tr>
			<tr>
				<td>Vivian Harrell</td>
				<td>Financial Controller</td>
				<td>San Francisco</td>
				<td>62</td>
				<td>2009/02/14</td>
				<td>$452,500</td>
			</tr>
			<tr>
				<td>Timothy Mooney</td>
				<td>Office Manager</td>
				<td>London</td>
				<td>37</td>
				<td>2008/12/11</td>
				<td>$136,200</td>
			</tr>
			<tr>
				<td>Jackson Bradshaw</td>
				<td>Director</td>
				<td>New York</td>
				<td>65</td>
				<td>2008/09/26</td>
				<td>$645,750</td>
			</tr>
			<tr>
				<td>Olivia Liang</td>
				<td>Support Engineer</td>
				<td>Singapore</td>
				<td>64</td>
				<td>2011/02/03</td>
				<td>$234,500</td>
			</tr>
			<tr>
				<td>Bruno Nash</td>
				<td>Software Engineer</td>
				<td>London</td>
				<td>38</td>
				<td>2011/05/03</td>
				<td>$163,500</td>
			</tr>
			<tr>
				<td>Sakura Yamamoto</td>
				<td>Support Engineer</td>
				<td>Tokyo</td>
				<td>37</td>
				<td>2009/08/19</td>
				<td>$139,575</td>
			</tr>
			<tr>
				<td>Thor Walton</td>
				<td>Developer</td>
				<td>New York</td>
				<td>61</td>
				<td>2013/08/11</td>
				<td>$98,540</td>
			</tr>
			<tr>
				<td>Finn Camacho</td>
				<td>Support Engineer</td>
				<td>San Francisco</td>
				<td>47</td>
				<td>2009/07/07</td>
				<td>$87,500</td>
			</tr>
			<tr>
				<td>Serge Baldwin</td>
				<td>Data Coordinator</td>
				<td>Singapore</td>
				<td>64</td>
				<td>2012/04/09</td>
				<td>$138,575</td>
			</tr>
			<tr>
				<td>Zenaida Frank</td>
				<td>Software Engineer</td>
				<td>New York</td>
				<td>63</td>
				<td>2010/01/04</td>
				<td>$125,250</td>
			</tr>
			<tr>
				<td>Zorita Serrano</td>
				<td>Software Engineer</td>
				<td>San Francisco</td>
				<td>56</td>
				<td>2012/06/01</td>
				<td>$115,000</td>
			</tr>
			<tr>
				<td>Jennifer Acosta</td>
				<td>Junior Javascript Developer</td>
				<td>Edinburgh</td>
				<td>43</td>
				<td>2013/02/01</td>
				<td>$75,650</td>
			</tr>
			<tr>
				<td>Cara Stevens</td>
				<td>Sales Assistant</td>
				<td>New York</td>
				<td>46</td>
				<td>2011/12/06</td>
				<td>$145,600</td>
			</tr>
			<tr>
				<td>Hermione Butler</td>
				<td>Regional Director</td>
				<td>London</td>
				<td>47</td>
				<td>2011/03/21</td>
				<td>$356,250</td>
			</tr>
			<tr>
				<td>Lael Greer</td>
				<td>Systems Administrator</td>
				<td>London</td>
				<td>21</td>
				<td>2009/02/27</td>
				<td>$103,500</td>
			</tr>
			<tr>
				<td>Jonas Alexander</td>
				<td>Developer</td>
				<td>San Francisco</td>
				<td>30</td>
				<td>2010/07/14</td>
				<td>$86,500</td>
			</tr>
			<tr>
				<td>Shad Decker</td>
				<td>Regional Director</td>
				<td>Edinburgh</td>
				<td>51</td>
				<td>2008/11/13</td>
				<td>$183,000</td>
			</tr>
			<tr>
				<td>Michael Bruce</td>
				<td>Javascript Developer</td>
				<td>Singapore</td>
				<td>29</td>
				<td>2011/06/27</td>
				<td>$183,000</td>
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