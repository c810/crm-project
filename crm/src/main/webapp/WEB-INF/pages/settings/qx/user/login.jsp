<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--动态地址--%>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<html>
<head>
    <%--如果写127.0.0.1的话,就写死了,如果别人在他们的主机上访问,就找不到了,应该写tomcat运行时所在的机器的真实的ip--%>
    <%--同时,如果以后项目名变了不叫crm了,那也就找不到了,所以都应该动态获取--%>
    <%--<base href="http://127.0.0.1:8080/crm/">--%>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <%--给添加了base标签后,页面中所有的../../../都去掉,以后系统不管找什么url,都到base中所示的根下面去找--%>
    <%--<link href="../../../jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />--%>
    <link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
    <script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
	<script type="text/javascript">
        $(function () {
            // 给登录按钮添加单机事件
            $("#loginBtn").click(function () {
                // 收集参数
                // trim()去除前后空格
                // 使用jQuery获取指定元素的指定属性的值:
                // 选择器.attr("属性名"); // 用来获取那些值不是true/false的属性的值
                // 选择器.prop("属性名"); // 用来获取那些值是true/false的属性的值,例如:checked,selected,readonly,disabled等
                // 这些参数名要和后面UserController里
                // public @ResponseBody Object login(String loginAct, String loginPwd, String isRemPwd, HttpServletRequest request) {
                // 的形参名保持一致
                var loginAct = $.trim($("#loginAct").val());
                var loginPwd = $.trim($("#loginPwd").val());
                var isRemPwd = $("#isRemPwd").prop("checked");
                // 表单验证
                if(loginAct==""){
                    alert("用户名不能为空");
                    return;
                }
                if(loginPwd==""){
                    alert("密码不能为空");
                    return;
                }
                // 发送请求(异步请求)
                $.ajax({
                    // 表示要把请求发到哪里去,我们要发送到UserController的public Object login()方法里,
                    // 直接拷贝其上面的@RequestMapping("/settings/qx/user/login.do")里的地址,去掉第一个/,因为网页中所有的url都从base指定路径下开始找
                    url:'settings/qx/user/login.do',
                    data:{
                        loginAct:loginAct,
                        loginPwd:loginPwd,
                        isRemPwd:isRemPwd
                    },
                    // 这里get和post都行,但是能用post就用post
                    type:'post',
                    dataType:'json',
                    // 用回调函数处理响应回来的json字符串
                    success:function (data) {
                        if(data.code=="1"){
                            // 登录成功,跳转到业务主页面,
                            // 因为是整个页面刷新,所以发同步请求
                            // 发同步请求有三种方式:地址栏,超链接,form表单,这里只能用地址栏
                            // 直接跳转到WEB-INF下的index页面跳转不过去,要通过Controller跳转
                            // 地址写所要跳转到的Controller的@RequestMapping("/workbench/index.do")内的地址
                            window.location.href="workbench/index.do";
                        }else {
                            // 登录失败,局部刷新提示信息
                            // html既可以写文本信息,也可以写标签,而text只能写文本信息,这里没有标签所以两者都可以
                            //$('#msg').text(data.message);
                            $('#msg').html(data.message);
                        }
                    }
                });
            });
        });
    </script>
</head>
<body>
    <div style="position: absolute; top: 0px; left: 0px; width: 60%;">
        <img src="image/IMG_7114.JPG" style="width: 100%; height: 90%; position: relative; top: 50px;">
    </div>
    <div id="top" style="height: 50px; background-color: #3C3C3C; width: 100%;">
        <div style="position: absolute; top: 5px; left: 0px; font-size: 30px; font-weight: 400; color: white; font-family: 'times new roman'">
            CRM &nbsp;<span style="font-size: 12px;">&copy;2019&nbsp;动力节点</span></div>
    </div>

    <div style="position: absolute; top: 120px; right: 100px;width:450px;height:400px;border:1px solid #D5D5D5">
        <div style="position: absolute; top: 0px; right: 60px;">
            <div class="page-header">
                <h1>登录</h1>
            </div>
            <form action="workbench/index.html" class="form-horizontal" role="form">
                <div class="form-group form-group-lg">
                    <div style="width: 350px;">
                        <input class="form-control" id="loginAct" type="text" placeholder="用户名">
                    </div>
                    <div style="width: 350px; position: relative;top: 20px;">
                        <input class="form-control" id="loginPwd" type="password" placeholder="密码">
                    </div>
                    <div class="checkbox" style="position: relative;top: 30px; left: 10px;">
                        <label>
                            <input type="checkbox" id="isRemPwd"> 十天内免登录
                        </label>
                        &nbsp;&nbsp;
                        <span id="msg"></span>
                    </div>
                    <%-- 这里不能写type="submit",因为这样的话是提交同步请求,返回后会全局刷新 --%>
                    <button type="button" id="loginBtn" class="btn btn-primary btn-lg btn-block"
                            style="width: 350px; position: relative;top: 45px;">登录
                    </button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>