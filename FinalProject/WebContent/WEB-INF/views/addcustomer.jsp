<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
	<head>
		<title>New account</title>
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
									<c:if test = "${customer.isAdmin()}">
										<h3>Add new customer</h3>
									</c:if>
									<c:if test = "${!customer.isAdmin()}">
										<h3>Create new account</h3>
									</c:if>
								</h3>
							</div>
							<div class="formstyle">
								<div class="formwidth">
									<form:form method="POST" action="createnewcustomer" modelAttribute="newCustomer">
										<table class="customerform">
											<tr>
												<td>
													<label for="firstName">First name:
														<form:input path="firstName" placeholder="First name" />
														<form:errors path="firstName" cssClass="error" />
													</label>
												</td>
												<td>
													<label for="lastName">Last name:
														<form:input path="lastName" placeholder="Last name"/>
														<form:errors path="lastName" cssClass="error" />
													</label>
												</td>
											</tr>
											<tr>
												<td>
													<label for="gender" CLASS="container">Gender:
														<form:radiobutton path = "gender" value = "male" label = "male" />
								                  		<form:radiobutton path = "gender" value = "female" label = "female" />
							                  		</label>
												</td>
												<td>
													<label for="birthday">Birthday:</label>
													<form:input path="birthday" type="date" placeholder="YYYY-MM-DD"/>
													<form:errors path="birthday" cssClass="error" />
												</td>
											</tr>				
											<tr>
												<td>
													<label for="phone">Phone:</label>
													<form:input path="phone" placeholder="917-000-0000"/>	
													<form:errors path="phone" cssClass="error" />				
												</td>
												<td>
													<label for="email">Email:</label>
													<form:input path="email" placeholder="Email@domen.com" />	
													<form:errors path="email" cssClass="error" />				
												</td>
											</tr>
											<tr>
												<td colspan="2">
													<label for="address">Address:</label>
													<form:textarea path = "address" rows = "3" cols = "30" />
													<form:errors path="address" cssClass="error" />
												</td>
											</tr>
											<tr>
												<td>
													<label for="login">Username:</label>
													<form:input path="login" placeholder="Username" />	
													<form:errors path="login" cssClass="error" />				
												</td>
												<td>
													<label for="password">Password:</label>
													<form:input path="password" placeholder="password" />
													<form:errors path="password" cssClass="error" />
												</td>
											</tr>
											<tr>
												<td>
													<c:if test = "${customer.isAdmin()}">
													<label for="active">Active:
														<form:checkbox path = "active"/>
													</label>
													</c:if>
													<c:if test = "${!customer.isAdmin()}">
														<form:hidden path = "active"/>
													</c:if>
												</td>
												<td>
													<c:if test = "${customer.isAdmin()}">
													<label for="admin">Admin:
														<form:checkbox path = "admin"/>
													</label>						
													</c:if>
													<c:if test = "${!customer.isAdmin()}">
														<form:hidden path = "admin"/>
													</c:if>
												</td>
											</tr>
											<tr>
												<td></td>
												<td>
													<input type="submit" value="Create" />
												</td>
											</tr>
											<tr>
												<td id="tdwitherror" colspan="2">
													<c:if test="${isError}">
														<div class="login__box-error">${errorMessage}</div>
													</c:if>
												</td>
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