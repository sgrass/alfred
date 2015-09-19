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
		    <option value="0"><spring:message code="tax.fixed"></spring:message></option>
		    <option value="1"><spring:message code="tax.service"></spring:message></option>
		</select>
	</div>
	
	<div id="selectStatus" style="display: none">
		<select class="form-control" id="checkedStatus">
		    <option value="1"><spring:message code="tax.normal"></spring:message></option>
		    <option value="0"><spring:message code="tax.disable"></spring:message></option>
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
                    
                       <div style="text-transform: none;"><spring:message code="tax.tax"></spring:message>
                           <div class="btn-group pull-right">
                                         <button id="editable-sample_new" class="btn btn-primary">
                                     <i class="fa fa-plus"></i>  <spring:message code="tax.tax"></spring:message> 
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
                                    <th><spring:message code="tax.taxName"></spring:message></th>
                                    <th><spring:message code="tax.per"></spring:message></th>
                                    <th><spring:message code="tax.type"></spring:message></th>
                                    <th><spring:message code="tax.status"></spring:message></th>
                                    <th><spring:message code="tax.edit"></spring:message></th>
                                    <th><spring:message code="tax.delete"></spring:message></th>
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
								<c:if test="${tax.taxType==0}"><spring:message code="tax.fixed"></spring:message></c:if>
								<c:if test="${tax.taxType==1}"><spring:message code="tax.service"></spring:message></c:if>
								</td> 
								<td  class="center">
								<c:if test="${tax.status==0}"><spring:message code="tax.disable"></spring:message></c:if>
								<c:if test="${tax.status==1}"><spring:message code="tax.normal"></spring:message></c:if>
								</td> 
								<td><a data="${tax.id}" class="edit" href="javascript:;"><spring:message code="tax.edit"></spring:message></a></td>
                                <td><a data="${tax.id}" class="delete" href="javascript:;"><spring:message code="tax.delete"></spring:message></a></td>
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
                      <div style="text-transform: capitalize;"> <spring:message code="taxgroup.taxGroup"></spring:message>
                         <div class="btn-group pull-right">
                                     <button id="editable-sample_new_order" class="btn btn-primary">
                                     <i class="fa fa-plus"></i>  <spring:message code="taxgroup.taxGroup"></spring:message>
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
                                    <th style="width:10%" ><spring:message code="taxgroup.groupName"></spring:message></th>
                                    <th><spring:message code="taxgroup.tax1"></spring:message></th>
                                    <th><spring:message code="taxgroup.tax2"></spring:message></th>
                                    <th><spring:message code="taxgroup.tax3"></spring:message></th>
                                    <th class="classdisplay">taxOn#</th>
                                    <th class="classdisplay">taxOn#</th>
                                    <th class="classdisplay">taxOn#</th>
                                    <th class="classdisplay">taxOn#</th>
                                    <th class="classdisplay">taxOn#</th>
                                    <th class="classdisplay">taxOn#</th>
                                    <th><spring:message code="taxgroup.edit"></spring:message></th>
                                    <th><spring:message code="taxgroup.delete"></spring:message></th>
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
									      on <spring:message code="taxgroup.tax1"></spring:message>
									</c:if>
									<c:if test="${tax.taxOn==2}">
									      on <spring:message code="taxgroup.tax2"></spring:message>
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

								<td><a data="${taxCat.id}" class="edit" href="javascript:;"><spring:message code="taxgroup.edit"></spring:message></a></td>
                                <td><a data="${taxCat.id}" class="delete" href="javascript:;"><spring:message code="taxgroup.delete"></spring:message></a></td>
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
<script src="${staticPath}/jquery.i18n.properties-min-1.0.9.js"></script>
<script src="${staticPath}/jquery.cookie.js"></script>
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