var addFlag=true;
$(document).ready(function(){
	  jQuery.i18n.properties({
	    name:'js',
	    path:'/static/i18n/',
	    mode:'map',
	    //language:"en-US",
	    callback: function() {
	     // alert(jQuery.i18n.prop("areyousure"));
	    }
	  });
});

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
                jqTds[1].innerHTML = '<a class="edit" href="">'+jQuery.i18n.prop("printer.edit")+'</a>';
                jqTds[2].innerHTML = '<a class="cancel" href="">'+jQuery.i18n.prop("printer.cancel")+'</a>';
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
                				 bootbox.alert(jQuery.i18n.prop(" printer.error.same"));
                				
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
              	    	        	    oTable.fnUpdate('<a data="'+data.flag+'" class="edit" href="">'+jQuery.i18n.prop(" printer.error.edit")+'</a>', nRow, 2, false);
            	    	                oTable.fnUpdate('<a data="'+data.flag+'" class="delete" href="">'+jQuery.i18n.prop(" printer.error.delete")+'</a>', nRow, 3, false);
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
                	    				 bootbox.alert(jQuery.i18n.prop("printer.error.add"));
                	    			}
                	    		});
                				
                			}
                				
                			
                			},
                			error : function() {
                				 bootbox.alert(jQuery.i18n.prop("printer.error.validation"));
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
                				 bootbox.alert(jQuery.i18n.prop("printer.error.same"));                			     return ;
                			     
                			     
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
                        	                oTable.fnUpdate('<a data="'+id+'"  class="edit"  href="">'+jQuery.i18n.prop(" printer.error.edit")+'</a>', nRow, 2, false);
                        	                oTable.fnUpdate('<a  data="'+id+'" class="delete" href="">'+jQuery.i18n.prop(" printer.error.delete")+'</a>', nRow, 3, false);
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
                	    				 bootbox.alert(jQuery.i18n.prop("printer.error.modify"));           
                	    			}
                	    		});
                				
                			}
                			
                			},
                			error : function() {
                				 bootbox.alert(jQuery.i18n.prop("printer.error.validation")); 
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
                            '<a class="edit" href="">'+jQuery.i18n.prop(" printer.error.edit")+'</a>', '<a class="cancel" data-mode="new" href="">'+jQuery.i18n.prop(" printer.error.cancel")+'</a>'
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
                bootbox.confirm(jQuery.i18n.prop("areyousure"), function(result) {

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
                                				
                                				 bootbox.alert(jQuery.i18n.prop("printer.error.delete")); 

                                			}
                                		});	
                    					
                    					
                    				}else{
                    					
                    					 bootbox.alert(jQuery.i18n.prop("printer.error.deltip")); 
            	    					
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
                } else if (nEditing == nRow && this.innerHTML ==jQuery.i18n.prop("printer.save")) {
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