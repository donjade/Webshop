<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
	<meta charset="UTF-8">
	<title>OriGo Webshop</title>
	<%--    <link rel="stylesheet" type="text/css" href="WebshopStylesheet.css"/>--%>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>

<%@include file="include/header.jsp" %>

<div class="container">
	<div class="card bg-white">
		<div class="card-header p-4">
			<div class="float-right">
				<h5 class="mb-0">Invoice #${order.num}</h5>
				Date: ${order.paydate}
			</div>
		</div>
		<div class="card-body">
			<div class="row mb-4">
				<div class="col-sm-6">
					<h5 class="mb-3">From:</h5>
					<h4 class="text-dark mb-1">OriGo webshop</h4>
					<div>2595, Ventura Drive Street Scotts Valley, California 95066</div>
					<div>Email: contact@origowebshop.com</div>
					<div>Phone: +831-439-1843</div>
				</div>
				<div class="col-sm-6 ">
					<h5 class="mb-3">To:</h5>
					<h4 class="text-dark mb-1">${customer.firstname} ${customer.lastname}</h4>
					<div>${customer.address}</div>
					<div>Email: ${customer.email}</div>
					<div>Phone: +${customer.mobile}</div>
				</div>
			</div>
			<div class="table-responsive-sm">
				<table class="table">
					<thead>
					<tr>
						<th>Product</th>
						<th class="text-center">Price</th>
						<th class="text-center">Qty</th>
						<th class="text-center">Total</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${order.orderitems}" var="orderitem">
						<tr>
							<td>${orderitem.product.name}</td>
							<td class="text-center">$${orderitem.product.price}</td>
							<td class="text-center">${orderitem.pquantity}</td>
							<td class="text-center">$${orderitem.subtotal}</td>
						</tr>
					</c:forEach>
					</tbody>
					<tfoot>
					<tr>
						<td>   </td>
						<td>   </td>
						<td class="text-right"><strong class="text-dark text-right">Shipping cost</strong></td>
						<td class="text-center">$${order.shipmethod.cost}</td>
					</tr>
					<tr>
						<td>   </td>
						<td>   </td>
						<td class="text-right text-dark"><h5>Total</h5></td>
						<td class="text-center text-dark"><h5>$${order.totalprice}</h5></td>
					</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</div>
</div>

<%@include file="include/footer.jsp" %>

</body>
</html>