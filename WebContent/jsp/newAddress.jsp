<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="styles/include.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add New Address</title>
</head>
<body>
<div style="centre">
				<h2>New Address Form</h2>
			
				<font color=red size=5>${error}</font>
				
				<form:form id="newAddressform" method="post" action="http://localhost:8080/MyKart/deliveryAddress"
					modelAttribute="userBean">
                    <input type="hidden" name="userId" value="${userData.userId}">
                    <input type="hidden" name="email" value="${userData.email}">
					New Address:
					<form:input id="address2" name="address2" value="${userData.address2}" path="address2" />
					<br>
					<br>
						<input type="submit" value="Submit">
				</form:form>
				</centre>
</body>
</html>