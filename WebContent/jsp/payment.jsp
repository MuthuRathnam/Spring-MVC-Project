<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="styles/top.jsp"%>
<title>Payment</title>
</head>
<body>
	<%@ include file="styles/checkouthead.jsp"%>
	<br>
	<br>
	<font size="5">${message}</font>
	<div class="container">
		<div class="card w-50 mx-auto my-2">
			<div class="card-header"
				style="text-align: center; background-color: #B0E0E6">
				<h3>2.Payment Methods</h3>
			</div>

			<div class="card-body" style="background-color: #ffffcc">
				Items Cost:-&nbsp&nbsp&nbsp&nbspRs.${datas.totalPrice} <br>
				Delivery Cost:-Rs.70<br> Total Amount:-<b>Rs.${datas.totalPrice+70}</b>

			
			<form:form id="paymentForm" method="post" action="payment"
				modelAttribute="orderBean">

				<br>
				<input type="hidden" name="userId" value="${datas.email}">
				<input type="hidden" name="cartId" value="${datas.cartId}">
				<input type="hidden" name="charges" value="70">
				<input type="radio" name="payType" value="Cash On Delivery">COD
					<p>
					<input type="radio" name="payType" value="Card">Card
				</p>
				<br>
				<div class="text-center">
					<input type="submit" class="btn btn-primary" value="Confirm">
				</div>
			</form:form>
			</div>
		</div>
	</div>

</body>
</html>