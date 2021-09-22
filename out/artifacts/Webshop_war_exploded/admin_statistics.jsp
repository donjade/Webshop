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
		<div class="pb-4 w-50 mx-auto">
			<h3>Usage of each shipping method</h3>
			<table class="table">
				<tr>
					<th>Shipping method name</th>
					<th class="text-center">Number</th>
				</tr>
				<c:forEach items="${map1}" var="record">
				<tr>
					<td>${record.key}</td>
					<td class="text-center">${record.value}</td>
				</tr>
				</c:forEach>
			</table>
		</div>

		<div class="pb-4">
			<h3>Sum of ordered number of each product</h3>
			<table class="table">
				<tr>
					<th>Product name</th>
					<th class="text-center">Number</th>
				</tr>
				<c:forEach items="${map2}" var="record">
				<tr>
					<td>${record.key}</td>
					<td class="text-center">${record.value}</td>
				</tr>
				</c:forEach>
			</table>
		</div>

		<div class="pb-4 w-50 mx-auto">
			<h3>Number of product of each sub category</h3>
			<table class="table">
				<tr>
					<th>Sub category name</th>
					<th class="text-center">Number</th>
				</tr>
				<c:forEach items="${map3}" var="record">
				<tr>
					<td>${record.key}</td>
					<td class="text-center">${record.value}</td>
				</tr>
				</c:forEach>
			</table>
		</div>

		<div class="pb-4 w-50 mx-auto">
			<h3>Maximum of order total price of each customer</h3>
			<table class="table">
				<tr>
					<th>Username</th>
					<th class="text-center">Total price</th>
				</tr>
				<c:forEach items="${map4}" var="record">
				<tr>
					<td>${record.key}</td>
					<td class="text-center">$${record.value}</td>
				</tr>
				</c:forEach>
			</table>
		</div>

		<hr/>

		<div>
			<h3>Maximum rate of each product which has review</h3>
			<table class="table">
				<tr>
					<th>Product name</th>
					<th class="text-center">Rate</th>
				</tr>
				<c:forEach items="${map5}" var="record">
				<tr>
					<td>${record.key}</td>
					<td class="text-center">${record.value}</td>
				</tr>
				</c:forEach>
			</table>
		</div>

	</div>
</div>

<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>
</body>

</html>