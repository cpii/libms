<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%
Date date = new java.util.Date();
pageContext.setAttribute("now", date);
%>

<c:set var="now" value="${date}" />

<jsp:useBean id="now" class="java.util.Date" scope="request" />

<c:url value="http://__fix_me__.com/" var="xurl"/>
<div>Copyright &copy; <fmt:formatDate value="${now}" pattern="yyyy" />Developed and hosted by __fix_me__<a href="${xurl}"\>__fix_me__</a></div> 
<div>Date: <fmt:formatDate value="${now}" pattern="MMM dd, yyyy hh:mm" /></div>
<div><input type="hidden" id="ctxtrelurl" value='<c:url value="/"/>'/></div>