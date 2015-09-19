var addFlag=true;
var EditableTable = function () {
    return {
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
                jqTds[0].innerHTML = '<form id="form_name_'+aData[0]+'"><input  data="'+aData[0]+'" type="text" class="form-control small"  value="' + aData[1] + '" required></form>';
                jqTds[1].innerHTML = '<form id="form_address_'+aData[0]+'"><input  type="text" class="form-control small"  value="' + aData[2] + '" required></form>';
                jqTds[2].innerHTML = '<form id="form_contact_'+aData[0]+'"><input  type="text" class="form-control small"  value="' + aData[3] + '" required></form>';
                jqTds[3].innerHTML = '<form id="form_email_'+aData[0]+'"><input  type="text" class="form-control small"  value="' + aData[4] + '" required></form>';
                jqTds[4].innerHTML = '<a class="edit" href="">Save</a>';
                jqTds[5].innerHTML = '<a class="cancel" href="">Cancel</a>';
            }
   
            function saveRow(oTable, nRow) {
                var aData = oTable.fnGetData(nRow);
                var jqInputs = $('input', nRow);
                var id=jqInputs[0].attributes[0].value;
                var supplierName=jqInputs[0].value;
                var supplierAddress=jqInputs[1].value;
                var supplierContact=jqInputs[2].value;
                var supplierEmail=jqInputs[3].value;
                //form 表单赋值
                $("#id").val(id);
                $("#supplierName").val(supplierName);
                $("#supplierAddress").val(supplierAddress);
                $("#contact").val(supplierContact);
                $("#email").val(supplierEmail);
                
                var validator=$("#form_name_"+id).validate();
                var validator1=$("#form_address_"+id).validate();
                var validator2=$("#form_contact_"+id).validate();
                var validator3=$("#form_email_"+id).validate();
            	var check = validator.form();
            	var check1 = validator1.form();
            	var check2 = validator2.form();
            	var check3 = validator3.form();
        		if(check&&check1&&check2&&check3){
                    //验证后提交表单
                	if (id== '') {
                		$("#supplier_form").attr("action","/supplier/insert");
           	        	$("#supplier_form").submit();
                		
                	} else {
                		$("#supplier_form").attr("action","/supplier/updateById");
           	        	$("#supplier_form").submit();
                	}	
        			
        			
        		}
                
            }
            var oTable = $('#editable-sample').dataTable({
            	bFilter:false,
            	bLengthChange:false,
            	iDisplayLength:5,
            	"aoColumnDefs": [
                   { "bSearchable": false, "bVisible": false, "aTargets": [ 0 ] }
                  ] 
            });

           
            var nEditing = null;

            $('#editable-sample_new').click(function (e) {
            	if(addFlag==true){
            	    e.preventDefault();
                    var aiNew = oTable.fnAddData(['', '','','','',
                            '<a class="edit" href="">Edit</a>', '<a class="cancel" data-mode="new" href="">Cancel</a>'
                    ]);
                    var nRow = oTable.fnGetNodes(aiNew[0]);
                    editRow(oTable, nRow);
                    nEditing = nRow;
                    addFlag=false;
            	}
            
            });

            $('#editable-sample').on('click', "a.delete",function (e) {
                e.preventDefault();
                var id=$(this).attr("data");
                var nRow = $(this).parents('tr')[0];
                bootbox.confirm("Are you sure?", function(result) {
                    
     
                    if (result) {
                       // What to do here?
                        //数据库持久化操作
                    	if(id==undefined){
                    		 addFlag=true;
                    		 oTable.fnDeleteRow(nRow);
                    	}else{
                    		
                		  $("#id").val(id);
                		  $("#supplier_form").attr("action","/supplier/deleteById");
             	          $("#supplier_form").submit();
	
                    	}
                    }              
                });
           

            });

            $('#editable-sample').on('click'," a.cancel" ,function (e) {
                if ($(this).attr("data") != "") {
               	 restoreRow(oTable, nEditing);
                    nEditing = null;
               } 
            });
            $('#editable-sample').on('click', "a.edit",function (e) {
                e.preventDefault();
                /* Get the row as a parent of the link that was clicked on */
                var nRow = $(this).parents('tr')[0];
                if (nEditing !== null && nEditing != nRow) {
                    /* Currently editing - but not this row - restore the old before continuing to edit mode */
                    restoreRow(oTable, nEditing);
                    editRow(oTable, nRow);
                    nEditing = nRow;
                } else if (nEditing == nRow && this.innerHTML == "Save") {
                    /* Editing this row and want to save it */
                    saveRow(oTable, nEditing);
                    //nEditing = null;
                } else {
                    /* No edit in progress - let's start one */
                    editRow(oTable, nRow);
                    nEditing = nRow;
                }
            });
        }

    };

}();