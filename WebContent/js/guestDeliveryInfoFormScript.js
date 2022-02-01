$(function(){
	var guestPurchaseInfoArray = sessionStorage.getItem('guestPurchaseInfo').split(",");

	var length = parseInt(guestPurchaseInfoArray.length);

	$("#guestCreditCardInfoFormJumpBtn").on('click',function(){

		var fullAddress = "";
		var tel = "";

		$("#userInfoInputArea,#addDeliveryInfoForm").find("input").each(function(index,element){
			switch(index){
			case 0:
			case 1:
			case 2:
				guestPurchaseInfoArray[length + index] = element.value;
				break;
			case 3:
			case 4:
				tel += ((element.value) + "-");
				break;
			case 5:
				tel += (element.value);
				guestPurchaseInfoArray[length + 3] = tel;
				break;
			case 6:
				guestPurchaseInfoArray[length + 4] = element.value;
				break;
			case 7:
			case 8:
			case 9:
				fullAddress += element.value
				guestPurchaseInfoArray[length + 5] = fullAddress;
				break;
			}
		});

		console.log(guestPurchaseInfoArray);

		sessionStorage.setItem('guestPurchaseInfo',guestPurchaseInfoArray.toString());

		window.location.href="guestCreditCardInfoForm";
	});
});