<%@page import="com.entities.Account"%>
<%@page import="com.entities.Customer"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<title>View Accounts</title>
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
								<div class="firstline"><h3>Accounts List</h3></div>
								<div class="firstlinelogin"><h3>Balance $ ${fullBalance}</h3></div>
							</div>

							<div class="formstyle">
							
								<div class="secondlinemain">
									<div class="secondline">
										<div class="outsidetable__button">
											<a href="addaccount" class="outsidetable__button-link">Add new account</a>
										</div>
									</div>
									<div class="secondline">
										<div class="outsidetable__button">
											<a href="createtransactionbetween" class="outsidetable__button-link">Make a transfer between accounts</a>
										</div>
									</div>
								</div>
								<table class="customers">
									<tr>
										<th>Account Number</th>
										<th>Bank name</th>
										<th>Routing number</th>
										<c:if test = "${customer.isAdmin()}">
											<th>Customer</th>
										</c:if>
										<th>Type</th>
										<th>Balance</th>
										<th>Edit</th>
										<th>Delete</th>
										<th>View transactions</th>
									</tr>
									<c:forEach var="account" items="${accounts}">
										<tr>
											<td>${account.getAccountNumber()}</td>	
											<td>${account.getBank().toStringForViewAccountsPage()}</td>
											<td>${account.getBank().getRoutingNumber()}</td>	
											<c:if test = "${customer.isAdmin()}">
												<td>${account.getCustomer().toStringForViewAccounts()}</td>
											</c:if>			
											<td>${account.getType()}</td>
											<td class="tdtextalignr">$${account.getBalanceOutput()}</td>
											<td class="tdtextalign">
												<a href="editaccount/${account.getAccount_ID()}">
													<img src="/FinalProject/resources/img/icons/edit-512.png" alt="Edit account" width="20" height="20">
												</a>
											</td>   
											<td class="tdtextalign">
												<a href="deleteaccount/${account.getAccount_ID()}">
													<img src="/FinalProject/resources/img/icons/delete.png" alt="Delete account" width="20" height="20">
												</a>
											</td>
											<td class="tdtextalign">
												<button class="btn btn-primary btn-xs" 
													onclick="window.location.href = 'viewpayments/${account.getAccount_ID()}';">
													View transactions
												</button>
											</td>
										</tr>
									</c:forEach>									
								</table>
								<% if (Boolean.valueOf(request.getParameter("isErrorViewAccounts")) == true) { %>
								<div class="divaftertable">
									<div class="secondlineaftertable__error">
										<div class="login__box-error">
											<%= request.getParameter("errorMessageViewAccounts") %>
										</div>
									</div>
								</div>
								<%} %>
							</div>
						</main>
					</div>
				</section>
				
			</div>
		</div>
		<jsp:include page="footer.jsp" />
	</body>
</html>