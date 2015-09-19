var addFlag=true;
function showTable(){
	 $("#editable-sample tr td:nth-child(1)").hide();
	 $("#editable-sample tr td:nth-child(2)").hide();
	 $("#editable-sample tr td:nth-child(3)").hide();
	 $("#editable-sample tr td:nth-child(4)").hide();
}
function showTableEach(){
	$("#editable-sample tr").each(function(){
		$("td:eq(0)",this).hide();
		$("td:eq(1)",this).hide();
		$("td:eq(2)",this).hide();
		$("td:eq(3)",this).hide();
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
                jqTds[9].innerHTML = '<img src="/static/images/details_open.png">';
                jqTds[10].innerHTML = ' <button type="button" class="btn btn-info"><i class="fa fa-trash-o"></i></button>';
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
                 $('#editable-sample tbody td img:eq(0)').trigger("click");	
                 addFlag=false;
        	}
              
            });
        }
    };

}();


function fnFormatDetails(oTable, nTr) {
    var aData = oTable.fnGetData(nTr);
    var id = aData[0] == null ? '' : aData[0];
    var printerId = aData[1] == null ? '' : aData[1];
    var happyHourId = aData[2] == null ? '' : aData[2];
    
    var enable = aData[3] == null ? '' : aData[3];
    var name = aData[4] == null ? '' : aData[4];
    var printer=aData[5] == null ? '' : aData[5];
    var happyHourName=aData[6] == null ? '' : aData[6];
    var happyStartTime=aData[7] == null ? '' : aData[7];
    var happyEndTime=aData[8] == null ? '' : aData[8];

    var enableSelect='<select class="form-control" style="width:203px" id="select_enable_'+aData[0]+'">';
        enableSelect+=' <option value="0">disable</option><option value="1">normal</option>';
        enableSelect+="</select>";
   
    //获取打印机信息
    var selectPrint=$("#selectPrint").html();
    var selectOp='<select class="form-control" style="width:203px"  name="printName" id="select_print_'+aData[0]+'">';
        selectOp+=selectPrint;
        selectOp+="</select>";
    //获取happyHour 信息   
    var selectHappy=$("#selectHappy").html();
        var selectOpHappy='<select style="width:203px" class="form-control" name="happyHourName" id="select_happy_'+aData[0]+'">';
        selectOpHappy+=' <option value="0"></option>';
        selectOpHappy+=selectHappy;
        selectOpHappy+="</select>";    
    var sOut = '<form id="form_table_'+id+'"><table colspan="6" width="100%" border="0" id="table_'+id+'" >';
    sOut+='  <tr><td ><input type="hidden" class="form-control" value="' + id + '"></td></tr>';
    sOut += '<tr><td style="text-align: left;">Name:</td<td><input type="text" name="name" class="form-control" value="' + name + '" required></td>';
    sOut += '<td style="text-align: left;">HappyHourName:</td<td> '+selectOpHappy+'</td></tr>';
    sOut += '<tr><td style="text-align: left;">Printer: </td<td>'+selectOp+'</td>';
    sOut += '<td>HappyStartTime:</td<td><input type="text" name="HappyStartTime"  data-format="yyyy-MM-dd"  " value="' + happyStartTime + '" readonly class="form-control form_datetime"></td></tr>';
    sOut += '<tr>';
    sOut += '<td d style="text-align: left;">Enable: </td<td>'+enableSelect+'</td>';
    sOut+='<td style="text-align: left;">HappyEndTime: </td<td><input type="text"  data-format="yyyy-MM-dd" value="' + happyEndTime + '" readonly class="form-control form_datetime"></td></tr>';
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
    $("#editable-sample th").click(function(){
    	
    	showTableEach();
    	
    })
    
    
    
    
    
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
            var id= aData[0] == null ? '' : aData[0];
            $('.form_datetime').datepicker({
            	format: 'yyyy-mm-dd'
              });
            
            //设置下拉列表险种的值
            
            var printerId = aData[1] == null ? '' : aData[1];
            var happyHourId = aData[2] == null ? '' : aData[2];
            var enable = aData[3] == null ? '' : aData[3];
         
            //设置下拉列表的信息
            $("#select_print_"+id+" option[value='"+printerId+"']").attr("selected", "selected"); 
            $("#select_happy_"+id+" option[value='"+happyHourId+"']").attr("selected", "selected"); 
            $("#select_enable_"+id+" option[value='"+enable+"']").attr("selected", "selected"); 
             
            
        }
        
        //设置显示区域的长度
        $(".details").attr("colspan","7");
        
    });
    //保存具体的某一行数据
    function saveRow(oTable, nRow,tableId) {
    	var jqInputs = $('input', nRow);
        var jqSelects = $('select', nRow);
        
        var printId= $("#"+tableId+" select:eq(1)").find("option:selected").val();
        var printName =$("#"+tableId+" select:eq(1)").find("option:selected").text();
        var happyHourId = $("#"+tableId+" select:eq(0)").find("option:selected").val();
        var happyHourName = $("#"+tableId+" select:eq(0)").find("option:selected").text();
        
        var id=$("#"+tableId+" input:eq(0)").val();
        var revenueName=$("#"+tableId+" input:eq(1)").val();
        var isActive=$("#"+tableId+" select:eq(2)").find("option:selected").val();
 
        
        var happyStartTime=$("#"+tableId+" input:eq(2)").val();
        var happyEndTime=$("#"+tableId+" input:eq(3)").val();
        var validator=$("#form_"+tableId).validate();
	    var check = validator.form();		
		if(check){
	        // ajax 请求数据录入
				if (id== '') {
					$.ajax({
    	    			type : "post",
    	    			url : "/revenueCenter/addRevenueCenter",
    	    			data : {
    	    				revName:revenueName,printId:printId,isActive:isActive,happyHourId:happyHourId,startTime:happyStartTime,endTime:happyEndTime
    	    			},
    	    			dataType : "json",
    	    			success : function(data) {
    	    	        if(data.flag!=0){
    	                    oTable.fnUpdate(data.flag, nRow, 0, false);
    	                    oTable.fnUpdate(printId, nRow, 1, false);
    	                    oTable.fnUpdate(happyHourId, nRow, 2, false);
    	                    oTable.fnUpdate(isActive, nRow, 3, false);
    	                    oTable.fnUpdate(revenueName, nRow, 4, false);
    	                    oTable.fnUpdate(printName, nRow, 5, false);
    	                    oTable.fnUpdate(happyHourName, nRow, 6, false);
    	                    oTable.fnUpdate(happyStartTime, nRow, 7, false);
    	                    oTable.fnUpdate(happyEndTime, nRow, 8, false);
    	                    oTable.fnUpdate('<img src="/static/images/details_close.png">', nRow, 9, false);
        	    	        oTable.fnUpdate('<button type="button" data="'+data.flag+'" class="btn btn-info"><i class="fa fa-trash-o"></i></button>', nRow, 10, false);        	    	           
    	    		        closeDial();
    	    	
        				}
    	    			},
    	    			error : function() {
    	    				bootbox.alert('The new failure!');
    	    			}
    	    		});
					
				} else {
					$.ajax({
    	    			type : "post",
    	    			url : "/revenueCenter/updateRevenueCenter",
    	    			data : {
    	    		      id:id,revName:revenueName,printId:printId,isActive:isActive,happyHourId:happyHourId,startTime:happyStartTime,endTime:happyEndTime
    	    			},
    	    			dataType : "json",
    	    			success : function(data, index) {
    	    				if(data.flag==0){
    	    				     oTable.fnUpdate(id, nRow, 0, false);
    	    	                    oTable.fnUpdate(printId, nRow, 1, false);
    	    	                    oTable.fnUpdate(happyHourId, nRow, 2, false);
    	    	                    oTable.fnUpdate(isActive, nRow, 3, false);
    	    	                    oTable.fnUpdate(revenueName, nRow, 4, false);
    	    	                    oTable.fnUpdate(printName, nRow, 5, false);
    	    	                    oTable.fnUpdate(happyHourName, nRow, 6, false);
    	    	                    oTable.fnUpdate(happyStartTime, nRow, 7, false);
    	    	                    oTable.fnUpdate(happyEndTime, nRow, 8, false);
    	    	                    oTable.fnUpdate('<img src="/static/images/details_close.png">', nRow, 9, false);
    	        	    	        oTable.fnUpdate('<button type="button" data="'+id+'" class="btn btn-info"><i class="fa fa-trash-o"></i></button>', nRow, 10, false);        	    	           
    	    	    		        closeDial();
    	    	    		
    	    				}
    	    			},
    	    			error : function() {
    	    				bootbox.alert('Modify the failure!');
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
                addFlag=true;
            }
        });
    }
    
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
                		
                		 
                        //数据库持久化操作
                        $.ajax({
                			type : "post",
                			url : "/revenueCenter/delRevenueCenter",
                			data : {
                				id:id
                			},
                			dataType : "json",
                			success : function(data, index) {
                				
                				if(data.flag==0){
                	                oTable.fnDeleteRow(nRow);
                	                showTableEach();
                					
                				}

                			},
                			error : function() {
                				bootbox.alert('Delete failed!');

                			}
                		});
                		
                		
                	}
                }              
            });
        	


        } else {
        	var tableId="table_"+id;
            saveRow(oTable, nEditing,tableId);
        }
    });
});