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
		<table class="table">
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Cost</th>
				<th></th>
				<th></th>
			</tr>
			<c:forEach items="${shipmethods}" var="shipmethod">
				<tr>
					<td>${shipmethod.id}</td>
					<td>${shipmethod.name}</td>
					<td>$${shipmethod.cost}</td>
					<td>
						<a role="button" href="admin_shipmethod_edit?id=${shipmethod.id}" class="btn btn-outline-success">Edit</a>
					</td>
					<td>
						<a role="button" href="admin_shipmethod_delete?id=${shipmethod.id}" class="btn btn-outline-danger">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</table>

		<hr/>

		<div class="p-3 mx-auto w-50">
			<h4>Add new shipping method</h4>
			<form action="admin_shipmethod_add" method="post">
				<div class="form-group row">
					<label for="name" class="col-sm-2 col-form-label">Name</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="name" name="name" required>
					</div>
				</div>
				<div class="form-group row">
					<label for="cost" class="col-sm-2 col-form-label">Cost</label>
					<div class="col-sm-10">
						<input type="number" class="form-control" id="cost" name="cost" min="0" required>
					</div>
				</div>
				<button type="submit" class="btn-outline-secondary">Add</button>
			</form>
		</div>
	</div>

</div>

<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>
</body>

</html>