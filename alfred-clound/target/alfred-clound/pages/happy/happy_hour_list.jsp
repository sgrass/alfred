<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/pages/inc/common.jsp"%>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="ThemeBucket">
<title>item_category</title>
<link href="${staticPath}/bs3/css/bootstrap.min.css" rel="stylesheet">
<link href="${staticPath}/css/bootstrap-reset.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${staticPath}/css/style.css" rel="stylesheet">
<link href="${staticPath}/css/style-responsive.css" rel="stylesheet" />
<link href="${staticPath}/css/timeticker.css" rel="stylesheet" />

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
			<div class="btn-group">
	      <button id="editable-sample_new" class="btn btn-primary" onclick="$('#newPanel').show();">
	          Add New <i class="fa fa-plus"></i>
	      </button>
      </div>
			<div class="row">
					<section class="panel" id="newPanel" style="display:none">
						<header class="panel-heading">new happy hour
							<span class="tools pull-right"> 
								<button type="button" class="btn btn-default btn-xs" onclick="$('#newPanel').hide();">cancel</button>
								<a href="javascript:;" class="fa fa-chevron-down"></a> 
							</span>
						</header>
						<div class="panel-body">
							<form id="newHForm" action="${contextPath}/happyHour/insert" method="post">
							<table class="table">
								<tr>
									<td align="right" colspan="2">Name:</td>
									<td><input type="text" class="form-control" name="happyHourName" required/></td>
									<td align="right">Status:</td>
									<td>
										<select class="form-control" name="isActive">
											<option value="1">normal</option>
											<option value="0">disable</option>
										</select>
									</td>
								</tr>
								<c:forEach var="week" items="${weeks}" varStatus="i">
								<tr>
									<td>
										<input type="hidden" name="${i.index}_week" value="${i.index}">
										<input type="checkbox" name="${i.index}_isActive" value="1">
									</td>
									<td>${week}</td>
									<td>
										<div class="input-group bootstrap-timepicker">
		                 <input type="text" name="${i.index}_startTime"  value="00:00" class="form-control timepicker-from">
			               <span class="input-group-btn">
			 	                <button class="btn btn-default" type="button">
			 	                  <i class="fa fa-clock-o"></i>
			 	                </button>
			               </span>
	                  </div>
									</td>
									<td align="center">--</td>
									<td>
										<div class="input-group bootstrap-timepicker">
		                 <input type="text" name="${i.index}_endTime"  value="23:45" class="form-control timepicker-from">
			               <span class="input-group-btn">
			 	                <button class="btn btn-default" type="button">
			 	                  <i class="fa fa-clock-o"></i>
			 	                </button>
			               </span>
	                  </div>
									</td>
								</tr>
								</c:forEach>
								<tr>
									<td align="center" colspan="5">
										<button type="button" class="btn btn-default btn-sm" onclick="saveHappyHours();">Save</button>
									</td>
								</tr>
							</table>
							</form>
						</div>
					</section>
					
					<c:forEach var="hpppyHour" items="${happyHourList}" varStatus="i">
					<section class="panel">
						<header class="panel-heading">
							${hpppyHour.happyHourName}
							<span class="tools pull-right"> 
								<button type="button" class="btn btn-default btn-xs" onclick="javascript:$('#newTr${i.index}').show()">add new</button>
								<button type="button" class="btn btn-default btn-xs" onclick="editHappyHour(${i.index},this)">edit</button>
								<a href="javascript:;" class="fa fa-chevron-down"></a> 
							</span>
							<span class="tools pull-right" style="display:none"> 
								<button type="button" class="btn btn-default btn-xs" onclick="cancelHappyHour(${i.index},this)">cancel</button>
								<a href="javascript:;" class="fa fa-chevron-down"></a> 
							</span>
						</header>
						<div class="panel-body">
						
							<form id="hForm${i.index}" action="${contextPath}/happyHour/updateById" method="post">
							<input type="hidden" name="id" value="${hpppyHour.id}">
							<table class="table" id="mTab${i.index}" style="display:none">
								<tr>
									<td align="right" colspan="2">Name:</td>
									<td><input type="text" class="form-control" name="happyHourName" value="${hpppyHour.happyHourName}" required/></td>
									<td align="right">Status:</td>
									<td>
										<select class="form-control" name="isActive">
											<option value="1" <c:if test="${hpppyHour.isActive eq 1}">selected</c:if>>normal</option>
											<option value="0" <c:if test="${hpppyHour.isActive eq 0}">selected</c:if>>disable</option>
										</select>
									</td>
								</tr>
								<c:forEach var="happyWeek" items="${hpppyHour.happyWeekList}" varStatus="n">
								<tr>
									<td>
										<input type="hidden" name="${n.index}_week" value="${n.index}">
										<input type="checkbox" name="${n.index}_isActive" value="1" <c:if test="${happyWeek.isActive eq 1}">checked</c:if>>
									</td>
									<td>${happyWeek.week}</td>
									<td>
										<div class="input-group bootstrap-timepicker">
											<fmt:formatDate var="startTime" value="${happyWeek.startTime}" pattern="HH:mm"/>
		                	<input type="text" name="${n.index}_startTime"  value="${startTime}" class="form-control timepicker-from">
			                <span class="input-group-btn">
			 	                <button class="btn btn-default" type="button">
			 	                  <i class="fa fa-clock-o"></i>
			 	                </button>
			                </span>
	                  </div>
									</td>
									<td align="center">--</td>
									<td>
										<div class="input-group bootstrap-timepicker">
										<fmt:formatDate var="endTime" value="${happyWeek.endTime}" pattern="HH:mm"/>
		                 <input type="text" name="${n.index}_endTime"  value="${endTime}" class="form-control timepicker-from">
			               <span class="input-group-btn">
			 	                <button class="btn btn-default" type="button">
			 	                  <i class="fa fa-clock-o"></i>
			 	                </button>
			               </span>
	                  </div>
									</td>
								</tr>
								</c:forEach>
								<tr>
									<td align="right">
										<button type="button" class="btn btn-default btn-sm" onclick="updateHappyHour(${i.index},'update')">Update</button>
									</td>
									<td align="left">
										<button type="button" class="btn btn-default btn-sm" onclick="updateHappyHour(${i.index},'delete')">Delete</button>
									</td>
								</tr>
							</table>
							</form>
							
							<table class="table" id="tab${i.index}">
								<thead>
									<tr>
										<th>Name</th>
										<th>DiscountPrice</th>
										<th>DiscountRate</th>
										<th>FreeItem</th>
										<th>FreeNum</th>
										<th>Edit</th>
										<th>Delete</th>
									</tr>
								</thead>
								<tbody>
									<form id="addhwForm${i.index}" action="${contextPath}/itemHappy/insert" method="post">
									<input type="hidden" name="happyHourId" value="${hpppyHour.id}">
									<tr id="newTr${i.index}" style="display: none">
										<td>
											<select class="form-control" name="itemMainCategoryId" onchange="setSubCategory('addSubCate${i.index}','addItem${i.index}',this.options[this.selectedIndex].value)">
												<c:forEach var="mainCate" items="${mainCateMap}">
													<option value="${mainCate.value.id}">${mainCate.value.mainCategoryName}</option>
												</c:forEach>
											</select>
											<select class="form-control" name="itemCategoryId" id="addSubCate${i.index}" onchange="setItem('addItem${i.index}',this.options[this.selectedIndex].value)">
												<option value="">please select</option>
											</select>
											<select class="form-control" name="itemId" id="addItem${i.index}">
												<option value="">please select</option>
											</select>
											<input type="hidden" name="itemMainCategoryName">
											<input type="hidden" name="itemCategoryName">
											<input type="hidden" name="itemName">
											<input type="hidden" name="freeItemName">
										</td>
										<td><input type="text" class="form-control small" name="discountPrice" value="${ih.discountPrice}"></td>
										<td><input type="text" class="form-control small" name="discountRate" value="${ih.discountRate}"></td>
										<td>
											<select class="form-control" style="width: 300px" id="source" name="freeItemId">
												<c:forEach var="map" items="${itemMap}">
													<optgroup label="${map.value[0].itemMainCategoryName}/${map.value[0].itemCategoryName}">
														<c:forEach var="item" items="${map.value}">
												 			<option value="${item.id}" <c:if test="${item.id==ih.freeItemId}">selected</c:if>>${item.itemName}</option>
												 		</c:forEach>
													</optgroup>
												</c:forEach>
											</select>
										</td>
										<td><input type="text" class="form-control small" name="freeNum" value="${ih.freeNum}"></td>
										<td><a class="edit" href="javascript:;" onclick="formSubmit('addhwForm${i.index}','newTr${i.index}')">Save</a></td>
										<td><a class="delete" href="javascript:;" onclick="javascript:$('#newTr${i.index}').hide();">Cancel</a></td>
									</tr>
									</form>
									
									<c:forEach var="ih" items="${hpppyHour.itemHappyList}" varStatus="j">
									<form id="m${i.index}${j.index}" action="${contextPath}/itemHappy/updateById" method="post">
									<input type="hidden" name="id" value="${ih.id}">
									<tr>
										<td>
											${ih.itemMainCategoryName}
											<c:if test="${!empty ih.itemCategoryName}">
												/${ih.itemCategoryName}
											</c:if>
											<c:if test="${!empty ih.itemName}">
												/${ih.itemName}
											</c:if>
										</td>
										<td>${ih.discountPrice}</td>
										<td>${ih.discountRate}</td>
										<td>${ih.freeItemName}</td>
										<td>${ih.freeNum}</td>
										<td><a class="edit" href="javascript:;" onclick="editItemHappy(this,${ih.itemCategoryId},${ih.itemId})">Edit</a></td>
										<td><a class="delete" href="javascript:;" onclick="deleteItemHappy(${ih.id})">Delete</a></td>
									</tr>
									
									<tr id="tr${i.index}${j.index}" style="display: none">
										<td>
											<select class="form-control" name="itemMainCategoryId" onchange="setSubCategory('subCate${i.index}${j.index}','item${i.index}${j.index}',this.options[this.selectedIndex].value,${ih.itemCategoryId})">
												<c:forEach var="mainCate" items="${mainCateMap}">
													<option value="${mainCate.value.id}" <c:if test="${mainCate.value.id==ih.itemMainCategoryId}">selected</c:if>>${mainCate.value.mainCategoryName}</option>
												</c:forEach>
											</select>
											<select class="form-control" name="itemCategoryId" id="subCate${i.index}${j.index}" onchange="setItem('item${i.index}${j.index}',this.options[this.selectedIndex].value,${ih.itemId})">
												<option value="">please select</option>
												<option value="${ih.itemCategoryId}" selected>${ih.itemCategoryName}</option>
											</select>
											<select class="form-control" name="itemId" id="item${i.index}${j.index}">
												<option value="">please select</option>
											</select>
											<input type="hidden" name="itemMainCategoryName">
											<input type="hidden" name="itemCategoryName">
											<input type="hidden" name="itemName">
											<input type="hidden" name="freeItemName">
										</td>
										<td><input type="text" class="form-control small" name="discountPrice" value="${ih.discountPrice}"></td>
										<td><input type="text" class="form-control small" name="discountRate" value="${ih.discountRate}"></td>
										<td>
											<select class="form-control" style="width: 300px" id="source" name="freeItemId">
												<c:forEach var="map" items="${itemMap}">
													<optgroup label="${map.value[0].itemMainCategoryName}/${map.value[0].itemCategoryName}">
														<c:forEach var="item" items="${map.value}">
												 			<option value="${item.id}" <c:if test="${item.id==ih.freeItemId}">selected</c:if>>${item.itemName}</option>
												 		</c:forEach>
													</optgroup>
												</c:forEach>
											</select>
										</td>
										<td><input type="text" class="form-control small" name="freeNum" value="${ih.freeNum}"></td>
										<td><a class="edit" href="javascript:;" onclick="formSubmit('m${i.index}${j.index}','tr${i.index}${j.index}')">Save</a></td>
										<td><a class="delete" href="javascript:;" onclick="cancelItemHappy(this)">Cancel</a></td>
									</tr>
									</form>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</section>
					</c:forEach>
			</div>
			<!-- page end-->
		</section>
	</section>
	
	<!--main content end-->

	<script src="${staticPath}/js/scripts.js"></script>
	<script src="${staticPath}/js/bootbox.js"></script>
	<script type="text/javascript"src="${staticPath}/js/bootstrap-timepicker/js/bootstrap-timepicker.js"></script>
	<script type="text/javascript" src="${staticPath}/js/jquery.validate.min.js"></script>
	<script src="${staticPath}/js/happy-hour.js"></script>
	<script>
		jQuery(document).ready(function() {
			HappyHourPanel.init();     
	    	$('.newhappyhour').bind('click', function(evt){
	          HappyHourPanel.newpanel('#panel_container', '#happyhour_tpl');
	    });
	        //EditableTable.init();

		});
	  //左边菜单展开
		$("#itemManage").addClass("active");
		$("#itemCategory").css('display', 'block');
		$("#modifierCategoryLi").addClass("active");
			
		
		function saveHappyHours() {
			var validator=$("#newHForm").validate();
		  if(validator.form()){
				$("#newHForm").submit();
		  }
		}
		function  deleteItemHappy(id){
      bootbox.confirm("Is Delete Item Discount ?", function(result) {
        if (result) {
           location.replace('/itemHappy/deleteById?id='+id);
        }              
    	}); 
		}
	   
		function editHappyHour(tabId,obj) {
			$("#mTab"+tabId).show();
			$("#tab"+tabId).hide();
			$(obj).parent().hide();
			$(obj).parent().next().show();
    }
		
		function cancelHappyHour(tabId,obj) {
			$("#mTab"+tabId).hide();
			$("#tab"+tabId).show();
			$(obj).parent().hide();
			$(obj).parent().prev().show();
		}
		
		function updateHappyHour(formId,status) {
			if (status == "update") {
				$("#hForm"+formId).attr('action','${contextPath}/happyHour/updateById');
			} else {
				$("#hForm"+formId).attr('action','${contextPath}/happyHour/deleteById');
			}
			
			var validator=$("#hForm"+formId).validate();
      var check = validator.form();
   		if(check){
				$("#hForm"+formId).submit();
   		}
		}
	    
    var preTrObj = "";
    function editItemHappy(obj,itemCategoryId,itemId) {
    	if (!preTrObj == "") {
    		$(preTrObj).parent().parent().show();
	    	$(preTrObj).parent().parent().next().hide();
    	}
    	preTrObj=obj;
    	$(obj).parent().parent().hide();
    	$(obj).parent().parent().next().show();
    	
    	//$("select[name='itemMainCategoryId']").trigger("change");
    	$(obj).parent().parent().next().find("select[name='itemMainCategoryId']").trigger("change");
    	$(obj).parent().parent().next().find("select[name='itemCategoryId']").trigger("change");
    	
//     	alert($(obj).parent().parent().next().find("select[name='itemCategoryId']").val(4));
    	
//     	if (itemCategoryId != null && itemCategoryId != "") {
//     		$(obj).parent().parent().next().find("select[name='itemCategoryId']").val(itemCategoryId);
//     	}
    	
//     	$(obj).parent().parent().next().find("select[name='itemId']").trigger("change");
// 			if (itemId != null && itemId != "") {
// 				$(obj).parent().parent().next().find("select[name='itemId']").val(itemId);
//     	}
    }
    
    function cancelItemHappy(obj) {
    	preTrObj="";
    	$(obj).parent().parent().hide();
    	$(obj).parent().parent().prev().show();
    }
    
    function removeInsert(obj) {
    	$(obj).parent().parent().remove();
    }
	  
    function setSubCategory(subCateId, itemId, mainCategoryId,defItemCategoryId) {
			var optionHtml = "<option value=''>please select</option>";
			flag = "";
			if (mainCategoryId== null || mainCategoryId=='') {
				$("#"+subCateId).html(optionHtml);
				return ;
			}
			$("#"+itemId).html(optionHtml);
			$.ajax({
				type: "POST",
				url: "/category/queryByMainCategoryId?mainCategoryId="+mainCategoryId,
				dataType: 'json',
				success: function(data){ 
					$.each(data, function(i,item){
						if (defItemCategoryId == item.id) {
							flag = "selected";
						}
						optionHtml+="<option value='"+item.id+"' "+flag+">"+item.itemCategoryName+"</option>";
						flag = "";
					});
					$("#"+subCateId).html(optionHtml);
				} 
			}); 
		}
    
    function setItem(itemId,categoryId,defItemId) {
			var optionHtml = "<option value=''>please select</option>";
			flag = "";
			if (categoryId== null || categoryId=='') {
				$("#"+itemId).html(optionHtml);
				return ;
			}
			$.ajax({
				type: "POST",
				url: "/item/queryByCategoryId?categoryId="+categoryId,
				dataType: 'json',
				success: function(data){ 
					$.each(data, function(i,item){
						if (defItemId == item.id) {
							flag = "selected";
						}
						optionHtml+="<option value='"+item.id+"' "+flag+">"+item.itemName+"</option>";
						flag = "";
					});
					$("#"+itemId).html(optionHtml);
				} 
			}); 
		}
    
    function formSubmit(formId,newTrId) {
//     	var itemMainCategoryId = $("#"+formId).find("select[name=itemMainCategoryId]").val();
//     	alert($("#"+formId+">select[name=itemMainCategoryId]").val());
// 			$("#"+formId).serialize()
    	
    	itemMainCategoryName = $("#"+newTrId).find("select[name=itemMainCategoryId] option:selected").text();
    	itemCategoryName = $("#"+newTrId).find("select[name=itemCategoryId] option:selected").text();
    	itemName = $("#"+newTrId).find("select[name=itemId] option:selected").text();
    	freeItemName = $("#"+newTrId).find("select[name=freeItemId] option:selected").text();
    	
    	$("#"+newTrId).find("input[name=itemMainCategoryName]").val(itemMainCategoryName);
    	$("#"+newTrId).find("input[name=itemCategoryName]").val(itemCategoryName);
    	$("#"+newTrId).find("input[name=itemName]").val(itemName);
    	$("#"+newTrId).find("input[name=freeItemName]").val(freeItemName);
    	
    	$("#"+formId).submit();
    }
    
	</script>
</body>