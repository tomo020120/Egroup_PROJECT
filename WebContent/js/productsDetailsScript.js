function favoriteClick(obj){
	if(obj.style.backgroundColor == ""){
		obj.style.backgroundColor = "pink";
		window.location.href = "addFavorite?itemId=" + obj.value;
	}else{
		obj.style.backgroundColor = "";
		window.location.href = "deleteFavorite?itemId=" + obj.value;
	}
}

$(function(){
	var token = $("#token").val();

	console.log(token);
	if(token != ""){ // ログインしてるときはゲスト購入のボタンを表示しない
		$("#nologinPurchaseBtn").hide();
	}else{
		$("#nologinPurchaseBtn").show();
	}


	var favoriteFlag = $("body").data("favorite");

	if(favoriteFlag){
		$("#favoriteBtn").css('background-color','pink');
	}

	$("#nologinPurchaseBtn").on('click',function(){
		var guestPurchaseInfoArray = new Array();

		guestPurchaseInfoArray[0] = $("#orderCount").val();

		guestPurchaseInfoArray[1] = $("#itemName").val();

		guestPurchaseInfoArray[2] = $("#itemPrice").val();

		guestPurchaseInfoArray[3] = $("#itemPict").attr('src');

		sessionStorage.setItem('guestPurchaseInfo',guestPurchaseInfoArray.toString()); // sessionに格納

		console.log(sessionStorage.getItem('guestPurchaseInfo').split(","));
		window.location.href="guestDeliveryInfoForm";
	});
});
