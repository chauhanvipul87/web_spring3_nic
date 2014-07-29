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
				<li><a href="javascript:void(0);" class="active-tab dashboard-tab">Search & Renew Policy</a></li>
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
						<h3 class="fl">Search & Renew Policy</h3>
						<span class="fr expand-collapse-text">Click to collapse</span>
						<span class="fr expand-collapse-text initial-expand">Click to expand</span>
					</div> <!-- end content-module-heading -->
					<div class="content-module-main cf">
					 <div align="center">
						<form action="renew_index.html" method="post"  id="frm_searchPolicy" name="frm_searchPolicy">
								<input type="hidden" value="searchpolicy" name="action" id="action" />
						 		<c:if test="${errorMsg !=null}" >
									<div style="width:45%;text-align: left;" class="${errorClass}">${errorMsg}</div>
								</c:if>
								<table class="table_myform" style="width: 50%;"> 
									<tbody>
										<tr>
											<td align="right" class="table_labels">Policy No: &nbsp;</td>
											 <td align="left">
												<input type="text" name="filter_policy_no" id="filter_policy_no" value="${policy_no}" style="width: 200px;" />
											</td>
										</tr>
										<tr>
											<td align="right" class="table_labels"></td>
											<td align="left">
												<input type="submit" style="padding: 7px;" value="Search" class="button round blue">
											</td>
										</tr>
									</tbody>
							 	</table>
							</form>
							<br/>
							<div id="response"> </div>
							<c:if test="${option =='showResult' }">
							<c:choose>
									<c:when test="${fn:length(userDetailsList) > 0 }">
										<form onsubmit="return false;">
										<c:forEach var="u" items="${userDetailsList}"  begin="0" end="0" step="1">    
										   <div align="center">
											<table class="table_viewform" style="width: 50%;"> 
												<tbody>
													<tr>
														<td align="right" class="table_labels">Policy Type : &nbsp;</td>
														<td align="left">
															<c:if test="${u.policy_type =='1' }">Mediclaim </c:if>
															<c:if test="${u.policy_type =='2' }">Personal Accident </c:if>
															<c:if test="${u.policy_type =='3' }">Fire </c:if>
															<c:if test="${u.policy_type =='4' }">Vehicle </c:if>
														</td>
													</tr>
													<tr>
														<td align="right" class="table_labels">Name: &nbsp;</td>
														<td align="left">${u.name }</td>
													</tr>
													<tr>
														<td align="right" class="table_labels">address:&nbsp;</td>
														<td align="left">${u.address}</td>
													</tr>
													<tr>
														<td align="right" class="table_labels">City:&nbsp;</td>
														<td align="left">${u.city}</td>
													</tr>
													<tr>
														<td align="right" class="table_labels">Phone No:&nbsp;</td>
														 <td align="left">${u.phone}</td>
													</tr>
													<tr>
														<td align="right" class="table_labels">Email :&nbsp; </td>
														 <td align="left">${u.email}</td>
													</tr>
													<c:if test="${u.policy_type =='4' }">
														<tr>
															 <td align="right" class="table_labels">Vehicle No :&nbsp; </td>
															 <td align="left">${u.vehicle_no}</td>
														 </tr>
													</c:if>
												</tbody>
										 	</table>
										 </div>			
										</c:forEach>
										<div align="center">
										<br/>
										<table class="table_myform" style="width: 50%">
											<thead>
												<tr>
													<th>Policy No</th>
													<th>Expiry Date</th>
													<th>Amount</th>
													<th>Status</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="u" items="${userDetailsList}">
													<tr>
														<td>${u.policy_no}</td>
														<td>${DateTimeFormater.formateDate(u.exp_date)}</td>
														<td>${u.amount }</td>
														<td>${u.status}</td>			
													</tr>
												</c:forEach>
											</tbody>
											<tfoot>	
												<c:choose>
														<c:when test="${status=='renew'}">
														<tr>
															<td colspan="4" align="center" style="color: red;">Policy was already renew.Please search with latest policy no.</td>
														</tr>
														</c:when>
														<c:otherwise>
														<tr>
															<td colspan="4" align="center"><input type="submit" onclick="openDialogRenewPolicy();"  value="&nbsp;Renew Policy&nbsp;" style="padding: 7px;" class="button round blue"></td>
														</tr>
														</c:otherwise>
												
												</c:choose>
											
													
											</tfoot>
										</table>
										</div>
									  </form>	
									</c:when>
								</c:choose>	
							</c:if>
						</div>	
									
					</div> <!-- end content-module-main -->
					
				</div> <!-- end content-module -->
		
		</div> <!-- end full-width -->
			
	</div> <!-- end content -->
	
		<jsp:include page="../footer/footer.jsp"></jsp:include>
	<!-- end footer -->
	<!-- ui-dialog start -->
		<div id=dialog_renewpolicy title="Enter Details for Renew Policy : ${policy_no}">
		<form onsubmit="return false;" name="frm_renewpolicy" id="frm_renewpolicy">
			
			<input type="hidden" name="policy_type" value="${policy_type}"  />
			<input type="hidden" name="userdetails_id" value="${userdetails_id}"  />
			<input type="hidden" name="old_policy" value="${old_policy}"  />
			<input type="hidden" name="policy_no" value="${policy_no}"  />
			policy_no
			
			
			<div align="center">
				<div  id="pop_renewpolicyerror" style="width: 400px;font-size: 13px;text-align: left;"  ></div>
				<table class="table_myform" style="width: 100%"> 
						<tbody>
							<tr>
								<td align="right">Policy No :<strong style="color: red">*</strong>&nbsp;</td>
								<td align="left"><input type="text" name="renew_policyno" id="renew_policyno" /></td>
							</tr>
							<tr>
								<td align="right">Expiry Date:<strong style="color: red">*</strong>&nbsp;</td>
								<td align="left"><input type="text" name="renew_expirydate" id="renew_expirydate" /></td>
							</tr>
							<tr>
								<td align="right">Amount :<strong style="color: red">*</strong>&nbsp;</td>
								<td align="left"><input type="text" name="renew_amount" id="renew_amount" onkeypress="return isNumberKey(event)" /></td>
							</tr>
						</tbody>
						
					</table>
			</div>
			</form>
		</div>
<script type="text/javascript" src="developerjs/renewpolicy.js"></script>
<script type="text/javascript">
$(function(){
	//Dialog
	$('#dialog_renewpolicy').dialog({
		autoOpen: false,
		width: 600,
		buttons: {
			"Renew Policy": function() {
				validateRenewPolicyRequest();
			},
			"Cancel": function() {
				$(this).dialog("close");
			}
		}
	});
	
	
	$("#renew_expirydate" ).datepicker();
	$("#renew_expirydate" ).datepicker("option", "dateFormat", 'yy-mm-dd'); 
	
});


</script>	
</body>
</html>