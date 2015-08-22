<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<br/><br/><br/>
<form:form modelAttribute="car" method="post">
   <div class="form-group">
      <label for="title">Make</label>
      <form:input path="make"/>         
   </div>
   <div class="form-group">
      <label for="isbn">Model (Long Label)</label>
      <form:input path="model"/>
   </div>
   <div class="form-group">
      <label for="isbn">Color</label>
      <form:input path="color"/>
   </div>
   <button type="submit" class="btn btn-default">Submit</button>
</form:form>
      
