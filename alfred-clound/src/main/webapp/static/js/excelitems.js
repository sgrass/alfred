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
		    '/importExcel/loadTreeData?t='+new Date(),
		    function(data) {
		    	Restaurant.menu=data.mtlJson;
		    }
		);

	var $tree = $('#tree1');
	$tree.tree({
	    data: Restaurant.menu,
	    closedIcon: '+',
	    openedIcon: '-',
	    selectable: true,    
	    autoOpen: true
	});

	$("#btnRvcMenuAddAll").click(function(){
		$("#addNewForm").submit();
		})
		
});








