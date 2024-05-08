<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="styles/include.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="styles/top.jsp"%>
<meta charset="UTF-8">
<title>Reset Password</title>
<%@ include file="styles/users.jsp"%>
</head>
<body>
	<br>
	<br>
	<div class="container">
		<div class="card w-50 mx-auto my-auto">
			<div class="card-header" style="text-align: center; color: blue;background-color:#C0C0C0;">
				<h4>Reset Password</h4>
			</div>
			<div class="card-body" style="background-color:#ffffcc;">
				<font color=red>${error}</font>
				<form:form id="forgotForm" method="post" action="forgot"
					modelAttribute="userBean">
					<br>
					<form:label path="email">Email-Id:</form:label>
					<form:input id="email" name="email" path="email" />
					<form:errors path="email" cssClass="error"/>
					<br>

					<form:label path="password">Enter Password:</form:label>
					<form:password id="password" name="password" path="password" />
					<span class="password-toggle-icon"><i class="fas fa-eye"></i></span>
                    <script>
                    const passwordField = document.getElementById("password");
                    const togglePassword = document.querySelector(".password-toggle-icon i");

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

					<form:label path="confirmPassword">Confirm the Password:</form:label>
					<form:password id="confirmPassword" name="confirmPassword" path="confirmPassword" />
					<form:errors path="password" cssClass="error" />
					<br>
					<br>
					<div class="text-center">
						<button type="submit" class="btn btn-primary">Reset</button>
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