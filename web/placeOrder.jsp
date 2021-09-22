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
	<div class="row">
		<div class="col-md-4 order-md-2 mb-4">
			<h4 class="d-flex justify-content-between align-items-center mb-3">
				<span class="text-muted">Your cart</span>
				<span class="badge badge-secondary badge-pill">${cartTotalItemNumber}</span>
			</h4>
			<ul class="list-group mb-3">
				<c:forEach items="${cartitems}" var="item">
				<li class="list-group-item d-flex justify-content-between lh-condensed">
					<div>
						<span class="d-inline-block text-truncate" style="max-width: 200px;">${item.product.name}</span><br/>
						<small class="text-muted">x ${item.pquantity}</small>
					</div>
					<h6 class="text-muted">$${item.subtotal}</h6>
				</li>
				</c:forEach>

				<li class="list-group-item d-flex justify-content-between">
					<span>Total</span>
					<strong>$${totalprice}</strong>
				</li>
			</ul>
		</div>
		<div class="col-md-8 order-md-1">
			<h4 class="mb-3">Billing address</h4>
			<form action="foreplaceOrder" method="post">
				<div class="row">
					<div class="col-md-6 mb-3">
						<label for="firstName">First name</label>
						<input type="text" class="form-control" id="firstName" name="firstname" value="${customer.firstname}" required>
					</div>
					<div class="col-md-6 mb-3">
						<label for="lastName">Last name</label>
						<input type="text" class="form-control" id="lastName" name="lastname" value="${customer.lastname}" required>
					</div>
				</div>

				<div class="mb-3">
					<label for="email">Email</label>
					<input type="email" class="form-control" id="email" name="email" value="${customer.email}" required>
				</div>

				<div class="mb-3">
					<label for="address">Address</label>
					<input type="text" class="form-control" id="address" name="address" value="${customer.address}" required>
				</div>

				<hr class="mb-4">
				<div class="custom-control custom-checkbox">
					<input type="checkbox" class="custom-control-input" checked>
					<label class="custom-control-label">Shipping address is the same as my billing address</label>
				</div>
				<hr class="mb-4">

				<h4 class="mb-3">Shipping method</h4>

				<div class="d-block my-3">
					<c:forEach items="${shipmethods}" var="shipmethod">
						<c:if test="${shipmethod.name == 'Pickup in store'}">
							<div class="form-check">
								<input name="shipmethodid" value="${shipmethod.id}" type="radio" class="form-check-input" required checked>
								<label class="form-check-label">${shipmethod.name} (+ $${shipmethod.cost})</label>
							</div>
						</c:if>
						<c:if test="${shipmethod.name != 'Pickup in store'}">
							<div class="form-check">
								<input name="shipmethodid" value="${shipmethod.id}" type="radio" class="form-check-input" required>
								<label class="form-check-label">${shipmethod.name} (+ $${shipmethod.cost})</label>
							</div>
						</c:if>
					</c:forEach>
				</div>

				<hr class="mb-4">

				<button class="btn btn-primary" type="submit">Place order</button>
			</form>
		</div>
	</div>
</div>

<%@include file="include/footer.jsp" %>

</body>
</html>