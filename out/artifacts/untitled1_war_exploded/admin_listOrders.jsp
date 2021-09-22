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
		<h4 class="text-center">Manage orders</h4>
		<table class="table">
			<thead>
			<tr>
				<th class="text-center">Ordered date</th>
				<th>No.</th>
				<th class="text-center">Customer name</th>
				<th class="text-center">Total price</th>
				<th class="text-center">Status</th>
				<th class="text-center">Need to be shipped within</th>
				<th></th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${orders}" var="order">
				<tr class="align-middle">
					<td class="text-center">${order.orderdate}</td>
					<td>
						<a href="admin_order_edit?num=${order.num}">${order.num}</a>
					</td>
					<td>${order.customer.firstname} ${order.customer.lastname}</td>
					<td class="text-center">$${order.totalprice}</td>
					<td class="text-center">${order.status}</td>
					<td class="text-center text-danger">
						<c:if test="${order.status == 'awaiting payment'}">
							Not payed yet
						</c:if>
						<c:if test="${order.status == 'order confirmed'}">
							${order.remainingDay} day(s)
						</c:if>
					</td>
					<td class="text-center">
						<c:if test="${order.status == 'order confirmed'}">
							<form action="admin_order_update" method="post">
								<input type="hidden" name="num" value="${order.num}">
								<input type="hidden" name="status" value="complete">
								<button type="submit" class="btn btn-outline-secondary">Ship</button>
							</form>
						</c:if>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>
</body>

</html>