<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
<ul>				
	<li><a href="/FinalProject/index">Home</a></li>
	<li><a href="/FinalProject/editcustomer/${customer.getCustomer_ID()}">My profile</a></li>
	<li class="dropdown">
		<a href="javascript:void(0)" class="dropbtn">Tools</a>
		<div class="dropdown-content">
			<c:if test = "${customer.isAdmin()}">
				<a href="/FinalProject/viewcustomers">View customers</a>
			</c:if>
			<a href="/FinalProject/viewbanks">View banks</a>
			<a href="/FinalProject/viewaccounts">View accounts</a>		
			<a href="/FinalProject/viewpayments/${command.getAccount().getAccount_ID()}">View payments</a>      
	    </div>
	</li>
	<li><a href="/FinalProject/signout">Sign out</a></li>
</ul>
	
	
	



			      
