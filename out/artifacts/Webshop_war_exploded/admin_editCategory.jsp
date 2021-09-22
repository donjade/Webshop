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

	<div id="content-wrapper" class="pt-4 mx-auto w-50">
		<h4>Edit ${category.name} category</h4>
		<form action="admin_category_update" method="post">
			<div class="form-group row">
				<label for="name" class="col-sm-2 col-form-label">ID</label>
				<div class="col-sm-10">
					<input type="text" class="form-control-plaintext" id="id" name="cid" value="${category.id}" readonly>
				</div>
			</div>
			<div class="form-group row">
				<label for="name" class="col-sm-2 col-form-label">Name</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="name" name="name" value="${category.name}" required>
				</div>
			</div>
			<button type="submit" class="btn btn-outline-primary">Update</button>
		</form>

		<hr/>

		<div>
			<h5>Subcategories</h5>
			<ul style="list-style: none">
				<c:forEach items="${category.subCategories}" var="subCategory">
					<li class="row ">
						<div>${subCategory.name}</div>
						<div class="col">
							<a href="admin_subCategory_delete?subcid=${subCategory.id}" role="button" class="btn btn-outline-danger">Delete</a>
						</div>
					</li>
				</c:forEach>
			</ul>
			<form action="admin_subCategory_add" method="get">
				<input type="hidden" name="cid" value="${category.id}">
				<label>Subcategory name </label><input type="text" name="subCategoryname" class="form-control" required>
				<button class="btn btn-outline-success">Add new subcategory</button>
			</form>
		</div>
	</div>
</div>

<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>