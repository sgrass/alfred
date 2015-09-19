<%@include file="/pages/inc/common.jsp" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="shiroEx" tagdir="/WEB-INF/tags/" %>
<aside>
    <div id="sidebar" class="nav-collapse">
        <!-- sidebar menu start-->
        <div class="leftside-navigation">
            <ul class="sidebar-menu" id="nav-accordion">
                <li>
                    <a class="active" href="${contextPath}/dashboard/dashboardOrder">
                        <i class="fa fa-dashboard"></i>
                        <span><spring:message code="rest.menu.dashboard"></spring:message></span>
                    </a>
                </li>
                <shiroEx:hasAnyPermissions name="manager:all,profile:all,tax:all,printer:all,place_table:all,settlement:all">
                <li class="sub-menu">
                    <a href="javascript:;"  id="rtManagerMenuA">
                        <i class="fa fa-trophy"></i>
                        <span><spring:message code="rest.menu.rest_manager"></spring:message></span>
                    </a>
                    <ul class="sub" id="rtManagerSub">
                    	<shiroEx:hasAnyPermissions name="manager:all,profile:all">
                        <li id="restInfo"><a href="${contextPath}/profile/profileIndex"><spring:message code="rest.menu.profile"></spring:message></a></li>
                      </shiroEx:hasAnyPermissions>
                      <shiroEx:hasAnyPermissions name="manager:all,tax:all">
                        <li id="taxSettingLi"><a href="${contextPath}/taxSetting/rtTaxSettingIndex"><spring:message code="rest.menu.tax_setting"></spring:message></a></li>
                      </shiroEx:hasAnyPermissions>
                      <shiroEx:hasAnyPermissions name="manager:all,printer:all">
                        <li id="printerLi"><a href="${contextPath}/printer/rtPrinter"><spring:message code="rest.menu.printer_kds"></spring:message></a></li>
                      </shiroEx:hasAnyPermissions>
                      <shiroEx:hasAnyPermissions name="manager:all,place_table:all">
                        <li id="placetableLi"><a href="${contextPath}/placeTable/revenueTabIndex"><spring:message code="rest.menu.place_table"></spring:message></a></li>
                      </shiroEx:hasAnyPermissions>
                      <shiroEx:hasAnyPermissions name="manager:all,settlement:all">
                        <li id="settlementResLi"><a href="${contextPath}/settlementRestaurant/settlementRestaurantIndex"><spring:message code="rest.menu.settlement"></spring:message></a></li>
                      </shiroEx:hasAnyPermissions>
                    </ul>
                </li>
                </shiroEx:hasAnyPermissions>
                <shiroEx:hasAnyPermissions name="manager:all,emp_user:all">
                <li class="sub-menu" >
                      <a href="javascript:; " id="accountManagerMenuA">
                        <i class="fa fa-user"></i>
                        <span><spring:message code="rest.menu.account_manager"></spring:message></span>
                      </a>
                    <ul class="sub" id="accountmanager_sub">
                        <li id="accounti1" ><a href="${contextPath}/empuser/empUser"><spring:message code="rest.menu.account"></spring:message></a></li>
                    </ul>
                </li>
                </shiroEx:hasAnyPermissions>
                <shiroEx:hasAnyPermissions name="manager:all,product:all">                                
                <li class="sub-menu" >
                      <a href="javascript:; " id="itemManage">
                        <i class="fa fa-laptop"></i>
                        <span><spring:message code="rest.menu.product.management"></spring:message></span>
                      </a>
                    <ul class="sub" id="itemCategory">
                        <li id="mainCategory" ><a href="${contextPath}/mainCategory/queryCategory"><spring:message code="rest.menu.category"></spring:message></a></li>
                        <li id="itemDetail"><a href="${contextPath}/item/queryAll"><spring:message code="rest.menu.item"></spring:message></a></li>
                        <li id="itemSetmealDetail"><a href="${contextPath}/setMeal/queryAll"><spring:message code="rest.menu.item.setmeal"></spring:message></a></li>
                        <li id="modifierCategoryLi"><a href="${contextPath}/modifier/queryCategory"><spring:message code="rest.menu.modifier.category"></spring:message></a></li>
                        <li id="modifierLi"><a href="${contextPath}/itemModifier/querySubCateModifier"><spring:message code="rest.menu.item.modifier"></spring:message></a></li>
                        <li id="happyHoursLi"><a href="${contextPath}/happyHour/queryAll"><spring:message code="rest.menu.happy_hours"></spring:message></a></li>
                    </ul>
                </li>
                </shiroEx:hasAnyPermissions>
                <shiroEx:hasAnyPermissions name="manager:all,revenue:all">
                <li class="sub-menu" >
                      <a href="javascript:; " id="rvCenterManage">
                        <i class="fa fa-hdd-o"></i>
                        <span><spring:message code="rest.menu.revenue_management"></spring:message></span>
                      </a>
                    <ul class="sub" id="rvCenters">
                        <li id="revenueCenterLi"><a href="${contextPath}/revenueCenter/rtRevenueCenter"><spring:message code="rest.menu.revenue_center"></spring:message></a></li>
                        <li id="revenueCenterManageMentLi"><a href="${contextPath}/reCrManagement/revenueTabIndex"><spring:message code="rest.menu.manpower"></spring:message></a></li>
                        <li id="revenueItemLi"><a href="${contextPath}/reveuneItem/queryRevenueItem"><spring:message code="rest.menu.revenue.menu"></spring:message></a></li>
                    </ul>
                </li>
                </shiroEx:hasAnyPermissions>
                <shiroEx:hasAnyPermissions name="manager:all,report_sales:all,report_order:all,report_menu:all,report_hourly:all,report_user:all,report_discount:all">
                <li class="sub-menu">
                    <a href="javascript:;" id="reportmenu_a">
                        <i class="fa fa-glass"></i>
                        <span><spring:message code="rest.menu.reports"></spring:message></span>
                    </a>
                    <ul class="sub" id="report_sub">
                    	<shiroEx:hasAnyPermissions name="manager:all,report_sales:all">
                        <li id="salesli"><a href="${contextPath}/reportDaySales/queryReportDaySales"><spring:message code="rest.menu.report.sales"></spring:message></a></li>
                      </shiroEx:hasAnyPermissions>
                      <shiroEx:hasAnyPermissions name="manager:all,report_order:all">  
                        <li id=orderlistli><a href="${contextPath}/orderDetail/queryOrderDetail"><spring:message code="rest.menu.report.order"></spring:message></a></li>
                      </shiroEx:hasAnyPermissions>
                      <shiroEx:hasAnyPermissions name="manager:all,report_menu:all">  
                        <li id="menuli"><a href="${contextPath}/reportPluDayItem/queryReportPluDayItem"><spring:message code="rest.menu.report.menu"></spring:message></a></li>
                      </shiroEx:hasAnyPermissions>
                      <shiroEx:hasAnyPermissions name="manager:all,report_hourly:all">  
                        <li id="hourlyli"><a href="${contextPath}/reportHourly/queryReportHourly"><spring:message code="rest.menu.report.hourly"></spring:message></a></li>
                      </shiroEx:hasAnyPermissions>
                      <shiroEx:hasAnyPermissions name="manager:all,report_user:all">  
                        <li id="userTimeSheetli"><a href="${contextPath}/userTimeSheet/queryUserTimeSheet"><spring:message code="rest.menu.report.time.sheet"></spring:message></a></li>
                      </shiroEx:hasAnyPermissions>  
                      <shiroEx:hasAnyPermissions name="manager:all,report_discount:all"> 
                       <li id="discountli"><a href="${contextPath}/reportDiscount/queryDiscountReport"><spring:message code="rest.menu.discount"></spring:message></a></li>
                      </shiroEx:hasAnyPermissions>  
                    </ul>
                </li>
                </shiroEx:hasAnyPermissions>
                <shiroEx:hasAnyPermissions name="manager:all,trans:all">
                <li class="sub-menu">
                    <a href="javascript:;" id="transactionManager">
                        <i class="fa fa-hdd-o"></i>
                        <span><spring:message code="rest.menu.transaction"></spring:message></span>
                    </a>
                    <ul class="sub" id="transact_sub">
                        <li id="transactionli"><a href="${contextPath}/report/transaction"><spring:message code="rest.menu.trans.transact"></spring:message></a></li>
                        <li id="bohli"><a href="${contextPath}/report/bohSettlement"><spring:message code="rest.menu.trans.boh"></spring:message></a></li>
                    </ul>
                </li>
                </shiroEx:hasAnyPermissions>
                <shiroEx:hasAnyPermissions name="manager:all,inventory:all">
                 <li class="sub-menu">
                    <a href="javascript:;" id="inventory">
                        <i class="fa fa-hdd-o"></i>
                        <span><spring:message code="rest.menu.inventory"></spring:message></span>
                    </a>
                    <ul class="sub" id="inventory_sub">
                        <li id="supplier"><a href="${contextPath}/supplier/queryAll"><spring:message code="rest.menu.inventory.supplier"></spring:message></a></li>
                        <li id="material"><a href="${contextPath}/material/queryAll"><spring:message code="rest.menu.inventory.raw"></spring:message></a></li>
                        <li id="material_stock"><a href="${contextPath}/materialStock/queryAll"><spring:message code="rest.menu.inventory.receipt"></spring:message></a></li>
                        <li id="recipe"><a href="${contextPath}/recipeMaterial/queryAll"><spring:message code="rest.menu.inventory.recipe"></spring:message></a></li>
                        <li id="modifier"><a href="${contextPath}/modifierMaterial/queryAll"><spring:message code="rest.menu.inventory.modifier"></spring:message></a></li>
                        <li id="materialReport"><a href="${contextPath}/material/queryMaterial"><spring:message code="rest.menu.inventory.raw.report"></spring:message></a></li>
                        <li id="materialStockReport"><a href="${contextPath}/materialStock/queryMaterialReceipt"><spring:message code="rest.menu.inventory.receipt.report"></spring:message></a></li>
                    </ul>
                </li>
                </shiroEx:hasAnyPermissions>
            </ul>
        </div>
        <!-- sidebar menu end-->
    </div>
</aside>
<script>


</script>

