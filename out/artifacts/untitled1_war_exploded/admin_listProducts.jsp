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

	<div id="content-wrapper" class="mx-auto px-4 pt-4 w-75">
		<table class="table">
			<thead>
			<tr>
				<th>ID</th>
				<th>Product image</th>
				<th>Product name</th>
				<th class="text-center">Price</th>
				<th class="text-center">Stock number</th>
				<th class="text-center">Subcategory id</th>
				<th></th>
				<th></th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${products}" var="product">
				<tr>
					<td>${product.id}</td>
					<td><img src="image/product/${product.imagename}.jpg" style="width: 85px; height: 85px;"></td>
					<td>${product.name}</td>
					<td class="text-center">$${product.price}</td>
					<td class="text-center">${product.stock}</td>
					<c:if test="${product.subcid == 0}">
						<td class="text-center">Do not belong to any subcategory</td>
					</c:if>
					<c:if test="${product.subcid != 0}">
						<td class="text-center">${product.subcid}</td>
					</c:if>
					<td>
						<a role="button" href="admin_product_edit?pid=${product.id}" class="btn btn-outline-success">Edit</a>
					</td>
					<td>
						<a role="button" href="admin_product_delete?pid=${product.id}" class="btn btn-outline-danger">Delete</a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>

		<hr/>

		<div class="p-3 mx-auto w-50">
			<h4>Add new product</h4>
			<form action="admin_product_add" method="post">
				<div class="form-group row">
					<label for="name" class="col-sm-2 col-form-label">Name</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="name" name="name" required>
					</div>
				</div>
				<div class="form-group row">
					<label for="description" class="col-sm-2 col-form-label">Description</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="description" name="description">
					</div>
				</div>
				<div class="form-group row">
					<label for="price" class="col-sm-2 col-form-label">Price</label>
					<div class="col-sm-10">
						<input type="number" class="form-control" id="price" name="price" min="1" required>
					</div>
				</div>
				<div class="form-group row">
					<label for="stock" class="col-sm-2 col-form-label">Stock</label>
					<div class="col-sm-10">
						<input type="number" class="form-control" id="stock" name="stock" min="1" required>
					</div>
				</div>
				<div class="form-group row">
					<label for="imagename" class="col-sm-2 col-form-label">Imagename (jpg)</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="imagename" name="imagename">
					</div>
				</div>
				<div class="form-group row">
					<label for="subcid" class="col-sm-2 col-form-label">Subcategory </label>
					<div class="col-sm-10">
						<select class="form-control" id="subcid" name="subcid" required>
							<c:forEach items="${subCategories}" var="subCategory">
								<option value="${subCategory.id}">${subCategory.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<button type="submit" class="btn btn-outline-primary">Add new product</button>
				</div>
			</form>
		</div>
	</div>
</div>

<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>
</body>

</html>