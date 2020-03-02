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
		<title>Information of the payment</title>
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
								<div class="firstline"><h3>Information of the payment</h3></div>
							</div>							
							<div class="formstyle">
								<table class="customers" >
									<tr>						
										<td>#</td>
										<td>${payment.getPayment_ID()}</td>
									</tr>
									<tr>
										<td>Date</td>
										<td>${payment.getDate()}</td>
									</tr>
									<tr>						
										<td>Account Number, Bank name</td>
										<td>${payment.getAccount().toStringForViewPaymentsPage()}</td>
									</tr>
									<tr>
										<td>Recipient</td>
										<td>${payment.getRecipient()}</td>
									</tr>
									<tr>
										<td>Amount</td>
										<td>${payment.getAmountOutput()}</td>
									</tr>
									<tr>
										<td>Description</td>
										<td>${payment.getDescription()}</td>
									</tr>
								</table>
								<div class="divaftertable">
									<div class="secondlineaftertable">
									
										<div class="outsidetable__button">
											<a href="/FinalProject/index" 
												class="outsidetable__button-link">
													Home page
											</a>
										</div>
										
										<div class="outsidetable__button">
											<a href="/FinalProject/viewpayments/${payment.getAccount().getAccount_ID()}" 
												class="outsidetable__button-link">
													Go to payments list
											</a>
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