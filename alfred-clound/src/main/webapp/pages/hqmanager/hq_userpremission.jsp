<!DOCTYPE html>
<%@include file="/pages/inc/common.jsp" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="ThemeBucket">
    <title>Role Permissions</title>
    <link rel="stylesheet" href="${staticPath}/css/bootstrap-switch.css"/>
</head>
<body>

   <div id="divUserId" style="display:none">
       <input type="text" value="${userId}"/>            
    </div>
<!--main content start-->
<section id="main-content">
<section class="wrapper">

        <!-- page start-->
        <div class="row">
        <div class="col-sm-12">
        <section class="panel">
                    <header class="panel-heading">
                    	<div style="text-transform: capitalize;"> <spring:message code="permissions.userName"></spring:message>:  ${userName}      </div>
                                              
                    </header>
                </section>
        </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <div class="row-fluid" id="draggable_portlets">
                       <div class="col-md-3 column sortable">
                       <c:forEach var="map" items="${permissionMap}"  begin="0" end="${fn:length(permissionMap)}" step="4">
                        <!-- BEGIN Portlet PORTLET-->
								<div class="row">
									<div class="col-md-12">
										<div class="panel panel-warning">
											<div class="panel-heading">
												<strong>${map.key}</strong> <span class="tools pull-right">
													<a class="fa fa-chevron-down" href="javascript:;"></a>
												</span>
											</div>
											<div class="panel-body">
												<table class="table" id="table_${map.key}">
													<th></th>
													<th style="text-align: center">Access</th>
													
													<c:forEach var="permission" items="${map.value}">
														<tr>
                                                            <td style="display:none">${permission.permissRule}</td>
															<td>${permission.permissName}</td>
															<td style="display:none">${permission.permissId}</td>
															<td><input type="checkbox" name="${permission.permissName}"
																${permission.permissDesc} class="switch-mini" /></td>
														</tr>
													</c:forEach>
													
												</table>
												<div class="text-center">
												<button type="button"class="btn btn-info" data="${map.key}"><i class="fa fa-save"></i>  <spring:message code="permissions.saveBut"></spring:message></button>
											    </div>
											</div>
										</div>
									</div>
								</div>
								<!-- END Portlet PORTLET-->
						    </c:forEach>
                         </div> 
                       <div class="col-md-3 column sortable">
                       <c:forEach var="map" items="${permissionMap}"  begin="1" end="${fn:length(permissionMap)}" step="4">
                        <!-- BEGIN Portlet PORTLET-->
								<div class="row">
									<div class="col-md-12">
										<div class="panel panel-warning">
											<div class="panel-heading">
												<strong>${map.key}</strong> <span class="tools pull-right">
													<a class="fa fa-chevron-down" href="javascript:;"></a>
												</span>
											</div>
											<div class="panel-body">
												<table class="table" id="table_${map.key}">
													<th></th>
													<th style="text-align: center">Access</th>
													
													<c:forEach var="permission" items="${map.value}">
														<tr>
														    <td style="display:none">${permission.permissRule}</td>
															<td>${permission.permissName}</td>
														    <td style="display:none">${permission.permissId}</td>
															<td><input type="checkbox" name="${permission.permissName}"
																${permission.permissDesc} class="switch-mini" /></td>
														</tr>
													</c:forEach>
													
												</table>
												<div class="text-center">
												<button type="button"class="btn btn-info" data="${map.key}"><i class="fa fa-save"></i>  <spring:message code="permissions.saveBut"></spring:message></button>
											    </div>
											</div>
										</div>
									</div>
								</div>
								<!-- END Portlet PORTLET-->
						    </c:forEach>
                         </div> 
			
                       <div class="col-md-3 column sortable">
                       <c:forEach var="map" items="${permissionMap}" begin="2" end="${fn:length(permissionMap)}" step="4">
                        <!-- BEGIN Portlet PORTLET-->
								<div class="row">
									<div class="col-md-12">
										<div class="panel panel-warning">
											<div class="panel-heading">
												<strong>${map.key}</strong> <span class="tools pull-right">
													<a class="fa fa-chevron-down" href="javascript:;"></a>
												</span>
											</div>
											<div class="panel-body">
												<table class="table" id="table_${map.key}">
													<th></th>
													<th style="text-align: center">Access</th>
													
													<c:forEach var="permission" items="${map.value}">
														<tr>
														    <td style="display:none">${permission.permissRule}</td>
															<td>${permission.permissName}</td>
                                                            <td style="display:none">${permission.permissId}</td>
															<td><input type="checkbox" name="${permission.permissName}"
																${permission.permissDesc} class="switch-mini" /></td>
														</tr>
													</c:forEach>
													
												</table>
												<div class="text-center">
												<button type="button"class="btn btn-info" data="${map.key}"><i class="fa fa-save"></i>  <spring:message code="permissions.saveBut"></spring:message></button>
											    </div>
											</div>
										</div>
									</div>
								</div>
								<!-- END Portlet PORTLET-->
						    </c:forEach>
                       </div> 
			
                       <div class="col-md-3 column sortable">
                       <c:forEach var="map" items="${permissionMap}" begin="3" end="${fn:length(permissionMap)}" step="4">
                        <!-- BEGIN Portlet PORTLET-->
								<div class="row">
									<div class="col-md-12">
										<div class="panel panel-warning">
											<div class="panel-heading">
												<strong>${map.key}</strong> <span class="tools pull-right">
													<a class="fa fa-chevron-down" href="javascript:;"></a>
												</span>
											</div>
											<div class="panel-body">
												<table class="table" id="table_${map.key}">
													<th></th>
													<th style="text-align: center">Access</th>
													
													<c:forEach var="permission" items="${map.value}">
														<tr>
														     <td style="display:none">${permission.permissRule}</td>
															 <td>${permission.permissName}</td>
                                                             <td style="display:none">${permission.permissId}</td>
															 <td><input type="checkbox" name="${permission.permissName}"
																${permission.permissDesc} class="switch-mini" /></td>
														</tr>
													</c:forEach>
													
												</table>
												<div class="text-center">
												<button type="button"class="btn btn-info" data="${map.key}"><i class="fa fa-save"></i>  <spring:message code="permissions.saveBut"></spring:message></button>
											    </div>
											</div>
										
										</div>
									</div>
								</div>
								<!-- END Portlet PORTLET-->
						    </c:forEach>
                         </div> 	
                </div>
            </div>
        </div>
        <!-- page end-->
</section>
</section>

<script src="${staticPath}/js/bootstrap-switch.js"></script>
<script src="${staticPath}/js/toggle-init.js"></script>
<script src="${staticPath}/js/scripts.js"></script>
<script src="${staticPath}/js/bootbox.js"></script>
<script src="${staticPath}/js/userpermission.js?v=1"></script>
<script>
	$(document).ready(function() {
€
		$("#hqmanagerment_a").addClass("active");
		$("#hqmanagerment_sub").css('display', 'block');
		//$("#enterpriseli").addClass("active");
		

	});
</script>
</body>
</html>