<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Login</title>
		<link rel="stylesheet" type="text/css" href="/FinalProject/resources/css/style.css">
		
		  <meta name="viewport" content="width=device-width, initial-scale=1">
		  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
		  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
		  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
		  
	</head>
	<body>
		<div class="wrapper__login">
			<div class="login__box"> 
				<div class="box-top__photo">
				</div>
				<div class="login__box-title">
					Welcome to Web Account System!
				</div>
				<form action="checkLoginPass" method="POST" class="customerform">
					<div>
						<input type="text" id="user" name="username" placeholder="Username"/>
					</div>
					<div>
						<input type="password" id="pass" name="password" placeholder="Password"/>
					</div>
					<div>
						<input type="submit" name="submit" value="Login"/>
					</div>
				</form>		
				<c:if test="${isError}">
					<div class="login__box-error">${errorMessage}</div>
				</c:if>
				<div class="login__box-bottom">
					<!-- <a href="addcustomer">Create new account</a> -->
					<button type="button" class="btn btn-link" onclick="window.location.href = 'addcustomer';">Create new account</button> 
				</div>
			</div>
		</div>
	</body>
</html>