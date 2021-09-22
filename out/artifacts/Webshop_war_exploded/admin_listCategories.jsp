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

	<div id="content-wrapper" class="mx-auto pt-4">
		<table class="table">
			<thead>
			<tr>
				<th>ID</th>
				<th>Category name</th>
				<th class="text-center">Subcategories</th>
				<th></th>
				<th></th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${categories}" var="category">
				<tr>
					<td>${category.id}</td>
					<td>${category.name}</td>
					<td>
						<ul style="list-style: none">
						<c:forEach items="${category.subCategories}" var="subCategory">
							<li>${subCategory.name}</li>
						</c:forEach>
						</ul>
					</td>
					<td>
						<a role="button" href="admin_category_edit?cid=${category.id}" class="btn btn-outline-success">Edit</a>
					</td>
					<td>
						<a role="button" href="admin_category_delete?cid=${category.id}" class="btn btn-outline-danger">Delete</a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>

		<hr/>

		<div class="p-3 mx-auto w-50">
			<h4>Add new category</h4>
			<form action="admin_category_add" method="post">
				<div class="form-group row">
					<label for="name" class="col-sm-2 col-form-label">Name</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="name" name="name" required>
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