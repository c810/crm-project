<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<script type="text/javascript">
		// TODO: 003 首页显示完毕,紧接着要跳转到登录页面 	[sd首页6/7/8]
		// window代表整个页面,document代表除去最顶行标签页栏剩下的区域,location代表地址栏,href代表输入的地址
		// 给href输入地址,等同于在地址栏直接输入地址
		// 由于当前页面在应用的根目录下,所以不用加base标签
		// 这里的toLogin就是要跳转到的UserController下的toLogin()方法 [sd首页8]
		window.location.href = "settings/qx/user/toLogin.do";
	</script>
</body>
</html>