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
        init: function (id) {
            function restoreRow(oTable, nRow) {
                var aData = oTable.fnGetData(nRow);
                var jqTds = $('>td', nRow);
                for (var i = 0, iLen = jqTds.length; i < iLen; i++) {
                	if(aData!=null){
                        oTable.fnUpdate(aData[i], nRow, i, false);
                	}
                }
                oTable.fnDraw();
            }
            function editRow(oTable, nRow) {
                var aData = oTable.fnGetData(nRow);
                var jqTds = $('>td', nRow);
                var checked="";
                if(aData[3]==1){
                	checked="checked" ;
                }else if(aData[3]==0){
                	checked="" ;
                }
                
                if(aData[0]==""){
                	checked="checked" ;
                }
                if(aData[5]==""){
                	aData[5]=4;
                }
                jqTds[0].innerHTML = '<form id="form_table_'+aData[0]+'"> <input data="'+aData[0]+'" data2="'+aData[2]+'" type="text" required class="form-control small" value="' + aData[4] + '">';
                jqTds[1].innerHTML = '<input type="text" name="digits" class="form-control small" value="' + aData[5] + '"></form>';
                jqTds[2].innerHTML = '<input type="checkbox" '+checked+' >';
                jqTds[3].innerHTML = '<a class="edit" href="">'+jQuery.i18n.prop("table.edit")+'</a>';
                jqTds[4].innerHTML = '<a class="cancel" href="">'+jQuery.i18n.prop("table.cancel")+'</a>';
            }
 
            function saveRow(oTable, nRow) {
                var aData = oTable.fnGetData(nRow);
                var jqInputs = $('input', nRow);
            	var revenueId=$("#revenueIdDiv input").val();
                //数据库持久化操作
                var id=jqInputs[0].attributes[0].value;;
                var placesId=jqInputs[0].attributes[1].value;;
                var tableName=jqInputs[0].value;
                var tablePacks=jqInputs[1].value;
                var isActivetem=jqInputs[2].checked;
                var isActive=0;
                if(isActivetem==true){
             	   isActive=1;
                }
                var validator=$("#form_table_"+id).validate({
             	   rules: {
             		  digits:{
             			 digits:true
             		   }
                    },
                    messages: {  
                    	digits:'please enter digits'
                    }
                });
         		var check = validator.form();
                
                if(check){
                    // ajax 请求数据录入
                	if (id== '') {
                		$.ajax({
        	    			type : "post",
        	    			url : "/placeTable/checkTables",
        	    			data : {
        	    				tableName:tableName,id:0,placesId:placesId,revenueId:revenueId
        	    			},
        	    			dataType : "json",
        	    			success : function(data) {
        	    				if(data.flag==false){
        	    					
        	    					 oTable.fnDeleteRow(nRow);
        	    					 addFlag=true;
     	    						bootbox.alert(jQuery.i18n.prop("table.error.same"));
        	    					 return ;
        	    				}else{
            					
        	    					$.ajax({
                    	    			type : "post",
                    	    			url : "/placeTable/addTables",
                    	    			data : {
                    	    				tableName:tableName,tablePacks:tablePacks,isActive:isActive,revenueId:revenueId,placesId:placesId
                    	    			},
                    	    			dataType : "json",
                    	    			
                    	    			success : function(data) {
                    	    				   
                    	    	        if(data.flag!=0){
                    	    	        	
                 	    	   	            oTable.fnUpdate(data.flag, nRow, 0, false);
                 	    	   	            oTable.fnUpdate(revenueId, nRow, 1, false);
                 	    	                oTable.fnUpdate(placesId, nRow, 2, false);
                 	    	                oTable.fnUpdate(isActive, nRow, 3, false);
                 	    	                oTable.fnUpdate(tableName, nRow, 4, false);
                 	    	                oTable.fnUpdate(tablePacks, nRow, 5, false);
                 	    	                oTable.fnUpdate(isActivetem, nRow, 6, false);
                 	    	                oTable.fnUpdate('<a data="'+data.flag+'" class="edit" href="">'+jQuery.i18n.prop("table.edit")+'</a>', nRow, 7, false);
                	    	                oTable.fnUpdate('<a data="'+data.flag+'" class="delete" href="">'+jQuery.i18n.prop("table.delete")+'</a>', nRow, 8, false);
                 	    	                //oTable.fnDraw();
                 	    	               addFlag=true;
                 	    	               // location.reload();
                        				}
                    	    	       
                    	    			
                    	    			}
                    	    		});	
        	    					
            				       }
        	    			
        	    			},
        	    			error : function() {
        	    				bootbox.alert(jQuery.i18n.prop("table.error.validation"));
        	    			}
        	    		});	
                		
                			
                	} else {
                		//table验证
                		$.ajax({
        	    			type : "post",
        	    			url : "/placeTable/checkTables",
        	    			data : {
        	    				tableName:tableName,id:id,placesId:placesId,revenueId:revenueId
        	    			},
        	    			dataType : "json",
        	    			success : function(data) {
        	    				if(data.flag==false){
        	    					 addFlag=true;
        	    						bootbox.alert(jQuery.i18n.prop("table.error.same"));
        	    	        	    					 return ;
        	    				     
        	    				     
        	    				}else{
                    	    		$.ajax({
                    	    			type : "post",
                    	    			url : "/placeTable/updateTables",
                    	    			data : {
                    	    				id:id,tableName:tableName,tablePacks:tablePacks,isActive:isActive,revenueId:revenueId
                    	    			},
                    	    			dataType : "json",
                    	    			success : function(data, index) {
                  
                    	    				if(data.flag==0){
                    	    					
                    	    					oTable.fnUpdate(id, nRow, 0, false);
                    	    					oTable.fnUpdate(revenueId, nRow, 1, false);
                    	    					oTable.fnUpdate(placesId, nRow, 2, false);
                     	    	                oTable.fnUpdate(isActive, nRow, 3, false);
                     	    	                oTable.fnUpdate(tableName, nRow, 4, false);
                     	    	                oTable.fnUpdate(tablePacks, nRow, 5, false);
                     	    	                oTable.fnUpdate(isActivetem, nRow, 6, false);
                    	    	                oTable.fnUpdate('<a class="edit" data="'+data.flag+'" href="">'+jQuery.i18n.prop("table.edit")+'</a>', nRow, 7, false);
                    	    	                oTable.fnUpdate('<a class="delete" data="'+data.flag+'" href="">'+jQuery.i18n.prop("table.delete")+'</a>', nRow, 8, false);
                    	    	                //oTable.fnDraw();
                    	    	                addFlag=true;
                    	    	        		//location.reload();
                    	    					//alert('修改成功！');
                    	    				}
                    	    				
                    	    			},
                    	    			error : function() {
                    	    				bootbox.alert(jQuery.i18n.prop("table.error.modify"));
                    	    			}
                    	    		});
        	    					
            				       }
        	    	
        	    			
        	    			},
        	    			error : function() {
        	    				bootbox.alert(jQuery.i18n.prop("table.error.validation"));
        	    			}
        	    		});	
                	}
                    
                	
                }
                
                
            }

   
            var oTable = $('#editable-sample_'+id).dataTable({
            	bFilter:false,
            	bLengthChange:false,
            	iDisplayLength:5,
            	"aoColumnDefs": [
                                 { "bSearchable": false, "bVisible": false, "aTargets": [ 0 ] },
                                 { "bVisible": false, "aTargets": [ 1 ] },
                                 { "bVisible": false, "aTargets": [ 2 ] },
                                 { "bVisible": false, "aTargets": [ 3 ] }
                                 
                             ] 

            });

            var nEditing = null;
            $('#editable-sample_new_'+id).click(function (e) {
            	if(addFlag==true){
            		  e.preventDefault();
                      //
                      var revenueId=$(this).attr("data");
                      var placeId=$(this).attr("dataRevenueId");
                      var aiNew = oTable.fnAddData(['', revenueId, revenueId, '','','','',
                              '<a class="edit" href="">'+jQuery.i18n.prop("table.edit")+'</a>', '<a class="cancel" data-mode="new" href="">'+jQuery.i18n.prop("table.cancel")+'</a>'
                      ]);
                      var nRow = oTable.fnGetNodes(aiNew[0]);
                      editRow(oTable, nRow,revenueId);
                      nEditing = nRow;

                      addFlag=false;
            	}
            	
              
            });

            $('#editable-sample_'+id+'').on('click',' a.delete', function (e) {
                e.preventDefault();
                
                var id=$(this).attr("data");
                var nRow = $(this).parents('tr')[0];
             
                bootbox.confirm(jQuery.i18n.prop("areyousure"), function(result) {
                    if (result) {
                    	if(id==undefined){
                    		addFlag=true;
                    		oTable.fnDeleteRow(nRow);
                    		
                    	}else{
                    		
                    		$.ajax({
                    			type : "post",
                    			url : "/placeTable/delTables",
                    			data : {
                    				id:id
                    			},
                    			dataType : "json",
                    			success : function(data, index) {
                    				
                    				if(data.flag==0){
                    					   oTable.fnDeleteRow(nRow);
                    					   addFlag=true;
                    				}

                    			},
                    			error : function() {
                    				
                    				bootbox.alert(jQuery.i18n.prop("table.error.delete"));

                    			}
                    		});
                    		
                    	}
                    }              
                });
                
           
               
             
            });

            $('#editable-sample_'+id+'').on('click','a.cancel',function (e) {

                //location.reload();
            });

            $('#editable-sample_'+id+'').on('click','a.edit', function (e) {
                e.preventDefault();

                /* Get the row as a parent of the link that was clicked on */
                var nRow = $(this).parents('tr')[0];

                if (nEditing !== null && nEditing != nRow) {
                    /* Currently editing - but not this row - restore the old before continuing to edit mode */
                    restoreRow(oTable, nEditing);
                    editRow(oTable, nRow);
                    nEditing = nRow;
                } else if (nEditing == nRow && this.innerHTML == '+jQuery.i18n.prop("table.save")+') {
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

//初始化数据
jQuery(document).ready(function() {
	//初始化表格
	var ps=$("#placeIdDiv input").val();
	var psstrs= new Array();
	psstrs = ps.split(",");
	for(var i=0;i<psstrs.length;i++){
		var placeId=psstrs[i];
		if(placeId!=''){
			EditableTable.init(placeId);
		}
	}

	//隐藏要隐藏的表格字段
	//展开选中的菜单
    $("#rtManagerMenuA").addClass("active");
    $("#rtManagerSub").css('display','block');
    $("#placetableLi").addClass("active");
    
	//点击Tab 选中Tab
	var revenueId=$("#revenueIdDiv input").val();
	$('#li_'+revenueId).addClass("active");
	
	//点击新增place按钮显示新增的表格
	$("#add_place__new").click(function(){
		$("#addPlaces").css('display','block');
	});
	
	
	//点击保存按钮保存place数据
	$("#addPlaceBut").click(function(){
		//获取表格中的数据
		var placeName=$("#addplacetable input:eq(0)").val();
		var placeDescription=$("#addplacetable input:eq(1)").val();
		var isCheck=$("#addplacetable input:eq(2)").is(':checked');
		var revenueId=$("#addplacetable input:eq(3)").val();
		var isActive=1;
		if(isCheck==false){
			isActive=0;
		}
		
		  var validator=$("#addplacetablefrom").validate();
    	  var check = validator.form();
		
		if(check){
			//数据验证
			$.ajax({
				type : "post",
				url : "/placeTable/checkPlace",
				data : {
					placeName:placeName,id:0,revenueId:revenueId
				},
				dataType : "json",
				success : function(data) {

				if(data.flag==false){
					
					bootbox.alert(jQuery.i18n.prop("place.error.same"));
					 
				     return ;
				}else{


				 	//ajax 保存数据
					$.ajax({
						type : "post",
						url : "/placeTable/addPlace",
						data : {
							placeName:placeName,placeDescription:placeDescription,isActive:isActive,revenueId:revenueId
						},
						dataType : "json",
						success : function(data) {

				        if(data.flag!=0){
							  //alert('新增成功！');
							  
				    	      location.reload();
						}
						
				
						
						},
						error : function() {
							bootbox.alert(jQuery.i18n.prop("place.error.add"));
						}
					}); 
					
				}
				
				},
				error : function() {
					bootbox.alert(jQuery.i18n.prop("table.error.validation"));
				}
			});
			
			
			
		}
	
		
		
		
		
	});
	//点击编辑 修改Place 里面的东西
	
	
	//点击编辑 修改Place 里面的东西
	$(".btn-success").click(function(){
		var placeId=$(this).attr("data");
		$("#placeaddtable_"+placeId).css('display','none');
		$("#placeedittable_"+placeId).css('display','block');
	});
	//更新区域
	$(".editPlaceBut").click(function(){
		var placeId=$(this).attr("data");
		var placeName=$("#place_"+placeId+" input:eq(0)").val();
		var placeDescription=$("#place_"+placeId+" input:eq(1)").val();
		var isCheck=$("#place_"+placeId+" input:eq(2)").is(':checked');
		var revenueId=$("#place_"+placeId+" input:eq(3)").val();
		
		var isActive=1;
		if(isCheck==false){
			isActive=0;
		}
		
		//
	    var validator=$("#form_"+placeId).validate();
    	var check = validator.form();
		
		if(check){
		//数据验证
		$.ajax({
			type : "post",
			url : "/placeTable/checkPlace",
			data : {
				placeName:placeName,id:placeId,revenueId:revenueId
			},
			dataType : "json",
			success : function(data) {

			if(data.flag==false){
				
				
				bootbox.alert(jQuery.i18n.prop("place.error.same"));
			     
			     
			}else{
				//ajax 编辑保存数据
				$.ajax({
					type : "post",
					url : "/placeTable/updatePlaces",
					data : {
						id:placeId,placeName:placeName,placeDescription:placeDescription,isActive:isActive,revenueId:revenueId
					},
					dataType : "json",
					success : function(data) {

			        if(data.flag!=0){
						  //alert('修改成功！');
			    	      location.reload();
					}
					
					},
					error : function() {
						bootbox.alert(jQuery.i18n.prop("table.error.modify"));
					}
				}); 
				
			}
			
			},
			error : function() {
				bootbox.alert(jQuery.i18n.prop("table.error.validation"));
			}
		});
		
		}
		
		
	
		
	});
	//删除区域
	$(".delPlaceBut").click(function(){
		var placeId=$(this).attr("data");
		/* if (confirm("Are you sure to delete this row ?") == false) {
             return;
         }*/
		
	    bootbox.confirm(jQuery.i18n.prop("areyousure"), function(result) {

            if (result) {
               // What to do here?
        		//ajax 编辑保存数据
        		$.ajax({
        			type : "post",
        			url : "/placeTable/delPlaces",
        			data : {
        				id:placeId
        			},
        			dataType : "json",
        			success : function(data) {

        	        if(data.flag==0){
        				  //alert('删除成功！');
        	    	      location.reload();
        			}
        			
        			},
        			error : function() {
        				bootbox.alert(jQuery.i18n.prop("table.error.delete"));
        			}
        		}); 
            	
            } else {
               // What to do here?
            }               
        });
	});
	
  $("#cancenPlaceBut").click(function(){
	  
	  $("#addPlaces").css('display','none');
	  
  })

	
});
