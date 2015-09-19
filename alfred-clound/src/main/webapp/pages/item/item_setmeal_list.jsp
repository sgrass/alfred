<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/pages/inc/common.jsp"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="ThemeBucket">
<title>item set meal Manager</title>
<link href="${staticPath}/js/advanced-datatable/css/demo_page.css" rel="stylesheet" />
<link href="${staticPath}/js/advanced-datatable/css/demo_table.css" rel="stylesheet" />
<link rel="stylesheet" href="${staticPath}/js/data-tables/DT_bootstrap.css" />
<link rel="stylesheet" href="${staticPath}/js/file-uploader/css/jquery.fileupload.css">
<link rel="stylesheet" href="${staticPath}/js/file-uploader/css/jquery.fileupload-ui.css">
<link rel="stylesheet" href="${staticPath}/js/jquery-pagination/pagination.css">
<link rel="stylesheet" type="text/css" href="${staticPath}/js/select2/select2.css" />

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
								<button id="editable-sample_new" class="btn btn-primary" onclick="addItemDetail(true)"><i class="fa fa-plus"></i> <spring:message code="product.item.setmeal"></spring:message></button>
							</div>
							</div>
						</header>
						<div class="panel-body">
							<div class="row" style="margin-bottom: 40px;">
								<form class="form-inline" method="post" id="queryItemForm" action="${contextPath}/setMeal/queryAll">
									<div class="form-group" style="margin-left: 25px;">
										<label class="col-lg-4 control-label" style="margin-top: 8px;white-space:nowrap;"><spring:message code="product.main.category.name"></spring:message>:</label>
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
										<label class="col-lg-3 control-label" style="margin-top: 8px;white-space:nowrap;"><spring:message code="product.sub.category.name"></spring:message>:</label>
										<div class="col-lg-9">
											<select class="form-control small" name="itemCategoryId" id="querySubcate" style="width: 203px">
												<option value="">All</option>
											</select>
										</div>
									</div>
									<div class="form-group style="margin-left: 10px;" >
										<button id="editable-sample_new" class="btn btn-primary"><i class="fa fa-search"></i> <spring:message code="product.button.query"></spring:message></button>
									</div>
									<input type="hidden" id="pages" value="${pageSize}">
									<input type="hidden" name="currPage" id="currPage" value="${currPage}">
							  </form>
						  </div>
							<table class="table table-bordered">
								<thead>
									<tr>
										<th><spring:message code="product.item.name"></spring:message></th>
										<th><spring:message code="product.item.price"></spring:message></th>
							 			<th><spring:message code="product.printer"></spring:message></th>
										<th><spring:message code="product.item.tax"></spring:message></th> 
										<th><spring:message code="product.button.edit"></spring:message></th>
										<th><spring:message code="product.button.delete"></spring:message></th>
									</tr>
								</thead>
								<tbody>
									<tr id="addTr" style="display:none">
										<td colspan="6" class="details">
											<form id="addNewForm" action="${contextPath}/setMeal/insert" method="post" enctype="multipart/form-data">
												<table border="0" width="100%">
													<tr>
														<td align="right"><spring:message code="product.item.setmeal"></spring:message>:</td>
														<td><input type="text" class="form-control small" name="itemName" required /></td>
														<td align="right"><spring:message code="product.item.description"></spring:message>:</td>
														<td><input type="text" class="form-control small" name="itemDesc" /></td>
														<td align="right"><spring:message code="product.item.price"></spring:message>:</td>
														<td><input type="text" class="form-control small" name="price" /></td>
														
													</tr>
													<tr>
													    <td align="right"><spring:message code="product.item.image"></spring:message>:</td>
														<td><input type="file" class="form-control small" name="imgFile"/></td>
														<td align="right"><spring:message code="product.printer"></spring:message>:</td>
														<td>
															<select class="form-control small" name="printerId">
															    <option value="">please select</option>
																<c:forEach var="printer" items="${printerList}">
																	<option value="${printer.id}">${printer.printerGroupName}</option>
																</c:forEach>
															</select>
														</td>
														<td align="right"><spring:message code="product.main.category.name"></spring:message>:</td>
														<td>
															<select class="form-control small" name="itemMainCategoryId" onchange="setSubCategory('addNewSubcate',this.options[this.selectedIndex].value)" required>
																<option value="">please select</option>
																<c:forEach var="mainCate" items="${categoryList}">
																	<option value="${mainCate.id}">${mainCate.mainCategoryName}</option>
																</c:forEach>
															</select>
														</td>
														
													</tr>
													<tr>
													
													<td align="right"><spring:message code="product.sub.category.name"></spring:message>:</td>
														<td>
															<select class="form-control small" name="itemCategoryId" id="addNewSubcate" required>
																<option value="">please select</option>
															</select>
														</td>
														<td align="right"><div style="display: block;width: 100px;"><spring:message code="product.item.tax"></spring:message>:</div></td>
														<td>
															<select class="form-control small" name="taxCategoryId">
															        <option value="">please select</option>
																	<c:forEach var="tax" items="${taxList}">
																		<option value="${tax.id}">${tax.taxCategoryName}</option>
																	</c:forEach>
															</select>
														</td>
														<td align="right"><spring:message code="product.item.delivery"></spring:message>:</td>
														<td>
															<select class="form-control small" name="isPack">
																<option value="0">normal</option>
																<option value="1">disable</option>
															</select>
														</td>
													</tr>
													<tr>
														
														<td align="right"><spring:message code="product.item.takeaway"></spring:message>:</td>
														<td>
															<select class="form-control small" name="isTakeout">
																<option value="0">normal</option>
																<option value="1">disable</option>
															</select>
														</td>
														<td align="right"><spring:message code="product.item.happy"></spring:message>:</td>
														<td>
															<select class="form-control small" name="happyHoursId">
																<option value="">please select</option>
																	<c:forEach var="happyHour" items="${happyHourList}">
																		<option value="${happyHour.id}">${happyHour.happyHourName}</option>
																	</c:forEach>
															</select>
														</td>
														<td align="right"><spring:message code="product.status"></spring:message></td>
														<td>
															<select class="form-control small" name="isActive">
																<option value="1">Enabled</option>
																<option value="0">Disabled</option>
															</select>
														</td>
													</tr>
													<tr>
													  <td align="right"><spring:message code="product.item.name"></spring:message></td>
													  	<td>
															<select  multiple required name="itemSeal" style="width:323px" class="populate">
																<c:forEach items="${itemSelectList}" var="items">
																 <option value="${items.id }">${items.itemName}</option>
																 </c:forEach>
															</select>
														</td>
													  
													  
														<td colspan="8" align="center">
															<input type="button" id="form_add" class="btn btn-info " value="<spring:message code="product.button.save"></spring:message>" /> 
															<input type="button" class="btn btn-info " value="<spring:message code="product.button.cancel"></spring:message>" onclick="addItemDetail(false)" />
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
											<td>
											<c:forEach var="printer" items="${printerList}">
											<c:if test="${item.printerId==printer.id}">
											${printer.printerGroupName}
											</c:if>
										     </c:forEach>
											</td>
											<td class="center">
											  <c:forEach var="tax" items="${taxList}">
											  <c:if test="${tax.id==item.taxCategoryId}">
											  ${tax.taxCategoryName}
											  </c:if>
											  </c:forEach>
											
											</td>
											<td><a class="edit" href="javascript:;" onclick="editItemDetail(${i.index},true)"><spring:message code="product.button.edit"></spring:message></a></td>
											<td><a class="delete" href="javascript:;" onclick="deleteItem(${item.id})"><spring:message code="product.button.delete"></spring:message></a></td>
										</tr>
										<tr id="tr${i.index}" style="display: none">
											<td colspan="6" class="details">
												<form id="form${i.index}" action="${contextPath}/setMeal/updateById" method="post" enctype="multipart/form-data">
													<input type="hidden" name="id" value="${item.id}">
													<table border="0" width="100%">
														<tr>
															<td align="right"><spring:message code="product.item.setmeal"></spring:message>:</td>
															<td><input type="text" class="form-control small" name="itemName" value="${item.itemName}" required/></td>
															<td align="right"><spring:message code="product.item.description"></spring:message>:</td>
															<td><input type="text" class="form-control small" name="itemDesc" value="${item.itemDesc}"/></td>
															<td align="right"><spring:message code="product.item.price"></spring:message>:</td>
															<td><input type="text" class="form-control small" name="price" value="${item.price}" /></td>
															
														</tr>
														
														<tr>
														<td align="right"><div style="display: block;width: 100px;"><spring:message code="product.item.image"></spring:message>:</div><input type="hidden" name="imgUrl2" value="${item.imgUrl}"/></td>
															<td>
																<input type="file" class="form-control small" name="imgFile"/>
																<a href="${item.imgUrl}" target="blank"><img src="${item.imgUrl}" width="50px" height="50px"></a>
													    </td>
															<td align="right"><spring:message code="product.printer"></spring:message>:</td>
															<td>
																<select class="form-control small" name="printerId">
																    <option value="">please select</option>
																	<c:forEach var="printer" items="${printerList}">
																		<option value="${printer.id}" <c:if test="${printer.id==item.printerId}">selected</c:if>>${printer.printerGroupName}</option>
																	</c:forEach>
															</select>
															</td>
															<td align="right"><div style="display: block;width: 100px;"><spring:message code="product.main.category.name"></spring:message>:</div></td>
															<td>
																<select class="form-control small" name="itemMainCategoryId" onchange="setSubCategory('subCate${i.index}',this.options[this.selectedIndex].value)" required>
																	<option value="">please select</option>
																	<c:forEach var="mainCate" items="${categoryList}">
																		<option value="${mainCate.id}" <c:if test="${mainCate.id==item.itemMainCategoryId}">selected</c:if>>${mainCate.mainCategoryName}</option>
																	</c:forEach>
																</select>
															</td>
														
														</tr>
														
														<tr>
														
															<td align="right"><spring:message code="product.sub.category.name"></spring:message>:</td>
															<td>
																<select class="form-control small" name="itemCategoryId" id="subCate${i.index}" required>
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
															<td align="right"><div style="display: block;width: 100px;"><spring:message code="product.item.tax"></spring:message>:</div></td>
															<td>
																<select class="form-control small" name="taxCategoryId">
																    <option value="">please select</option>
																     <c:forEach var="tax" items="${taxList}">
																		<option value="${tax.id}" <c:if test="${tax.id==item.taxCategoryId}">selected</c:if>>${tax.taxCategoryName}</option>
																	</c:forEach>
															
																</select>
															</td>
															<td align="right"><spring:message code="product.item.delivery"></spring:message>:</td>
															<td>
																<select class="form-control small" name="isPack">
																	<option value="0">normal</option>
																	<option value="1">disable</option>
																</select>
															</td>
														</tr>
														<tr>
															
															<td align="right"><spring:message code="product.item.takeaway"></spring:message>:</td>
															<td>
																<select class="form-control small" name="isTakeout">
																	<option value="0">normal</option>
																	<option value="1">disable</option>
																</select>
															</td>
															<td align="right"><spring:message code="product.item.happy"></spring:message>:</td>
															<td>
																<select class="form-control small" name="happyHoursId">
																	<option value="">please select</option>
																	<c:forEach var="happyHour" items="${happyHourList}">
																		<option value="${happyHour.id}" <c:if test="${happyHour.id==item.happyHoursId}">selected</c:if>>${happyHour.happyHourName}</option>
																	</c:forEach>
																</select>
															</td>
															<td align="right"><spring:message code="product.status"></spring:message>:</td>
															<td>
																<select class="form-control small" name="isActive">
																	<option value="1">Enabled</option>
																	<option value="0">Disabled</option>
																</select>
															</td>
														</tr>
														<tr>
														 <td align="right"><spring:message code="product.item.name"></spring:message></td>
													  	<td>
													  	
															<select  multiple required name="itemSeal" style="width:323px" class="populate">
																<c:forEach items="${itemSelectList}" var="items">
																
																		<c:set var="selected" value=""></c:set>
																		<c:forEach var="md" items="${item.itemSetMealList}">
																			<c:if
																				test="${items.id eq md.itemId}">
																				<c:set var="selected" value="selected"></c:set>
																			</c:if>
																		</c:forEach>
																		<option value="${items.id }" ${selected}>${items.itemName}</option>
																 </c:forEach>
															</select>
														</td>
															<td colspan="8" align="center">
																<input type="button"  data="${i.index}" class="btn btn-info  updateClass" value="<spring:message code="product.button.save"></spring:message>" />
																<input type="button" class="btn btn-info " value="<spring:message code="product.button.cancel"></spring:message>" onclick="editItemDetail(${i.index},false)" />
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
    <script type="text/javascript" src="${staticPath}/js/select2/select2.js"></script>
    <script type="text/javascript" src="${staticPath}/js/select-init.js"></script>
    <script type="text/javascript" src="${staticPath}/js/jquery.validate.min.js"></script>
    <script type="text/javascript" src="${staticPath}/js/jquery-pagination/jquery.pagination.js"></script>
	<script>
	
		//左边菜单展开
		$("#itemManage").addClass("active");
		$("#itemCategory").css('display', 'block');
		$("#itemSetmealDetail").addClass("active");
		//小数点后面的两位
		jQuery.validator.addMethod("decimal", function (value, element) {  
			   var decimal = /^-?\d+(\.\d{1,2})?$/;  
			   return this.optional(element) || (decimal.test(value));  
			     },
			     $.validator.format("decimal point is limited to 2 digits.")); 
		
		jQuery(document).ready(function() {
// 			EditableTable.init();


            $(".populate").select2();
            
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
			
			$("#form_add").click(function(){
				   var validator=$("#addNewForm").validate({
					   rules: {
		             		  'price':{
		             			 decimal:true ,
		             			 min:0
		             		   }
		                    },
		                    messages: {  
		                    	'price':'please enter number'
		                    } 
					   
				   });
	            	var check = validator.form();
	        		if(check){
	        			$("#addNewForm").submit();
	        		}
				
			})
			
		
			
		 $(".updateClass").click(function(){
			var index=$(this).attr("data");
			var validator=$("#form"+index).validate({
				 rules: {
            		  'price':{
            			  decimal:true ,
            			  min:0
            			  }
                   },
                   messages: {  
                   	'price':'please enter number'
                   } 
				
			});
         	var check = validator.form();
     		if(check){
     			$("#form"+index).submit();
     		}
			 
		 })
			
			
		});
		
		function editItemDetail(trId,show) {
// 			$(obj).parent().parent().next().show();
			if (show){
				$("#tr"+trId).show();
			} else {
				$("#tr"+trId).hide();
			}
		}
		
		function addItemDetail(show) {
			if (show){
				$("#addTr").show();
			} else {
				$("#addTr").hide();
			}
		}
		
		function deleteItem(itemId) {
			bootbox.confirm("Are you sure?", function(result) {
				if (result) {
					location.replace("${contextPath}/setMeal/deleteById?id="+itemId);	
		    }               
	    });
		}
		
		function setSubCategory(subCateId,mainCategoryId,subCategoryId) {
			flag = "";
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
