<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Home</title>
		<link rel="stylesheet" type="text/css"
			href="/FinalProject/resources/css/style.css">
		 <meta name="viewport" content="width=device-width, initial-scale=1">
	</head>
	<body>
		<div class="wrapper">
			<div class="maincontent">
				<jsp:include page="header.jsp" />
				<section class="content">
					<div class="container">
						<main class="main-content">
						
							<div>
								<div class="firstline">
									<h3>Home</h3>
								</div>
								<div class="firstlinelogin">
									<h3>Last 10 transactions</h3>
								</div>
							</div>
							
							<div class="formstyle">
							
								<div class="secondlinemain">
									<div class="secondline">
										<div class="outsidetable__button">
											<a href="viewaccounts" class="outsidetable__button-link">Go to my accounts</a>
										</div>
									</div>
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
							
							</div>
						</main>
					</div>
				</section>
			</div>
		</div>
		<jsp:include page="footer.jsp" />
	</body>
</html>