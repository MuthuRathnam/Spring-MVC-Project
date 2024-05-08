<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="styles/top.jsp"%>
<meta charset="ISO-8859-1">
<title>ProductInfo</title>
</head>

<body>
	<%@ include file="styles/head.jsp"%>
	<font color="red" size="5">${error}</font>
	<br>
	<div id="container">
		<div class="row">
			<div class="col-sm-5" style="margin-left: 60px">
				<img src="data:image/png;base64,${detail.pic}" width="350" height="250" />
					 <br> 
					 <br>
				<div style="margin-left: 90px">
				
					<form:form action="${pagecontext.request.contextpath }/MyKart/addToCart" method="post" modelAttribute="bean">
						Quantity: <input type="number" name="quantity" min="1" value="1">
						<input type="hidden" name="productId" value="${detail.productId}" />
						<p>
							<input type="submit" class="btn btn-primary" value="Add to Cart" />
						</p>
					</form:form>
				</div>
			</div>

			<div class="col-sm-6" style="padding-left: 70px">
				<h3>
					<font color="blue">${detail.name}</font>
				</h3>
				<h4>
					<font color="maroon">Price:&#8377.${detail.price}</font>
				</h4>
				<p>
					<font color="brown">Model:</font>${detail.type}</p>
				<p>
					<font color="brown">Available Colors:</font>${detail.color}</p>
				<p>
					<font color="brown">Display Size:</font>${detail.size}</p>
				<p>
					<font color="brown">Features:</font>${detail.features}</p>
				<p>
					<font color="brown">Offers:</font>-${detail.offers}</p>
				<p>
					<font color="brown">Details:</font>${detail.details}</p>
				<p>
					<font color="blue">Seller:</font>${detail.seller}</p>
			</div>
		</div>
	</div>
	<br>
	<br>
	<%@ include file="styles/footer.jsp"%>
</body>
</html>