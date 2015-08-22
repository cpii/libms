<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script type="text/javascript"
    src="<c:url value='/resources/js/libms/book.js' />"></script>
    
<c:url value="/books/search" var="searchUrl"/>
<div class="row">
<div class="col-xs-4">

<form id="book-search-form" action="${searchUrl}" method="post">
  <div class="input-group">    
    <input type="text" name = "q" id="book-search-query" class="form-control" aria-label="Text input with segmented button dropdown" autocomplete="off">
    <div class="input-group-btn">
      <button type="button" id="book-search-button" class="btn btn-default">Search</button>
      <sec:authorize access="hasRole('ROLE_STAFF')">      
      <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
        <span class="caret"></span>
        <span class="sr-only">Toggle Dropdown</span>
      </button>
      <ul class="dropdown-menu dropdown-menu-right" role="menu">        
        <li><a href='<c:url value="/books/new" />'>New Book</a>        
      </ul>
      </sec:authorize>
    </div>
    
  </div><!-- /.input-group -->
  </form>
</div>
</div>      
<div class="row top20">
<div class="col-xs-8">
<table class="table table-bordered table-striped table-hover table-condensed compact-data-table">
<tr>
    <th>ID</th>
    <th>Title</th>
    <th>ISBN</th>
    <th>Date Published</th>
    <th>Keywords</th>
    <th>Author</th>
    <th>Publisher</th>  
    <th>Available</th>
    <th><br/></th>    
</tr>

<c:forEach items="${books}" var="book">
	<tr>
		<td><a href='<c:url value="/books/edit?bookId=${book.id}"/>'>${book.id}</a></td>
		<td><c:out value="${book.title}" /></td>
		<td><c:out value="${book.isbn}" /></td>
		<td><c:out value="${book.published}" /></td>		
		<td><c:out value="${book.keywords}" /></td>
		<td><c:out value="${book.authorNames}" /></td>
		<td><c:out value="${book.publisher.name}" /></td>		
		<td>
		  <c:choose>
		   <c:when test="${book.available}">
		    On Shelf
		   </c:when>
		   <c:otherwise>
		    Not available
		   </c:otherwise>
		  </c:choose>
		</td>
		<td><a href='<c:url value="/delete?bookId=${book.id}"/>'>Delete</a></td>
		
	</tr>
</c:forEach>

</table>
</div>
<sec:authorize access="hasRole('ROLE_STAFF')">
<div class="col-xs-4">
<div class="panel panel-default" id="equipments-table-panel">
<div class="panel-heading clearfix">
   <strong>Checkout Books</strong>
</div>
<div class="panel-body">     
<c:url value="/books/checkout" var="frmAction" />
  <form id="book-checkout-form" method="post" action="${frmAction}">
      <div class="form-group">
        <label for="title">Books</label>
        <select name="book.id" class="form-control">
        <c:forEach items="${books}" var="book">
          <c:if test="${book.available}">
            <option value="${book.id}">${book.title} - ${book.authors[0].name}</option>
          </c:if>
        </c:forEach>
        </select>            
      </div>
      <div class="form-group">
        <label for="title">Members</label>
        <select name="member.id" class="form-control">
        <c:forEach items="${members}" var="member">
          <option value="${member.id}">${member.firstName} - ${member.lastName}</option>
        </c:forEach>
        </select>        
      </div>
      <div class="form-group pull-right">
        <button type="submit" class="btn btn-primary" id="book-checkout-form-submit">Checkout</button>
      </div>
   </form>
</div>
</div>
</div>
</sec:authorize>
</div>