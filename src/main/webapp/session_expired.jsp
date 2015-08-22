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



<div>
	<img src='<c:url value="/resources/images/__FIX_ME__.jpg" />' alt="Huron TG"/>
</div>

<br />

<div>
<c:url value="/appl/" var="loginUrl" />

<div class="ui-widget">
	<div class="ui-state-highlight ui-corner-all" style="margin-top: 20px; padding: 0 .7em;"> 
		<p>
			<span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span>
			<strong>Your session has expired.</strong>
			To re-login, please click <a href='<c:out value="${loginUrl}"/>'>here</a>.
		</p>
	</div>
</div>
	

<br/>

</div>

