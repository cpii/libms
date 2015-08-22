 <%@ page import="java.util.*"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<link rel="stylesheet"
    href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<link rel="stylesheet"
    href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">

<script src='<c:url value="/resources/js/jquery-1.11.1.min.js" />'></script>
<script
    src='<c:url value="/resources/js/jquery-migrate-1.2.1.min.js" />'></script>
<script
    src='<c:url value="/resources/bootstrap-3.2.0/js/bootstrap.min.js" />'></script>

<c:url value="/" var="loginUrl" />
<div class="alert alert-success top20" role="alert">
  <strong>You have been logged out.</strong> To re-login, please click <a href='<c:out value="${loginUrl}"/>'>here</a>
</div>