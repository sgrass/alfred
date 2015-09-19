(function ($) {
    "use strict";
    $(document).ready(function () {
    	//ajax请求
        var startTime=$('input[name="daterangepicker_start"]').val();
        var endTime=$('input[name="daterangepicker_end"]').val();
        getSalesChar(startTime,endTime);
    	getPieData();
    	
    	
        $(document).on('click', '.event-close', function () {
            $(this).closest("li").remove();
            return false;
        });

        $('.progress-stat-bar li').each(function () {
            $(this).find('.progress-stat-percent').animate({
                height: $(this).attr('data-percent')
            }, 1000);
        });

        $('.todo-check label').click(function () {
            $(this).parents('li').children('.todo-title').toggleClass('line-through');
        });


        $(document).on('click', '.todo-remove', function () {
            $(this).closest("li").remove();
            return false;
        });


        $('.stat-tab .stat-btn').click(function () {
            $(this).addClass('active');
            $(this).siblings('.btn').removeClass('active');

        });

        $('select.styled').customSelect();
        $("#sortable-todo").sortable();
    });
    
})(jQuery);

//获取退表值
function getSalesChar(startTime,endTime){
	 // Use Morris.Area instead of Morris.Line
	$.ajax({
		type : "post",
		url : "/dashboard/ajaxDataChart",
		dataType : "json",
		data:{startTime:startTime,endTime:endTime},
		success : function(data, index) {
			$("#graph-area").html("");
			$("#revenueList").html("");
			
			var gridSales= Morris.Area({
                element: 'graph-area',
                padding: 10,
                behaveLikeLine: true,
                gridEnabled: false,
                gridLineColor: '#dddddd',
                axes: true,
                fillOpacity: .7,
                data:data.orderList,
                lineColors: ['#ED5D5D', '#D6D23A', '#a94442','#32D2C9'],
                xkey: 'time',
                ykeys: ['subTotal', 'tax', 'discount','total'],
                labels: ['subTotal', 'tax', 'discount','total'],
                pointSize: 0,
                lineWidth: 0,
                hideHover: 'auto'
            });
		var revenue=data.revenueCenterList;
		var revenueCenters="";
		var total=0;
		for(var i=0;i<revenue.length;i++){
			revenueCenters+='<li class="stat-divider">';
			revenueCenters+=' <span class="first-city">$  '+revenue[i].total+'</span>';
			revenueCenters+=revenue[i].revName;
			revenueCenters+='</li>';
			total+=revenue[i].total;
		}
		
		$("#revenueTotal").text("$"+total);
		$("#revenueList").html(revenueCenters);
		}
	});
}

function getPieData(){
    //AJAX
    $.ajax({
		type : "post",
		url : "/dashboard/ajaxDataPie",
		dataType : "json",
		success : function(data, index) {
			  var pieItemCategoryData=data.itemCategory;
			  var pieItemData=data.items;
			  var itemCategoryPie = new Chart(document.getElementById("pie-chart-js").getContext("2d")).Pie(pieItemCategoryData);
			  //分装table
			  var itemPie = new Chart(document.getElementById("item-pie-chart-js").getContext("2d")).Pie(pieItemData);
			  var itemCategoryTbody="";
			  var itemTbody="";
			  for(var i=0;i<pieItemCategoryData.length-1;i++){
				  
				  itemCategoryTbody+="<tr>";
				  itemCategoryTbody+="<td><span class='bar-legend-pointer' style='background:"+pieItemCategoryData[i].color+"'></span></td>";
				  itemCategoryTbody+="<td>"+pieItemCategoryData[i].name+"</td><td>"+pieItemCategoryData[i].value+"</td>";
				  itemCategoryTbody+="</tr>";
				  
			  }
              for(var i=0;i<pieItemData.length-1;i++){
            	  itemTbody+="<tr>";
            	  itemTbody+="<td><span class='bar-legend-pointer' style='background:"+pieItemData[i].color+"'></span></td>";
            	  itemTbody+="<td>"+pieItemData[i].name+"</td><td>"+pieItemData[i].value+"</td>";
            	  itemTbody+="</tr>";
			  }
			  $("#itemCategoryPie").html(itemCategoryTbody);
			  $("#itemPie").html(itemTbody);
	  
		}
	});
     
}
