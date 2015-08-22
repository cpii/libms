$(document).ready(function() {
	
	var loadAuthorTable = function() {
		$.ajax({
		      url: $("#ctxtrelurl").val() + "authors/", // libmst/authors
		      method: "get",		      
		      success: function(data, textStatus, jqXHR) {
		        $("#author-table-container").html(data);
		      },
		      error: function(jqXHR, textStatus, errorThrown) {
		    	  $("#author-table-container").html("Error encountered on the server-side");
		      }     
		});
	};
	
	loadAuthorTable();
	
	// Initialize Create-Author modal dialog
	$("#author-create-dialog").modal({
		backdrop: "static",
		keyboard: true,
		show: false		
	});
	
	// Initialize Edit-Author modal dialog
	$("#author-edit-dialog").modal({
		backdrop: "static",
		keyboard: true,
		show: false		
	});
  
  // Initialize date-picker
  $(".need-datepicker").datepicker({
		format: 'mm/dd/yyyy',
		todayHighlight: true,
		autoclose: true			
	});
	
  ///////////////////////////////////////////////////////////////////////////////////////////
	// Create Author Link
	// Attempt 1
	/*$("#author-create-link").on("click", function() {
		$("#author-create-dialog").modal('show');
		
	});*/
		
	 // Attempt 2
	$("#author-container").on("click", "#author-create-link", function() {
		$("#author-create-dialog").modal('show');		
	});
	 
	 /////////////////////////////////////////////////////////////////////////////////////////	
	 $("#author-create-button").on("click", function() {
		 $('#author-form').ajaxSubmit({
			beforeSubmit: function() {
		    $('#author-form').validate({  
		    	rules : {
		    		"firstName" : {
						required : true
					},
					"lastName" : {
						required : true
					},
					"birthday" : {
						required : true
					}
				},
				messages: {
					"firstName": {
						required: "&nbsp;&nbsp; First Name is a required field",
						validName: "&nbsp;&nbsp;Invalid characters entered"
					},
					"lastName": {
						required: "&nbsp;&nbsp; Last Name is a a required field",
						validName: "&nbsp;&nbsp;Invalid characters entered"
					},
					"birthday": {
						required: "&nbsp;&nbsp; Birthday is a required field",
						validName: "&nbsp;&nbsp;Invalid characters entered"
					},
				}
			 });
			 return $('#author-form').valid();
			},
			preventDefault: true,
			clearForm: true,
			url: $("#ctxtrelurl").val() + "authors",
			type: 'post',			
			error: function(xhr, status, errorThrown) {
				$("#author-create-dialog .alert-danger").html(xhr.responseText);
			},
			success: function(responseText, statusText, xhr) {					
				$("#author-create-dialog").modal('hide');
				
				// Refresh profile details
				loadAuthorTable();
			}
		});
	 });
	 
	 /////////////////////////////
	 // Show Author Edit form 
	 $("#author-container").on("click", ".author-edit-button", function() {
		 var authorId = $(this).attr("data-author-id");
		 $.ajax({
		      url: $("#ctxtrelurl").val() + "authors/" +authorId + "/edit", // libmst/authors
		      method: "get",		      
		      success: function(data, textStatus, jqXHR) {		    	  		       
		    	  $("#author-edit-dialog .modal-body").html(data);
		    	  $("#author-edit-dialog").modal("show");
		    	
		    	  // Initialize date-picker
		    	  $(".need-datepicker").datepicker({
		    			format: 'mm/dd/yyyy',
		    			todayHighlight: true,
		    			autoclose: true			
		    		});
		      },
		      error: function(jqXHR, textStatus, errorThrown) {
		    	  $("#author-table-container").html("Error encountered on the server-side");
		      }     
		});
	 });
	 
	 // Submit author edit changes
	 $("#author-edit-submit-button").on("click", function() {
		 var authorId = $("#author-form-edit #id").val();		 
		 $('#author-form-edit').ajaxSubmit({
				beforeSubmit: function() {
			    $('#author-form-edit').validate({  
			    	rules : {
			    		"firstName" : {
							required : true
						},
						"lastName" : {
							required : true
						},
						"birthday" : {
							required : true
						}
					},
					messages: {
						"firstName": {
							required: "&nbsp;&nbsp; First Name is a required field",
							validName: "&nbsp;&nbsp;Invalid characters entered"
						},
						"lastName": {
							required: "&nbsp;&nbsp; Last Name is a a required field",
							validName: "&nbsp;&nbsp;Invalid characters entered"
						},
						"birthday": {
							required: "&nbsp;&nbsp; Birthday is a required field",
							validName: "&nbsp;&nbsp;Invalid characters entered"
						},
					}
				 });
				 return $('#author-form-edit').valid();
				},
				preventDefault: true,
				clearForm: true,
				url: $("#ctxtrelurl").val() + "authors/" + authorId,
				type: "post",	
				data: {_method: "put"},
				error: function(xhr, status, errorThrown) {
					$("#author-edit-dialog .alert-danger").html(xhr.responseText);
				},
				success: function(responseText, statusText, xhr) {					
					$("#author-edit-dialog").modal('hide');
					
					// Refresh profile details
					//loadAuthorTable();
					
					//$("#author-container").trigger("author-edited", {"authorId": authorId});
				}
			});
	 });
	 
	 ////////////////////////////
	 
	 $("#author-container").on("author-edited", function(e, param) {
		console.log("author-edited: authorId=" + param.authorId);
	 });
});
