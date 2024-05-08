 
 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="styles/top.jsp"%>
<meta charset="ISO-8859-1">
<title>Cart Page</title>
</head>
<body>
	<%@ include file="styles/users.jsp"%>
	<font size="4">${message}</font>
	<font color="red" size="5">${error}</font>
	<br>
	<br> 
	TotalQuantity= <font color="brown">${data.totalQuantity}</font> 
	TotalPrice= <font color="blue">&#8377.${data.totalPrice}</font>
	
	<div class="row">
		<div id="main">
			<div class="column">
				<p>
					<c:forEach var="items" items="${data.entries}">
						<div class="container">
							<div class="row">
								<div class="column">
									<div class="col-sl-4" style="margin-left: 60px">
									<input type="checkbox" name="productId" value="${items.productId}">
										<img src="data:image/png;base64,${items.product.pic}" width="200" height="160" />
										
										<form:form action="updateCartEntry" method="post" modelAttribute="quantity">
										 Quantity: <input type="number" name="quantity" min="1" max="5" value="${items.quantity}" />
											<input type="hidden" name="price" value="${items.price}" />
											<input type="hidden" name="cartEntryId" value="${items.cartEntryId}" />
											<input type="submit" value="Update" class="btn btn-primary">
										</form:form>
										
									</div>
								</div>

								<div class="col-sl-8" style="padding-left: 50px">
									<h5>
										<font color="blue">${items.product.name}</font>
									</h5>
									<h6>
										<font color="blue">Price:- &#8377.${items.price}</font>
									</h6>
									TotalPrice:- &#8377.<font color="brown">${items.totalPrice}</font><br>

									<form:form action="deleteCartEntry" method="post" modelAttribute="delete">
										<input type="hidden" name="cartEntryId" value="${items.cartEntryId}">
										<button type="submit" class="btn btn-danger">Delete</button>
									</form:form>
									<a href="deliveryAddress">Buy Now</a>
								</div>
							</div>
						</div>
					</c:forEach>
				</p>
			</div>
		</div>
	</div>
	<div class="col-sm-5" style="margin-left: 60px">
		<a href="deliveryAddress" class="btn btn-info">CheckOut All</a>
	</div>
	<br>
	<br>
	<%@ include file="styles/footer.jsp"%>

</body>
</html>