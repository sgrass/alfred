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
                
                var materialDiv=$("#materialDiv").html();
                var supplierDiv=$("#supplierDiv").html();
                
                var selectMaterial="";
                    selectMaterial+='<select  class="form-control" id="material_'+aData[0]+'"  style="width:200px" required>';
                    selectMaterial+=materialDiv;
                    selectMaterial+='</select>';
                    
                var selectSupplier="";
                    selectSupplier+='<select class="form-control" id="supplier_'+aData[0]+'"  style="width:200px" required>';
                    selectSupplier+=supplierDiv;
                    selectSupplier+='</select>';
                jqTds[0].innerHTML = '<form id="form_supplierId_'+aData[0]+'"><input  data="'+aData[0]+'" type="hidden" class="form-control small"  value="' + aData[1] + '" >'+selectSupplier+'</form>';
                jqTds[1].innerHTML = '<form id="form_materialId_'+aData[0]+'">'+selectMaterial+'</form>';
                jqTds[2].innerHTML = '<form id="form_stockQty_'+aData[0]+'"><input  type="text" name="stockQty" class="form-control small"  value="' + aData[3] + '" required></form>';
                jqTds[3].innerHTML = '<form id="form_stockTotalPrice_'+aData[0]+'"><input  data1="'+aData[5]+'" data2="'+aData[6]+'" name="price" type="text" class="form-control small"  value="' + aData[4] + '" required></form>';
                jqTds[4].innerHTML = '<a class="edit" href="">Save</a>';
                jqTds[5].innerHTML = '<a class="cancel" href="">Cancel</a>';
            }
   
            function saveRow(oTable, nRow) {
                var aData = oTable.fnGetData(nRow);
                var jqInputs = $('input', nRow);
                var jqSelect = $('select', nRow);
                
                var id=jqInputs[0].attributes[0].value;
               // var rawMaterialName=jqInputs[0].value;
               // var unitOfMeasurement=jqInputs[1].value;
                var stockQty=jqInputs[1].value;
                var stockTotalPrice=jqInputs[2].value;
                
                var materialId=$("#material_"+id+"").val();
                var materialName=$("#material_"+id+"").find("option:selected").text();
                var supplierId=$("#supplier_"+id+"").val();
                var supplierName=$("#supplier_"+id+"").find("option:selected").text();
               // var supplierId=jqInputs[3].attributes[0].value;
                //var materialId=jqInputs[3].attributes[1].value;
                //form 表单赋值
                $("#id").val(id);
                $("#materialId").val(materialId);
                $("#supplierId").val(supplierId);
                
                
                $("#materialName").val(materialName);
                $("#supplierName").val(supplierName);
                
                $("#stockQty").val(stockQty);
                $("#stockTotalPrice").val(stockTotalPrice);
                
                var validator=$("#form_supplierId_"+id).validate();
                var validator1=$("#form_materialId_"+id).validate();
                var validator2=$("#form_stockQty_"+id).validate({
                	
                	rules : {
                		stockQty : {
							required : true,
							digits : true
						}
					},
					messages : {

						stockQty : {
							required : "cannot be empty",
							digits : "Tip: only fill in an integer"
						}
					}
                	
                });
                var validator3=$("#form_stockTotalPrice_"+id).validate({
                	rules : {
                		price : {
							required : true,
							 decimal:true 
						}
					},
					messages : {

						price : {
							required : "cannot be empty",
							decimal : "please enter number"
						}
					}
                	
                
                	
                });
            	var check = validator.form();
            	var check1 = validator1.form();
            	var check2 = validator2.form();
            	var check3 = validator3.form();
        		if(check&&check1&&check2&&check3){
                    //验证后提交表单
                	if (id== '') {
                		$("#material_stock_form").attr("action","/materialStock/insert");
           	        	$("#material_stock_form").submit();
                		
                	} else {
                		$("#material_stock_form").attr("action","/materialStock/updateById");
           	        	$("#material_stock_form").submit();
                	}	
        			
        			
        		}
                
            }
            var oTable = $('#editable-sample').dataTable({
            	bFilter:false,
            	bLengthChange:false,
            	iDisplayLength:5,
            	"aoColumnDefs": [
                   { "bSearchable": false, "bVisible": false, "aTargets": [ 0 ] },
                   { "bSearchable": false, "bVisible": false, "aTargets": [ 5 ] },
                   { "bSearchable": false, "bVisible": false, "aTargets": [ 6] }
                  ] 
            });

           
            var nEditing = null;

            $('#editable-sample_new').click(function (e) {
            	if(addFlag==true){
            	    e.preventDefault();
                    var aiNew = oTable.fnAddData(['', '','','','','','',
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
                		  $("#material_stock_form").attr("action","/materialStock/deleteById");
             	          $("#material_stock_form").submit();
	
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
                    
                    var aData = oTable.fnGetData(nRow);
                    //alert(aData[0]+"--"+aData[5]);
           		    $("#material_"+aData[0]+" option[value="+aData[6]+"]").attr("selected", "selected"); 
           		    $("#supplier_"+aData[0]+" option[value="+aData[5]+"]").attr("selected", "selected"); 
                }
               
                
            });
        }

    };

}();