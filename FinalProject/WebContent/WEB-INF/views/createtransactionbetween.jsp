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
		<title>Create a transfer between my accounts</title>
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
									Create a transfer between my accounts
								</h3>
							</div>
							
							<div class="formstyle">
								<div class="formwidth">	

									<form:form method="POST" action="/FinalProject/savetransactionbetween">		
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
													<label for="date">Date:
														<form:input path = "date" disabled="true"/>
													</label>
												</td>
												<td></td>
											</tr>	
											<tr>						
												<td>
													<label for = "account"><b>FROM</b> Account number, bank name:
														<form:select path="account.account_ID">
															<option value="${command.getAccount().getAccount_ID()}">
																${command.getAccountToBoo() ? "Select an account ": accountFrom.toStringForTransactionTo()}
															</option>
															<c:forEach items="${accounts}" var="account">
																<option value="${account.getAccount_ID()}">${account.toStringForTransactionTo()}</option>
															</c:forEach>
														</form:select>
													</label>
													<div class="error">${errMsgForAccountFrom}</div>
												</td>
												<td></td>
											</tr>
											<tr>						
												<td>
													<label for = "recipient"><b>TO</b> Account number, bank name:
														<form:select path="recipient">
															<option value="${command.getRecipient()}">
																${command.getAccountToBoo() ? "Select an account ": accountTo.toStringForTransactionTo()}
															</option>
															<c:forEach items="${accounts}" var="account">
																<option value="${account.getAccount_ID()}">${account.toStringForTransactionTo()}</option>
															</c:forEach>
														</form:select>
														
													</label>
													<div class="error">${errMsgForAccountTo}</div>
												</td>
												<td></td>
											</tr>
											<tr>
												<td>
													<label for="amount">Amount, $:
														<form:input path="amount" placeholder="0.00" />
													</label>
													<div class="error">${errMsgForAmount}</div>
												</td>
												<td></td>
											</tr>
											<tr>
												<td>
												<!-- possible values: 0 - credit, 1 - debit -->
													<label for="method"><!-- Method: -->
														<form:hidden path="method" />
													</label>
												</td>
												<td></td>
											</tr>
											<tr>
												<td>
													<label for="description">
														<form:hidden path = "description" />
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