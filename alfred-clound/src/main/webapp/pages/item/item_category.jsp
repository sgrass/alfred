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
	          <spring:message code="product.main.category"></spring:message> <i class="fa fa-plus"></i>
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
						<header class="panel-heading"><spring:message code="product.sub.category"></spring:message>
							<span class="tools pull-right"> 
								<button type="button" class="btn btn-default btn-xs" onclick="$('#newPanel').hide();"><spring:message code="product.button.cancel"></spring:message></button>
								<a href="javascript:;" class="fa fa-chevron-down"></a> 
							</span>
						</header>
						<div class="panel-body">
							<form id="newMcForm" action="${contextPath}/mainCategory/insert" method="post">
							<table class="table">
								<tr>
									<td align="right"><spring:message code="product.category.name">:</spring:message></td>
									<td><input type="text" class="form-control small" name="mainCategoryName" id="mainCategoryName"/></td>
								</tr>
								<tr>
									<td align="right"><spring:message code="product.status"></spring:message>:</td>
									<td>
										<select class="form-control small" name="isActive">
											<option value="1" <c:if test="${imc.isActive == 1}">selected</c:if>>normal</option>
											<option value="0" <c:if test="${imc.isActive == 0}">selected</c:if>>disable</option>
										</select>
									</td>
								</tr>
								<tr>
									<td align="right"><spring:message code="product.printer"></spring:message>:</td>
									<td>
										<select class="form-control small" name="printerId">
											<option value="">please select</option>
											<c:forEach var="printer" items="${printerList}">
												<option value="${printer.id}">${printer.printerGroupName}</option>
											</c:forEach>
										</select>
									</td>
								</tr>
								<tr>
									<td align="center" colspan="2">
										<button type="button" class="btn btn-default btn-sm" onclick="$('#newMcForm').submit();"><spring:message code="product.button.save"></spring:message></button>
									</td>
								</tr>
							</table>
							</form>
						</div>
					</section>
					
					<c:forEach var="imc" items="${categoryList}" begin="0" end="${fn:length(categoryList)}" step="2" varStatus="i">
					<section class="panel">
						<header class="panel-heading">
							${imc.mainCategoryName} 
							<span class="tools pull-right"> 
<!-- 								<a href="javascript:;" class="fa fa-cog"></a>  -->
<!-- 								<a href="javascript:;" class="fa fa-times"></a> -->
								<button type="button" class="btn btn-default btn-xs" onclick="addNewTr(${i.index},${imc.id},${imc.printerId ne null?imc.printerId:'null'})"><spring:message code="product.button.addnew"></spring:message></button>
								<button type="button" class="btn btn-default btn-xs" onclick="editMainCategory(${i.index},this)"><spring:message code="product.button.edit"></spring:message></button>
								<a href="javascript:;" class="fa fa-chevron-down"></a> 
							</span>
							<span class="tools pull-right" style="display:none"> 
								<button type="button" class="btn btn-default btn-xs" onclick="cancelMainCategory(${i.index},this)"><spring:message code="product.button.cancel"></spring:message></button>
								<a href="javascript:;" class="fa fa-chevron-down"></a> 
							</span>
						</header>
						<div class="panel-body">
						
							<form id="mForm${i.index}" method="post">
							<input type="hidden" name="id" value="${imc.id}">
							<table class="table" id="mTab${i.index}" style="display:none">
								<tr>
									<td align="right"><spring:message code="product.main.category.name"></spring:message>:</td>
									<td><input type="text" class="form-control small" id="mainCategoryName" name="mainCategoryName" value="${imc.mainCategoryName}"/></td>
								</tr>
								<tr>
									<td align="right"><spring:message code="product.status"></spring:message>:</td>
									<td>
										<select class="form-control small" name="isActive">
											<option value="1" <c:if test="${imc.isActive == 1}">selected</c:if>>normal</option>
											<option value="0" <c:if test="${imc.isActive == 0}">selected</c:if>>disable</option>
										</select>
									</td>
								</tr>
								<tr>
									<td align="right"><spring:message code="product.printer"></spring:message>:</td>
									<td>
										<select class="form-control small" name="printerId">
											<option value="">please select</option>
											<c:forEach var="printer" items="${printerList}">
												<option value="${printer.id}" <c:if test="${printer.id == imc.printerId}">selected</c:if>>${printer.printerGroupName}</option>
											</c:forEach>
										</select>
									</td>
								</tr>
								<tr>
									<td align="right">
										<button type="button" class="btn btn-default btn-sm" onclick="updateMainCategory(${i.index},'update')"><spring:message code="product.button.update"></spring:message></button>
									</td>
									<td align="left">
										<button type="button" class="btn btn-default btn-sm" onclick="updateMainCategory(${i.index},'delete')"><spring:message code="product.button.delete"></spring:message></button>
									</td>
								</tr>
							</table>
							</form>
							
							<table class="table" id="tab${i.index}">
								<thead>
									<tr>
										<th><spring:message code="product.sub.category.name"></spring:message></th>
										<th><spring:message code="product.status"></spring:message></th>
										<th><spring:message code="product.printer"></spring:message></th>
										<th><spring:message code="product.button.edit"></spring:message></th>
										<th><spring:message code="product.button.delete"></spring:message></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="ic" items="${imc.itemCategoryList}" varStatus="j">
									<c:if test="${ic.isActive ne -1}">
									<form id="subCat${i.index}${j.index}" action="${contextPath}/category/updateById" method="post">
									<input type="hidden" name="id" value="${ic.id}">
									<tr>
										<td>${ic.itemCategoryName}</td>
										<td>
											<c:if test="${ic.isActive == 1}">normal</c:if>
											<c:if test="${ic.isActive == 0}">disable</c:if>
										</td>
										<td>
											<c:forEach var="printer" items="${printerList}">
												<c:if test="${printer.id == ic.printerId}">${printer.printerGroupName}</c:if>
											</c:forEach>
										</td>
										<td><a class="edit" href="javascript:;" onclick="editItemCategory(this)"><spring:message code="product.button.edit"></spring:message></a></td>
										<td><a class="delete" href="javascript:;" onclick="deleteItemCategory(${ic.id},${ic.restaurantId})"><spring:message code="product.button.delete"></spring:message></a></td>
									</tr>
									
									<tr style="display:none">
										<td>
											<input type="text" class="form-control small" name="itemCategoryName" value="${ic.itemCategoryName}"/>
											<label name="icn" class="error" style="display:none">Please Enter Sub Category Name.</label>
										</td>
										<td>
											<select class="form-control small" name="isActive">
												<option value="1" <c:if test="${ic.isActive == 1}">selected</c:if>>normal</option>
												<option value="0" <c:if test="${ic.isActive == 0}">selected</c:if>>disable</option>
											</select>
										</td>
										<td>
										<select class="form-control small" name="printerId">
											<option value="">please select</option>
											<c:forEach var="printer" items="${printerList}">
												<option value="${printer.id}" <c:if test="${printer.id == ic.printerId}">selected</c:if>>${printer.printerGroupName}</option>
											</c:forEach>
										</select>
										</td>
										<td><a class="edit" href="javascript:;" onclick="updateSubCategory('subCat${i.index}${j.index}',this)"><spring:message code="product.button.save"></spring:message></a></td>
										<td><a class="cancel" href="javascript:;" onclick="cancelItemCategory(this)"><spring:message code="product.button.cancel"></spring:message></a></td>
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
					<c:forEach var="imc" items="${categoryList}" begin="1" end="${fn:length(categoryList)}" step="2" varStatus="i">
					<section class="panel">
						<header class="panel-heading">
							${imc.mainCategoryName} 
							<span class="tools pull-right"> 
<!-- 								<a href="javascript:;" class="fa fa-cog"></a>  -->
<!-- 								<a href="javascript:;" class="fa fa-times"></a> -->
								<button type="button" class="btn btn-default btn-xs" onclick="addNewTr(${i.index},${imc.id},${imc.printerId ne null?imc.printerId:'null'})"><spring:message code="product.button.addnew"></spring:message></button>
								<button type="button" class="btn btn-default btn-xs" onclick="editMainCategory(${i.index},this)"><spring:message code="product.button.edit"></spring:message></button>
								<a href="javascript:;" class="fa fa-chevron-down"></a> 
							</span>
							<span class="tools pull-right" style="display:none"> 
								<button type="button" class="btn btn-default btn-xs" onclick="cancelMainCategory(${i.index},this)"><spring:message code="product.button.cancel"></spring:message></button>
								<a href="javascript:;" class="fa fa-chevron-down"></a> 
							</span>
						</header>
						<div class="panel-body">
							<form id="mForm${i.index}" method="post">
							<input type="hidden" name="id" value="${imc.id}">
							<table class="table" id="mTab${i.index}" style="display:none">
								<tr>
									<td align="right"><spring:message code="product.main.category.name"></spring:message>:</td>
									<td><input type="text" class="form-control small" id="mainCategoryName" name="mainCategoryName" value="${imc.mainCategoryName}"/></td>
								</tr>
								<tr>
									<td align="right"><spring:message code="product.status"></spring:message>:</td>
									<td>
										<select class="form-control small" name="isActive">
											<option value="1" <c:if test="${imc.isActive == 1}">selected</c:if>>normal</option>
											<option value="0" <c:if test="${imc.isActive == 0}">selected</c:if>>disable</option>
										</select>
									</td>
								</tr>
								<tr>
									<td align="right"><spring:message code="product.printer"></spring:message>:</td>
									<td>
										<select class="form-control small" name="printerId">
											<option value="">please select</option>
											<c:forEach var="printer" items="${printerList}">
												<option value="${printer.id}" <c:if test="${printer.id == imc.printerId}">selected</c:if>>${printer.printerGroupName}</option>
											</c:forEach>
										</select>
									</td>
								</tr>
								<tr>
									<td align="right">
										<button type="button" class="btn btn-default btn-sm" onclick="updateMainCategory(${i.index},'update')"><spring:message code="product.button.update"></spring:message></button>
									</td>
									<td align="left">
										<button type="button" class="btn btn-default btn-sm" onclick="updateMainCategory(${i.index},'delete')"><spring:message code="product.button.delete"></spring:message></button>
									</td>
								</tr>
							</table>
							</form>
							<table class="table" id="tab${i.index}">
								<thead>
									<tr>
										<th><spring:message code="product.sub.category.name"></spring:message></th>
										<th><spring:message code="product.status"></spring:message></th>
										<th><spring:message code="product.printer"></spring:message></th>
										<th><spring:message code="product.button.edit"></spring:message></th>
										<th><spring:message code="product.button.delete"></spring:message></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="ic" items="${imc.itemCategoryList}" varStatus="j">
									<c:if test="${ic.isActive ne -1}">
									<form id="subCat${i.index}${j.index}" action="${contextPath}/category/updateById" method="post">
									<input type="hidden" name="id" value="${ic.id}">
									<tr>
										<td>${ic.itemCategoryName}</td>
										<td>
											<c:if test="${ic.isActive == 1}">normal</c:if>
											<c:if test="${ic.isActive == 0}">disable</c:if>
										</td>
										<td>
											<c:forEach var="printer" items="${printerList}">
												<c:if test="${printer.id == ic.printerId}">${printer.printerGroupName}</c:if>
											</c:forEach>
										</td>
										<td><a class="edit" href="javascript:;" onclick="editItemCategory(this)"><spring:message code="product.button.edit"></spring:message></a></td>
										<td><a class="delete" href="javascript:;" onclick="deleteItemCategory(${ic.id},${ic.restaurantId})" ><spring:message code="product.button.delete"></spring:message></a></td>
									</tr>
									<tr style="display:none">
										<td>
											<input type="text" class="form-control small" name="itemCategoryName" value="${ic.itemCategoryName}"/>
											<label name="icn" class="error" style="display:none">Please Enter Sub Category Name.</label>
										</td>
										<td>
											<select class="form-control small" name="isActive">
												<option value="1" <c:if test="${ic.isActive == 1}">selected</c:if>>normal</option>
												<option value="0" <c:if test="${ic.isActive == 0}">selected</c:if>>disable</option>
											</select>
										</td>
										<td>
										<select class="form-control small" name="printerId">
											<option value="">please select</option>
											<c:forEach var="printer" items="${printerList}">
												<option value="${printer.id}" <c:if test="${printer.id == ic.printerId}">selected</c:if>>${printer.printerGroupName}</option>
											</c:forEach>
										</select>
										</td>
										<td><a class="edit" href="javascript:;" onclick="updateSubCategory('subCat${i.index}${j.index}',this)"><spring:message code="product.button.save"></spring:message></a></td>
										<td><a class="cancel" href="javascript:;" onclick="cancelItemCategory(this)"><spring:message code="product.button.cancel"></spring:message></a></td>
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
			
			<form id="itemCagegoryForm" action='${contextPath}/category/insert' method='post'>
				<input type="hidden" name="itemMainCategoryId">
				<input type="hidden" name="itemCategoryName">
				<input type="hidden" name="isActive">
				<input type="hidden" name="printerId">
			</form>
			<!-- page end-->
		</section>
	</section>
	<script src="${staticPath}/js/bootbox.js"></script>
	<!--main content end-->
	<script type="text/javascript" src="${staticPath}/js/jquery.validate.min.js"></script>
	<script src="${staticPath}/js/scripts.js"></script>
	<script src="${staticPath}/jquery.i18n.properties-min-1.0.9.js"></script>
	<script src="${staticPath}/jquery.cookie.js"></script>
	<script>
	$(document).ready(function(){
	  jQuery.i18n.properties({
	    name:'js',
	    path:'${staticPath}/i18n/',
	    mode:'map',
	    //language:"en-US",
	    callback: function() {
	      //alert(jQuery.i18n.prop("test"));
	    }
	  });
	});
	
	//左边菜单展开
	$("#itemManage").addClass("active");
	$("#itemCategory").css('display', 'block');
	$("#mainCategory").addClass("active");
	  
	
  jQuery(document).ready(function() {
      //EditableTable.init();
      
		$("#newMcForm").validate({
			rules : {
				mainCategoryName : {
				required: true,
				}
			},
			messages : {
				mainCategoryName : jQuery.i18n.prop("product.main_category.prompt.add"),
			}
		});
  });
  
	function  deleteItemCategory(id,restaurantId){
		bootbox.confirm(jQuery.i18n.prop("product.category.prompt.delete"), function(result) {
    	if (result) {
      	// What to do here?
        location.replace('/category/deleteById?id='+id+'&restaurantId='+restaurantId);
	    } else {
			// What to do here?
      }               
    }); 
	}
	    
	    
	function editMainCategory(tabId,obj) {
		$("#mTab"+tabId).show();
		$("#tab"+tabId).hide();
		$(obj).parent().hide();
		$(obj).parent().next().show();
   }
	
	function cancelMainCategory(tabId,obj) {
		$("#mTab"+tabId).hide();
		$("#tab"+tabId).show();
		$(obj).parent().hide();
		$(obj).parent().prev().show();
	}
	
	function updateMainCategory(formId,status) {
		if (status == "update") {
			var validator=$("#mForm"+formId).validate({
			  rules : {
				  mainCategoryName : {
					required: true,
					}
				},
				messages : {
					mainCategoryName : jQuery.i18n.prop("product.main_category.prompt.add"),
				}
	    });
			  
			var check = validator.form();
			if(check){
				$("#mForm"+formId).attr('action','${contextPath}/mainCategory/updateById');
				$("#mForm"+formId).submit();
		  }
		} else {
			$("#mForm"+formId).attr('action','${contextPath}/mainCategory/deleteById');
			$("#mForm"+formId).submit();
		}
	}
	
	function updateSubCategory(formId,obj) {
		itemCategoryName = $(obj).parent().parent().find("input[name=itemCategoryName]").val().trim();
		if (itemCategoryName == '') {
  		$(obj).parent().parent().find("label[name=icn]").show();
  		$(obj).parent().parent().find("input[name=itemCategoryName]").focus();
  		return false;
  	}
		$("#"+formId).submit();
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
	    
	function addNewTr(tabId, imcId, printerId) {
		//$("#tab"+tabId+">tbody").append("<tr>"+$("#trNew"+tabId).clone(true).html()+"</tr>");
		tr = $('<tr></tr>');
		td1 = $("<td></td>").append('<input type="text" class="form-control small" name="itemCategoryName" /><label name="icn" class="error" style="display:none">'+jQuery.i18n.prop("product.sub_category.prompt.add")+'</label><input type="hidden" name="itemMainCategoryId" value="'+imcId+'">');
		td2 = $("<td></td>").append('<select class="form-control small" name="isActive">'+
				'<option value="1">normal</option>'+
				'<option value="0">disable</option>'+
				'</select>');
		td3 = $("<td></td>").append('<select class="form-control small" name="printerId">'+
				'<option value="">please select</option>'+
				'<c:forEach var="printer" items="${printerList}">'+
					'<option value="${printer.id}">${printer.printerGroupName}</option>'+
				'</c:forEach>'+
				'</select>');
		td4 = $("<td></td>").append('<a class="edit" href="javascript:;" onclick="insertItemCategory(this)">'+$.i18n.prop("button.save")+'</a>');
		td5 = $("<td></td>").append('<a class="cancel" href="javascript:;" onclick="removeInsert(this)">'+$.i18n.prop("button.cancel")+'</a></td>');
		tr.append(td1);
		tr.append(td2);
		tr.append(td3);
		tr.append(td4);
		tr.append(td5);
		//tr.append(form);
		//$("#tab"+tabId+">tbody").append("<tr>"+$("#trNew"+tabId).clone(true).html()+"</tr>");

		$("#tab"+tabId+">tbody").append(tr);
		if (printerId != null)
			$("#tab"+tabId+">tbody").find("option[value='"+printerId+"']").attr("selected",true);
  }
	    
  function insertItemCategory(obj) {
	  $(obj).parent().parent().find("label[name=icn]").hide();
  	itemMainCategoryId = $(obj).parent().parent().find("input[name=itemMainCategoryId]").val();
  	itemCategoryName = $(obj).parent().parent().find("input[name=itemCategoryName]").val().trim();
  	isActive = $(obj).parent().parent().find("select[name=isActive]").val();
  	printerId = $(obj).parent().parent().find("select[name=printerId]").val();
  	
  	$("#itemCagegoryForm>input[name=itemMainCategoryId]").val(itemMainCategoryId);
  	$("#itemCagegoryForm>input[name=itemCategoryName]").val(itemCategoryName);
  	$("#itemCagegoryForm>input[name=isActive]").val(isActive);
  	$("#itemCagegoryForm>input[name=printerId]").val(printerId);
  	
  	
  	if (itemCategoryName == '') {
  		$(obj).parent().parent().find("label[name=icn]").show();
  		$(obj).parent().parent().find("input[name=itemCategoryName]").focus();
  		return false;
  	}
  	
  	$("#itemCagegoryForm").submit();
  } 
	</script>
</body>