<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/pages/inc/common.jsp"%>
<html lang="en">
<head>
<meta charset="utf-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="ThemeBucket">
<meta name="decorator" content="none" />
<link rel="shortcut icon" href="images/favicon.png">

<title>Login</title>

<!--Core CSS -->
<link href="${staticPath}/bs3/css/bootstrap.min.css" rel="stylesheet">
<link href="${staticPath}/css/bootstrap-reset.css" rel="stylesheet">
<link href="${staticPath}/font-awesome/css/font-awesome.css"
	rel="stylesheet" />

<!-- Custom styles for this template -->
<link href="${staticPath}/css/style.css" rel="stylesheet">
<link href="${staticPath}/css/style-responsive.css" rel="stylesheet" />

<!-- Just for debugging purposes. Don't actually copy this line! -->
<!--[if lt IE 9]>
    <script src="js/ie8-responsive-file-warning.js"></script><![endif]-->

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>

<body class="login-body">

	<div class="container">
        <div class="text-center title">
           Alfred BackOffice
        </div>
		<form id="loginForm" class="form-signin" method="post"
			action="${contextPath}/login/loginVerify">
			<h2 class="form-signin-heading">sign in now</h2>
			<div class="login-wrap">
				<div class="user-login-info">
					<input type="text" id="accountName" name="accountName"
						class="form-control" placeholder="User ID" autofocus> <input
						type="password" id="password" name="password" class="form-control"
						placeholder="Password">
				</div>
				<div id="errMsg"
					style="color: red; text-align: center; display: none">${errMsg}！</div>
				<label class="checkbox"> <input type="checkbox" name="remember" value="remember-me"> Remember me <span class="pull-right">
						<a data-toggle="modal" href="#myModal"> Forgot Password?</a>
				</span>
				</label>
				<button class="btn btn-lg btn-login btn-block" type="submit">Signin</button>
				<div class="registration">
					Don't have an account yet? <a class="" href="${contextPath}/company/forwardRegistration">
						Create an account </a>
				</div>
			</div>
		</form>

		<!-- Modal -->
		<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog"
			tabindex="-1" id="myModal" class="modal fade">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title">Forgot Password ?</h4>
					</div>
					<div class="modal-body">
						<p>Enter your e-mail address below to reset your password.</p>
						<input type="text" name="email" placeholder="Email"
							autocomplete="off" class="form-control placeholder-no-fix">

					</div>
					<div class="modal-footer">
						<button data-dismiss="modal" class="btn btn-default" type="button">Cancel</button>
						<button class="btn btn-success" type="button">Submit</button>
					</div>
				</div>
			</div>
		</div>
		<!-- modal -->
	</div>

	<!--Core js-->
	<script src="${staticPath}/js/jquery.js"></script>
	<script type="text/javascript" src="${staticPath}/js/jquery.validate.min.js"></script>
	<script src="${staticPath}/bs3/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$().ready(function() {
			if ('${errMsg}' != '') {
				$("#errMsg").show();
			}

			$("#loginForm").validate({
				rules : {
					accountName : {
						required: true,
					  email: true
					},
					password : "required",
				},
				messages : {
					accountName : "Please enter a valid email account",
					password : "Please enter your password",
				}
			});
		});
	</script>
</body>
</html>
