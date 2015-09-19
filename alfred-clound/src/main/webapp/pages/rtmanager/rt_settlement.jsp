<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/pages/inc/common.jsp"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="ThemeBucket">
<title>RT Settlement</title>
 <link rel="stylesheet" type="text/css" href="${staticPath}/js/jquery-tags-input/jquery.tagsinput.css" />
</head>
<body>
<section id="main-content">
    <section class="wrapper">
        <!-- page start-->
        <div class="row">
            <div class="col-sm-12">
                <section class="panel">
                    <header class="panel-heading" style="height:67px"> 
                    <div style="text-transform: capitalize;"><spring:message code="settlementSettings"></spring:message>
                    <span class="tools pull-right">
                        <button type="button" class="btn btn-success" id="saveBut"><i class="fa fa-save"></i><spring:message code="settlementSettings.save"></spring:message></button>
                     </span>
                    </div>
                    </header>
                    <div class="panel-body">
            
                   <form class="form-horizontal bucket-form" id="settlementForm" method="POST" action="${contextPath}/settlementRestaurant/addSettlementRestaurant">
                   <div class="form-group">
                        <label class="col-sm-3 control-label col-lg-3" for="inputSuccess"><spring:message code="settlementSettings.general"></spring:message></label>
                        <div class="col-lg-6">
                            <label class="checkbox-inline">
                                <input type="checkbox" name="general" ${cash} value="Cash">  <spring:message code="settlementSettings.cash"></spring:message>
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                    
                        <label class="col-sm-3 control-label col-lg-3" for="inputSuccess"><spring:message code="settlementSettings.media"></spring:message></label>
                        <div class="col-lg-3">
                            <c:forEach items="${baseMedia}" var="media" begin="0" step="2">
                                <div class="checkbox">
                                <label>
                                    <input type="checkbox" name="media" value="${media.key }" <c:if test="${countryDefault[media.key] ne null}">checked</c:if> >
                                     ${media.value}
                                </label>
                                </div>
                            </c:forEach>
                        </div>
                        
                        <div class="col-lg-3">
                            <c:forEach items="${baseMedia}" var="media" begin="1" step="2">
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" name="media" value="${media.key }" <c:if test="${countryDefault[media.key] ne null}">checked</c:if>>
                                     ${media.value}
                                </label>
                            </div>
                            </c:forEach>
                        </div>
                   
                    </div>
                    
                    <div class="form-group">
                        <label class="col-sm-3 control-label col-lg-3" for="inputSuccess"><spring:message code="settlementSettings.adjustments"></spring:message></label>
                        <div class="col-lg-3">
                        
                           <c:forEach items="${baseAdjustments}" var="adjustments" begin="0" step="2">
                       
                            <div class="checkbox">
                                <label>
                                   <input type="checkbox" name="adjustments" value="${adjustments.key } " <c:if test="${adjuestDefault[adjustments.key] ne null}">checked</c:if>>
                                     ${adjustments.value}
                                </label>
                            </div>
                               <c:if test="${ adjustments.key==2000}">
                                <div id="reasonsDiv" style="display:none">
                                 <label>
                                  reasons
                                </label>
                                  <input id="reasons" name="reasons" type="text" class="tags" value="${reasons }" />
                                  </div>
                            </c:if>
                            
                            <c:if test="${ adjustments.key==105}">
                             <div id="remarksDiv" style="display:none">
                                 <label>
                                  remarks
                                </label>
                                  <input id="remarks" name="remarks" type="text" class="tags" value="${remarks}" />
                              </div>
                            </c:if>
                            
                            </c:forEach>
                        </div>
                          <div class="col-lg-3">
                          
                   
                           <c:forEach items="${baseAdjustments}" var="adjustments" begin="1" step="2">
                            <div class="checkbox">
                                <label>
                                   <input type="checkbox" name="adjustments" id="adjustments" value="${adjustments.key }" <c:if test="${adjuestDefault[adjustments.key] ne null}">checked</c:if>>
                                     ${adjustments.value}
                                </label>
                            </div>
                            <c:if test="${ adjustments.key==2000}">
                            
                               <div id="reasonsDiv" style="display:none">
                                 <label>
                                  reasons
                                </label>
                                  <input id="reasons" name="reasons" type="text" class="tags" value="${reasons}" />
                                 </div>
                            </c:if>
                            <c:if test="${ adjustments.key==105}">
                                 <div id="remarksDiv" style="display:none">
                                 <label>
                                  remarks
                                </label>
                                  <input id="remarks" name="remarks" type="text" class="tags" value="${remarks}" />
                                 </div>
                            </c:if>

                            </c:forEach>

                        </div>
                    </div>
                    
                  <div class="form-group">
                        <label class="col-sm-3 control-label col-lg-3" for="inputSuccess"><spring:message code="settlementSettings.other"></spring:message></label>
                        <div class="col-lg-6">
                           
                              <input id="otherPayment" name="otherPayment" type="text" class="tags" value="${otherPayment}" />
 
                        </div>
                    </div>
                    
                </form>
                       
                </div>
                </section>
            </div>
        </div>
        <!-- page end-->
    </section>
</section>
<!--main content end-->
<script src="${staticPath}/js/jquery-tags-input/jquery.tagsinput.js"></script>
<script type="text/javascript" src="${staticPath}/js/jquery.validate.min.js"></script>
<script src="${staticPath}/js/scripts.js"></script>
<script>
	jQuery(document).ready(function() {
	
			//左边菜单展开
			$("#rtManagerMenuA").addClass("active");
			$("#rtManagerSub").css('display', 'block');
			$("#settlementResLi").addClass("active");
	        
			  $('input[type="checkbox"][name="adjustments"]:checked').each(
                      function() {
                    	  if($(this).val()==2000){
                    			 $("#reasonsDiv").show();
                    	  }else if($(this).val()==105){
                    		  $("#remarksDiv").show(); 
                    	  }  
                      }
          );
			
			
	        $('#reasons').tagsInput({
	            width: '250',
	            defaultText:'reasons',
	            onChange: function(elem, elem_tags)
	            {
	                var languages = [];
	                $('.tag', elem_tags).each(function()
	                {
	                    if($(this).text().search(new RegExp('\\b(' + languages.join('|') + ')\\b')) >= 0)
	                        $(this).css('background-color', 'yellow');
	                });
	            }
	        });
	        $('#remarks').tagsInput({
	            width: '250',
	            defaultText:'remarks',
	            onChange: function(elem, elem_tags)
	            {
	                var languages = [];
	                $('.tag', elem_tags).each(function()
	                {
	                    if($(this).text().search(new RegExp('\\b(' + languages.join('|') + ')\\b')) >= 0)
	                        $(this).css('background-color', 'yellow');
	                });
	            }
	        });
	        
	        $('#otherPayment').tagsInput({
	            width: '250',
	            defaultText:'other',
	            onChange: function(elem, elem_tags)
	            {
	                var languages = [];
	                $('.tag', elem_tags).each(function()
	                {
	                    if($(this).text().search(new RegExp('\\b(' + languages.join('|') + ')\\b')) >= 0)
	                        $(this).css('background-color', 'yellow');
	                });
	            }
	        });
	        
	        
	        
	        
	        $("#saveBut").click(function(){
	        	$("#settlementForm").submit();
	        });
	        
	        //显示隐藏
	        
	        $('input[type="checkbox"][name="adjustments"]').click(function(){
	        	var checkdeVal="";
	            $('input[type="checkbox"][name="adjustments"]:checked').each(
	                      function() {
	                    	  if($(this).val()==2000){
	                    			 $("#reasonsDiv").show();
	                    	  }else if($(this).val()==105){
	                    		  $("#remarksDiv").show(); 
	                    	  }  
	                    	  checkdeVal=checkdeVal+"|"+$(this).val();         
	                      }
	          );
	            
	           if(!(checkdeVal.indexOf("2000") > 0)){
	        		 $("#reasonsDiv").hide();
	           }
               if(!(checkdeVal.indexOf("105") > 0)){
	        	   $("#remarksDiv").hide(); 
	        	   
	           }
	        	
	        })
	  
	        
	        
	
	});
</script>

</body>
</html>
