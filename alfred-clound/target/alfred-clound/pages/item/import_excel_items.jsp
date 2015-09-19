<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/pages/inc/common.jsp"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="ThemeBucket">
<title>RT Revenue Center Menu</title>
 <link href="${staticPath}/css/jqtree.css" rel="stylesheet" />
</head>
<body>
<div id="revenueIdDiv">
<input type="hidden" value="${revenueId}"/>
</div>
	<!--main content start-->
	<section id="main-content">
		<section class="wrapper">
			<!-- page start-->
			<div class="row">
				<div class="col-md-12">
			     <section class="panel">
			
	  <div class="panel-body">		 
        <section class="panel">
    <!--     <header class="panel-heading">
		 </header> -->
        <div class="panel-body">
             <div class="row">
                      <div class="col-md-4 col-md-offset-2">
                      <h1>Excel Menu</h1>
                        <div id="tree1"></div>
                        <button id="btnRvcMenuAddAll" class="btn btn-default btn-md">OK</button>
                        <button id="btnBack" class="btn btn-default btn-md"><a href="${contextPath}/item/queryAll"><font style="color: #fff;">Back</font></a></button>
                      </div>
                  </div>
               <form id="addNewForm" action="${contextPath}/importExcel/insert" method="post">
                  
                  
               </form>
        </div>
        </section>
		</div>
		</section>
				</div>
			</div>
			<!-- page end-->
		</section>
	</section>
	<!--main content end-->
	 <script src="${staticPath}/js/scripts.js"></script>
     <script src="${staticPath}/js/tree.jquery.js"></script>
     <script src="${staticPath}/js/bootbox.js"></script>
     <script type="text/javascript" src="${staticPath}/js/excelitems.js"></script>
	 <script>	
	 //点击Tab 选中Tab
	var revenueId=$("#revenueIdDiv input").val();
	 $('#li_'+revenueId).addClass("active");
	 $("#rvCenterManage").addClass("active");
     $("#rvCenters").css('display','block');
     $("#revenueCenterManageMentLi").addClass("active");
	 </script>
</body>
</html>