<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional/ /EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="styles/top.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile</title>
<%@ include file="styles/users.jsp"%>
</head>
<body>
	<br>
	<br>
	<div class="container">
		<div class="card w-50 mx-auto my-6">
			<div class="card-header"
				style="text-align: center; background-color: #ADD8E6">
				<h4>Profile</h4>
			</div>
			<div class="card-body" style="background-color: #FFFFFF">

				<form:form method="get" action="edit" modelAttribute="userBean"
					enctype="multipart/form-data">
					<h1>
						<font color="maroon" size="5">Your Details</font>
					</h1>
					<img width="100" height="100"
						src="data:image/jpg;base64,${userBean.pic}" />
					<br>
					<br>
	FirstName : ${userBean.firstName}
	<br>
					<br> 
	LastName : ${userBean.lastName}
	<br>
					<br> 
	Email : ${userBean.email}
	<br>
					<br> 
	Gender: ${userBean.gender}
	<br>
					<br> 
	Mobile Number : ${userBean.mobile}
	<br>
					<br>
	Address:${userBean.house},${userBean.area},
	        ${userBean.landMark},${userBean.city},
	        ${userBean.state},${userBean.country}-${userBean.pincode}. 
					<br>
					<br>
					<div class="text-center">
						<a
							href="http://localhost:8080/MyKart/profile/edit/?emailId=${userBean.email}">Edit</a>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<br>
	<br>
	<%@ include file="styles/footer.jsp"%>
	<br>
</body>
</html>