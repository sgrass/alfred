var Restaurant = {};
var RevenueCentreMenuData = {};
RevenueCentreMenuData.menu = [];
Restaurant.hassub = true;
Restaurant.menu=[];
Restaurant.menu2=[];
Restaurant.updateId=[];

Restaurant.getFirstLevelData = function(nodes) {
    if (! nodes) {
        nodes = Restaurant.menu;
    }

    var data = [];

    $.each(nodes, function() {
        var node = {
            label: this.label,
            id: this.id
        };

        if (this.children) {
            node.load_on_demand = true;
        }

        data.push(node);
    });

    return data;
}

Restaurant.getChildrenOfNode = function(node_id) {
    var result = null;

    function iterate(nodes) {
        $.each(nodes, function() {
            if (result) {
                return;
            }
            else {
                if (this.id == node_id) {
                    result = this;
                }

                if (this.children) {
                    iterate(this.children);
                }
            }
        });
    }

    iterate(Restaurant.menu);

    return Restaurant.getFirstLevelData(result.children);
}

function isProductItem(node) {
  if (node.children.length ==0)
    return true;
  return false;
}

function isSubCategory(node) {
  if (!Restaurant.hassub) return false;
  if (node.children.length>0 && node.parent!=null && node.parent.parent != null)
    return true;
  return false;
}

function isMainCategory(node) {
  if (node.children.length>0 && node.parent.parent==null)
    return true;
  return false;
}


$(document).ready(function() {
	//数据同步加载
	$.ajaxSettings.async = false;  
	//load 数据
	$.getJSON(
		    '/rvcMenu/loadTreeData?t='+new Date(),
		    function(data) {
		    	Restaurant.menu=data.mtlJson;
		    }
		);
	$.getJSON(
		    '/rvcMenu/	loadUpdateTreeData?t='+new Date()+'&revenueId='+revenueId,
		    function(data) {
		    	Restaurant.menu2=data.mtlJson;
		    }
		);
	

	
	var $tree = $('#tree1');
	var $treervc = $('#tree2');
	
	
	
	
	
	$tree.tree({
	    data: Restaurant.menu,
	    closedIcon: '+',
	    openedIcon: '-',
	    selectable: true,    
	    autoOpen: true
	});
	
	$treervc.tree({
	    data: Restaurant.menu2,
	    closedIcon: '+',
	    openedIcon: '-',    
	    autoOpen: true,
	    selectable: false,
        onCreateLi: function(node, $li) {
            $li.find('.jqtree-element').append(
                '<a class="tree-actions"><i class="fa fa-trash-o editSelected"  data-node-id="'+ node.id +'"></i></a>'
            );
        }	    
	});
	
	
	var oldUpdateNodes=$treervc.tree('getTree').id_mapping;
	 Restaurant.updateId=[];
	   $.each(oldUpdateNodes, function(idx) {
		   var node = oldUpdateNodes[idx];
		   if (isProductItem(node)) {
			   Restaurant.updateId.push(node.id);
            }
	    });
	
	$tree.bind(
	    'tree.click',
	    function(event) {
	        var node = event.node;
	        var flg = isProductItem(node);
	        if (flg){
	          addMenuNode(node);
	        } else if (isSubCategory(node)){
	            addSubCategoryNode(node);
	        } else {
	           flg = isMainCategory(node);
	           if (flg){
	             addMenuUnderOneMainCategoryNode(node);
	           }
	        }
	    }
	);

    $treervc.on(
        'click', '.editSelected',
        function(e) {
            var maincatNode = null;
            var subcatNode = null;
            
            var node_id = $(e.target).data('node-id');
            var node = $treervc.tree('getNodeById', node_id);
            var flg = isProductItem(node);
	        if (flg){
	          subcatNode = node.parent;
	          maincatNode = subcatNode.parent;
	        } else if (isSubCategory(node)){
	           maincatNode = node.parent;
	        }
            if (node) {
                $treervc.tree('removeNode', node);
            }
            if (subcatNode && subcatNode.children.length==0) {
               $treervc.tree('removeNode', subcatNode);
            }
            if (maincatNode && maincatNode.children.length==0) {
               $treervc.tree('removeNode', maincatNode);
            }
        }
    );
    		
	$('#btnRvcMenuEdit').bind('click', function(){
		var nodes =$treervc.tree('getTree').id_mapping;
		//var oldNodes=$tree.tree('getTree').id_mapping;
		var result ="";
           var resultArray=[];
		   $.each(nodes, function(idx) {
			   var node = nodes[idx];
			   if (isProductItem(node)) {
	            	result+=node.id+",";
	            	resultArray.push(node.id);
	            }
		    });
		  
			   
		  //比对获取要删除的Id
		
			   var arrList=Restaurant.updateId;
			   for(var i=0;i<Restaurant.updateId.length;i++){
				   for(var j=0;j<resultArray.length;j++){
					   if(Restaurant.updateId[i]==resultArray[j]){
						   //相同时清楚原先数组里面的值
						   arrList.splice(i,1); 
					   }
				   }
			   }
			   
			  //要删除的ID
			   var removeItemDetailId="";
			   if(arrList.length>0){
				   for(var i=0;i<arrList.length;i++){
					   removeItemDetailId+=arrList[i]+',';
					   
				   }
			   }
		   result=result.substring(0,result.length-1);
		   if(removeItemDetailId.length>0){
			   removeItemDetailId=removeItemDetailId.substring(0,removeItemDetailId.length-1); 
		   }
		  
	      $.getJSON(
				'/rvcMenu/addRevenueCenterMenuTree',
				{revenueId:revenueId,itemDetailIds:result,removeItemDetailId:removeItemDetailId},
			    function(data) {
					window.location.href='/reveuneItem/queryRevenueItem?revenueId='+revenueId;
			    }
			);
		
	});

	$('#btnRvcMenuAddAll').bind('click', function(){
	  $treervc.tree('loadData', Restaurant.menu);
	});
	

	function addMainCategoryNode(node) {
	   var flg = $treervc.tree('getNodeById',node.id);
	   if (flg ==undefined || flg==null) {
	     $treervc.tree('appendNode',node);
	   }
	}
	
	function addMenuUnderOneMainCategoryNode(node) { 
	   addMainCategoryNode(node);
	   for (var i=0;i<node.children.length;i++) {
	      addSubCategoryNode(node.children[i]);
	   }
	}
	
	function addSubCategoryNode(node) {
	   var parentnode = node.parent;
	   var subnode_id = node.id;
	   var mainnode_id = parentnode.id
	    
	   var mainnode = $treervc.tree('getNodeById',mainnode_id);
	   if (mainnode==undefined) {
	     addMainCategoryNode(parentnode);
	     mainnode = $treervc.tree('getNodeById',mainnode_id);
	   }
	   if ($treervc.tree('getNodeById',subnode_id)==undefined) {
	     $treervc.tree('appendNode',node,mainnode);
	   }
	   
	   //add all children 
	   var orgSubNodes = $tree.tree('getNodeById',node.id);
	   var newSubNode= $treervc.tree('getNodeById',node.id);
	   
	   for (var i=0; i < orgSubNodes.children.length; i++) {
		    var child = orgSubNodes.children[i];
			if ($treervc.tree('getNodeById',child.id)==undefined) {
			    $treervc.tree('appendNode',child,newSubNode);
			}
		}	    
	}
	
	function addMenuNode(node) {
	   var menu_id = node.id;
	   var subcatnode = node.parent;
	   var subnode_id = subcatnode.id;
	   var mainnode_id = subcatnode.parent.id;
	    
	   var mainnode = $treervc.tree('getNodeById',mainnode_id);
	   if (mainnode==undefined) {
	     addMainCategoryNode(subcatnode.parent);
	     mainnode = $treervc.tree('getNodeById',mainnode_id);
	   }
	   if ($treervc.tree('getNodeById',subnode_id)==undefined) {
	     $treervc.tree('appendNode',subcatnode,mainnode);
	   }
	   var subcatnode = $treervc.tree('getNodeById',subnode_id);
	   //menu
	   if ($treervc.tree('getNodeById',menu_id)==undefined) {
		  $treervc.tree('appendNode',node,subcatnode);
	   }
	}
		
});
