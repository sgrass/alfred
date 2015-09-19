<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/pages/inc/common.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="ThemeBucket">
    <title>Material Stock</title>
    <link href="${staticPath}/js/advanced-datatable/css/demo_page.css" rel="stylesheet"/>
    <link href="${staticPath}/js/advanced-datatable/css/demo_table.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${staticPath}/js/data-tables/DT_bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${staticPath}/js/select2/select2.css" />
</head>
<body>
	<!--main content start-->
<section id="main-content">

	<div style="display: none" id="materialDiv">
		<c:forEach var="material" items="${materialList}">
			<option value="${material.id }">${material.rawMaterialName}</option>
		</c:forEach>
	</div>
	<div style="display: none" id="supplierDiv">
	
		<c:forEach var="supplier" items="${supplierList}">
			<option value="${supplier.id }">${supplier.supplierName}</option>
		</c:forEach>
	</div>
	
	<div style="display: none">
		<form id="material_stock_form"
			action="${contextPath}/materialStock/insert" method="post">
			<input id="id" name="id" type="hidden" value="" /> <input
				id="materialId" name="materialId" type="hidden" value="" /> <input
				id="supplierId" name="supplierId" type="hidden" value="" /> <input
				id="stockQty" name="stockQty" type="hidden" value="" /> <input
				id="stockTotalPrice" name="stockTotalPrice" type="hidden" value="" />
		</form>
	</div>
<section class="wrapper">
        <!-- page start-->
        <div class="row">
            <div class="col-sm-12">
                 <section class="panel">
                    <header class="panel-heading" style="height:67px"> 
                      <div style="text-transform: capitalize;"> Material Stock
                         <div class="btn-group pull-right">
                                     <button id="editable-sample_new" class="btn btn-primary">
                                     <i class="fa fa-plus"></i>  Material Stock 
                                    </button>
                                </div>
                        </div>        
                    </header>
                    <div class="panel-body">
                        <div class="adv-table editable-table ">
                            <div class="space15"></div>
                            <table class="table table-striped table-hover table-bordered" id="editable-sample">
                                <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>supplierName</th>
                                    <th>materialName</th>
                                    <th>stockQty</th>
                                    <th>stockTotalPrice</th>
                                    <th>supplierId</th>
                                    <th>materialId</th>
                                    <th>Edit</th>
                                    <th>Delete</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${inventoryMaterialStockList}" var="materialStock"> 
								<tr class="gradeX"> 
								<td  class="center">${materialStock.id}</td> 
								<td  class="center">${materialStock.supplierName}</td> 
								<td  class="center">${materialStock.materialName}</td> 
								<td  class="center">${materialStock.stockQty}</td> 
								<td  class="center">${materialStock.stockTotalPrice}</td> 
								<td  class="center">${materialStock.supplierId}</td> 
								<td  class="center">${materialStock.materialId}</td> 
								<td><a data="${materialStock.id}" class="edit" href="javascript:;">Edit</a></td>
                                <td><a data="${materialStock.id}" class="delete" href="javascript:;">Delete</a></td>
								</tr> 
                                </c:forEach> 
                                </tbody>
                            </table>
                        </div>
                    </div>
                </section>
            </div>
        </div>
        <!-- page end-->
        </section>
</section>
<!--main content end-->
<script type="text/javascript" src="${staticPath}/js/data-tables/jquery.dataTables.js"></script>
<script type="text/javascript" src="${staticPath}/js/data-tables/DT_update_bootstrap.js"></script>
<script src="${staticPath}/js/bootbox.js"></script>
<!--toggle initialization-->
<script src="${staticPath}/js/material_stock.js"></script>
<script type="text/javascript" src="${staticPath}/js/select2/select2.js"></script>
<script type="text/javascript" src="${staticPath}/js/select-init.js"></script>
<script type="text/javascript" src="${staticPath}/js/jquery.validate.min.js"></script>
<script src="${staticPath}/js/scripts.js"></script>
<script>
    jQuery(document).ready(function() {
    	//初始化表格
    	EditableTable.init();
    	//展开选中的菜单
        $("#inventory_menu").addClass("active");
        $("#inventory_sub").css('display','block');
        $("#material_stock").addClass("active");

        $("#editable-sample_filter").hide();
		$("#editable-sample_length").hide();
        
    });
</script>
</body>
</html>
