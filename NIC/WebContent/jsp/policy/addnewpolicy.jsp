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
				<li><a href="javascript:void(0);" class="active-tab dashboard-tab">Add Policy</a></li>
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

			<jsp:include page="../menu/left_panel.jsp"></jsp:include> <!-- end side-menu -->
			
			<div class="side-content fr">

				<div class="content-module">
					<div class="content-module-heading cf">
						<h3 class="fl">Add Policy</h3>
						<span class="fr expand-collapse-text">Click to collapse</span>
						<span class="fr expand-collapse-text initial-expand">Click to expand</span>
					</div> <!-- end content-module-heading -->
					
					<div class="content-module-main cf">
						<form action="addnewpolicy.html" method="post"  id="frm_addNewCustomerDetails" name="frm_addNewCustomerDetails">
						  <input type="hidden" name="action" id="action"  value="addnewpolicy" />
						  <div align="center">
							   <div  id="popup_addError" style="width:70%;font-size: 13px;text-align: left;"></div>
							   	<c:if test="${errorMsg !=null}" >
									<div  style="width:70%;font-size: 13px;text-align: left;" class="${errorClass}">${errorMsg}</div>
								</c:if>
								<table class="table_myform" style="width: 100%;"> 
									<tbody>
										<tr>
											<td align="right" class="table_labels">Policy Type : &nbsp;</td>
											<td align="left">
											<select name="policytype" id="policytype" style="width: 300px;" onchange="checkVehiclePolicy(this.value);">
															<option value="0">Select Policy Type</option>
															<option value="1">Mediclaim</option>
															<option value="2">Personal Accident</option>
															<option value="3">Fire</option>
															<option value="4">Vehicle</option>
												</select>
											</td>
										</tr>
										<tr>
											<td align="right" class="table_labels">Name: &nbsp;</td>
											<td align="left"><input type="text" name="name" id="name" style="width: 300px;" /></td>
											
										</tr>
										 <tr>
											<td align="right" class="table_labels">Email :&nbsp; </td>
											<td align="left"><input type="text" name="email" id="email" style="width: 300px;"/></td>
										</tr>
										<tr>
											<td align="right" class="table_labels">address:&nbsp;</td>
											<td align="left"><textarea name="address" id="address" rows="5" cols="34" style="resize: none;"></textarea></td>
										</tr>
										<tr>
											<td align="right" class="table_labels">City:&nbsp;</td>
											<td align="left"><input type="text" name="city" id="city" style="width: 300px;" /></td>
										</tr>
										<tr>
											<td align="right" class="table_labels">Phone No:&nbsp;</td>
											<td align="left"><input type="text" name="phone" id="phone" onkeypress="return onlyIntegerNumber(event);" style="width: 300px;"/></td>
										</tr>
										<tr>
											<td align="right" class="table_labels">Policy No:&nbsp;</td>
											<td align="left"><input type="text" name="policyno" id="policyno" style="width: 300px;" /></td>
										</tr>
										<tr id="vehicleopt" style="display: none;">
											<td align="right" class="table_labels">Vehicle No:&nbsp;</td>
											<td align="left"><input type="text" name="vehicleno" id="vehicleno" style="width: 300px;" /></td>
										</tr>
										<tr>
											<td align="right" class="table_labels">Expiry Date:&nbsp;</td>
											<td align="left"><input type="text" name="expdate" id="expdate"/></td>
										</tr>
										<tr>
											<td align="right" class="table_labels">Amount:&nbsp;</td>
											<td align="left"><input type="text" name="amount" id="amount" onkeypress="return isNumberKey(event)" /></td>
										</tr>
										<tr><td align="right">&nbsp;</td>
											<td align="left"><input type="submit" class="button round blue" onclick="return validateAddNewRequest();" value="Add" style="padding: 7px;">
											&nbsp;<input type="reset" class="button round blue" onclick="resetFormValue();" style="padding: 7px;" value="Reset"></td>
										</tr>
									</tbody>
							 	</table>
								</div>			
						</form>
									
					</div> <!-- end content-module-main -->
					
				</div> <!-- end content-module -->
		
			</div> <!-- end side-content -->
		
		</div> <!-- end full-width -->
			
	</div> <!-- end content -->
	
		<jsp:include page="../footer/footer.jsp"></jsp:include>
	<!-- end footer -->
<script type="text/javascript" src="developerjs/nic.js"></script>
<script type="text/javascript">
$(function(){
	
	$("#expdate" ).datepicker();
	$("#expdate" ).datepicker("option", "dateFormat", 'yy-mm-dd'); 
	
});


</script>	
</body>
</html>