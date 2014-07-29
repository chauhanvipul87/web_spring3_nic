function openDialogRenewPolicy(){
	$('#dialog_renewpolicy').dialog('open');
}

function validateRenewPolicyRequest() {
	
	var policy_no   = $("#renew_policyno").val();
	var exp_date     = $("#renew_expirydate").val();
	var amount       = $("#renew_amount").val();
	
	var msg ="";

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
		showPopupError("error-box round", msg,"pop_renewpolicyerror");
		return false;
	}else{
		$("#dialog_renewpolicy").dialog("close");
		var $ajaxUrl = "addRenewPolicyDetails.html";
		var $ajaxResponseLayer = "response";
		$arguments =$("#frm_renewpolicy").serialize();
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