<!DOCTYPE html>
<%@include file="/pages/inc/common.jsp"%>
<html lang="en">
<head>
<meta charset="utf-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="ThemeBucket">
<link rel="shortcut icon" href="images/favicon.png">

<title>Login</title>

<!--Core CSS -->
<link href="${staticPath}/bs3/css/bootstrap.min.css" rel="stylesheet">
<link href="${staticPath}/css/bootstrap-reset.css" rel="stylesheet">
<link href="${staticPath}/font-awesome/css/font-awesome.css"rel="stylesheet" />

<!-- Custom styles for this template -->
<link href="${staticPath}/css/style.css" rel="stylesheet">
<link href="${staticPath}/css/style-responsive.css" rel="stylesheet" />
<script type="text/javascript" src="${staticPath}/js/city_state.js"></script>

<style>
    select {
	width: 190px;
	font: normal 11px verdana;
	color: #fff;
	background: #000;
}
.error{
color:red;
}
    </style>
</head>
<body class="login-body">
	<div class="container">
		<form class="form-signin" id="signupFormUser" name="signupFormUser" method="post"
			action="${contextPath}/company/registration">
			<h2 class="form-signin-heading">registration now</h2>
			<div class="login-wrap">
				<p>Enter your account details below</p>
				<div class="row">
					<div class="col-md-6">
						<input type="text" name="firstName"  id="firstName" class="form-control" placeholder="First Name" autofocus>
					</div>
					<div class="col-md-6">
						<input type="text" name="lastName" id="lastName" class="form-control" placeholder="Last Name"  autofocus>
					</div>
				</div>
				<input type="email" class="form-control input-lg m-bot15" id="accountName" name="accountName"placeholder="Email" autofocus  > 
				<input type="password" class="form-control" name="password"  id="password" placeholder="Password">
				<input type="password" class="form-control " name="confirm_password"  id="confirm_password" placeholder="Re-type Password">
				<p>Enter Company details below</p>
				<input type="text" class="form-control"id="companyName" name="companyName" placeholder="Company Name"  autofocus> <input type="text"
					class="form-control" name="address1" id="address1" placeholder="Address1"
					autofocus> <input type="text" class="form-control"
					name="address2" placeholder="Address2" id="address2" autofocus>
				   <!--   <input type="text" name="country" id="country" class="form-control" placeholder="Country" autofocus> -->
				     <select class="form-control input-sm m-bot15"  onchange="set_country(this,state,city)" name="country" required>
                            <script type="text/javascript">setRegions(this);</script>
                      </select>
				     <div class="row">
					<div class="col-md-6">
					<!-- <input type="text" class="form-control" id="state" name="state" placeholder="State" autofocus> -->
					 <select name="state" class="form-control input-sm m-bot15" disabled="disabled" onchange="set_city_state(this,city)" required></select>
					</div>
					<div class="col-md-6">
					 <select name="city"  class="form-control input-sm m-bot15"  disabled="disabled" onchange="print_city_state(state,this)" required></select>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<input type="text" name="telNo" id="telNo" class="form-control"
							placeholder="Tel"  required>
					</div>
					<div class="col-md-6">
						<input type="text" name="postalCode" id="postalCode" class="form-control"
							placeholder="Postal"  required>
					</div>
				</div>

				<label class="checkbox"> <input type="checkbox" value="agree this condition" id="agree" name="agree" required >Agree to Our Policy
				</label>
				<button class="btn btn-lg btn-login btn-block" type="button" id="submintForm">Submit</button>

				<div class="registration">
					Already Registered. <a class=""
						href="${contextPath}/pages/login/login.jsp"> Login </a>
				</div>
			</div>
		</form>
		

	</div>
	<div id="txtregion"></div>
    <div id="txtplacename"></div>
	<!-- Placed js at the end of the document so the pages load faster -->
	<!--Core js-->
	<script src="${staticPath}/js/jquery.js"></script>
	<script src="${staticPath}/bs3/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${staticPath}/js/jquery.validate.min.js"></script>
    <script src="${staticPath}/js/regisvalidation.js"></script>

    
</body>
</html>