var nEditing = null;
//全局新增按钮
var addFlag=true;
//隐藏表格字段
function showTable(){
		$("#editable-sample tr td:nth-child(1)").hide(); 
		$("#editable-sample tr td:nth-child(6)").hide();  
		$("#editable-sample tr td:nth-child(7)").hide();  
		$("#editable-sample tr td:nth-child(8)").hide();  
		$("#editable-sample tr td:nth-child(9)").hide();  
		$("#editable-sample tr td:nth-child(10)").hide();  
		$("#editable-sample tr td:nth-child(11)").hide();  
		$("#editable-sample tr td:nth-child(12)").hide();  
		$("#editable-sample tr td:nth-child(13)").hide();  
		$("#editable-sample tr td:nth-child(14)").css('width','30%');
		$("#editable-sample tr td:nth-child(15)").hide();
}

function showTableEach(){
	$("#editable-sample tr").each(function(){
		$("td:eq(0)",this).hide();
		$("td:eq(5)",this).hide();
		$("td:eq(6)",this).hide();
		$("td:eq(7)",this).hide();
		$("td:eq(8)",this).hide();
		$("td:eq(9)",this).hide();
		$("td:eq(10)",this).hide();
		$("td:eq(11)",this).hide();
		$("td:eq(12)",this).hide();
		$("td:eq(13)",this).css('width','30%');
		$("td:eq(14)",this).hide();
	}
	); 
}


var EditableTable = function () {
    return {
        //main function to initiate the module
        init: function () {
            function restoreRow(oTable, nRow) {
                var aData = oTable.fnGetData(nRow);
                var jqTds = $('>td', nRow);
                for (var i = 0, iLen = jqTds.length; i < iLen; i++) {
                     oTable.fnUpdate(aData[i], nRow, i, false);
                }
                oTable.fnDraw();
            }

            function editRow(oTable, nRow) {
              //  var aData = oTable.fnGetData(nRow);
                var jqTds = $('>td', nRow);
                jqTds[16].innerHTML = '<img src="/static/images/details_open.png">';
                jqTds[17].innerHTML = ' <button type="button" class="btn btn-info"><i class="fa fa-trash-o"></i></button>';
            }

            var oTable = $('#editable-sample').dataTable({
            	bFilter:false,
            	bLengthChange:false,
            	iDisplayLength:5
            });

            jQuery('#editable-sample_wrapper .dataTables_filter input').addClass("form-control medium"); // modify table search input
            jQuery('#editable-sample_wrapper .dataTables_length select').addClass("form-control xsmall"); // modify table per page dropdown
            $('#editable-sample_new').click(function (e) {
            	if(addFlag==true){
                e.preventDefault();
                var aiNew = oTable.fnAddData(['', '']);
                var nRow = oTable.fnGetNodes(aiNew[0]);
                editRow(oTable, nRow);
                showTable(); 
                $('#editable-sample tbody td img:first').trigger("click");
                addFlag=false;
            	}
            });


        }

    };

}();

//显示详情
function fnFormatDetails(oTable, nTr) {
	
    var aData = oTable.fnGetData(nTr);
    var temId = aData[0] == null ? '' : aData[0];
   // var tempRestaurant = aData[1] == null ? '' : aData[1];
    var tempEmail = aData[2] == null ? '' : aData[2];
   // var tempAddress = aData[3] == null ? '' : aData[3];
    var tempTel = aData[4] == null ? '' : aData[4];
    var tempCountry= aData[5] == null ? '' : aData[5];
    var tempState = aData[6] == null ? '' : aData[6];
    var tempCity = aData[7] == null ? '' : aData[7];
    var tempPostal = aData[8] == null ? '' : aData[8];
    var tempAddress1 = aData[9] == null ? '' : aData[9];
    var tempAddress2 = aData[10] == null ? '' : aData[10];
    var tempDescription = aData[11] == null ? '' : aData[11];
    var restaurant = aData[12] == null ? '' : aData[12];
    var type = aData[14] == null ? '' : aData[14];
    var key = aData[15] == null ? '' : aData[15];
    $("#checkedOption option[value='"+type+"']").attr("selected", "selected"); 
    var selectRes=$("#selectRes").html();
    var disables="";

    //国家  省份  城市下来列表组装
    if(tempCountry==""){
    	tempCountry="SELECT COUNTRY";
    }
    if(tempState==""){
        // disables="disabled";
    }
  
    var  contury='<select class="form-control "  style="width:203px;"  onchange="set_country(this,state,city)"  name="country"><option value="'+tempCountry+'" selected="selected">'+tempCountry+'</option>';
         contury+=getRegions(this);
         contury+='</select>';
        
    var state='<select name="state" class="form-control " style="width:203px;" '+disables+' onchange="set_city_state(this,city)"> ';
    if(tempState!=""){
        state+='<option value="'+tempState+'" selected="selected">'+tempState+'</option>';
   }  
        state+='</select>';
    var city='<select name="city"  class="form-control" '+disables+' style="width:203px;"  onchange="print_city_state(state,this)">';
	       
	 if(tempCity!=""){
		 city+='<option value="'+tempCity+'" selected="selected">'+tempCity+'</option>';
	   }  
      city+='</select>';
    
    var sOut ='<form id="form_table_'+temId+'"><table border="0"  colspan="8" style="padding-left:50px;" id="table_'+temId+'" >';
    sOut+='<tr><td><input type="hidden" class="form-control" value="' + temId + '"></td></td></tr>';
    sOut +='<tr><td>Restaurant:</td><td><input type="text" class="form-control small" name="restaurant" value="' + restaurant + '"></td>';
    sOut += '<td>Email:</td> <td><input type="email" name="email" class="form-control small" value="' + tempEmail + '"></td><td>Tel:</td><td><input type="text" name="tel" class="form-control small" value="' + tempTel + '"></td></tr>';
    sOut += '<tr><td>Address1:</td> <td><input type="text" class="form-control name="address1" small" value="' + tempAddress1 + '"></td>';
    sOut += '<td>Address2:</td> <td><input type="text" class="form-control small" value="' + tempAddress2 + '"></td><td>Postal:</td> <td><input type="text" class="form-control small"value="' + tempPostal + '"></tr>';
    sOut += '<tr><td>Country:</td> <td>'+contury+'</td>';
    sOut += '<td>State:</td> <td>'+state+'</td><td>City:</td> <td>'+city+'</td></tr>';
    sOut += '<tr><td>Description:</td> <td><input type="text" class="form-control small"value="' + tempDescription + '"></td><td>Type:</td><td>'+selectRes+'</td>';
    sOut += '<td><a class="edit" href=""><button type="button" data="' + temId + '" class="btn btn-info"><i class="fa fa-save"></i>Save</button></a></td></tr>';
    sOut += '<tr><td><input type="hidden" class="form-control" value="' + key + '"></td></tr></table></form>';
    return sOut;
}

$(document).ready(function () {
    var nCloneTh = document.createElement('th');
    nCloneTh.innerHTML = 'Edit';
    var nCloneTd = document.createElement('td');
    nCloneTd.innerHTML = '<img src="/static/images/details_open.png">';
    nCloneTd.className = "center";

    $('#editable-sample thead tr').each(function () {

        this.insertBefore(nCloneTh, this.childNodes[this.childNodes.length - 2]);
    });
    $('#editable-sample tbody tr').each(function () {
        this.insertBefore(nCloneTd.cloneNode(true), this.childNodes[this.childNodes.length - 2]);
    });

    /*
     * Initialse DataTables, with no sorting on the 'details' column
     */
    var oTable = $('#editable-sample').dataTable({
    	bFilter:false,
    	bLengthChange:false,
    	iDisplayLength:5,
        "aoColumnDefs": [
            { "bSortable": false, "aTargets": [ 0 ] }
        ],
        "aaSorting": [
            [1, 'asc']
        ]
    });

    /* Add event listener for opening and closing details
     * Note that the indicator for showing which row is open is not controlled by DataTables,
     * rather it is done here
     */
    $(document).on('click', '#editable-sample tbody td img', function () {
        var nTr = $(this).parents('tr')[0];
        if (oTable.fnIsOpen(nTr)) {
            /* This row is already open - close it */
            this.src = "/static/images/details_open.png";
            oTable.fnClose(nTr);
        }
        else {
            /* Open this row */
            this.src = "/static/images/details_close.png";
            
            oTable.fnOpen(nTr, fnFormatDetails(oTable, nTr), 'details');
            
            //编辑时确定是哪一行数据
            nEditing = nTr;
        }
        //设置显示区域的长度
        $(".details").attr("colspan","8");
    });


    //保存具体的某一行数据
    function saveRow(oTable, nRow,tableId) {
    	//取值
    	var id =$("#"+tableId+" input:eq(0)").val();
        var email = $("#"+tableId+" input:eq(2)").val();
        var tel = $("#"+tableId+" input:eq(3)").val();
        var postal = $("#"+tableId+" input:eq(6)").val();
        var address1 =$("#"+tableId+" input:eq(4)").val();
        var address2 = $("#"+tableId+" input:eq(5)").val();
        var description =$("#"+tableId+" input:eq(7)").val();
        var restaurant =$("#"+tableId+" input:eq(1)").val();
        var key =$("#"+tableId+" input:eq(8)").val();
        var address = address1+' '+address2;
        var restauranthref = '<a class="active" href="/dashboard/index"><span>'+restaurant+'</span></a>';
        var type=$("#"+tableId+" select:eq(3)").val();
        var country= $("#"+tableId+" select:eq(0)").val();
        var state =$("#"+tableId+" select:eq(1)").val();
        var city = $("#"+tableId+" select:eq(2)").val();
        
        //var jqInputs = $('input', nRow);

		var validator=$("#form_"+tableId).validate({
			 rules:{
				 restaurant:{
					 required:true 
				 },
				   email:
				      {
				        required: true,
				        email: true
				      },
				      tel:{
				    	  required: true
				      },
				      country:{ required: true},
				      state:{ required: true},
				      city:{ required: true},
				      address1:{ required: true}
				      
			    },
            messages: {
                email: "Please enter a valid email"
                	
            }
        });
 
		var check = validator.form();
		
		if(check){
	        // ajax 请求数据录入
	    	if (id== '') {
	    		//数据验证
	    		addFlag=true;
	    		$.ajax({
	    			type : "post",
	    			url : "/restaurant/checkRestaurant",
	    			data : {
	    				id:0,restaurantName:restaurant
	    			},
	    			dataType : "json",
	    			success : function(data) {
	        			if(data.flag==false){
	        				 bootbox.alert('The company is having the same restaurant, please replace other name!');
	        			     return ;
	        			}else{
	        	    		$.ajax({
	        	    			type : "post",
	        	    			url : "/restaurant/addRestaurant",
	        	    			data : {
	        	    				restaurantName:restaurant,description:description,email:email,address1:address1,address2:address2,telNo:tel,country:country,state:state,city:city,postalCode:postal,type:type
	        	    			},
	        	    			dataType : "json",
	        	    			success : function(data) {
	        	    	        if(data.flag!=0){

	            	    			oTable.fnUpdate(data.flag, nRow, 0, false);
	        	    	            oTable.fnUpdate(restauranthref, nRow, 1, false);
	        	    	            oTable.fnUpdate(email, nRow, 2, false);
	        	    	            oTable.fnUpdate(address, nRow, 3, false);
	        	    	            oTable.fnUpdate(tel, nRow, 4, false);
	        	    	            oTable.fnUpdate(country, nRow, 5, false);
	        	    	            oTable.fnUpdate(state, nRow, 6, false);
	        	    	            oTable.fnUpdate(city, nRow, 7, false);
	        	    	            oTable.fnUpdate(postal, nRow, 8, false);
	        	    	            oTable.fnUpdate(address1, nRow, 9, false);
	        	    	            oTable.fnUpdate(address2, nRow, 10, false);
	        	    	            oTable.fnUpdate(description, nRow, 11, false);
	        	    	            oTable.fnUpdate(restaurant, nRow, 12, false);
	        	    	            oTable.fnUpdate(type, nRow, 14, false);
	        	    	            oTable.fnUpdate(key, nRow, 15, false);
	        	    	            oTable.fnUpdate('<img src="/static/images/details_close.png">', nRow, 16, false);
	            	    	        oTable.fnUpdate('<button type="button" data="'+data.flag+'" class="btn btn-info"><i class="fa fa-trash-o"></i></button>', nRow, 17, false);        	    	           
	        	    		        closeDial();
	        						
	        					}
	        					
	        	    
	        	    			},
	        	    			error : function() {
	        	    				bootbox.alert('The new failure!');
	        	    			}
	        	    		});
						
					}
					
	    			
	    			},
	    			error : function() {
	    				bootbox.alert('Validation error!');
	    			}
	    		});
	    		
	    		//
	    	

	    	} else {
	    		//修改数据验证
	    		
	    		$.ajax({
	    			type : "post",
	    			url : "/restaurant/checkRestaurant",
	    			data : {
	    				id:id,restaurantName:restaurant
	    			},
	    			dataType : "json",
	    			success : function(data) {
	        			if(data.flag==false){
	        				 bootbox.alert('The company is having the same restaurant, please replace other name!');
	        			     return ;
	        			}else{
	        	    		$.ajax({
	        	    			type : "post",
	        	    			url : "/restaurant/updateRestaurant",
	        	    			data : {
	        	    				id:id,restaurantName:restaurant,description:description,email:email,address1:address1,address2:address2,telNo:tel,country:country,state:state,city:city,postalCode:postal,type:type
	        	    			},
	        	    			dataType : "json",
	        	    			success : function(data, index) {
	        	    				  
	        		    	        if(data.flag=="0"){

	        		    	            oTable.fnUpdate(id, nRow, 0, false);
	        		    	            oTable.fnUpdate(restauranthref, nRow, 1, false);
	        		    	            oTable.fnUpdate(email, nRow, 2, false);
	        		    	            oTable.fnUpdate(address, nRow, 3, false);
	        		    	            oTable.fnUpdate(tel, nRow, 4, false);
	        		    	            oTable.fnUpdate(country, nRow, 5, false);
	        		    	            oTable.fnUpdate(state, nRow, 6, false);
	        		    	            oTable.fnUpdate(city, nRow, 7, false);
	        		    	            oTable.fnUpdate(postal, nRow, 8, false);
	        		    	            oTable.fnUpdate(address1, nRow, 9, false);
	        		    	            oTable.fnUpdate(address2, nRow, 10, false);
	        		    	            oTable.fnUpdate(description, nRow, 11, false);
	        		    	            oTable.fnUpdate(restaurant, nRow, 12, false);
	        		    	            oTable.fnUpdate(type, nRow, 14, false);
	        		    	            oTable.fnUpdate(key, nRow, 15, false);
	        		    	            oTable.fnUpdate('<img src="/static/images/details_close.png">', nRow, 16, false);
	        		    	            oTable.fnUpdate('<button type="button" data="'+id+'" class="btn btn-info"><i class="fa fa-trash-o"></i></button>', nRow, 17, false);
	        		    	           
	        		    		        closeDial();
	        	    					//bootbox.alert('修改成功！');
	        	    				}
	        	    				

	        	    			},
	        	    			error : function() {
	        	    				bootbox.alert('Modify the failure！！');
	        	    			}
	        	    		});
						
						
					}
					
	    			
	    			},
	    			error : function() {
	    				bootbox.alert('Validation error！！');
	    			}
	    		});
	    		

	    	}
	        
			
			
		}
        

        //保存后关闭展开的信息
    }
    
	 $('#editable-sample th').click(function(){
		 showTableEach(); 
	 })
    
    
    function closeDial(){
    	 $('#editable-sample tbody td img').each(function (e, i) {
             var src = i.src.substring(i.src.length - 9, i.src.length);
             if (src == 'close.png') {
                 $('#editable-sample tbody td img:eq("' + e + '")').trigger("click");
             }
         });
    };

    $(document).on('click', '#editable-sample tbody td button', function (e) {
    	
        e.preventDefault();

        var id=$(this).attr("data");
        
       
        var nTr = $(this).html();
        var nRow = $(this).parents('tr')[0];
        if (nTr.trim() == '<i class="fa fa-trash-o"></i>') {
        	
            bootbox.confirm("Are you sure?", function(result) {
                if (result) {
                	 if(id==undefined){
                		  oTable.fnDeleteRow(nRow);
                		  addFlag=true;
                	 }else{
                         $.ajax({
                 			type : "post",
                 			url : "/restaurant/delRestaurant",
                 			data : {
                 				id:id
                 			},
                 			dataType : "json",
                 			success : function(data, index) {
             	    	        if(data.flag=="0"){
             	    	        	  oTable.fnDeleteRow(nRow);
             	    	        	  showTableEach();
                 				}
                 			},
                 			error : function() {
                 				bootbox.alert('Delete failed！');

                 			}
                 		}); 
                		 
                	 };
                }               
            });
            

            
        } else {
        	
        	 var tableId="table_"+id;
            saveRow(oTable, nEditing,tableId);
        }
    });
});