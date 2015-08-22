<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<link rel="stylesheet"
	href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">
<link rel="stylesheet" type="text/css"
	href='<c:url value="/resources/css/libms.css" />' />
<head>
<script src='<c:url value="/resources/js/jquery-1.11.1.min.js" />'></script>
<script
	src='<c:url value="/resources/js/jquery-migrate-1.2.1.min.js" />'></script>
<script
	src='<c:url value="/resources/bootstrap-3.2.0/js/bootstrap.min.js" />'></script>
<script
	src="http://cdn.jsdelivr.net/jquery.validation/1.13.1/jquery.validate.js"></script>
<style type="text/css">
div.demo-div {
	border: 1px solid black;
}
</style>
<script type="text/javascript">
$(document).ready(function() {
	$("#ajax-demo-1").on("click", function() {
		console.log("I was indeed called........");		
		$("#demo-div-1").load("/libmst/public/ajax/simpletextline");
	});
	
	$("#ajax-demo-2").on("click", function() {
	    $("#demo-div-2").load("/libmst/public/ajax/dynamicontent");
	});
	
	$("#ajax-demo-3").on("click", function() {
		$.ajax({
			url: "/libmst/public/ajax/simpletextline",
			success: function(data, textStatus, jqXHR) {
				$("#demo-div-3").html(data);
			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.log("hohum");
			}			
		});
	});
	
	$('#exampleModal').modal({
	    keyboard: false,
	    show: false
	  })  
	  
	  $("#modal-demo-1").on("click", function () {
	    $("#exampleModal").modal("show");
	  });
	
	$("#demo-form-submit-button").on("click", function() {
		console.log($("#demoform1").serialize());
	    $.ajax({
	      url: "/libmst/public/ajax/formsubmit1",
	      method: "post",
	      data: $("#demoform1").serialize(),
	      success: function(data, textStatus, jqXHR) {
	        $(".modal-body").html(data);
	        $("#exampleModal").modal("show");
	      },
	      error: function(jqXHR, textStatus, errorThrown) {
	    	  $("#demo-div-4").html("Error encountered on the server-side");
	      }     
	    });
	  });
	
	
});
</script>	
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-6">
				<h3>Ajax Demo1</h3>
				<button id="ajax-demo-1" class="top20">Load the following div using ajax</button>
				<div id="demo-div-1" class="demo-div top20">
					<p>The contents of this div will be fetched dynamically from the server using ajax.</p>
				</div>
				
				<h3>Ajax Demo2 - Fetch a table dynamically</h3>
        <button id="ajax-demo-2" class="top20">Loads a table dynamically using ajax</button>
        <div id="demo-div-2" class="demo-div top20"><p>The contents of this div will be fetched dynamically from the server using ajax.</p></div>
			</div>
		</div>		
		
		<div class="row">
			<div class="col-xs-6">
				<h3>Ajax Demo3</h3>
				<button id="ajax-demo-3" class="top20">Load the following div using ajax</button>
				<div id="demo-div-3" class="demo-div top20"><p>The contents of this div will be fetched dynamically from the server using ajax.</p></div>
			</div>
		</div>
		
		<div class="row">
      <div class="col-xs-3">
        <h3>Ajax Demo4</h3>
        <form id="demoform1">
           <div class="form-group">
              <label for="exampleInputEmail1">Email address</label>
              <input type="email" class="form-control" id="exampleInputEmail1" name="email" placeholder="Enter email">
           </div>
           <div class="form-group">
              <label for="name">Name</label>
              <input type="text" class="form-control" id="name" name="name" placeholder="Enter name">
           </div>
           <div class="form-group">
              <label for="exampleInputPassword1">Password</label>
              <input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
           </div>           
           <div class="checkbox">
              <label>
                 <input name="simulate-error" type="checkbox"> Simulate Error on Server
              </label>
           </div>
           <button type="button" id="demo-form-submit-button" class="btn btn-default">Submit</button>
        </form>        
        <div id="demo-div-4" class="demo-div top20"><p>Submit the form to the server and display the server's response in this div.</p></div>
      </div>
    </div>
    
    <div class="row">
			<div class="col-xs-6">
				<h3>Modal Demo</h3>
				<button id="modal-demo-1" class="top20">Launch Modal</button>
				
				
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel">New message</h4>
      </div>
      <div class="modal-body">
        <form>
          <div class="form-group">
            <label for="recipient-name" class="control-label">Recipient:</label>
            <input type="text" class="form-control" id="recipient-name">
          </div>
          <div class="form-group">
            <label for="message-text" class="control-label">Message:</label>
            <textarea class="form-control" id="message-text"></textarea>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Send message</button>
      </div>
    </div>
  </div>
</div>
			</div>
		</div>
		
	</div>
</body>
</html>