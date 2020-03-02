<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<title>View Banks</title>
		<link rel="stylesheet" type="text/css" href="/FinalProject/resources/css/style.css">
	</head>
	<body>
		<div class="wrapper">
			<div class="maincontent">
				<jsp:include page="header.jsp" />
				<section class="content">
					<div class="container">
						<main class="main-content">
							<div>
								<div class="firstline"><h3>Banks List</h3></div>
							</div>

							<div class="formstyle">
							
								<div class="secondlinemain">
									<div class="secondline">
										<div class="outsidetable__button">
											<a href="addbank" class="outsidetable__button-link">Add new bank</a>
										</div>
									</div>
								</div>
								<table class="customers">
									<tr>
										<th>Routing Number</th>
										<th>Name</th>
										<th>Region</th>
										<th>Address</th>
										<th>Edit</th>
										<th>Delete</th>
									</tr>
									<c:forEach var="bank" items="${banks}">
									<tr>
										<td>${bank.getRoutingNumber()}</td>
										<td>${bank.getName()}</td>
										<td>${bank.getRegion()}</td>
										<td>${bank.getAddress()}</td>
										<td class="tdtextalign"><a href="editbank/${bank.getBank_ID()}">
											<img src="/FinalProject/resources/img/icons/edit-512.png" alt="Edit account" width="20" height="20">
										</a></td>  
										<td class="tdtextalign"><a href="deletebank/${bank.getBank_ID()}">
											<img src="/FinalProject/resources/img/icons/delete.png" alt="Delete account" width="20" height="20">
										</a></td>
									</tr>
									</c:forEach>
								</table>
							</div>
						</main>
					</div>
				</section>
			</div>
		</div>
		<jsp:include page="footer.jsp" />
	</body>
</html>