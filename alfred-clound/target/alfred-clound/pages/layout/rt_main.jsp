<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@include file="/pages/inc/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="ThemeBucket">
    <link rel="shortcut icon" href="${staticPath}/images/favicon.png">
    <title>AlfredAdmin</title>
    
    
    <link href="${staticPath}/bs3/css/bootstrap.min.css" rel="stylesheet">
    <link href="${staticPath}/js/jquery-ui/jquery-ui-1.10.1.custom.min.css" rel="stylesheet">
    <link href="${staticPath}/css/bootstrap-reset.css" rel="stylesheet">
    <link href="${staticPath}/font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="${staticPath}/js/jvector-map/jquery-jvectormap-1.2.2.css" rel="stylesheet">
    <link href="${staticPath}/css/clndr.css" rel="stylesheet">
    
    <!-- Custom styles for this template -->
    <link href="${staticPath}/css/style.css" rel="stylesheet">
    <link href="${staticPath}/css/style-responsive.css" rel="stylesheet"/>

	<!-- Placed js at the end of the document so the pages load faster -->
<!--Core js-->
<script src="${staticPath}/js/jquery.js"></script>
<script src="${staticPath}/js/jquery-ui/jquery-ui-1.10.1.custom.min.js"></script>
<script src="${staticPath}/bs3/js/bootstrap.min.js"></script>
<script src="${staticPath}/js/jquery.dcjqaccordion.2.7.js"></script>
<script src="${staticPath}/js/jquery.scrollTo.min.js"></script>
<script src="${staticPath}/js/jQuery-slimScroll-1.3.0/jquery.slimscroll.js"></script>
<script src="${staticPath}/js/jquery.nicescroll.js"></script>
</head>
<body>
	<section id="container"> 
		<!--header start-->
		 <jsp:include page="/pages/layout/rt_top.jsp"></jsp:include>
		 <!--header end--> 
		 <!--sidebar start-->
		<jsp:include page="/pages/layout/rt_menu.jsp"></jsp:include>
		 <!--sidebar end-->
		<decorator:head /> <decorator:body /> 
	</section>
</body>
</html>