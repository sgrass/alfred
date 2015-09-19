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
    <title>Item Analys Report</title>
    <link href="${staticPath}/js/advanced-datatable/css/demo_page.css" rel="stylesheet"/>
    <link href="${staticPath}/js/advanced-datatable/css/demo_table.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${staticPath}/js/data-tables/DT_bootstrap.css"/>
    <link rel="stylesheet" href="${staticPath}/css/daterangepicker-bs3.css"/>
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
						   Item Analys Report
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
										<input type="hidden" id="startTime" name="startTime" value=""/>
					                    <input type="hidden" id="endTime" name="endTime" value=""/>
					                    <input type="hidden" id="revenueName" name="revenueName"  value=""/>
					                
								  </form>
							</div>
							</div>
							<div class="adv-table">
								<table class="display table table-bordered table-striped" id="item-table">
									<thead>
										<tr>
											<th>Restaurant Date</th>
											<th>Revenue Center</th>
											<th>Main Category</th>
											<th>Sub Category</th>
											<th>Item</th>
											<th>Amount Qty</th>
											<th>Amount Price</th>
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
			<!-- page end-->
<!--dynamic table-->
<script type="text/javascript" src="${staticPath}/js/advanced-datatable/js/jquery.dataTables10.js"></script>
<script type="text/javascript" src="${staticPath}/js/data-tables/DT_bootstrap.js"></script>
<script type="text/javascript" src="${staticPath}/js/moment.js"></script>
<script type="text/javascript" src="${staticPath}/js/daterangepicker.js"></script>
<script src="${staticPath}/js/scripts.js"></script>
<script>
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

    //revenueCenter下拉列表
    $('#revenueId').change(function(){
    	reflashTableList();
    });
   
    //导出列表
    $(".dropdown-menu li").click(function(){
    	if($(this).text()=='Export PDF'){
    		$("#revenueName").val($("#revenueId").find("option:selected").text());
    		$("#startTime").val($('input[name="daterangepicker_start"]').val());
    		$("#endTime").val( $('input[name="daterangepicker_end"]').val());
        	$(".form-inline").attr("action","/reportPluDayItem/exportReportPluDayItemPdf");
        	$(".form-inline").submit();
    		
    	}else if($(this).text()=='Export Excel'){
    		$("#revenueName").val($("#revenueId").find("option:selected").text());
    		$("#startTime").val($('input[name="daterangepicker_start"]').val());
    		$("#endTime").val( $('input[name="daterangepicker_end"]').val());
        	$(".form-inline").attr("action","/reportPluDayItem/exportReportPluDayItem");
        	$(".form-inline").submit();
    		
    	};
    	
    });
    
    $("#reportmenu_a").addClass("active");
    $("#report_sub").css('display','block');
    $("#menuli").addClass("active");
    

    var oTable = $('#item-table').dataTable({
	        "bServerSide" : true,
			"sAjaxSource" : "/reportPluDayItem/ajaxReportPluDayItemJson",//mvc后台ajax调用接口。
			'bPaginate' : true,//是否分页。
			"bProcessing" : true,//当datatable获取数据时候是否显示正在处理提示信息。
			'bFilter' : false,//是否使用内置的过滤功能。
			'bLengthChange' : true,//是否允许用户自定义每页显示条数。
			"columns" : [ {
				"data" : "businessDate"
			}, {
				"data" : "revenueName"
			}, {
				"data" : "itemMainCategoryName"
			} , {
				"data" : "itemCategoryName"
			} , {
				"data" : "itemName"
			} , {
				"data" : "itemCount"
			}, {
				"data" : "itemPrice"
			}],
			bFilter : false,
			bLengthChange : false,
			iDisplayLength:5,
			"fnCreatedRow": function( nRow, aData, iDataIndex ) {
				//var dateFmt=new Date(parseInt(aData.businessDate)).format("MM-dd-yyyy");
	             $('td:eq(0)', nRow).html(moment(aData.businessDate).format("MM-DD-YYYY"));
				
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
         oSettings.sAjaxSource="/reportPluDayItem/ajaxReportPluDayItemJson";
         oTable.fnClearTable(0);
         oTable.fnDraw();
		 
	 }

    
    
    
    
    
    
    
    
    
    
 });
</script>
</body>
</html>
