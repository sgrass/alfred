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
<link rel="stylesheet" type="text/css" href="${staticPath}/js/jquery-multi-select/css/multi-select.css" />
<link rel="stylesheet" type="text/css" href="${staticPath}/js/jquery-tags-input/jquery.tagsinput.css" />
<link rel="stylesheet" type="text/css" href="${staticPath}/js/select2/select2.css" />

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
		</section>
			<div class="col-sm-6">
				<c:forEach var="imc" items="${categoryList}" begin="0" end="${fn:length(categoryList)}" step="2" varStatus="i">
					<section class="panel">
						<header class="panel-heading">
							${imc.mainCategoryName} 
							<span class="tools pull-right">
								<a href="javascript:;" class="fa fa-chevron-down"></a>
							</span>
						</header>
						<div class="panel-body">
							<table class="table" id="tab${i.index}">
								<thead>
									<tr>
										<th><spring:message code="product.sub.category.name"/></th>
										<th><spring:message code="modifier.name"/></th>
										<th><spring:message code="product.button.save"/></th>
										<th><spring:message code="product.button.edit"/></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="ic" items="${imc.itemCategoryList}" varStatus="j">
										<tr>
											<td>${ic.itemCategoryName}</td>
											<td>
												<select multiple id="sel${i.index}${j.index}" style="width:200px" class="populate" onchange="javascript:$('#save${i.index}${j.index}').show()">
												 <c:forEach var="mc" items="${modifierCategoryList}">
												 	<c:set var="selected" value=""></c:set>
														<c:forEach var="md" items="${itemModifierList}">
													 		<c:if test="${mc.id eq md.modifierCategoryId and md.type eq 1 and ic.id eq md.itemCategoryId}">
													 			<c:set var="selected" value="selected"></c:set>
													 		</c:if>
													 	</c:forEach>	
												 		<option value="${mc.id}" ${selected}>${mc.id}--${mc.categoryName}</option>
												 	</c:forEach>
											 </select>
											</td>
											<td>
												<div id="save${i.index}${j.index}" style="display:none"><a class="edit" href="javascript:;" onclick="saveCateModifier(${ic.id},'${i.index}${j.index}')"><spring:message code="product.button.save"/></a></div>
											</td>
											<td>
												<a class="edit" href="${contextPath}/itemModifier/queryItemModifier?itemMainCategoryId=${imc.id}&itemCategoryId=${ic.id}"><spring:message code="product.button.edit"/></a>
											</td>
										</tr>
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
								<a href="javascript:;" class="fa fa-chevron-down"></a>
							</span>
						</header>
						<div class="panel-body">
							<table class="table" id="tab${i.index}">
								<thead>
									<tr>
										<th><spring:message code="product.sub.category.name"/></th>
										<th><spring:message code="modifier.name"/></th>
										<th><spring:message code="product.button.save"/></th>
										<th><spring:message code="product.button.edit"/></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="ic" items="${imc.itemCategoryList}" varStatus="j">
										<input type="hidden" name="id" value="${ic.id}">
										<tr>
											<td>${ic.itemCategoryName}</td>
											<td>
												<select multiple id="sel${i.index}${j.index}" style="width:200px" class="populate" onchange="javascript:$('#save${i.index}${j.index}').show()">
												 <c:forEach var="mc" items="${modifierCategoryList}">
												 	<c:set var="selected" value=""></c:set>
														<c:forEach var="md" items="${itemModifierList}">
													 		<c:if test="${mc.id eq md.modifierCategoryId and md.type eq 1 and ic.id eq md.itemCategoryId}">
													 			<c:set var="selected" value="selected"></c:set>
													 		</c:if>
													 	</c:forEach>	
												 		<option value="${mc.id}" ${selected}>${mc.id}--${mc.categoryName}</option>
												 	</c:forEach>
											 </select>
											</td>
											<td>
												<div id="save${i.index}${j.index}" style="display:none"><a class="edit" href="javascript:;" onclick="saveCateModifier(${ic.id},'${i.index}${j.index}')"><spring:message code="product.button.save"/></a></div>
											</td>
											<td>
												<a class="edit" href="${contextPath}/itemModifier/queryItemModifier?itemMainCategoryId=${imc.id}&itemCategoryId=${ic.id}"><spring:message code="product.button.edit"/></a>
											</td>
										</tr>
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
	
	<script src="${staticPath}/js/bootbox.js"></script>
	<script type="text/javascript" src="${staticPath}/js/jquery-multi-select/js/jquery.multi-select.js"></script>
	<script type="text/javascript" src="${staticPath}/js/jquery-multi-select/js/jquery.quicksearch.js"></script>
	<script type="text/javascript" src="${staticPath}/js/select2/select2.js"></script>
	<script src="${staticPath}/js/scripts.js"></script>
	
	<script>
	
	//左边菜单展开
	$("#itemManage").addClass("active");
	$("#itemCategory").css('display', 'block');
	$("#modifierLi").addClass("active");
	  
	
  jQuery(document).ready(function() {
	  $(".table").find("select").select2();
  });
  
  function saveCateModifier(itemCategoryId,selId) {
		$.ajax({
			type: "get",
			url: "/itemModifier/insertCateModifier",
			cache:false,
			traditional:true,
			data:{itemCategoryId:itemCategoryId, modifierIds:$("#sel"+selId).val()},
			dataType: "json",
			success: function(data){ 
				if (data) {
					alert("save the successfully!");
					$("#save"+selId).hide();
				} else {
					alert("save the failure!");
				}
			} 
		});
	}
  
	</script>
</body>