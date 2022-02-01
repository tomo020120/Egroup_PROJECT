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

	$("#guestPurchaseComfirmPageJumpBtn").on('click',function(){
		var array = sessionStorage.getItem('guestPurchaseInfo').split(",");

		var month = "";
		var year = "";
		var expirationDate = "";

		$("#addCreditCardForm").find("input,div,select").each(function(index,element){
			switch(index){
				case 0:array[9] = element.value;
				break;
				case 1:array[10] = $(element).text();
				break;
				case 2:array[11] = element.value;
				break;
				case 3:month = element.value;
				break;
				case 4:year = element.value;
				break;
			}
		});

		expirationDate = (year + "/" + month);
		array[12] = expirationDate;

		sessionStorage.setItem('guestPurchaseInfo',array.toString());

		window.location.href="guestPurchaseComfirmPage";
	});
});