/**
 * @author Victor Z.
 * Created on: 02/16/2020
 * Updated on: 02/16/2020
 * Version: 1.0
 * 
 * 
 * for addpayment.jsp
 * 
 */

function myFunction() {
	var amount = document.getElementById("amount");
	console.log(amount.value);
	console.log(parseInt(amount.value));
	console.log(isNaN(parseFloat(amount.value)));
	if (!isNaN(parseInt(amount.value))) {
		
		amount.value = parseFloat(amount.value);
		
		var result = confirm("Do you confirm this transaction?");
		if (result == true) {
			document.getElementById("command").submit();
		} else {
			var errormsg = document.getElementById("tdwitherror");
			console.log(errormsg);
			document.getElementById("tdwitherror").innerHTML = "<div class=\"login__box-error\">You have not confirmed this transaction.</div>";
		}
		
	} else {
		var errormsg = document.getElementById("tdwitherror");
		console.log(errormsg);
		document.getElementById("tdwitherror").innerHTML = "<div class=\"login__box-error\">Amount of payment must be numbers.</div>";
	}
}