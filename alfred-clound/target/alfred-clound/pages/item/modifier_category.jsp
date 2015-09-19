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
				<div class="col-sm-6">
					<c:if test="${empty categoryList}">
					<section class="panel" id="newPanel">
					</c:if>
					<c:if test="${!empty categoryList}">
					<section class="panel" id="newPanel" style="display:none">
					</c:if>
						<header class="panel-heading">new category
							<span class="tools pull-right"> 
								<button type="button" class="btn btn-default btn-xs" onclick="$('#newPanel').hide();">cancel</button>
								<a href="javascript:;" class="fa fa-chevron-down"></a> 
							</span>
						</header>
						<div class="panel-body">
							<form id="newModForm" action="${contextPath}/modifier/insert" method="post">
							<input type="hidden" name="status" value="category">
							<table class="table">
								<tr>
									<td align="right">Modifier Category Name:</td>
									<td><input type="text" class="form-control small" id="categoryName" name="categoryName"/></td>
								</tr>
								<tr>
									<td align="right">Modifier Category Template:</td>
									<td>
										<select class="form-control small" name="templateCateId">
											<option value="">please select</option>
											<c:forEach items="${templateModifierList}" var="tm">
												<option value="${tm.id}">${tm.categoryName}</option>
											</c:forEach>
										</select>
									</td>
								</tr>
								<tr>
									<td align="right">Status:</td>
									<td>
										<select class="form-control small" name="isActive">
											<option value="1">normal</option>
											<option value="0">disable</option>
										</select>
									</td>
								</tr>
								<tr>
									<td align="center" colspan="2">
										<button type="button" class="btn btn-default btn-sm" onclick="saveNewModifier()">Save</button>
									</td>
								</tr>
							</table>
							</form>
						</div>
					</section>
					
					<c:forEach var="mv" items="${categoryList}" begin="0" end="${fn:length(categoryList)}" step="2" varStatus="i">
					<section class="panel">
						<header class="panel-heading">
							${mv.categoryName}
							<span class="tools pull-right"> 
								<button type="button" class="btn btn-default btn-xs" onclick="addNewTr(${i.index},${mv.id})">add new</button>
								<button type="button" class="btn btn-default btn-xs" onclick="editModifierCategory(${i.index},this)">edit</button>
								<a href="javascript:;" class="fa fa-chevron-down"></a> 
							</span>
							<span class="tools pull-right" style="display:none"> 
								<button type="button" class="btn btn-default btn-xs" onclick="cancelModifierCategory(${i.index},this)">cancel</button>
								<a href="javascript:;" class="fa fa-chevron-down"></a> 
							</span>
						</header>
						<div class="panel-body">
						
							<form id="mForm${i.index}" method="post">
							<input type="hidden" name="id" value="${mv.id}">
							<input type="hidden" name="status" value="category">
							<table class="table" id="mTab${i.index}" style="display:none">
								<tr>
									<td align="right">Modifier category Name:</td>
									<td><input type="text" class="form-control small" name="categoryName" value="${mv.categoryName}"/></td>
								</tr>
								<tr>
									<td align="right">Status:</td>
									<td>
										<select class="form-control small" name="isActive">
											<option value="1" <c:if test="${mv.isActive == 1}">selected</c:if>>normal</option>
											<option value="0" <c:if test="${mv.isActive == 0}">selected</c:if>>disable</option>
										</select>
									</td>
								</tr>
								<tr>
									<td align="right">
										<button type="button" class="btn btn-default btn-sm" onclick="updateModifierCategory(${i.index},'update')">Update</button>
									</td>
									<td align="left">
										<button type="button" class="btn btn-default btn-sm" onclick="updateModifierCategory(${i.index},'delete')">Delete</button>
									</td>
								</tr>
							</table>
							</form>
							
							<table class="table" id="tab${i.index}">
								<thead>
									<tr>
										<th>Modifier Name</th>
										<th>Price</th>
										<th>Active</th>
										<th>Default</th>
										<th>edit</th>
										<th>delete</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="m" items="${mv.modifierList}" varStatus="j">
									<c:if test="${m.isActive ne -1}">
									<form id="m${i.index}${j.index}" action="${contextPath}/modifier/updateById" method="post">
									<input type="hidden" name="id" value="${m.id}">
									<input type="hidden" name="categoryId" value="${m.categoryId}">
									<tr>
										<td>${m.modifierName}</td>
										<td>${m.price}</td>
										<td>
											<c:if test="${m.isActive == 1}">normal</c:if>
											<c:if test="${m.isActive == 0}">disable</c:if>
										</td>
										<td>
											<c:if test="${m.isDefault == 1}">YES</c:if>
											<c:if test="${m.isDefault == 0}">NO</c:if>
										</td>
										<td><a class="edit" href="javascript:;" onclick="editItemCategory(this)">Edit</a></td>
										<td><a class="delete" href="javascript:;" onclick="deleteModifierItemCategory(${m.id})">Delete</a></td>
									</tr>
									
									<tr style="display:none">
										<td>
											<input type="text" class="form-control small" name="modifierName" value="${m.modifierName}"/>
											<label name="icn" class="error" style="display:none">Please Enter Modifier Name.</label>
										</td>
										<td>
											<input type="text" class="form-control small" name="price" value="${m.price}"/>
											<label name="priceErr" class="error" style="display:none">decimal point is limited to 2 digits.</label>
										</td>
										<td>
											<select class="form-control small" name="isActive">
												<option value="1" <c:if test="${m.isActive == 1}">selected</c:if>>normal</option>
												<option value="0" <c:if test="${m.isActive == 0}">selected</c:if>>disable</option>
											</select>
										</td>
										<td><input type="checkBox" name="isDefault" value="1" <c:if test="${m.isDefault == 1}">checked</c:if>/></td>
										<td><a class="edit" href="javascript:;" onclick="updateModifier('#m${i.index}${j.index}',this)">Save</a></td>
										<td><a class="cancel" href="javascript:;" onclick="cancelItemCategory(this)">Cancel</a></td>
									</tr>
									</form>
									</c:if>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</section>
					</c:forEach>
				</div>
				
				
				<div class="col-sm-6">
					<c:forEach var="mv" items="${categoryList}" begin="1" end="${fn:length(categoryList)}" step="2" varStatus="i">
					<section class="panel">
						<header class="panel-heading">
							${mv.categoryName}
							<span class="tools pull-right"> 
								<button type="button" class="btn btn-default btn-xs" onclick="addNewTr(${i.index},${mv.id})">add new</button>
								<button type="button" class="btn btn-default btn-xs" onclick="editModifierCategory(${i.index},this)">edit</button>
								<a href="javascript:;" class="fa fa-chevron-down"></a> 
							</span>
							<span class="tools pull-right" style="display:none"> 
								<button type="button" class="btn btn-default btn-xs" onclick="cancelModifierCategory(${i.index},this)">cancel</button>
								<a href="javascript:;" class="fa fa-chevron-down"></a> 
							</span>
						</header>
						<div class="panel-body">
						
							<form id="mForm${i.index}" method="post">
							<input type="hidden" name="id" value="${mv.id}">
							<input type="hidden" name="status" value="category">
							<table class="table" id="mTab${i.index}" style="display:none">
								<tr>
									<td align="right">Modifier category Name:</td>
									<td><input type="text" class="form-control small" name="categoryName" value="${mv.categoryName}"/></td>
								</tr>
								<tr>
									<td align="right">Status:</td>
									<td>
										<select class="form-control small" name="isActive">
											<option value="1" <c:if test="${mv.isActive == 1}">selected</c:if>>normal</option>
											<option value="0" <c:if test="${mv.isActive == 0}">selected</c:if>>disable</option>
										</select>
									</td>
								</tr>
								<tr>
									<td align="right">
										<button type="button" class="btn btn-default btn-sm" onclick="updateModifierCategory(${i.index},'update')">Update</button>
									</td>
									<td align="left">
										<button type="button" class="btn btn-default btn-sm" onclick="updateModifierCategory(${i.index},'delete')">Delete</button>
									</td>
								</tr>
							</table>
							</form>
							
							<table class="table" id="tab${i.index}">
								<thead>
									<tr>
										<th>Modifier Name</th>
										<th>Price</th>
										<th>Active</th>
										<th>Default</th>
										<th>edit</th>
										<th>delete</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="m" items="${mv.modifierList}" varStatus="j">
									<c:if test="${m.isActive ne -1}">
									<form id="m${i.index}${j.index}" action="${contextPath}/modifier/updateById" method="post">
									<input type="hidden" name="id" value="${m.id}">
									<input type="hidden" name="categoryId" value="${m.categoryId}">
									<tr>
										<td>${m.modifierName}</td>
										<td>${m.price}</td>
										<td>
											<c:if test="${m.isActive == 1}">normal</c:if>
											<c:if test="${m.isActive == 0}">disable</c:if>
										</td>
										<td>
											<c:if test="${m.isDefault == 1}">YES</c:if>
											<c:if test="${m.isDefault == 0}">NO</c:if>
										</td>
										<td><a class="edit" href="javascript:;" onclick="editItemCategory(this)">Edit</a></td>
										<td><a class="delete" href="javascript:;"  onclick="deleteModifierItemCategory(${m.id})">Delete</a></td>
									</tr>
									
									<tr style="display:none">
										<td>
											<input type="text" class="form-control small" name="modifierName" value="${m.modifierName}"/>
											<label name="icn" class="error" style="display:none">Please Enter Modifier Name.</label>
										</td>
										<td>
											<input type="text" class="form-control small" name="price" value="${m.price}"/>
											<label name="priceErr" class="error" style="display:none">decimal point is limited to 2 digits.</label>
										</td>
										<td>
											<select class="form-control small" name="isActive">
												<option value="1" <c:if test="${m.isActive == 1}">selected</c:if>>normal</option>
												<option value="0" <c:if test="${m.isActive == 0}">selected</c:if>>disable</option>
											</select>
										</td>
										<td><input type="checkBox" name="isDefault" value="1" <c:if test="${m.isDefault == 1}">checked</c:if>/></td>
										<td><a class="edit" href="javascript:;" onclick="updateModifier('#m${i.index}${j.index}',this)">Save</a></td>
										<td><a class="cancel" href="javascript:;" onclick="cancelItemCategory(this)">Cancel</a></td>
									</tr>
									</form>
									</c:if>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</section>
					</c:forEach>
				</div>
			</div>
			
			<form id="modifierForm" action='${contextPath}/modifier/insert' method='post'>
				<input type="hidden" name="categoryId">
				<input type="hidden" name="modifierName">
				<input type="hidden" name="isActive">
				<input type="hidden" name="price">
			</form>
			<!-- page end-->
		</section>
	</section>
	
	<!--main content end-->

	<script type="text/javascript" src="${staticPath}/js/jquery.validate.min.js"></script>
  <script src="${staticPath}/js/scripts.js"></script>
  <script src="${staticPath}/js/bootbox.js"></script>
	<script>
		jQuery(document).ready(function() {
	        //EditableTable.init();

		});
		
	  //左边菜单展开
		$("#itemManage").addClass("active");
		$("#itemCategory").css('display', 'block');
		$("#modifierCategoryLi").addClass("active");
		
		
		function saveNewModifier() {
			var validator=$("#newModForm").validate({
			  rules : {
				  categoryName : {
					required: true,
					}
				},
				messages : {
					categoryName : "Please Enter Modifier Category Name",
				}
	    });
			  
			if(validator.form()){
				$("#newModForm").submit();
		  }
		}
		
		function  deleteModifierItemCategory(id){
	    bootbox.confirm("Is Delete Sub Category ?", function(result) {
	      if (result) {
	         location.replace('/modifier/deleteById?id='+id);
	      }           
    	}); 
		}
	   
		function editModifierCategory(tabId,obj) {
			$("#mTab"+tabId).show();
			$("#tab"+tabId).hide();
			$(obj).parent().hide();
			$(obj).parent().next().show();
    }
		
		function cancelModifierCategory(tabId,obj) {
			$("#mTab"+tabId).hide();
			$("#tab"+tabId).show();
			$(obj).parent().hide();
			$(obj).parent().prev().show();
		}
		
		function updateModifierCategory(formId,status) {
			if (status == "update") {
				var validator=$("#mForm"+formId).validate({
				  rules : {
					  categoryName : {
						required: true,
						}
					},
					messages : {
						categoryName : "Please Enter Modifier Category Name",
					}
		    });
				  
				if(validator.form()){
					$("#mForm"+formId).attr('action','${contextPath}/modifier/updateById');
					$("#mForm"+formId).submit();
			  }
			} else {
				$("#mForm"+formId).attr('action','${contextPath}/modifier/deleteById');
				$("#mForm"+formId).submit();
			}
		}
		
		function updateModifier(formId, obj) {
			modifierName = $(obj).parent().parent().find("input[name=modifierName]").val().trim();
			price = $(obj).parent().parent().find("input[name=price]").val().trim();
			if (modifierName == '') {
	  		$(obj).parent().parent().find("label[name=icn]").show();
	  		$(obj).parent().parent().find("input[name=modifierName]").focus();
	  		return false;
	  	}
			
			var reg = /^-?[0-9]+([.]{1}[0-9]{1,2})?$/;
    	if (price == '') {
     		$(obj).parent().parent().find("label[name=priceErr]").show();
     		$(obj).parent().parent().find("input[name=price]").focus();
     		return false;
     	} else if (!reg.test(price)){
     		$(obj).parent().parent().find("label[name=priceErr]").show();
     		$(obj).parent().parent().find("input[name=price]").focus();
     		return false;
     	}
			$(formId).submit();
		}
	    
    var preTrObj = "";
    function editItemCategory(obj) {
    	if (!preTrObj == "") {
    		$(preTrObj).parent().parent().show();
	    	$(preTrObj).parent().parent().next().hide();
    	}
    	preTrObj=obj;
    	$(obj).parent().parent().hide();
    	$(obj).parent().parent().next().show();
    }
    
    function cancelItemCategory(obj) {
    	preTrObj="";
    	$(obj).parent().parent().hide();
    	$(obj).parent().parent().prev().show();
    }
    
    function removeInsert(obj) {
    	$(obj).parent().parent().remove();
    }
	    
    function addNewTr(tabId, mcId) {
			tr = $('<tr></tr>');
			td1 = $("<td></td>").append('<input type="text" class="form-control small" name="modifierName" /><label name="icn" class="error" style="display:none">Please Enter Modifier Name.</label>'+
					'<input type="hidden" name="categoryId" value="'+mcId+'">');
			td2 = $("<td></td>").append('<input type="text" class="form-control small" name="price" /><label name="priceErr" class="error" style="display:none">decimal point is limited to 2 digits.</label>');
			td3 = $("<td></td>").append('<select class="form-control small" name="isActive">'+
					'<option value="1">normal</option>'+
					'<option value="0">disable</option>'+
					'</select>');
			td4 = $("<td></td>").append('<input type="checkbox"  name="isActive" />');
			td5 = $("<td></td>").append('<a class="edit" href="javascript:;" onclick="insertModifier(this)">Save</a>');
			td6 = $("<td></td>").append('<a class="cancel" href="javascript:;" onclick="removeInsert(this)">Cancel</a></td>');
			tr.append(td1);
			tr.append(td2);
			tr.append(td3);
			tr.append(td4);
			tr.append(td5);
			tr.append(td6);

			$("#tab"+tabId+">tbody").append(tr);
    }
	    
    function insertModifier(obj) {
    	categoryId = $(obj).parent().parent().find("input[name=categoryId]").val();
    	modifierName = $(obj).parent().parent().find("input[name=modifierName]").val().trim();
    	price = $(obj).parent().parent().find("input[name=price]").val();
    	isActive = $(obj).parent().parent().find("select[name=isActive]").val();
    	
    	$("#modifierForm>input[name=categoryId]").val(categoryId);
    	$("#modifierForm>input[name=modifierName]").val(modifierName);
    	$("#modifierForm>input[name=price]").val(price);
    	$("#modifierForm>input[name=isActive]").val(isActive);
    	
    	if (modifierName == '') {
     		$(obj).parent().parent().find("label[name=icn]").show();
     		$(obj).parent().parent().find("input[name=modifierName]").focus();
     		return false;
     	}
    	
    	var reg = /^-?[0-9]+([.]{1}[0-9]{1,2})?$/;
    	if (price == '') {
     		$(obj).parent().parent().find("label[name=priceErr]").show();
     		$(obj).parent().parent().find("input[name=price]").focus();
     		return false;
     	} else if (!reg.test(price)){
     		$(obj).parent().parent().find("label[name=priceErr]").show();
     		$(obj).parent().parent().find("input[name=price]").focus();
     		return false;
     	}
    	
    	$("#modifierForm").submit();
    } 
	</script>
</body>