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
			$("#guestDeliveryInfoList").append("<div>" + purchaseInfoArray[count]+"</div>");
		}
		else if((count >= 10) && (count <= 13)){
			if(count == 10){ // カード番号
				$("#guestCreditCardInfoList").append( "<div>" + purchaseInfoArray[count]+"(カード下四桁)</div>");
			}
			else{
				$("#guestCreditCardInfoList").append("<div>" + purchaseInfoArray[count]+"</div>");
			}
		}
	});
	var price = parseInt(purchaseInfoArray[2]);
	var itemCount = parseInt(purchaseInfoArray[0]);

	var subTotal = (price * itemCount);

	var billingAmount = (subTotal + 500); // 一度末尾の「円」を取り除いて500円を足してから「円」をもう一度つける

	$("#subTotal").html("\\" + subTotal);
	$("#BillingAmount").html(billingAmount);


	var sendMailDescriptionInfoArray = purchaseInfoArray;

	$("#guestPurchaseDetermineBtn").on('click',function(){
		sendMailDescriptionInfoArray[0] = billingAmount;
		console.log(sendMailDescriptionInfoArray);
		window.location.href="guestPurchaseDetermine?sendMailDescription=" + sendMailDescriptionInfoArray.toString();
	});

});