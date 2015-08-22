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
	<img src='<c:url value="/resources/images/__fix_me__.jpg" />' alt="__fix_me__"/>
</div>

<br />

<div>
<c:if test="${not empty login_error}">
	
	<div class="ui-widget">
		<div class="ui-state-error ui-corner-all" style="padding: 0 .7em;">

			<p>
				<span class="ui-icon ui-icon-alert"
					style="float: left; margin-right: .3em;"></span> 
					<strong>Your login attempt was not successful:</strong>	${SPRING_SECURITY_LAST_EXCEPTION.message}
			</p>
						
		</div>
	</div>	
</c:if>

<c:if test="${not empty sessionexpired}">	
	
	<div class="ui-widget">
		<div class="ui-state-highlight ui-corner-all" style="margin-top: 20px; padding: 0 .7em;"> 
			<p>
				<span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span>
				<strong>You have been logged out, possibly due to multiple logins. Please re-login.</strong>
			</p>
		</div>
	</div>
	
</c:if>
<br/>
<form action="<c:url value='j_spring_security_check'/>" method="POST">
<table>

	<tr>
		<td><label for="j_username">Username</label>:</td>
		<td><input id="j_username" name="j_username" size="20"
			maxlength="50" type="text" value=""/></td>
	</tr>
	<tr>
		<td><label for="j_password">Password</label>:</td>
		<td><input id="j_password" name="j_password" size="20" maxlength="50" type="password" value=""/></td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" id="loginFormSubmit" value="Login" />
			<script>
				document.getElementById("j_username").focus();
			</script>
		</td>
	</tr>

</table>
</form>
</div>

<hr />

<div>Copyright &copy; <fmt:formatDate value="${now}" pattern="yyyy" /> __fix_me__ <a href='<c:url value="http://dc.med.utoronto.ca/" />'\>__fix_me__</a></div> 
<div>Date: <fmt:formatDate value="${now}" pattern="MMM dd, yyyy hh:mm" /></div>



