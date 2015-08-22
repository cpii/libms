<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<%-- <link rel="stylesheet" type="text/css" href='<c:url value="/resources/bootstrap-3.2.0/css/bootstrap.min.css" />' /> --%>
<link rel="stylesheet"
	href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">
<%-- <link rel="stylesheet" type="text/css" href='<c:url value="/resources/bootstrap-3.2.0/css/bootstrap-theme.min.css" />' /> --%>
<!-- <link href="http://cdn.datatables.net/plug-ins/e9421181788/integration/bootstrap/3/dataTables.bootstrap.css" rel="stylesheet"> -->
<link rel="stylesheet" type="text/css"
	href='<c:url value="/resources/DataTables-1.10.3/dataTables.bootstrap.css" />' />
<link rel="stylesheet" type="text/css"
	href='<c:url value="/resources/humane/libnotify.css" />' />
<link rel="stylesheet" type="text/css"
	href='<c:url value="/resources/css/libms.css" />' />
<link rel="stylesheet" type="text/css"
	href='<c:url value="/resources/css/bootstrap-datepicker3.css" />' />

<%-- <script src='<c:url value="/resources/js/angular.js" />'></script>
   <script src='<c:url value="/resources/js/angular-resource.js" />'></script> --%>
<!-- <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script> -->
<script src='<c:url value="/resources/js/jquery-1.11.1.min.js" />'></script>
<!-- <script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script> -->
<script
	src='<c:url value="/resources/js/jquery-migrate-1.2.1.min.js" />'></script>
<script
	src='<c:url value="/resources/bootstrap-3.2.0/js/bootstrap.min.js" />'></script>
<script
	src="http://cdn.jsdelivr.net/jquery.validation/1.13.1/jquery.validate.js"></script>
<!-- <script src="http://cdn.datatables.net/1.10.0/js/jquery.dataTables.js"></script> -->
<script
	src='<c:url value="/resources/DataTables-1.10.3/jquery.dataTables.min.js" />'></script>
<!-- <script src="http://cdn.datatables.net/plug-ins/e9421181788/integration/bootstrap/3/dataTables.bootstrap.js"></script> -->
<script
	src='<c:url value="/resources/DataTables-1.10.3/dataTables.bootstrap.js" />'></script>
<script src='<c:url value="/resources/js/jquery.form.js" />'></script>
<script src='<c:url value="/resources/humane/humane.min.js" />'></script>
<script src='<c:url value="/resources/js/libms/common.js" />'></script>
<script src='<c:url value="/resources/js/libms/libms.js" />'></script>
<script src='<c:url value="/resources/js/bootstrap-datepicker.js" />'></script>
<script src='<c:url value="/resources/js/typeahead.js" />'></script>

<title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-xs-12">
				<div class="header">
					<tiles:insertAttribute name="header" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<div id="main-content-container">				
					<tiles:insertAttribute name="body" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<div class="footer">
					<tiles:insertAttribute name="footer" />
				</div>
			</div>
		</div>
	</div>
</body>
</html>