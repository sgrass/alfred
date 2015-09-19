<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="com.alfred.constant.ConfigConstant"%>
<%
request.setAttribute("contextPath", ConfigConstant.ROOT_PATH);
//request.setAttribute("contextPath", request.getContextPath());
request.setAttribute("fullPath", request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath());
request.setAttribute("staticPath", ConfigConstant.STATIC_PATH);
%>
