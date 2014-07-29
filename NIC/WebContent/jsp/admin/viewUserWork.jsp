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
	<jsp:include page="../menu/admin_menu.jsp"></jsp:include>
	<!-- end top-bar -->
	<jsp:include page="../menu/sliding_worlds.jsp"></jsp:include>
	<!-- HEADER -->
	<div id="header-with-tabs">
		
		<div class="page-full-width cf">
			<ul id="tabs" class="fl">
				<li><a href="javascript:void(0)" class="dashboard-tab" >View Work By User</a></li>
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
				
					<h3 class="fl">View Work By User</h3>
					<span class="fr expand-collapse-text">Click to collapse</span>
					<span class="fr expand-collapse-text initial-expand">Click to expand</span>
				
				</div> <!-- end content-module-heading -->
				
				<div class="content-module-main">
						<form onsubmit="return false;" id="frm_viewUserWork" name="frm_viewUserWork">
							<c:if test="${errorMsg !=null}" >
								<div  class="${errorClass}" style="width: 400px;font-size: 13px;text-align: left;"  >${errorMsg}</div>
							</c:if>
							<table class="table_myform">
									<tr>
										<td>Start Date  :</td>
										<td><input type="text" name="startDate" id="startDate"  /> </td>											
									</tr>
									<tr>
										<td>End Date  :</td>
										<td><input type="text" name="endDate" id="endDate"  /> </td>											
									</tr>
									<tr>
										<td>Select User <strong style="color:red;">*</strong>&nbsp; :</td>
										<td>
											<select name="user_id" id="user_id" style="width: 300px;" onchange="showWorkByUser(this.value);">
															<option value="0">Select User</option>
															<c:forEach var="u" items="${userList}">
																<option value="${u.id}">${u.username}</option>
															</c:forEach>
											</select>
										</td>
									</tr>
								
							</table>
								
							</form>	
								
							<br/>
							<div id="response"></div>
							
					<div class="stripe-separator"><!--  --></div>
				</div> <!-- end content-module-main -->
			
			</div> <!-- end content-module -->
		
		</div> <!-- end full-width -->
			
	</div> <!-- end content -->
	
	<!-- FOOTER -->
		
		<jsp:include page="../footer/footer.jsp"></jsp:include>
	<!-- end footer -->
		
<script type="text/javascript" src="developerjs/admin.js"></script>
<script type="text/javascript">
	$(function(){
		
		$("#startDate" ).datepicker();
		$("#startDate" ).datepicker("option", "dateFormat", 'yy-mm-dd');
	
		$("#endDate" ).datepicker();
		$("#endDate" ).datepicker("option", "dateFormat", 'yy-mm-dd');
		
	});
	</script>	
</body>
</html>