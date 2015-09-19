var nEditing = null;
var addFlag=true;
function showTable() {
	
	$("#editable-sample tr td:nth-child(1)").hide();
	$("#editable-sample tr td:nth-child(3)").hide();
	$("#editable-sample tr td:nth-child(4)").hide();
}


function showTableEach() {
	
	$("#editable-sample tr").each(function() {
		$("td:eq(0)", this).hide();
		$("td:eq(2)", this).hide();
		$("td:eq(3)", this).hide();
	});

}

var EditableTable = function() {
	return {
		init : function() {
			function restoreRow(oTable, nRow) {
				var aData = oTable.fnGetData(nRow);
				var jqTds = $('>td', nRow);
				for (var i = 0, iLen = jqTds.length; i < iLen; i++) {
					if (aData != null) {
						oTable.fnUpdate(aData[i], nRow, i, false);
					}
				}
				oTable.fnDraw();
			}

			function editRow(oTable, nRow) {
				var aData = oTable.fnGetData(nRow);
				var jqTds = $('>td', nRow);
				jqTds[7].innerHTML = '<img src="/static/images/details_open.png">';
				jqTds[8].innerHTML = ' <button type="button" class="btn btn-info"><i class="fa fa-trash-o"></i></button>';
			}

			var oTable = $('#editable-sample').dataTable({
				bFilter : false,
				bLengthChange : false,
				iDisplayLength : 5
			});

			jQuery('#editable-sample_wrapper .dataTables_filter input').addClass("form-control medium"); // modify table search
			// input
			jQuery('#editable-sample_wrapper .dataTables_length select').addClass("form-control xsmall"); 
			var nEditing = null;

			$('#editable-sample_new').click(function(e) {
				if (addFlag == true) {
					e.preventDefault();
					var aiNew = oTable.fnAddData([ '' ]);
					var nRow = oTable.fnGetNodes(aiNew[0]);
					editRow(oTable, nRow);
					// 隐藏不要显示的字段
					showTable();
					$('#editable-sample tbody td img:first').trigger("click");
					addFlag = false;

				}

			});
		}

	};

}();


function fnFormatDetails(oTable, nTr) {
	
	var disabled='disabled'
    var aData = oTable.fnGetData(nTr);
    var id = aData[0] == null ? '' : aData[0];
    var empId = aData[1] == null ? '' : aData[1];
    var password = aData[2] == null ? '' : aData[2];
    var firstName = aData[5] == null ? '' : aData[5];
    var lastName = aData[6] == null ? '' : aData[6];
    // var type=aData[3] == null ? '' : aData[3];
 
    var selectOp='<select class="form-control" id="select_'+id+'" style="width:188px"> ';
    selectOp+="<option value='10'>waiter</option>";
    selectOp+="<option value='11'>kitchen</option>";
    selectOp+=" <option value='12'>cashier</option>";
    selectOp+="<option value='13'>lobby manager</option>";
	selectOp+="</select>" ;
    if(aData[0]==null||aData[0]==''){
    	disabled='';
    }
    var sOut = '<form id="form_table_'+id+'"> <table colspan="6" border="0" id="table_'+id+'" >';
    sOut+='  <tr><td><input type="hidden" class="form-control" value="' + id + '"></td></tr>';
    sOut += '<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;EmpId:<input type="text" class="form-control" name="empId" id="empId" value="' + empId + '" '+disabled+' required ></td>';
    sOut += '<td>&nbsp;Password: <input type="password" name="password" id="passowrd" class="form-control " value="' + password + '"></td><td> Confirm Password: <input type="password" name="confirm_password" class="form-control " value="' + password + '"></td></tr>';
    sOut += '<tr><td>First Name: <input type="text" class="form-control "value="' + firstName + '"></td>';
    sOut += '<td>Last Name: <input type="text" class="form-control "value="' + lastName + '"></td><td>Employee&nbsp;&nbsp;&nbsp;&nbsp;Type:&nbsp;'+selectOp+'</td></tr>';
    sOut+='  <tr><td></td><td><a class="edit" href=""><button type="button" data="' + id + '"   class="btn btn-info"><i class="fa fa-save"></i>Save</button></a></td></tr>';
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
    var oTable = $('#editable-sample').dataTable({
    	bFilter:false,
    	bLengthChange:false,
    	iDisplayLength:5,
        "aoColumnDefs": [
            { "bSortable": false, "aTargets": [ 0 ] }
        ],
        "oLanguage": {
            "sEmptyTable": "No waiter and cashier found. Please add new account first. "
        },        
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
            var aData = oTable.fnGetData(nTr);
            var id = aData[0] == null ? '' : aData[0];
            var type=aData[3] == null ? '' : aData[3];
            $("#select_"+id+" option[value='"+type+"']").attr("selected", "selected"); 
            //编辑时确定是哪一行数据
            nEditing = nTr;
        }
        
        //设置显示区域的长度
        $(".details").attr("colspan","6");
    });


    //保存具体的某一行数据
    function saveRow(oTable, nRow,tableId) {
      
       var type=$("#"+tableId+" select").val();
       var typeName=$("#"+tableId+" select").find("option:selected").text();
       var id=$("#"+tableId+" input:eq(0)").val();
       var empId=$("#"+tableId+" input:eq(1)").val();
       var pasword=$("#"+tableId+" input:eq(2)").val();
       var firstName=$("#"+tableId+" input:eq(4)").val();
       var lastName=$("#"+tableId+" input:eq(5)").val();
       var validator=$("#form_"+tableId).validate({
    	   rules: {
    		   empId:{
    			   digits:true,
    			   minlength: 5,
    			   maxlength: 5
    		   },
               password: {
                   required: true,
                   minlength: 5,
    			   maxlength: 5
               },
               confirm_password: {
                   required: true,
                   minlength: 5,
    			   maxlength: 5,
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
           empId:{
        	   
        	   required: 'please enter digits',
               minlength: "Your e must be at least 5 characters long",   
        	   
           }
           }
       });
		var check = validator.form();
		if(check){
         // ajax 请求数据录入
     	if (id== '') {
     		//empId验证
     		$.ajax({
     			type : "post",
     			url : "/empuser/checkEmpId",
     			data : {
     	            empId:empId
     			},
     			dataType : "json",
     			success : function(data) {

     			if(data.flag==false){
     				 bootbox.alert('Employee number already exists!');
     			     return;
     			}else{
     				
     				$.ajax({
     	    			type : "post",
     	    			url : "/empuser/addEmpUser",
     	    			data : {
     	    				password:pasword,firstName:firstName,lastName:lastName,type:type,empId:empId
     	    			},
     	    			dataType : "json",
     	    			success : function(data) {
     	    	        if(data.flag==0){
         	    			oTable.fnUpdate(data.flag, nRow, 0, false);
          			        oTable.fnUpdate(empId, nRow, 1, false);
          			        oTable.fnUpdate(pasword, nRow, 2, false);
          			        oTable.fnUpdate(typeName, nRow, 4, false);
          			        oTable.fnUpdate(firstName, nRow, 5, false);
          			        oTable.fnUpdate(lastName, nRow, 6, false);
          			        oTable.fnUpdate(type, nRow, 3, false);
          			        oTable.fnUpdate('<img src="/static/images/details_close.png">', nRow, 7, false);
         	    	        oTable.fnUpdate('<button type="button" data="'+data.flag+'"  class="btn btn-info"><i class="fa fa-trash-o"></i></button>', nRow, 8, false);
         	    	        closeDial();
         				}
     	    			},
     	    			error : function() {
     	    				//alert('新增出错！');
     	    			}
     	    		});
     				
     			}

     			},
     			error : function() {
     				bootbox.alert('Validation error！');
     			}
     		});
     		
     		

     	} else {
     		$.ajax({
     			type : "post",
     			url : "/empuser/updateEmpUser",
     			data : {
     				id:id,password:pasword,firstName:firstName,lastName:lastName,type:type
     			},
     			dataType : "json",
     			success : function(data, index) {
     				if(data.flag==0){
     			        oTable.fnUpdate(id, nRow, 0, false);
     			        oTable.fnUpdate(empId, nRow, 1, false);
     			        oTable.fnUpdate(pasword, nRow, 2, false);
      			        oTable.fnUpdate(typeName, nRow, 4, false);
     			        oTable.fnUpdate(firstName, nRow, 5, false);
     			        oTable.fnUpdate(lastName, nRow, 6, false);
     			        oTable.fnUpdate(type, nRow, 3, false);
     			        oTable.fnUpdate('<img src="/static/images/details_close.png">', nRow, 7, false);
     			        oTable.fnUpdate('<button type="button" data="'+id+'"  class="btn btn-info"><i class="fa fa-trash-o"></i></button>', nRow, 8, false);
     			   	    closeDial();
     				}
     			},
     			error : function() {
     				bootbox.alert('Modify the failure！');
     			}
     		});
     	}
    	 
     }
    }
    
    function closeDial(){
        //保存后关闭展开的信息
        $('#editable-sample tbody td img').each(function (e, i) {
            var src = i.src.substring(i.src.length - 9, i.src.length);
            if (src == 'close.png') {
                $('#editable-sample tbody td img:eq("' + e + '")').trigger("click");
                addFlag=true;
            }
            
        });
    	
    }
    
    $('#editable-sample th').click(function () {
    	showTableEach();
    });
    
    
    $(document).on('click', '#editable-sample tbody td button', function (e) {
        e.preventDefault();
        var id=$(this).attr("data");
     
        
        var nTr = $(this).html();
        var nRow = $(this).parents('tr')[0];
        if (nTr.trim() == '<i class="fa fa-trash-o"></i>') {
            bootbox.confirm("Are you sure?", function(result) {
                if (result) {
                   // What to do here?
                	if(id==undefined){
                		addFlag=true;
                		oTable.fnDeleteRow(nRow);
                		
                	}else{
                		$.ajax({
                			type : "post",
                			url : "/empuser/delEmpUser",
                			data : {
                				id:id
                			},
                			dataType : "json",
                			success : function(data, index) {
                				
                				if(data.flag==0){
                					  oTable.fnDeleteRow(nRow);
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