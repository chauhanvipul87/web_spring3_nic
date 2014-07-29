function validateAddNewRequest(){
	
	var policytype  = $("#policytype").val();
	var name        = $("#name").val();
	var email 	    = $("#email").val();
	
	var msg ="";
	if(policytype =="0"){
		msg +="Please Select Policy Type.<br/>";
	}
	if(name ==''){
		msg ="Please Enter Name <br/>";
	}
	if(email !=''){
		if(!IsEmail(email)){
		msg +="Please Enter Valid Email Address.<br/>";
		}
	}
	
	if(msg!=""){
		showPopupError("error-box round", msg,"popup_addError");
		return false;
	}else{
		return true;
	}
}

function selectRequestType(){
	var actionOpt = $("#table-select-actions").val();
	if(actionOpt =='export'){
		exportallselected("export.html");
	}
} 

function exportallselected(action){
	//alert('in exportALL');
	    var val = [];
		var checkedval="";
		$('input[name=chkbox]').each(function(i)
		{
					val[i] = $(this).val();
					if(this.checked == true)
					{
							checkedval= checkedval  + "," + $(this).val();
					}
		});
		if(checkedval!="")
		{ 
			document.getElementById("ids").value=checkedval;
			document.getElementById("export_type").value='csv';
			document.gridform.action = action;	
			document.gridform.submit();
		}
		else
		{	document.getElementById("ids").value="";
		    document.getElementById("export_type").value="";
			alert("Please select at least 1 record to proceed.");
			return false;
		} 
}

function checkallstatus(){
	 document.gridform.checkall.checked=false;
}
function togglecheckall()
{
	var field=document.gridform.chkbox;
	var chkall = document.gridform.checkall.checked;
	var len = field.length;
	if(len == undefined){
		if(chkall){
			field.checked = true;
		}else{
			field.checked = false;
		}
	}else{
		if(chkall){
			for(var i=0;i<len;i++){
				field[i].checked = true;
			}
		}else{
			for(var i=0;i<len;i++){
				field[i].checked = false;
			}
		}
	}
}

function deleteCustomerDetail(userdetails_id){
	if(confirm("Are you sure want to delete this policy ?")){
		showProgressBar();
		$.get("deleteCustomerDetails.html", { userdetails_id:userdetails_id },
				function(data){
					if(data.search("showMessage")>-1){
						humanMsg.displayMsg(data);
						hideProgressBar();
						showCustomerList();
						return false;
					}
				 });
	}
	return false;
}
function checkVehiclePolicy(value){
	if(value ==4){
		$("#vehicleopt").show();
	}else{
		$("#vehicleopt").hide();
	}
}

function showVehicleNo(selected_value,policy_type,vehicle_no){
//		alert('selected Value'+selected_value);
//		alert('policy_type '+policy_type);
//		alert('vehicle_no '+vehicle_no);
		if(selected_value== 4){
			if(selected_value !=policy_type){
				//alert(selected_value+"not equal"+policy_type);
				var trValue = $("#edit_vehicleopt_already").html();
				//alert('trValue '+trValue);
				if(trValue !=""){
					//alert('in if trValue ');
					$("#edit_vehicleopt_already").html('');
				}else{
					$("#edit_vehicleopt_already").html("<td style='float:right' class='table_labels'>Vehicle No:&nbsp;</td><td align='left'><input type='text' name='edit_vehicleno' id='edit_vehicleno' value='' /></td>");
				}
			}else{
					//alert('outer else ');
					$("#edit_vehicleopt_already").html("<td style='float:right' class='table_labels'>Vehicle No:&nbsp;</td><td align='left'><input type='text' name='edit_vehicleno' id='edit_vehicleno' value='"+vehicle_no+"' /></td>");
			}
		}else{
			$("#edit_vehicleopt_already").html('');
		}
}

function resetFormValue(){
	$("#vehicleopt").hide();
}

function editCustomerRequest(){
	var policytype  = $("#edit_policytype").val();
	var name        = $("#edit_name").val();
	var email 	    = $("#edit_email").val();

	var policy_no   = $("#edit_policyno").val();
	var exp_date     = $("#edit_expdate").val();
	var amount       = $("#edit_amount").val();
	
	var msg ="";
	if(policytype =="0"){
		msg +="Please Select Policy Type.";
	}
	if(name ==''){
		msg ="Please Enter Name <br/>";
	}
	if(email !=''){
		if(!IsEmail(email)){
		msg +="Please Enter Valid Email Address.<br/>";
		}
	}
	if(policy_no ==''){
		msg +="Please Enter Policy No<br/>";
	}
	if(exp_date ==''){
		msg +="Please Enter Expiry Date<br/>";
	}
	if(amount ==''){
		msg +="Please Enter Amount<br/>";
	}
	
	if(msg!=""){
		showPopupError("error-box round", msg,"popup_editError");
		return false;
	}else{
		$("#dialog_editCustomer").dialog("close");
		var $ajaxUrl = "updateCustomerDetails.html";
		var $ajaxResponseLayer = "response";
		$arguments =$("#frm_editCustomerDetails").serialize();
		sendRequest_nic($ajaxUrl, $arguments, "",$ajaxResponseLayer);
		return false;
	}
}

function showPopupError(className,errorMsg,responseLayer){
	var timeout = 5000;
	$("#"+responseLayer).addClass(className);
	$("#"+responseLayer).html(errorMsg);
	$("#"+responseLayer).show('fast').delay(timeout).hide('fast');
}

function viewCustomerDetail(userdetails_id){
	$("#response").hide();
	$("#filter_div").hide();
	var $ajaxUrl = "viewCustomerDetail.html";
	var $ajaxResponseLayer = "viewCustomerDetail";
	$arguments ="action=viewCustomerDetail&userdetails_id="+userdetails_id;
	sendRequest_nic($ajaxUrl, $arguments, "",$ajaxResponseLayer);
	return false;
}
function back2ListView(){
	$("#response").show();
	$("#filter_div").show();
	$("#viewCustomerDetail").html('');
	
}
function pagination(currentPage ,$ajaxUrl,$ajaxResponseLayer){
	$arguments ="action=listCustomers&current_page="+currentPage+"&record="+$("#record_option").val()+"&"+$("#frm_filterDetails").serialize();;
	sendRequest_nic($ajaxUrl, $arguments, "",$ajaxResponseLayer);
}

function searchResult(){
	var $ajaxUrl = "listCustomer.html";
	var $ajaxResponseLayer = "response";
	var currentPage = $("#cpage").val();
	$arguments ="action=listCustomers&current_page="+currentPage+"&record="+$("#record_option").val()+"&"+$("#frm_filterDetails").serialize();
	sendRequest_nic($ajaxUrl, $arguments, "",$ajaxResponseLayer);
	return false;
	
}

function editCustomerDetail(userdetails_id,policy_no){
	showProgressBar();
	$.get("editCustomerDetail.html", { userdetails_id:userdetails_id, policy_no:policy_no },
			function(data){
				if(data.search("showMessage")>-1){
					humanMsg.displayMsg(data);
					hideProgressBar();
					return false;
				}else{
					$('#dialog_editCustomer').html(data);
					$('#dialog_editCustomer').dialog('open');
					hideProgressBar();
					return false;
				}
				
			 });
}
function showCustomerList(){
	$("#response").show();
	$("#filter_div").show();
	$("#viewCustomerDetail").html('');
	var $ajaxUrl = "listCustomer.html";
	var $ajaxResponseLayer = "response";
	var currentPage 	= $("#cpage").val();
	var record_option   = $("#record_option").val();
	if(currentPage ==undefined || currentPage ==''){
		currentPage =1;
	}
	if(record_option ==undefined || record_option ==''){
		record_option =10;
	}
	$arguments ="action=listCustomers&current_page="+currentPage+"&record="+record_option+"&"+$("#frm_filterDetails").serialize();;
	sendRequest_nic($ajaxUrl, $arguments, "",$ajaxResponseLayer);
	return false;
}
