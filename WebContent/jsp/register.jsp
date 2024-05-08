<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="styles/include.jsp"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="styles/top.jsp"%>
<meta charset="ISO-8859-1">
<title>Register</title>
<%@ include file="styles/users.jsp"%>
</head>
<body>
	<br>
	<br>
	<div class="container">
		<div class="card w-50 mx-auto my-auto">
			<div class="card-header"
				style="text-align: center; background-color: #ADD8E6;">
				<h3>REGISTER FORM</h3>
			</div>
			<div class="card-body" style="background-color: #ffffcc">
				<font color=red size=5>${error} </font>
				<form:form id="registerform" method="post" action="register"
					modelAttribute="userBean" enctype="multipart/form-data">

					<form:label path="firstName">FirstName:</form:label>
					<form:input id="firstName" name="firstName" path="firstName" />
					<form:errors path="firstName" cssClass="error" />
					<br>

					<form:label path="lastName">LastName:</form:label>
					<form:input id="lastName" name="lastName" path="lastName" />
					<form:errors path="lastName" cssClass="error" />
					<br>
					<form:label path="gender">Gender:</form:label>
					<form:radiobutton id="gender" name="gender" value="M" label="Male"
						path="" />
					<form:radiobutton id="gender" name="gender" value="F"
						label="Female" path="" />
					<br>
					<form:label path="email">Email-Id:</form:label>
					<form:input id="email" type="email" name="email" path="email" />
					<form:errors path="email" cssClass="error" />
					<br>

					<form:label path="password">Enter Password:</form:label>
					<form:password id="password" name="password" path="password" />
					<span class="password-toggle-icon"><i class="fas fa-eye"></i></span>
					<script>
						var passwordField = document.getElementById("password");
						var togglePassword = document
								.querySelector(".password-toggle-icon i");

						togglePassword.addEventListener("click",
								function() {
									if (passwordField.type === "password") {
										passwordField.type = "text";
										togglePassword.classList
												.remove("fa-eye");
										togglePassword.classList
												.add("fa-eye-slash");
									} else {
										passwordField.type = "password";
										togglePassword.classList
												.remove("fa-eye-slash");
										togglePassword.classList.add("fa-eye");
									}
								});
					</script>
					<form:errors path="password" cssClass="error" />
					<br>

					<form:label path="confirmPassword">Confirm the Password:</form:label>
					<form:password id="confirmPassword" name="confirmPassword"
						path="confirmPassword" />
					<form:errors path="confirmPassword" cssClass="error" />
					<br>

					<form:label path="mobile">MobileNumber:</form:label>
					<form:input id="mobile" name="mobile" path="mobile" maxlength="10" />
					<form:errors path="mobile" cssClass="error" />
					<br>

					<form:label path="image">Profile Image:</form:label>
					<form:input type="file" id="image" name="image" path="image" />
					<br>
					<form:label path="house">Flat No:,Apartment Name:</form:label>
					<form:input id="house" name="house" path="house" />
					<br>
					<form:label path="area">Area,Sector:</form:label>
					<form:input id="area" name="area" path="area" />
					<br>
					<form:label path="landMark">LandMark:</form:label>
					<form:input id="landMark" name="landMark" path="landMark" />
					<br>
					<label for="country">Country:</label>
					<select id="country" name="country">
						<option value="44">India</option>
					</select>
                    &nbsp&nbsp&nbsp&nbsp
                    <select id="stateSelect" onchange="stateSelected();"
						class="selector">
						<c:forEach items="${datas}" var="datas">
							<option value="${datas.state}" selected="selected">Select a state</option>
						</c:forEach>
					</select>
					<br> City:-
						<select id="city" name="city">
						<option value="${list.cities}">Select City</option>
					</select> &nbsp&nbsp&nbsp&nbsp&nbsp

						<form:label path="pincode">Pin code:</form:label>
					<form:input id="pincode" name="pincode" path="pincode" />
					<div class="text-center">
						<button type="submit" class="btn btn-primary">Sign-Up</button>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<br>
	<%@ include file="styles/footer.jsp"%>
</body>
</html>