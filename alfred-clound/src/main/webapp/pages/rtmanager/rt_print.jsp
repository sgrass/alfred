<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/pages/inc/common.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="ThemeBucket">
    <title>RT Printer</title>
    <!--dynamic table-->
      <link href="${staticPath}/js/advanced-datatable/css/demo_page.css" rel="stylesheet"/>
    <link href="${staticPath}/js/advanced-datatable/css/demo_table.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${staticPath}/js/data-tables/DT_bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${staticPath}/js/select2/select2.css" />
    <style>
    .classdisplay{
     display:none;
    }
    .select2-choices{
		border-radius: 4px;
		height: 34px;
		padding: 6px 12px;
		font-size: 14px;
		border: 1px solid #eee; 
		box-shadow: none;
		color: #c2c2c2
     }
    </style>
</head>
<body>
	<!--main content start-->
<section id="main-content">
        <div id="selectRes" >
          <c:forEach items="${printerList}" var="printer">
           <option value="${printer.id}">${printer.printerName}</option>
		  </c:forEach>
	    </div>
        <section class="wrapper">
        <!-- page start-->
        <div class="row">
            <div class="col-sm-12">
                 <section class="panel">
                    <header class="panel-heading" style="height:67px"> 
                      <div style="text-transform: capitalize;"><spring:message code="printer.title"></spring:message>
                         <div class="btn-group pull-right">
                                     <button id="editable-sample_new" class="btn btn-primary">
                                     <i class="fa fa-plus"></i> <spring:message code="printer.addBut"></spring:message> 
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
                                    <th><spring:message code="printer.addBut"></spring:message> </th>
                                    <th><spring:message code="printer.edit"></spring:message> </th>
                                    <th><spring:message code="printer.delete"></spring:message> </th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${printerList}" var="printer"> 
								<tr class="gradeX"> 
								<td  class="center">${printer.id}</td> 
								<td  class="center">${printer.printerName}</td> 
								<td><a data="${printer.id}" class="edit" href="javascript:;"><spring:message code="printer.edit"></spring:message></a></td>
                                <td><a data="${printer.id}" class="delete" href="javascript:;"><spring:message code="printer.delete"></spring:message></a></td>
								</tr> 
                                </c:forEach> 
                                </tbody>
                            </table>
                        </div>
                    </div>
                </section>
            </div>
        </div>
            <div class="row">
            <div class="col-sm-12">
                 <section class="panel">
                    <header class="panel-heading" style="height:67px"> 
                      <div style="text-transform: capitalize;">  <spring:message code="printer.group"></spring:message>
                         <div class="btn-group pull-right">
                                     <button id="editable-sample_new_order" class="btn btn-primary">
                                     <i class="fa fa-plus"></i> <spring:message code="printer.group"></spring:message>
                                    </button>
                                </div>
                        </div>        
                    </header>
                    <div class="panel-body">
                        <div class="adv-table editable-table " id="taxRuleTable">
                            <div class="space15"></div>
                            <table class="table table-striped table-hover table-bordered" id="printer_group_edit">
                                <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>Printer Id</th>
                                    <th><spring:message code="printer.groupName"></spring:message></th>
                                    <th><spring:message code="printer.addBut"></spring:message></th>
                                    <th><spring:message code="printer.edit"></spring:message></th>
                                    <th><spring:message code="printer.delete"></spring:message></th>
                                </tr>
                                </thead>
                                <tbody> 
                                <c:forEach items="${printerGroupVOList}" var="printerGroup"> 
								<tr class="gradeX"> 
								<td  class="center">${printerGroup.id}</td> 
								<td  class="center">
								<c:forEach items="${printerGroup.printerGroupList}" var="printer">
									    ${printer.printerId},
								</c:forEach>
								</td>
								<td  class="center">${printerGroup.printerGroupName}</td> 
								<td  class="center">
								<c:forEach items="${printerGroup.printerGroupList}" var="printer">
									${printer.printerName}
								</c:forEach>
								</td>
								<td><a data="${printerGroup.id}" class="edit" href="javascript:;"><spring:message code="printer.edit"></spring:message></a></td>
                                <td><a data="${printerGroup.id}" class="delete" href="javascript:;"><spring:message code="printer.delete"></spring:message></a></td>
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
<script src="${staticPath}/js/rtprint.js"></script>
<script src="${staticPath}/js/printergroup.js"></script>
<script type="text/javascript" src="${staticPath}/js/select2/select2.js"></script>
<script type="text/javascript" src="${staticPath}/js/select-init.js"></script>
<script type="text/javascript" src="${staticPath}/js/jquery.validate.min.js"></script>
<script src="${staticPath}/js/scripts.js"></script>
<script src="${staticPath}/jquery.i18n.properties-min-1.0.9.js"></script>
<script src="${staticPath}/jquery.cookie.js"></script>
<script>
    jQuery(document).ready(function() {
    	//初始化表格
    	EditableTable.init();
    	PrinterGroupTable.init();

    	//展开选中的菜单
        $("#rtManagerMenuA").addClass("active");
        $("#rtManagerSub").css('display','block');
        $("#printerLi").addClass("active");
        
        
        $("#editable-sample_filter").hide();
		$("#editable-sample_length").hide();

        
    });
</script>
</body>
</html>
