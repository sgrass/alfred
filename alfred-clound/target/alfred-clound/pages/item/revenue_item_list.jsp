<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/pages/inc/common.jsp"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="ThemeBucket">
<title>item Manager</title>

<link href="${staticPath}/js/advanced-datatable/css/demo_page.css" rel="stylesheet" />
<link href="${staticPath}/js/advanced-datatable/css/demo_table.css" rel="stylesheet" />
<link rel="stylesheet" href="${staticPath}/js/data-tables/DT_bootstrap.css" />
<link rel="stylesheet" href="${staticPath}/js/jquery-pagination/pagination.css">

<!-- Just for debugging purposes. Don't actually copy this line! -->
<!--[if lt IE 9]>
  <script src="${staticPath}/js/ie8-responsive-file-warning.js"></script><![endif]-->

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
  <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
  <![endif]-->
</head>

<body>
	<section id="main-content">
		<section class="wrapper">
			<!-- page start-->
             <input type="hidden" id="revenueIds" value="${revenueId}">
			<div class="row">
				<div class="col-sm-12">
					<section class="panel">
						<header class="panel-heading tab-bg-dark-navy-blue">
<!-- 							Item List <span class="tools pull-right"> </span> -->
							<ul class="nav nav-tabs nav-justified ">
					     <c:forEach items="${revenueList}" var="revenue">
								<li id="li_${revenue.id}"><a data-toggle="tab" href="#" onclick="chooseTab(${revenue.id})">${revenue.revName}</a></li>
					     </c:forEach>
							</ul>
						</header>
						<div class="panel-body">
							<div class="adv-table editable-table ">
								<div class="clearfix">
									<div class="btn-group">
										<button id="editable-sample_new" class="btn btn-primary" onclick="location.href='${contextPath}/rvcMenu/revenueTab?revenueId=${revenueId}'">
											Edit Revenue Item 
										</button>
									</div>
								</div>
							</div>
						</div>
						<div class="panel-body">

							<table class="table table-bordered">
								<thead>
									<tr>
										<th>Item Name</th>
										<th>Price</th>
										<!-- <th>Print to</th>
										<th>Status</th> -->
										<th>Edit</th>
										<th>Delete</th>
									</tr>
								</thead>
								<tbody>
									<tr id="addTr" style="display:none">
										<td colspan="6" class="details">
											<form id="addNewForm" action="${contextPath}/reveuneItem/insert" method="post" enctype="multipart/form-data">
												<table border="0" width="100%">
													<tr>
														<td align="right">Name:</td>
														<td><input type="text" class="form-control small" name="itemName" /></td>
														<td align="right">Desc:</td>
														<td><input type="text" class="form-control small" name="itemDesc" /></td>
														<td align="right">Price:</td>
														<td><input type="text" class="form-control small" name="price" /></td>
														<td align="right">Image:</td>
														<td><input type="file" class="form-control small" name="imgFile"/></td>
													</tr>
													<tr>
														<td align="right">Printer:</td>
														<td>
															<select class="form-control small" name="printerId">
																<option value="">please select</option>
																<c:forEach var="printer" items="${printerList}">
																	<option value="${printer.id}">${printer.printerGroupName}</option>
																</c:forEach>
															</select>
														</td>
														<td align="right">Main Category:</td>
														<td>
															<select class="form-control small" name="itemMainCategoryId" onchange="setSubCategory('addNewSubcate',this.options[this.selectedIndex].value)">
																<option value="">please select</option>
																<c:forEach var="mainCate" items="${categoryList}">
																	<option value="${mainCate.id}">${mainCate.mainCategoryName}</option>
																</c:forEach>
															</select>
														</td>
														<td align="right">Subcategory:</td>
														<td>
															<select class="form-control small" name="itemCategoryId" id="addNewSubcate">
																<option value="">please select</option>
															</select>
														</td>
														<td align="right">Tax:</td>
														<td>
															<select class="form-control small" name="taxCategoryId">
																<option value="">please select</option>
																<option value="1">11</option>
															</select>
														</td>
													</tr>
													<tr>
														<td align="right">Delivery:</td>
														<td>
															<select class="form-control small" name="isPack">
																<option value="0">normal</option>
																<option value="1">disable</option>
															</select>
														</td>
														<td align="right">Take Away:</td>
														<td>
															<select class="form-control small" name="isTakeout">
																<option value="0">normal</option>
																<option value="1">disable</option>
															</select>
														</td>
														<td align="right">Happy Hour:</td>
														<td>
															<select class="form-control small" name="happyHoursId">
																<option value="">please select</option>
															</select>
														</td>
														<td align="right">Status</td>
														<td>
															<select class="form-control small" name="isActive">
																<option value="1">Enabled</option>
																<option value="0">Disabled</option>
															</select>
														</td>
													</tr>
													<tr>
														<td colspan="8" align="center">
															<input type="submit" class="btn btn-info " value="Save" /> 
															<input type="button" class="btn btn-info " value="Cancel" onclick="addItemDetail(false)" />
														</td>
													</tr>
												</table>
											</form>
										</td>
									</tr>
									<c:forEach var="item" items="${itemList}" varStatus="i">
										<tr class="">
											<td>${item.itemName}</td>
											<td>${item.price}</td>
										<%-- 	<td>${item.printerId}</td>
											<td class="center">${item.isActive}</td> --%>
											<td><a class="edit" href="javascript:;" onclick="editItemDetail(${i.index},true)">Edit</a></td>
											<td><a class="delete" href="javascript:;" onclick="deleteItem(${item.id})">Delete</a></td>
										</tr>
										<tr id="tr${i.index}" style="display: none">
											<td colspan="6" class="details">
												<form id="form${i.index}" action="${contextPath}/reveuneItem/updateById" method="post" enctype="multipart/form-data">
													<input type="hidden" name="id" value="${item.id}">
													<table border="0" width="100%">
													<tr><td><input name="revenueId" value="${revenueId }" type="hidden"></td></tr>
														<tr>
															<td align="right">Name:</td>
															<td><input type="text" class="form-control small" name="itemName" value="${item.itemName}"/></td>
															<td align="right">Description:</td>
															<td><input type="text" class="form-control small" name="itemDesc" value="${item.itemDesc}"/></td>
															<td align="right">Price:</td>
															<td><input type="text" class="form-control small" name="price" value="${item.price}"/></td>
															
														</tr>
														
														<tr>
														<td align="right"><div style="display: block;width: 100px;">Image:</div><input type="hidden" name="imgUrl2" value="${item.imgUrl}"/></td>
															<td>
																<input type="file" class="form-control small" name="imgFile"/>
																<a href="${item.imgUrl}" target="blank"><img src="${item.imgUrl}" width="50px" height="50px"></a>
													    </td>
															<td align="right">Print to:</td>
															<td>
																<select class="form-control small" name="printerId">
																	<option value="">please select</option>
																	<c:forEach var="printer" items="${printerList}">
																		<option value="${printer.id}" <c:if test="${printer.id==item.printerId}">selected</c:if>>${printer.printerGroupName}</option>
																	</c:forEach>
															</select>
															</td>
															<td align="right"><div style="display: block;width: 100px;">Main Category:</div></td>
															<td>
																<select class="form-control small" name="itemMainCategoryId" onchange="setSubCategory('subCate${i.index}',this.options[this.selectedIndex].value)">
																	<option value="">please select</option>
																	<c:forEach var="mainCate" items="${categoryList}">
																		<option value="${mainCate.id}" <c:if test="${mainCate.id==item.itemMainCategoryId}">selected</c:if>>${mainCate.mainCategoryName}</option>
																	</c:forEach>
																</select>
															</td>
														
														</tr>
														
														<tr>
														
															<td align="right">Subcategory:</td>
															<td>
																<select class="form-control small" name="itemCategoryId" id="subCate${i.index}">
																	<option value="">please select</option>
																	<c:forEach var="mainCate" items="${categoryList}">
																		<c:forEach var="subCate" items="${mainCate.itemCategoryList}">
																			<c:if test="${mainCate.id==item.itemMainCategoryId}">
																				<option value="${subCate.id}" <c:if test="${subCate.id==item.itemCategoryId}">selected</c:if>>${subCate.itemCategoryName}</option>
																			</c:if>
																		</c:forEach>
																	</c:forEach>
																</select>
															</td>
															<td align="right"><div style="display: block;width: 100px;">Tax Category:</div></td>
															<td>
																<select class="form-control small" name="taxCategoryId">
																	<option value="">please select</option>
																	 <c:forEach var="tax" items="${taxList}">
																		<option value="${tax.id}" <c:if test="${tax.id==item.taxCategoryId}">selected</c:if>>${tax.taxCategoryName}</option>
																	</c:forEach>
															
																</select>
															</td>
															<td align="right">Delivery:</td>
															<td>
																<select class="form-control small" name="isPack">
																	<option value="0">normal</option>
																	<option value="1">disable</option>
																</select>
															</td>
														</tr>
														<tr>
															
															<td align="right">Takeaway:</td>
															<td>
																<select class="form-control small" name="isTakeout">
																	<option value="0">normal</option>
																	<option value="1">disable</option>
																</select>
															</td>
															<td align="right">Happy Hours :</td>
															<td>
																<select class="form-control small" name="happyHoursId">
																	<option value="">please select</option>
																	<c:forEach var="happyHour" items="${happyHourList}">
																		<option value="${happyHour.id}" <c:if test="${happyHour.id==item.happyHoursId}">selected</c:if>>${happyHour.happyHourName}</option>
																	</c:forEach>
																</select>
															</td>
															<td align="right">Status:</td>
															<td>
																<select class="form-control small" name="isActive">
																	<option value="1">Enabled</option>
																	<option value="0">Disabled</option>
																</select>
															</td>
														</tr>
														<tr>
															<td colspan="8" align="center">
																<input type="submit" class="btn btn-info " value="Save" />
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
							<form method="post" id="queryItemForm" action="${contextPath}/reveuneItem/queryRevenueItem">
								<input type="hidden" id="pages" value="${pageSize}">
								<input type="hidden" name="currPage" id="currPage" value="${currPage}">
							</form>
							<div class="span6"><div class="dataTables_info" id="dynamic-table_info">Showing ${startRow} to ${endRow} of ${rowCount} entries</div></div>
							<div id="Pagination" class="pagination"></div>
						</div>
					</section>
				</div>
			</div>
			<!-- page end-->
		</section>
	</section>

	<%-- <script type="text/javascript" src="${staticPath}/js/advanced-datatable/js/jquery.dataTables.js"></script> --%>
	<%-- <script type="text/javascript" src="${staticPath}/js/data-tables/DT_bootstrap.js"></script> --%>
	<%-- <script src="${staticPath}/js/table-editable.js"></script> --%>
	<script type="text/javascript" src="${staticPath}/js/jquery-pagination/jquery.pagination.js"></script>
	<script src="${staticPath}/js/scripts.js"></script>
	<script src="${staticPath}/js/bootbox.js"></script>
	<script>
		jQuery(document).ready(function() {
// 			EditableTable.init();
			$('#li_'+$("#revenueIds").val()).addClass("active");
			
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
		//左边菜单展开
		$("#rvCenterManage").addClass("active");
		$("#itemCategory").css('display', 'block');
		$("#revenueItemLi").addClass("active");
	   
		
		function chooseTab(revenueId) {
			location.href="${contextPath}/reveuneItem/queryRevenueItem?revenueId="+revenueId;
		}
		
		function editItemDetail(trId,show) {
// 			$(obj).parent().parent().next().show();
			if (show){
				$("#tr"+trId).show();
			} else {
				$("#tr"+trId).hide();
			}
		}
		
		function deleteItem(itemId) {
		    bootbox.confirm("Are you sure?", function(result) {

	            if (result) {
	               // What to do here?
					location.replace("${contextPath}/reveuneItem/deleteById?id="+itemId);
	            } else {
	               // What to do here?
	            }               
	        });
			
			
			
		/* 	if (confirm("Is Delete Item ?")) {
				location.replace("${contextPath}/reveuneItem/deleteById?id="+itemId);	
			} */
		}
		
		function setSubCategory(subCateId,mainCategoryId) {
			var optionHtml = "<option value=''>please select</option>";
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
						optionHtml+="<option value='"+item.id+"'>"+item.itemCategoryName+"</option>"
					});
					$("#"+subCateId).html(optionHtml);
				} 
			}); 
		}
	</script>
	<!-- END JAVASCRIPTS -->
</body>
</html>
