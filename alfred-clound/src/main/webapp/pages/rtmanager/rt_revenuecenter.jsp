<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/pages/inc/common.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Alfred">
    <title>Alfred Cloud POS - HQ Management</title>
    <!--dynamic table-->
    <link href="${staticPath}/js/advanced-datatable/css/demo_page.css" rel="stylesheet"/>
    <link href="${staticPath}/js/advanced-datatable/css/demo_table.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${staticPath}/js/data-tables/DT_bootstrap.css"/>
    <link href="${staticPath}/js/bootstrap-datetimepicker/css/datetimepicker.css" rel="stylesheet">
	<style>
	.classdisplay {
		display: none;
	}
	</style>
</head>
<body>
    <!--print select   -->
    <div id="selectPrint" style="display: none">
	<c:forEach items="${printerlist}" var="print">
        <option value="${print.id}">${print.printerName}</option>
	</c:forEach> 
	</div>

 <!--happyHour select   -->
    <div id="selectHappy" style="display: none">
	<c:forEach items="${happyHourList}" var="happy">
        <option value="${happy.id}">${happy.happyHourName}</option>
	</c:forEach> 
	</div>

<section id="main-content">
    <section class="wrapper">
        <!-- page start-->
        <div class="row">
            <div class="col-sm-12">
                <section class="panel">
                    <header class="panel-heading" style="height:67px"> 
                    
                    <div style="text-transform: capitalize;"> Revenue Center 
                    	<c:if test="${fn:length(revenueCenterList) < company.level}">
                    	<div class="btn-group pull-right">
										  	<button id="editable-sample_new" class="btn btn-primary">
                        	<i class="fa fa-plus"></i>   Revenue Center 
                        </button>
							     		</div>
							     		</c:if>
							     		<c:if test="${fn:length(revenueCenterList) >= company.level}">
											<br>you can activate 1 register
											</c:if>
				    				</div>
                </header>
                    <div class="panel-body">
                        <div class="adv-table">
                            <table class="table table-striped table-hover table-bordered" id="editable-sample">
                                <thead>
                                <tr>
                                    <th class="classdisplay">Id</th>
                                    <th class="classdisplay">PrinterId</th>
                                    <th class="classdisplay">HappyHourId</th>
                                    <th class="classdisplay">Enable</th>
                                    <th>Name</th>
                                    <th>Printer </th>
                                    <th>HappyHourName</th>
                                    <th>HappyStartTime</th>
                                    <th>HappyEndTime</th>
                                    <th>Delete</th>
                                </tr>
                                </thead>
                                <tbody>          
                                <c:forEach items="${revenueCenterList}" var="revenue"> 
								<tr class="gradeX"> 
								<td  class="center">${revenue.id}</td> 
								<td  class="center">${revenue.printId}</td> 
								<td  class="center">${revenue.happyHourId}</td> 
								<td  class="center">${revenue.isActive}</td> 
								<td  class="center">${revenue.revName}</td> 
								<td  class="center">${revenue.printerName}</td> 
								<td  class="center">${revenue.happyHourName}</td>
								<td> 
								<c:if test='${revenue.happyHourId!=0}'>
								<fmt:formatDate value="${revenue.happyStartTime}" pattern="yyyy-MM-dd"/>
								</c:if> 
								</td>
								<td  class="center">
								<c:if test='${revenue.happyHourId!=0}'>
								<fmt:formatDate value="${revenue.happyEndTime}" pattern="yyyy-MM-dd"/>
								</c:if>
								</td>
								<td>
								   <button type="button" data='${revenue.id}' class="btn btn-info"><i class="fa fa-trash-o"></i></button>
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
<!--main content end-->

<script type="text/javascript" src="${staticPath}/js/advanced-datatable/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="${staticPath}/js/data-tables/DT_update_bootstrap.js"></script>
<script type="text/javascript" src="${staticPath}/js/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script src="${staticPath}/js/bootbox.js"></script>
<script type="text/javascript" src="${staticPath}/js/jquery.validate.min.js"></script>
<script src="${staticPath}/js/rtrevenuecenter.js"></script>
<script src="${staticPath}/js/scripts.js"></script>

<script>
	jQuery(document).ready(function() {
	//初始化table
		EditableTable.init();
		//隐藏要隐藏的表格字段
		showTable();
		//左边菜单展开
		$("#rvCenterManage").addClass("active");
		$("#rvCenters").css('display', 'block');
		$("#revenueCenterLi").addClass("active");
		//上下翻页的隐藏要隐藏的字段
		$(".next").click(function() {
			showTableEach();
		});
		$(".prev").click(function() {
			showTableEach();
		});


	});
</script>
<!-- END JAVASCRIPTS -->
</body>
</html>
