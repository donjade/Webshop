<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
	<div class="container">
		<a class="navbar-brand" href="forehome">OriGo webshop</a>

		<form class="form-inline" action="foresearch" method="post">
			<input class="form-control mr-sm-2" type="search" name="keyword" placeholder="Search" aria-label="Search">
			<button class="btn btn-outline-primary my-2 my-sm-0" type="submit">Search</button>
		</form>

		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<c:if test="${customer == null}">
					<li class="nav-item">
						<a class="nav-link" href="login.jsp">Login</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="register.jsp">Register</a>
					</li>
				</c:if>

				<c:if test="${customer != null}">
					<li class="nav-item">
						<a class="nav-link" href="profile.jsp">Welcome ${customer.username}</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="forelogout">Logout</a>
					</li>
				</c:if>
				<li class="nav-item">
					<a class="nav-link" href="cart.jsp">Cart<span class="badge">${cartTotalItemNumber}</span></a>
				</li>
			</ul>
		</div>
	</div>
</nav>

<c:if test="${successmsg != null}">
	<div class="alert text-center" style="background-color: limegreen">
		<span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
		${successmsg}
	</div>
</c:if>
<c:if test="${errormsg != null}">
	<div class="alert text-center" style="background-color: orangered">
		<span class="closebtn" onclick="this.parentElement.style.display='none';" >&times;</span>
		${errormsg}
	</div>
</c:if>



<%--<header>--%>
<%--	<div id="headerText"><a href="forehome">OriGo Webshop</a></div>--%>

<%--	<div class="bar">--%>
<%--		<img src="image/bars.png" alt="bars" height="30" width="30">--%>
<%--		<c:forEach items="${categories}" var="category" varStatus="st">--%>
<%--			<li><a href="forecategory?cid=${category.id}">${category.name}</a></li>--%>
<%--		</c:forEach>--%>
<%--	</div>--%>

<%--	<div class="search-box">--%>
<%--		<form action="foresearch" method="post">--%>
<%--			<input class="search-txt" name="keyword" type="text" placeholder="Search">--%>
<%--			<button class="search-btn" type="submit">--%>
<%--				<img src="image/search.png" alt="search" height="30" width="30">--%>
<%--			</button>--%>
<%--		</form>--%>
<%--	</div>--%>

<%--	<div class="cart">--%>
<%--		<a href="#">--%>
<%--			<img src="image/cart.png" alt="cart" height="55" width="55">--%>
<%--		</a>--%>
<%--	</div>--%>


<%--	<div id="menu-li">--%>
<%--		<ul>--%>
<%--			<li><a href="login.jsp">Login</a></li>--%>
<%--			<li><a>|</a></li>--%>
<%--			<li><a href="register.jsp">Register</a></li>--%>
<%--		</ul>--%>
<%--	</div>--%>
<%--</header>--%>