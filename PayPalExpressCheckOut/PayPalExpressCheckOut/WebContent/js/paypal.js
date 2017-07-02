function acceptOrderDetails() {
    
	var firstname = document.getElementById("FirstName").value;
	var lastname = document.getElementById("LastName").value;
    var transactionamt = document.getElementById("TransactionAmount").value;
    var currency = document.getElementById("Currency").value;
    var orderid = document.getElementById("OrderID").value;
    var orderDesc = document.getElementById("OrderDesc").value;
    var line1 = document.getElementById("Line1").value;
    var line2 = document.getElementById("Line2").value;
    var city = document.getElementById("City").value;
    var state = document.getElementById("State").value;
    var countryCode = document.getElementById("CountryCode").value;
    var postalCode = document.getElementById("PostalCode").value;
    var phoneNumber = document.getElementById("PhoneNum").value;
    
    
    //document.getElementById("demo").innerHTML = orderId;
    var messageBox  = document.getElementById("demo");
    messageBox.innerHTML = "";
    messageBox.innerHTML += "FirstName: " + firstname + "<br/>";
    messageBox.innerHTML += "LastName: " + lastname + "<br/>";
    messageBox.innerHTML += "TransactionAmount: " + transactionamt + "<br/>";
    messageBox.innerHTML += "Currency: " + currency + "<br/>";
    messageBox.innerHTML += "OrderID: " + orderid + "<br/>";
    messageBox.innerHTML += "OrderDesc: " + orderDesc + "<br/>";
    messageBox.innerHTML += "Line1: " + line1 + "<br/>";
    messageBox.innerHTML += "Line2: " + line2 + "<br/>";
    messageBox.innerHTML += "City: " + city + "<br/>";
    messageBox.innerHTML += "State: " + state + "<br/>";
    messageBox.innerHTML += "CountryCode: " + countryCode + "<br/>";
    messageBox.innerHTML += "PostalCode: " + postalCode + "<br/>";
    messageBox.innerHTML += "PhoneNumber: " + phoneNumber + "<br/>";
    
}

