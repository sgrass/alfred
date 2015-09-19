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
    <link rel="stylesheet" type="text/css" href="${staticPath}/js/select2/select2.css" />
	<style>
	.classdisplay {
		display: none;
	}
	.select2-choices{
		border-radius: 4px;
		height: 34px;
		padding: 6px 12px;
		font-size: 14px;
		border: 1px solid #eee; 
		box-shadow: none;
		color: #c2c2c2
}
	</style>
</head>
<body>

	<!--main content start-->
<section id="main-content">
    <section class="wrapper">
    	<div id="selectRes" >
          <c:forEach items="${resList}" var="res">
           <option value="${res.id}">${res.restaurantName}</option>
		  </c:forEach>
	    </div>
        <!-- page start-->
        <div class="row">
            <div class="col-sm-12">
                <section class="panel">
                    <header class="panel-heading" style="height:67px"> 
                    <div style="text-transform: capitalize;"><spring:message code="managers.subsidiary.managers"></spring:message>
                      <div class="btn-group pull-right">
										  <button id="editable-sample_new" class="btn btn-primary">
                                         <i class="fa fa-plus"></i>   <spring:message code="managers.addBut"></spring:message>
                                    </button>
							     </div>
				       </div>			     
                   </header>
                    <div class="panel-body">
                        <div class="adv-table">
                            <table class="table table-striped table-hover table-bordered" id="editable-sample">
                                <thead>
                                <tr>
                                    <th class="classdisplay">Id</th>
                                    <th><spring:message code="managers.email"></spring:message></th>
                                    <th class="classdisplay">Password</th>
                                    <th><spring:message code="managers.firstName"></spring:message></th>
                                    <th><spring:message code="managers.lastName"></spring:message></th>
                                    <th class="classdisplay">userRestaurantId</th>
                                    <th  class="classdisplay">RestaurantId</th>
                                    <th><spring:message code="managers.permissions"></spring:message></th>
                                    <th><spring:message code="managers.delete"></spring:message></th>
                                </tr>
                                </thead>
                                <tbody>          
                                <c:forEach items="${userList}" var="user"> 
								<tr class="gradeX"> 
								<td  class="center">${user.id}</td> 
								<td  class="center">${user.accountName}</td> 
								<td  class="center">${user.password}</td> 
								<td  class="center">${user.firstName}</td>
								<td  class="center">${user.lastName}</td> 
								<td  class="center">
								<c:forEach items="${user.userRestaurantList}" var="per">
								 ${per.id},
								</c:forEach>
								</td>
							    <td  class="center">	
							    <c:forEach items="${user.userRestaurantList}" var="per">
								  ${per.restaurantId},	
								</c:forEach></td> 
							    <td class="center">
                                   <a class="active" href="${contextPath}/userPermission/permission?userId=${user.id}&userName=${user.accountName}"><span ><i class="fa fa-user"></i></span></a>  
                                </td>
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
<!--main content end-->

<script type="text/javascript" src="${staticPath}/js/advanced-datatable/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="${staticPath}/js/data-tables/DT_bootstrap.js"></script>
<script src="${staticPath}/js/bootbox.js"></script>
<script type="text/javascript" src="${staticPath}/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${staticPath}/js/select2/select2.js"></script>
<script type="text/javascript" src="${staticPath}/js/select-init.js"></script>
<script type="text/javascript" src="${staticPath}/js/hqmanager.js?v=1"></script>
<script src="${staticPath}/jquery.i18n.properties-min-1.0.9.js"></script>
<script src="${staticPath}/jquery.cookie.js"></script>
<script>
	jQuery(document).ready(function() {
			//初始化table
			EditableTable.init();
			//隐藏要隐藏的表格字段
			showTable();
			//左边菜单展开
			$("#hqmanagerment_a").addClass("active");
			$("#hqmanagerment_sub").css('display', 'block');
			$("#managersli").addClass("active");
			
			
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
