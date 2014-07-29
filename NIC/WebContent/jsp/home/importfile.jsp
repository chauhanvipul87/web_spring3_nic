<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${username ==null}" >
	<% response.sendRedirect("adminpanel.html"); %>
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
	
	<!-- HEADER -->
	<div id="header-with-tabs">
		
	<div class="page-full-width cf">
			<ul id="tabs" class="fl">
				<li><a href="javascript:void(0);" class="active-tab dashboard-tab">Import File</a></li>
			</ul> <!-- end tabs -->
			
			<!-- Change this image to your own company's logo -->
			<!-- The logo will automatically be resized to 30px height. -->
			<a href="javascript:void(0);" id="company-branding-small" class="fr"><img src="images/company-logo.png" alt="Blue Hosting" /></a>
			
		</div> <!-- end full-width -->	

	</div> <!-- end header -->
	
	<!-- MAIN CONTENT -->
	<div id="content">
		
		<div class="page-full-width cf">

			<jsp:include page="../menu/left_panel.jsp"></jsp:include> <!-- end side-menu -->
			
			<div class="side-content fr">

				<div class="content-module">
					<div class="content-module-heading cf">
						<h3 class="fl">Select File to Import</h3>
						<span class="fr expand-collapse-text">Click to collapse</span>
						<span class="fr expand-collapse-text initial-expand">Click to expand</span>
					</div> <!-- end content-module-heading -->
					
					<div class="content-module-main cf">
						<div class="half-size-column fl">
							<form action="file_entered.html" method="post" enctype="multipart/form-data">
							<c:if test="${errorMsg !=null}" >
								<div  class="${errorClass}" style="width: 400px;font-size: 13px;text-align: left;"  >${errorMsg}</div>
							</c:if>
							<table class="table_myform">
									<tr>
										<td>Policy Type <strong style="color:red;">*</strong>&nbsp; :</td>
										<td>
											<select name="policytype" id="policytype" style="width: 300px;" onchange="checkVehiclePolicy(this.value);">
															<option value="0">Select Policy Type</option>
															<c:forEach var="policy" items="${policytype}">
																<option value="${policy.id}">${policy.policy_type}</option>
															</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td>Select File <strong style="color:red;">*</strong>&nbsp; :</td>
										<td><input type="file" name="file" id="file" style="padding: 4px;" />
										</td>
									</tr>
									 <tr>
								      <td>&nbsp;</td><td align="left"><input type="submit" value="Submit" class="button round blue" /></td>
								    </tr>
							</table>
								
							</form>
						
						</div> <!-- end half-size-column -->

					</div> <!-- end content-module-main -->
					
				</div> <!-- end content-module -->

			</div> <!-- end side-content -->
		
		</div> <!-- end full-width -->
			
	</div> <!-- end content -->
	
		<jsp:include page="../footer/footer.jsp"></jsp:include>
	<!-- end footer -->

</body>
</html>