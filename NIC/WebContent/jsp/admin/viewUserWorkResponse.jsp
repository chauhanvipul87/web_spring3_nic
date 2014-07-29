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
<table class="table_myform" style="width: 100%;">
	<thead>
		<tr>
			<th style="padding: 7px;">Name</th>
			<th>Address</th>
			<th>City</th>
			<th>Phone</th>
			<th>Policy No</th>
			<th>Expiry Date</th>
			<th>Amount</th>
			<th>Policy Type </th>
			<th>Entered Date Time</th>
		</tr>
	</thead>
	<tbody >
		<c:choose>
				<c:when test="${fn:length(listUserWorked) > 0 }">
				<c:forEach var="u" items="${listUserWorked}">    
						<tr>
								<td>${u.name}</td>
								<td>${u.address}</td>
								<td>${u.city}</td>
								<td>${u.phone}</td>
								<td style="background: #FFC022;">${u.policy_no}</td>
								<td style="background: #FFC022;">${u.exp_date}</td>
								<td>${u.amount}</td>
								<td>${u.policy_type}</td>
								<td>${u.modified_datetime}</td>
						</tr>
				</c:forEach>		
				</c:when>
				<c:otherwise>
						<tr>
							<td colspan="9" style="text-align: center;">No Record Found.</td>
						</tr>
				</c:otherwise>
		</c:choose>
	</tbody>
</table>
		<c:choose>
		<c:when test="${fn:length(listUserWorked) > 0 }">
				<br/>
				<table class="table_myform">
				<tr>
							<th>Total Entries :</th> <th>${totalEntry}</th>
				</tr>			
				</table>
		</c:when>
	</c:choose>

</c:otherwise>
</c:choose>		

		