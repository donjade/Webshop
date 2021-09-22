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

		<%@include file="include/categorymenu.jsp" %>

		<div class="col-lg-9">
			<h3>Search result for: ${keyword}</h3>

			<div class="row">
				<c:forEach items="${products}" var="product">

					<div class="col-lg-4 col-md-6 mb-4">
						<div class="card h-100">
							<img class="card-img-top" src="image/product/${product.imagename}.jpg"
								 alt="${product.name}">
							<div class="card-body">
								<p class="card-title">
									<a href="foreproduct?pid=${product.id}" title="${product.name}">${product.name}</a>
								</p>
								<p>$${product.price}</p>
							</div>
							<div class="card-footer">
								Rate: ${product.rate}
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</div>
</div>
<%@include file="include/footer.jsp" %>

</body>
</html>