var nEditing = null;
//全局新增按钮
var addFlag=true;
function showTable(){
	 $("#editable-sample tr td:nth-child(1)").hide();  
	 $("#editable-sample tr td:nth-child(3)").hide();  
	 $("#editable-sample tr td:nth-child(6)").hide(); 
	 $("#editable-sample tr td:nth-child(7)").hide(); 
}

function showTableEach(){
	$("#editable-sample tr").each(function(){
		$("td:eq(0)",this).hide();
		$("td:eq(2)",this).hide();
		$("td:eq(5)",this).hide();
		$("td:eq(6)",this).hide();
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
                var aData = oTable.fnGetData(nRow);
                var jqTds = $('>td', nRow);
                jqTds[8].innerHTML = '<img src="/static/images/details_open.png">';
                jqTds[9].innerHTML = ' <button type="button" class="btn btn-info"><i class="fa fa-trash-o"></i></button>';
            }
            var oTable = $('#editable-sample').dataTable({
            	bFilter:false,
            	bLengthChange:false,
            	iDisplayLength:5
            	});

            jQuery('#editable-sample_wrapper .dataTables_filter input').addClass("form-control medium"); // modify table search input
            jQuery('#editable-sample_wrapper .dataTables_length select').addClass("form-control xsmall"); // modify table per page dropdown

            var nEditing = null;
            $('#editable-sample_new').click(function (e) {
            	if(addFlag==true){
            		 e.preventDefault();
                     var aiNew = oTable.fnAddData(['']);
                     var nRow = oTable.fnGetNodes(aiNew[0]);
                     editRow(oTable, nRow);
                     //隐藏不要显示的字段
                     showTable();      
                     $('#editable-sample tbody td img:first').trigger("click");	
                     addFlag=false;
            		
            	}
               
            });
        }
    };

}();


function fnFormatDetails(oTable, nTr) {
	var disabled='disabled'
    var aData = oTable.fnGetData(nTr);
    var id = aData[0] == null ? '' : aData[0];
    var email = aData[1] == null ? '' : aData[1];
    var password = aData[2] == null ? '' : aData[2];
    var firstName = aData[3] == null ? '' : aData[3];
    var lastName = aData[4] == null ? '' : aData[4];
    var restaurantId=aData[6] == null ? '' : aData[6];
    var selectRes=$("#selectRes").html();
    //
    if(aData[0]==null||aData[0]==''){
    	disabled='';
    }
    
   	var restaurantName='<select multiple required name="ids_'+id+'" id="ids_'+id+'" style="width:200px" class="populate">'
	  restaurantName+=selectRes;
	  restaurantName+=' </select>';
	  
    var sOut = '<form id="form_table_'+id+'"><table colspan="6" border="0" id="table_'+id+'" >';
    sOut+='  <tr><td><input type="hidden" class="form-control" value="' + id + '"></td></tr>';
    sOut += '<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Email:<input type="email" name="email" class="form-control" value="' + email + '" '+disabled+'></td>';
    sOut += '<td>Password: <input type="password" name="password" id="passowrd" class="form-control " value="' + password + '"></td><td> Confirm Password: <input type="password" name="confirm_password" class="form-control " value="' + password + '"></td></tr>';
    sOut += '<tr><td>First Name: <input type="text" class="form-control "value="' + firstName + '"></td>';
    sOut += '<td>Last Name: <input type="text" class="form-control "value="' + lastName + '"></td><td>Multiple Restaurant:'+restaurantName+'</td></tr>';
    sOut+='  <tr><td><td><a class="edit" href=""><button type="button" data="' + id + '"  class="btn btn-info"><i class="fa fa-save"></i>Save</button></a></td></tr>';
    sOut += '</table></form>';
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
    var oTable = $('#editable-sample').dataTable({bFilter:false,
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
            //下拉
            var aData = oTable.fnGetData(nTr);
            var restaurantId=aData[6] == null ? '' : aData[6];
            
            var id= aData[0] == null ? '' : aData[0];
            restaurantId= $.trim(restaurantId);

            
            var totalRes = new Array();
            if(restaurantId.indexOf(",")>=0){
            	
            	 totalRes=restaurantId.split(",");
                 if(totalRes.length>0){
                 	for(var i=0;i<totalRes.length;i++){ 
                 		 $("#ids_"+id+" option[value="+totalRes[i]+"]").attr("selected", "selected"); 
                 	}
                 }	
            	
            }else{
        		 $("#ids_"+id+" option[value="+restaurantId+"]").attr("selected", "selected"); 
            }
           
           
            $("#ids_"+id).select2();

        }
        
        //设置显示区域的长度
        $(".details").attr("colspan","6");
        
       //$("#checkedOption").select2();
        
    });
    //保存具体的某一行数据
    function saveRow(oTable, nRow,tableId) {
       //取值
       var restaurantId=$("#"+tableId+" select").val();
       var id=$("#"+tableId+" input:eq(0)").val();
       var email=$("#"+tableId+" input:eq(1)").val();
       var pasword=$("#"+tableId+" input:eq(2)").val();
       var pasword2=$("#"+tableId+" input:eq(3)").val();
       var firstName=$("#"+tableId+" input:eq(4)").val();
       var lastName=$("#"+tableId+" input:eq(5)").val();
       var userRestaurantId=$("#"+tableId+" input:eq(6)").val();
       var jqInputs = $('input', nRow);
       restaurantId= $.trim(restaurantId);
        
       var validator=$("#form_"+tableId).validate({
    	   
    	   rules: {
    		   email:{
    			   required: true,
    		       email: true 
    		   },
               password: {
                   required: true,
                   minlength: 5
               },
               confirm_password: {
                   required: true,
                   minlength: 5,
                   equalTo: "#passowrd"
               }
           },
           messages: {  
        	   password: {
               required: "Please provide a password",
               minlength: "Your password must be at least 5 characters long"
              },
           confirm_password: {
               required: "Please provide a password",
               minlength: "Your password must be at least 5 characters long",
               equalTo: "Please enter the same password as above"
           },
               email: "Please enter a valid email"
           }
       });

		var check = validator.form();
		if(check){
	        // ajax 请求数据录入
				if (id== '') {
	    		//
	    		$.ajax({
	                type: "post",
	                url: "/user/checkManagerName",
	                data: {accountName:email,id:0},
	                dataType: "json",
	                success: function(data){
	               	 if(data.flag==false){
	                         bootbox.alert('该经理已经存在');
	                         return;
	               	 }else{
	               		$.ajax({
	            			type : "post",
	            			url : "/user/addManager",
	            			data : {
	            				accountName : email,password:pasword,firstName:firstName,lastName:lastName,restaurantIds:restaurantId
	            			},
	            			dataType : "json",
	            			success : function(data) {
	            	        if(data.flag!=0){
		            			oTable.fnUpdate(data.flag, nRow, 0, false);
	            	            oTable.fnUpdate(email, nRow, 1, false);
	            	            oTable.fnUpdate(pasword, nRow, 2, false);
	            	            oTable.fnUpdate(firstName, nRow, 3, false);
	            	            oTable.fnUpdate(lastName, nRow, 4, false);
	            	            oTable.fnUpdate(userRestaurantId, nRow, 5, false);
	            	            oTable.fnUpdate(restaurantId, nRow, 6, false);
	            	            oTable.fnUpdate('<a class="active" href="/userPermission/permission?userId='+data.flag+'&userName='+email+'"><span ><i class="fa fa-user"></i></span></a>', nRow, 7, false);
	            	            oTable.fnUpdate('<img src="/static/images/details_close.png">', nRow, 8, false);
	            	            oTable.fnUpdate('<button type="button" data="'+data.flag+'"  class="btn btn-info"><i class="fa fa-trash-o"></i></button>', nRow, 9, false);
	            	    		closeDial();
	        					//bootbox.alert('新增成功！');
	        				}
	            	    
	            			},
	            			error : function() {
	            				bootbox.alert('The new failure！');
	            			}
	            		});
	               		
	               		 
	               	 };

	                },error:function(){

	                }
	            });
	    		
	    	} else {
	    		
	    		$.ajax({
	     			type : "post",
	     			url : "/user/updateManager",
	     			data : {
	     				uId:id,accountName:email,password:pasword,firstName:firstName,lastName:lastName,restaurantIds:restaurantId
	     			},
	     			dataType : "json",
	     			success : function(data, index) {
	     				if(data.flag==0){
	     					oTable.fnUpdate(id, nRow, 0, false);
	     			        oTable.fnUpdate(email, nRow, 1, false);
	     			        oTable.fnUpdate(pasword, nRow, 2, false);
	     			        oTable.fnUpdate(firstName, nRow, 3, false);
	     			        oTable.fnUpdate(lastName, nRow, 4, false);
	     			        oTable.fnUpdate(userRestaurantId, nRow, 5, false);
	     			        oTable.fnUpdate(restaurantId, nRow, 6, false);
	     			        oTable.fnUpdate('<a class="active" href="/userPermission/permission?userId='+id+'&userName='+email+'"><span ><i class="fa fa-user"></i></span></a>', nRow, 7, false);
	     			        oTable.fnUpdate('<img src="/static/images/details_close.png">', nRow, 8, false);
	     			        oTable.fnUpdate('<button type="button" data="'+id+'"  class="btn btn-info"><i class="fa fa-trash-o"></i></button>', nRow, 9, false);
	     			        closeDial();
	     					//bootbox.alert('修改成功！');
	     				}
	     			},
	     			error : function() {
	     				bootbox.alert('Modify the failure！');
	     			}
	     		});
	    		
	    	}
		}
   
    }
    //编辑成功后自动关闭
    function closeDial(){
    	 //保存后关闭展开的信息
        $('#editable-sample tbody td img').each(function (e, i) {
            var src = i.src.substring(i.src.length - 9, i.src.length);
            if (src == 'close.png') {
                $('#editable-sample tbody td img:eq("' + e + '")').trigger("click");
            }
        });
    }
    
    

    //排序后隐藏
    $("#editable-sample th").click(function(){
    	showTableEach();
    })
    
    //删除操作
    $(document).on('click', '#editable-sample tbody td button', function (e) {
        e.preventDefault();
        var id=$(this).attr("data");        
        var nTr = $(this).html();
        var nRow = $(this).parents('tr')[0];
        if (nTr.trim() == '<i class="fa fa-trash-o"></i>') {
            bootbox.confirm("Are you sure delete?", function(result) {
                if (result) {
                	if(id==undefined){
                	    oTable.fnDeleteRow(nRow);
                		addFlag=true;
                	}else{
                	     $.ajax({
                 			type : "post",
                 			url : "/user/delManager",
                 			data : {
                 				userId:id
                 			},
                 			dataType : "json",
                 			success : function(data, index) {
                 				if(data.flag==0){
                 		            oTable.fnDeleteRow(nRow);
                 		             showTableEach();
                 				}
                 			},
                 			error : function() {
                 				bootbox.alert('Delete failed！');
                 			}
                 		});
                	}
                }              
            });
        	


        } else {
        	 
            //var table=$("table table input:eq(1)").val();
            var tableId="table_"+id;
            saveRow(oTable, nEditing,tableId);
        }
    });
});