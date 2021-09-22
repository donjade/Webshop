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

	<%@include file="include/admin_sidebar.jsp"%>

	<div id="content-wrapper" class="mx-auto pt-4 w-50">
		<h4>Edit product #${product.id}</h4>
		<form action="admin_product_update" method="post">
			<div class="form-group row">
				<label for="name" class="col-sm-2 col-form-label">ID</label>
				<div class="col-sm-10">
					<input type="text" class="form-control-plaintext" id="id" name="id" value="${product.id}" readonly>
				</div>
			</div>
			<div class="form-group row">
				<label for="name" class="col-sm-2 col-form-label">Name</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="name" name="name" value="${product.name}" required>
				</div>
			</div>
			<div class="form-group row">
				<label for="description" class="col-sm-2 col-form-label">Description</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="description" name="description" value="${product.description}">
				</div>
			</div>
			<div class="form-group row">
				<label for="price" class="col-sm-2 col-form-label">Price</label>
				<div class="col-sm-10">
					<input type="number" class="form-control" id="price" name="price" min="1" value="${product.price}" required>
				</div>
			</div>
			<div class="form-group row">
				<label for="stock" class="col-sm-2 col-form-label">Stock</label>
				<div class="col-sm-10">
					<input type="number" class="form-control" id="stock" name="stock" value="${product.stock}" min="1" required>
				</div>
			</div>
			<div class="form-group row">
				<label for="imagename" class="col-sm-2 col-form-label">Imagename (jpg)</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="imagename" name="imagename" value="${product.imagename}">
				</div>
			</div>
			<div class="form-group row">
				<label for="subcid" class="col-sm-2 col-form-label">Subcategory </label>
				<div class="col-sm-10">
					<select class="form-control" id="subcid" name="subcid" required>
						<c:forEach items="${subCategories}" var="subCategory">
							<c:if test="${subCategory.id == product.subcid}">
								<option value="${subCategory.id}" selected>${subCategory.name}</option>
							</c:if>
							<c:if test="${subCategory.id != product.subcid}">
								<option value="${subCategory.id}">${subCategory.name}</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
			</div>
			<button type="submit" class="btn btn-outline-primary">Update</button>
		</form>
	</div>

</div>

<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>