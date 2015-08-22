<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script type="text/javascript"
	src="<c:url value='/resources/js/libms/book.js' />"></script>
<c:url value="/books/save" var="axion" />

<div class="row">
	<div class="col-xs-4">
		<form:form id="book-form" modelAttribute="book" method="post"
			action="${axion}">
			<div class="form-group">
				<label for="title">Title</label>
				<form:input path="title" class="form-control" />
				<form:errors path="title" cssClass="text-danger" />
			</div>
			<div class="form-group">
				<label for="isbn">ISBN</label>
				<form:input path="isbn" class="form-control" />
				<form:errors path="isbn" cssClass="text-danger" />
			</div>
			<div class="form-group">
				<label for="published">Date Published</label>
				<form:input path="published" class="form-control"
					id="published-date" autocomplete="false" />
				<form:errors path="published" cssClass="text-danger" />
			</div>
      <div class="form-group">
        <label for="isbn">Cost</label>
        <form:input path="cost" class="form-control" />
        <form:errors path="cost" cssClass="text-danger" />
      </div>
			<div class="form-group">
				<label for="keywords">Keywords</label>
				<form:input path="keywords" class="form-control" />
			</div>
			<div class="form-group">
				<label for="keywords">Publisher</label>
				<form:select path="publisher.id" items="${publishers}"
					itemLabel="name" itemValue="id" class="form-control" />
			</div>

			<div class="form-group">
				<label for="keywords">Authors</label>
				<form:select path="authors[0].id" items="${authors}"
					itemLabel="name" itemValue="id" class="form-control" />
			</div>
      
      <div class="form-group">
        <label for="copies">Number of Copies</label>
        <input type="number" id="copies" name="bkcopies" class="form-control" />
      </div>

			<button type="button" class="btn btn-default" id="book-form-submit">Submit</button>
		</form:form>

	</div>

</div>