<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/pages/inc/common.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="ThemeBucket">
    <title>RT Printer</title>
    <link href="${staticPath}/js/advanced-datatable/css/demo_page.css" rel="stylesheet"/>
    <link href="${staticPath}/js/advanced-datatable/css/demo_table.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${staticPath}/js/data-tables/DT_bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${staticPath}/js/select2/select2.css" />
</head>
<body>
	<!--main content start-->
<section id="main-content">
	    <div style="display:none">
	    <form  id="supplier_form" action="${contextPath}/supplier/insert" method="post">
	    <input id="id"  name="id" type="hidden" value=""/>
	    <input id="supplierName" name="supplierName" type="hidden" value=""/>
	    <input id="supplierAddress" name="supplierAddress" type="hidden" value=""/>
	    <input id="contact" name="contact" type="hidden" value=""/>
	    <input id="email" name="email" type="hidden" value=""/>
	    </form>
	    </div>
        <section class="wrapper">
        <!-- page start-->
        <div class="row">
            <div class="col-sm-12">
                 <section class="panel">
                    <header class="panel-heading" style="height:67px"> 
                      <div style="text-transform: capitalize;"> InventorySupplier
                         <div class="btn-group pull-right">
                                     <button id="editable-sample_new" class="btn btn-primary">
                                     <i class="fa fa-plus"></i>  InventorySupplier 
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
                                    <th>SupplierName</th>
                                    <th>SupplierAddress</th>
                                    <th>Contact</th>
                                    <th>Email</th>
                                    <th>Edit</th>
                                    <th>Delete</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${supplierList}" var="supplier"> 
								<tr class="gradeX"> 
								<td  class="center">${supplier.id}</td> 
								<td  class="center">${supplier.supplierName}</td> 
								<td  class="center">${supplier.supplierAddress}</td> 
								<td  class="center">${supplier.contact}</td> 
								<td  class="center">${supplier.email}</td> 
								<td><a data="${supplier.id}" class="edit" href="javascript:;">Edit</a></td>
                                <td><a data="${supplier.id}" class="delete" href="javascript:;">Delete</a></td>
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
<script src="${staticPath}/js/supplier.js"></script>
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
        $("#supplier").addClass("active");
        $("#editable-sample_filter").hide();
		$("#editable-sample_length").hide();
        
    });
</script>
</body>
</html>
