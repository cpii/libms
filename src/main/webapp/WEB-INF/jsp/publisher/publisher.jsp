<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<script type="text/javascript">
$(document).ready(function() {
	var id = $("#id").val();
	if (id == null) {
		console.log("id is null");
	} else {
		console.log("id is not null");
	}
});
</script>
<title>Publishers</title>
</head>
<body>
<h3>New Publisher</h3>

<form:form modelAttribute="publisher" action="/libms/updatePublisher" method="post">
<label>Name:</label><form:input path="name"/><br><br>
<label>Email:</label><form:input path="email"/><br><br>
<label>Phone:</label><form:input path="phone"/><br><br>
<form:hidden path="id"/>
<input type="submit" value="Save">
</form:form>
</body>
</html>