<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="<c:url value='/template/web/css/login.css'/>">
</head>
<body>
	<div class="container">
		<form action="/test/login" method="post">
			<div class="row">
				<h2 style="text-align: center">Login Form</h2>
				<div class="col">
					<c:if test="${not empty message}">
						<center><p style="color: red;">${message }</p></center>
					</c:if>
					<input type="text" name="username" placeholder="Username" required>
					<input type="password" name="password" placeholder="Password" required> 
					<input type="submit" value="Login">
					<a href="/test/home" style="text-decoration: none;">&#10229; Return Blog Home</a>
				</div>
			</div>
		</form>
	</div>

</body>
</html>