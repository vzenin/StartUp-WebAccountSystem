<%@page import="com.entities.Account"%>
<%@page import="com.entities.Customer"%>
<%@page import="com.entities.Payment"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<title>View Payments</title>
		<link rel="stylesheet" type="text/css" href="/FinalProject/resources/css/style.css">
	</head>
	<body>
		<div class="wrapper">
			<div class="maincontent">
				<jsp:include page="header.jsp" />		
				<section class="content">
					<div class="container clearfix">
						<main class="main-content">
							<div>
								<div class="firstline">
									<h3>${account.getBank().toStringForViewAccountsPage()} : Account number ${account.getAccountNumber()}</h3>
								</div>
								<div class="firstlinelogin">
									<h3>Balance $ ${account.getBalanceOutput()}</h3>
								</div>
							</div>							
							<div class="formstyle">
							
								<div class="secondlinemain">
									<div class="secondline">
										<div class="outsidetable__button">
											<a href="/FinalProject/addpayment/${id}" class="outsidetable__button-link">Make a payment</a>
										</div>
									</div>
									<div class="secondline">
										<div class="outsidetable__button">
											<a href="/FinalProject/createtransactionto/${id}" class="outsidetable__button-link">Make a transfer to other account</a>
										</div>
									</div>
									<c:if test = "${customer.isAdmin()}">
									<div class="secondline">
										<div class="outsidetable__button">
											<a href="/FinalProject/addincome/${id}" class="outsidetable__button-link">Make an income</a>
										</div>
									</div>
									</c:if>
								</div>
								<table class="customers">
									<tr>
										<th>Date</th>
										<th>Recipient</th>
										<th>Amount</th>
										<c:if test = "${customer.isAdmin()}">
											<th>Edit</th>
											<th>Delete</th>
										</c:if>
										<th>View</th>
									</tr>
									<c:forEach var="payment" items="${payments}">
									<tr>			
										<td>${payment.getDate()}</td>
										<td>${payment.getRecipient()}</td>	
										<td class="tdtextalignr">
											${payment.getAmountOutput()}
										</td>
										<c:if test = "${customer.isAdmin()}">
											<td class="tdtextalign"><a href="/FinalProject/editpayment/${payment.getPayment_ID()}">
												<img src="/FinalProject/resources/img/icons/edit-512.png" alt="Edit account" width="20" height="20">
											</a></td>  
											<td class="tdtextalign"><a href="/FinalProject/deletepayment/${payment.getPayment_ID()}">
												<img src="/FinalProject/resources/img/icons/delete.png" alt="Delete account" width="20" height="20">
											</a></td>
										</c:if>
										<td class="tdtextalign"><a href="/FinalProject/viewpayment/${payment.getPayment_ID()}">
											<img src="/FinalProject/resources/img/icons/search_document-512.png" alt="Delete account" width="20" height="20">
										</a></td>
									</tr>
									</c:forEach>
								</table>
								<div class="divaftertable">
									<div class="secondlineaftertable">
										<div class="outsidetable__button">
											<a href="/FinalProject/viewaccounts" class="outsidetable__button-link">Return to accounts list</a>
										</div>
									</div>
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