/**
 * 用户权限 设置JS
 */

//权限保存方法提交
$("button").click(function(){
//获取要更改的权限信息
var id=$(this).attr("data");
var indexLength=$("#table_"+id+" tr").length;
//字符串拼接要修改的权限信息
//获取修改的是状态
var content="";
var userId=$("#divUserId input").val();
var checkAll=0;
for(var i=1;i<indexLength;i++){

	var checkKey=$("#table_"+id+" tr:eq("+i+") td:eq(0)").text();
	var premissionId=$("#table_"+id+" tr:eq("+i+") td:eq(2)").text();
	
	var checkStatus=$("#table_"+id+" tr:eq("+i+") td div div").attr("class");
	
	if(checkStatus.indexOf("switch-on")>=0){
		if(i==1){
			content+=userId+","+premissionId+","+checkKey+","+0;
		}else{
			content+=";"+userId+","+premissionId+","+checkKey+","+0;
		}
	}else{
		if(i==1){
			content+=userId+","+premissionId+","+checkKey+","+1;
		}else{
			
			content+=";"+userId+","+premissionId+","+checkKey+","+1;
		}
		checkAll=1;
	}
      
}
	//Ajax 数据提交
bootbox.confirm("Are you sure?", function(result) {
	if(result){
		
		$.ajax({
			type : "post",
			url : "/userPermission/updateUserPermission",
			data : {
				content:content,checkAll:checkAll,userId:userId
			},
			dataType : "json",
			success : function(data, index) {
				//bootbox.alert('修改成功！');
			},
			error : function() {
				bootbox.alert('Modify the failure！');
			}
		});
		
		
	}else{
		
		location.reload();
	}
	
})
	

	
});



