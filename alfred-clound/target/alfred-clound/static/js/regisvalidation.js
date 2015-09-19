var Script = function () {
	

    $().ready(function() {
        // validate the comment form when it is submitted
    	   // validate signup form on keyup and submit
		
		var validator=$("#signupFormUser").validate({
            rules: {
            	firstName: "required",
            	lastName: "required",
            	companyName:"required",
                password: {
                    required: true,
                    minlength: 5
                },
                confirm_password: {
                    required: true,
                    minlength: 5,
                    equalTo: "#password"
                }
            },
            messages: {
            	firstName: "Please enter your firstname",
            	lastName: "Please enter your lastname",
                password: {
                    required: "Please provide a password",
                    minlength: "Your password must be at least 5 characters long"
                },
                confirm_password: {
                    required: "Please provide a password",
                    minlength: "Your password must be at least 5 characters long",
                    equalTo: "Please enter the same password as above"
                },
                email: "Please enter a valid email address",
                companyName:"Please enter your companyname"
            }
        });
         
    	
    	
    	
    	$("#submintForm").click(function(){
  
    		var check = validator.form();
    		
    	    if (check) { 	 
    	    	
    		var AccountName=$("#accountName").val();
    		$.ajax({
                 type: "post",
                 url: "../../company/checkAccountName",
                 data: {account_name:AccountName},
                 dataType: "json",
                 success: function(data){
                     
                	 if(data.flag==true){
                		
                	   
                          $("#signupFormUser").submit();
                	 
                	 }else{
              
                		 alert('该账户已存在');
                		
                		 
                	 };

                 },error:function(){
                	 
                	 
                	 
                 }
             });
    		 
    	       } 
    		
    	})

        
    });
}();