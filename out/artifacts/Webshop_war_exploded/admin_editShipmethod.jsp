<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>Dashboard</title>
	<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
</head>

<body id="page-top">
<div id="wrapper" class="d-flex">

	<%@include file="include/admin_sidebar.jsp" %>

	<div id="content-wrapper" class="mx-auto pt-4 w-50">
		<form class="form-horizontal" action="admin_shipmethod_update" method="post">
			<div class="form-group">
				<label class="control-label col-sm-2" for="id">ID:</label>
				<div class="col-sm-10">
					<input type="number" class="form-control" id="id" name="id" value="${shipmethod.id}" readonly>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="name">Shipping method name:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="name" name="name" value="${shipmethod.name}">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="cost">Shipping method cost:</label>
				<div class="col-sm-10">
					<input type="number" class="form-control" id="cost" name="cost" value="${shipmethod.cost}" min="0">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-outline-primary">Update</button>
				</div>
			</div>
		</form>
	</div>

</div>

<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>
</body>

</html>