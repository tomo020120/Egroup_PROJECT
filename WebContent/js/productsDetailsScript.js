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
	var favoriteFlag = $("body").data("favorite");

	if(favoriteFlag){
		$("#favoriteBtn").css('background-color','pink');
	}

	$("#nologinPurchaseBtn").on('click',function(){
		var guestPurchaseInfoArray = new Array();

		guestPurchaseInfoArray[0] = $("#orderCount").val();

		$("body").find(".targetInfo").each(function(index,element){
			var count = (parseInt(index) + 1)

			if(count == 3){
				guestPurchaseInfoArray[count] = $("#itemPict").attr('src');
			}else{
				guestPurchaseInfoArray[count] = element.innerText;
			}
		});

		sessionStorage.setItem('guestPurchaseInfo',guestPurchaseInfoArray.toString()); // sessionに格納

		window.location.href="guestDeliveryInfoForm";
	});
});
