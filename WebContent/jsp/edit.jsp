<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional/ /EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<%@ include file="styles/top.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit</title>
<%@ include file="styles/users.jsp"%>
</head>
<body>
	<br>
	<br>
	<div class="container">
		<div class="card w-50 mx-auto my-auto">
			<div class="card-header"
				style="text-align: center; background-color: #ADD8E6;">
				<h3>Edit Profile</h3>
			</div>
			<div class="card-body" style="background-color: #ffffcc">

				<font color="blue">${message}</font>

				<form:form id="editform" method="post"
					action="http://localhost:8080/MyKart/update"
					modelAttribute="userBean" enctype="multipart/form-data">

					<form:label path="firstName">Enter your FirstName:</form:label>
					<form:input id="firstName" name="firstName" path="firstName"
						value="${userBean.firstName}" />
					<br>
					<form:label path="lastName">Enter your LastName:</form:label>
					<form:input id="lastName" name="lastName" path="lastName"
						value="${userBean.lastName}" />
					<br>
					<form:label path="email">Enter your Email:</form:label>
					<form:input id="email" path="email" readonly="true"
						value="${userBean.email}" />
					<br>
					<form:label path="gender">Gender:</form:label>
					<form:radiobutton id="gender" name="gender" value="M" label="Male"
						path="gender" />
					<form:radiobutton id="gender" name="gender" value="F"
						label="Female" path="gender" />
					<br>
					<form:label path="mobile">MobileNumber:</form:label>
					<form:input id="mobile" name="mobile" path="mobile" maxlength="10"
						value="${userBean.mobile}" />
					<br>
					<form:label path="image">Upload Image:</form:label>
					<form:input type="file" id="image" name="image" path="image" />
					<br>
					<form:label path="house">Flat No:,Apartment Name:</form:label>
					<form:input id="house" name="house" path="house"
						value="${userBean.house}" />
					<br>
					<form:label path="area">Area,Sector:</form:label>
					<form:input id="area" name="area" path="area"
						value="${userBean.area}" />
					<br>
					<form:label path="landMark">LandMark:</form:label>
					<form:input id="landMark" name="landMark" path="landMark"
						value="${userBean.landMark}" />
					<br>
						City:-<select id="city" name="city">
						
						<form:input name="city" path="city" value="${userBean.city}" />
					</select>
					&nbsp&nbsp&nbsp&nbsp
                    
                    State:-<select id="stateSelect" class="selector">
						<option value="${datas.state}" selected="selected">${userBean.state}</option>
					</select>
					<br>
					<label for="country">Country:-</label>
					<select id="country" name="country">
						<option value="44">${userBean.country}</option>
					</select>
                    
				 &nbsp&nbsp&nbsp&nbsp&nbsp

						<form:label path="pincode">Pin code:</form:label>
					<form:input id="pincode" name="pincode" path="pincode"
						value="${userBean.pincode}" />
					<br>
					<div class="text-center">
						<button type="submit" class="btn btn-primary">Update</button>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<br>
	<%@ include file="styles/footer.jsp"%>
</body>
</html>