<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>jquery中delegate()绑定事件方式</title>
    <style type="text/css">
        .container
        {
            width: 300px;
            height: 300px;
            border: 1px #ccc solid;
            background-color: Green;
        }
        .btn-test
        {
            border: 1px #ccc solid;
            padding: 5px 15px;
            cursor: pointer;
        }
    </style>
    <script src="js/jquery-1.8.0.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function () {

            /***********单元素添加单事件***********/

            //按钮绑定单击事件 实现div的显示隐藏
            $(".header").delegate("#btn-test1", "click", function () {
                $(".container").slideToggle();
            });


            /***********单元素添加多事件***********/

            //空格相隔方式
            $(".header").delegate("#btn-test1", "click mouseout", function () {
                $(".container").slideToggle();
            });

            //大括号替代方式
            $(".header").delegate("#btn-test1", {
                "mouseout": function () {
                    alert("这是mouseout事件!");
                },
                "click": function () {
                    $(".container").slideToggle();
                }
            });


        });
    </script>
</head>
<body>
    <div class="header">
        <input type="button" value="按钮1" class="btn-test" id="btn-test1" />
        <input type="button" value="按钮2" class="btn-test" id="btn-test2" />
    </div>
    <div class="container">
    </div>
</body>
</html>