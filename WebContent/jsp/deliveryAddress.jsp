<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="styles/include.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="styles/top.jsp"%>
<title>Select Address</title>
</head>
<body>
	<%@ include file="styles/checkouthead.jsp"%>
	<br>
	<br>
	<div class="container">
		<div class="card w-100 mx-auto my-6">
			<div class="card-header"
				style="text-align: center; background-color: #B0E0E6">
				<h4>1.Select Delivery Address</h4>
			</div>
			<div class="card-body" style="background-color: #ffffcc">
				<form:form id="deliveryAddressform" action="deliveryAddress"  method="post" modelAttribute="orderBean" >
				
					<input type="hidden" name="userId" value="${userData.email}">
					<input type="checkbox" name="address" value="${userData.address}">
				${userData.firstName}&nbsp${userData.lastName} ,
				${userData.address}. &nbsp Mobile:${userData.mobile}&nbsp |
				<a href="newAddress/?emailId=${userData.email}">Edit Address</a>
					<br>
					<br>

					<input type="checkbox" name="address" value="${userData.address2}">
				 ${userData.firstName}&nbsp${userData.lastName} ,${userData.address2}. 
				 &nbsp Mobile:${userData.mobile}&nbsp |
				<a href="newAddress/?emailId=${userData.email}">Edit Address</a>
					<br>
					<br>
					<a href="newAddress/?emailId=${userData.email}">+ Add New Address</a>
					<br>
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