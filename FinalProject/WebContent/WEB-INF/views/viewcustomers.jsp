<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<title>View Customers</title>
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
								<div class="firstline"><h3>Customers List</h3></div>
							</div>
							<div class="formstyle">
							
								<div class="secondlinemain">
									<div class="secondline">
										<div class="outsidetable__button">
											<a href="addcustomer" class="outsidetable__button-link">Add new customer</a>
										</div>
									</div>
								</div>
								<table class="customers">
									<tr>
										<th>First Name</th>
										<th>Last Name</th>
										<th>Email</th>
										<th>isActive</th>
										<th>Admin</th>
										<th>Edit</th>
										<th>Delete</th>
									</tr>
									<c:forEach var="customer" items="${customers}">
									<tr>
										<td>${customer.getFirstName()}</td>
										<td>${customer.getLastName()}</td>		
										<td>${customer.getEmail()}</td>
										<td>${customer.isActive()}</td>
										<td>${customer.isAdmin()}</td>
										<td class="tdtextalign"><a href="editcustomer/${customer.getCustomer_ID()}">
											<img src="/FinalProject/resources/img/icons/edit-512.png" alt="Edit account" width="20" height="20">
										</a></td>  
										<td class="tdtextalign"><a href="deletecustomer/${customer.getCustomer_ID()}">
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