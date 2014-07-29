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
				<c:if test="${option =='viewCustomerDetail'}">
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
										<tr>
											<td colspan="4" align="center"><input type="submit" onclick="back2ListView();" value="&nbsp;Back&nbsp;" style="padding: 7px;" class="button round blue"></td>
										</tr>
								</tfoot>
							</table>
							</div>
						  </form>	
						</c:when>
						<c:otherwise>
						 <div align="center">
							<table class="table_viewform" style="width: 50%;"> 
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
