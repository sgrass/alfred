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
<div id="placeIdDiv">
<input type="hidden" value="${placeIdStr}">
</div>
	<!--main content start-->
	<section id="main-content">
		<section class="wrapper">
			<!-- page start-->
			<div class="row">
				<div class="col-md-12">
					<section class="panel">
						<header class="panel-heading tab-bg-dark-navy-blue">
							<ul class="nav nav-tabs nav-justified " style="text-transform: none;">
							     <c:forEach items="${revenueCenterList}" var="rev">
								<li id="li_${rev.id}"><a data-toggle="tab" href="#" onclick="location.href='${contextPath}/placeTable/revenueTab?revenueId=${rev.id}'">${rev.revName}</a></li>
							     </c:forEach>
							</ul>
						</header>
						<div class="panel-body">
						  <div class="row" style="margin-left:5px;background: #D3D3D3;">
						          	<div class="btn-group">
											<button id="add_place__new" class="btn btn-primary">
												 <i class="fa fa-plus"></i>Places
											</button>
										</div>
						        </div>	
				<div class="row" id="addPlaces" style="display:none">        
                <div class="col-lg-12">
                <section class="panel">
                    <header class="panel-heading">
                     <div class="btn-group">
                               ADD PLACES
                                </div>
                                <div class="btn-group pull-right">
                                     <button type="button" class="btn btn-success" id="addPlaceBut" style="margin-right:20px;"><i class="fa fa-save"></i>  Save</button>
                                      <button type="button" class="btn btn-success" id="cancenPlaceBut">  Cancel</button>
                                </div>
                    </header>
                    <div class="panel-body">
                        <div class="adv-table editable-table ">
                           <form id="addplacetablefrom">
                            <table id="addplacetable">
								<tbody>
									<tr class="gradeX">
										<td>placesName</td>
										<td><input class=" form-control" name="placesName" type="text" required /></td>
									</tr>
									<tr class="gradeX">
										<td>placeDes</td>
										<td><input class=" form-control" name="placesName" type="text" /></td>
									</tr>
									<tr class="gradeX">
										<td>isActive</td>
										<td><input type="checkbox" name="isActive" checked/></td>
									</tr>
									<tr class="gradeX" style="display:none">
									
										<td><input type="hidden" value="${revenueId}"/></td>
									</tr>
								</tbody>
				             </table>
				             </form>
                        </div>
                    </div>
                </section>
                </div>
	             </div>
							<div class="tab-content tasi-tab">
								<div id="overview" class="tab-pane active">
									<c:forEach items="${placeTableList}" var="place">
									 
									<div class="row">
									 <div class="col-sm-12">		 
                <section class="panel" id="placeaddtable_${place.id}" style="margin-top: 15px;">
                    <header class="panel-heading">
                    <div class="row">
                                <div class="pull-left" style="text-transform: none;">
                                ${place.placeName}
                                </div>
                                <div class=" pull-right">
                                     <button type="button" data='${place.id}'  dataRevenueId="${revenueId}" class="btn btn-primary" style="margin-right:20px;" id="editable-sample_new_${place.id}"><i class="fa fa-plus"></i>Table</button>
                                     <button type="button" data='${place.id}' class="btn btn-success"><i class="fa fa-edit"></i>  Edit</button>
                                </div>
                    </div>
                    </header>
                    <div class="panel-body">
                        <div class="adv-table editable-table ">
                            <table class="table table-striped table-hover table-bordered" id="editable-sample_${place.id}">
                                <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>revenueId</th>
                                    <th>placesId</th>
                                    <th>isActive</th>
                                    <th>TableName</th>
                                    <th>TablePacks</th>
                                    <th>isActive</th>
                                    <th>Edit</th>
                                    <th>Delete</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${place.tablesList}" var="table"> 
                                <c:if test="${table.isActive!=-1}">
								<tr class="gradeX"> 
								<td  class="center">${table.id}</td> 
								<td  class="center">${table.revenueId}</td> 
								<td  class="center">${table.placesId}</td> 
								<td  class="center">${table.isActive}</td> 
								
								<td  class="center">${table.tableName}</td> 
							    <td  class="center">${table.tablePacks}</td> 
							    <td class="center">
							     <c:if test="${table.isActive==0}">false</c:if>
								 <c:if test="${table.isActive==1}">true</c:if>
							    </td>
								<td><a data="${table.id}" class="edit" href="javascript:;">Edit</a></td>
                                <td><a data="${table.id}" class="delete" href="javascript:;">Delete</a></td>
								</tr> 
								</c:if>
                                </c:forEach> 
                                </tbody>
                            </table>
                        </div>
                    </div>
                </section>
                 <section class="panel " style="display:none" id="placeedittable_${place.id}" >
                    <header class="panel-heading">
                     <div class="btn-group" style="text-transform: none;">
                               ${place.placeName}
                                </div>
                                <div class="btn-group pull-right">
                                
                                     <button type="button" data='${place.id}' class="btn btn-success editPlaceBut"  style="margin-right:20px"><i class="fa fa-save"></i>  Save</button>
                                      <button type="button" data='${place.id}' class="btn btn-success delPlaceBut"><i class="fa fa-trash-o"></i>  Delete</button>
                                </div>
                    </header>
                    <div class="panel-body">
                        <div class="adv-table editable-table ">
                            <form id="form_${place.id}">
                            <table id="place_${place.id}">
								<tbody>
									<tr class="gradeX">
										<td>placesName</td>
										<td><input class=" form-control" name="placesName" type="text" required  value="${place.placeName}"/></td>
									</tr>
									<tr class="gradeX">
										<td>placeDes</td>
										<td><input class=" form-control" name="placesName" type="text" value="${place.placeDescription}"/></td>
									</tr>
									<tr class="gradeX">
										<td>isActive</td>
										<td><input type="checkbox" name="isActive" checked/></td>
									</tr>
									<tr class="gradeX" style="display:none">
									
										<td><input type="hidden" value="${revenueId}"/></td>
									</tr>
								</tbody>
				             </table>
				             </form>
                        </div>
                    </div>
                </section>
            </div>
									</div>
							        </c:forEach>
								</div>
							</div>
						</div>
					</section>
				</div>
			</div>
			<!-- page end-->
		</section>
	</section>
	<!--main content end-->
	<script type="text/javascript" src="${staticPath}/js/advanced-datatable/js/jquery.dataTables10.js"></script>
	<script type="text/javascript" src="${staticPath}/js/data-tables/DT_bootstrap.js"></script>
	<script type="text/javascript" src="${staticPath}/js/jquery.validate.min.js"></script>
	<script src="${staticPath}/js/scripts.js"></script>
	<script src="${staticPath}/js/bootbox.js"></script>
	<script src="${staticPath}/js/rttablesplaces.js?v=1"></script>
</body>
</html>
