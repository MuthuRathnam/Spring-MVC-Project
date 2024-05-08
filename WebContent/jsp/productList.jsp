<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="styles/top.jsp"%>
<meta charset="ISO-8859-1">
<title>Products List</title>
</head>
<body>
	<%@ include file="styles/head.jsp"%>
	<font color="red" size="5">${error}</font>
	<br>
	<div class="row">
		<div id="main">
			<div class="column">
				<p>
					<c:forEach var="items" items="${list}">
						<div class="container">
							<div class="row">
								<div class="col-sm-5">
									<div style="margin-left: 50px">
										<a
											href="http://localhost:8080/MyKart/productInfo/${items.productId}">
											<img src="data:image/jpg;base64,${items.pic}" width="250"
											height="180" />
										</a>
									</div>
								</div>
								<div class="col-sl-7">
									<div style="padding-left: 100px">
										<h4>
											<font color="blue">${items.name}</font>
										</h4>
										<h5>
											<font color="maroon">Price:-&#8377.${items.price}</font>
										</h5>
										<p>Color:-${items.color}</p>
										<p>Size:-${items.size}</p>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				<p>
			</div>
		</div>
	</div>
</body>
</html>