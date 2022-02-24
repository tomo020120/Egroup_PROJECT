$(function(){
	var purchaseInfoArray = sessionStorage.getItem('guestPurchaseInfo').split(",");

	console.log(purchaseInfoArray);

	// 購入商品詳細は表示が他と変わるためループ外で処理
	$("#itemName").text(purchaseInfoArray[1]);
	$("#itemCount").text("個数"+purchaseInfoArray[0]);
	$("#itemPrice").text(purchaseInfoArray[2]);
	$("#itemPic").append("<img src=" +purchaseInfoArray[3]+" width=500px>");
	$.each(purchaseInfoArray,function(index,element){
		var count = (index + 6);

		if((count >= 6) && (count <= 9)){
			$("#guestDeliveryInfoList").append(purchaseInfoArray[count]+"<br>");
		}
		else if((count >= 10) && (count <= 13)){
			if(count == 10){ // カード番号
				$("#guestCreditCardInfoList").append( purchaseInfoArray[count]+"(カード下四桁)"+"<br>");
			}
			else{
				$("#guestCreditCardInfoList").append( purchaseInfoArray[count]+"<br>");
			}
		}
	});
	var price = parseInt(purchaseInfoArray[2]);
	var itemCount = parseInt(purchaseInfoArray[0]);

	var subTotal = (price * itemCount);

	var billingAmount = (subTotal + 500); // 一度末尾の「円」を取り除いて500円を足してから「円」をもう一度つける

	$("#subTotal").html("\\" + subTotal);
	$("#BillingAmount").html(billingAmount);


	var sendMailDescriptionInfoArray = new Array();

	$("#guestPurchaseDetermineBtn").on('click',function(){
		$("body").find("#guestDeliveryInfoList p,#guestCreditCardInfoList p,#BillingAmount ").each(function(index,element){
			sendMailDescriptionInfoArray[index] = $(element).html();
		});

		var length = sendMailDescriptionInfoArray.length;

		sendMailDescriptionInfoArray[length] = purchaseInfoArray[4];
		sendMailDescriptionInfoArray[length + 1] = purchaseInfoArray[5];

		console.log(sendMailDescriptionInfoArray)

		window.location.href="guestPurchaseDetermine?sendMailDescription=" + sendMailDescriptionInfoArray.toString();
	});

});