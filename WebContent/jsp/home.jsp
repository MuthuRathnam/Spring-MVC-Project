<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="styles/top.jsp" %>
<meta charset="ISO-8859-1">
<title>MyKart Home</title>
</head>

<body>
	<%@ include file="styles/head.jsp"%>
	<font color="red" size="5">${error}</font>
	<font color="green" size="5">${message}</font>
	<br>
	<div id="container">
		<div class="row">
			<p>
				<c:forEach var="main" items="${stock}">
					<div class="col-sm-4">
						<div class="col-sm-12" style="margin-left: 50px">
							<a href="http://localhost:8080/MyKart/subCategory/${main.id}">
								<img src="data:image/jpg;base64,${main.pic}" width="200" height="150" />
							</a>
						</div>

						<div class="row">
							<div class="col-sm-12" style="margin-left: 150px">
								<div>${main.name}</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</p>
		</div>
	</div>
	<br>
	<br>
	<%@ include file="styles/footer.jsp"%>
</body>
</html>