<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="styles/include.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="styles/top.jsp"%>
<meta charset="ISO-8859-1">
<title>Login</title>
<%@ include file="styles/users.jsp"%>
</head>
<body>
	<br>
	<br>
	<div class="container">
		<div class="card w-50 mx-auto my-6">
			<div class="card-header"
				style="text-align: center; background-color: #ADD8E6">
				<h4>LOGIN</h4>
			</div>
			<div class="card-body" style="background-color: #FFFFFF">
				<font color=red>${error}</font>

				<form:form id="loginForm" method="post" action="login"
					modelAttribute="userBean">

					<form:label path="email">Email-Id</form:label>
					<form:input id="email" name="email" path="email" />
					<form:errors path="email" cssClass="error" />
					<br>
					<br>
					<form:label path="password">Password</form:label>
					<input id="password" type="password" name="password" >
					<span class="password-toggle-icon"><i class="fas fa-eye"></i></span>
                    <script>
                    var passwordField = document.getElementById("password");
                    var togglePassword = document.querySelector(".password-toggle-icon i");

                    togglePassword.addEventListener("click", function () {
                      if (passwordField.type === "password") {
                        passwordField.type = "text";
                        togglePassword.classList.remove("fa-eye");
                        togglePassword.classList.add("fa-eye-slash");
                      } else {
                        passwordField.type = "password";
                        togglePassword.classList.remove("fa-eye-slash");
                        togglePassword.classList.add("fa-eye");
                      }
                    });
                    </script>
					<form:errors path="password" cssClass="error" />
					
					<br>
					<br>
					<div class="text-center">
						<button type="submit" class="btn btn-primary">Login</button>
						&nbsp&nbsp&nbsp <a href="forgot">Forgot Password!</a>
						&nbsp&nbsp&nbsp <a href="register">Register</a>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<br>
	<br>
	<%@ include file="styles/footer.jsp"%>
</body>
</html>