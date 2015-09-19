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
                        <span>Dashboard</span>
                    </a>
                </li>
                <shiroEx:hasAnyPermissions name="manager:all,profile:all,tax:all,printer:all,place_table:all,settlement:all">
                <li class="sub-menu">
                    <a href="javascript:;"  id="rtManagerMenuA">
                        <i class="fa fa-trophy"></i>
                        <span>Restaurant Management</span>
                    </a>
                    <ul class="sub" id="rtManagerSub">
                    	<shiroEx:hasAnyPermissions name="manager:all,profile:all">
                        <li id="restInfo"><a href="${contextPath}/profile/profileIndex">Profile</a></li>
                      </shiroEx:hasAnyPermissions>
                      <shiroEx:hasAnyPermissions name="manager:all,tax:all">
                        <li id="taxSettingLi"><a href="${contextPath}/taxSetting/rtTaxSettingIndex">Tax Setting</a></li>
                      </shiroEx:hasAnyPermissions>
                      <shiroEx:hasAnyPermissions name="manager:all,printer:all">
                        <li id="printerLi"><a href="${contextPath}/printer/rtPrinter">Printer & KDS</a></li>
                      </shiroEx:hasAnyPermissions>
                      <shiroEx:hasAnyPermissions name="manager:all,place_table:all">
                        <li id="placetableLi"><a href="${contextPath}/placeTable/revenueTabIndex">Place & Table</a></li>
                      </shiroEx:hasAnyPermissions>
                      <shiroEx:hasAnyPermissions name="manager:all,settlement:all">
                        <li id="settlementResLi"><a href="${contextPath}/settlementRestaurant/settlementRestaurantIndex">Settlement Settings</a></li>
                      </shiroEx:hasAnyPermissions>
                    </ul>
                </li>
                </shiroEx:hasAnyPermissions>
                <shiroEx:hasAnyPermissions name="manager:all,emp_user:all">
                <li class="sub-menu" >
                      <a href="javascript:; " id="accountManagerMenuA">
                        <i class="fa fa-user"></i>
                        <span>Account Management</span>
                      </a>
                    <ul class="sub" id="accountmanager_sub">
                        <li id="accounti1" ><a href="${contextPath}/empuser/empUser">Account</a></li>
                    </ul>
                </li>
                </shiroEx:hasAnyPermissions>
                <shiroEx:hasAnyPermissions name="manager:all,product:all">                                
                <li class="sub-menu" >
                      <a href="javascript:; " id="itemManage">
                        <i class="fa fa-laptop"></i>
                        <span>Product Management</span>
                      </a>
                    <ul class="sub" id="itemCategory">
                        <li id="mainCategory" ><a href="${contextPath}/mainCategory/queryCategory">Product Category</a></li>
                        <li id="itemDetail"><a href="${contextPath}/item/queryAll">Menu Item</a></li>
                        <li id="modifierCategoryLi"><a href="${contextPath}/modifier/queryCategory">Modifier Category</a></li>
                        <li id="modifierLi"><a href="${contextPath}/itemModifier/querySubCateModifier">Menu Item Modifier</a></li>
                        <li id="happyHoursLi"><a href="${contextPath}/happyHour/queryAll">Happy Hours</a></li>
                    </ul>
                </li>
                </shiroEx:hasAnyPermissions>
                <shiroEx:hasAnyPermissions name="manager:all,revenue:all">
                <li class="sub-menu" >
                      <a href="javascript:; " id="rvCenterManage">
                        <i class="fa fa-hdd-o"></i>
                        <span>Revenue Center Management</span>
                      </a>
                    <ul class="sub" id="rvCenters">
                        <li id="revenueCenterLi"><a href="${contextPath}/revenueCenter/rtRevenueCenter">Revenue Center</a></li>
                        <li id="revenueCenterManageMentLi"><a href="${contextPath}/reCrManagement/revenueTabIndex">Manpower</a></li>
                        <li id="revenueItemLi"><a href="${contextPath}/reveuneItem/queryRevenueItem">Menu</a></li>
                    </ul>
                </li>
                </shiroEx:hasAnyPermissions>
                <shiroEx:hasAnyPermissions name="manager:all,report_sales:all,report_order:all,report_menu:all,report_hourly:all,report_user:all,report_discount:all">
                <li class="sub-menu">
                    <a href="javascript:;" id="reportmenu_a">
                        <i class="fa fa-glass"></i>
                        <span>Reports</span>
                    </a>
                    <ul class="sub" id="report_sub">
                    	<shiroEx:hasAnyPermissions name="manager:all,report_sales:all">
                        <li id="salesli"><a href="${contextPath}/reportDaySales/queryReportDaySales">Sales Report</a></li>
                      </shiroEx:hasAnyPermissions>
                      <shiroEx:hasAnyPermissions name="manager:all,report_order:all">  
                        <li id=orderlistli><a href="${contextPath}/orderDetail/queryOrderDetail">Order Report</a></li>
                      </shiroEx:hasAnyPermissions>
                      <shiroEx:hasAnyPermissions name="manager:all,report_menu:all">  
                        <li id="menuli"><a href="${contextPath}/reportPluDayItem/queryReportPluDayItem">Menu Report</a></li>
                      </shiroEx:hasAnyPermissions>
                      <shiroEx:hasAnyPermissions name="manager:all,report_hourly:all">  
                        <li id="hourlyli"><a href="${contextPath}/reportHourly/queryReportHourly">Hourly Report</a></li>
                      </shiroEx:hasAnyPermissions>
                      <shiroEx:hasAnyPermissions name="manager:all,report_user:all">  
                        <li id="userTimeSheetli"><a href="${contextPath}/userTimeSheet/queryUserTimeSheet">User Time Sheet Report</a></li>
                      </shiroEx:hasAnyPermissions>  
                      <shiroEx:hasAnyPermissions name="manager:all,report_discount:all"> 
                       <li id="discountli"><a href="${contextPath}/reportDiscount/queryDiscountReport">Discount Report</a></li>
                      </shiroEx:hasAnyPermissions>  
                    </ul>
                </li>
                </shiroEx:hasAnyPermissions>
                <shiroEx:hasAnyPermissions name="manager:all,trans:all">
                <li class="sub-menu">
                    <a href="javascript:;" id="transactionManager">
                        <i class="fa fa-hdd-o"></i>
                        <span>Transaction & Settlement</span>
                    </a>
                    <ul class="sub" id="transact_sub">
                        <li id="transactionli"><a href="${contextPath}/report/transaction">Transaction Report</a></li>
                        <li id="bohli"><a href="${contextPath}/report/bohSettlement">BOH Settlement</a></li>
                    </ul>
                </li>
                </shiroEx:hasAnyPermissions>
                <shiroEx:hasAnyPermissions name="manager:all,inventory:all">
                 <li class="sub-menu">
                    <a href="javascript:;" id="inventory">
                        <i class="fa fa-hdd-o"></i>
                        <span>inventory</span>
                    </a>
                    <ul class="sub" id="inventory_sub">
                        <li id="supplier"><a href="${contextPath}/supplier/queryAll">Supplier Management</a></li>
                        <li id="material"><a href="${contextPath}/material/queryAll">Material Managemant</a></li>
                        <li id="material_stock"><a href="${contextPath}/materialStock/queryAll">Material Stock</a></li>
                        <li id="recipe"><a href="${contextPath}/recipeMaterial/queryAll">Recipe Material</a></li>
                        <li id="modifier"><a href="${contextPath}/modifierMaterial/queryAll">Modifier Material</a></li>
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

