<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
	<head>
		<title>Edit bank</title>
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
									Edit bank
								</h3>
							</div>
							
							<div class="formstyle">
								<div class="formwidth">	

									<form:form method="POST" action="/FinalProject/savechangesofbank" >
										<table class="customerform">
											<tr>
												<td><form:hidden path="bank_ID" /></td>
												<td></td>
											</tr>
											<tr>
												<td>
													<label for="name">Bank name:
														<form:input path="name" />
													</label>
												</td>
												<td></td>
											</tr>
											<tr>
												<td>						
													<label for="region">Region:
														<form:input path="region" />
													</label>
												</td>
												<td></td>
											</tr>
											<tr>
												<td>						
													<label for="routingNumber">Routing Number:
														<form:input path="routingNumber" />
													</label>
												</td>
												<td></td>
											</tr>
											<tr>
												<td>
													<label for="address">Address:</label>
													<form:textarea path = "address" rows = "3" cols = "30" />
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