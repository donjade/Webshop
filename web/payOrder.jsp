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

<div class="container mx-auto pt-5 text-center">
	<h4 class="pb-4">Confirm payment</h4>
	<div>
		<h6>Your current balance: $${customer.balance}</h6>
		You need to pay $${order.totalprice}
	</div>
	<div>
		<form action="foreconfirmOrder" method="post">
			<input type="hidden" name="ordernum" value="${order.num}">
			<button type="submit" class="btn btn-success">Pay</button>
		</form>
	</div>
	<div>
		<a href="orders.jsp" role="button" class="btn btn-secondary">Cancel</a>
	</div>
</div>

<%@include file="include/footer.jsp" %>

</body>
</html>