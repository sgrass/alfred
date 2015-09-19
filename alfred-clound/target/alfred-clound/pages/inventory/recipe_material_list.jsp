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
							<form class="form-inline" method="post" id="queryItemForm" action="${contextPath}/recipeMaterial/queryAll">
								<div class="form-group" style="margin-left: 25px;">
									<label class="col-lg-4 control-label" style="margin-top: 8px;">MainCategory:</label>
									<div class="col-lg-8">
										<select class="form-control small" name="itemMainCategoryId" id="queryMainCate" onchange="setSubCategory('querySubcate',this.options[this.selectedIndex].value,'${itemCategoryId}')" style="width: 203px">
										  <option value="">All</option>
										  <c:forEach var="mainCate" items="${categoryList}">
												<option value="${mainCate.id}" <c:if test="${mainCate.id==itemMainCategoryId}">selected</c:if>>${mainCate.mainCategoryName}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="form-group" >
									<label class="col-lg-3 control-label" style="margin-top: 8px;">SubCategory:</label>
									<div class="col-lg-9">
										<select class="form-control small" name="itemCategoryId" id="querySubcate" style="width: 203px">
											<option value="">All</option>
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
										<th width="15%">Item Name</th>
										<th width="75%">Material</th>
										<th width="10%" align="center">Edit</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="item" items="${itemList}" varStatus="i">
										<tr class="">
											<td>${item.itemName}</td>
											<td>
												<c:set var="flag" value="false"></c:set>
												<c:forEach var="recipeMaterial" items="${rmList}" varStatus="t">
													<c:if test="${item.id eq recipeMaterial.itemId}">
														<c:if test="${flag}">,</c:if>
														<c:set var="flag" value="true"></c:set>
														${recipeMaterial.materialName}(${recipeMaterial.useQty}${recipeMaterial.unitOfMeasurement})
													</c:if>
												</c:forEach>
											</td>
											<td><a class="edit" href="javascript:;" onclick="editItemDetail(${i.index},true)">Edit</a></td>
										</tr>
										<tr id="tr${i.index}" style="display: none">
											<td colspan="3" class="details" align="left">
												<form id="form${i.index}" action="${contextPath}/recipeMaterial/insert" method="post">
													<table border="0" width="60%">
														<c:forEach var="material" items="${materialList}" varStatus="j">
															<c:set var="checked" value=""></c:set>
															<c:set var="useQty" value=""></c:set>
															<c:forEach var="recipeMaterial" items="${rmList}">
																<c:if test="${material.id eq recipeMaterial.materialId and item.id eq recipeMaterial.itemId}">
																	<c:set var="checked" value="checked"></c:set>
																	<c:set var="useQty" value="${recipeMaterial.useQty}"></c:set>
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
														<input type="hidden" name="id" value="${item.id}">
														<input type="hidden" name="itemMainCategoryId" value="${item.itemMainCategoryId}">
														<input type="hidden" name="itemCategoryId" value="${item.itemCategoryId}">
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
		$("#recipe").addClass("active");

		//小数点后面的两位
		jQuery.validator.addMethod("decimal", function (value, element) {  
			var decimal = /^-?\d+(\.\d{1,2})?$/;  
			return this.optional(element) || (decimal.test(value));  
			},
			$.validator.format("decimal point is limited to 2 digits.")
		);
		
		jQuery(document).ready(function() {
			$("#queryMainCate").trigger("change");
			
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
		
		function setSubCategory(subCateId,mainCategoryId,subCategoryId) {
			flag = "";
			var optionHtml = "<option value=''>All</option>";
			if (mainCategoryId== null || mainCategoryId=='') {
				$("#"+subCateId).html(optionHtml);
				return ;
			}
			$.ajax({
				type: "POST",
				url: "/category/queryByMainCategoryId?mainCategoryId="+mainCategoryId,
				dataType: 'json',
				success: function(data){ 
					$.each(data, function(i,item){
						if (subCategoryId == item.id) {
							flag = "selected";
						}
						optionHtml+="<option value='"+item.id+"' "+flag+">"+item.itemCategoryName+"</option>"
						flag = "";
					});
					$("#"+subCateId).html(optionHtml);
				} 
			}); 
		}
	</script>
	<!-- END JAVASCRIPTS -->
</body>
</html>
