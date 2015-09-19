<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/pages/inc/common.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <title>Alfred Cloud POS - HQ Management</title>
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

<div id="selectRes" style="display: none">
		<select class="form-control" id="checkedOption">
             <option value="1"><spring:message code="restaurant.selectRestaurant"></spring:message></option>
             <option value="2"><spring:message code="restaurant.selectCoffee"></spring:message></option>
             <option value="3"><spring:message code="restaurant.selectFood"></spring:message></option>
             <option value="4"><spring:message code="restaurant.selectBar"></spring:message></option>
		</select>
	</div>
	<!--main content start-->
	<section id="main-content">
		<section class="wrapper">
			<!-- page start-->
			<div class="row">
				<div class="col-sm-12">
					<section class="panel">
          	<header class="panel-heading" style="height:67px"> 						
						<div style="text-transform: capitalize;"><spring:message code="restaurant.subsidiary"></spring:message>
							<c:if test="${fn:length(resList) < company.level}">
						  <div class="btn-group pull-right">
								<button id="editable-sample_new" class="btn btn-primary">
											 <i class="fa fa-plus"></i><spring:message code="restaurant.addBut"></spring:message>
								</button>
							</div>
							</c:if>
							<c:if test="${fn:length(resList) >= company.level}">
							<br><spring:message code="restaurant.tip"></spring:message>
							</c:if>
						</div>
	
						</header>
						<div class="panel-body">
							<div class="adv-table" id="restaurantTable">
								
								<table class="table table-striped table-hover table-bordered"
									id="editable-sample">
									<thead>
										<tr>
										    <th class="classdisplay">Id</th>
											<th width='10%'><spring:message code="restaurant.restaurantName"></spring:message></th>
											<th><spring:message code="restaurant.email"></spring:message></th>
											<th><spring:message code="restaurant.address"></spring:message></th>
											<th><spring:message code="restaurant.tel"></spring:message></th>
											<th class="classdisplay">Country</th>
											<th class="classdisplay">State</th>
											<th class="classdisplay">City</th>
											<th class="classdisplay">Postal</th>
											<th class="classdisplay">address1</th>
											<th class="classdisplay">address2</th>
											<th class="classdisplay">description</th>
											<th class="classdisplay">Restaurant</th>
											<th><spring:message code="restaurant.restaurantName"></spring:message></th>
											<th class="classdisplay">Type</th>
											<th><spring:message code="restaurant.key"></spring:message></th>
											<th><spring:message code="restaurant.delete"></spring:message></th>
										</tr>
									</thead>
									<tbody>
									 <c:forEach items="${resList}" var="res"> 
										<tr class="gradeX">
										    <td>${res.id}</td>
										    <td><a class="active" href="${contextPath}/dashboard/index?resId=${res.id}"><span>${res.restaurantName}</span></a></td>
											<td class="center">${res.email}</td>
											<td class="center">${res.address1}  ${res.address2}</td>
											<td class="center">${res.telNo}</td>
											<td class="center">${res.country}</td>
											<td class="center">${res.state}</td>
											<td class="center">${res.city}</td>
										    <td class="center">${res.postalCode}</td>
										    <td class="center">${res.address1}</td>
										    <td class="center">${res.address2}</td>
										    <td class="center">${res.description}</td>
										    <td class="center">${res.restaurantName}</td>
										    <td>
												 <c:forEach items="${res.usersList}" var="user"> 
												 	${user.accountName}
												 </c:forEach>
											</td>
											 <td class="center">${res.type}</td>
											 <td class="center">${res.restaurantKey}</td>
											<td class="center">
												<button type="button" data='${res.id}'class="btn btn-info">
													<i class="fa fa-trash-o"></i>
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
	<div id="txtregion"></div>
    <div id="txtplacename"></div>
<script type="text/javascript" src="${staticPath}/js/advanced-datatable/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="${staticPath}/js/data-tables/DT_update_bootstrap.js"></script>
<script src="${staticPath}/js/scripts.js"></script>
<script src="${staticPath}/js/bootbox.js"></script>
<script type="text/javascript" src="${staticPath}/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${staticPath}/js/city_state.js"></script>
<script src="${staticPath}/js/hqrestaurant.js?v=1"></script>
<script src="${staticPath}/jquery.i18n.properties-min-1.0.9.js"></script>
<script src="${staticPath}/jquery.cookie.js"></script>
<script>
	
	
	jQuery(document).ready(function() {
		//初始化话表格
		EditableTable.init();
		//隐藏要隐藏的表格字段
		showTable();
		//左边菜单展开
		$("#hqmanagerment_a").addClass("active");
		$("#hqmanagerment_sub").css('display', 'block');
		$("#enterpriseli").addClass("active");
		
		//上下翻页的隐藏要隐藏的字段
      //上下翻页的隐藏要隐藏的字段
	        $(".next").click(function(){
	        	showTableEach();
	        });
	        $(".prev").click(function(){
	        	showTableEach();
	        });
        
	        $("#editable-sample_filter").hide();
			$("#editable-sample_length").hide();

	});
</script>
<!-- END JAVASCRIPTS -->
</body>
</html>
