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
		<title>Edit account</title>
		<link rel="stylesheet" type="text/css" href="/FinalProject/resources/css/style.css">
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
									Edit account
								</h3>
							</div>
							<div class="formstyle">
								<div class="formwidth">	
		
									<form:form method="POST" action="/FinalProject/savechangesofaccount" modelAttribute="newAccount">		
										<table class="customerform">
											<tr>
												<td></td>
												<td><form:hidden path="account_ID" /></td>
											</tr>
											<tr>
												<td>
													<c:if test = "${customer.isAdmin()}">
													<label for = "customer.customer_ID">Customer ID:
														<form:input path = "customer.customer_ID" />
													</label>
													</c:if>
													<c:if test = "${!customer.isAdmin()}">
													<label for = "customer.customer_ID">
														<form:hidden path = "customer.customer_ID" />
													</label>
													</c:if>
												</td>
												<td></td>
											</tr>
											<tr>
												<td>
													<label for = "bank">Bank:
														<form:select path="bank.bank_ID" class="customerform">
															<option value="${bank.getBank_ID()}">${bank.toStringForEditAccountPage()}</option>
															<c:forEach items="${banks}" var="bank">		
																<c:if test = "${itembank.getBank_ID()!=bank.getBank_ID()}">
																	<form:option value="${bank.getBank_ID()}" label="${bank.toStringForEditAccountPage()}"/>
																</c:if>																		
															</c:forEach>
														</form:select>
													</label>
												</td>
												<td></td>
											</tr>
											<tr>
												<td>						
													<label for = "accountNumber">Account Number:
														<form:input path = "accountNumber" />
														<form:errors path="accountNumber" cssClass="error" />
													</label>
												</td>
												<td></td>
											</tr>
											<tr>
												<td>
													<label for="type" CLASS="container">Type:
														<form:radiobutton path = "type" value = "Checking" label = "Checking" />
								                  		<form:radiobutton path = "type" value = "Savings" label = "Savings" />
								                  		<form:radiobutton path = "type" value = "Credit" label = "Credit" />							
													</label>
												</td>
												<td></td>
											</tr>				
											<tr>
												<td>
													<br>
													<label for="balance">Balance: 
														<b>$${newAccount.getBalanceOutput()}</b>
														<form:hidden path="balance" />
													</label>
												</td>
												<td></td>
											</tr>
											<tr>
												<td>						
													<label for="active"><!-- Active: -->
														<form:hidden path="active" />
													</label>
												</td>
												<td></td>
											</tr>
											<tr>
												<td><input type="submit" value="Save" /></td>
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
	</body>
</html>