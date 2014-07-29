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
				<li><a href="javascript:void(0)" onclick="showMemberList();" class="dashboard-tab" >Manage Staff Member</a></li>
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
				
					<h3 class="fl">List Members</h3>
					<span class="fr expand-collapse-text">Click to collapse</span>
					<span class="fr expand-collapse-text initial-expand">Click to expand</span>
				
				</div> <!-- end content-module-heading -->
				
				<div class="content-module-main" id="response">
				
					<div class="stripe-separator"><!--  --></div>
				</div> <!-- end content-module-main -->
			
			</div> <!-- end content-module -->
		
		</div> <!-- end full-width -->
			
	</div> <!-- end content -->
	
	<!-- FOOTER -->
		
		<jsp:include page="../footer/footer.jsp"></jsp:include>
	
	<!-- end footer -->
<!-- ui-dialog start -->
		<div id=dialog_addnewuser title="Add New User">
		<form onsubmit="return false;" name="frm_newuser" id="frm_newuser">
			<div align="center">
				<div  id="pop_error" style="width: 400px;font-size: 13px;text-align: left;"  ></div>
				<table class="table_myform"> 
						<tbody>
							<tr>
								<td align="right">Username:<strong style="color: red">*</strong>&nbsp;</td>
								<td align="left"><input type="text" name="username" id="username" /></td>
							</tr>
							<tr>
								<td align="right">Password:<strong style="color: red">*</strong>&nbsp;</td>
								<td align="left"><input type="password" name="password" id="password" /></td>
							</tr>
							<tr>
								<td align="right">Confirm Password:<strong style="color: red">*</strong>&nbsp;</td>
								<td align="left"><input type="password" name="cpassword" id="cpassword" /></td>
							</tr>
							<tr>
								<td align="right">First Name:<strong style="color: red">*</strong>&nbsp;</td>
								<td align="left"><input type="text" name="fname" id="fname" /></td>	
							</tr>
							<tr>
								<td align="right">Last Name:<strong style="color: red">*</strong>&nbsp;</td>
								<td align="left"><input type="text" name="lname" id="lname" /></td>	
							</tr>
							<tr>
								<td align="right">Address:<strong style="color: red">*</strong>&nbsp; </td>
								<td align="left"><textarea name="address" id="address" rows="3" cols="16" style="resize: none;"></textarea></td>	
							</tr>
							<tr>
								<td align="right">Phone No:<strong style="color: red">*</strong>&nbsp;</td>
								<td align="left"><input type="text" name="phone" id="phone" onkeypress="return onlyIntegerNumber(event)" /></td>	
							</tr>
							<tr>
								<td align="right">Date Of Birth (DOB):&nbsp;</td>
								<td align="left"><input type="text" name="dob" id="dob" /></td>	
							</tr>
							<tr>
								<td align="right">Date Of Joining (DOJ):&nbsp;</td>
								<td align="left"><input type="text" name="doj" id="doj" /></td>	
							</tr>
							<tr>
								<td align="right">Email :&nbsp; </td>
								<td align="left"><input type="text" name="email" id="email" /></td>	
							</tr>
							<tr>
								<td align="right">UserType :<strong style="color: red">*</strong>&nbsp; </td>
								<td align="left">
										<select id="dropdown-actions" name="usertype">
											<option value="0">Select User Type</option>
											<option value="1">Admin User</option>
											<option value="2">Staff User</option>
										</select>
							</tr>
							
						</tbody>
						
					</table>
			</div>
			</form>
		</div>
		
		<div id=dialog_edituser title="Edit User Details"> </div>
		<div id=dialog_viewuser title="View User Details"> </div>
		
<!-- ui-dialog end-->			
<script type="text/javascript" src="developerjs/admin.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	showMemberList();
});
$(function(){
	// Dialog
	$('#dialog_addnewuser').dialog({
		autoOpen: false,
		width: 600,
		buttons: {
			"Add": function() {
				validateRequest();
			},
			"Cancel": function() {
				$(this).dialog("close");
			}
		}
	});
	
	$('#dialog_edituser').dialog({
		autoOpen: false,
		width: 600,
		buttons: {
			"Update": function() {
				editUserRequest();
			},
			"Cancel": function() {
				$(this).dialog("close");
			}
		}
	});
	
	$('#dialog_viewuser').dialog({
		autoOpen: false,
		width: 600,
		buttons: {
			"OK": function() {
				$(this).dialog("close");
			},
			"Cancel": function() {
				$(this).dialog("close");
			}
		}
	});
	
	
	$("#dob" ).datepicker();
	$("#dob" ).datepicker("option", "dateFormat", 'yy-mm-dd');

	$("#doj" ).datepicker();
	$("#doj" ).datepicker("option", "dateFormat", 'yy-mm-dd');
	
});


</script>	

</body>
</html>