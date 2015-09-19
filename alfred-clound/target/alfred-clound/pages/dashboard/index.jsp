<!DOCTYPE html>
<%@include file="/pages/inc/common.jsp" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="ThemeBucket">
    <link rel="shortcut icon" href="${staticPath}/images/favicon.png">
    <title>Index</title>
    <!--clock css-->
    <link href="${staticPath}/js/css3clock/css/style.css" rel="stylesheet">
    <!--Morris Chart CSS -->
    <link rel="stylesheet" href="${staticPath}/js/morris-chart/morris.css">
    <!-- Custom styles for this template -->
    <link href="${staticPath}/css/style-responsive.css" rel="stylesheet"/> 
    <link rel="stylesheet" href="${staticPath}/css/daterangepicker-bs3.css"/>
    <style>
    .table>thead>tr>th {
    border-bottom: 0px solid #aaa;
    }
   .table>thead>tr>th, .table>tbody>tr>th, .table>tfoot>tr>th, .table>thead>tr>td, .table>tbody>tr>td, .table>tfoot>tr>td {
     border-top: 0px solid #aaa;
    }
    </style>
</head>
<body>
<!--main content start-->
<section id="main-content">
<section class="wrapper">
<!--mini statistics start-->
<div class="row">
    <div class="col-md-3">
        <div class="mini-stat clearfix">
            <span class="mini-stat-icon orange"><i class="fa fa-gavel"></i></span>

            <div class="mini-stat-info">
                <span>${order.subTotal }</span>
                SUB TOTAL
            </div>
        </div>
    </div>
    <div class="col-md-3">
        <div class="mini-stat clearfix">
            <span class="mini-stat-icon tar"><i class="fa fa-tag"></i></span>

            <div class="mini-stat-info">
               <span>${order.tax }</span>
                 TOTAL TAX
            </div>
        </div>
    </div>
    <div class="col-md-3">
        <div class="mini-stat clearfix">
            <span class="mini-stat-icon pink"><i class="fa fa-money"></i></span>

            <div class="mini-stat-info">
                        <span>${order.discount }</span>
                TOTAL DISCOUNT
            </div>
        </div>
    </div>
    <div class="col-md-3">
        <div class="mini-stat clearfix">
            <span class="mini-stat-icon green"><i class="fa fa-eye"></i></span>

            <div class="mini-stat-info">
                <span>${order.total } </span>
                TOTAL AMOUNT
            </div>
        </div>
    </div>
</div>
<!--mini statistics end-->
<div class="row">
    <div class="col-md-8">
        <!--earning graph start-->
        <section class="panel">
            <header class="panel-heading">
                Earning Graph <span class="tools pull-right">
            <a href="javascript:;" class="fa fa-chevron-down"></a>
            <a href="javascript:;" class="fa fa-times"></a>
            </span>
            
            <div id="reportrange" style="cursor: pointer; height: 34px; width:278px;margin-top: -7px;padding: 6px 12px;font-size: 14px;line-height: 1.428571429;background-color: #fff;background-image: none;border: 1px solid #e2e2e4;box-shadow: none; color: #c2c2c2; border-radius: 4px;margin-right: 10px;" class="pull-right">
                  <i class="glyphicon glyphicon-calendar fa fa-calendar"></i>
                  <span></span> <b class="caret"></b>
                  </div>
            </header>
            <div class="panel-body">
                <div id="graph-area" class="main-chart">
                </div>
                <div class="region-stats">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="region-earning-stats">
                             <div id="selectName">LAST 7 DAYS</div> <span id="revenueTotal"></span>
                              
                            </div>
                            <ul class="clearfix location-earning-stats" id="revenueList">

                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--earning graph end-->
        
         <section class="panel">
                        <header class="panel-heading">
                          Today's Top Sales by Categories
                        <span class="tools pull-right">
                            <a href="javascript:;" class="fa fa-chevron-down"></a>
                            <a href="javascript:;" class="fa fa-cog"></a>
                            <a href="javascript:;" class="fa fa-times"></a>
                         </span>
                        </header>
                        <div class="panel-body">

                            <div class="row">
                            <div class="col-md-8 chartJS">
                                   <canvas id="pie-chart-js" height="250" width="250" ></canvas>
                            </div>
                              <div class="col-md-4">
                                   <table class="table">
                                   <thead>
                                   <tr>
                                      <th></th>
                                      <th>Category</th>
                                      <th>Qty</th>
                                   </tr>
                                   </thead>
                                   <tbody id="itemCategoryPie">
                                
                                   </tbody>
                                   </table>
                            </div>
                            </div>
                        </div>
                    </section>
                <section class="panel">
                        <header class="panel-heading">
                            Today's Top Sales Item Sold
                        <span class="tools pull-right">
                            <a href="javascript:;" class="fa fa-chevron-down"></a>
                            <a href="javascript:;" class="fa fa-cog"></a>
                            <a href="javascript:;" class="fa fa-times"></a>
                         </span>
                        </header>
                        <div class="panel-body">
                             <div class="row">
                            <div class="col-md-8 chartJS">
                                 <canvas id="item-pie-chart-js" height="250" width="250" ></canvas>
                            </div>
                              <div class="col-md-4">
                                   <table class="table">
                                   <thead>
                                   <tr>
                                      <th></th>
                                      <th>Category</th>
                                      <th>Qty</th>
                                   </tr>
                                   </thead>
                                   <tbody id="itemPie">
                                
                                   </tbody>
                                   </table>
                            </div>
                            </div>
                            
                        </div>
                    </section>
    </div>
    <div class="col-md-4">
    <!--Session sales  start-->
	      <section class="panel">
	            <div class="panel-body">
	                <div class="top-stats-panel">
	                    <h4 class="widget-h">Session Sales</h4>
	
	                    <div class="bar-stats">
	                        <ul class="progress-stat-bar clearfix">
	                            <li data-percent="${sessionSales.firsePercent}%"><span class="progress-stat-percent pink"></span></li>
	                            <li data-percent="${sessionSales.secoundPercent}%"><span class="progress-stat-percent"></span></li>
	                            <li data-percent="${sessionSales.thirdPercent }%"><span class="progress-stat-percent yellow-b"></span></li>
	                        </ul>
	                        <ul class="bar-legend">
	                            <li><span class="bar-legend-pointer pink"></span>  Breakfast</li>
	                            <li><span class="bar-legend-pointer green"></span>  Launch</li>
	                            <li><span class="bar-legend-pointer yellow-b"></span>  Dinner</li>
	                        </ul>
	                        <div class="daily-sales-info">
	                            <span class="sales-count">$${sessionSales.total } </span> <span class="sales-label">Net Sales</span>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </section>
        <!-- Sales Settment -->
          <section class="panel">
            <div class="panel-body">
                <div class="top-stats-panel">
                    <h4 class="widget-h">Daily Settment</h4>

                    <div class="bar-stats">
                        <ul class="progress-stat-bar clearfix">
                            <li data-percent="${play.firsePercent}%"><span class="progress-stat-percent pink"></span></li>
                            <li data-percent="${play.secoundPercent}%"><span class="progress-stat-percent"></span></li>
                            <li data-percent="${play.thirdPercent}%"><span class="progress-stat-percent yellow-b"></span></li>
                        </ul>
                        <ul class="bar-legend">
                            <li><span class="bar-legend-pointer pink"></span>  Cash</li>
                            <li><span class="bar-legend-pointer green"></span>  Card</li>
                            <li><span class="bar-legend-pointer yellow-b"></span>  Other</li>
                        </ul>
                        <div class="daily-sales-info">
                            <span class="sales-count">$${play.total}</span> <span class="sales-label">Total Checks</span>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
</div>
</section>
</section>


<!--[if lte IE 8]>
<script language="javascript" type="text/javascript" src="js/flot-chart/excanvas.min.js"></script><![endif]-->
<script src="${staticPath}/js/skycons/skycons.js"></script>
<script src="${staticPath}/js/jquery.scrollTo/jquery.scrollTo.js"></script>
<!-- <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script> -->
<script src="${staticPath}/js/calendar/clndr.js"></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.5.2/underscore-min.js"></script>
<script src="${staticPath}/js/calendar/moment-2.2.1.js"></script>
<script src="${staticPath}/js/evnt.calendar.init.js"></script>
<script src="${staticPath}/js/jvector-map/jquery-jvectormap-1.2.2.min.js"></script>
<script src="${staticPath}/js/jvector-map/jquery-jvectormap-us-lcc-en.js"></script>
<script src="${staticPath}/js/gauge/gauge.js"></script>
<!--clock init-->
<script src="${staticPath}/js/css3clock/js/css3clock.js"></script>
<!--Easy Pie Chart-->
<script src="${staticPath}/js/easypiechart/jquery.easypiechart.js"></script>
<!--Sparkline Chart-->
<script src="${staticPath}/js/sparkline/jquery.sparkline.js"></script>
<!--Morris Chart-->
<script src="${staticPath}/js/morris-chart/morris.js"></script>
<script src="${staticPath}/js/morris-chart/raphael-min.js"></script>


<script src="${staticPath}/js/chart-js/Chart.js"></script>
<script src="${staticPath}/js/dashboard.js"></script>
<script src="${staticPath}/js/jquery.customSelect.min.js"></script>


<script type="text/javascript" src="${staticPath}/js/moment.js"></script>
<script type="text/javascript" src="${staticPath}/js/daterangepicker.js"></script>

<!--common script init for all pages-->
<script src="${staticPath}/js/scripts.js"></script>
<!--script for this page-->
<script>

$(document).ready(function() {

    var cb = function(start, end, label) {
      console.log(start.toISOString(), end.toISOString(), label);
      $('#reportrange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
      //alert("Callback has fired: [" + start.format('MMMM D, YYYY') + " to " + end.format('MMMM D, YYYY') + ", label = " + label + "]");
      //ajax请求
       var startTime=$('input[name="daterangepicker_start"]').val();
       var endTime=$('input[name="daterangepicker_end"]').val();
       getSalesChar(startTime,endTime);
    }

    var optionSet1 = {
      startDate: moment().subtract('days', 6),
      endDate: moment(),
      minDate: '01/01/2012',
      maxDate: '12/31/2014',
      dateLimit: { days: 60 },
      showDropdowns: true,
      showWeekNumbers: true,
      timePicker: false,
      timePickerIncrement: 1,
      timePicker12Hour: true,
      ranges: {
    	  'Last 7 Days': [moment().subtract('days', 6), moment()],
          'This Month': [moment().startOf('month'), moment().endOf('month')]    
      },
      opens: 'left',
      buttonClasses: ['btn btn-default'],
      applyClass: 'btn-small btn-primary',
      cancelClass: 'btn-small',
      format: 'MM/DD/YYYY',
      separator: ' to '
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
    
    
  //选择日期
    $(".ranges li").click(function(){
       $("#selectName").html("");
       $("#selectName").html($(this).text());
    });

 });

</script>

</body>
</html>