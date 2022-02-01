$(function(){
	/*クレジットカード番号入力中動的に、カード会社判定を行う。*/
	$("#creditCardNo").on("input",function(){
		var inputNo = $(this).val(); //入力値取得
		var firstCardNo = inputNo.slice(0,1);
		var company = "";

		if(firstCardNo == 4){ //頭文字で判定
			company = "VISA";
		}else if(firstCardNo == 5){
			company = "Master Card"
		}else{
			var firstTwoDigitsCardNo = inputNo.slice(0,2);

			if(firstTwoDigitsCardNo == 35){
				company = "JCB";
			}else if(firstTwoDigitsCardNo == 36){
				company = "Diners Club";
			}else if((firstTwoDigitsCardNo == 34) || (firstTwoDigitsCardNo == 37 )){
				company = "American Express";
			}
		}

		$("#cardCompany").html(company); // 判定結果の文字列をセット
	});

	var guestPurchaseInfoArray = sessionStorage.getItem('guestPurchaseInfo').split(",");

	var length = parseInt(guestPurchaseInfoArray.length);

	$("#guestPurchaseConfirmPageJumpBtn").on('click',function(){
		var month = "";
		var year = "";
		var expirationDate = "";

		$("#addCreditCardForm").find("input,div,select").each(function(index,element){
			switch(index){
				case 0:guestPurchaseInfoArray[length + index] = (element.value).slice(-4);
				break;
				case 1:guestPurchaseInfoArray[length + index] = $(element).text();
				break;
				case 2:guestPurchaseInfoArray[length + index] = element.value;
				break;
				case 3:month = element.value;
				break;
				case 4:year = element.value;
				break;
			}
		});

		expirationDate = (year + "/" + month);
		guestPurchaseInfoArray[length + 3] = expirationDate;

		console.log(guestPurchaseInfoArray);

		sessionStorage.setItem('guestPurchaseInfo',guestPurchaseInfoArray.toString());

		window.location.href="guestPurchaseConfirmPage";
	});
});