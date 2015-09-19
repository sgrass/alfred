<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/pages/inc/common.jsp"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="ThemeBucket">
<title>RT Places Tables</title>
<link href="${staticPath}/js/advanced-datatable/css/demo_page.css" rel="stylesheet"/>
<link href="${staticPath}/js/advanced-datatable/css/demo_table.css" rel="stylesheet"/>
<link rel="stylesheet"href="${staticPath}/js/data-tables/DT_bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${staticPath}/js/jquery-multi-select/css/multi-select.css" />
    <link rel="stylesheet" type="text/css" href="${staticPath}/js/jquery-tags-input/jquery.tagsinput.css" />
<style>
.classdisplay {
	display: none;
}
</style>
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
						<header class="panel-heading tab-bg-dark-navy-blue">
							<ul class="nav nav-tabs nav-justified ">
							     <c:forEach items="${revenueCenterList}" var="rev">
								<li id="li_${rev.id}"><a data-toggle="tab" href="#" onclick="location.href='${contextPath}/reCrManagement/revenueTab?revenueId=${rev.id}'">${rev.revName}</a></li>
							     </c:forEach>
							</ul>
						</header>
	  <div class="panel-body">		 
        <section class="panel">
        <header class="panel-heading">
        <div style="text-transform: capitalize;">
				<div class="row">
					<div class="pull-left">ManPower</div>
					<div class=" pull-right">
						<button type="button" class="btn btn-success" id="savemainpowerBut"><i class="fa fa-save"></i> Save
						</button>
					</div>
				</div>
		</div>
		 </header>
        <div class="panel-body">
        <form action="${contextPath}/reCrManagement/addManPower?revenueId=${revenueId}" class="form-horizontal "  method="post" id="manPowerForm">
        <div class="row">
        <div class=col-md-6>
          <div class="form-group last">
        <label class="control-label col-md-3">Cashier</label>
        <div class="col-md-9">
        
        <select name="country" class="multi-select" multiple="" id="my_multi_cashier" >
        <c:forEach items="${userManagerList}" var="user" >
        <c:if test="${user.type==12}">
           <c:if test="${user.revenueId!=null}">
          <option selected value="${user.userId}">${user.empId}</option>
          </c:if>
           <c:if test="${user.revenueId==null}">
          <option  value="${user.userId}">${user.empId}</option>
          </c:if>
          
         </c:if>
        </c:forEach>
        </select>
        </div>
        </div>
        </div>
         <div class=col-md-6>
         
        <div class="form-group last">
        <label class="control-label col-md-3">Manager</label>
        <div class="col-md-9">
        <select name="country" class="multi-select" multiple="" id="my_multi_manager" >
         <c:forEach items="${userManagerList}" var="user" >
        <c:if test="${user.type==13}">
           <c:if test="${user.revenueId!=null}">
          <option selected value="${user.userId}">${user.empId}</option>
          </c:if>
           <c:if test="${user.revenueId==null}">
          <option  value="${user.userId}">${user.empId}</option>
          </c:if>
         </c:if>
        </c:forEach>
        </select>
        </div>
        </div>
        </div>
      </div>
       <div class="row">
       <div class="col-md-6">
        <div class="form-group last">
        <label class="control-label col-md-3">Waiter</label>
        <div class="col-md-9">
        <select name="country" class="multi-select" multiple="" id="my_multi_waiter" >
          <c:forEach items="${userManagerList}" var="user" >
        <c:if test="${user.type==10}">
          <c:if test="${user.revenueId!=null}">
          <option selected value="${user.userId}">${user.empId}</option>
          </c:if>
           <c:if test="${user.revenueId==null}">
          <option  value="${user.userId}">${user.empId}</option>
          </c:if>
         </c:if>
        </c:forEach>
        </select>
        </div>
        </div>
       </div>
       </div>
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
	<script type="text/javascript" src="${staticPath}/js/data-tables/jquery.dataTables.js"></script>
	<script type="text/javascript"src="${staticPath}/js/data-tables/DT_bootstrap.js"></script>
     <script src="${staticPath}/js/bootstrap-switch.js"></script>
     <script src="${staticPath}/js/toggle-init.js"></script>
	 <script src="${staticPath}/js/scripts.js"></script>
	 <script type="text/javascript" src="${staticPath}/js/jquery-multi-select/js/jquery.multi-select.js"></script>
	 <script type="text/javascript" src="${staticPath}/js/jquery-multi-select/js/jquery.quicksearch.js"></script>
     <script src="${staticPath}/js/bootbox.js"></script>
	 <script src="${staticPath}/js/rtrevenuecentermanagement.js"></script>
	 <script>
	 //form  表单提交
	 $("#savemainpowerBut").click(function(){
		 
		 $("#manPowerForm").submit();
		
	 })

	 //点击Tab 选中Tab
	var revenueId=$("#revenueIdDiv input").val();
	$('#li_'+revenueId).addClass("active");
	
	 $("#rvCenterManage").addClass("active");
     $("#rvCenters").css('display','block');
     $("#revenueCenterManageMentLi").addClass("active");
	
	
	 </script>
</body>
</html>
