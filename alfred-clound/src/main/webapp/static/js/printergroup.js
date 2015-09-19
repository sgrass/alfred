var editFlag=1;
var addOrderFlag=true;

var PrinterGroupTable = function () {
    return {
        init: function () {
            function restoreRow(oTable, nRow) {
                var aData = oTable.fnGetData(nRow);
                var jqTds = $('>td', nRow);
                for (var i = 0, iLen = jqTds.length; i < iLen; i++) {
                    oTable.fnUpdate(aData[i], nRow, i, false);
                }
                oTable.fnUpdate('<a data="'+aData[0]+'" class="edit" href="">'+jQuery.i18n.prop("printer.edit")+'</a>', nRow, 4, false);
                oTable.fnUpdate('<a data="'+aData[0]+'" class="delete" href="">'+jQuery.i18n.prop("printer.delete")+'</a>', nRow, 5, false);
                oTable.fnDraw();
            }
            function editRow(oTable, nRow) {
               
                var aData = oTable.fnGetData(nRow);
                var jqTds = $('>td', nRow);
                var selectRes=$("#selectRes").html();
                var id = aData[0] == null ? '' : aData[0];
        		var printerName=' <form id="form_select_'+id+'"><select multiple name="ids_'+id+'" id="ids_'+id+'" style="width:200px" class="populate" required>'
        		    printerName+=selectRes;
        		    printerName+=' </select></form>';
        		
				jqTds[0].innerHTML = '<form id="form_group_'+aData[0]+'"> <input data="'+aData[0]+'" type="text" class="form-control small" value="' + aData[2] + '" required></form>';
                jqTds[1].innerHTML = printerName;
                jqTds[2].innerHTML = '<a class="edit" data="'+aData[0]+'" href="">'+jQuery.i18n.prop("printer.save")+'</a>';
                jqTds[3].innerHTML = '<a data="'+aData[0]+'" class="cancel" href="">'+jQuery.i18n.prop("printer.cancel")+'</a>';
                //下拉
                var pId=aData[1] == null ? '' : aData[1];
                pId= $.trim(pId);
                var totalRes = new Array();
                if(pId.indexOf(",")>=0){
                	 totalRes=pId.split(",");
                	 if(totalRes.length!=undefined){
                     if(totalRes.length>0){
                     	for(var i=0;i<totalRes.length;i++){ 
                     		 $("#ids_"+id+" option[value="+totalRes[i]+"]").attr("selected", "selected"); 
                     	}
                     }
                  
                
                }else{
                	
                	
            		 $("#ids_"+id+" option[value="+pId+"]").attr("selected", "selected"); 
                }
           	 }
                $("#ids_"+id).select2();
            }

            function saveRow(oTable, nRow) {
                var aData = oTable.fnGetData(nRow);
                var jqInputs = $('input', nRow);
                var id=jqInputs[0].attributes[0].value;
                var groupName=jqInputs[0].value
                var printerId=$("#ids_"+id).val();
                var text=$(".select2-search-choice div");
                var textVal="";
             
                 var temPrintId="";
                 
                var validator=$("#form_group_"+id).validate();
                var validator1=$("#form_select_"+id).validate();
            	var check = validator.form();
            	var check1 = validator1.form();
        		
        		if(check&&check1){
        			
        			   for(var i=0;i<text.length;i++){
                      	 textVal+=" "+text[i].innerText;
                       }
        			
        		    $.each(printerId,function(n,value) {   
                        if(n==0){
                       	 temPrintId+=value;
                        }else{
                       	 temPrintId+=","+value;
                        }
                    }); 
                   
        			
 
                //新增数据持久化
                // ajax 请求数据录入
            	if (id== '') {
            				$.ajax({
            	    			type : "post",
            	    			url : "/printerGroup/addPrinterGroup",
            	    			data : {
            	    				printerId:printerId,groupName:groupName
            	    			},
            	    			dataType : "json",
            	    			success : function(data) {
            	    				if(data.flag!=0){
            	    					
            	    					oTable.fnUpdate(data.flag, nRow, 0, false);
                      	                oTable.fnUpdate(temPrintId, nRow, 1, false);
                      	                oTable.fnUpdate(groupName, nRow, 2, false);
                      	                oTable.fnUpdate(textVal, nRow, 3, false);
              	    	        	    oTable.fnUpdate('<a data="'+data.flag+'" class="edit" href="">'+jQuery.i18n.prop("printer.edit")+'</a>', nRow, 4, false);
            	    	                oTable.fnUpdate('<a data="'+data.flag+'" class="delete" href="">'+jQuery.i18n.prop("printer.delete")+'</a>', nRow, 5, false);
                      	                //oTable.fnDraw();
            	    				}
            	    				
            	    			},
            	    			error : function() {
            	    				 bootbox.alert(jQuery.i18n.prop("printer.error.add"));
            	    			}
            	    		});
            			
           

            	} else {
            		
    				$.ajax({
    	    			type : "post",
    	    			url : "/printerGroup/updatePrinterGroup",
    	    			data : {
    	    				id:id,printerId:printerId,groupName:groupName

    	    				
    	    			},
    	    			dataType : "json",
    	    			success : function(data) {
    	    				
    	    				if(data.flag==0){
    	    					
    	    					oTable.fnUpdate(id, nRow, 0, false);
              	                oTable.fnUpdate(temPrintId, nRow, 1, false);
              	                oTable.fnUpdate(groupName, nRow, 2, false);
              	                oTable.fnUpdate(textVal, nRow, 3, false);
      	    	        	    oTable.fnUpdate('<a data="'+id+'" class="edit" href="">'+jQuery.i18n.prop("printer.edit")+'</a>', nRow, 4, false);
    	    	                oTable.fnUpdate('<a data="'+id+'" class="delete" href="">'+jQuery.i18n.prop("printer.delete")+'</a>', nRow, 5, false);
              	                //oTable.fnDraw();
    	    				}
    	    				
    	    			},
    	    			error : function() {
    	    				 bootbox.alert(jQuery.i18n.prop("printer.error.modify"));
    	    			}
    	    		});
    			
            		
            		
            	}
                
        		}
            }
            
            var oTable = $('#printer_group_edit').dataTable({
            	bFilter:false,
            	bLengthChange:false,
            	iDisplayLength:5,
            	"aoColumnDefs": [
                                 { "bSearchable": false, "bVisible": false, "aTargets": [ 0 ] },
                                 { "bSearchable": false, "bVisible": false, "aTargets": [ 1 ] }
                             ] 
            });

            var nEditing = null;
            $('#editable-sample_new_order').click(function (e) {
            	if(addOrderFlag==true){
            	     e.preventDefault();
                     var aiNew = oTable.fnAddData(['', '', '','',
                             '<a class="edit" href="">'+jQuery.i18n.prop("printer.edit")+'</a>', '<a class="cancel" data-mode="new" href="">'+jQuery.i18n.prop("printer.cancel")+'</a>'
                     ]);
                     var nRow = oTable.fnGetNodes(aiNew[0]);
                     editRow(oTable, nRow);
                     nEditing = nRow;
                     $("#ids_").select2();
                     addOrderFlag=false;
            		
            	}
           
            });


            $('#printer_group_edit').on('click', "a.delete",function (e) {
                e.preventDefault();
                var id=$(this).attr("data");
                var nRow = $(this).parents('tr')[0];
                
                bootbox.confirm(jQuery.i18n.prop("areyousure"), function(result) {
                    if (result) {
                    	
                    	if(id==undefined){
                    		 oTable.fnDeleteRow(nRow);
                    		 addOrderFlag=true;
                    	}else{
                    		
                    		  // What to do here?
                        	$.ajax({
            	    			type : "post",
            	    			url : "/printerGroup/delPrinterGroup",
            	    			data : {
            	    				id:id
            	    			},
            	    			dataType : "json",
            	    			success : function(data) {  
            	    	        if(data.flag==0){
            	    	        	 oTable.fnDeleteRow(nRow);
            	    	        }

            	    			},
            	    			error : function() {
            	    				 bootbox.alert(jQuery.i18n.prop("printer.error.delete"));
            	    			}
            	    		});
                    	}	
                    }              
                });
            });

            $('#printer_group_edit').on('click'," a.cancel" ,function (e) {
                e.preventDefault();
                if ($(this).attr("data") == "") {
                	 location.reload();
                } else {
                    restoreRow(oTable, nEditing);
                    nEditing = null;
                }
               
            });

            $('#printer_group_edit').on('click', "a.edit",function (e) {
                e.preventDefault();

                /* Get the row as a parent of the link that was clicked on */
                var nRow = $(this).parents('tr')[0];

                if (nEditing !== null && nEditing != nRow) {
                    /* Currently editing - but not this row - restore the old before continuing to edit mode */
                    restoreRow(oTable, nEditing);
                    editRow(oTable, nRow);
                    nEditing = nRow;
                } else if (nEditing == nRow && this.innerHTML == jQuery.i18n.prop("printer.save")) {
                    /* Editing this row and want to save it */
                    saveRow(oTable, nEditing);
                   // nEditing = null;
                } else {
                    editRow(oTable, nRow);
                    nEditing = nRow;
                }
                
            });  
        }

    };

}();

