<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/pages/inc/common.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="ThemeBucket">
    <title>EMP</title>
    <!--dynamic table-->
    <link href="${staticPath}/js/advanced-datatable/css/demo_page.css" rel="stylesheet"/>
    <link href="${staticPath}/js/advanced-datatable/css/demo_table.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${staticPath}/js/data-tables/DT_bootstrap.css"/>
	<style>
	.classdisplay {
		display: none;
	}
	</style>
</head>
<body>
    <input type="hidden" id="defaultUserType" value="${type }" >

	<div id="selectRes" style="display: none">
	
	</div>
	<!--main content start-->
<section id="main-content">
    <section class="wrapper">
        <!-- page start-->
        <div class="row">
            <div class="col-sm-12">
                <section class="panel">
                    <header class="panel-heading" style="height:67px"> 
                       <div style="text-transform: capitalize;"> Employee 
                       <div class="btn-group pull-right">
                           <button id="editable-sample_new" class="btn btn-primary">
                               <i class="fa fa-plus"></i>  Account
                            </button>
                        </div>
                        </div>
                    </header>
                    <div class="panel-body">
                    <div class="clearfix">
							<div class="row" style="margin-bottom: 40px;">
								<form class="form-inline" method="post" role="form" action="${contextPath}/empuser/empUser">
									<div class="form-group" >
										<label class="col-lg-3 control-label" style="margin-top: 8px;">UserType:</label>
										<div class="col-lg-9">
											<select class="form-control" id="userType" name="type" style="width: 203px">
											  <option value="20">All</option>
											  <option value="10">waiter</option>
											  <option value="11">kitchen</option>
											  <option value="12">cashier</option>
											  <option value="13">lobby manager</option>
											</select>
										</div>
										</div>
								  </form>
							</div>
							</div>
              
                        <div class="adv-table" id="userListTable">
                          
                            <table class="table table-striped table-hover table-bordered" id="editable-sample">
                                <thead>
                                <tr>
                                    <th class="classdisplay">Id</th>
                                    <th>EmpID</th>
                                    <th class="classdisplay">Password</th>
                                    <th class="classdisplay">type</th>
                                    <th>Emp type</th>
                                    <th>First Name</th>
                                    <th>Last Name</th>
                                    <th>Delete</th>
                                </tr>
                                </thead>
                                <tbody>          
                                <c:forEach items="${userList}" var="user"> 
								<tr class="gradeX"> 
								<td  class="center">${user.id}</td> 
								<td  class="center">${user.empId}</td> 
								
								<td  class="center">${user.password}</td>
								<td  class="center">${user.type}</td> 
								<td>
								<c:if test="${user.type==10}">
								waiter
								</c:if>
								<c:if test="${user.type==11}">
								kitchen
								</c:if>
								<c:if test="${user.type==12}">
								cashier
								</c:if>
								<c:if test="${user.type==13}">
								lobby manager
								</c:if>
								</td> 
								<td  class="center">${user.firstName}</td>
								<td  class="center">${user.lastName}</td> 
								<td class="center">
                                        <button type="button" data='${user.id}' class="btn btn-info"><i class="fa fa-trash-o"></i>
                                        </button>
                                  </td>
								</tr> 
                                </c:forEach> 
                              </tbody>
                            </table>
                        </div>
                    </div>
                </section>
            </div>
        </div>
        <!-- page end-->
    </section>
</section>
<!--dynamic table-->
<script type="text/javascript" src="${staticPath}/js/advanced-datatable/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="${staticPath}/js/data-tables/DT_update_bootstrap.js"></script>
<script type="text/javascript" src="${staticPath}/js/jquery.validate.min.js"></script>
<script src="${staticPath}/js/bootbox.js"></script>
<!--common script init for all pages-->
<script src="${staticPath}/js/bootstrap-switch.js"></script>
<!--toggle initialization-->
<script src="${staticPath}/js/toggle-init.js"></script>
<!--dynamic table initialization -->
<script src="${staticPath}/js/empuser.js?v=2"></script>
<!--common script init for all pages-->
<script src="${staticPath}/js/scripts.js"></script>
<!--script for this page-->
<script>
	jQuery(document).ready(function() {
         
	    $('#userType').val($("#defaultUserType").val());

		showTable();
		//初始化table
		EditableTable.init();
		
        $("#accountManagerMenuA").addClass("active");
        $("#accountmanager_sub").css('display','block');
        $("#accounti1").addClass("active");
        
	    $('#userType').change(function(){
	    	$(".form-inline").attr("action","/empuser/empUser");
	    	$(".form-inline").submit();
	    });
	    //上下翻页的隐藏要隐藏的字段
        $("#userListTable .next").click(function(){
        	showTableEach();
        });
        $("#userListTable .prev").click(function(){
        	showTableEach();
        });
	});
</script>
<!-- END JAVASCRIPTS -->
</body>
</html>
