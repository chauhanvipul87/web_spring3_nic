function showWorkByUser(user_id){
	if(user_id==0){
		msg ="Please select Valid User.";
		humanMsg.displayMsg(msg);
	}else{
		var $ajaxUrl = "viewUserWork.html";
		var $ajaxResponseLayer = "response";
		$arguments ="user_id="+user_id+"&action=viewUserWork&"+$("#frm_viewUserWork").serialize();;
		sendRequest($ajaxUrl, $arguments, "",$ajaxResponseLayer);
		return false;
	}
}

function deleteUser(user_id){
	if(confirm("Are you sure want to delete this user ?")){
		showProgressBar();
		$.get("deleteUserDetails.html", { user_id:user_id },
				function(data){
					if(data.search("showMessage")>-1){
						humanMsg.displayMsg(data);
						hideProgressBar();
						showMemberList();
						return false;
					}
				 });
	}
	return false;
}

function viewUser(user_id){
	showProgressBar();
	$.get("viewUserDetails.html", { user_id:user_id },
			function(data){
				if(data.search("showMessage")>-1){
					humanMsg.displayMsg(data);
					hideProgressBar();
					return false;
				}else{
					$('#dialog_viewuser').html(data);
					$('#dialog_viewuser').dialog('open');
					hideProgressBar();
					return false;
				}
				
			 });
}

function editUserRequest(){
	var username  = $("#edit_username").val();
	var password  = $("#edit_password").val();
	var cpassword = $("#edit_cpassword").val();
	var fname     = $("#edit_fname").val();
	var lname     = $("#edit_lname").val();
	var address   = $("#edit_address").val();
	var phone     = $("#edit_phone").val();
	var email 	  = $("#edit_email").val();
	var usertype  = $("#edit_dropdown-actions").val();
	
	var msg ="";
	if(username ==''){
		msg ="Please Enter Username<br/>";
	}
	if(password !=""){
		if(cpassword ==''){
			msg +="Please Enter Confirm Password<br/>";
		}
	}
	if(password!="" && cpassword !=""){
		if(password !=cpassword){
			msg +="Confirm Password Does Not Match With Password<br/>";
		}
	}
	if(fname ==''){
		msg +="Please Enter First Name<br/>";
	}
	if(lname ==''){
		msg +="Please Enter Last Name<br/>";
	}
	if(address ==''){
		msg +="Please Enter address<br/>";
	}
	if(phone ==''){
		msg +="Please Enter Phone No<br/>";
	}
	if(!IsEmail(email)){
		msg +="Please Enter Valid Email Address.<br/>";
	}
	if(usertype =="0"){
		msg +="Please Select User Type.";
	}
	
	if(msg!=""){
		showPopupError("error-box round", msg,"pop_edit_error");
		return false;
	}else{
		$("#dialog_edituser").dialog("close");
		var $ajaxUrl = "updateUserDetails.html";
		var $ajaxResponseLayer = "response";
		$arguments =$("#frm_editUserDetails").serialize();
		sendRequest($ajaxUrl, $arguments, "",$ajaxResponseLayer);
		return false;
	}
	
}


function editUser(user_id){
	showProgressBar();
	$.get("editUserDetails.html", { user_id:user_id },
			function(data){
				if(data.search("showMessage")>-1){
					humanMsg.displayMsg(data);
					hideProgressBar();
					return false;
				}else{
					$('#dialog_edituser').html(data);
					$('#dialog_edituser').dialog('open');
					hideProgressBar();
					return false;
				}
				
			 });
}

function validateRequest(){
	var username  = $("#username").val();
	var password  = $("#password").val();
	var cpassword = $("#cpassword").val();
	var fname     = $("#fname").val();
	var lname     = $("#lname").val();
	var address   = $("#address").val();
	var phone     = $("#phone").val();
	var email 	  = $("#email").val();
	var usertype  = $("#dropdown-actions").val();
	
	var msg ="";
	if(username ==''){
		msg ="Please Enter Username<br/>";
	}
	if(password ==''){
		msg +="Please Enter Password<br/>";
	}
	if(cpassword ==''){
		msg +="Please Enter Confirm Password<br/>";
	}
	if(password!="" && cpassword !=""){
		if(password !=cpassword){
			msg +="Confirm Password Does Not Match With Password<br/>";
		}
	}
	if(fname ==''){
		msg +="Please Enter First Name<br/>";
	}
	if(lname ==''){
		msg +="Please Enter Last Name<br/>";
	}
	if(address ==''){
		msg +="Please Enter address<br/>";
	}
	if(phone ==''){
		msg +="Please Enter Phone No<br/>";
	}
	if(email !=''){
			if(!IsEmail(email)){
				msg +="Please Enter Valid Email Address.<br/>";
			}
	}
	if(usertype =="0"){
		msg +="Please Select User Type.";
	}
	
	if(msg!=""){
		showPopupError("error-box round", msg,"pop_error");
		return false;
	}else{
		$("#dialog_addnewuser").dialog("close");
		var $ajaxUrl = "addnewuser.html";
		var $ajaxResponseLayer = "response";
		$arguments =$("#frm_newuser").serialize();
		sendRequest($ajaxUrl, $arguments, "",$ajaxResponseLayer);
		return false;
	}
	
}

function showPopupError(className,errorMsg,responseLayer){
	var timeout = 5000;
	$("#"+responseLayer).addClass(className);
	$("#"+responseLayer).html(errorMsg);
	$("#"+responseLayer).show('fast').delay(timeout).hide('fast');
}

function showMemberList(){
	
	var $ajaxUrl = "showMemberList.html";
	var $ajaxResponseLayer = "response";
	$arguments ="action=showMember";
	sendRequest($ajaxUrl, $arguments, "",$ajaxResponseLayer);
	return false;
	
}

function openDialog_addNewUser(){
	$('#dialog_addnewuser').dialog('open');
}
function sendRequest($ajaxUrl,$arguments,$timeStamp,$ajaxResponseLayer){
	
	var $currentTime = new Date();
	var $timeStamp = $currentTime.getHours() + $currentTime.getMinutes()
	+ $currentTime.getSeconds() + $currentTime.getMilliseconds()
	+ $currentTime.getDay() + $currentTime.getMonth()
	+ $currentTime.getFullYear() + Math.random();
	
	 $.ajax({
		url : $ajaxUrl + "?" + $arguments + "&stamp=" + $timeStamp,
		cache : false,
		beforeSend : function() {
			/* call method before send request to server  */
			showProgressBar();
		},
		complete : function($response, $status) {
			/* hide progress bar once we get response */
			hideProgressBar();
			if ($status != "error" && $status != "timeout") {
				/* for set response in div */
				if($ajaxResponseLayer !="")  
			    {	
//					alert('$ajaxUrl ::'+$ajaxUrl);
					//return checkAuthentication($response);
					if($response.responseText.search("showMessage")>-1){
//						alert('in if');
						humanMsg.displayMsg($response.responseText.trim());
						//$("#error").html($response.responseText.trim());
						processAfterResponse($ajaxUrl,$arguments,$timeStamp,$ajaxResponseLayer,$response.responseText);
						return false;
					}
					if($ajaxUrl == 'puturl.html'){
							//code here
					}
					
					if($ajaxUrl =='puturl.html'){
						// $('#dialog_addnewuser').dialog('open');
					}
					if($ajaxUrl =='puturl.html'){
						// $('#dialog_dropoffagentList').dialog('open');
					}

					$("#"+$ajaxResponseLayer).html($response.responseText);
					processAfterResponse($ajaxUrl,$arguments,$timeStamp,$ajaxResponseLayer,$response.responseText);
				}
				
			}
		},
		error : function($obj) {
			/* call when error occurs */
			hideProgressBar();
			alert("Something went wrong while processing your request."+$obj.responseText);
		}
	});  
}

function processAfterResponse($ajaxUrl,$arguments,$timeStamp,$ajaxResponseLayer,$response){
	if($ajaxUrl == 'addnewuser.html' || $ajaxUrl =='updateUserDetails.html'){
		showMemberList();
	}			
}
function showProgressBar() {
		document.getElementById('ajax_loader').style.display = 'block';
}

function hideProgressBar() {
	 document.getElementById('ajax_loader').style.display = 'none';
}

var hideError = function closer() {
	$.modal.close();
	  //alert("Done!"); 
}

function showMessage(msg){
	humanMsg.displayMsg(msg);
}

function IsEmail(email) {
	  var regex = /^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	  return regex.test(email);
}

function onlyIntegerNumber(evt){
	
	var e = evt; // for trans-browser compatibility
  var charCode = e.which || e.keyCode;
  if(charCode == 9){
  	 return true;
  }
  if((charCode >=35 && charCode <38) || charCode ==39 || e.which ==0){
  	 humanMsg.displayMsg("Please enter only digit (0-9)");
  	return false;
  }
  
  if ((charCode > 31) && (charCode < 48 || charCode > 57) ){
  		
  	   humanMsg.displayMsg("Please enter only digit (0-9)");
 	       //alert('Only digit is allowed.');
 	       return false;
 	 }else{
	   return true;
   }
}

function isNumberKey(evt)
{
	var e = evt; // for trans-browser compatibility
    var charCode = e.which || e.keyCode;
    
    if(charCode == 46 || charCode == 9){
    	return true;
    }
    if((charCode >=35 && charCode <38) || charCode ==39 || e.which ==0){
    	humanMsg.displayMsg("Allow only numeric characters (0-9 or .)");
   	 return false;
    }
    if ((charCode > 31) && (charCode < 48 || charCode > 57) ){
    		humanMsg.displayMsg("Allow only numeric characters (0-9 or .)");
   	       return false;
   	 }else{
	   return true;
     }
}
if(typeof String.prototype.trim !== 'function') {
	  String.prototype.trim = function() {
	    return this.replace(/^\s+|\s+$/g, ''); 
	  }
	}