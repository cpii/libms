$(document).ready(function() {
	$("#book-form").validate({
        rules: {
            title: {
                required: true
            },
			cost: {
		        number: true
		    }
        },
        messages: {
            title: {
                required: "You must enter a title!"
            }
        }
    });
	
	$("#book-form-submit").on("click", function() {
		if ($("#book-form").valid()) {
			  $("#book-form").submit();
	    } else {
	    	console.log("invalid");
	    }
	});
	
	$("#published-date").datepicker({
		format: 'mm/dd/yyyy',
		todayHighlight: true,
		autoclose: true
			
	});	
	
	$("#book-search-button").on("click", function() {
		var query = $("#book-search-query").val();
		console.log(query);
		if (query) {
			$("#book-search-form").submit();
		} 
	});
});


//else {
//	window.open($("#ctxtrelurl").val()+"/books", "_self");
//}