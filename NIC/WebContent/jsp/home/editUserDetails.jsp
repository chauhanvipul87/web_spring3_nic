<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${username == null}" >
	<% response.sendRedirect("adminpanel.html"); %>
</c:if>
<c:choose>
			<c:when test="${ errorMsg != null}">
				<div id="emsg" class="${errorClass}"><script type="text/javascript">showMessage('${errorMsg}');</script></div>	
			</c:when>
			<c:otherwise>
				<c:if test="${option =='EditUserDetails'}">
					<c:choose>
					<c:when test="${fn:length(userMap) > 0 }">
						
						<form onsubmit="return false;" name="frm_editUserDetails" id="frm_editUserDetails">
							<div align="center">
								<div  id="pop_edit_error" style="width: 400px;font-size: 13px;text-align: left;"  ></div>
								<input type="hidden" name="edit_userid" id="edit_userid" value="${userMap.id}" />
								   <table class="table_myform"> 
										<tbody>
											<tr>
												<td align="right">Username:<strong style="color: red">*</strong>&nbsp;</td>
												<td align="left"><input type="text" name="edit_username" id="edit_username" value="${userMap.username}" /></td>
											</tr>
											<tr>
												<td align="right">Password:&nbsp;</td>
												<td align="left"><input type="password" name="edit_password" id="edit_password" /></td>
											</tr>
											<tr>
												<td align="right">Confirm Password:&nbsp;</td>
												<td align="left"><input type="password" name="edit_cpassword" id="edit_cpassword" /></td>
											</tr>
											<tr>
												<td align="right">First Name:<strong style="color: red">*</strong>&nbsp;</td>
												<td align="left"><input type="text" name="edit_fname" id="edit_fname" value="${userMap.fname}" /></td>	
											</tr>
											<tr>
												<td align="right">Last Name:<strong style="color: red">*</strong>&nbsp;</td>
												<td align="left"><input type="text" name="edit_lname" id="edit_lname"  value="${userMap.lname}"/></td>	
											</tr>
											<tr>
												<td align="right">Address:<strong style="color: red">*</strong>&nbsp; </td>
												<td align="left"><textarea name="edit_address" id="edit_address" rows="3" cols="16" style="resize: none;">${userMap.address}</textarea></td>	
											</tr>
											<tr>
												<td align="right">Phone No:<strong style="color: red">*</strong>&nbsp;</td>
												<td align="left"><input type="text" name="edit_phone" id="edit_phone" onkeypress="return onlyIntegerNumber(event)" value="${userMap.phone}" /></td>	
											</tr>
											<tr>
												<td align="right">Date Of Birth (DOB):&nbsp;</td>
												<td align="left"><input type="text" name="edit_dob" id="edit_dob" value="${userMap.birthdate}" /></td>	
											</tr>
											<tr>
												<td align="right">Date Of Joining (DOJ):&nbsp;</td>
												<td align="left"><input type="text" name="edit_doj" id="edit_doj"  value="${userMap.doj}" /></td>	
											</tr>
											<tr>
												<td align="right">Email :<strong style="color: red">*</strong>&nbsp; </td>
												<td align="left"><input type="text" name="edit_email" id="edit_email" value="${userMap.email}"  /></td>	
											</tr>
											<tr>
												<td align="right">UserType :<strong style="color: red">*</strong>&nbsp; </td>
												<td align="left">
														<select id="edit_dropdown-actions" name="edit_usertype">
																
															<c:choose>
																	<c:when test="${userMap.usertype_id ==1 }">
																		<option value="1" selected="selected">Admin User</option>
																		<option value="0">Select User Type</option>
																		<option value="2">Staff User</option>
																	</c:when>
																	<c:otherwise>
																		<option value="2" selected="selected">Staff User</option>
																		<option value="0">Select User Type</option>
																		<option value="1">Admin User</option>
																		
																	</c:otherwise>	
															</c:choose>
														</select>
											</tr>
										</tbody>
										
									</table>
							</div>
							</form>
							<script type="text/javascript">
									$(function(){
										
										$("#edit_dob" ).datepicker();
										$("#edit_dob" ).datepicker("option", "dateFormat", 'yy-mm-dd');
									
										$("#edit_doj" ).datepicker();
										$("#edit_doj" ).datepicker("option", "dateFormat", 'yy-mm-dd');
										
									});
							</script>	
															
					</c:when>
					</c:choose>			
				</c:if>
			</c:otherwise>
</c:choose>		

		