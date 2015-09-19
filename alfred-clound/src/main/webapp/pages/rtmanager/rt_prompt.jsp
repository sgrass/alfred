<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/pages/inc/common.jsp"%>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta name="keyword" content="">

    <title>404</title>
    <link href="${staticPath}/font-awesome/css/font-awesome.css" rel="stylesheet" />
</head>


<section id="main-content">
     <section class="wrapper">
          <section class="error-wrapper">
          <div >
              <h2>Recenue Center not Create</h2>
          </div>  
           <a href="${contextPath}/revenueCenter/rtRevenueCenter" class="back-btn"><i class="fa fa-home"></i> Back To Recenue Center</a>
      </section>
     </section>
     </section>
     
        <script src="${staticPath}/js/scripts.js"></script>
     <script type="text/javascript">
     $("#rtManagerMenuA").addClass("active");
     $("#rtManagerSub").css('display','block');
     $("#placetableLi").addClass("active");
     </script>
  </body>
</html>
