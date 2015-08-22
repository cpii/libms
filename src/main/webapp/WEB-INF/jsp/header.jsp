<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:url value="/j_spring_security_logout" var="logoutUrl" />
<div class="top30"></div>
<nav class="navbar navbar-default " role="navigation">
   <div class="container-fluid">
      <div class="navbar-header">
         <button type="button" class="navbar-toggle" data-toggle="collapse"
            data-target="#main-menu-bar">
            <span class="sr-only">Toggle navigation</span> <span
               class="icon-bar"></span> <span class="icon-bar"></span> <span
               class="icon-bar"></span>
         </button>
         <a class="navbar-brand" href='<c:url value="/home" />'>libms</a>
      </div>

      <div class="collapse navbar-collapse"
         id="main-menu-bar">
         <ul class="nav navbar-nav">
            <li id="menu-item-workorders"><a href='<c:url value="/books" />'>Books</a></li>
            <sec:authorize access="hasRole('ROLE_ADMINISTRATOR')">
                <li id="menu-item-workorders"><a href='<c:url value="/employees" />'>Employees</a></li>
            </sec:authorize>
            <sec:authorize access="hasAnyRole('ROLE_STAFF','ROLE_ADMINISTRATOR')">
                <li id="menu-item-workorders"><a href='<c:url value="/members" />'>Members</a></li>
            </sec:authorize>                        
            <sec:authorize access="hasAnyRole('ROLE_STAFF','ROLE_ADMINISTRATOR')">
                <li id="menu-item-workorders"><a href='<c:url value="/authors/main" />'>Authors</a></li>
            </sec:authorize>
         </ul>
         <ul class="nav navbar-nav navbar-right">
            <li class="dropdown"><a href="#" class="dropdown-toggle"
               data-toggle="dropdown"><sec:authentication
                     property="principal.displayName" /> <b class="caret"></b></a>
               <ul class="dropdown-menu">
                  <li><a href="${logoutUrl}">Log Out</a></li>                 
                  <li class="divider"></li>
                  <li><a href="#">Menu Item 4</a></li>
               </ul></li>
         </ul>
      </div>
      <!-- /.navbar-collapse -->
   </div>
   <!-- /.container-fluid -->
</nav>