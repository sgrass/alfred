<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="com.alfred.constant.ConfigConstant"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">  
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
  <title>Report</title>
  <style type="text/css">
	html {
	  font-family: sans-serif;
	  -webkit-text-size-adjust: 100%;
	      -ms-text-size-adjust: 100%;
	}
	body {
	  margin: 0;
	  font-size:12px;
	}
	strong {
	  font-weight: bold;
	}
	.container_12 {
		width: 92%;
		margin-left: 4%;
		margin-right: 4%;
	}
	
	.grid_1,
	.grid_2,
	.grid_3,
	.grid_4,
	.grid_5,
	.grid_6,
	.grid_7,
	.grid_8,
	.grid_9,
	.grid_10,
	.grid_11,
	.grid_12 {
		display:inline;
		float: left;
		position: relative;
		margin-left: 1%;
		margin-right: 1%;
	}
	
	.alpha {
		margin-left: 0;
	}
	
	.omega {
		margin-right: 0;
	}
	

	.container_12 .grid_1 {
		width:6.333%;
	}
	
	.container_12 .grid_2 {
		width:14.667%;
	}
	
	.container_12 .grid_3 {
		width:23.0%;
	}
	
	.container_12 .grid_4 {
		width:31.333%;
	}
	
	.container_12 .grid_5 {
		width:39.667%;
	}
	
	.container_12 .grid_6 {
		width:48.0%;
	}
	
	.container_12 .grid_7 {
		width:56.333%;
	}
	
	.container_12 .grid_8 {
		width:64.667%;
	}
	
	.container_12 .grid_9 {
		width:73.0%;
	}
	
	.container_12 .grid_10 {
		width:81.333%;
	}
	
	.container_12 .grid_11 {
		width:89.667%;
	}
	
	.container_12 .grid_12 {
		width:98.0%;
	}
	
	.container_12 .prefix_1 {
		padding-left:8.333%;
	}
	
	.container_12 .prefix_2 {
		padding-left:16.667%;
	}
	
	.container_12 .prefix_3 {
		padding-left:25.0%;
	}
	
	.container_12 .prefix_4 {
		padding-left:33.333%;
	}
	
	.container_12 .prefix_5 {
		padding-left:41.667%;
	}
	
	.container_12 .prefix_6 {
		padding-left:50.0%;
	}
	
	.container_12 .prefix_7 {
		padding-left:58.333%;
	}
	
	.container_12 .prefix_8 {
		padding-left:66.667%;
	}
	
	.container_12 .prefix_9 {
		padding-left:75.0%;
	}
	
	.container_12 .prefix_10 {
		padding-left:83.333%;
	}
	
	.container_12 .prefix_11 {
		padding-left:91.667%;
	}
	
	
	.container_12 .suffix_1 {
		padding-right:8.333%;
	}
	
	.container_12 .suffix_2 {
		padding-right:16.667%;
	}
	
	.container_12 .suffix_3 {
		padding-right:25.0%;
	}
	
	.container_12 .suffix_4 {
		padding-right:33.333%;
	}
	
	.container_12 .suffix_5 {
		padding-right:41.667%;
	}
	
	.container_12 .suffix_6 {
		padding-right:50.0%;
	}
	
	.container_12 .suffix_7 {
		padding-right:58.333%;
	}
	
	.container_12 .suffix_8 {
		padding-right:66.667%;
	}
	
	.container_12 .suffix_9 {
		padding-right:75.0%;
	}
	
	.container_12 .suffix_10 {
		padding-right:83.333%;
	}
	
	.container_12 .suffix_11 {
		padding-right:91.667%;
	}
	
	
	.container_12 .push_1 {
		left:8.333%;
	}
	
	.container_12 .push_2 {
		left:16.667%;
	}
	
	.container_12 .push_3 {
		left:25.0%;
	}
	
	.container_12 .push_4 {
		left:33.333%;
	}
	
	.container_12 .push_5 {
		left:41.667%;
	}
	
	.container_12 .push_6 {
		left:50.0%;
	}
	
	.container_12 .push_7 {
		left:58.333%;
	}
	
	.container_12 .push_8 {
		left:66.667%;
	}
	
	.container_12 .push_9 {
		left:75.0%;
	}
	
	.container_12 .push_10 {
		left:83.333%;
	}
	
	.container_12 .push_11 {
		left:91.667%;
	}
	
	.container_12 .pull_1 {
		left:-8.333%;
	}
	
	.container_12 .pull_2 {
		left:-16.667%;
	}
	
	.container_12 .pull_3 {
		left:-25.0%;
	}
	
	.container_12 .pull_4 {
		left:-33.333%;
	}
	
	.container_12 .pull_5 {
		left:-41.667%;
	}
	
	.container_12 .pull_6 {
		left:-50.0%;
	}
	
	.container_12 .pull_7 {
		left:-58.333%;
	}
	
	.container_12 .pull_8 {
		left:-66.667%;
	}
	
	.container_12 .pull_9 {
		left:-75.0%;
	}
	
	.container_12 .pull_10 {
		left:-83.333%;
	}
	
	.container_12 .pull_11 {
		left:-91.667%;
	}
	
	
	.clear {
		clear: both;
		display: block;
		overflow: hidden;
		visibility: hidden;
		width: 0;
		height: 0;
	}
	
	.clearfix:after {
		clear: both;
		content: ' ';
		display: block;
		font-size: 0;
		line-height: 0;
		visibility: hidden;
		width: 0;
		height: 0;
	}
	
	.clearfix {
		display: inline-block;
	}
	
	* html .clearfix {
		height: 1%;
	}
	
	.clearfix {
		display: block;
	}
	.title {
	 text-align: center;
	}	
	.reporttitle {
	 text-align: center;
	 font-size:18px;
	 margin:20px 0px;
	}
	.table {
	  width: 100%;
	  margin-bottom: 16px;
	}
	.head {
	  background-color: #e8e9f0;
	}
	.head .title {
	  color:#767676;
	  padding:8px 8px;
	  font-size: 14px;
	  text-align: left;	
	}
	.alignright{
	  text-align: right;
	}
	.section {
	  margin:10px 0px;
	}
	.header {
	 margin: 10px 4px;
	}
	.totalfooter {
	 border-top: 3px solid #333;
	 padding:8px 0px;
	 font-weight: bold;
	 float:right;
	}
  </style>
</head>
<body>
 <div class="container_12">
  <div class="grid_12 title">
   <div class="reporttitle"><strong>Daily Sales Summary</strong></div>
  </div>
  <div class="grid_12 header">
	  <div class="grid_3"><strong>Restaurant</strong></div>
	  <div class="grid_8">${restaurantName }</div>
	  <div class="grid_3"><strong>Business Date</strong></div>
	  <div class="grid_8">${startTime }--${endTime }</div>
	  <div class="grid_3"><strong>Revenue Center</strong></div>
	  <div class="grid_8">${revenueName }</div>
	  <div class="grid_3"><strong>Created</strong></div>
	  <div class="grid_8">${create }</div>
  </div>
  <div class="grid_6">
	 <div class="grid_12 section">
	  <div class="head grid_12">
	    <div class="title"><strong>NETT Sales</strong></div>
	  </div>
      <div class="grid_12">
       <table class="table">
	        <tbody>
	        <c:set var="salesTotal" value="0.00"></c:set>
	        <tr>
	            <td class="grid_1"></td>
	            <td>Item Sales</td>
	            <td class="alignright">${unit}${ReportDaySales.itemSales}</td>
	            <c:set var="salesTotal" value="${salesTotal+ReportDaySales.itemSales }"></c:set>
	        </tr>
	        <tr>
	            <td class="grid_1"></td>
	            <td>ENT Items</td>
	            <td  class="alignright">${unit}${ReportDaySales.focItem}</td>
	            <c:set var="salesTotal" value="${salesTotal+ReportDaySales.focItem }"></c:set>
	        </tr>
	        <tr>
	            <td class="grid_1"></td>
	            <td>ENT Bills</td>
	            <td  class="alignright">${unit}${ReportDaySales.focBill}</td>
	            <c:set var="salesTotal" value="${salesTotal+ReportDaySales.focBill }"></c:set>
	        </tr>
	        <tr>
	            <td class="grid_1"></td>
	            <td>Void Items</td>
	            <td  class="alignright">${unit}${ReportDaySales.totalBillVoid}</td>
	            <c:set var="salesTotal" value="${salesTotal+ReportDaySales.totalBillVoid }"></c:set>
	        </tr>
	        <tr>
	            <td class="grid_1"></td>
	            <td>Void Bills</td>
	            <td  class="alignright">${unit}${ReportDaySales.totalVoid}</td>
	            <c:set var="salesTotal" value="${salesTotal+ReportDaySales.totalVoid }"></c:set>
	        </tr>
	        <tr>
	            <td class="grid_1"></td>
	            <td>Total Discounts</td>
	            <td  class="alignright">${unit}${ReportDaySales.discountPer+ReportDaySales.discount}</td>
	            <c:set var="salesTotal" value="${salesTotal+ReportDaySales.discountPer+ReportDaySales.discount }"></c:set>
	        </tr>
	        <tr>
	            <td class="grid_1"></td>
	            <td>Total Tax/SVC</td>
	            <td  class="alignright">${unit}${ReportDaySales.totalTax}</td>
	            <c:set var="salesTotal" value="${salesTotal+ReportDaySales.totalTax }"></c:set>
	        </tr>
	        <tr>
	            <td class="grid_1"></td>
	            <td>Rounding</td>
	            <td  class="alignright">${unit}${round.roundBalancePrice}</td>
	            <c:set var="salesTotal" value="${salesTotal+round.roundBalancePrice }"></c:set>
	        </tr>
	        </tbody>
	    </table>
      </div>
      <div class="grid_6 totalfooter">
         <div class="grid_7">Total</div>
         <div class="grid_5 alignright">${unit}${salesTotal}</div>
      </div>
     </div>

	 <div class="grid_12 section">
	  <div class="head grid_12">
	    <div class="title"><strong>Operating Metrics</strong></div>
	  </div>
      <div class="grid_12">
       <table class="table">
	        <tbody>
	        <tr>
	            <td class="grid_1"></td>
	            <td>Guests</td>
	            <td class="alignright">${ReportDaySales.personQty}</td>
	        </tr>
	        <tr>
	            <td class="grid_1"></td>
	            <td>Guest Avg</td>
	            <td  class="alignright">${unit}${ReportDaySales.nettSales/ReportDaySales.personQty}</td>
	        </tr>
	        <tr>
	            <td class="grid_1"></td>
	            <td>Checks</td>
	            <td  class="alignright">${ReportDaySales.orderQty}</td>
	        </tr>
	        <tr>
	            <td class="grid_1"></td>
	            <td>Check Avg</td>
	            <td  class="alignright">${unit}${ReportDaySales.nettSales/ReportDaySales.orderQty}</td>
	        </tr>
	        </tbody>
	    </table>
      </div>
     </div>

	 <div class="grid_12 section">
	  <div class="head grid_12">
	    <div class="title"><strong>Transactions</strong></div>
	  </div>
      <div class="grid_12">
       <table class="table">
	        <tbody>
	         <c:set var="payTotal" value="0.00"></c:set>
	        <tr>
	            <td class="grid_1"></td>
	            <td>Cash</td>
	            <td class="alignright">${unit}${ReportDaySales.cash}</td>
	             <c:set var="payTotal" value="${payTotal+ReportDaySales.cash }"></c:set>
	            
	        </tr>
	        <tr>
	            <td class="grid_1"></td>
	            <td>VISA</td>
	            <td  class="alignright">${unit}${ReportDaySales.visa}</td>
	            <c:set var="payTotal" value="${payTotal+ReportDaySales.visa }"></c:set>
	            
	        </tr>
	        <tr>
	            <td class="grid_1"></td>
	            <td>Master Card</td>
	            <td  class="alignright">${unit}${ReportDaySales.totalCard}</td>
	            <c:set var="payTotal" value="${payTotal+ReportDaySales.totalCard }"></c:set>
	            
	        </tr>
	        </tbody>
	    </table>
      </div>
      <div class="grid_6 totalfooter">
         <div class="grid_7">Total</div>
         <div class="grid_5 alignright">${unit}${payTotal}</div>
      </div>
     </div>
  </div>

  <div class="grid_6">
	 <div class="grid_12 section">
	  <div class="head grid_12">
	    <div class="title"><strong>Sales Anasysis</strong></div>
	  </div>
      <div class="grid_12">
       <table class="table">
	        <tbody>
	        <c:set var="itemTotal" value="0.00"></c:set>
	        <c:forEach items="${ReportPluDayItemList}" var="ReportPluDayItem">
	      
	         <tr>
	            <td class="grid_1"></td>
	            <td>${ReportPluDayItem.itemMainCategoryName }</td>
	            <td class="alignright">${unit}${ReportPluDayItem.itemPrice }</td>
	            <c:set var="itemTotal" value="${itemTotal+ReportPluDayItem.itemPrice}"></c:set>
	        </tr>
	        </c:forEach>
	        </tbody>
	    </table>
      </div>
      <div class="grid_6 totalfooter">
         <div class="grid_7">Total</div>
         <div class="grid_5 alignright">${unit}${itemTotal }</div>
      </div>
     </div>
       
	 <div class="grid_12 section">
	  <div class="head grid_12">
	    <div class="title"><strong>Discounts</strong></div>
	  </div>
      <div class="grid_12">
       <table class="table">
	        <tbody>
	        <c:set var="discountCount" value="0.00"></c:set>
	        <tr>
	            <td class="grid_1"></td>
	            <td>Discount on %</td>
	            <td class="alignright">${unit}${ReportDaySales.discountPer}</td>
	            <c:set var="discountCount" value="${discountCount+ReportDaySales.discountPer}"></c:set>
	        </tr>
	        <tr>
	            <td class="grid_1"></td>
	            <td>Discount on $</td>
	            <td  class="alignright">${unit}${ReportDaySales.discount}</td>
	            <c:set var="discountCount" value="${discountCount+ReportDaySales.discount}"></c:set>
	            
	        </tr>
	        </tbody>
	    </table>
      </div>
      <div class="grid_6 totalfooter">
         <div class="grid_7">Total</div>
         <div class="grid_5 alignright">${unit}${discountCount}</div>
      </div>
     </div>  

	 <div class="grid_12 section">
	  <div class="head grid_12">
	    <div class="title"><strong>Tax/Service Charge</strong></div>
	  </div>
      <div class="grid_12">
       <table class="table">
	        <tbody>
	        <c:set var="taxTatol" value="0.00" ></c:set>
	        <c:forEach items="${taxList}" var="tax">
	          <tr>
	            <td class="grid_1"></td>
	            <td>${tax.taxName }</td>
	            <td class="alignright">${unit}${tax.taxAmount}</td>
	            <c:set var="taxTatol" value="${taxTatol+tax.taxAmount}" ></c:set>
	          </tr>
	        </c:forEach>
	        </tbody>
	    </table>
      </div>
      <div class="grid_6 totalfooter">
         <div class="grid_7">Total</div>
         <div class="grid_5 alignright">${unit}${taxTatol}</div>
      </div>
     </div>  
  </div>
 </div>  
</body>
</html>