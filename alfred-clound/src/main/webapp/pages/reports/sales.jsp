<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/pages/inc/common.jsp" %>
<jsp:useBean id="now" class="java.util.Date" />
<html lang="en">
<head>
    <meta charset="utf-8">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="ThemeBucket">
    <link rel="shortcut icon" href="images/favicon.png">
    <title>Alfred_Report_OrderList</title>
    <link href="${staticPath}/js/advanced-datatable/css/demo_page.css" rel="stylesheet"/>
    <link href="${staticPath}/js/advanced-datatable/css/demo_table.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${staticPath}/js/data-tables/DT_bootstrap.css"/>
    <link rel="stylesheet" href="${staticPath}/css/daterangepicker-bs3.css"/>
<style>
.openMain {
  margin-left: -240px !important;
}
.openRight {
right: 0px !important;
-webkit-transition: all .3s ease-in-out;
-moz-transition: all .3s ease-in-out;
-o-transition: all .3s ease-in-out;
transition: all .3s ease-in-out;
}
.fonds {
color: #FFFFFF;
}
</style>
</head>
<body>
	<section id="main-content">
		<section class="wrapper">
			<!-- page start-->
			<div class="row">
				<div class="col-sm-12">
					<section class="panel">
						<header class="panel-heading" style="height: 67px;">
						  <div style="text-transform: capitalize;">
						   Day Sales Report
				
			      <div class="btn-group pull-right">
                            <button id="exportOrderList" data-toggle="dropdown" class="btn btn-success dropdown-toggle" type="button"><i class="fa fa-download"></i> Export <span class="caret"></span></button>
                            <ul role="menu" class="dropdown-menu">
                                <li><a href="#">Export Excel</a></li>
                                <li><a href="#">Export PDF</a></li>
                            </ul>
                        </div><!-- /btn-group -->
				</div>
				   </header>
						<div class="panel-body">
						    <div class="clearfix">
							<div class="row" style="margin-bottom: 40px;">
								<form class="form-inline" method="post" role="form">
									<div class="form-group" style="margin-left: 25px;">
										<label class="col-lg-4 control-label" style="margin-top: 8px;">ReveCenter:</label>
										<div class="col-lg-8">
											<select class="form-control" id="revenueId" name="revenueId" style="width: 203px">
											  <option value="0">All</option>
											  <c:forEach items="${revenueCenterList }" var="revenueCenter">
											  <option value="${revenueCenter.id }">${revenueCenter.revName }</option>
											  </c:forEach>
											</select>
										</div>
									</div>
									  <div id="reportrange" style="cursor: pointer; height: 34px; width:306px;padding: 6px 12px;font-size: 14px;line-height: 1.428571429;background-color: #fff;background-image: none;border: 1px solid #e2e2e4;box-shadow: none; color: #c2c2c2; border-radius: 4px;margin-right: 10px;" class="pull-right">
						                <i class="glyphicon glyphicon-calendar fa fa-calendar"></i>
						                <span></span> <b class="caret"></b>
						               </div>
										<input type="hidden" id="startTime" name="startTime" value="${startTime }"/>
					                    <input type="hidden" id="endTime" name="endTime" value="${endTime }"/>
					                    <input type="hidden" id="revenueName" name="revenueName"  value="0"/>
					                    
								  </form>
							</div>
							</div>
							<div class="adv-table">
								<table class="display table table-bordered table-striped" id="sales-table">
									<thead>
										<tr>
											<th>Restaurant Date</th>
											<th>Total Tax</th>
											<th>Item Sales</th>
											<th>Total Sales</th>
											<th>Sales Details</th>
										</tr>
									</thead>
									<tbody>
									   
									</tbody>
								</table>
							</div>
						</div>
					</section>
				</div>
			</div>
		</section>
	</section>
	

<div id="rightDiv" class="right-sidebar">
<div class="search-row">
    <input type="text" placeholder="Search" class="form-control">
</div>
<div class="right-stat-bar">
<ul class="right-side-accordion">
<li class="widget-collapsible">
    <a href="#" class="head widget-head terques-bg active clearfix">
        <span class="pull-left">SUMMARY</span>
        <span class="pull-right widget-collapse"><i class="ico-minus"></i></span>
    </a>
    <ul class="widget-container">
        <li>
            <table class="table fonds">
								<thead>
								<tr>
									<th>Type</th>
									<th>Qty</th>
									<th>Amount</th>
							     </tr>
								</thead>
								<tbody id="summaryTbody">
								</tbody>

							</table>
        </li>
    </ul>
</li>
<li class="widget-collapsible">
    <a href="#" class="head widget-head red-bg active clearfix">
        <span class="pull-left">Media</span>
        <span class="pull-right widget-collapse"><i class="ico-minus"></i></span>
    </a>
    <ul class="widget-container">
        <li>
             <table class="table fonds">
								<thead>
								<tr>
									<th>Type</th>
									<th>Qty</th>
									<th>Amount</th>
							     </tr>
								</thead>
								<tbody id="mediaTbody">
								</tbody>
							</table>
        </li>
    </ul>
</li>
<li class="widget-collapsible">
    <a href="#" class="head widget-head yellow-bg  active clearfix">
        <span class="pull-left">VOID/REFUND SUMMARY</span>
        <span class="pull-right widget-collapse"><i class="ico-minus"></i></span>
    </a>
    <ul class="widget-container">
        <li>
            <table class="table fonds">
								<thead>
								<tr>
									<th>Type</th>
									<th>Qty</th>
									<th>Amount</th>
							     </tr>
								</thead>
								<tbody id="voidTbody">
									
								</tbody>

							</table>
        </li>
    </ul>
</li>

<li class="widget-collapsible">
    <a href="#" class="head widget-head purple-bg active">
        <span class="pull-left">Discounts</span>
        <span class="pull-right widget-collapse"><i class="ico-minus"></i></span>
    </a>
    <ul class="widget-container">
        <li>
           <table class="table fonds">
								<thead>
								<tr>
									<th>Type</th>
									<th>Qty</th>
									<th>Amount</th>
							     </tr>
								</thead>
								<tbody id="discountTbody">
									
								</tbody>

							</table>
						</li>
    </ul>
</li>


<li class="widget-collapsible">
    <a href="#" class="head widget-head terques-bg active clearfix">
        <span class="pull-left">Tax</span>
        <span class="pull-right widget-collapse"><i class="ico-minus"></i></span>
    </a>
    <ul class="widget-container">
        <li>
            <table class="table fonds">
								<thead>
								<tr>
									<th>Type</th>
									<th>Qty</th>
									<th>Amount</th>
							     </tr>
								</thead>
								<tbody id="taxTbody">
									
								</tbody>

							</table>
        </li>
    </ul>
</li>
</ul>
<div id="totalDiv" class="fonds"></div>
</div>

</div>
<!--right sidebar end-->
<!-- page end-->
<!--dynamic table-->
<script type="text/javascript" src="${staticPath}/js/advanced-datatable/js/jquery.dataTables10.js?v=1"></script>
<script type="text/javascript" src="${staticPath}/js/data-tables/DT_bootstrap.js"></script>
<!--common script init for all pages-->
<script type="text/javascript" src="${staticPath}/js/moment.js"></script>
<script type="text/javascript" src="${staticPath}/js/daterangepicker.js"></script>
<script src="${staticPath}/js/scripts.js"></script>
<script>

//return
function returnPrice(value){
	
	if(value==""&&value==null){
		
		value="0.00";
	}
	return value;
}
//return
function returnQty(value){
	
	if(value==""&&value==null){
		
		value="0";
	}
	return value;
}

function show(busDate){

	var businessDate=busDate;

	//Ajax 
	var summaryTalbe="";
	var mediaTable="";
	var voidTable="";
	var discountTable="";
	var taxTable="";
	var startTime=$('input[name="daterangepicker_start"]').val();
	var endTime= $('input[name="daterangepicker_end"]').val()
	var revenueId=$("#revenueId").val();
	if($("#container").attr("class")=='openMain'){
	 	$("#container").removeClass("openMain");
		$("#rightDiv").removeClass("openRight");
	}else{
		$.ajax({
			type : "post",
			url : "/reportDaySales/getReportDaySales",
			data : {
				businessDate:businessDate,startTime:startTime,endTime:endTime,revenueId:revenueId
			},
			dataType : "json",
			success : function(resData) {
		
				var data=resData.reportDayTax;
				var taxList=resData.reportDayTaxList;
	          //
				if(data!=null){
				summaryTalbe='<tr><td>ItemSales</td><td>'+returnQty(data.itemSalesQty)+'</td><td>'+data.itemSales+'</td></tr>';
				summaryTalbe+='<tr><td>Total Tax</td><td></td><td>'+data.totalTax+'</td></tr>';
				summaryTalbe+='<tr><td>Discount on %</td><td>'+data.discountPerQty+'</td><td>'+data.discountPer+'</td></tr>';
				summaryTalbe+='<tr><td>Discount on $</td><td>'+data.discountQty+'</td><td>'+data.discount+'</td></tr>';
				summaryTalbe+='<tr><td>ENT Bills $</td><td>'+data.focBillQty+'</td><td>'+data.focBill+'</td></tr>';
				summaryTalbe+='<tr><td>VOID Bills$</td><td>'+data.totalVoidQty+'</td><td>'+data.totalVoid+'</td></tr>';
				summaryTalbe+='<tr><td>Total Sales</td><td></td><td>'+data.totalSales+'</td></tr>';
				$("#summaryTbody").html(summaryTalbe);
				voidTable+='<tr><td>VOID</td><td>'+data.totalVoidQty+'</td><td>'+data.totalVoid+'</td></tr>';
				$("#voidTbody").html(voidTable);
				discountTable+='<tr><td>Discount on %</td><td>'+data.discountPerQty+'</td><td>'+data.discountPer+'</td></tr>';
				discountTable+='<tr><td>Discount on $</td><td>'+data.discountQty+'</td><td>'+data.discount+'</td></tr>';
				discountTable+='<tr><td>Total Discount</td><td>'+(data.discountPerQty+data.discountQty)+'</td><td>'+(data.discount+data.discountPer)+'</td></tr>';
				$("#discountTbody").html(discountTable);
				
				mediaTable+='<tr><td>CASH</td><td>'+data.cashQty+'</td><td>'+data.cash+'</td></tr>';
				mediaTable+='<tr><td>NETS</td><td>'+data.netsQty+'</td><td>'+data.nets+'</td></tr>';
				mediaTable+='<tr><td>VISA</td><td>'+data.visaQty+'</td><td>'+data.visa+'</td></tr>';
				mediaTable+='<tr><td>MC</td><td>'+data.mcQty+'</td><td>'+data.mc+'</td></tr>';
				mediaTable+='<tr><td>AMEX</td><td>'+data.amexQty+'</td><td>'+data.amex+'</td></tr>';
				mediaTable+='<tr><td>JBL</td><td>'+data.jblQty+'</td><td>'+data.jbl+'</td></tr>';
				mediaTable+='<tr><td>UnionPay</td><td>'+data.unionPayQty+'</td><td>'+data.unionPay+'</td></tr>';
				mediaTable+='<tr><td>Diner</td><td>'+data.dinerQty+'</td><td>'+data.diner+'</td></tr>';
				
				//mediaTable+='<tr><td>CC</td><td>'+data.holdldQty+'</td><td>'+data.holdld+'</td></tr>';
				mediaTable+='<tr><td>BOH</td><td>'+data.holdldQty+'</td><td>'+data.holdld+'</td></tr>';
				//mediaTable+='<tr><td>ENT</td><td>'+data.totalCashQty+'</td><td>'+data.totalCash+'</td></tr>';
				
				mediaTable+='<tr><td>TOTAL CARD</td><td>'+data.totalCardQty+'</td><td>'+data.totalCard+'</td></tr>';
				mediaTable+='<tr><td>TOTAL NETS</td><td>'+data.netsQty+'</td><td>'+(data.nets)+'</td></tr>';
				mediaTable+='<tr><td>TOTAL CASH</td><td>'+data.totalCashQty+'</td><td>'+data.totalCash+'</td></tr>';
				
				//mediaTable+='<tr><td>TOTAL CC</td><td>'+data.itemSalesQty+'</td><td>'+data.itemSales+'</td></tr>';
				mediaTable+='<tr><td>TOTAL BOH</td><td>'+data.holdldQty+'</td><td>'+(data.holdld)+'</td></tr>';
				//mediaTable+='<tr><td>TOTAL ENT</td><td>'+data.itemSalesQty+'</td><td>'+data.itemSales+'</td></tr>';
		
				$("#mediaTbody").html(mediaTable);
				}
	           if(taxList.length >0){
	        	   
				for(var tax in taxList){
					taxTable+='<tr><td>'+taxList[tax].taxName+'</td><td>'+taxList[tax].taxQty+'</td><td>'+taxList[tax].taxAmount+'</td></tr>';
				}
				$("#taxTbody").html(taxTable);
				
				var totalTable='<table class="table"><tbody><tr><td>Nett Sales</td><td>'+data.nettSales+'</td></tr>';
				totalTable+='<tr><td>Total # of Bills</td><td>'+data.totalBills+'</td></tr>';
				//totalTable+='<tr><td>AvgBills</td><td>3</td></tr>';
				totalTable+='<tr><td>Total open Items</td><td>'+data.openCount+'</td></tr>';
				totalTable+='</tbody></table>';
	           }
				$("#totalDiv").html(totalTable);
			 	$("#container").addClass("openMain");
				$("#rightDiv").addClass("openRight");

			},
			error : function() {
				alert('error');

			}
		});
	
	}
	
	
}
$(document).ready(function(){

	
	   var cb = function(start, end, label) {
	        console.log(start.toISOString(), end.toISOString(), label);
	        $("#defaultTime").val(""+start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
	        $('#reportrange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
	        reflashTableList();
	      }
	    var optionSet1 = {
	    	      startDate: moment().subtract('days', 6),
	    	      endDate: moment(),
	    	      minDate: '01/01/2012',
	    	      maxDate: '12/31/2018',
                 /*dateLimit: { days: 60 }, */	    
	              showDropdowns: true,
	    	      showWeekNumbers: true,
	    	      timePicker: false,
	    	      timePickerIncrement: 1,
	    	      timePicker12Hour: true,
	    	      ranges: {
	    	         'Today': [moment(), moment()],
	    	         'Yesterday': [moment().subtract('days', 1), moment().subtract('days', 1)],
	    	         'Last 7 Days': [moment().subtract('days', 6), moment()],
	    	         'Last 30 Days': [moment().subtract('days', 29), moment()],
	    	         'This Month': [moment().startOf('month'), moment().endOf('month')],
	    	         'Last Month': [moment().subtract('month', 1).startOf('month'), moment().subtract('month', 1).endOf('month')]
	    	      },
	    	      opens: 'left',
	    	      buttonClasses: ['btn btn-default'],
	    	      applyClass: 'btn-small btn-primary',
	    	      cancelClass: 'btn-small',
	    	      format: 'MM/DD/YYYY',
	    	      separator: ' to ',
	    	      locale: {
	    	          applyLabel: 'Submit',
	    	          cancelLabel: 'Clear',
	    	          fromLabel: 'From',
	    	          toLabel: 'To',
	    	          customRangeLabel: 'Custom',
	    	          daysOfWeek: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr','Sa'],
	    	          monthNames: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December']
	    	      }
	    	    };
	    

	    $('#reportrange span').html(moment().subtract('days', 6).format('MMMM D, YYYY') + ' - ' + moment().format('MMMM D, YYYY'));
	    $('#reportrange').daterangepicker(optionSet1, cb);
	    $('#reportrange').on('show.daterangepicker', function() { console.log("show event fired"); });
	    $('#reportrange').on('hide.daterangepicker', function() { console.log("hide event fired"); });
	    $('#reportrange').on('apply.daterangepicker', function(ev, picker) { 
	      console.log("apply event fired, start/end dates are " 
	        + picker.startDate.format('MMMM D, YYYY') 
	        + " to " 
	        + picker.endDate.format('MMMM D, YYYY')
	      ); 
	    });
	    
	    $('#reportrange').on('cancel.daterangepicker', function(ev, picker) { console.log("cancel event fired"); });
	    $('#options1').click(function() {
	      $('#reportrange').data('daterangepicker').setOptions(optionSet1, cb);
	    });
	    $('#destroy').click(function() {
	      $('#reportrange').data('daterangepicker').remove();
	    });
	    
	    $('#revenueId').change(function(){
	        reflashTableList();
	    });
	    
	  
	    //导出列表
	    $(".dropdown-menu li").click(function(){
	    	if($(this).text()=='Export PDF'){
	    		$("#revenueName").val($("#revenueId").find("option:selected").text());
	    		$("#startTime").val($('input[name="daterangepicker_start"]').val());
	    		$("#endTime").val( $('input[name="daterangepicker_end"]').val());
	        	$(".form-inline").attr("action","/reportDaySales/exportOrderDetailPdf");
	        	$(".form-inline").submit();
	    		
	    	}else if($(this).text()=='Export Excel'){
	    		$("#revenueName").val($("#revenueId").find("option:selected").text());
	    		$("#startTime").val($('input[name="daterangepicker_start"]').val());
	    		$("#endTime").val( $('input[name="daterangepicker_end"]').val());
	        	$(".form-inline").attr("action","/reportDaySales/exportOrderDetailExcel");
	        	$(".form-inline").submit();
	    		
	    	};
	    	
	    });
	    
		 $("#reportmenu_a").addClass("active");
		 $("#report_sub").css('display','block');
		 $("#salesli").addClass("active");
		 
		 var oTable = $('#sales-table').dataTable({
		        "bServerSide" : true,
				"sAjaxSource" : "/reportDaySales/ajaxReportDaySalesJson",//mvc后台ajax调用接口。
				'bPaginate' : true,//是否分页。
				"bProcessing" : true,//当datatable获取数据时候是否显示正在处理提示信息。
				'bFilter' : false,//是否使用内置的过滤功能。
				'bLengthChange' : true,//是否允许用户自定义每页显示条数。
				"sServerMethod": "POST",
				"columns" : [ {
					"data" : "businessDate"
				}, {
					"data" : "totalTax"
				}, {
					"data" : "itemSales"
				} , {
					"data" : "totalSales"
				} , {
					"data" : null
				}],
				bFilter : false,
				bLengthChange : false,
				iDisplayLength:5,
				 "fnInitComplete": function(oSettings, json) {
			
					 
				 },
				"fnCreatedRow": function( nRow, aData, iDataIndex ) {
					//var dateFmt=new Date(parseInt(aData.businessDate)).format("MM-dd-yyyy");
		             $('td:eq(0)', nRow).html(moment(aData.businessDate).format("MM-DD-YYYY"));
		             
		             var busdate=moment(aData.businessDate).format("MM-DD-YYYY");
		             var datails='<button class="btn btn-info look" onclick=show(\"'+busdate+'\");><i class="fa fa-info-circle"></i> look </button>';
		          
		             $('td:eq(4)', nRow).html(datails);
					 },
			     "fnInfoCallback": function( oSettings, iStart, iEnd, iMax, iTotal, sPre ) {
					  if(iTotal==0){
						$("#exportOrderList").attr('disabled',"true");
						}else{		
						$("#exportOrderList").removeAttr("disabled");
						};  
					//return iStart +" to "+ iEnd;
					
					return "Showing "+iStart+"to  "+iEnd+" of "+iTotal+" entries"
						
			    },
				"fnServerParams" : function(aoData) {
					aoData.push({
						"name" : "revenueId",
						"value" :$("#revenueId").val()
					});
					aoData.push({
						"name" : "startTime",
						"value" : $('input[name="daterangepicker_start"]').val()
					});
					aoData.push({
						"name" : "endTime",
						"value" : $('input[name="daterangepicker_end"]').val()
					});
				}
			});
		 
      
		 function reflashTableList(){
	      var oSettings= oTable.fnSettings();
	      oSettings.sAjaxSource="/reportDaySales/ajaxReportDaySalesJson";
	      oTable.fnClearTable(0);
	      oTable.fnDraw();
			 
		 }  
	  
});




</script>
</body>
</html>
