<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table>
<tr><th>Title</th><th>ISBN</th></tr>
<c:forEach items="${books}" var="book">
<tr><td>${book.title}</td><td>${book.isbn}</td></tr>
</</c:forEach>
</table>