<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/pages/inc/common.jsp" %>
<html lang="en">
<head>
    <meta charset="utf-8">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="ThemeBucket">
    <link rel="shortcut icon" href="images/favicon.png">
    <title>Alfred_Report_KOT</title>
    <!--responsive table-->
    <link href="${staticPath}/css/table-responsive.css" rel="stylesheet" />
      <!--dynamic table-->
    <link href="${staticPath}/js/advanced-datatable/css/demo_page.css" rel="stylesheet"/>
    <link href="${staticPath}/js/advanced-datatable/css/demo_table.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${staticPath}/js/data-tables/DT_bootstrap.css"/>
</head>
<body>
	<!--main content start-->
	<section id="main-content">
		<section class="wrapper">
			<!-- page start-->
			<div class="row">
				<div class="col-sm-12">
					<section class="panel">
						<header class="panel-heading text-center">
							Urban Spoon KOT LIST REPORT <span class="tools pull-right">
								<a href="javascript:;" class="fa fa-chevron-down"></a> <a
								href="javascript:;" class="fa fa-cog"></a> <a
								href="javascript:;" class="fa fa-times"></a>
							</span>

						</header>
						<header class="panel-heading">
							<div class="row">
								<div class="col-md-3">Restaurant Open Date</div>
								<div class="col-md-3">2014-1-10</div>
								<div class="col-md-3">Created</div>
								<div class="col-md-3">2014-1-10</div>
							</div>
						</header>
						<div class="panel-body">
							<div class="row" style="margin-bottom: 20px;">
								<form class="form-inline" role="form">
									<div class="form-group">
										<label for="startTimeInput">StartTime:</label> <input
											type="text" class="form-control" id="startTimeInput">
									</div>
									<div class="form-group">
										<label for="endTimeInput">EndTime:</label> <input type="text"
											class="form-control" id="endTimeInput">
									</div>
									<div class="form-group">
										<label for="orderIdInput">OrderId:</label> <input type="text"
											class="form-control" id="orderIdInput">
									</div>
									<div class="form-group">
										<label class="col-lg-4 control-label">Country</label>
										<div class="col-lg-8">
											<select class="form-control" style="width: 100%">
												<option>1</option>
												<option>2</option>
												<option>3</option>
												<option>4</option>
												<option>5</option>
											</select>
										</div>
									</div>

								</form>

							</div>
							<div class="adv-table">

								<table class="display table table-bordered table-striped"
									id="dynamic-table">
									<thead>
										<tr>
											<th>Date</th>
											<th>Order ID</th>
											<th>KOT NO</th>
											<th>User Name</th>
											<th>Table</th>

										</tr>
									</thead>
									<tbody>
										<tr>
											<td>10/1/2014</td>
											<td>1</td>
											<td class="numeric">10001</td>
											<td class="numeric">admin</td>
											<td class="numeric">T1</td>
										</tr>
										<tr>
											<td>10/1/2014</td>
											<td>1</td>
											<td class="numeric">10001</td>
											<td class="numeric">admin</td>
											<td class="numeric">T1</td>
										</tr>
										<tr>
											<td>10/1/2014</td>
											<td>1</td>
											<td class="numeric">10001</td>
											<td class="numeric">admin</td>
											<td class="numeric">T1</td>
										</tr>
										<tr>
											<td>10/1/2014</td>
											<td>1</td>
											<td class="numeric">10001</td>
											<td class="numeric">admin</td>
											<td class="numeric">T1</td>
										</tr>
										<tr>
											<td>10/1/2014</td>
											<td>1</td>
											<td class="numeric">10001</td>
											<td class="numeric">admin</td>
											<td class="numeric">T1</td>
										</tr>
										<tr>
											<td>10/1/2014</td>
											<td>1</td>
											<td class="numeric">10001</td>
											<td class="numeric">admin</td>
											<td class="numeric">T1</td>
										</tr>
										<tr>
											<td>10/1/2014</td>
											<td>1</td>
											<td class="numeric">10001</td>
											<td class="numeric">admin</td>
											<td class="numeric">T1</td>
										</tr>

										<tr>
											<td>10/1/2014</td>
											<td>1</td>
											<td class="numeric">10001</td>
											<td class="numeric">admin</td>
											<td class="numeric">T1</td>
										</tr>
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


	<!--dynamic table-->
<script type="text/javascript" language="javascript" src="${staticPath}/js/advanced-datatable/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="${staticPath}/js/data-tables/DTU_bootstrap.js"></script>
<!--common script init for all pages-->
<!--dynamic table initialization -->
<script src="${staticPath}/js/dynamic_table_init.js"></script>
<!--common script init for all pages-->
<script src="${staticPath}/js/scripts.js"></script>
<!--script for this page-->
<script>
	$(document).ready(function() {
		$("#reportmenu_a").addClass("active");
		$("#report_sub").css('display', 'block');
		$("#kotli").addClass("active");
	});
</script>
</body>
</html>
