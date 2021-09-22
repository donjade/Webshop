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

<%@include file="include/header.jsp"%>

<div class="container">
    <div class="row">
        <div class="col-md-3 ">
            <div class="list-group ">
                <a href="profile.jsp" class="list-group-item list-group-item-action active">Personal information</a>
                <a href="balance.jsp" class="list-group-item list-group-item-action">Balance</a>
                <a href="orders.jsp" class="list-group-item list-group-item-action">Orders</a>
            </div>
        </div>
        <div class="col-md-9">
            <div class="card">
                <div class="card-body">
                    <div class="row">
                        <div class="col-md-12">
                            <h4>Your Profile</h4>
                            <hr>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <form action="foremodifyProfile" method="post">
                                <input type="hidden" name="id" value="${customer.id}"/>
                                <div class="form-group row">
                                    <label for="username" class="col-4 col-form-label">User Name</label>
                                    <div class="col-8">
                                        <input id="username" name="username" placeholder="Username" class="form-control here" required="required" value="${customer.username}" type="text">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="newpass" class="col-4 col-form-label">Password</label>
                                    <div class="col-8">
                                        <input id="newpass" name="password" value="${customer.password}" placeholder="New Password" class="form-control here" type="text">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="email" class="col-4 col-form-label">Email</label>
                                    <div class="col-8">
                                        <input id="email" name="email" value="${customer.email}" placeholder="Email" class="form-control here" required="required" type="text">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="name" class="col-4 col-form-label">First Name</label>
                                    <div class="col-8">
                                        <input id="name" name="firstname" value="${customer.firstname}" placeholder="First Name" class="form-control here" type="text">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="lastname" class="col-4 col-form-label">Last Name</label>
                                    <div class="col-8">
                                        <input id="lastname" name="lastname" value="${customer.lastname}" placeholder="Last Name" class="form-control here" type="text">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="lastname" class="col-4 col-form-label">Mobile</label>
                                    <div class="col-8">
                                        <input name="mobile" value="${customer.mobile}" placeholder="Mobile number" class="form-control here" type="text">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="newpass" class="col-4 col-form-label">Address</label>
                                    <div class="col-8">
                                        <input name="address" value="${customer.address}" placeholder="Address" class="form-control here" type="text">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="offset-4 col-8">
                                        <button name="submit" type="submit" class="btn btn-primary">Update</button>
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