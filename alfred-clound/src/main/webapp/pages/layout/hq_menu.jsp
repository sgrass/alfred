<%@include file="/pages/inc/common.jsp" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="shiroEx" tagdir="/WEB-INF/tags/" %>
<aside>
	<div id="sidebar" class="nav-collapse">
		<!-- sidebar menu start-->
		<div class="leftside-navigation">
			<ul class="sidebar-menu" id="nav-accordion">
				
				<li class="sub-menu">
				    <a href="javascript:; " id="hqmanagerment_a">
					   <i class="fa fa-laptop"></i> 
					   <span><spring:message code="hq.menu.company"></spring:message></span>
				    </a>
					<ul class="sub" id="hqmanagerment_sub">
						<shiroEx:hasAnyPermissions name="manager:all,rest:all">
						<li id="enterpriseli"><a
							href="${contextPath}/restaurant/hqrestaurant"><spring:message code="hq.menu.subsidiary"></spring:message></a></li>
						</shiroEx:hasAnyPermissions>
						<shiroEx:hasAnyPermissions name="manager:all,user:all">	
						<li id="managersli"><a
							href="${contextPath}/user/managers"><spring:message code="hq.menu.managers"></spring:message></a></li>
						</shiroEx:hasAnyPermissions>
					</ul>
			   </li>
			</ul>
		</div>
		<!-- sidebar menu end-->
	</div>
</aside>