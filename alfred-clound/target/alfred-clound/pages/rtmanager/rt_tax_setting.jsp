<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/pages/inc/common.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="ThemeBucket">
    <title>Alfred Cloud POS - Tax Setting</title>
    <!--dynamic table-->
      <link href="${staticPath}/js/advanced-datatable/css/demo_page.css" rel="stylesheet"/>
    <link href="${staticPath}/js/advanced-datatable/css/demo_table.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${staticPath}/js/data-tables/DT_bootstrap.css" />
    <style>
    .classdisplay{
     display:none;
    }
    </style>
</head>
 <body>
	<div id="selectRes" style="display: none">
		<select class="form-control" id="checkedOption">
		    <option value="0">Fixed Tax</option>
		    <option value="1">Service Charge</option>
		</select>
	</div>
	
	<div id="selectStatus" style="display: none">
		<select class="form-control" id="checkedStatus">
		    <option value="1">Normal</option>
		    <option value="0">Disable</option>
		</select>
	</div>
	
	<div id="selectTax" style="display:none">
	    <c:forEach items="${taxList}" var="tax">
	      <option value="${tax.id}">${tax.taxName}</option>
	   </c:forEach>
	</div>
 	<!--main content start-->
<section id="main-content">
        <section class="wrapper">
           <!-- page start-->
        <div class="row">
            <div class="col-sm-12">
                <section class="panel">
                    <header class="panel-heading" style="height:67px"> 
                    
                       <div style="text-transform: none;">Tax
                           <div class="btn-group pull-right">
                                         <button id="editable-sample_new" class="btn btn-primary">
                                     <i class="fa fa-plus"></i>  Tax 
                                    </button>
                                </div>
                        </div>
                    </header>
                    <div class="panel-body">
                        <div class="adv-table editable-table " id="taxTable">
                            <div class="space15"></div>
                            <table class="table table-striped table-hover table-bordered" id="editable-sample">
                                <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>Type</th>
                                    <th>Status</th>
                                    <th>Tax Name</th>
                                    <th>Tax Percentage([0-100]%)</th>
                                    <th>Type</th>
                                    <th>Status</th>
                                    <th>Edit</th>
                                    <th>Delete</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${taxList}" var="tax"> 
								<tr class="gradeX"> 
								<td  class="center">${tax.id}</td> 
							    <td  class="center">${tax.taxType}</td> 
							    <td  class="center">${tax.status}</td> 
								
								<td  class="center">${tax.taxName}</td> 
								
								
								
								<td  class="center"><fmt:formatNumber value="${tax.taxPercentage*100}" pattern="##.##" minFractionDigits="0" ></fmt:formatNumber></td> 
								
								<td  class="center">
								<c:if test="${tax.taxType==0}">Fixed Tax</c:if>
								<c:if test="${tax.taxType==1}">Service Charge</c:if>
								</td> 
								<td  class="center">
								<c:if test="${tax.status==0}">Disable</c:if>
								<c:if test="${tax.status==1}">Normal</c:if>
								</td> 
								<td><a data="${tax.id}" class="edit" href="javascript:;">Edit</a></td>
                                <td><a data="${tax.id}" class="delete" href="javascript:;">Delete</a></td>
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
        <!-- page start-->
         
        <div class="row">
            <div class="col-sm-12">
                 <section class="panel">
                    <header class="panel-heading" style="height:67px"> 
                      <div style="text-transform: capitalize;">  Tax Group
                         <div class="btn-group pull-right">
                                     <button id="editable-sample_new_order" class="btn btn-primary">
                                     <i class="fa fa-plus"></i>  Tax Order 
                                    </button>
                                </div>
                        </div>        
                    </header>
                    <div class="panel-body">
                        <div class="adv-table editable-table " id="taxRuleTable">
                            <div class="space15"></div>
                            <table class="table table-striped table-hover table-bordered" id="editable-sample_order">
                                <thead>
                                <tr>
                                   <th class="classdisplay">Id</th>
                                    <th style="width:10%" >Group Name</th>
                                    <th>Tax #1</th>
                                    <th>Tax #2</th>
                                    <th>Tax #3</th>
                                    <th class="classdisplay">taxOn#</th>
                                    <th class="classdisplay">taxOn#</th>
                                    <th class="classdisplay">taxOn#</th>
                                    <th class="classdisplay">taxOn#</th>
                                    <th class="classdisplay">taxOn#</th>
                                    <th class="classdisplay">taxOn#</th>
                                    <th>Edit</th>
                                    <th>Delete</th>
                                </tr>
                                </thead>
                                <tbody> 
                                <c:forEach items="${taxCategoryLists}" var="taxCat"> 
								<tr class="gradeX"> 
								<td  class="center">${taxCat.id}</td> 
								<td  style="width:10%" class="center">${taxCat.taxCategoryName}</td> 
								
								<c:forEach items="${taxCat.taxCategoryList}" var="tax">
									<td  class="center">${tax.taxCategoryName}
									
									<c:if test="${tax.taxOn==0}">
									      on value
									</c:if>
									
									<c:if test="${tax.taxOn==1}">
									      on Tax#1
									</c:if>
									<c:if test="${tax.taxOn==2}">
									        on Tax#2
									</c:if>
									
									</td> 
								</c:forEach>
								<c:if test="${fn:length(taxCat.taxCategoryList)==2}">
								<td  class="center"></td> 
								</c:if>
							    <c:if test="${fn:length(taxCat.taxCategoryList)==1}">
								<td  class="center"></td> 
								<td  class="center"></td> 
								</c:if>
								<c:if test="${fn:length(taxCat.taxCategoryList)==0}">
								<td  class="center"></td> 
								<td  class="center"></td> 
								<td  class="center"></td> 
								</c:if>
							   <c:forEach items="${taxCat.taxCategoryList}" var="tax">
									<td  class="center">${tax.taxOn}</td> 
								</c:forEach>
								<c:if test="${fn:length(taxCat.taxCategoryList)==2}">
								<td  class="center"></td> 
								</c:if>
							    <c:if test="${fn:length(taxCat.taxCategoryList)==1}">
								<td  class="center"></td> 
								<td  class="center"></td> 
								</c:if>
								<c:if test="${fn:length(taxCat.taxCategoryList)==0}">
								<td  class="center"></td> 
								<td  class="center"></td> 
								<td  class="center"></td> 
								</c:if>

                                <c:forEach items="${taxCat.taxCategoryList}" var="tax">
									<td  class="center">${tax.taxCategoryName}</td> 
								</c:forEach>
							
							    <c:if test="${fn:length(taxCat.taxCategoryList)==2}">
								<td  class="center"></td> 
								</c:if>
							    <c:if test="${fn:length(taxCat.taxCategoryList)==1}">
								<td  class="center"></td> 
								<td  class="center"></td> 
								</c:if>
								<c:if test="${fn:length(taxCat.taxCategoryList)==0}">
								<td  class="center"></td> 
								<td  class="center"></td> 
								<td  class="center"></td> 
								</c:if>

								<td><a data="${taxCat.id}" class="edit" href="javascript:;">Edit</a></td>
                                <td><a data="${taxCat.id}" class="delete" href="javascript:;">Delete</a></td>
								</tr> 
                                </c:forEach> 
                                </tbody>
                            </table>
                        </div>
                        
                    </div>
                </section>
            </div>
        </div>
        </section>
</section>
<!--main content end-->
<script type="text/javascript" src="${staticPath}/js/advanced-datatable/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="${staticPath}/js/data-tables/DT_bootstrap.js"></script>

<script type="text/javascript" src="${staticPath}/js/jquery.validate.min.js"></script>
<script src="${staticPath}/js/bootbox.js"></script>
<script src="${staticPath}/js/rtTaxSetting.js"></script>
<script src="${staticPath}/js/rtTaxSettingOrder.js"></script>
<script src="${staticPath}/js/scripts.js"></script>
<%-- <script src="${staticPath}/js/table-editable.js"></script>
 --%>
<script>
    jQuery(document).ready(function() {
    	//初始化表格
    	EditableTable.init();
    	TaxSettingOrderTable.init();

    	//展开选中的菜单
        $("#rtManagerMenuA").addClass("active");
        $("#rtManagerSub").css('display','block');
        $("#taxSettingLi").addClass("active");

    });
</script>
</body>
</html>