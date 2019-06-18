<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>云后台管理系统</title>
<script src="js/jquery.min.js" type="text/javascript"></script>
<script src="js/jquery-ui.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<link rel="shortcut icon" href="img/website_logo.ico"
	type="image/x-icon" />
<link rel="stylesheet" type="text/css" href="css/jquery-ui.min.css" />
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<style type="text/css">
body {
	font: 12px/180% Arial, Helvetica, Verdana;
	color: #5a5a5a;
	margin: 0;
	background: #FFF;
}

body.login {
	background: url(img/login_bgx.gif);
}

.login_m {
	width: 403px;
	margin: 0 auto;
	margin-top: 98px;
	margin-bottom: 98px;
	/*position: absolute;left:50%;top:50%;margin-left:-202px;margin-top:-188px;*/
}

.login_logo {
	text-align: center;
	margin-bottom: 5px;
}

.login_boder {
	background: url(img/login_m_bg.png) no-repeat;
	height: 300px;
	overflow: hidden;
	background-size: 100% 100%;
}

.login_padding {
	padding: 30px 50px 30px 50px;
}

.login_boder h2 {
	color: #4f5d80;
	text-transform: uppercase;
	font-size: 12px;
	font-weight: normal;
	margin-bottom: 11px;
}

.forget_model_h2 {
	color: #4f5d80;
	font-size: 12px;
	font-weight: normal;
	margin-bottom: 11px;
}

.login_boder input.txt_input {
	width: 295px;
	height: 36px;
	border: 1px solid #cad2db;
	background: url(../images/txt_input_bg.gif) no-repeat;
	padding: 0 5px;
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	border-radius: 5px;
	line-height: 36px;
	margin-bottom: 10px;
	font-size: 14px;
	color: #717171;
	font-family: Arial;
}

.login_boder input.txt_input2 {
	margin-bottom: 10px;
}

.login_boder input.txt_input:focus {
	transition: border linear .2s, box-shadow linear .2s;
	-moz-transition: border linear .2s, -moz-box-shadow linear .2s;
	-webkit-transition: border linear .2s, -webkit-box-shadow linear .2s;
	outline: none;
	border-color: rgba(173, 173, 173.75);
	box-shadow: 0 0 8px rgba(173, 173, 173, .5);
	-moz-box-shadow: 0 0 8px rgba(173, 173, 173, .5);
	-webkit-box-shadow: 0 0 8px rgba(173, 173, 173, 3);
	border: 1px solid #6192c8;
}

.login_boder p.forgot {
	font-size: 11px;
	text-align: right;
	margin-bottom: 15px;
}

.login_boder p.forgot a, .login_boder p.forgot a:visited {
	color: #8c8e91;
}

.login_boder p.forgot a:hover {
	color: #206fd5;
}

#form {
	margin-top: 5px;
	margin-bottom: 15px;
	margin-left: 20px;
	margin-right: 20px;
}

label {
	font-size: 10px;
	font-family: 微软雅黑;
}
</style>

</head>
<body class="login" mycollectionplug="bind">
	<div class="login_m">
		<div class="login_logo">
			<img src="img/logo.png" width="100" height="100">
		</div>
		<div class="login_boder">

			<div class="login_padding" id="login_model">

				<form id="form" method="post">
					<div class="form-group">
						<label for="username" class="control-label">用户名</label>
						<div class="input-group">
							<span class="input-group-addon"><i
								class="	glyphicon glyphicon-user"></i></span> <input type="text"
								class="form-control" name="username" id="username"
								onchange="accountChange()" /> <a id="account_del"
								class="glyphicon glyphicon-remove btn form-control-feedback"
								style="pointer-events: auto; display: none"
								onclick="accountDel()"></a>
						</div>
					</div>
					<div class="form-group">
						<label for="password" class="control-label">密码</label>
						<div class="input-group">
							<span class="input-group-addon"><i
								class="	glyphicon glyphicon-lock"></i></span> <input type="password"
								class="form-control" name="password" id="password" />
						</div>

					</div>
					<div class="form-group text-right">
						<a id="iforget" href="javascript:void(0);">忘记密码?</a>
					</div>
					<div>
						<button class="btn btn-info  btn-block"
							data-loading-text="Loading..." type="button" onclick="sub()"
							id="login">登录</button>
					</div>
				</form>
			</div>

			<!-- <div id="forget_model" class="login_padding" style="display:none">
<br>

   <h1>Forgot password</h1>
   <br>
   <div class="forget_model_h2">(Please enter your registered email below and the system will automatically reset users’ password and send it to user’s registered email address.)</div>
    <label>
    <input type="text" id="usrmail" class="txt_input txt_input2">
   </label>
</div> -->

		</div>
	</div>
	<script type="text/javascript">
		function sub() {
			var username = $("#username").val();
			var password = $("#password").val();
			if (username != "" && password != "") {
				$("#login").button('loading').delay(1000).queue(function() {
					// $(this).button('reset');
					// $(this).dequeue(); 
					$.post('loginCheck', {
						username : username,
						password : password
					}, function(data) {
						if (data == "0") {
							$("#login").button('reset');
							$("#login").dequeue();
							alert("用户名或密码错误");
						} else {
							$("#login").button('reset');
							$("#login").dequeue();
							window.location.href = "manager";
						}
					});
				});
			} else {
				alert("用户名或密码不能为空！");
			}
		}
		$("body").keydown(function(e) {
			var keynum;
			var keychar;
			keynum = window.event ? e.keyCode : e.which;
			//keychar = String.fromCharCode(keynum);
			if (keynum == 13) {
				sub();
			}
		});

		function accountDel() {
			$("#username").val("");
			$("#account_del").hide();
		}
		function accountChange() {
			var username = $("#username").val();
			if (username != "") {
				$("#account_del").show();
			}
		}
	</script>

</body>

</html>