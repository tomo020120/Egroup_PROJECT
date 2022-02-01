$(function(){
	var purchaseInfoArray = sessionStorage.getItem('guestPurchaseInfo').split(",");

	console.log(purchaseInfoArray);

	// 購入商品詳細は表示が他と変わるためループ外で処理
	$("#guestPurchaseItemInfo").append("<td>" + purchaseInfoArray[1] + "</td>");
	$("#guestPurchaseItemInfo").append("<td>" + purchaseInfoArray[0] + "</td>");
	$("#guestPurchaseItemInfo").append("<td>" + purchaseInfoArray[2] + "</td>");
	$("#guestPurchaseItemInfo").append("<td><img src=" + purchaseInfoArray[3] + "></td>");

	$.each(purchaseInfoArray,function(index,element){
		var count = (index + 6);

		if((count >= 6) && (count <= 9)){
			$("#guestDeliveryInfoList").append("<p>" + purchaseInfoArray[count] + "</p>");
		}
		else if((count >= 10) && (count <= 13)){
			if(count == 10){ // カード番号
				$("#guestCreditCardInfoList").append("<p>下四桁:" + purchaseInfoArray[count] + "</p>");
			}
			else{
				$("#guestCreditCardInfoList").append("<p>" + purchaseInfoArray[count] + "</p>");
			}
		}
	});
	var price = parseInt(purchaseInfoArray[2].slice(0,-1));
	var itemCount = parseInt(purchaseInfoArray[0]);

	var subTotal = (price * itemCount);

	var billingAmount = (subTotal + 500); // 一度末尾の「円」を取り除いて500円を足してから「円」をもう一度つける

	$("#subTotal").html(subTotal);
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