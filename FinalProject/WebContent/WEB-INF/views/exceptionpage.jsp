<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Exception page</title>
		<link rel="stylesheet" type="text/css"
			href="/FinalProject/resources/css/style.css">
	</head>
	<body>	
				
		<jsp:include page="menu.jsp" />
		
		<h3>Exception page</h3>
		<h5>login: ${customer.getLogin()} </h5>
		
		<%-- <h3>${exception.exceptionMsg}</h3> --%>
		
	</body>
</html>