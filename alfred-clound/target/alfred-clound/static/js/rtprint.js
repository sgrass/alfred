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
                jqTds[0].innerHTML = '<form id="form_printer_'+aData[0]+'"><input  data="'+aData[0]+'" type="text" class="form-control small"  value="' + aData[1] + '" required></form>';
                jqTds[1].innerHTML = '<a class="edit" href="">Save</a>';
                jqTds[2].innerHTML = '<a class="cancel" href="">Cancel</a>';
            }
   
            function saveRow(oTable, nRow) {
                var aData = oTable.fnGetData(nRow);
                var jqInputs = $('input', nRow);
                var id=jqInputs[0].attributes[0].value;
                var printerName=jqInputs[0].value;
                
                var validator=$("#form_printer_"+id).validate();
            	var check = validator.form();
        		if(check){
            
        		    // ajax 请求数据录入
                	if (id== '') {
                		//打印机名称验证
                		$.ajax({
                			type : "post",
                			url : "/printer/checkPrinter",
                			data : {
                				printerName:printerName,id:0
                			},
                			dataType : "json",
                			success : function(data) {

                			if(data.flag==false){
                				 oTable.fnDeleteRow(nRow);
                				 addFlag=true;
                				 bootbox.alert('The name of the printer has already exists, please replace the other!');
                			     return;
                			}else{
                				
                				$.ajax({
                	    			type : "post",
                	    			url : "/printer/addPrinter",
                	    			data : {
                	    				printerName:printerName
                	    			},
                	    			dataType : "json",
                	    			success : function(data) {  
                	    		     var list=data.printerList;
                	    		     var printerlistdiv="";
                	    	        if(data.flag!=0){
                	    	            oTable.fnUpdate(data.flag, nRow, 0, false);
                      	                oTable.fnUpdate(data.flag, nRow, 2, false);
                      	                oTable.fnUpdate(printerName, nRow, 1, false);
              	    	        	    oTable.fnUpdate('<a data="'+data.flag+'" class="edit" href="">Edit</a>', nRow, 2, false);
            	    	                oTable.fnUpdate('<a data="'+data.flag+'" class="delete" href="">Delete</a>', nRow, 3, false);
                      	                //oTable.fnDraw();
                	    	        	//bootbox.alert('新增成功！');
                    				if(list.length>0){
                    					for(var printer in list){
            	    						//taxTem
        	    							printerlistdiv+='<option value="'+list[printer].id+'">'+list[printer].printerName+'</option>';
            	    					}
                    					  $("#selectRes").html(printerlistdiv);
                    					
                    					
                    				}
                	    	        
                	    	        }else{
                    					
                    					//bootbox.alert('新增失败！');
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
                			url : "/printer/checkPrinter",
                			data : {
                				printerName:printerName,id:id
                			},
                			dataType : "json",
                			success : function(data) {

                			if(data.flag==false){
                				 addFlag=true;
                				 bootbox.alert('The name of the printer has already exists, please replace the other!');
                			     return ;
                			     
                			     
                			}else{

                	    		$.ajax({
                	    			type : "post",
                	    			url : "/printer/updatePrinter",
                	    			data : {
                	    				printerName:printerName,id:id
                	    			},
                	    			dataType : "json",
                	    			success : function(data, index) {
                	    				var list=data.printerList;
                   	    		        var printerlistdiv="";
                	    				if(data.flag==0){
                        			        oTable.fnUpdate(id, nRow, 0, false);
                        	                oTable.fnUpdate(printerName, nRow, 1, false);
                        	                oTable.fnUpdate('<a data="'+id+'"  class="edit"  href="">Edit</a>', nRow, 2, false);
                        	                oTable.fnUpdate('<a  data="'+id+'" class="delete" href="">Delete</a>', nRow, 3, false);
                        	                //oTable.fnDraw();
                        	    			if(list.length>0){
                            					for(var printer in list){
                    	    						//taxTem
                	    							printerlistdiv+='<option value="'+list[printer].id+'">'+list[printer].printerName+'</option>';
                    	    					}
                            			     $("#selectRes").html(printerlistdiv);
                        	    			}  
                	    				}
                	    			},
                	    			error : function() {
                	    				bootbox.alert('Modify the failure!');
                	    			}
                	    		});
                				
                			}
                			
                			},
                			error : function() {
                				bootbox.alert('Validation error!');
                			}
                		});
                		

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
                    var aiNew = oTable.fnAddData(['', '',
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
                    		
                    		

                    		
                    		$.ajax({
                    			type : "post",
                    			url : "/printer/checkCount",
                    			data : {
                    				id:id
                    			},
                    			dataType : "json",
                    			success : function(data, index) {
                  
                    				if(data.flag==false){
                    					
                    					$.ajax({
                                			type : "post",
                                			url : "/printer/delPrinter",
                                			data : {
                                				id:id
                                			},
                                			dataType : "json",
                                			success : function(data, index) {
                                				var list=data.printerList;
                           	    		        var printerlistdiv="";
                                				if(data.flag==0){
                                					 /* oTable.fnDeleteRow(nRow);
                                						if(list.length>0){
                                        					for(var printer in list){
                                	    						//taxTem
                            	    							printerlistdiv+='<option value="'+list[printer].id+'">'+list[printer].printerName+'</option>';
                                	    					}
                                        					  $("#selectRes").html(printerlistdiv);
                                        					
                                        					
                                        				}*/
                                               	 location.reload();

                                					  
                                				}

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