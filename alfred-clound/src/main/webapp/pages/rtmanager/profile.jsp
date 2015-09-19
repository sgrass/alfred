<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/pages/inc/common.jsp"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="ThemeBucket">
<script type="text/javascript" src="${staticPath}/js/city_state.js"></script>
<title>RT Profile</title>
</head>
<body>
	<!--main content start-->
<section id="main-content">
     <section class="wrapper">
<div class="row" style="width:60%; margin:0 auto;"></div>
<div class="col-md-8">
    <section class="panel">
        <div class="panel-body">
            <!--  <div class="position-center">-->
            <form class="form-horizontal" method="post"  id="contact-form" action="${contextPath}/profile/updateRestaurant"  enctype="multipart/form-data">
                <div class="form-group">
                    <label class="col-lg-3 control-label"><spring:message code="profile.storeType"></spring:message></label>
                    <div class="col-lg-9">
                        <select class="form-control" disabled>
                             <c:if test="${restaurant.type==1 }" >
                              <option value="1"><spring:message code="profile.res"></spring:message></option>
                             </c:if>
                            <c:if test="${restaurant.type==2 }" >
                                <option value="2"><spring:message code="profile.coffee"></spring:message></option>
                             </c:if>
                              <c:if test="${restaurant.type==3 }" >
                           <option value="3"><spring:message code="profile.food"></spring:message></option>
                             </c:if>
                              <c:if test="${restaurant.type==4 }" >
                              <option value="4"><spring:message code="profile.bar"></spring:message></option>
                             </c:if> 
                        </select>
                    </div>
                </div>
                 <input type="hidden" name="id" value="${restaurant.id }"/>
                <div class="form-group">
                    <label for="inputStoreName" class="col-lg-3 col-sm-3 control-label"><spring:message code="profile.store"></spring:message></label>
                    <div class="col-lg-9">
                        <input type="text" name="restaurantName" class="form-control" id="inputStoreName" value="${restaurant.restaurantName }" placeholder="Company Name">
                    </div>
                </div>
                    <div class="form-group">
                    <label class="col-lg-3 col-sm-2 control-label"><spring:message code="profile.state"></spring:message></label>
                    <div class="col-lg-9">
                         <select class="form-control"  onchange="set_country(this,state,city)"  name="country">
                            <option value="${restaurant.country }" selected="selected">${restaurant.country }</option>
                            <script type="text/javascript">setRegions(this);</script>
                      </select>
                    </div>
                </div>
                <div class="form-group">
                  
                    <label for="inputState" class="col-lg-3 col-sm-3 control-label"><spring:message code="profile.country"></spring:message></label>

                    <div class="col-lg-4">
                       <select name="state" class="form-control" disabled="disabled" onchange="set_city_state(this,city)">
                        <option value="${restaurant.state }" selected="selected">${restaurant.state }</option>
                       </select>
                    </div>
                    <label for="inputCity" class="col-lg-1 col-sm-1 control-label"><spring:message code="profile.city"></spring:message></label>
                    <div class="col-lg-4">
                       <select name="city" id="inputCity" class="form-control"  disabled="disabled" onchange="print_city_state(state,this)">
                       <option value="${restaurant.city }" selected="selected">${restaurant.city }</option>
                       
                       </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputZip" class="col-lg-3 col-sm-3 control-label"><spring:message code="profile.post"></spring:message></label>
                    <div class="col-lg-4">
                        <input type="text" name="postalCode" value="${restaurant.postalCode }" class="form-control" id="inputZip" placeholder="Post">
                    </div>
                    <label for="inputPhone" class="col-lg-1 col-sm-1 control-label"><spring:message code="profile.phone"></spring:message></label>
                    <div class="col-lg-4">
                        <input type="tel" name="telNo" value="${restaurant.telNo }" class="form-control" id="inputPhone" placeholder="Phone" required/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputStreetAddress" class="col-lg-3 col-sm-3 control-label"><spring:message code="profile.address1"></spring:message></label>
                    <div class="col-lg-9">
                        <input type="text"  name="address1"  value="${restaurant.address1 }" class="form-control" id="inputStreetAddress" placeholder="Street Address">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputAddress" class="col-lg-3 col-sm-3 control-label"><spring:message code="profile.address2"></spring:message></label>
                    <div class="col-lg-9">
                        <input type="text"   name="address2"  value="${restaurant.address2 }" class="form-control" id="inputAddress"placeholder="inputAddressLine2">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputEmail" class="col-lg-3 col-sm-3 control-label"><spring:message code="profile.email"></spring:message></label>
                    <div class="col-lg-9">
                        <input type="email"  name="email"   value="${restaurant.email }" class="form-control" id="inputEmail" required placeholder="Email"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputWebSite" class="col-lg-3 col-sm-3 control-label"><spring:message code="profile.website"></spring:message></label>
                    <div class="col-lg-9">
                        <input type="text" name="website" value="${restaurant.website }" class="form-control" id="inputWebSite" placeholder="WebSite">
                    </div>
                </div>
                <div class="center" style="display:none">
                 <input type="text" name="addressPrint" id="addressPrint">
                 <input type="text" name="restaurantPrint" id="restaurantPrint">
                
             
                 </div>
                 <div class="form-group">
                    <label for="inputLog0" class="col-lg-3 col-sm-3 control-label">Logo</label>
                    <div class="col-lg-9">
                       <input type="file" class="form-control small" name="imgFile"/>
                       <c:if test="${restaurant.logoUrl==null}">
                       <c:set value="none" var="displaystatus"></c:set>
                       </c:if>
                       <div style="display:${displaystatus}">
                   
                       <a href="${restaurant.logoUrl}" target="blank">
                       <img   id="imgShow" src="${restaurant.logoUrl}" width="50px" height="50px">
                       </a>
                       <input type="button" class="btn" id="delImages"  value="X">
                       </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-offset-3 col-lg-3">
                        <div class="checkbox">
                            <label><spring:message code="profile.mobile"></spring:message><input id="checkbox1" value="${restaurant.qrPayment }"   name="checkboxstatus" type="checkbox"></label>
                        </div>
                    </div>
                    <div class="col-lg-offset-3 col-lg-3">
                        <button type="button" id="saveBut" class="btn btn-danger"><spring:message code="profile.saveBut"></spring:message></button>
                    </div>
                </div>
            </form>
        </div>
    </section>
</div>
<div class="col-md-4">
    <div class="row mini-stat-o clearfix">
        <div class="col-md-12 " id="getText">
            <c:if test="${restaurant.logoUrl==null}">
            <c:set value="none" var="displaystatus"></c:set>
            </c:if>
            <div class="row text-center" style="display:${displaystatus}">
            
            <a href="${restaurant.logoUrl}" target="blank">
            <img  id="imgPrin" src="${restaurant.logoUrl}" width="50px" height="50px"></a>
            
            </div>

            <div class="row text-center"><b><span id="spanStoreName"></span></b></div>
            <div class="row text-center"><span id="spanStreetAddress"></span></div>
            <div class="row text-center">
	            <span id="spanStreetline2"></span>
	            <span id="spanCity"></span> 
	            <span id="spanZip"></span>
            </div>
            <div class="row text-center"><spring:message code="profile.phone"></spring:message>:<span id="spanPhone"></span></div>
            <div class="row text-center"> <spring:message code="profile.email"></spring:message>:<span id="spanEmail"></span></div>
            <div class="row text-center"> <spring:message code="profile.website"></spring:message>:<span id="spanWebsite"></span></div>
        </div>
    </div>
    <div class="row  mini-stat-o clearfix">
        <div class="col-md-12">
            <div class="row ">
                <div class="col-md-4">
                   <spring:message code="profile.op"></spring:message>:Cashier
                </div>
                <div class="col-md-4">
                    <spring:message code="profile.pop"></spring:message>:BOO1
                </div>
                <div class="col-md-4">
                    <spring:message code="profile.date"></spring:message>:01/04/2014
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <spring:message code="profile.billNo"></spring:message>:0A0103424
                </div>
                <div class="col-md-4 col-md-offset-2">
                    <spring:message code="profile.time"></spring:message>:23:00
                </div>

            </div>
            <div class="row">

                <div class="col-md-6">
                    <strong><spring:message code="profile.table"></spring:message>:11</strong>
                </div>
                <div class="col-md-4 col-md-offset-2">
                    <strong> <spring:message code="profile.pax"></spring:message>:2</strong>
                </div>
            </div>
        </div>

    </div>
    <div class="row mini-stat-o clearfix">
        <div class="col-md-12">
            <div class="row">
                <div class="col-md-6"><spring:message code="profile.itemName"></spring:message></div>
                <div class="col-md-2"><spring:message code="profile.qty"></spring:message></div>
                <div class="col-md-2"><spring:message code="profile.amount"></spring:message></div>
            </div>
            <div class="row">
                <div class="col-md-6">buffet</div>
                <div class="col-md-2">1</div>
                <div class="col-md-2">$33.80</div>
            </div>
            <div class="row">
                <div class="col-md-6">Beer mug</div>
                <div class="col-md-2">2</div>
                <div class="col-md-2">$40.00</div>
            </div>
            <div class="row">
                <div class="col-md-6">Lemon tea</div>
                <div class="col-md-2">2</div>
                <div class="col-md-2">$4.00</div>
            </div>
        </div>

    </div>
    <div class="row mini-stat-o">
        <div class="col-md-12">
            <div class="row text-right">
                <div class="col-md-6"><spring:message code="profile.subTotal"></spring:message></div>
                <div class="col-md-6">$77.80</div>
            </div>
            <div class="row text-right">
                <div class="col-md-6"><spring:message code="profile.discount"></spring:message></div>
                <div class="col-md-6">-$10.00</div>
            </div>
            <div class="row text-right">
                <div class="col-md-6"><spring:message code="profile.serviceCharge"></spring:message>(10%)</div>
                <div class="col-md-6">$6.78</div>
            </div>
            <div class="row text-right">
                <div class="col-md-6"><spring:message code="profile.gst"></spring:message>(7%)</div>
                <div class="col-md-6">$5.22</div>
            </div>
            <div class="row text-right">
                <div class="col-md-6"><spring:message code="profile.grand"></spring:message></div>
                <div class="col-md-6">$79.8O</div>
            </div>
        </div>

    </div>
    <div class="row mini-stat-o">
        <div class="col-md-12">
            <div class="row text-right">
                <div class="col-md-4"><spring:message code="profile.cash"></spring:message></div>
                <div class="col-md-8">$100.OO</div>
            </div>
            <div class="row text-right">
                <div class="col-md-4"><spring:message code="profile.change"></spring:message></div>
                <div class="col-md-8">$20.00</div>
            </div>
            <div class="row text-right">
               <div id="ihatetheqrcode" class="text-center" style="display:none">
               <a href="#"><img src="${staticPath}/images/qr_payment.jpg" width="50px" height="50px"></a>
               </div>
            </div>
            <div class="row text-center">
              <spring:message code="profile.thank"></spring:message>
            </div>
            <div class="row text-center">
                 <spring:message code="profile.please"></spring:message>
            </div>
        </div>
    </div>
</div>
	</section>
	</section>
	<div id="txtregion"></div>
    <div id="txtplacename"></div>
	 <script type="text/javascript" src="${staticPath}/js/scripts.js"></script>
	 <script type="text/javascript" src="${staticPath}/js/bootbox.js"></script>
	 <script type="text/javascript" src="${staticPath}/js/jquery.validate.min.js"></script>
	 <script src="${staticPath}/jquery.i18n.properties-min-1.0.9.js"></script>
     <script src="${staticPath}/jquery.cookie.js"></script>
	 <script type="text/javascript">
	 
	    $("#rtManagerMenuA").addClass("active");
		$("#rtManagerSub").css('display', 'block');
		$("#restInfo").addClass("active");
		
	 
	 
    $(document).ready(function () {
    	setTemplateValue();
    	 if($("#checkbox1").val()==1){
    		$("#checkbox1").attr("checked",'true');
    		$("#ihatetheqrcode").show();
    	}; 
        //当光标离开时触发
       var validator= $('.form-horizontal').validate({
             rules: {
                inputEmail: {
                    required: true,
                    email: true
                },
                inputZip: {
                    required: true,
                    number: true,
                    rangelength: [3, 5]
                },
                inputPhone: {
                    required: true,
                    number: true

                },
                website:{
                	url:true     
                }
            },
            messages: {
                inputEmail: "Please enter a valid email address",
                inputPhone: {
                    required: "You must enter your phone number",
                    number: "Phone number must contain digits only"
                },inputZip:{
                    required: "You must enter your zip code",
                    number: "Zip code must contain digits only",
                    rangelength : "Zip code must have between 3 to 5 "
                }
            }
        });


        $("#saveBut").click(function(){
        	
	       var check = validator.form();
    		
	       //var textContent=($("#getText").text());
    
	       
	       
	       
	       var addressPrint=getPrinterLayout();
	       var restaurantPrint=getRestaurantPrint();
	       $("#addressPrint").val(addressPrint);
	       $("#restaurantPrint").val(restaurantPrint);
	       
    	    if (check) { 
    	    	
                $(".form-horizontal").submit();

    	    }
        	
        	
        });
        
        

          function getRestaurantPrint(){
				var storeName = $("#inputStoreName").val();
				return storeName;
        	  
          }
        
        
      
			function getPrinterLayout() {

				    var contentText="";
				    
					var phone = $("#inputPhone").val();

					var streetAddress = $("#inputStreetAddress").val();

					var address = $("#inputAddress").val();

					var city = $("#inputCity").val();

					var zip = $("#inputZip").val();

					var email = $("#inputEmail").val();

					var webSite = $("#inputWebSite").val();
					
					contentText+=streetAddress+'</br>'
					contentText+=address+' '+city+' '+zip+'</br>';
		            contentText+='Tel:'+phone+'</br>';
					contentText+='Email:'+email+'</br>';
					contentText+='WebSite:'+webSite;
					return contentText;
					

				}

				function setTemplateValue() {

					var phone = $("#inputPhone").val();
					$("#spanPhone").text(phone);

					var storeName = $("#inputStoreName").val();
					$("#spanStoreName").text(storeName);

					var storeName = $("#inputStoreName").val();
					$("#spanStoreName").text(storeName);

					var streetAddress = $("#inputStreetAddress").val();
					$("#spanStreetAddress").text(streetAddress);

					var address = $("#inputAddress").val();
					$("#spanStreetline2").text(address);

					var city = $("#inputCity").val();
					$("#spanCity").text(city);

					var zip = $("#inputZip").val();
					$("#spanZip").text(zip);

					var email = $("#inputEmail").val();
					$("#spanEmail").text(email);

					var webSite = $("#inputWebSite").val();
					$("#spanWebsite").text(webSite);
				}

				//update phone
				$("#inputPhone").blur(function() {
					var phone = $("#inputPhone").val();
					$("#spanPhone").text(phone);
				});

				//update inputStoreName
				$("#inputStoreName").blur(function() {
					var storeName = $("#inputStoreName").val();
					$("#spanStoreName").text(storeName);
				});

				//update inputStreetAddress
				$("#inputStreetAddress").blur(function() {
					var streetAddress = $("#inputStreetAddress").val();
					$("#spanStreetAddress").text(streetAddress);
				});

				//update inputAddress
				$("#inputAddress").blur(function() {
					var address = $("#inputAddress").val();
					$("#spanStreetline2").text(address);
				});

				//update inputCity
				$("#inputCity").blur(function() {
					var city = $("#inputCity").val();
					$("#spanCity").text(city);
				});

				//update inputZip
				$("#inputZip").blur(function() {
					var zip = $("#inputZip").val();
					$("#spanZip").text(zip);
				});
				//update inputEmail
				$("#inputEmail").blur(function() {

					var email = $("#inputEmail").val();
					$("#spanEmail").text(email);
				});

				//update inputWebSite
				$("#inputWebSite").blur(function() {
					var webSite = $("#inputWebSite").val();
					$("#spanWebsite").text(webSite);
				});

			})

			$("#checkbox1").click(function() {
				var thischeck = $(this);
				if (thischeck.is(':checked')) {
					//选中了显示二维码
					$("#ihatetheqrcode").show();
					$("#checkbox1").val(1);

				} else {
					//不选择不显示
					$("#ihatetheqrcode").hide();
					$("#checkbox1").val(0);

				}
			})
			
			$("#delImages").click(function(){
				var path=$("#imgPrin").attr("src");
				$("#imgPrin").hide();
				$("#imgShow").hide();
				$(this).hide();
				$.ajax({
	     			type : "post",
	     			url : "/profile/ajaxDelImage",
	     			data : {
	     				path:path
	     			},
	     			dataType : "json",
	     			success : function(data, index) {
	     			},
	     			error : function() {
	     				bootbox.alert('Modify the failure！');
	     			}
	     		});
				
			})
			
		</script>
</body>
</html>
