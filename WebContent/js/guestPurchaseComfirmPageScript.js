$(function(){
	var purchaseInfoArray = sessionStorage.getItem('guestPurchaseInfo').split(",");

	console.log(purchaseInfoArray);

	$.each(purchaseInfoArray,function(index,element){
		console.log(element);
	});
});