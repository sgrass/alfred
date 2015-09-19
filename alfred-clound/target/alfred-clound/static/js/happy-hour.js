var HappyHourPanel = function () {
        
    return {
        //main function to initiate the module
        newpanel: function(container, paneltpl){
          var newpanel = $(paneltpl).html();
          $(container).prepend(newpanel);
          var paneladded = container+" .col-md-6:first-child"; 
          $(paneladded+' .timepicker-from').timepicker({"defaultTime":"value","showMeridian":false});
  		  $(paneladded+' .timepicker-to').timepicker({"defaultTime":"value","showMeridian":false});
  		  $(paneladded).show('slow');

	      //edit mode
	      var panels = $(paneladded+' .panel-body');
	      var header =  $(paneladded+' .panel-heading');
	      if ($(header).hasClass('.edit')){
	        $(header).removeClass('edit');
	      }else{
	        $(header).addClass('edit');
	      }
	      $.each (panels, function( index, div ) {
	         if ($(div).hasClass('hide')){
	            $(div).removeClass('hide');
	         }else {
	             $(div).addClass('hide');
	         }
	      });  
        },
        
        init: function () {
          $('.timepicker-from').timepicker({"defaultTime":"value","showMeridian":false});
  		  $('.timepicker-to').timepicker({"defaultTime":"value","showMeridian":false});
  		    		 
  		  $("#panel_container").on("click",'.happyhourpanel .cancelBtn', function(evt) {
		      //todo: recover data
		      var panelparent = $($(evt.currentTarget).parents()[2]);
		      var panels = $(panelparent.find('.panel-body'));
		      var header = $(panelparent.find('.panel-heading')[0]);      
		      header.removeClass('edit');
		      $.each (panels, function( index, div ) {
		         if ($(div).hasClass('hide')){
		            $(div).removeClass('hide');
		         }else {
		             $(div).addClass('hide');
		         }
		      });   
		  });
		  
		  $("#panel_container").on("click",'.happyhourpanel .saveBtn', function(evt) {
		      var panelparent = $($(evt.currentTarget).parents()[2]);
		      var panels = $(panelparent.find('.panel-body'));
		      var header = $(panelparent.find('.panel-heading')[0]); 
		      //todo: save data
		      
		      header.removeClass('edit');
		      $.each (panels, function( index, div ) {
		         if ($(div).hasClass('hide')){
		            $(div).removeClass('hide');
		         }else {
		             $(div).addClass('hide');
		         }
		      });      
		  });
		  $("#panel_container").on("click",'.happyhourpanel .hheditBtn', function(evt) {		 
		      var panelparent = $($(evt.currentTarget).parents()[2]);
		      var panels = $(panelparent.find('.panel-body'));
		      var header = panelparent.find('.panel-heading')[0];
		      if ($(header).hasClass('.edit')){
		        $(header).removeClass('edit');
		      }else{
		        $(header).addClass('edit');
		      }
		      $.each (panels, function( index, div ) {
		         if ($(div).hasClass('hide')){
		            $(div).removeClass('hide');
		         }else {
		             $(div).addClass('hide');
		         }
		      });  
		  });
        }
    };
}();