<%@page import="com.entities.Account"%>
<%@page import="com.entities.Customer"%>
<%@page import="com.entities.Bank"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
	<head>
		<title>Make a payment</title>
		<link rel="stylesheet" type="text/css" href="/FinalProject/resources/css/style.css">
		 <meta name="viewport" content="width=device-width, initial-scale=1">
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
									Make a payment
								</h3>
							</div>
							<div class="formstyle">
								<div class="formwidth">	
		
									<form:form method="POST" action="/FinalProject/createnewpayment">		
										<table class="customerform">
											<tr>
												<td>						
													<label for = "payment_ID">
														<form:hidden path = "payment_ID" value="${payment.getPayment_ID()}"/>
													</label>
												</td>
												<td></td>
											</tr>
											<tr>						
												<td>
													<label for = "account">Account number, bank name:
														<form:hidden path = "account.account_ID" />
														<form:input path = "" value = "${command.getAccount().toStringForTransactionTo()}" disabled="true"/>
													</label>
												</td>
												<td></td>
											</tr>
											<tr>
												<td>						
													<label for="date">Date:
														<form:input path = "date" disabled="true"/>
													</label>
												</td>
												<td></td>
											</tr>	
											<tr>
												<td>
													<label for="recipient">Recipient:
														<form:input path="recipient" />
													</label>
												</td>
												<td></td>
											</tr>
											<tr>
												<td>
													<label for="amount">Amount:
														<form:input path="amount" />
													</label>
												</td>
												<td></td>
											</tr>
											<tr>
												<td>
												<!-- possible values: 0 - credit, 1 - debit -->
													<label for="mathod"><!-- Method: -->
														<form:hidden path="method" />
													</label>
												</td>
												<td></td>
											</tr>
											<tr>
												<td>
													<label for="description">Description:
														<form:textarea path = "description" rows = "3" cols = "30" />
													</label>
												</td>
												<td></td>
											</tr>
											<tr>
												<td>
													<!-- <input type="submit" value="Create" /> -->
													<input type="button" onclick="myFunction()" value="Create" />
												</td>
												<td></td>
											</tr>
											<tr>
												<td id="tdwitherror">
													<c:if test="${isError}">
														<div class="login__box-error">${errorMessage}</div>
													</c:if>
												</td>
												<td></td>
											</tr>											
										</table>
									</form:form>
								</div>
							</div>
						</main>
					</div>
				</section>
			</div>
		</div>
		<jsp:include page="footer.jsp" />
		<script src="/FinalProject/resources/js/amountcheck.js"></script>
	</body>
</html>