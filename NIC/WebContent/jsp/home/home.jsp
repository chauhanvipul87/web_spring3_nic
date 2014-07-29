<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${username == null}" >
	<% response.sendRedirect("index.html"); %>
</c:if>
<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="../header/header.jsp"></jsp:include>
</head>
<body>
	<!-- TOP BAR -->
	<jsp:include page="../menu/menu.jsp"></jsp:include>
	<!-- end top-bar -->
	<jsp:include page="../menu/sliding_worlds.jsp"></jsp:include>
	<!-- HEADER -->
	<div id="header-with-tabs">
		
		<div class="page-full-width cf">
			<ul id="tabs" class="fl">
				<li><a href="javascript:void(0)" class="dashboard-tab" >Reminder Policy List</a></li>
			</ul> <!-- end tabs -->
			<!-- Change this image to your own company's logo -->
			<!-- The logo will automatically be resized to 30px height. -->
			<a href="javascript:void(0);" id="company-branding-small" class="fr"><img src="images/company-logo.png" alt="Blue Hosting" /></a>
			
		</div> <!-- end full-width -->	

	</div> <!-- end header -->
	

	<!-- MAIN CONTENT -->
	<div id="content">
         <div id="ajax_loader" style="text-align: center;">
				<img style="vertical-align: middle;" alt="loading..." src="images/loading2.gif" height="35"
					/> &nbsp; Processing...Please wait...
		</div>
		
		<div class="page-full-width cf">

			<div class="content-module">
			
				<div class="content-module-heading cf">
				
					<h3 class="fl">Current Month's Policies: </h3>
					<span class="fr expand-collapse-text">Click to collapse</span>
					<span class="fr expand-collapse-text initial-expand">Click to expand</span>
				
				</div> <!-- end content-module-heading -->
				
				<div class="content-module-main">
					<div id="response1">	
					<c:choose>
						<c:when test="${ errorMsg != null}">
							<div id="emsg" class="${errorClass}">${errorMsg}</div>	
						</c:when>
					 <c:otherwise>
					<table class="table_myform" style="width: 100%;">
						<thead>
							<tr>
								<th style="padding: 7px;">Name</th>
								<th>Address</th>
								<th>City</th>
								<th>Phone</th>
								<th>Policy No</th>
								<th>Expiry Date</th>
								<th>Amount</th>
								<th>Policy Type </th>
							</tr>
						</thead>
						<tbody >
							<c:choose>
									<c:when test="${fn:length(currentMonthPolicyList) > 0 }">
									<c:forEach var="u" items="${currentMonthPolicyList}">    
											<tr>
													<td>${u.name}</td>
													<td>${u.address}</td>
													<td>${u.city}</td>
													<td>${u.phone}</td>
													<td style="background: #FFC022;">${u.policy_no}</td>
													<td style="background: #FFC022;">${DateTimeFormater.formateDate(u.exp_date)}</td>
													<td>${u.amount}</td>
													<td>${u.policy_type}</td>
											</tr>
									</c:forEach>		
									</c:when>
									<c:otherwise>
											<tr>
												<td colspan="8" style="text-align: center;">No Record Found.</td>
											</tr>
									</c:otherwise>
							</c:choose>
						</tbody>
					</table>
    				</c:otherwise>
    			</c:choose>
    			
				</div>
					<div class="stripe-separator"><!--  --></div>
				</div> <!-- end content-module-main -->
				
				<!-- Code for next month -->
				<br/><br/>
				<div class="content-module-heading cf">
				
					<h3 class="fl">Next Month's Policies : </h3>
					<span class="fr expand-collapse-text">Click to collapse</span>
					<span class="fr expand-collapse-text initial-expand">Click to expand</span>
				
				</div> <!-- end content-module-heading -->
				
				<div class="content-module-main">
					<div id="response2">
						<table class="table_myform" style="width: 100%;">
						<thead>
							<tr>
								<th>Name</th>
								<th>Address</th>
								<th>City</th>
								<th>Phone</th>
								<th>Policy No</th>
								<th>Expiry Date</th>
								<th>Amount</th>
								<th>Policy Type </th>
							</tr>
						</thead>
						<tbody >
							<c:choose>
									<c:when test="${fn:length(nextMonthPolicyList) > 0 }">
									<c:forEach var="u" items="${nextMonthPolicyList}">    
											<tr>
													<td>${u.name}</td>
													<td>${u.address}</td>
													<td>${u.city}</td>
													<td>${u.phone}</td>
													<td style="background: #FFC022;">${u.policy_no}</td>
													<td style="background: #FFC022;">${DateTimeFormater.formateDate(u.exp_date)}</td>
													<td>${u.amount}</td>
													<td>${u.policy_type}</td>
											</tr>
									</c:forEach>		
									</c:when>
									<c:otherwise>
											<tr>
												<td colspan="8" style="text-align: center;">No Record Found.</td>
											</tr>
									</c:otherwise>
							</c:choose>
						</tbody>
					</table>
					
					</div>
												
					<div class="stripe-separator"><!--  --></div>
				</div> <!-- end content-module-main -->
			
			</div> <!-- end content-module -->
		
		</div> <!-- end full-width -->
			
	</div> <!-- end content -->
	
	<!-- FOOTER -->
		
		<jsp:include page="../footer/footer.jsp"></jsp:include>
	<!-- end footer -->
</body>
</html>