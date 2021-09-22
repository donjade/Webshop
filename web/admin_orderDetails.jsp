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

		<h6><strong>Order no. </strong>${order.num}</h6>
		<p><strong>Status: </strong> ${order.status}</p>
		<p><strong>Ordered date: </strong>${order.orderdate}</p>
		<c:if test="${order.status != 'awaiting payment'}">
			<p><strong>Payed date: </strong>${order.paydate}</p>
		</c:if>
		<c:if test="${order.status == 'complete'}">
			<p><strong>Delivery date: </strong>${order.shipdate}</p>
		</c:if>
		<h4>Shipping information</h4>
		<ul style="list-style: none">
			<li>Customer name: ${order.customer.firstname} ${order.customer.lastname}</li>
			<li>Customer mobile: ${order.customer.mobile}</li>
			<li>Shipping address: ${order.customer.address}</li>
			<li>Shipping method: ${order.shipmethod.name}</li>
		</ul>
		<h4>Order information</h4>
		<table class="table">
			<thead>
			<tr>
				<th class="text-center">Product name</th>
				<th class="text-center">Price</th>
				<th class="text-center">Qty</th>
				<th class="text-center">Subtotal</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${order.orderitems}" var="item">
				<tr>
					<td class="col-sm-8 col-md-6">${item.product.name}</td>
					<td class="col-sm-1 col-md-1 text-center">$${item.product.price}</td>
					<td class="col-sm-1 col-md-1 text-center">${item.pquantity}</td>
					<td class="col-sm-1 col-md-1 text-center">$${item.subtotal}</td>
				</tr>
			</c:forEach>
			</tbody>
			<tfoot>
			<tr>
				<td colspan="3" class="text-right">Delivery fee</td>
				<td class="text-center"><h6><strong>$${order.shipmethod.cost}</strong></h6></td>
			</tr>
			<tr>
				<td>  </td>
				<td>  </td>
				<td><h6>Total</h6></td>
				<td class="text-center"><h6><strong>$${order.totalprice}</strong></h6></td>
			</tr>
			</tfoot>
		</table>
	</div>
</body>
</html>
