$(function(){
	$("#guestCreditCardInfoFormJumpBtn").on('click',function(){
		var guestPurchaseInfoArray = sessionStorage.getItem('guestPurchaseInfo').split(",");

		var fullAddress = "";
		var tel = "";

		$("#addDeliveryInfoForm").find("input").each(function(index,element){
			if(index == 1 || index == 2 || index == 3){
				tel += (element.value);
				if(index != 3){
					tel += "-";
				}

				guestPurchaseInfoArray[6] = tel;
			}else if(index == 4){
				guestPurchaseInfoArray[7] = element.value;
			}else if(index == 5 || index == 6 || index == 7){
				fullAddress += element.value;

				guestPurchaseInfoArray[8] = fullAddress;
			}else{
				guestPurchaseInfoArray[parseInt(index) + 5] = element.value;
			}
		});

		sessionStorage.setItem('guestPurchaseInfo',guestPurchaseInfoArray.toString());

		window.location.href="guestCreditCardInfoForm";
	});
});