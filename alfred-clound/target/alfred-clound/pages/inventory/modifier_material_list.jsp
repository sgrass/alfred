<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/pages/inc/common.jsp"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="ThemeBucket">
<title>material Manager</title>

<link href="${staticPath}/js/advanced-datatable/css/demo_page.css" rel="stylesheet" />
<link href="${staticPath}/js/advanced-datatable/css/demo_table.css" rel="stylesheet" />
<link rel="stylesheet" href="${staticPath}/js/data-tables/DT_bootstrap.css" />
<link rel="stylesheet" href="${staticPath}/js/jquery-pagination/pagination.css">

</head>

<body>
	<section id="main-content">
		<section class="wrapper">
			<!-- page start-->
			<div class="row">
				<div class="col-sm-12">
					<section class="panel">
                    <header class="panel-heading" style="height:67px"> 
							<div style="text-transform: capitalize;">
							<div class="btn-group pull-right">
								<button id="editable-sample_new" class="btn btn-primary" onclick="addItemDetail(true)"><i class="fa fa-plus"></i> Material</button>
							</div>
							</div>
						</header>
						<div class="panel-body">
						<div class="row" style="margin-bottom: 40px;">
							<form class="form-inline" method="post" id="queryItemForm" action="${contextPath}/modifierMaterial/queryAll">
								<div class="form-group" style="margin-left: 25px;">
									<label class="col-lg-4 control-label" style="margin-top: 8px;">ModifierCategory:</label>
									<div class="col-lg-8">
										<select class="form-control small" name="modifierId" id="modifierCategoryId"  style="width: 250px">
										  <option value="">All</option>
										  <c:forEach var="modifierCate" items="${modifierCateList}">
												<option value="${modifierCate.id}" <c:if test="${modifierCate.id==modifierId}">selected</c:if>>${modifierCate.categoryName}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="form-group" >
									<button id="editable-sample_new" class="btn btn-primary"><i class="fa fa-search"></i> Query</button>
								</div>
								<input type="hidden" id="pages" value="${pageSize}">
								<input type="hidden" name="currPage" id="currPage" value="${currPage}">
						  </form>
					  </div>
						<div class="panel-body">
							<table class="table table-bordered">
								<thead>
									<tr>
										<th width="15%">Modifier Name</th>
										<th width="75%">Material</th>
										<th width="10%" align="center">Edit</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="modifier" items="${modifierList}" varStatus="i">
										<tr class="">
											<td>${modifier.modifierName}</td>
											<td>
												<c:set var="flag" value="false"></c:set>
												<c:forEach var="mMaterial" items="${rmList}" varStatus="t">
													<c:if test="${modifier.id eq mMaterial.modifierId}">
														<c:if test="${flag}">,</c:if>
														<c:set var="flag" value="true"></c:set>
														${mMaterial.materialName}(${mMaterial.useQty}${mMaterial.unitOfMeasurement})
													</c:if>
												</c:forEach>
											</td>
											<td><a class="edit" href="javascript:;" onclick="editItemDetail(${i.index},true)">Edit</a></td>
										</tr>
										<tr id="tr${i.index}" style="display: none">
											<td colspan="3" class="details" align="left">
												<form id="form${i.index}" action="${contextPath}/modifierMaterial/insert" method="post">
													<table border="0" width="60%">
														<c:forEach var="material" items="${materialList}" varStatus="j">
															<c:set var="checked" value=""></c:set>
															<c:set var="useQty" value=""></c:set>
															<c:forEach var="mMaterial" items="${rmList}">
																<c:if test="${material.id eq mMaterial.materialId and modifier.id eq mMaterial.modifierId}">
																	<c:set var="checked" value="checked"></c:set>
																	<c:set var="useQty" value="${mMaterial.useQty}"></c:set>
																</c:if>
															</c:forEach>
															<tr>
																<td align="right" width="10%"><input type="checkbox" name="checkedIds" value="${j.index}" ${checked}></td>
																<td width="10%">${material.rawMaterialName}</td>
																<td width="75%"><input type="text" class="form-control small" name="useQyt${j.index}" value="${useQty }"/></td>
																<td width="5%" align="left">${material.unitOfMeasurement}</td>
																<input type="hidden" name="materialId${j.index}" value="${material.id}">
																<input type="hidden" name="materialName${j.index}" value="${material.rawMaterialName}">
																<input type="hidden" name="unitOfMeasurement${j.index}" value="${material.unitOfMeasurement}">
															</tr>
														</c:forEach>
														<input type="hidden" name="modifierId" value="${modifier.id}">
														<tr>
															<td colspan="3" align="center">
																<input type="submit" data="${i.index}" class="btn btn-info  updateClass" value="Save" />
																<input type="button" class="btn btn-info " value="Cancel" onclick="editItemDetail(${i.index},false)" />
															</td>
														</tr>
													</table>
												</form>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<div class="span6"><div class="dataTables_info" id="dynamic-table_info">Showing ${startRow} to ${endRow} of ${rowCount} entries</div></div>
							<div id="Pagination" class="pagination"></div>
						</div>
					</section>
				</div>
			</div>
			<!-- page end-->
		</section>
	</section>

	<script src="${staticPath}/js/scripts.js"></script>
    <script src="${staticPath}/js/bootbox.js"></script>
    <script type="text/javascript" src="${staticPath}/js/jquery.validate.min.js"></script>
    <script type="text/javascript" src="${staticPath}/js/jquery-pagination/jquery.pagination.js"></script>
	<script>
	
		//左边菜单展开
		$("#inventory").addClass("active");
		$("#inventory_sub").css('display', 'block');
		$("#modifier").addClass("active");

		//小数点后面的两位
		jQuery.validator.addMethod("decimal", function (value, element) {  
			var decimal = /^-?\d+(\.\d{1,2})?$/;  
			return this.optional(element) || (decimal.test(value));  
			},
			$.validator.format("decimal point is limited to 2 digits.")
		);
		
		jQuery(document).ready(function() {
			var pages = $("#pages").val();
			if (pages < 1) pages = 1;
			var currPage = $("#currPage").val();
			$("#Pagination").pagination(pages, {
				current_page : currPage - 1,
				//两侧显示的首尾分页的条目数,默认是0
				num_edge_entries : 1,
				//连续分页主体部分显示的分页条目数,默认是10
				num_display_entries : 5,
				callback : function(page) {
					$("#currPage").val(++page);
					$("#queryItemForm").submit();
				},
				items_per_page : 1
			});
			
		});
		
		function editItemDetail(trId,show) {
			if (show){
				$("#tr"+trId).show();
			} else {
				$("#tr"+trId).hide();
			}
		}
	</script>
	<!-- END JAVASCRIPTS -->
</body>
</html>
