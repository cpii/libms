<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h3>Update Publisher</h3>

<form action="/libms/updatePublisher" method="post">
<label>Name:</label><input type="text" name="name" value="${publisher.name}"><br><br>
<label>Email:</label><input type="text" name="email" value="${publisher.email}"><br><br>
<label>Phone:</label><input type="text" name="phone" value="${publisher.phone}">
<input type="hidden" name="id" value="${publisher.id}"/>
<input type="submit" value="Update">
</form>
</body>
</html>