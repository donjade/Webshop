<div class="col-3">
<nav>
	<ul class="mainmenu">
		<c:forEach items="${categories}" var="category">
			<li>
				<a class="border border-light" href="forecategory?cid=${category.id}">${category.name}</a>
				<ul class="submenu">
				<c:forEach items="${category.subCategories}" var="subCategory">
					<li><a href="foresubCategory?subcid=${subCategory.id}">${subCategory.name}</a></li>
				</c:forEach>
				</ul>
			</li>
		</c:forEach>
	</ul>
</nav>
</div>