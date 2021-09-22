<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>OriGo Webshop</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<%--    <link rel="stylesheet" type="text/css" href="WebshopStylesheet.css"/>--%>
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>

<%@include file="include/header.jsp"%>

<div class="container">

    <div class="row">

        <%@include file="include/categorymenu.jsp"%>

        <div class="col-lg-9">

            <c:forEach items="${newProductsByCategory}" var="products">
                <h3>New arrivals in ${products.key}</h3><br/>
                <div class="row">
                    <c:forEach items="${products.value}" var="product">
                        <div class="col-lg-4 col-md-6 mb-4">
                            <div class="card h-75">
                                <img class="card-img-top" src="image/product/${product.imagename}.jpg" alt="${product.name}">
                                <div class="card-body">
                                    <p class="card-title">
                                        <a href="foreproduct?pid=${product.id}" title="${product.name}">${product.name}</a>
                                    </p>
                                    <p>$${product.price}</p>
                                </div>
                                <div class="card-footer">Rate: ${product.rate}</div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:forEach>

        </div>
    </div>
</div>
<%@include file="include/footer.jsp"%>

</body>
</html>