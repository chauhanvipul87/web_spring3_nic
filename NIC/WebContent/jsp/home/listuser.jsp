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
				<c:if test="${option =='listUser'}">
				<table class="grid">
						<thead>
							<tr>
								<th style="padding:5px;">
									<a style="color: #2a2e36;" class="button round add_greenLink image-right ic-add text-upper" href="javascript:void(0);" 
										onclick="openDialog_addNewUser();">Add&nbsp;</a></th>
								<th>Username</th>
								<th>First Name</th>
								<th>Last Name</th>
								<th>DOB</th>
								<th>DOJ</th>
								<th>Phone No</th>
								<th>Email</th>
								<th>User Type</th>
								<th>Actions</th>
							</tr>
						</thead>
						<tbody >
							<c:choose>
									<c:when test="${fn:length(listUser) > 0 }">
									<c:forEach var="u" items="${listUser}">    
											<tr>
													<td><input type="checkbox"></td>
													<td>${u.username}</td>
													<td>${u.fname}</td>
													<td>${u.lname}</td>
													<td>${u.birthdate}</td>
													<td>${u.doj}</td>
													<td>${u.phone}</td>
													<td>${u.email}</td>
													<td>${u.usertype}</td>
													<td>
														<a href="javascript:void(0);" title="View User" class="table-actions-button ic-table-view" onclick="viewUser('${u.id}')"></a>
														<a href="javascript:void(0);" title="Edit User" class="table-actions-button ic-table-edit" onclick="editUser('${u.id}')"></a>
														<a href="javascript:void(0);" title="Delete User" class="table-actions-button ic-table-delete" onclick="deleteUser('${u.id}')"></a>
													</td>
											</tr>
									</c:forEach>		
									</c:when>
									<c:otherwise>
											<tr>
												<td colspan="10" style="text-align: center;">No Record Found.Please add user.</td>
											</tr>
									</c:otherwise>
							
							</c:choose>
						</tbody>
						
					</table>
    		</c:if>
    		</c:otherwise>
 </c:choose>
    	
    