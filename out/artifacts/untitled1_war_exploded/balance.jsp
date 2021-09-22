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

<%@include file="include/header.jsp"%>

<div class="container">
    <div class="row">
        <div class="col-md-3 ">
            <div class="list-group ">
                <a href="profile.jsp" class="list-group-item list-group-item-action">Personal information</a>
                <a href="balance.jsp" class="list-group-item list-group-item-action active">Balance</a>
                <a href="orders.jsp" class="list-group-item list-group-item-action">Orders</a>
            </div>
        </div>
        <div class="col-md-9">
            <div class="card">
                <div class="card-body">
                    <div class="row">
                        <div class="col-md-12">
                            <h4>Your Balance</h4>
                            <hr>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group row">
                                <label class="col-4 col-form-label">Current balance</label>
                                <div class="col-8">
                                    $${customer.balance}
                                </div>
                            </div>

                            <form action="foremodifyBalance" method="post">
                            <div class="row">
                                <div class="container-fluid d-flex justify-content-center">
                                    <div class="col-md-8 col-lg-6">
                                        <div class="card">
                                            <div class="card-header">
                                                <div class="row">
                                                    <div class="col-md-6"> <span>CREDIT/DEBIT CARD PAYMENT</span> </div>
                                                    <div class="col-md-6 text-right" style="margin-top: -5px;"> <img src="https://img.icons8.com/color/36/000000/visa.png"> <img src="https://img.icons8.com/color/36/000000/mastercard.png"> <img src="https://img.icons8.com/color/36/000000/amex.png"> </div>
                                                </div>
                                            </div>
                                            <div class="card-body" style="height: 350px">
                                                <div class="form-group"> <label class="control-label">CARD NUMBER</label> <input type="tel" class="input-lg form-control cc-number" value="1111-2222-3333-0000" readonly> </div>
                                                <div class="row">
                                                    <div class="col-md-6">
                                                        <div class="form-group"> <label class="control-label">CARD EXPIRY</label> <input type="tel" class="input-lg form-control cc-exp" value="08/20" readonly> </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="form-group"> <label class="control-label">CARD CVC</label> <input type="tel" class="input-lg form-control cc-cvc" value="314" readonly> </div>
                                                    </div>
                                                </div>
                                                <div class="form-group"> <label class="control-label">CHARGING AMOUNT</label> <input name="balance" type="number" class="input-lg form-control" placeholder="eg. 4000" min="0"> </div>
                                                <div class="form-group"> <button type="submit" class="btn btn-outline-success btn-lg form-control" style="font-size: .8rem;">MAKE PAYMENT</button></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="include/footer.jsp"%>

</body>
</html>