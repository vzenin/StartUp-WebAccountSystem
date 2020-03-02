<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Error page</title>
		<link rel="stylesheet" type="text/css"
			href="/FinalProject/resources/css/style.css">
	</head>
	<body>	
		<div class="wrapper">
			<div class="maincontent">
				<jsp:include page="header.jsp" />
				<section class="content">
					<div class="container">
						<main class="main-content">
							<div class="firstline">
								<h3>
									Error page
								</h3>
							</div>
							<div class="formstyle">
								<div class="formwidth">
									<img src="/FinalProject/resources/img/content/errorpage.png" alt="Delete account" width="500">
									<h3>Unknown exception:</h3>
									<h3 class="error">${exception}</h3>
								</div>
							</div>
						</main>
					</div>
				</section>
			</div>
		</div>
		<jsp:include page="footer.jsp" />
	</body>
</html>