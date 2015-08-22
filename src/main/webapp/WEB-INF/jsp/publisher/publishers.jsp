<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Publishers</title>
</head>
<body>
<p>
	<a href="/libms/publisherEntryForm">New Publisher</a>
</p>

<table border="1" cellspacing="3" cellpadding="5">
<tr><th>ID</th><th>Name</th><th>Email</th><th>Phone</th><th>Delete</th></tr>
<c:forEach items="${publishers}" var="publisher">
	<tr>
		<td><a href='/libms/displayPublisherUpdateForm?publisherId=${publisher.id}'>${publisher.id}</a></td>
		<td><c:out value="${publisher.name}" /></td>
		<td><c:out value="${publisher.email}" /></td>
		<td><c:out value="${publisher.phone}" /></td>
		<td><a href='/libms/deletePublisher?publisherId=${publisher.id}'>Delete</a></td>
	</tr>
</c:forEach>

</table>
</body>
</html>