<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<%
Date date = new java.util.Date();
pageContext.setAttribute("now", date);
%>

<c:set var="now" value="${date}" />
<jsp:useBean id="now" class="java.util.Date" scope="request" />
<c:url value="http://www.hurontg.com/" var="htg"/>
<input type="hidden" id="ctxtrelurl" value='<c:url value="/"/>'/>
<p class="text-center"><small>Copyright &copy; <fmt:formatDate value="${now}" pattern="yyyy" /> <a href="${htg}">hurontg.com</a></small></p> 




  