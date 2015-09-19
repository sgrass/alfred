var addFlag=true;
function displayTd() {
	//$("#editable-sample tr td:nth-child(1)").hide();
/*	$("#editable-sample tr").each(function(){
		$("td:eq(0)",this).hide();
	}
	);   */
	
}
var EditableTable = function () {
    return {
        init: function () {
            function restoreRow(oTable, nRow) {
                var aData = oTable.fnGetData(nRow);
                var jqTds = $('>td', nRow);
                for (var i = 0, iLen = jqTds.length; i < iLen; i++) {
                    oTable.fnUpdate(aData[i], nRow, i, false);
                }
 
                oTable.fnUpdate(aData[5], nRow, 5, false);
	            oTable.fnUpdate(aData[6], nRow, 6, false);
 	        	oTable.fnUpdate('<a data="'+aData[0]+'" class="edit" href="">Edit</a>', nRow, 7, false);
	            oTable.fnUpdate('<a data="'+aData[0]+'" class="delete" href="">Delete</a>', nRow, 8, false);
	              
                oTable.fnDraw();
            }
            function editRow(oTable, nRow) {
                var aData = oTable.fnGetData(nRow);
                var jqTds = $('>td', nRow);
                var type=aData[1];
                var status=aData[2];
                var typeName=aData[5];
                var statusName=aData[6];
                
                $("#checkedOption option[value='"+type+"']").attr("selected", "selected"); 
                $("#checkedStatus option[value='"+status+"']").attr("selected", "selected"); 
                
                var selectRes=$("#selectRes").html();
                var selectStatus=$("#selectStatus").html();
                //jqTds[0].innerHTML = '<input type="hidden" class="form-control small" value="' + aData[0] + '">';
                jqTds[0].innerHTML = '<form id="form_table_0'+aData[0]+'"><input  data="'+aData[0]+'" type="text" class="form-control small" value="' + aData[3] + '" required></form>';
                jqTds[1].innerHTML = '<form id="form_table_1'+aData[0]+'"><input type="text" name="digits"class="form-control small" value="' + aData[4] + '" required ></form>';
                jqTds[2].innerHTML = selectRes;
                jqTds[3].innerHTML = selectStatus;
                jqTds[4].innerHTML = '<a class="edit" data="'+aData[0]+'" href="">Save</a>';
                jqTds[5].innerHTML = '<a data="'+aData[0]+'" class="cancel" href="">Cancel</a>';
            }

            function saveRow(oTable, nRow) {
                var aData = oTable.fnGetData(nRow);  
                var jqInputs = $('input', nRow);
                var jqselect = $('select', nRow);
                var selectTypeNameObj = $('select option:selected', nRow);

                var type=jqselect[0].value;
                var status=jqselect[1].value;
                var typeName=selectTypeNameObj[0].innerText;
                var statusName=selectTypeNameObj[1].innerText;
                //数据库持久化操作
                var id=jqInputs[0].attributes[0].value;
                var taxName=jqInputs[0].value;
                var taxPercentage=jqInputs[1].value;
                var taxType=type;
       
                //var aData = oTable.fnGetData(nRow);
                
                
                var validator=$("#form_table_0"+id).validate();
                
                var validator1=$("#form_table_1"+id).validate({
              	   rules: {
              		  digits:{
              			  // digits:true 
              			 range:[0,100],
              			 digits:true 
              		   }
                     },
                     messages: {  
                     	digits:'please enter number[0-100]'
                     }
                 });
                
                
         		var check = validator.form();
         		var check1 = validator1.form();
                
                if(check&&check1){
                	
                	   
                    // ajax 请求数据录入
                	if (id== '') {
                		//打印机名称验证
                		$.ajax({
                			type : "post",
                			url : "/taxSetting/checkTaxName",
                			data : {
                				taxName:taxName,id:0
                			},
                			dataType : "json",
                			success : function(data) {

                			if(data.flag==false){
                				 oTable.fnDeleteRow(nRow);
                				 bootbox.alert('Tax name already exists, please replace the other name!');
                			     return;
                			}else{
                				
                				$.ajax({
                	    			type : "post",
                	    			url : "/taxSetting/addTax",
                	    			data : {
                	    				taxName:taxName,taxPercentage:taxPercentage,taxType:taxType,status:status
                	    			},
                	    			dataType : "json",
                	    			success : function(data) {   
                	    				var taxTem=data.taxList;
                	    				if(data.flag!=0){
                	    					var taxlistdiv="";
                	    					if(taxTem.length>0){
                	    						
                	    						for(var tax in taxTem){
                    	    						//taxTem
                	    							taxlistdiv+='<option value="'+taxTem[tax].id+'">'+taxTem[tax].taxName+'</option>';
                    	    					}	
                	    					}
                	    				$("#selectTax").html(taxlistdiv);
                	    	        	oTable.fnUpdate(data.flag, nRow, 0, false);
                	    	        	oTable.fnUpdate(taxType, nRow, 1, false);
                	    	        	oTable.fnUpdate(status, nRow, 2, false);
                	    	        	oTable.fnUpdate(taxName, nRow, 3, false);
               	    	                oTable.fnUpdate(taxPercentage, nRow, 4, false);
              	    	                oTable.fnUpdate(typeName, nRow, 5, false);
              	    	                oTable.fnUpdate(statusName, nRow, 6, false);
                 	    	        	oTable.fnUpdate('<a data="'+data.flag+'" class="edit" href="">Edit</a>', nRow, 7, false);
              	    	                oTable.fnUpdate('<a data="'+data.flag+'" class="delete" href="">Delete</a>', nRow, 8, false);
              	    	               // oTable.fnDraw();
              	    	                addFlag=true;
              	    	                
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
                		

                	} else {
                		$.ajax({
                			type : "post",
                			url : "/taxSetting/checkTaxName",
                			data : {
                				taxName:taxName,id:id
                			},
                			dataType : "json",
                			success : function(data) {

                			if(data.flag==false){
                				
               				 bootbox.alert('Tax name already exists, please replace the other name!');
                			     return ;     
                			}else{
                	    		$.ajax({
                	    			type : "post",
                	    			url : "/taxSetting/updateTax",
                	    			data : {
                	    				taxName:taxName,taxPercentage:taxPercentage,taxType:taxType,id:id,status:status
                	    			},
                	    			dataType : "json",
                	    			success : function(data, index) {
                	    				var taxTem=data.taxList;
                	    				if(data.flag==0){
                	    					var taxlistdiv="";
                	    					if(taxTem.length>0){
                	    						
                	    						for(var tax in taxTem){
                    	    						//taxTem
                	    							taxlistdiv+='<option value="'+taxTem[tax].id+'">'+taxTem[tax].taxName+'</option>';
                    	    					}	
                	    					}
                	    				  $("#selectTax").html(taxlistdiv);
                	    					
                	    					oTable.fnUpdate(id, nRow, 0, false);
                    	    	        	oTable.fnUpdate(taxType, nRow, 1, false);
                    	    	        	oTable.fnUpdate(status, nRow, 2, false);
                    	    	        	oTable.fnUpdate(taxName, nRow, 3, false);
                  	    	                oTable.fnUpdate(taxPercentage, nRow, 4, false);
                  	    	                oTable.fnUpdate(typeName, nRow, 5, false);
                  	    	                oTable.fnUpdate(statusName, nRow, 6, false);
                  	    	          	    oTable.fnUpdate('<a data="'+id+'" class="edit" href="">Edit</a>', nRow, 7, false);
              	    	                    oTable.fnUpdate('<a data="'+id+'" class="delete" href="">Delete</a>', nRow, 8, false);
              	    	              
                  	    	               // oTable.fnDraw();
                  	    	                addFlag=true;
                	    				}
                	    			},
                	    			error : function() {
                	    				//bootbox.alert('修改失败！');
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
            }

            function cancelEditRow(oTable, nRow) {
                var jqInputs = $('input', nRow);
                oTable.fnUpdate(jqInputs[0].value, nRow, 0, false);
                oTable.fnUpdate(jqInputs[1].value, nRow, 1, false);
                oTable.fnUpdate(jqInputs[2].value, nRow, 2, false);
               // oTable.fnUpdate(jqInputs[3].value, nRow, 3, false);
               // oTable.fnUpdate(jqInputs[4].value, nRow, 4, false);
               // oTable.fnUpdate(jqInputs[5].value, nRow, 5, false);
               // oTable.fnUpdate(jqInputs[6].value, nRow, 6, false);
                oTable.fnUpdate('<a class="edit" href="">Edit</a>', nRow, 7, false);
                oTable.fnDraw();
            }
            var oTable = $('#editable-sample').dataTable({
            	bFilter:false,
            	bLengthChange:false,
            	iDisplayLength:5,
            	//"bSort":false,
//            	"aaSorting": [[ 0, "asc" ]],
            	"aoColumnDefs": [
                                 { "bSearchable": false, "bVisible": false, "aTargets": [ 0 ] },
                                 { "bVisible": false, "aTargets": [ 1 ] },
                                 { "bVisible": false, "aTargets": [ 2 ] }
                             ] 
            	
            });

            var nEditing = null;
            $('#editable-sample_new').click(function (e) {
            	if(addFlag==true){
            		  e.preventDefault();
                      var aiNew = oTable.fnAddData(['', '', '', '','','','',
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

               /* if (confirm("Are you sure to delete this row ?") == false) {
                    return;
                }*/
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
                    		$.ajax({
                    			type : "post",
                    			url : "/taxSetting/checkCount",
                    			data : {
                    				id:id
                    			},
                    			dataType : "json",
                    			success : function(data, index) {
                    				
            	    				if(data.flag==false){
            	    					
            	    					
                                    	$.ajax({
                                			type : "post",
                                			url : "/taxSetting/delTax",
                                			data : {
                                				id:id
                                			},
                                			dataType : "json",
                                			success : function(data, index) {
                                				
                                				var taxTem=data.taxList;
                        	    				if(data.flag==0){
                                                  	 location.reload();

                        	    					/*
                        	    					var taxlistdiv="";
                        	    					if(taxTem.length>0){
                        	    						
                        	    						for(var tax in taxTem){
                            	    						//taxTem
                        	    							taxlistdiv+='<option value="'+taxTem[tax].id+'">'+taxTem[tax].taxName+'</option>';
                            	    					}	
                        	    					}
                        	    				  $("#selectTax").html(taxlistdiv);
                                	              oTable.fnDeleteRow(nRow);
                                	              addFlag=true;
                                		
                                				*/}

                                			},
                                			error : function() {
                                				
                                				bootbox.alert('Delete failed!');

                                			}
                                		});		
            	    					
            	    				}else{
            	    					
            	    					bootbox.alert('Existing related, please delete the related association and then deleted!');
            	    					
            	    				}

                    			},
                    			error : function() {
                    				
                    				bootbox.alert('Delete failed!');

                    			}
                    		});	
                    		
                    		
                        
                    		
                    	}
                    
                    } else {
                       // What to do here?
                    }               
                });
                
              
            });

            $('#editable-sample').on('click'," a.cancel" ,function (e) {
                e.preventDefault();
                var nRow = $(this).parents('tr')[0];
                if ($(this).attr("data") != "") {
                	 restoreRow(oTable, nEditing);
                     nEditing = null;
                }else{
                	
  	              oTable.fnDeleteRow(nRow);

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