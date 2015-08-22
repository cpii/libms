<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="row top20">
<div class="col-xs-8">
<table class="table table-bordered table-striped table-hover table-condensed compact-data-table">
<tr>
    <th>Title</th>
    <th>Checked out On</th>
    <th>Due Date</th>   
    <th><br/></th>       
</tr>

<c:forEach items="${items}" var="item">
  <tr>
    <td><c:out value="${item.bookCopy.book.title}" /></td>
    <td><c:out value="${item.borrowedDate}" /></td>
    <td><c:out value="${item.dueDate}" /></td>    
    <td><a href='<c:url value="/books/return?borrowedItemId=${item.id}"/>'>Return</a></td>    
  </tr>
</c:forEach>

</table>
</div>
</div>