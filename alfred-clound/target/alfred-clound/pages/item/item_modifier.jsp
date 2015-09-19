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
			<!-- page start-->

			<div class="row">
				<div class="col-sm-12">
					<section class="panel">
						<header class="panel-heading" style="height:67px">
							<div style="text-transform: capitalize;"> Item Modifier 
              <div class="btn-group pull-right">
              	<button id="editable-sample_new" class="btn btn-primary" onclick="location.replace('${contextPath}/itemModifier/querySubCateModifier')"><i class="fa"></i>  Go Back</button>
              </div>
             	</div>
						</header>
						<div class="panel-body">

							<table class="table table-striped table-hover table-bordered" id="im-table">
								<thead>
									<tr>
										<th>Item Name</th>
										<th>Item Description</th>
										<th>Item Modifier</th>
										<th>Edit</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="itemDetail" items="${itemDetailList}" varStatus="i">
										<tr class="">
											<td>${itemDetail.itemName}</td>
											<td>${itemDetail.itemDesc}</td>
											<td>
												 <select multiple id="sel${i.index}" style="width:600px" class="populate" onchange="javascript:$('#save${i.index}').show()">
													 <c:forEach var="mc" items="${modifierCategoryList}">
													 	<c:set var="selected" value=""></c:set>
													 	<c:forEach var="md" items="${itemModifierList}">
													 		<c:if test="${mc.id eq md.modifierCategoryId and md.type eq 0 and itemDetail.id eq md.itemId}">
													 			<c:set var="selected" value="selected"></c:set>
													 		</c:if>
													 	</c:forEach>	
													 	 <option value="${mc.id}" ${selected}>${mc.id}--${mc.categoryName}</option>
													 </c:forEach>
												 </select>
											</td>
											<td>
												<div id="save${i.index}" style="display:none"><a class="edit" href="javascript:;" onclick="saveItemModifier(${itemDetail.id},${itemDetail.itemCategoryId},${i.index})">Save</a></div>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</section>
				</div>
			</div>
			<!-- page end-->
		</section>
	</section>

	<script src="${staticPath}/js/item-modifier-table.js"></script>
	<script type="text/javascript" src="${staticPath}/js/data-tables/jquery.dataTables.js"></script>
	<script type="text/javascript"src="${staticPath}/js/data-tables/DT_bootstrap.js"></script>
	<script src="${staticPath}/js/bootbox.js"></script>
	<script type="text/javascript" src="${staticPath}/js/jquery-multi-select/js/jquery.multi-select.js"></script>
	<script type="text/javascript" src="${staticPath}/js/jquery-multi-select/js/jquery.quicksearch.js"></script>
	<script type="text/javascript" src="${staticPath}/js/select2/select2.js"></script>
	<script src="${staticPath}/js/scripts.js"></script>
	
	<script>
		jQuery(document).ready(function() {
			$(".table-bordered").find("select").select2();
			
// 			EditableTable.init("cm-table");
			EditableTable.init("im-table");
			
			$(".active").click(function(){
				$(".table-bordered").find("select").select2();
      });
			$(".next").click(function(){
				$(".table-bordered").find("select").select2();
      });
      $(".prev").click(function(){
    	  $(".table-bordered").find("select").select2();
      });
			
// 			$("#cm-table_filter").hide();
// 			$("#cm-table_length").hide();
			$("#im-table_filter").hide();
			$("#im-table_length").hide();
			
		});
		
		//左边菜单展开
		$("#itemManage").addClass("active");
		$("#itemCategory").css('display', 'block');
		$("#modifierLi").addClass("active");
		
		function saveItemModifier(itemId,itemCategoryId,selId) {
			$.ajax({
				type: "get",
				url: "/itemModifier/insert",
				cache:false,
				traditional:true,
				data:{itemId:itemId,itemCategoryId:itemCategoryId,modifierIds:$("#sel"+selId).val()},
				dataType: "json",
				success: function(data){ 
					if (data>0) {
						//alert("save the successfully!");
						$("#save"+selId).hide();
					} else {
						alert("save the failure!");
					}
				} 
			});
		}
		
	</script>
	<!-- END JAVASCRIPTS -->
</body>
</html>
