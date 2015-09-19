var editFlag=1;
var addOrderFlag=true;
function displayTds() {
	/*
	$("#editable-sample_order tr").each(function(){
		$("td:eq(0)",this).hide();
		$("td:eq(1)",this).css('width','10%');
		$("td:eq(2)",this).css('width','10%');
		$("td:eq(3)",this).css('width','30%');
		$("td:eq(4)",this).css('width','30%');
		$("td:eq(5)",this).hide();
		$("td:eq(6)",this).hide();
		$("td:eq(7)",this).hide();
		$("td:eq(8)",this).hide();
		$("td:eq(9)",this).hide();
		$("td:eq(10)",this).hide();
	})
	*/
     /*$("#editable-sample_order tr td:nth-child(1)").hide();  
	 $("#editable-sample_order tr td:nth-child(6)").hide();  
	 $("#editable-sample_order tr td:nth-child(7)").hide(); 
	 $("#editable-sample_order tr td:nth-child(8)").hide(); 
	 $("#editable-sample_order tr td:nth-child(9)").hide(); 
	 $("#editable-sample_order tr td:nth-child(10)").hide(); 
	 $("#editable-sample_order tr td:nth-child(11)").hide(); 
	 $("#editable-sample_order tr td:nth-child(2)").css('width','10%');
	 $("#editable-sample_order tr td:nth-child(3)").css('width','10%');
	 $("#editable-sample_order tr td:nth-child(4)").css('width','30%');
	 $("#editable-sample_order tr td:nth-child(5)").css('width','30%');*/
}


var TaxSettingOrderTable = function () {
    return {
        //main function to initiate the module
        init: function () {
            function restoreRow(oTable, nRow) {
                var aData = oTable.fnGetData(nRow);
                var jqTds = $('>td', nRow);
                for (var i = 0, iLen = jqTds.length; i < iLen; i++) {
                    oTable.fnUpdate(aData[i], nRow, i, false);
                }
                oTable.fnUpdate('<a data="'+oTable[0]+'" class="edit" href="">Edit</a>', nRow, 11, false);
                oTable.fnUpdate('<a data="'+oTable[0]+'" class="delete" href="">Delete</a>', nRow, 12, false);
            
                oTable.fnDraw();
            }
            function editRow(oTable, nRow) {
                editFlag++;
                var aData = oTable.fnGetData(nRow);
                var jqTds = $('>td', nRow);
                
                var optionHtml=$("#selectTax").html();
                var  onTaxVal1="";
                onTaxVal1+='<option value="0">value</option><option value="1">Tax#1</option>'
                
                var  onTaxVal2="";
                     onTaxVal2+='<option value="0">value</option><option value="1">tax#1</option><option value="2">tax#2</option>'	
                	
                //tax1
                var tax1="";
                tax1+='<div class="row"  style="margin-left: 30px;">'
                tax1+='<select onchange="Tax1Select(this)" id="tax1_'+editFlag+'" style="width:100px" class="form-control">'+optionHtml+'</select>';
				tax1+='</div>'
		
                var tax2="";
                tax2+='<div class="row" style="margin-right: 20px;" >'
                tax2+='<div class="col-md-5"><select id="tax2_'+editFlag+'" onchange="Tax2Select(this)" class="form-control" style="width:100px"><option value="">-Please Select-</option></select></div>';
                tax2+='<div class="col-md-2">on</div>';
                tax2+='<div class="col-md-5"><select id="tax2Onvalue_'+editFlag+'" class="form-control" style="width:100px">'+onTaxVal1+'</select></div>';
				tax2+='</div>'
				
			    var tax3="";
                tax3+='<div class="row"  style="margin-right: 20px;">'
                tax3+='<div class="col-md-5"><select id="tax3_'+editFlag+'" class="form-control" style="width:100px"><option value="">-Please Select-</option></select></div>';
                tax3+='<div class="col-md-2">on</div>';
                tax3+='<div class="col-md-5"><select id="tax3Onvalue_'+editFlag+'" class="form-control" style="width:100px">'+onTaxVal2+'</select></div>';
				tax3+='</div>'
					
                //jqTds[0].innerHTML = '<input type="hidden" class="form-control small" value="' + aData[0] + '">';
				jqTds[0].style.width="10%";
				jqTds[0].innerHTML = '<form id="form_grouptax_'+aData[0]+'"><input data="'+aData[0]+'" type="text" class="form-control small" value="' + aData[1] + '" required style="width:120px"></form>';
                jqTds[1].innerHTML = tax1;
                jqTds[2].innerHTML = tax2;
                jqTds[3].innerHTML = tax3;
                jqTds[4].innerHTML = '<a class="edit" href="">Save</a>';
                jqTds[5].innerHTML = '<a data="'+aData[0]+'" class="cancel" href="">Cancel</a>';

            }

            function saveRow(oTable, nRow) {
                var aData = oTable.fnGetData(nRow);
                
                var jqInputs = $('input', nRow);
                 //获取下拉列表中的值
                var taxIdOb = $('select', nRow);
                
                var taxNameObj = $('select option:selected', nRow);
                var tax1Id=taxIdOb[0].value == null ? 0 : taxIdOb[0].value;
                var tax1Name=taxNameObj[0].innerText== null ? '' : taxNameObj[0].innerText;
                var tax2Id=taxIdOb[1].value== '' ? 0 : taxIdOb[1].value;
                var tax2Name=taxNameObj[1].innerText== '-Please Select-' ? '' : taxNameObj[1].innerText;
                var tax2onId=taxIdOb[2].value
                var tax3Id=taxIdOb[3].value== '' ? 0 :taxIdOb[3].value;
                var tax3Name=taxNameObj[3].innerText== '-Please Select-' ? '' : taxNameObj[3].innerText;
                var tax3onId=taxIdOb[4].value
 
                var taxGroupId=jqInputs[0].attributes[0].value;
                var taxGroupName=jqInputs[0].value;
                
                
                var validator=$("#form_grouptax_"+taxGroupId).validate();
            	var check = validator.form();
        		
        		if(check){
                //新增数据持久化
                // ajax 请求数据录入
            	if (taxGroupId== '') {
            				$.ajax({
            	    			type : "post",
            	    			url : "/taxSetting/addTaxCategory",
            	    			data : {
            	    				taxCategoryName:taxGroupName,tax1Id:tax1Id,tax2Id:tax2Id,tax2OnValue:tax2onId,tax3Id:tax3Id,tax3OnValue:tax3onId
            	    			},
            	    			dataType : "json",
            	    			success : function(data) {  
            	    	        if(data.flag!=0){
            	    	        	location.reload();	
            	    	        }

            	    			},
            	    			error : function() {
            	    				bootbox.alert('The new failure！');
            	    			}
            	    		});
            			
           

            	} else {
            		
           
    				$.ajax({
    	    			type : "post",
    	    			url : "/taxSetting/updateTaxCategory",
    	    			data : {
    	    				taxGroupId:taxGroupId,taxCategoryName:taxGroupName,tax1Id:tax1Id,tax2Id:tax2Id,tax2OnValue:tax2onId,tax3Id:tax3Id,tax3OnValue:tax3onId
    	    			},
    	    			dataType : "json",
    	    			success : function(data) {  
    	    	        if(data.flag==0){
    	    	        	
    	    	          location.reload();
    	    	        }else{
        					
        					//bootbox.alert('新增失败！');
        				}
        				
    	    
    	    			
    	    			},
    	    			error : function() {
    	    				bootbox.alert('Modify the failure！');
    	    			}
    	    		});
    			
            		
            		
            	}
        		}
                
            }



            var oTable = $('#editable-sample_order').dataTable({
            	bFilter:false,
            	bLengthChange:false,
            	iDisplayLength:5,
            	"aoColumnDefs": [
                                 { "bSearchable": false, "bVisible": false, "aTargets": [ 0 ] },
                                 { "bVisible": false, "aTargets": [ 5 ] },
                                 { "bVisible": false, "aTargets": [ 6 ] },
                                 { "bVisible": false, "aTargets": [ 7 ] },
                                 { "bVisible": false, "aTargets": [ 8 ] },
                                 { "bVisible": false, "aTargets": [ 9 ] },
                                 { "bVisible": false, "aTargets": [ 10 ] }
                             ] 
            });

            jQuery('#editable-sample_order_wrapper .dataTables_filter input').addClass("form-control medium"); // modify table search input
            jQuery('#editable-sample_order_wrapper .dataTables_length select').addClass("form-control xsmall"); // modify table per page dropdown
            var nEditing = null;
            $('#editable-sample_new_order').click(function (e) {
            	if(addOrderFlag==true){
            	     e.preventDefault();
                     var aiNew = oTable.fnAddData(['', '', '', '','','','','','','','',
                             '<a class="edit" href="">Edit</a>', '<a class="cancel" data-mode="new" href="">Cancel</a>'
                     ]);
                     var nRow = oTable.fnGetNodes(aiNew[0]);
                     editRow(oTable, nRow);
                     nEditing = nRow;
       
                     addOrderFlag=false;
            		
            	}
           
            });

            
 
            
       
            
            $('#editable-sample_order').on('click', "a.delete",function (e) {
                e.preventDefault();

               /* if (confirm("Are you sure to delete this row ?") == false) {
                    return;
                }*/
                var id=$(this).attr("data");
                var nRow = $(this).parents('tr')[0];
                
                bootbox.confirm("Are you sure?", function(result) {

                    if (result) {
                    	
                    	if(id==undefined){
                    		
                    		 oTable.fnDeleteRow(nRow);
                    		addOrderFlag=true;
                    		
                    		
                    	}else{
                    		
                    		  // What to do here?
                        	$.ajax({
            	    			type : "post",
            	    			url : "/taxSetting/delTaxCategory",
            	    			data : {
            	    				id:id
            	    			},
            	    			dataType : "json",
            	    			success : function(data) {  
            	    	        if(data.flag!=0){
            	    	        	
            	    	        	 oTable.fnDeleteRow(nRow);
            	    	        	
            	    	        }else{
                					
                				}
                				
      
            	    			
            	    			},
            	    			error : function() {
            	    				bootbox.alert('Delete failed！');
            	    			}
            	    		});
                    		
                    	}
                     
                    	
                    	
                    } else {
                       // What to do here?
                    }               
                });
                
			
                
                
             
            });

            $('#editable-sample_order').on('click'," a.cancel" ,function (e) {
                e.preventDefault();
                if ($(this).attr("data") == "") {
                	 location.reload();
                } else {
                    restoreRow(oTable, nEditing);
                    nEditing = null;
                }
               
            });

            $('#editable-sample_order').on('click', "a.edit",function (e) {
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
                    
                    //给tax1赋值
                    $("#tax1_"+editFlag+" option").each(function(){
                    	if($(this).text()==aData[8]){
                    	$(this).attr("selected","true");
                    	}
                    });
                    //给tax2赋值
           	       var optionHtml=$("#selectTax").html();
           	       $("#tax2_"+editFlag).append(optionHtml);
           	       $("#tax3_"+editFlag).append(optionHtml);
       
           	       //给ONValue 设置值
           	       if(aData[6]!=''){
           	    	  $("#tax2Onvalue_"+editFlag+" option[value='"+aData[6]+"']").attr("selected","selected");   
           	       }else{
            	    	  $("#tax2Onvalue_"+editFlag+" option[value='0']").attr("selected","selected");   
           	       }
           	      if(aData[7]!=''){
         	    	  $("#tax3Onvalue_"+editFlag+" option[value='"+aData[7]+"']").attr("selected","selected");   
         	       }else{
         	    	  $("#tax3Onvalue_"+editFlag+" option[value='0']").attr("selected","selected");     
         	    	   
         	       }
           	       
                    $("#tax2_"+editFlag+" option").each(function(){
                    	if($(this).text()==aData[9]){
                    	$(this).attr("selected","true");
                    	}
                    	if($(this).text()==aData[8]){
                    		$(this).remove();
                    	}
                    
                    });
                    //给tax3赋值
                    $("#tax3_"+editFlag+" option").each(function(){
                    	if($(this).text()==aData[10]){
                    	$(this).attr("selected","true");
                    	}
                    	if($(this).text()==aData[8]){
                    		$(this).remove();
                    	}
                    	if($(this).text()==aData[9]){
                    		$(this).remove();
                    	}
                    });
                    
                }
                
                //编辑前给对象赋值
                
        
                
            });  
        }

    };

}();
//
function Tax1Select(v){
	   //$(v).prepend("<option value='111'>111111</option>");
	     var selectId=$(v).val();
	     var optionHtml=$("#selectTax").html();
	    
	     $("#tax2_"+editFlag).empty();
	     $("#tax3_"+editFlag).empty();
	     //找到下一个下拉框
	     $("#tax2_"+editFlag).append("<option value=''>-Please Select-</option>");
	     $("#tax3_"+editFlag).append("<option value=''>-Please Select-</option>");
	     $("#tax2_"+editFlag).append(optionHtml);
	     $("#tax2_"+editFlag+" option[value='"+selectId+"']").remove();
}
//
function Tax2Select(v){
	
	 var selectId2=$(v).val();
	     $("#tax3_"+editFlag).empty();
	     $("#tax3_"+editFlag).append("<option value=''>-Please Select-</option>");
	    if(selectId2!=''){
		 var optionHtml=$("#selectTax").html();
		 $("#tax3_"+editFlag).append(optionHtml);
		 var selectId1=  $("#tax1_"+editFlag).val();
	     $("#tax3_"+editFlag+" option[value='"+selectId2+"']").remove();
	     $("#tax3_"+editFlag+" option[value='"+selectId1+"']").remove(); 
	 }
    
}
