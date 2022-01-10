function setTelNumber(fullTelephoneNumber){
	console.log(fullTelephoneNumber);
	var splitTel = fullTelephoneNumber.split('-');

	document.getElementById('firstTelephoneNumber').value = splitTel[0];
	document.getElementById('secondTelephoneNumber').value = splitTel[1];
	document.getElementById('thirdTelephoneNumber').value = splitTel[2];
}

function setAddress(fullAddress){
	var splitAddress = fullAddress.split('\/'); // エスケープ処理

	document.getElementById('address').value = splitAddress[0];
	document.getElementById('houseNumber').value = splitAddress[1];
	document.getElementById('buildingName').value = splitAddress[2];
}

