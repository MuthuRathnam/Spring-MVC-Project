<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="styles/top.jsp"%>
<meta charset="ISO-8859-1">
<title>Brand Categories</title>
</head>
<body>
	<%@ include file="styles/head.jsp"%>
	<font color="red" size="5">${error}</font>
	<br>
	<div id="main">
		<div class="row">
			<p>
				<c:forEach var="brType" items="${logo}">
					<div class="col-sm-4">
						<div class="container">
							<div class="row">
								<div class="col-sm-12" style="margin-left: 50px">
										<a
											href="http://localhost:8080/MyKart/productList/${brType.id}">
											<img src="data:image/jpg;base64,${brType.pic}" width="200"
											height="150" />
										</a>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12" style="margin-left: 140px">
									<div>${brType.name}</div>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			<p>
		</div>
	</div>
	<br>
	<br>
	<%@ include file="styles/footer.jsp"%>
</body>
</html>
