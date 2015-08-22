<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<script type="text/javascript" src="<c:url value='/resources/js/libms/author.js' />"></script>

<!-- <div id="author-container">

<div id="author-table-container" class="top10">
</div>

</div>

 -->
 
<div id="author-container">

<div id="author-table-container" class="top10">
</div>

<div class="modal fade" id="author-create-dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Create Author</h4>
      </div>
      <div class="modal-body">
      <div class="alert alert-danger hidden" role="alert"></div>
        <div class="container-fluid">
          <div class="row">
            <div class="col-xs-4">              
              <form:form id="author-form" modelAttribute="author">
                <div class="form-group">
                  <label for="title">First Name</label>
                  <form:input path="firstName" class="form-control" />
                </div>
                <div class="form-group">
                  <label for="title">Middle Name</label>
                  <form:input path="middleName" class="form-control" />
                </div>
                <div class="form-group">
                  <label for="title">Last Name</label>
                  <form:input path="lastName" class="form-control" />
                </div>
                <div class="form-group">
                  <label for="title">Gender</label>
                  <form:input path="gender" class="form-control" />
                </div>
                <div class="form-group">
                  <label for="title">Date of Birth</label>
                  <form:input path="birthday" class="form-control need-datepicker" />
                </div>            
              </form:form>
            </div>
          </div>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" id="author-create-button">Create</button>
      </div>
    </div>
  </div>
</div>

<div class="modal fade" id="author-edit-dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Edit Author</h4>
      </div>
      <div class="modal-body">
      
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" id="author-edit-submit-button">Save Changes</button>
      </div>
    </div>
  </div>
</div>

</div>
 