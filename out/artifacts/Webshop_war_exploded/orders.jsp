<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<meta charset="UTF-8">
	<title>Welcome ${customer.username}</title>
	<%--    <link rel="stylesheet" type="text/css" href="WebshopStylesheet.css"/>--%>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>

<%@include file="include/header.jsp" %>

<div class="container">
	<div class="row">
		<div class="col-md-3 ">
			<div class="list-group ">
				<a href="profile.jsp" class="list-group-item list-group-item-action">Personal information</a>
				<a href="balance.jsp" class="list-group-item list-group-item-action">Balance</a>
				<a href="orders.jsp" class="list-group-item list-group-item-action active">Orders</a>
			</div>
		</div>
		<div class="col-md-9">
			<div class="card">
				<div class="card-body">
					<div class="row">
						<div class="col-md-12">
							<h4>Your Orders</h4>
							<hr>
						</div>
					</div>
					<c:if test="${not empty customer.orders}">
					<div class="row">
						<c:forEach items="${customer.orders}" var="order">
							<ul style="list-style: none">
								<li>Order number: ${order.num}</li>
								<li><strong>Status: </strong>${order.status}</li>
								<c:if test="${order.status == 'awaiting payment'}">
									<li>
										<a href="forepayOrder?num=${order.num}" class="btn btn-success">Pay</a>
									</li>

								</c:if>
								<c:if test="${order.status != 'awaiting payment'}">
									<li><a href="foreviewInvoice?ordernum=${order.num}">invoice</a></li>
								</c:if>
							</ul>
							<table class="table table-hover bg-white">
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
									<td colspan="3" class="text-right">Delivery fee: (${order.shipmethod.name})</td>
									<td class="text-center"><strong>$${order.shipmethod.cost}</strong></td>
								</tr>
								<tr>
									<td>  </td>
									<td>  </td>
									<td><h6>Total</h6></td>
									<td class="text-center"><h6><strong>$${order.totalprice}</strong></h6></td>
								</tr>
								</tfoot>
							</table>
							<hr/>
						</c:forEach>
					</div>
					</c:if>
					<c:if test="${empty customer.orders}">
						<div class="row">
							You have not ordered yet
						</div>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</div>

<%@include file="include/footer.jsp" %>

</body>
</html>