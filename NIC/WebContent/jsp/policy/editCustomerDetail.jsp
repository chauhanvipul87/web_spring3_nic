<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${username ==null}" >
	<% response.sendRedirect("index.html"); %>
</c:if>
<c:choose>
			<c:when test="${ errorMsg != null}">
				<div id="emsg" class="${errorClass}"><script type="text/javascript">showMessage('${errorMsg}');</script></div>	
			</c:when>
			<c:otherwise>
				<c:if test="${option =='editCustomerDetail'}">
				<c:choose>
						<c:when test="${fn:length(editUserDetailList) > 0 }">
							<form onsubmit="return false;" id="frm_editCustomerDetails" name="frm_editCustomerDetails">
							<c:forEach var="u" items="${editUserDetailList}">    
							   <div align="center">
							   <div  id="popup_editError" style="width: 400px;font-size: 13px;text-align: left;"></div>
							    <input type="hidden" name="edit_userdetails_id" value="${u.userdetails_id}" id="edit_userdetails_id"/>
								<table class="table_myform" style="width: 100%;"> 
									<tbody>
										<tr>
											<td align="right" class="table_labels">Policy Type : <strong style="color: red">*</strong>&nbsp;</td>
											<td align="left">
											<select id="edit_policytype" name="edit_policytype" style="width: 300px;" onchange="showVehicleNo(this.value,'${u.policy_type}','${u.vehicle_no}');">
												
												<c:forEach var="pl" items="${policyList}" >
													<c:choose>
														<c:when test="${ pl.id == u.policy_type}">
															<option selected="selected" value="${pl.id}">${pl.policy_type}</option>
															<option value="0">Select Policy Type</option>
														</c:when>
														<c:otherwise>
															<option value="${pl.id}">${pl.policy_type}</option>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</select>
											</td>
										</tr>
										<tr>
											<td align="right" class="table_labels">Name: <strong style="color: red">*</strong>&nbsp;</td>
											<td align="left"><input type="text" name="edit_name" id="edit_name" value="${u.name}" style="width: 300px;" /></td>
											
										</tr>
										 <tr>
											<td align="right" class="table_labels">Email :&nbsp; </td>
											<td align="left"><input type="text" name="edit_email" id="edit_email" value="${u.email}" style="width: 300px;"/></td>
										</tr>
										<tr>
											<td align="right" class="table_labels">address:&nbsp;</td>
											<td align="left"><textarea name="edit_address" id="edit_address" rows="3" cols="16" style="resize: none;">${u.address}</textarea></td>
										</tr>
										<tr>
											<td align="right" class="table_labels">City:&nbsp;</td>
											<td align="left"><input type="text" name="edit_city" id="edit_city" value="${u.city}" /></td>
										</tr>
										<tr>
											<td align="right" class="table_labels">Phone No:&nbsp;</td>
											<td align="left"><input type="text" name="edit_phone" id="edit_phone" onkeypress="return onlyIntegerNumber(event)" value="${u.phone}"/></td>
										</tr>
										<tr>
											<td align="right" class="table_labels">Policy No:&nbsp;</td>
											<td align="left"><input type="text" name="edit_policyno" id="edit_policyno" value="${u.policy_no}" /></td>
										</tr>
										<tr>
											<td align="right" class="table_labels">Expiry Date:&nbsp;</td>
											<td align="left"><input type="text" name="edit_expdate" id="edit_expdate" value="${u.exp_date}"/></td>
										</tr>
										<tr>
											<td align="right" class="table_labels">Amount:&nbsp;</td>
											<td align="left"><input type="text" name="edit_amount" id="edit_amount" value="${u.amount}" onkeypress="return isNumberKey(event)"/></td>
										</tr>
										<c:choose>
											<c:when test="${ u.policy_type ==4}">
												<tr id="edit_vehicleopt_already">
													<td align="right" class="table_labels">Vehicle No:&nbsp;</td>
													<td align="left"><input type="text" name="edit_vehicleno" id="edit_vehicleno" value="${u.vehicle_no}" /></td>
												</tr>
											</c:when>
											<c:otherwise>
												<tr id="edit_vehicleopt_already"></tr>												
											</c:otherwise>
										</c:choose>

									</tbody>
							 	</table>
							 </div>			
							</c:forEach>
							</form>
							<script type="text/javascript">
								$(function(){
									$("#edit_expdate" ).datepicker();
									$("#edit_expdate" ).datepicker("option", "dateFormat", 'yy-mm-dd');
								});
								
								</script>	
					</c:when>
					<c:otherwise>
					 	<div align="center">
							<table class="table_viewform" style="width: 100%;"> 
									<tbody>
										<tr><tD style="color: red;">Error Occurred while fetching Customer Details. </tD>
										</tr>
									</tbody>
							</table>
						</div>
					</c:otherwise>
				</c:choose>
			</c:if>
		</c:otherwise>
</c:choose>
							