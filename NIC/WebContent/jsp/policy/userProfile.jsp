<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${username ==null}" >
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
				<li><a href="javascript:void(0);" class="dashboard-tab" >User Details</a></li>
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
				
					<h3 class="fl">View User Details </h3>
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
						<c:choose>
							<c:when test="${fn:length(userMap) > 0 }">
										<div align="center">
											   <table class="table_myform" style="width: 100%;"> 
													<tbody>
														<tr>
															<td align="left" width="150px;">Username:&nbsp;</td>
															<td align="left">${userMap.username}</td>
														</tr>
														<tr>
															<td align="left">First Name:&nbsp;</td>
															<td align="left">${userMap.fname}</td>	
														</tr>
														<tr>
															<td align="left">Last Name:&nbsp;</td>
															<td align="left">${userMap.lname}</td>	
														</tr>
														<tr>
															<td align="left">Address:&nbsp; </td>
															<td align="left">${userMap.address}</td>	
														</tr>
														<tr>
															<td align="left">Phone No:&nbsp;</td>
															<td align="left">${userMap.phone}</td>	
														</tr>
														<tr>
															<td align="left">Date Of Birth (DOB):&nbsp;</td>
															<td align="left">${userMap.birthdate}</td>	
														</tr>
														<tr>
															<td align="left">Date Of Joining (DOJ):&nbsp;</td>
															<td align="left">${userMap.doj}</td>	
														</tr>
														<tr>
															<td align="left">Email :&nbsp; </td>
															<td align="left">${userMap.email}</td>	
														</tr>
														<tr>
															<td align="left">UserType :&nbsp; </td>
															<td align="left">${userMap.usertype}</td>
														</tr>
										</tbody>
										
									</table>
							</div>
												</c:when>
										</c:choose>			
								</c:otherwise>
					</c:choose>		
					    			
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