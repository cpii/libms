<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!-- 1 -->
<%-- <table>
<tr><th>First Name</th><th>Last Name</th><th>Gender</th><th>Birthday</th></tr>
<c:forEach items="${authors}" var="author">
<tr>
	<td><c:out value="${author.firstName}"/></td>
	<td><c:out value="${author.lastName}"/></td>
	<td><c:out value="${author.gender}"/></td>
	<td><c:out value="${author.birthday}"/></td>
</tr>
</c:forEach>
</table> --%>

 
<!--  2  -->
<%-- <table id="author-table" class="table table-striped table-bordered table-condensed">
<thead>
<tr>
   <th>First Name</th>
   <th>Last Name</th>
   <th>Gender</th>
   <th>Birthday</th> 
   <th></th>  
</tr>
</thead>
<tbody>
<c:forEach items="${authors}" var="author">
<tr data-author-id='<c:out value="${author.id}"/>'>
  <td><c:out value="${author.firstName}"/></td>
	<td><c:out value="${author.lastName}"/></td>
	<td><c:out value="${author.gender}"/></td>
	<td><c:out value="${author.birthday}"/></td>
   <td>
	   <button type="button" class="btn btn-default btn-xs author-edit-button">
	      <span class="glyphicon glyphicon-pencil"></span> 
	   </button>	   
   </td>
</tr>
</c:forEach>
</tbody>    
</table> --%>

<!-- 3 -->
 
 
<div class="panel panel-default">
<div class="panel-heading clearfix">
    <strong>Authors</strong>    
    <div class="btn-group pull-right">
      <a href="#" id="author-create-link" class="btn btn-default btn-xs">
         <span class="glyphicon glyphicon-plus"></span> 
      </a>
    </div>
</div>
  <div class="panel-body">     
<table id="author-table" class="table table-striped table-condensed">
<thead>
<tr>
   <th>First Name</th>
   <th>Last Name</th>
   <th>Gender</th>
   <th>Birthday</th>
   <th></th>
</tr>
</thead>
<tbody>
<c:forEach items="${authors}" var="author">
<tr>
  <td><c:out value="${author.firstName}"/></td>
  <td><c:out value="${author.lastName}"/></td>
  <td><c:out value="${author.gender}"/></td>
  <td><c:out value="${author.birthday}"/></td>
   <td>
     <button type="button" class="btn btn-default btn-xs author-edit-button" data-author-id='<c:out value="${author.id}"/>'>
        <span class="glyphicon glyphicon-pencil"></span> 
     </button>     
   </td>
</tr>
</c:forEach>
</tbody>    
</table>
</div>
</div>