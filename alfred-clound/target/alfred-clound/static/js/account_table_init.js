var nEditing = null;
var nflag = 0;


function showTable(){
	 //当前的第二个TD
	var mainTd=$(".sorting_1");
    //userID
    var userIdTd=mainTd.prev();
    var passwordTd=mainTd.next();
    userIdTd.addClass("classdisplay");
    passwordTd.addClass("classdisplay");
	
}

var EditableTable = function () {

    return {
        //main function to initiate the module
        init: function () {
            function restoreRow(oTable, nRow) {
                var aData = oTable.fnGetData(nRow);
                var jqTds = $('>td', nRow);

                for (var i = 0, iLen = jqTds.length; i < iLen; i++) {
                    // oTable.fnUpdate(aData[i], nRow, i, false);
                }
                oTable.fnDraw();
            }

            function editRow(oTable, nRow) {
                var aData = oTable.fnGetData(nRow);
                var jqTds = $('>td', nRow);
                jqTds[7].innerHTML = '<img src="../static/images/details_open.png">';
                jqTds[8].innerHTML = ' <button type="button" class="btn btn-info"><i class="fa fa-trash-o"></i></button>';
            }

            var oTable = $('#editable-sample').dataTable({
                "aLengthMenu": [
                    [5, 15, 20, -1],
                    [5, 15, 20, "All"] // change per page values here
                ],
                // set the initial value
                "iDisplayLength": 5,
                "sDom": "<'row'<'col-lg-6'l><'col-lg-6'f>r>t<'row'<'col-lg-6'i><'col-lg-6'p>>",
                "sPaginationType": "bootstrap",
                "oLanguage": {
                    "sLengthMenu": "_MENU_ records per page",
                    "oPaginate": {
                        "sPrevious": "Prev",
                        "sNext": "Next"
                    }
                },
                "aoColumnDefs": [
                    {
                        'bSortable': false,
                        'aTargets': [0]
                    }
                ]
            });

            jQuery('#editable-sample_wrapper .dataTables_filter input').addClass("form-control medium"); // modify table search input
            jQuery('#editable-sample_wrapper .dataTables_length select').addClass("form-control xsmall"); // modify table per page dropdown

            var nEditing = null;

            $('#editable-sample_new').click(function (e) {
                e.preventDefault();
                var aiNew = oTable.fnAddData(['']);
                var nRow = oTable.fnGetNodes(aiNew[0]);
                editRow(oTable, nRow);
                //隐藏不要显示的字段
                showTable();      
                $('#editable-sample tbody td img:first').trigger("click");
               
              

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
    var userRestaurantId=aData[5] == null ? '' : aData[5];
    var restaurantId=aData[6] == null ? '' : aData[6];
    
    $("#checkedOption option[value='"+restaurantId+"']").attr("selected", "selected"); 
    var selectRes=$("#selectRes").html();
    if(aData[0]==null||aData[0]==''){
    	disabled='';
    }
    var sOut = '<table  width="80%"colspan="5" border="0" id="table_'+id+'" >';
    sOut+='  <tr><td><input type="hidden" class="form-control" value="' + id + '"></td></tr>';
    sOut += '<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Email:<input type="email" class="form-control" value="' + email + '" '+disabled+'></td>';
    sOut += '<td>Password: <input type="password" class="form-control " value="' + password + '"></td><td>Restaurant:</td><td>'+selectRes+'</td></tr>';
    sOut += '<tr><td>First Name: <input type="text" class="form-control "value="' + firstName + '"></td>';
    sOut += '<td>Last Name: <input type="text" class="form-control "value="' + lastName + '"></td><td><a class="edit" href=""><button type="button" data="' + id + '"  userrestaurantId="'+userRestaurantId+'" class="btn btn-info"><i class="fa fa-save"></i>Save</button></a></td></tr>';
    sOut+='  <tr><td><input type="hidden" class="form-control " value="' + userRestaurantId + '"></td></td></tr>';
    sOut += '</table>';
    return sOut;
}

$(document).ready(function () {

    var nCloneTh = document.createElement('th');
    nCloneTh.innerHTML = 'Edit';
    var nCloneTd = document.createElement('td');
    nCloneTd.innerHTML = '<img src="../static/images/details_open.png">';
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
            this.src = "../static/images/details_open.png";
            oTable.fnClose(nTr);
        }
        else {
            /* Open this row */
            this.src = "../static/images/details_close.png";
            
            oTable.fnOpen(nTr, fnFormatDetails(oTable, nTr), 'details');
            
            //编辑时确定是哪一行数据
            nEditing = nTr;
        }
        
        //设置显示区域的长度
        $(".details").attr("colspan","5");
    });


    //保存具体的某一行数据
    function saveRow(oTable, nRow,tableId,userRestaurantId) {
       var restaurantId=$("#"+tableId+" select").val();
       var id=$("#"+tableId+" input:eq(0)").val();
       var email=$("#"+tableId+" input:eq(1)").val();
       var pasword=$("#"+tableId+" input:eq(2)").val();
       var firstName=$("#"+tableId+" input:eq(3)").val();
       var lastName=$("#"+tableId+" input:eq(4)").val();
       var userRestaurantId=$("#"+tableId+" input:eq(5)").val();
       var check=$("#"+tableId+" input:eq(6)").val();
       
        var jqInputs = $('input', nRow);
        oTable.fnUpdate(id, nRow, 0, false);
        oTable.fnUpdate(email, nRow, 1, false);
        oTable.fnUpdate(pasword, nRow, 2, false);
        oTable.fnUpdate(firstName, nRow, 3, false);
        oTable.fnUpdate(lastName, nRow, 4, false);
        oTable.fnUpdate(userRestaurantId, nRow, 5, false);
        oTable.fnUpdate(restaurantId, nRow, 6, false);
        oTable.fnUpdate('<img src="../static/images/details_close.png">', nRow, 7, false);
        oTable.fnUpdate('<button type="button" data="'+id+'"  class="btn btn-info"><i class="fa fa-trash-o"></i></button>', nRow, 8, false);
        
    
        // ajax 请求数据录入
    	if (id== '') {
    		$.ajax({
    			type : "post",
    			url : "../user/addManager",
    			data : {
    				accountName : email,password:pasword,firstName:firstName,lastName:lastName,restaurantId:restaurantId
    			},
    			dataType : "json",
    			success : function(data) {
    			oTable.fnUpdate(data.flag, nRow, 0, false);
    	        oTable.fnUpdate('<button type="button" data="'+data.flag+'"  class="btn btn-info"><i class="fa fa-trash-o"></i></button>', nRow, 8, false);
    			alert('新增成功！');
    			
    			},
    			error : function() {
    				alert('新增出错！');
    			}
    		});

    	} else {
    		$.ajax({
    			type : "post",
    			url : "../user/updateManager",
    			data : {
    				id:id,accountName : email,password:pasword,firstName:firstName,lastName:lastName,restaurantId:restaurantId,userRestaurantId:userRestaurantId
    			},
    			dataType : "json",
    			success : function(data, index) {
    				alert('修改成功！');

    			},
    			error : function() {
    				alert('修改失败！');
    			}
    		});

    	}
        
        
        //保存后关闭展开的信息
        data = $('#editable-sample tbody td img');
        $('#editable-sample tbody td img').each(function (e, i) {
            var src = i.src.substring(i.src.length - 9, i.src.length);
            if (src == 'close.png') {
                $('#editable-sample tbody td img:eq("' + e + '")').trigger("click");
            }
            
        });

    }
    $(document).on('click', '#editable-sample tbody td button', function (e) {
        e.preventDefault();
        var id=$(this).attr("data");
        var userRestaurantId=$(this).attr("userRestaurantId");
        
        var nTr = $(this).html();
        var nRow = $(this).parents('tr')[0];
        if (nTr.trim() == '<i class="fa fa-trash-o"></i>') {
            if (confirm("Are you sure to delete this row ?") == false) {
                return;
            }
            oTable.fnDeleteRow(nRow);
    		$.ajax({
    			type : "post",
    			url : "../user/delManager",
    			data : {
    				userId:id,userRestaurantId:userRestaurantId
    			},
    			dataType : "json",
    			success : function(data, index) {
    				
    				alert('删除成功！');

    			},
    			error : function() {
    				
    				alert('删除出错！');

    			}
    		});

            
        } else {
        	 
            //var table=$("table table input:eq(1)").val();
            
            var tableId="table_"+id;
             
            saveRow(oTable, nEditing,tableId,userRestaurantId);

        }
    });
});