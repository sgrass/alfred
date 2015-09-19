<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/pages/inc/common.jsp" %>
<html>
<head>
 <meta charset="utf-8">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 <meta name="description" content="">
 <meta name="author" content="ThemeBucket">
 <title>EMP</title>
<link rel="shortcut icon" href="images/favicon.png">
<title>Password</title>
<!--Core CSS -->
<link href="${staticPath}/bs3/css/bootstrap.min.css" rel="stylesheet">
<link href="${staticPath}/css/bootstrap-reset.css" rel="stylesheet">
<link href="${staticPath}/font-awesome/css/font-awesome.css"rel="stylesheet" />
<!-- Custom styles for this template -->
<link href="${staticPath}/css/style.css" rel="stylesheet">
<link href="${staticPath}/css/style-responsive.css" rel="stylesheet" />
</head>

<body class="login-body">
<section id="main-content">
    <section class="wrapper">
		<form id="updateForm" class="form-signin">
			<h2 class="form-signin-heading">Update User Password</h2>
			<div class="login-wrap">
				<div class="user-login-info">
					<input type="password" id="oldPassword" name="oldPassword"class="form-control" placeholder="Old Password" autofocus> 
					<input type="password" id="newPassword" name="newPassword"class="form-control" placeholder="New Password" autofocus> 
					<input type="password" id="confirmPassword" name="confirmPassword" class="form-control"placeholder="Confirm Password">
				</div>
				<div id="errMsg" style="color: red; text-align: center; display: none"></div>
				<button class="btn btn-lg btn-login btn-block" id="updatePasswordBut" type="button">Update</button>
			</div>
		</form>

    </section>
</section>
	<script type="text/javascript" src="${staticPath}/js/jquery.validate.min.js"></script>
	<script type="text/javascript">
		$().ready(function() {

			
		var	validator=$("#updateForm").validate({
				rules : {
					    oldPassword : {
						required: true,
						rangelength:[5,30]
					    },
					    newPassword : {
					    	required: true,
							rangelength:[5,30]
					    },
					    confirmPassword : {
					    	required: true,
							rangelength:[5,30],
							equalTo: "#newPassword"
					    } 
				},
				messages : {
					oldPassword : {
					    required: "Please provide a oldPassword",
	                    minlength: "Your oldPassword must be at least 5 characters long",
				      },
					newPassword : {
						    required: "Please provide a newPassword",
		                    minlength: "Your newPassword must be at least 5 characters long",
						},
					confirmPassword:{
						    required: "Please provide a confirmPassword",
		                    minlength: "Your confirmPassword must be at least 5 characters long",
		                    equalTo: "Please enter the same password as above"
					}
				}
				
				
			});
		
		
	    $("#updatePasswordBut").click(function(){
	    	$("#errMsg").hide();
	    	var check = validator.form();
			if(check){
				$.ajax({
	    			type : "post",
	    			url : "/userInfo/updatePassword",
	    			data : {
	    				oldPassword:$("#oldPassword").val(),firstPassword:$("#newPassword").val(),comfirmPassword:$("#confirmPassword").val()
	    			},
	    			dataType : "json",
	    			success : function(data, index) {
	    				
	    				$("#errMsg").html(data.status);
	    				$("#errMsg").show();
	    				//$('#updatePasswordBut').reset() 
	    				

	    			},
	    			error : function() {
	    				alert('更新出错！');

	    			}
	    		});	
				
			}
	    	
	    })
		
			
		});
	</script>
</body>
</html>
