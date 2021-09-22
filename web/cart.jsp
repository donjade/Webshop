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
	<c:if test="${not empty cartitems}">
		<table class="table table-hover">
			<thead>
			<tr>
				<th>Product</th>
				<th class="text-center">Price</th>
				<th class="text-center">Quantity</th>
				<th class="text-center">Subtotal</th>
				<th>  </th>
				<th>  </th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${cartitems}" var="item">
				<tr>
					<form action="forechangeCartitem" method="post">
						<td class="col-sm-8 col-md-6">
							<div class="media">
								<img class="media-object" src="image/product/${item.product.imagename}.jpg" style="width: 72px; height: 72px;">
								<div class="media-body">
									<h6 class="media-heading">${item.product.name}</h6>
									<span>Status: </span>
									<c:if test="${item.product.stock < item.pquantity}">
										<span class="text-danger"><strong>Unavaible</strong></span>
									</c:if>
									<c:if test="${item.product.stock >= item.pquantity}">
										<span class="text-success"><strong>In Stock</strong></span>
									</c:if>
								</div>
							</div>
						</td>
						<td class="col-sm-1 col-md-1 text-center">$${item.product.price}</td>
						<td class="col-sm-1 col-md-1 text-center">
							<input type="number" name="quantity"class="form-control" value="${item.pquantity}" min="1">
						</td>
						<td class="col-sm-1 col-md-1 text-center"><strong>$${item.subtotal}</strong></td>
						<td class="col-sm-1 col-md-1">
							<input type="hidden" name="pid" value="${item.product.id}"/>
							<button type="submit" class="btn btn-outline-success">Update</button>
						</td>
					</form>
					<td class="col-sm-1 col-md-1">
						<a href="foredeleteCartitem?pid=${item.product.id}" class="btn btn-outline-danger" role="button">Remove</a>
					</td>
				</tr>
			</c:forEach>

			</tbody>
			<tfoot>
			<tr>
				<td>  </td>
				<td>  </td>
				<td><h6>Total</h6></td>
				<td class="text-center"><h6><strong>$${totalprice}</strong></h6></td>
				<td>  </td>
				<td>  </td>
			</tr>
			<tr>
				<td><a href="forehome" class="btn btn-outline-secondary" role="button">Homepage</a></td>
				<td>  </td>
				<td>  </td>
				<td>  </td>
				<td>  </td>
				<td><a href="forecheckoutCart" class="btn btn-outline-primary">Checkout</a></td>
			</tr>
			</tfoot>
		</table>
	</c:if>
	<c:if test="${empty cartitems}">
		<div class="align-middle">
			<h2>Your shopping cart is empty</h2>
			<p><a href="forehome">Go to shopping</a></p>
		</div>
	</c:if>
</div>

<%@include file="include/footer.jsp" %>

</body>
</html>