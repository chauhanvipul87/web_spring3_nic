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
			 <c:if test="${option =='listCustomers'}">
			 <form name="gridform" id="gridform" method="post" onsubmit="return false;">
			 	<input id="ids" name="ids" type="hidden" /> 
				<input id="export_type" name="export_type" type="hidden"/>
				<input type="hidden" name="cpage" id="cpage" value="${currentPage}"/>
				<table class="grid">
						<thead>
							<tr>
								<th style="padding:5px;">
									<!-- <a style="color: #2a2e36;" class="button round add_greenLink image-right ic-add text-upper" href="javascript:void(0);" 
										onclick="openDialog_addNewCustomer();">Add&nbsp;</a> -->
										<input type="checkbox" onclick="togglecheckall();" id="checkall" name="checkall">
										</th>
								<th>Name</th>
								<th>Address</th>
								<th>City</th>
								<th>Phone</th>
								<th>Policy No</th>
								<th>Expiry Date</th>
								<th>Amount</th>
								<th>Policy Type </th>
								<th>Status</th>
								<th>Actions</th>
							</tr>
						</thead>
						<tbody >
							<c:choose>
									<c:when test="${fn:length(dataList) > 0 }">
									<c:forEach var="u" items="${dataList}">    
											<tr>
													<td><input type="checkbox" onclick="checkallstatus();" value="${u.userdetails_id}" id="chkbox" name="chkbox"></td>
													<td>${u.name}</td>
													<td>${u.address}</td>
													<td>${u.city}</td>
													<td>${u.phone}</td>
													<%-- <td style="background: #FDE8E4">${u.policy_no}</td> --%>
													<td style="background: #FFC022" align="center">
														<c:choose>
															<c:when test="${u.policy_no == null }">
																	-
															</c:when>
															<c:otherwise>
																${u.policy_no}
															</c:otherwise>
														</c:choose>
													</td>
													<td style="background: #FFC022" align="center">
													<c:choose>
															<c:when test="${u.exp_date == null }">
																	-
															</c:when>
															<c:otherwise>
																${DateTimeFormater.formateDate(u.exp_date)}
															</c:otherwise>
														</c:choose>
													</td>
													<td align="center">
														<c:choose>
															<c:when test="${u.amount == null }">
																	-
															</c:when>
															<c:otherwise>
																${u.amount}
															</c:otherwise>
														</c:choose>
													</td>
													<td>
														<c:if test="${u.policy_type =='1' }">Mediclaim </c:if>
														<c:if test="${u.policy_type =='2' }">Personal Accident </c:if>
														<c:if test="${u.policy_type =='3' }">Fire </c:if>
														<c:if test="${u.policy_type =='4' }">Vehicle </c:if>
													</td>
													<td align="center">
														<c:choose>
															<c:when test="${u.status == null }">
																Not Entered
															</c:when>
															<c:otherwise>
																${u.status}
															</c:otherwise>
														</c:choose>
													</td>
													<td>
														<a href="javascript:void(0);" title="View Customer Details" class="table-actions-button ic-table-view" onclick="viewCustomerDetail('${u.userdetails_id}')"></a>
														<c:if test="${u.status !='renew'}">
															<a href="javascript:void(0);" title="Edit Customer Details" class="table-actions-button ic-table-edit" onclick="editCustomerDetail('${u.userdetails_id}','${u.policy_no}')"></a>
															<a href="javascript:void(0);" title="Delete Customer Details" class="table-actions-button ic-table-delete" onclick="deleteCustomerDetail('${u.userdetails_id}')"></a>
														</c:if>
													</td>
											</tr>
									</c:forEach>		
									</c:when>
									<c:otherwise>
											<tr>
												<td colspan="11" style="text-align: center;color: red;">No Record Found.</td>
											</tr>
									</c:otherwise>
							
							</c:choose>
						</tbody>
						<c:choose>
			           <c:when test="${fn:length(dataList) > 0 }">
						<tfoot>
							<tr>
								<td colspan="11">
									<table style="width: 100%" class="table_myform">
										<tr>
										<td style="width:22%">
										
											 <label for="table-select-actions">With selected:</label>
		
											<select id="table-select-actions">
												<option value="export">Export</option>
											</select>
											
											<a href="javascript:void(0);" onclick="selectRequestType();" class="round button blue text-upper small-button">Apply to selected</a>	
		
										</td>
										<td align="right"  style="width:30%">
										Displaying ${initno} to ${uptono} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												 Page <select id="page_option" onchange="pagination(this.value,'${url}','${responseLayer}')">
															<c:forEach var="i" begin="1" end="${totalPage}" step="1" varStatus="status">
																<c:choose>
																	<c:when test="${currentPage == i}">
																		<option value="${i}" selected="selected">${i}</option>
																	</c:when>
																	<c:otherwise>
																		<option value="${i}">${i}</option>
																	</c:otherwise>
																</c:choose>
															</c:forEach>
										 	</select>
										</td>
										
										 <td align="left">&nbsp;
										 <c:choose>
										 		<c:when test="${currentPage ==1 }">
										 			&nbsp;<a href="javascript:void(0);" title="Go To First Record"><img src="images/paging_first.png" alt="Go To First Record"  /></a>
										 			&nbsp;<a href="javascript:void(0);" title="Go To Previous Record"><img src="images/paging_prev.png" alt="Go To Previous Record"  /></a>
										 		</c:when>
										 		<c:otherwise>
										 			&nbsp;<a href="javascript:void(0);" onclick="pagination('1','${url}','${responseLayer}')" title="Go To First Record"><img src="images/paging_first.png" alt="Go To First Record"  /></a>
										 			&nbsp;<a href="javascript:void(0);" onclick="pagination('${currentPage-1}','${url}','${responseLayer}')" title="Go To Previous Record"><img src="images/paging_prev.png" alt="Go To Previous Record"  /></a>
										 		</c:otherwise>
										 
										 </c:choose>
										 <c:choose>
										 		<c:when test="${currentPage == totalPage }">
										 			&nbsp;<a href="javascript:void(0);" title="Go To Next Record"><img src="images/paging_next.png" alt="Go To Next Record"  /></a>
										 			&nbsp;<a href="javascript:void(0);" title="Go To Last Record"><img src="images/paging_last.png" alt="Go To Last Record"  /></a>
										 		</c:when>
										 		<c:otherwise>
										 			&nbsp;<a href="javascript:void(0);" onclick="pagination('${currentPage+1}','${url}','${responseLayer}')" title="Go To Next Record"><img src="images/paging_next.png" alt="Go To Next Record"  /></a>
										 			&nbsp;<a href="javascript:void(0);" onclick="pagination('${totalPage}','${url}','${responseLayer}')" title="Go To Last Record"><img src="images/paging_last.png" alt="Go To Last Record"  /></a>
										 		</c:otherwise>
										 </c:choose>
										</td>
										<td align="right" style="width:15%"> 
										<div align="right">
												Record Per Page <select id="record_option" onchange="pagination('${currentPage}','${url}','${responseLayer}')">
													<c:choose>
														<c:when test="${record == 5}">
															<option value="5" selected="selected">5</option>
														</c:when>
														<c:otherwise>
															<option value="5">5</option>
														</c:otherwise>
													</c:choose>
													<c:choose>
														<c:when test="${record == 10}">
															<option value="10" selected="selected">10</option>
														</c:when>
														<c:otherwise>
															<option value="10">10</option>
														</c:otherwise>
													</c:choose>
													<c:choose>
														<c:when test="${record == 15}">
															<option value="15" selected="selected">15</option>
														</c:when>
														<c:otherwise>
															<option value="15">15</option>
														</c:otherwise>
													</c:choose>
													<c:choose>
														<c:when test="${record == 20}">
															<option value="20" selected="selected">20</option>
														</c:when>
														<c:otherwise>
															<option value="20">20</option>
														</c:otherwise>
													</c:choose>
													<c:choose>
														<c:when test="${record == 30}">
															<option value="30" selected="selected">30</option>
														</c:when>
														<c:otherwise>
															<option value="30">30</option>
														</c:otherwise>
													</c:choose>
													<c:choose>
														<c:when test="${record == 40}">
															<option value="40" selected="selected">40</option>
														</c:when>
														<c:otherwise>
															<option value="40">40</option>
														</c:otherwise>
													</c:choose>
													<c:choose>
														<c:when test="${record == 50}">
															<option value="50" selected="selected">50</option>
														</c:when>
														<c:otherwise>
															<option value="50">50</option>
														</c:otherwise>
													</c:choose>
											</select>
											</div>
											</td>
										</tr>
										</table>	
									</td>		
								</tr>
							  </tfoot>
							</c:when>
						</c:choose>
						</table>
					</form>	
    		</c:if>
    		</c:otherwise>
 </c:choose>
    	
    