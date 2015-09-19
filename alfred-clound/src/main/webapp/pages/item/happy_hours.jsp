<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/pages/inc/common.jsp"%>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="ThemeBucket">
<link rel="shortcut icon" href="images/favicon.png">
<title>Happy hours</title>
<link href="${staticPath}/css/timeticker.css" rel="stylesheet" />
<style>
.happyhour-info {
	height: 44px;
	font-size: 18px;
	padding: 6px;
	margin-bottom: 8px;
	border: 1px solid transparent;
	border-radius: 4px;
}

.happyhour-info .notification-info {
	margin-top: -26px !important;
}

.panel-heading {
	height: 54px;
}

.panel-heading .groupbtn {
	margin-top: -6px;
}

.panel-heading .hhname {
	display: none;
}

.panel-heading .cancelBtn {
	display: none;
}

.panel-heading .saveBtn {
	display: none;
}

.edit .cancelBtn {
	display: inline;
}

.edit .saveBtn {
	display: inline;
}

.edit .clsPanelBtn {
	display: none;
}

.edit .hheditBtn {
	display: none;
}

.hhnameinput {
	width: 200px;
	float: left;
}

.happyhourpanel .alert-icon {
	width: 30px;
	height: 30px;
	display: inline-block;
	-webkit-border-radius: 100%;
	-moz-border-radius: 100%;
	border-radius: 100%;
}

.happyhourpanel .alert-icon i {
	width: 30px;
	height: 30px;
	display: block;
	text-align: center;
	line-height: 30px;
	font-size: 20px;
	color: #fff;
}
</style>
</head>
<body>
<%--  <fmt:setLocale value="en_Us"/> --%>
	<!--main content start-->
	<section id="main-content">
		<section class="wrapper">
			<div class="row">
				<div class="col-xs-2 newhappyhour">
					<button type="submit" class="btn btn-default btn-primary ">
						<i class="fa fa-plus"></i> New Happy Hour
					</button>
				</div>
			</div>
			<div class="row" id="panel_container">
				<div class="col-sm-12">
					<div class="row-fluid">
						<div class="col-md-6 column sortable">
						  <div class="row" id="panel_container_col1">
						  
						   <c:forEach items="${listVo}" var="vo"  begin="0" end="${tally}" >
									<div class="col-md-12">
		    
		    <form action="${contextPath}/happyHours/updateHappyHours" method="post">
		    <section class="panel happyhourpanel">
		        <header class="panel-heading">
		            <h4 class="hhname">${vo.happyHoursName}</h4>
		             <input type="hidden" name="happyHoursId" value="${vo.id}">
		            <input type="text" name="happyHoursName" class="form-control small hhnameinput" value="${vo.happyHoursName}">
		            <div class="groupbtn pull-right">
			            <button class="btn btn-default clsPanelBtn" type="button"><i class="fa fa-chevron-down"></i> Close</button>
			            <button class="btn btn-default hheditBtn" type="button"><i class="fa fa-clock-o"></i> Edit</button>
			            <button class="btn btn-default cancelBtn" type="button"><i class="fa fa-clock-o"></i> Cancel</button>
			            <button class="btn btn-default saveBtn" type="submit"><i class="fa fa-clock-o"></i> Save</button>
		            </div>
		        </header>
               <div class="panel-body hide">
            <table class="table">
                <thead>
                <tr>
                    <th>Week</th>
                    <th>From</th>
                    <th>To</th>
                    <th>Active</th>
                </tr>
                </thead>
                <tbody>
                
                 <c:forEach items="${vo.happyHoursDetailsList}" var="li">
                 <c:if test="${li.week==7||li.week==6}">
                  <tr class="alert-danger">
                    <td><span class="alert-icon"><i class="fa">${li.week}</i></span></td>
                    <td>
	                   <div class="input-group bootstrap-timepicker">
	                   
	                    
		                 <input type="text" name="${li.week}_startTime" class="form-control timepicker-from" value="<fmt:formatDate value="${li.startTime}" pattern="HH:mm" timeStyle=""/>">
			              <span class="input-group-btn">
			 	                <button class="btn btn-default" type="button">
			 	                  <i class="fa fa-clock-o"></i></button>
			              </span>
	                  </div>
	                </td>
                    <td>
	                 <div class="input-group bootstrap-timepicker">
		                 <input type="text" name="${li.week}_endTime" class="form-control timepicker-to"  value="<fmt:formatDate value="${li.endTime}" pattern="HH:mm" timeStyle=""/>">
			                 <span class="input-group-btn">
			 	                <button class="btn btn-default" type="button">
			 	                  <i class="fa fa-clock-o"></i></button>
			                 </span>
	                 </div>
	                </td>
	                <td>
	                    <c:if test="${li.isActive==0}">
	                        <input name="${li.week}_isActive" type="checkbox">
	                    </c:if>
	                     <c:if test="${li.isActive==1}">
	                        <input name="${li.week}_isActive" type="checkbox" checked/>
	                    </c:if>
	                    
	                </td>
                </tr>   
                 </c:if>
                 
                 <c:if test="${li.week==1||li.week==2||li.week==3||li.week==4||li.week==5}">
                 <tr class="alert-info">
                    <td><span class="alert-icon"><i class="fa">${li.week}</i></span></td>
                    <td>
	                   <div class="input-group bootstrap-timepicker">
		                 <input type="text" name="${li.week}_startTime" class="form-control timepicker-from" value="<fmt:formatDate value="${li.startTime}" pattern="HH:mm" timeStyle=""/>">
			              <span class="input-group-btn">
			 	                <button class="btn btn-default" type="button">
			 	                  <i class="fa fa-clock-o"></i></button>
			              </span>
	                  </div>
	                </td>
                    <td>
	                 <div class="input-group bootstrap-timepicker">
		                 <input type="text" name="${li.week}_endTime" class="form-control timepicker-to" value="<fmt:formatDate value="${li.endTime}" pattern="HH:mm" timeStyle=""/>">
			                 <span class="input-group-btn">
			 	                <button class="btn btn-default" type="button">
			 	                  <i class="fa fa-clock-o"></i></button>
			                 </span>
	                 </div>
	                </td>
	                <td>
						 <c:if test="${li.isActive==0}">
	                        <input name="${li.week}_isActive" type="checkbox">
	                    </c:if>
	                     <c:if test="${li.isActive==1}">
	                        <input name="${li.week}_isActive" type="checkbox" checked/>
	                    </c:if>
	                    
	                </td>
                </tr>   
                  </c:if>
                 </c:forEach> 
                </tbody>
            </table>
          </div>          
        <div class="panel-body">
             <!--循环遍历 -->
              <c:forEach items="${vo.happyHoursDetailsList}" var="li">
                <c:if test="${li.week==7||li.week==6}">
                <div class="alert-danger happyhour-info clearfix">
                <span class="alert-icon"><i class="fa">${li.week}</i></span>
                <div class="notification-info">
                    <ul class="clearfix notification-meta">
                      <li class="pull-left notification-sender"><span>
                        <fmt:formatDate value="${li.startTime}" type="both" pattern="HH:mm"/>
                       </span> -  <fmt:formatDate value="${li.endTime}" type="both" pattern="HH:mm"/>
                        </li>
                    </ul>
                </div>
               </div>
                </c:if>
                <c:if test="${li.week==1||li.week==2||li.week==3||li.week==4||li.week==5}">
                <div class="alert-info happyhour-info clearfix">
                <span class="alert-icon"><i class="fa">${li.week}</i></span>
                <div class="notification-info">
                    <ul class="clearfix notification-meta">
                        <li class="pull-left notification-sender"><span>
                        <fmt:formatDate value="${li.startTime}" type="both" pattern="HH:mm"/>
                       </span> -  <fmt:formatDate value="${li.endTime}" type="both" pattern="HH:mm"/>
                        </li>
                    </ul>
                </div>
               </div>
                </c:if>
           </c:forEach>
        </div>
    </section>
</form>
   </div>
							</c:forEach>
							</div>
						</div>
						<div class="col-md-6 column sortable">
						 <div class="row" id="panel_container_col2">
						   <c:forEach items="${listVo}" var="vo" begin="${tally+1}" end="${fn:length(listVo)}">		 
		<div class="col-md-12">
		 <form action="${contextPath}/happyHours/updateHappyHours" method="post">
		    <section class="panel happyhourpanel">
		        <header class="panel-heading">
		            <h4 class="hhname">${vo.happyHoursName}</h4>
		             <input type="hidden" name="happyHoursId" value="${vo.id}">
		            <input type="text" name="happyHoursName" class="form-control small hhnameinput" value="${vo.happyHoursName}">
		            <div class="groupbtn pull-right">
			            <button class="btn btn-default clsPanelBtn" type="button"><i class="fa fa-chevron-down"></i> Close</button>
			            <button class="btn btn-default hheditBtn" type="button"><i class="fa fa-clock-o"></i> Edit</button>
			            <button class="btn btn-default cancelBtn" type="button"><i class="fa fa-clock-o"></i> Cancel</button>
			            <button class="btn btn-default saveBtn" type="submit"><i class="fa fa-clock-o"></i> Save</button>
		            </div>
		        </header>
               <div class="panel-body hide">
            <table class="table">
                <thead>
                <tr>
                    <th>Week</th>
                    <th>From</th>
                    <th>To</th>
                    <th>Active</th>
                </tr>
                </thead>
                <tbody>
                
                 <c:forEach items="${vo.happyHoursDetailsList}" var="li">
                 <c:if test="${li.week==7||li.week==6}">
                  <tr class="alert-danger">
                    <td><span class="alert-icon"><i class="fa">${li.week}</i></span></td>
                    <td>
	                   <div class="input-group bootstrap-timepicker">
	                    
		                 <input type="text" name="${li.week}_startTime" class="form-control timepicker-from" value="<fmt:formatDate value="${li.startTime}" pattern="HH:mm" timeStyle=""/>">
			              <span class="input-group-btn">
			 	                <button class="btn btn-default" type="button">
			 	                  <i class="fa fa-clock-o"></i></button>
			              </span>
	                  </div>
	                </td>
                    <td>
	                 <div class="input-group bootstrap-timepicker">
		                 <input type="text" name="${li.week}_endTime" class="form-control timepicker-to"  value="<fmt:formatDate value="${li.endTime}" pattern="HH:mm" timeStyle=""/>">
			                 <span class="input-group-btn">
			 	                <button class="btn btn-default" type="button">
			 	                  <i class="fa fa-clock-o"></i></button>
			                 </span>
	                 </div>
	                </td>
	                <td>
	                    <c:if test="${li.isActive==0}">
	                        <input name="${li.week}_isActive" type="checkbox">
	                    </c:if>
	                     <c:if test="${li.isActive==1}">
	                        <input name="${li.week}_isActive" type="checkbox" checked/>
	                    </c:if>
	                    
	                </td>
                </tr>   
                 </c:if>
                 
                 <c:if test="${li.week==1||li.week==2||li.week==3||li.week==4||li.week==5}">
                 <tr class="alert-info">
                    <td><span class="alert-icon"><i class="fa">${li.week}</i></span></td>
                    <td>
	                   <div class="input-group bootstrap-timepicker">
		                 <input type="text" name="${li.week}_startTime" class="form-control timepicker-from" value="<fmt:formatDate value="${li.startTime}" pattern="HH:mm" timeStyle=""/>">
			              <span class="input-group-btn">
			 	                <button class="btn btn-default" type="button">
			 	                  <i class="fa fa-clock-o"></i></button>
			              </span>
	                  </div>
	                </td>
                    <td>
	                 <div class="input-group bootstrap-timepicker">
		                 <input type="text" name="${li.week}_endTime" class="form-control timepicker-to" value="<fmt:formatDate value="${li.endTime}" pattern="HH:mm" timeStyle=""/>">
			                 <span class="input-group-btn">
			 	                <button class="btn btn-default" type="button">
			 	                  <i class="fa fa-clock-o"></i></button>
			                 </span>
	                 </div>
	                </td>
	                <td>
						 <c:if test="${li.isActive==0}">
	                        <input name="${li.week}_isActive" type="checkbox">
	                    </c:if>
	                     <c:if test="${li.isActive==1}">
	                        <input name="${li.week}_isActive" type="checkbox" checked/>
	                    </c:if>
	                    
	                </td>
                </tr>   
                  </c:if>
                 </c:forEach> 
                </tbody>
            </table>
          </div>          
        <div class="panel-body">
             <!--循环遍历 -->
              <c:forEach items="${vo.happyHoursDetailsList}" var="li">
                <c:if test="${li.week==7||li.week==6}">
                <div class="alert-danger happyhour-info clearfix">
                <span class="alert-icon"><i class="fa">${li.week}</i></span>
                <div class="notification-info">
                    <ul class="clearfix notification-meta">
                      <li class="pull-left notification-sender"><span>
                        <fmt:formatDate value="${li.startTime}" type="both" pattern="HH:mm"/>
                       </span> -  <fmt:formatDate value="${li.endTime}" type="both" pattern="HH:mm"/>
                        </li>
                    </ul>
                </div>
               </div>
                </c:if>
                <c:if test="${li.week==1||li.week==2||li.week==3||li.week==4||li.week==5}">
                <div class="alert-info happyhour-info clearfix">
                <span class="alert-icon"><i class="fa">${li.week}</i></span>
                <div class="notification-info">
                    <ul class="clearfix notification-meta">
                        <li class="pull-left notification-sender"><span>
                        <fmt:formatDate value="${li.startTime}" type="both" pattern="HH:mm"/>
                       </span> -  <fmt:formatDate value="${li.endTime}" type="both" pattern="HH:mm"/>
                        </li>
                    </ul>
                </div>
               </div>
                </c:if>
           </c:forEach>
        </div>
    </section>
</form>
        </div>
							
			</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
		<!--main content end-->
	</section>
	<script type="text/javascript"src="${staticPath}/js/bootstrap-timepicker/js/bootstrap-timepicker.js"></script>
	<script src="${staticPath}/js/happy-hour.js"></script>
	<script src="${staticPath}/js/scripts.js"></script>
	<script type="text/template" id="happyhour_tpl">
  <form action="${contextPath}/happyHours/addHappyHours" method="post">
   <div class="col-md-6" style="display:none;">
    <section class="panel happyhourpanel">
        <header class="panel-heading">
            <h4 class="hhname">Happy Hour</h4>
            <input type="text" name="happyHoursName" class="form-control small hhnameinput" value="Happy Hour Name">
            <div class="groupbtn pull-right">
	            <button class="btn btn-default clsPanelBtn" type="button"><i class="fa fa-chevron-down"></i> Close</button>
	            <button class="btn btn-default hheditBtn" type="button"><i class="fa fa-clock-o"></i> Edit</button>
	            <button class="btn btn-default cancelBtn" type="button"><i class="fa fa-clock-o"></i> Cancel</button>
	            <button class="btn btn-default saveBtn" type="submit"><i class="fa fa-clock-o"></i> Save</button>
            </div>
        </header>
        <div class="panel-body hide">
            <table class="table">
                <thead>
                <tr>
                    <th>Week</th>
                    <th>From</th>
                    <th>To</th>
                    <th>Active</th>
                </tr>
                </thead>
                <tbody>
                <tr class="alert-danger">
                    <td><span class="alert-icon"><i class="fa">7</i></span></td>
                    <td>
	                   <div class="input-group bootstrap-timepicker">
		                 <input type="text" name="7_startTime"  value="00:00" class="form-control timepicker-from">
			              <span class="input-group-btn">
			 	                <button class="btn btn-default" type="button">
			 	                  <i class="fa fa-clock-o"></i></button>
			              </span>
	                  </div>
	                </td>
                    <td>
	                 <div class="input-group bootstrap-timepicker">
		                 <input type="text"  name="7_endTime"  value="23:45" class="form-control timepicker-to">
			                 <span class="input-group-btn">
			 	                <button class="btn btn-default" type="button">
			 	                  <i class="fa fa-clock-o"></i></button>
			                 </span>
	                 </div>
	                </td>
	                <td>
						<input type="checkbox" name="7_isActive">
	                </td>
                </tr>
                <tr class="alert-info">
                    <td><span class="alert-icon"><i class="fa">1</i></span></td>
                    <td>
	                 <div class="input-group bootstrap-timepicker">
		                 <input type="text"  name="1_startTime"  value="00:00"  class="form-control timepicker-from">
			                 <span class="input-group-btn">
			 	                <button class="btn btn-default" type="button">
			 	                  <i class="fa fa-clock-o"></i></button>
			                 </span>
	                 </div>
	                </td>
                    <td>
	                 <div class="input-group bootstrap-timepicker">
		                 <input type="text"  name="1_endTime"  value="23:45"  class="form-control timepicker-to">
			                 <span class="input-group-btn">
			 	                <button class="btn btn-default" type="button">
			 	                 <i class="fa fa-clock-o"></i></button>
			                 </span>
	                 </div>
	                </td>
	                <td><input type="checkbox" name="1_isActive"></td>
                </tr>
                <tr class="alert-info">
                    <td><span class="alert-icon"><i class="fa">2</i></span></td>
                    <td>
	                 <div class="input-group bootstrap-timepicker">
		                 <input type="text"  name="2_startTime"  value="00:00"  class="form-control timepicker-from">
			                 <span class="input-group-btn">
			 	                <button class="btn btn-default" type="button">
			 	                 <i class="fa fa-clock-o"></i></button>
			                 </span>
	                 </div>
	                </td>
                    <td>
	                 <div class="input-group bootstrap-timepicker">
		                 <input type="text"  name="2_endTime"  value="23:45"  class="form-control timepicker-to">
			                 <span class="input-group-btn">
			 	                <button class="btn btn-default" type="button">
			 	                  <i class="fa fa-clock-o"></i></button>
			                 </span>
	                 </div>
	                </td>
	                <td><input type="checkbox" name="2_isActive" ></td>
                </tr>
                <tr class="alert-info">
                    <td><span class="alert-icon"><i class="fa">3</i></span></td>
                    <td>
	                 <div class="input-group bootstrap-timepicker">
		                 <input type="text"   name="3_startTime"  value="00:00"  class="form-control timepicker-from">
			                 <span class="input-group-btn">
			 	                <button class="btn btn-default" type="button">
			 	                  <i class="fa fa-clock-o"></i></button>
			                 </span>
	                 </div>
	                </td>
                    <td>
	                 <div class="input-group bootstrap-timepicker">
		                 <input type="text"  name="3_endTime"  value="23:45"  class="form-control timepicker-to">
			                 <span class="input-group-btn">
			 	                <button class="btn btn-default" type="button">
			 	                 <i class="fa fa-clock-o"></i></button>
			                 </span>
	                 </div>
	                </td>
	                <td><input type="checkbox" name="3_isActive" ></td>
                </tr>                                
                <tr class="alert-info">
                    <td><span class="alert-icon"><i class="fa">4</i></span></td>
                    <td>
	                 <div class="input-group bootstrap-timepicker">
		                 <input type="text"  name="4_startTime"  value="00:00"  class="form-control timepicker-from">
			                 <span class="input-group-btn">
			 	                <button class="btn btn-default" type="button">
			 	                  <i class="fa fa-clock-o"></i></button>
			                 </span>
	                 </div>
	                </td>
                    <td>
	                 <div class="input-group bootstrap-timepicker">
		                 <input type="text"  name="4_endTime"  value="23:45" class="form-control timepicker-to">
			                 <span class="input-group-btn">
			 	                <button class="btn btn-default" type="button">
			 	                  <i class="fa fa-clock-o"></i></button>
			                 </span>
	                 </div>
	                </td>
	                <td><input type="checkbox"  name="4_isActive"></td>
                </tr> 
                <tr class="alert-info">
                    <td><span class="alert-icon"><i class="fa">5</i></span></td>
                    <td>
	                 <div class="input-group bootstrap-timepicker">
		                 <input type="text"  name="5_startTime"  value="00:00"  class="form-control timepicker-from">
			                 <span class="input-group-btn">
			 	                <button class="btn btn-default" type="button">
			 	                  <i class="fa fa-clock-o"></i></button>
			                 </span>
	                 </div>
	                </td>
                    <td>
	                 <div class="input-group bootstrap-timepicker">
		                 <input type="text"  name="5_endTime"  value="23:45" class="form-control timepicker-to">
			                 <span class="input-group-btn">
			 	                <button class="btn btn-default" type="button">
			 	                  <i class="fa fa-clock-o"></i></button>
			                 </span>
	                 </div>
	                </td>
	                <td><input type="checkbox" name="5_isActive"></td>
                </tr>
                <tr class="alert-danger">
                    <td><span class="alert-icon"><i class="fa">6</i></span></td>
                    <td>
	                 <div class="input-group bootstrap-timepicker">
		                 <input type="text"  name="6_startTime"  value="00:00" class="form-control timepicker-from">
			                 <span class="input-group-btn">
			 	                <button class="btn btn-default" type="button">
			 	                  <i class="fa fa-clock-o"></i></button>
			                 </span>
	                 </div>
	                </td>
                    <td>
	                 <div class="input-group bootstrap-timepicker">
		                 <input type="text"  name="6_endTime"  value="23:45" class="form-control timepicker-to">
			                 <span class="input-group-btn">
			 	                <button class="btn btn-default" type="button">
			 	                  <i class="fa fa-clock-o"></i></button>
			                 </span>
	                 </div>
	                </td>
	                <td><input type="checkbox" name="6_endTime"></td>
                </tr>                 
                </tbody>
            </table>
          </div>          
        <div class="panel-body">
            <div class="alert-danger happyhour-info clearfix">
                <span class="alert-icon"><i class="fa">S</i></span>
                <div class="notification-info">
                    <ul class="clearfix notification-meta">
                        <li class="pull-left notification-sender"><span>00:00</span> - 23:45</li>
                    </ul>
                </div>
            </div>
            <div class="alert-info happyhour-info clearfix">
                <span class="alert-icon"><i class="fa">M</i></span>

                <div class="notification-info">
                    <ul class="clearfix notification-meta">
                        <li class="pull-left notification-sender"><span>00:00</span> - 23:45</li>
                    </ul>
                </div>
            </div>
            <div class="alert-info happyhour-info clearfix">
                <span class="alert-icon"><i class="fa">T</i></span>

                <div class="notification-info">
                    <ul class="clearfix notification-meta">
                        <li class="pull-left notification-sender"><span>00:00</span> - 23:45</li>
                    </ul>
                </div>
            </div>
            <div class="alert-info happyhour-info clearfix">
                <span class="alert-icon"><i class="fa">W</i></span>

                <div class="notification-info">
                    <ul class="clearfix notification-meta">
                        <li class="pull-left notification-sender"><span>00:00</span> - 23:45</li>
                    </ul>
                </div>
            </div>
            <div class="alert-info happyhour-info clearfix">
                <span class="alert-icon"><i class="fa">T</i></span>

                <div class="notification-info">
                    <ul class="clearfix notification-meta">
                        <li class="pull-left notification-sender"><span>00:00</span> - 23:45</li>
                    </ul>
                </div>
            </div>
            <div class="alert-info happyhour-info clearfix">
                <span class="alert-icon"><i class="fa">F</i></span>

                <div class="notification-info">
                    <ul class="clearfix notification-meta">
                        <li class="pull-left notification-sender"><span>00:00</span> - 23:45</li>
                    </ul>
                </div>
            </div>
            <div class="alert-danger happyhour-info clearfix">
                <span class="alert-icon"><i class="fa">S</i></span>

                <div class="notification-info">
                    <ul class="clearfix notification-meta">
                        <li class="pull-left notification-sender"><span>00:00</span> - 23:45</li>
                    </ul>
                </div>
            </div>
        </div>
    </section>
   </div>
</form>
</script>
	<script type="text/javascript">
    HappyHourPanel.init();     
    $('.newhappyhour').bind('click', function(evt){
          HappyHourPanel.newpanel('#panel_container', '#happyhour_tpl');
    });
    
	//左边菜单展开
	$("#itemManage").addClass("active");
	$("#itemCategory").css('display', 'block');
	$("#happyHoursLi").addClass("active");
   
</script>
</body>
</html>