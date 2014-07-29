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
				<li><a href="javascript:void(0)" onclick="showCustomerList();" class="dashboard-tab" >List Policies</a></li>
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
				
					<h3 class="fl">List Policies</h3>
					<span class="fr expand-collapse-text">Click to collapse</span>
					<span class="fr expand-collapse-text initial-expand">Click to expand</span>
				
				</div> <!-- end content-module-heading -->
				
				<div class="content-module-main">
					<div id="filter_div" class="filter_div">
					<form onsubmit="return false;" name="frm_filterDetails" id="frm_filterDetails">
						<table class="table_myform" style="width: 100%;">
							<tbody>
							<tr>
								<td colspan="3"><u>Enter Search Criteria :-</u></td>
							</tr>
							<tr>
								<td width="70px;" align="right">Name : </td><td><input type="text" name="filter_name" id="filter_name" style="width: 200px;" /></td>
								<td width="80px;" align="right">Address : </td><td><input type="text" name="filter_address" id="filter_address" style="width: 200px;" /></td>
								<td width="60px;" align="right">City : </td><td><input type="text" name="filter_city" id="filter_city" style="width: 200px;" /></td>
							</tr>
							<tr>
								<td align="right">Phone No :</td><td><input type="text" name="filter_phone" id="filter_phone" style="width: 200px;" onkeypress="return onlyIntegerNumber(event)"/></td>
								<td align="right">Email :</td><td><input type="text" name="filter_email" id="filter_email" style="width: 200px;" /></td>
								<td align="right">Status : </td><td><select id="filter_status" name="filter_status" style="width: 210px;">
													<option value="0">Select Status</option>
													<option value="pending" >Pending</option>
													<option value="notentered">Not Entered</option> </select></td>
							</tr>
							<tr>
								<td align="right">Policy No : </td><td><input type="text" name="filter_policyno" id="filter_policyno" style="width: 200px;" /></td>
								<td align="right">Expiry Date :</td><td><input type="text" name="filter_expdate" id="filter_expdate" style="width: 200px;" /></td>
								<td align="right">Amount : </td><td><input type="text" name="filter_amount" id="filter_amount" style="width: 200px;" onkeypress="return isNumberKey(event)" /></td>
							</tr>
							<tr>
								<td align="right">Vehicle No: </td><td><input type="text" name="filter_vehicleno" id="filter_vehicleno" style="width: 200px;" /></td>
								<td align="right">Policy Type :</td><td> 
									<select id="filter_policytype" name="filter_policytype" style="width: 210px;">
											<option value="0">Select Policy Type</option>
											<c:forEach var="u" items="${policyList}" >
												<option value="${u.id}">${u.policy_type}</option>
											</c:forEach>
									</select>
								</td>
								<td align="right">&nbsp;</td><td>&nbsp; </td>
							</tr>
							<tr>
								<td colspan="6" align="center">
									<input type="submit" class="button round blue" value="Search" style="padding: 7px;" onclick="searchResult();"> &nbsp;<input type="reset" class="button round blue" style="padding: 7px;" value="Reset">
								</td>
							</tr>
						</table>
						</form>
					</div>
					<br/>
					<div id="response"></div>
					<div id="viewCustomerDetail"></div>
					
					<div class="stripe-separator"><!--  --></div>
				</div> <!-- end content-module-main -->
			
			</div> <!-- end content-module -->
		
		</div> <!-- end full-width -->
			
	</div> <!-- end content -->
	
	<!-- FOOTER -->
		
		<jsp:include page="../footer/footer.jsp"></jsp:include>
	
	<!-- end footer -->
<!-- ui-dialog start -->
		<div id=dialog_editCustomer title="Edit Customer Details"> </div>
<!-- ui-dialog end-->			
<script type="text/javascript" src="developerjs/nic.js"></script>
<script type="text/javascript">

$(document).ready(function() {
	showCustomerList();
});
$(function(){
	// Dialog

	$('#dialog_editCustomer').dialog({
		autoOpen: false,
		width: 600,
		buttons: {
			"Update": function() {
				editCustomerRequest();
			},
			"Cancel": function() {
				$(this).dialog("close");
			}
		}
	});
	
	$("#filter_expdate" ).datepicker();
	$("#filter_expdate" ).datepicker("option", "dateFormat", 'yy-mm-dd');

});
</script>	

</body>
</html>