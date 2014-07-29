function sendRequest_nic($ajaxUrl,$arguments,$timeStamp,$ajaxResponseLayer){
	
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
	if($ajaxUrl == 'updateCustomerDetails.html' || $ajaxUrl =='addNewCustomer.html'){
		showCustomerList();
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