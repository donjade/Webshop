<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<meta charset="UTF-8"/>
	<title>${product.name}</title>
	<%--	<link rel="stylesheet" type="text/css" href="WebshopStylesheet.css"/>--%>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/style.css" rel="stylesheet">
</head>
<body>
<%@include file="include/header.jsp" %>

<div class="container">
	<div class="product-details">
		<div class="product-details-image">
			<img src="image/product/${product.imagename}.jpg" alt="${product.name}" width="300" height="400"/>
		</div>

		<div class="product-information">
			<h2>${product.name}</h2>
			<hr/>
			<p style="font-size: small">Rating: ${product.rate}</p>
			<p style="font-size: 30px">$${product.price}</p>
			<form action="foreaddToCart" method="post">
				<input type="hidden" name="pid" value="${product.id}"/>
				<div>Quantity: <input type="number" name="quantity" value="1" min="1"/>
					<button type="submit" class="btn btn-outline-primary">Add to cart</button>
				</div>
			</form>
			<c:if test="${product.stock > 0}">
				<p style="color: green"><b>Availability:</b> In Stock</p>
			</c:if>
			<c:if test="${product.stock == 0}">
				<p style="color: darkred"><b>Unavailable</b></p>
			</c:if>
			<p>Description: ${product.description}</p>
		</div>
	</div>

	<hr/>

	<div>
		<h4>Reviews</h4>
		<c:if test="${empty reviews}">
			There's no reviews yet
		</c:if>
		<c:forEach items="${reviews}" var="review">
			<div class="pl-4 row">
				<div class="col col-lg-2"><h6><strong>${review.custuname}</strong><br/><small>${review.createddate}</small></h6></div>
				<div class="col"><p>${review.content}</p></div>
				<div class="col col-lg-2"><p>Rate: ${review.rate}</p></div>
			</div>
			<hr/>
		</c:forEach>
		<c:if test="${customer != null}">
			<form action="forenewReview" method="post" class="w-50">
				<input type="hidden" name="custuname" value="${customer.username}">
				<input type="hidden" name="pid" value="${product.id}">
				<div class="form-group">
					<textarea class="form-control" name="content" rows="2" placeholder="Write something..."></textarea>
				</div>
				Rate: <input type="number" name="rate" min="0" max="5">
				<button type="submit" class="btn btn-outline-secondary">Submit</button>
			</form>
		</c:if>
	</div>
</div>

<%@include file="include/footer.jsp" %>
</body>
</html>
