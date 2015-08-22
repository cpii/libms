<%@ page import="java.util.*"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%
Date date = new java.util.Date();
pageContext.setAttribute("now", date);
%>

<c:set var="now" value="${date}" />
<jsp:useBean id="now" class="java.util.Date" scope="request" />
<div class="top20"></div>
<div class="container-fluid">
<div class="row">
<div class="col-xs-12">
    <c:if test="${not empty login_error}">
        <div class="alert alert-danger" role="alert"><strong>Your login attempt was not successful:</strong> ${SPRING_SECURITY_LAST_EXCEPTION.message}</div>			
    </c:if>

    <c:if test="${not empty sessionexpired}">	
	   <div class="alert alert-warning" role="alert"><strong>You have been logged out, possibly due to multiple logins. Please re-login.</strong></div>	
    </c:if>
</div>
</div>
<div class="row">
<div class="col-xs-4">

    <form class="form-signin" role="form" action="<c:url value='j_spring_security_check'/>" method="post">
        <h2 class="form-signin-heading">Please sign in</h2>
        <input type="text" class="form-control" placeholder="Your user name" required autofocus id="j_username" name="j_username"> 
        <input type="password" class="form-control" placeholder="Password" required id="j_password" name="j_password"> 
        <label class="checkbox"><input type="checkbox" value="remember-me">Remember me</label>
        <button class="btn btn-primary" type="submit">Sign in</button>
    </form>
</div>
</div>
</div>


