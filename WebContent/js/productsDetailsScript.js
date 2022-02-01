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

		$("#itemInfoList").find("td").each(function(index,element){
			if(index == 4){
				guestPurchaseInfoArray[index] = $("img").attr('src');
			}else if(index == 5){
				return false;
			}else{
				guestPurchaseInfoArray[index] = element.innerText;
			}
		});

		sessionStorage.setItem('guestPurchaseInfo',guestPurchaseInfoArray.toString()); // sessionに格納

		window.location.href="guestDeliveryInfoForm";
	});
});
