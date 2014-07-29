<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${username ==null}" >
	<% response.sendRedirect("adminpanel.html"); %>
</c:if>
<c:choose>
			<c:when test="${ errorMsg != null}">
				<div id="emsg" class="${errorClass}"><script type="text/javascript">showMessage('${errorMsg}');</script></div>	
			</c:when>
			<c:otherwise>
				<c:if test="${option =='viewUserDetails'}">
					<c:choose>
					<c:when test="${fn:length(userMap) > 0 }">
							<div align="center">
								   <table class="table_myform"> 
										<tbody>
											<tr>
												<td align="right">Username:&nbsp;</td>
												<td align="left">${userMap.username}</td>
											</tr>
											<tr>
												<td align="right">First Name:&nbsp;</td>
												<td align="left">${userMap.fname}</td>	
											</tr>
											<tr>
												<td align="right">Last Name:&nbsp;</td>
												<td align="left">${userMap.lname}</td>	
											</tr>
											<tr>
												<td align="right">Address:&nbsp; </td>
												<td align="left" width="150px;">${userMap.address}</td>	
											</tr>
											<tr>
												<td align="right">Phone No:&nbsp;</td>
												<td align="left">${userMap.phone}</td>	
											</tr>
											<tr>
												<td align="right">Date Of Birth (DOB):&nbsp;</td>
												<td align="left">${userMap.birthdate}</td>	
											</tr>
											<tr>
												<td align="right">Date Of Joining (DOJ):&nbsp;</td>
												<td align="left">${userMap.doj}</td>	
											</tr>
											<tr>
												<td align="right">Email :&nbsp; </td>
												<td align="left">${userMap.email}</td>	
											</tr>
											<tr>
												<td align="right">UserType :&nbsp; </td>
												<td align="left">${userMap.usertype}</td>
											</tr>
										</tbody>
										
									</table>
							</div>
							</c:when>
					</c:choose>			
				</c:if>
			</c:otherwise>
</c:choose>		

		