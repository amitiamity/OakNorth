/**
 * 
 */

function getOfficerDetails(){
	
	var q = $("#query").value;
	var url = "/search/officers";
	var apikey = "63dGXMLQM6LXd58xKat3a64RHhsuY2Sr3AqVmBfW";
	var fnSuccess = function (dataReceived) {
		    if(dataReceived) {
		        alert("Welcome "+dataReceived.name);
		    }else{
		        alert("Authentication failed")
		    }
		    };
		 
    var fnError = function (e) {
		        alert("Error");
		    };
   console.log("Enter here 1");
    oakNorthajaxCall(url,fnSuccess,fnError);
}

function oakNorthajaxCall(url,fnSuccess,fnError){
	console.log (url);
    $.ajax({
        type:'GET',
        url:url,
        contentType:"application/json",
        success:fnSuccess,
        error: fnError
    });
}
